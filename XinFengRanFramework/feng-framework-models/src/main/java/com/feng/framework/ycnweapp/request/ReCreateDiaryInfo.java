package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReCreateDiaryInfo
 * @Author 小风谷
 * @Date 2021/3/29 11:43
 * @Version 1.0
 * @Description 接收创建打卡日记实体类
 */
@Data
public class ReCreateDiaryInfo {

    private  Long project_id;
    private  Long user_id;

    /*** 打卡日记文件内容*/
    private  String text_content;
    /*** 打卡日记的地理位置信息*/
    private  String punch_card_address;
    /*** 地理位置对应的经度*/
    private  String address_longitude;
    /*** 地理位置对应的纬度*/
    private  String address_latitude;
    /*** 打卡日记可见类型*/
    private  Long visible_type;
    /*** 0--非补打卡日记*/
    private  Long is_repair_diary;


}
