package com.feng.framework.ycnweapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AllCommentInfo
 * @Author 小风谷
 * @Date 2021/3/23 17:44
 * @Version 1.0
 * @Description 所有的评论信息
 */
@Data
@ToString
@Entity
@Table(name = "AllCommentInfo")
public class AllCommentInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public  Long id;

    /**日记id*/
    @Column(name = "diaryId")
    public  Long  diaryId;

    /** 创造该打卡者id*/
    @Column(name = "CreateId")
    public  Long CreateId;

    /** 评论记录id */
    @Column(name = "UseId")
    private   Long UseId;


    /** 该条评论所属一级评论的id */
    @Column(name = "pid")
    public  Long pid;


    /** 文本评论内容 */
    @Column(name = "TextComment")
    public  String  TextComment;

    /** 音频评论内容文件路径 */
    @Column(name = "SoundComment")
    public  String  SoundComment;

    /** 评论发表时间 */
    @Column(name = "CreateTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    public Date CreateTime;

    /** 评论者集合 */
    @Column(name = "ReviewerId")
    public Long ReviewerId;


    /** 日记用户 评论者 集合 */
    @Column(name = "RespondentId")
    public Long RespondentId;

    /** 点赞次数 */
    @Column(name = "CommenCount")
    public Long CommenCount;


}
