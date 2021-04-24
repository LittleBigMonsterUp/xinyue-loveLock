package com.feng.framework.ycnweapp;

import com.feng.framework.ycnweapp.response.ResClockInfo;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName ClockDetailsInfo
 * @Author 小风谷
 * @Date 2021/3/23 16:41
 * @Version 1.0
 * @Description 打卡项详细信息学表
 */
@Data
@ToString
@Entity
@Table(name = "ClockDetailsInfo")
public class ClockDetailsInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "ClassName")
    private String ClassName;  //打卡名字.

    @Column(name = "ClockType")
    private String ClockType;  //打卡类型.

    @Column(name = "ImgUrl")
    private String ImgUrl;     //打卡图片

    @Column(name = "ClockId")
    private Long ClockId;    //打卡简介 id:'简介记录id',content:'简介内容',order:'排序',type:'简介类型'

    @Column(name = "AttendUserNum")
    private Long AttendUserNum;   //打卡参与的人数

    @Column(name = "AllPunchCardNum")
    private Long AllPunchCardNum;  //总打卡人数

    @Column(name = "CreatorId")
    private Long   CreatorId;  // 打卡用户的Id

    @Column(name = "NickName")
    private String NickName; //打卡创建人的名字

    @Column(name = "Sex")
    private Long Sex;  //打卡创建人的性别

    @Column(name = "Introduce")
    private String Introduce; //打卡创建人介绍

    @Column(name = "weChatNum")
    private String weChatNum; //打卡创建人的微信号

    @Column(name = "AvatarUrl")
    private String AvatarUrl; //打卡创建人头像

    @Column(name = "ClockDiaryId")
    private Long  ClockDiaryId;  //打卡日记Id

}
