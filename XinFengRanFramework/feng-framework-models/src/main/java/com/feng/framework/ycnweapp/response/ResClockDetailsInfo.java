package com.feng.framework.ycnweapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ClockDetailsInfo
 * @Author 小风谷
 * @Date 2021/3/21 10:25
 * @Version 1.0
 * @Description  接收打卡详情页
 */
@Data
@NoArgsConstructor
public class ResClockDetailsInfo {

    private String className;  //打卡名字.


    private String ImgUrl;     //打卡图片

    private List<ResClockInfo> intrInfoList;    //打卡简介 id:'简介记录id',content:'简介内容',order:'排序',type:'简介类型'

    private Long attendUserNum;   //打卡参与的人数

    private Long allPunchCardNum;  //总打卡人数

    private Long creatorId;  // 打卡创建人的姓名

    private String nickName; //打卡创建人的名字

    private Long sex;  //打卡创建人的性别

    private String introduce; //打卡创建人介绍

    private String weChatNum; //打卡创建人的微信号

    private String avatarUrl; //打卡创建人头像

    private List<ResAttendUserInfo> attendUserInfo; //此项目参与人的列表


}
