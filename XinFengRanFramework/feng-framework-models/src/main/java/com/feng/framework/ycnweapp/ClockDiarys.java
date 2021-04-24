package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName UClockDiarys
 * @Author 小风谷
 * @Date 2021/4/24 12:00
 * @Version 1.0
 * @Description  记录用户打卡日记详情
 */
@Data
@ToString
@Entity
@Table(name = "ClockDiarys")
public class ClockDiarys {

    /** id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;


    /**打卡日记Id*/
    @Column(name = "UserId")
    private Long  UserId;  //打卡日记Id

    /**打卡圈子Id*/
    @Column(name = "ClockDetailsInfoId")
    public Long ClockDetailsInfoId;

    /**打卡次数*/
    @Column(name = "ClockCount")
    public Long ClockCount;

    /** 积分 */
    @Column(name = "Integral")
    public Long Integral;
}
