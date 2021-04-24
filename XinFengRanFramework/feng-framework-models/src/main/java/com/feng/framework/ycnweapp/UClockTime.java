package com.feng.framework.ycnweapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName ClockTime
 * @Author 小风谷
 * @Date 2021/3/17 15:27
 * @Version 1.0
 * @Description 用户打卡记录表
 */
@Data
@ToString
@Entity
@Table(name = "UClockTime")
public class UClockTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    //用户表
    @Column(name = "UserName")
    private  String UserName;

    //用户身份证号
    @Column(name = "IdCard")
    private String IdCard;

    //创建班级名
    @Column(name = "ClassName")
    private  String ClassName;


    //打卡图片
    @Column(name = "Image")
    private  String  Image;

    //打卡音频
    @Column(name = "Audio")
    private  String  Audio;

    //每日打卡时间
    @Column(name = "ClockTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date ClockTime;

}
