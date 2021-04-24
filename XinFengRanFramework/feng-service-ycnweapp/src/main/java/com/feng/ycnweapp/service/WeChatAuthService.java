package com.feng.ycnweapp.service;

import com.alibaba.fastjson.JSONObject;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.User;
import com.feng.framework.ycnweapp.request.ReWeChatAuth;
import com.feng.ycnweapp.common.Constant;
import com.feng.ycnweapp.dao.UserRepository;
import com.feng.ycnweapp.utils.JwtTokenUtils;
import com.feng.ycnweapp.wechat.WxMiniApi;
import com.feng.ycnweapp.wechat.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WeChatAuthService
 * @Author 小风谷
 * @Date 2021/3/24 16:26
 * @Version 1.0
 * @Description  微信授权登入
 * 1、小程序端调用 wx.login()向微信接口服务获取 临时登录凭证code ，并上传至开发者服务端。
 * 2、开发者服务端向微信服务接口服务调用 auth.code2Session 接口，换取 用户唯一标识 OpenID 和 会话密钥 session_key。
 * 3、开发者服务端根据session_key等信息,基于JWT标准，生成自定义的网络令牌token，返回至小程序端存储。
 * 4、App 保存在本地后在后面的每次请求中都在 header 中带着 token 进行请求。后端重写过滤器，在过滤器中解析 token 值并将 token 中带的对象放到登录用户中，让 security 认为请求已经是在登录状态下进行。
 */
@Service
public class WeChatAuthService {

    @Value("${wxMini.appId}")
    private String appId;
    @Value("${wxMini.secret}")
    private String secret;

    private final JwtTokenUtils jwtTokenUtils;
    private final WxMiniApi wxMiniApi;

    @Autowired
    UserRepository userRepository;


    public WeChatAuthService(JwtTokenUtils jwtTokenUtils, WxMiniApi wxMiniApi) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.wxMiniApi = wxMiniApi;
    }


    @Transactional(rollbackFor = Exception.class)
    public AjaxResult WechatLogin(ReWeChatAuth authUser, HttpServletRequest request) {

        AjaxResult ajaxResult = new AjaxResult();

        //authType=1代表是微信登录

        System.out.println("authUser:"+authUser);
        JSONObject jsonObject = wxMiniApi.authCode2Session(appId, secret, authUser.getCode());
        if (jsonObject == null) {
            throw new RuntimeException("调用微信端授权认证接口错误");
        }
        String openId = jsonObject.getString(Constant.OPEN_ID);
        String sessionKey = jsonObject.getString(Constant.SESSION_KEY);
        String unionId = jsonObject.getString(Constant.UNION_ID);
        if (StringUtils.isEmpty(openId)) {
            ajaxResult.setCode(Integer.parseInt(jsonObject.getString(Constant.ERR_CODE)));
            ajaxResult.setMsg(jsonObject.getString(Constant.ERR_MSG));
            ajaxResult.setSuccess(false);
            return ajaxResult;
        }
        authUser.setOpenId(openId);
        System.out.println("openId:"+openId);
        //判断用户表中是否存在该用户，不存在则进行解密得到用户信息，并进行新增用户
        User resultUser = userRepository.findByopenId(openId);
        if (resultUser == null) {
            String userInfo = WeChatUtil.decryptData(authUser.getEncryptedData(), sessionKey, authUser.getIv());
            if (StringUtils.isEmpty(userInfo)) {
                throw new RuntimeException("解密用户信息错误");
            }
            User user = JSONObject.parseObject(userInfo, User.class);
            if (user == null) {
                throw new RuntimeException("填充用户对象错误");
            }
            System.out.println("user");
            user.setUnionId(unionId);
            user.setOpenId(openId);
            user.setSessionKey(sessionKey);
            user.setUName(authUser.getNickName());
            user.setAvatarUrl(authUser.getAvatarUrl());
            user.setSex(authUser.getGender());
            userRepository.save(user);
            authUser.setUserInfo(user);

        } else {
            authUser.setUserInfo(resultUser);
        }

        //创建token
        String token = jwtTokenUtils.createToken(openId, null);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("生成token错误");
        }
        authUser.setToken(token);

        ajaxResult.setCode(200);
        ajaxResult.setMsg("获取token成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(authUser);
        return  ajaxResult;
    }

}
