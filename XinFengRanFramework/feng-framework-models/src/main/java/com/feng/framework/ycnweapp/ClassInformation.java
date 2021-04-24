package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName ClassClassification
 * @Author 小风谷
 * @Date 2021/3/6 21:09
 * @Version 1.0
 * @Description 已建立班级信息表
 */
@Data
@ToString
@Entity
@Table(name = "ClassInformation")
public class ClassInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 创建者ID*/
    @Column(name = "UseId")
    private   Long UseId;
    //创建者姓名
    @Column(name = "UName")
    private String  UName;

    //用户身份证号
    @Column(name = "IdCard")
    private String IdCard;

    //创建的班级名称
    @Column(name = "ClassName")
    private String  ClassName;

    //课程描述
    @Column(name = "Description")
    private String Description ;

}
