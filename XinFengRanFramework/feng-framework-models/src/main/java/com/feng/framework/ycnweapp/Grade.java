package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName ClassLevel
 * @Author 小风谷
 * @Date 2021/3/6 21:05
 * @Version 1.0
 * @Description 年级表
 */
@Data
@ToString
@Entity
@Table(name = "Grade")
public class Grade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //年级名称
    @Column(name = "GradeName")
    private String GradeName;

}
