package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Class
 * @Author 小风谷
 * @Date 2021/3/17 16:49
 * @Version 1.0
 * @Description 班级名称列表
 */
@Data
@ToString
@Entity
@Table(name = "Clbum")
public class Clbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //创建班级名称
    @Column(name = "ClbumName")
    private String  ClbumName;

}
