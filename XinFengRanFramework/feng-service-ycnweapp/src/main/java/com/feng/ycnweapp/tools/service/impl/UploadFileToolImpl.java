package com.feng.ycnweapp.tools.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.DiaryResource;
import com.feng.ycnweapp.common.Constant;
import com.feng.framework.ycnweapp.tools.UploadFile;
import com.feng.ycnweapp.dao.DiaryResourceRepository;
import com.feng.ycnweapp.tools.repository.UploadFileRepository;
import com.feng.ycnweapp.tools.service.UploadFileTool;
import com.feng.ycnweapp.utils.PictureUtil;
import com.feng.ycnweapp.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件上传实现类
 *
 * @author zhuhuix
 * @date 2020-04-20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UploadFileToolImpl implements UploadFileTool {

    private final UploadFileRepository uploadFileRepository;

    @Autowired
    DiaryResourceRepository diaryResourceRepository;

    @Value("${uploadFile.path}")
    private String path;

    @Value("${uploadFile.maxSize}")
    private long maxSize;

    public UploadFileToolImpl(UploadFileRepository uploadFileRepository) {
        this.uploadFileRepository = uploadFileRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadFile upload(String projectId, String diaryId,String userId, String resourceType, MultipartFile multipartFile) {

        System.out.println("projectId:"+projectId+"diaryId"+diaryId+"resourceType"+resourceType+"multipartFile");
        //检查文件大小
        if (multipartFile.getSize() > maxSize * Constant.MB) {
            throw new RuntimeException("超出文件上传大小限制" + maxSize + "MB");
        }
        //获取上传文件的主文件名与扩展名
        String primaryName = FileUtil.mainName(multipartFile.getOriginalFilename());
        String extension = FileUtil.extName(multipartFile.getOriginalFilename());
        //根据文件扩展名得到文件类型
        String type = getFileType(extension);
        //给上传的文件加上时间戳
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmmssS");
        String nowStr = "-" + date.format(format);
        String fileName = primaryName + nowStr + "." + extension;

        try {
            String filePath = path + type + File.separator + fileName;
            File dest = new File(filePath).getCanonicalFile();
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            multipartFile.transferTo(dest);
            if (ObjectUtil.isNull(dest)) {
                throw new RuntimeException("上传文件失败");
            }

            UploadFile uploadFile = new UploadFile(projectId, fileName, primaryName, extension, dest.getPath(), type, multipartFile.getSize(), projectId);
            DiaryResource diaryResource =new DiaryResource();
            diaryResource.setDiaryId(Long.parseLong(diaryId));
            diaryResource.setProjectId(Long.parseLong(projectId) );
            diaryResource.setResourceUrl(dest.getPath());
            diaryResource.setType(Long.parseLong(resourceType));
            diaryResource.setSize(multipartFile.getSize());
            diaryResource.setUserId(Long.parseLong(userId));
            diaryResource.setFileName(fileName);
            diaryResourceRepository.save(diaryResource);
            System.out.println("文件上传成功保存后的数据"+uploadFile);
            return uploadFileRepository.save(uploadFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public String uploadQnu(String projectId, String diaryId,String userId, String resourceType, MultipartFile multipartFile) {

        // 用来获取其他参数
       // MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        //String name = params.getParameter("username");
       //System.out.println("params"+params);
       // System.out.println("name:"+name);
        String fileResults;
        try {
            if (!multipartFile.isEmpty()) {
                FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
                String fileName= PictureUtil.getRandomFileName(10);
                String path = QiniuUtils.uploadQNImg(inputStream,fileName); // KeyUtil.genUniqueKey()生成图片的随机名
                if(path==null || path.length()==0){
                    fileResults= "error"; //七牛云图片上传失败
                }
                String savaPath="https://"+path;
                DiaryResource diaryResource =new DiaryResource();
                //diaryResource.setDiaryId(Long.parseLong(diaryId));
                diaryResource.setProjectId(Long.parseLong(projectId) );
                diaryResource.setResourceUrl(savaPath);
                diaryResource.setDiaryId(Long.parseLong(diaryId));
                diaryResource.setType(Long.parseLong(resourceType));
                diaryResource.setSize(multipartFile.getSize());
                diaryResource.setFileName(savaPath);
                diaryResource.setUserId(Long.parseLong(userId));

                diaryResourceRepository.save(diaryResource);
                System.out.print("七牛云返回的图片链接:" + savaPath);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        fileResults="success";
        return fileResults;
    }

    /**
     * 根据文件扩展名给文件类型
     *
     * @param extension 文件扩展名
     * @return 文件类型
     */
    private static String getFileType(String extension) {
        String document = "txt doc pdf ppt pps xlsx xls docx csv";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if (image.contains(extension)) {
            return "image";
        } else if (document.contains(extension)) {
            return "document";
        } else if (music.contains(extension)) {
            return "music";
        } else if (video.contains(extension)) {
            return "video";
        } else {
            return "other";
        }
    }
}
