package com.feng.ycnweapp.tools.service;


import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.tools.UploadFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口定义
 *
 * @author zhuhuix
 * @date 2020-04-20
 */
public interface UploadFileTool {

    /**
     * 文件上传
     *
     * @param projectId   圈子id
     * @param diaryId     打卡日记id
     * @param resourceType 资源类型
     * @param multipartFile 文件
     * @return 上传信息
     */
   UploadFile upload(String projectId, String diaryId, String userId,String resourceType, MultipartFile multipartFile);

    /**
     * 文件上传
     *
     * @param projectId   圈子id
     * @param diaryId     打卡日记id
     * @param resourceType 资源类型
     * @param multipartFile 文件
     * @return 上传信息
     */
    String uploadQnu(String projectId, String diaryId,String userId,String resourceType, MultipartFile multipartFile);
}
