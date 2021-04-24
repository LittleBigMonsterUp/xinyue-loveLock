package com.feng.ycnweapp.controller;

import com.feng.api.yncweapp.IWxFile;
import com.feng.framework.model.response.AjaxResult;
import com.feng.ycnweapp.service.WxFileService;
import com.sun.xml.bind.api.impl.NameConverter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxFileController
 * @Author 小风谷
 * @Date 2021/3/27 11:07
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/WxFile")
public class WxFileController implements IWxFile {

    @Autowired
    WxFileService wxFileServicef;




    @ApiOperation("微信上传文件")
    @PostMapping(value = "/FileUpload")
    @Override
    public AjaxResult fileUpload(HttpServletRequest request) {
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;

        MultipartFile multipartFile = req.getFile("diaryResourceFile");
        String projectId = req.getParameter("projectId");
        String diaryId = req.getParameter("diaryId");
        String userId = req.getParameter("userId");
        String resourceType = req.getParameter("resourceType");
        System.out.println("projectId:"+projectId+"diaryId"+diaryId+"resourceType"+resourceType+"multipartFile"+multipartFile.getSize());

        return wxFileServicef.uploadFile(projectId,diaryId,userId,resourceType,multipartFile);
    }


    @ApiOperation("微信上传文件(七牛云版)")
    @PostMapping(value = "/fileUploadQnu")
    @Override
    public AjaxResult fileUploadQnu(HttpServletRequest request) {

        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("diaryResourceFile");
        String projectId = req.getParameter("projectId");
        String diaryId = req.getParameter("diaryId");
        String userId = req.getParameter("userId");
        String resourceType = req.getParameter("resourceType");
        System.out.println("projectId:"+projectId+" diaryId:"+diaryId+" userId:"+userId+" resourceType:"+resourceType+" multipartFile:"+multipartFile.getSize());


        return wxFileServicef.uploadFileQnu(projectId,diaryId,userId,resourceType,multipartFile);
    }
}
