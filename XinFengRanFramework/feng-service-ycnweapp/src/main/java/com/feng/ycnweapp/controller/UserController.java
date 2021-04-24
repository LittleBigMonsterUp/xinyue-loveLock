package com.feng.ycnweapp.controller;

import com.feng.api.yncweapp.IUser;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReWeChatAuth;
import com.feng.ycnweapp.service.WeChatAuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Author 小风谷
 * @Date 2021/3/23 22:03
 * @Version 1.0
 * @Description  用户登入
 */
@RestController
@RequestMapping("/ClockUser")
public class UserController implements IUser {

    @Autowired
    WeChatAuthService weChatAuthService;

    @ApiOperation("登录授权")
    @PostMapping(value = "/WechatLogin")
    @Override
    public AjaxResult WechatLogin(@RequestBody ReWeChatAuth authUser, HttpServletRequest request) {
        return weChatAuthService.WechatLogin(authUser,request);
    }
}
