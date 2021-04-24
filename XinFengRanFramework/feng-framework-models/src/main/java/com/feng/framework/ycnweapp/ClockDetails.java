package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName ClockDetails
 * @Author 小风谷
 * @Date 2021/3/29 17:45
 * @Version 1.0
 * @Description  参与者打卡项表
 */
@Data
@ToString
@Entity
@Table(name = "ClockDetails")
public class ClockDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 打卡名字*/
    @Column(name = "ClassName")
    private String ClassName;
    /** 打卡类型.*/
    @Column(name = "ClockType")
    private String ClockType;


    /** 打卡人的id */
    @Column(name = "UserId")
    private Long   UserId;

    /** 打卡人的昵称 */
    @Column(name = "NickName")
    public  String NickName;

    /** 打卡创建人的性别 */
    @Column(name = "Sex")
    private Long Sex;
    /** 打卡创建人头像 */
    @Column(name = "AvatarUrl")
    private String AvatarUrl;

    /** 身份标识 0 普通用户 , 1 老师用户 */
    @Column(name = "IsLabel")
    private String IsLabel;


    /** 个人打卡次数 */
    @Column(name = "ClockCount",columnDefinition="tinyint default 0")
    private Long   ClockCount=new Long(0);


    /**打卡圈子Id*/
    @Column(name = "ClockDetailsInfoId")
    public Long ClockDetailsInfoId;
}
