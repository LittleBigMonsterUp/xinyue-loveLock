package com.feng.framework.ycnweapp.response;

import lombok.Data;

/**
 * @ClassName ResClockList
 * @Author 小风谷
 * @Date 2021/3/26 16:11
 * @Version 1.0
 * @Description 主页打卡列表
 */
@Data
public class ResClockList {

    public  Long id;
    public  String  cover_img_url;
    public  String  project_name;   //打卡名字
    public  Long    all_punch_card_num;   //总打卡人次数
    public  Long    attend_user_num;   //参与打开人数

}
