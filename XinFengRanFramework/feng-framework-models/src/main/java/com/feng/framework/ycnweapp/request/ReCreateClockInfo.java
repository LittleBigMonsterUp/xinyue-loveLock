package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ResCreateClockInfo
 * @Author 小风谷
 * @Date 2021/3/28 11:10
 * @Version 1.0
 * @Description 创建打卡
 */
@Data
public class ReCreateClockInfo {

    private  String project_name;
    private  String clock_course_name;
    private  String type_label; //选择标签名字
    private  Long creator_id;

}
