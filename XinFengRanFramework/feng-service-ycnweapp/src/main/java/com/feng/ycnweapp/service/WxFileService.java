package com.feng.ycnweapp.service;

import com.feng.framework.model.response.AjaxResult;
import com.feng.ycnweapp.tools.service.UploadFileTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName WxFileService
 * @Author 小风谷
 * @Date 2021/3/27 11:09
 * @Version 1.0
 * @Description
 */
@Service
public class WxFileService {


    private final UploadFileTool uploadFileTool;

    public WxFileService(UploadFileTool uploadFileTool) {
        this.uploadFileTool = uploadFileTool;
    }

    @Transactional(rollbackFor = Exception.class)
    public AjaxResult uploadFile(String projectId, String diaryId,String userId, String resourceType, MultipartFile multipartFile) {
        AjaxResult ajaxResult = new AjaxResult();

        ajaxResult.setCode(200);
        ajaxResult.setMsg("文件上传成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(uploadFileTool.upload(projectId, diaryId,userId,resourceType ,multipartFile));
        return ajaxResult;

    }

    //微信文件上传 七牛云版
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult uploadFileQnu(String projectId, String diaryId,String userId, String resourceType, MultipartFile multipartFile) {
        AjaxResult ajaxResult = new AjaxResult();
        String fileresult=uploadFileTool.uploadQnu(projectId, diaryId,userId,resourceType ,multipartFile);
        if(fileresult=="success") {
            ajaxResult.setCode(200);
            ajaxResult.setMsg("文件上传成功");
            ajaxResult.setSuccess(true);
            ajaxResult.setData(fileresult);
        }else {
            ajaxResult.setCode(201);
            ajaxResult.setMsg("文件上传失败");
            ajaxResult.setSuccess(false);
            ajaxResult.setData(fileresult);
        }
        return ajaxResult;

    }
}
