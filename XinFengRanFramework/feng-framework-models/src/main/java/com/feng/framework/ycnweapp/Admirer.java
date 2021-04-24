package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Admirer
 * @Author 小风谷
 * @Date 2021/3/23 18:40
 * @Version 1.0
 * @Description 点赞表
 */
@Data
@ToString
@Entity
@Table(name = "Admirer")
public class Admirer {
    /** 点赞者id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public  Long id;

    /**日记id*/
    @Column(name = "tenLikeId")
    public  Long  tenLikeId;

    /**新点赞次数*/
    @Column(name = "adCount")
    public  Long  adCount;

    /** 点赞者名字 */
    @Column(name = "NickName")
    public  String NickName;
}
