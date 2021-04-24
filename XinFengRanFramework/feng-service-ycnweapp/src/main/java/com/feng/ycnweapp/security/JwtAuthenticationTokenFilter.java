package com.feng.ycnweapp.security;

import com.feng.ycnweapp.config.JwtSecurityProperties;
import com.feng.ycnweapp.utils.JwtTokenUtils;
import com.feng.ycnweapp.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*************** 1、***************/
/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * 确保在一次请求只通过一次filter，而不需要重复执行
 * @ClassName JwtAuthenticationTokenFilter
 * @Author 小风谷
 * @Date 2021/3/23 23:15
 * @Version 1.0
 * @Description jwt 认证拦截器 用于拦截 请求 提取jwt 认证
 * 继承 OncePerRequestFilter，重写 doFilterInternal 方法。
 * 在 doFilterInternal中获取 header 中带着的验证信息，解析 token 值获取用
 * 户信息，并将用户信息设置到 SecurityContextHolder 中。
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        JwtSecurityProperties jwtSecurityProperties = SpringContextHolder.getBean(JwtSecurityProperties.class);
        String requestRri = httpServletRequest.getRequestURI();
        //获取request token
        String token = null;
        // // 获取 header 解析出 jwt 并进行认证 无token 直接进入下一个过滤器  因为  SecurityContext 的缘故 如果无权限并不会放行
        String bearerToken = httpServletRequest.getHeader(jwtSecurityProperties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtSecurityProperties.getTokenStartWith())) {
            token = bearerToken.substring(jwtSecurityProperties.getTokenStartWith().length());
        }
        //验证token
        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
            //得到token 信息
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
        } else {
            log.debug("no valid JWT token found, uri: {}", requestRri);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
