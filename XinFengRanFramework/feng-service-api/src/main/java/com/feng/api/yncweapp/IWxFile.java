package com.feng.api.yncweapp;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReWeChatAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IWxFile
 * @Author 小风谷
 * @Date 2021/3/27 10:10
 * @Version 1.0
 * @Description
 */
@Api(value = "微信文件上传")
public interface IWxFile {


    @ApiOperation(value = "微信文件上传")
    public AjaxResult fileUpload(HttpServletRequest request);

    @ApiOperation(value = "微信文件上传(七牛云版)")
    public AjaxResult fileUploadQnu(HttpServletRequest request);



}
