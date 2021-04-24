package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName UClock
 * @Author 小风谷
 * @Date 2021/3/8 11:08
 * @Version 1.0
 * @Description 用户打卡表
 */
@Data
@ToString
@Entity
@Table(name = "UClockClass")
public class UClockClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //创建的班级名称
    @Column(name = "ClassName")
    private  String ClassName;


    //打卡群主名
    @Column(name = "TeacherName")
    private  String TeacherName;

    //用户
    @Column(name = "UserName")
    private  String UserName;

    //用户身份证号
    @Column(name = "IdCard")
    private String IdCard;

}
