package com.feng.api.yncweapp;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.User;
import com.feng.framework.ycnweapp.request.ReWeChatAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName IUser
 * @Author 小风谷
 * @Date 2021/3/23 22:02
 * @Version 1.0
 * @Description 用户登入
 */
@Api(value = "用户登入接口")
public interface IUser {

    @ApiOperation(value = "微信授权登入")
    public AjaxResult WechatLogin(@RequestBody ReWeChatAuth authUser, HttpServletRequest request);
}
