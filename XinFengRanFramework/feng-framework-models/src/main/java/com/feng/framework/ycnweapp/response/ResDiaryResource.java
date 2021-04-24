package com.feng.framework.ycnweapp.response;

import lombok.Data;

/**
 * @ClassName ResDiaryResource
 * @Author 小风谷
 * @Date 2021/3/23 9:57
 * @Version 1.0
 * @Description  媒体资源路径表
 */
@Data
public class ResDiaryResource {

    /**打卡日记相关的资源文件记录id */
    public  Long id;
    /** 资源文件路径信息*/
    public  String  resourceUrl;

   /** 1-图片 2-音频 3-视频 */
    public  Long  type;

}
