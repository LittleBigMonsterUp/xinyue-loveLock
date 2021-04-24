package com.feng.framework.ycnweapp.response;

import lombok.Data;

/**
 * @ClassName ResClockInfo
 * @Author 小风谷
 * @Date 2021/3/21 20:59
 * @Version 1.0
 * @Description
 */
@Data
public class ResClockInfo {

    private String id;  //简介id

    private String content;     //打卡简介内容

    private String order;     //排序

    private String type; //打卡类型
}
