package com.feng.framework.ycnweapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ClockDiary
 * @Author 小风谷
 * @Date 2021/3/23 17:01
 * @Version 1.0
 * @Description 打卡日记表
 */
@Data
@ToString
@Entity
@Table(name = "ClockDiary")
public class ClockDiary {

    /** 用户日记id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    /** 日记发表人的id */
    @Column(name = "UserId")
    private Long   UserId;


    /** 打开日记内容 */
    @Column(name = "Textcontent")
    public String Textcontent;


    /** 打卡时间 */
    @Column(name = "PunchCardTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    public Date PunchCardTime;

    /**打卡地理位置信息*/
    @Column(name = "PunchCardAddress")
    public String PunchCardAddress;

    /**经度*/
    @Column(name = "AddressLongitude")
    public String AddressLongitude;

    /**纬度*/
    @Column(name = "AddressLatitude")
    public String AddressLatitude;

    /**日记可见类型', // 0-公开 圈子成员可见 1--仅圈主可见*/
    @Column(name = "VisibleType")
    public Long  VisibleType;

    /**当前日记已坚持天数*/
    @Column(name = "CurrDiaryPunchCardDayNum")
    public Long CurrDiaryPunchCardDayNum;

    /**是否置顶', // 0--不置顶 1--置顶*/
    @Column(name = "HaveSticky")
    public Long HaveSticky;

    /**是否为补打卡日记', // 0--不是 1--是*/
    @Column(name = "IsRepairDiary")
    public Long IsRepairDiary;

    /**补打卡时间*/
    @Column(name = "RepairPunchCardTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    public Date RepairPunchCardTime;

    /**日记发表者信息*/
    @Column(name = "PublisherID")
    public Long  PublisherID;


    /**日记资源*/
    @Column(name = "DiaryResourceId")
    public Long DiaryResourceId;

    /**点赞人数*/
    @Column(name = "LikeUserNum")
    public Long LikeUserNum;

    /**评论总数*/
    @Column(name = "CommentNum")
    public Long CommentNum;

    /**当前小程序使用者对本条日记的点赞情况 true已点赞 false未点赞*/
    @Column(name = "HaveLike")
    public String HaveLike;

    /**每条日记只显示前十条点赞记录*/
    @Column(name = "TenLikeInfoId")
    public Long  TenLikeInfoId;

    /**该日记的所有评论*/
    @Column(name = "AllCommentInfoId")
    public Long AllCommentInfoId;

    /**打卡圈子Id*/
    @Column(name = "ClockDetailsInfoId")
    public Long ClockDetailsInfoId;

}
