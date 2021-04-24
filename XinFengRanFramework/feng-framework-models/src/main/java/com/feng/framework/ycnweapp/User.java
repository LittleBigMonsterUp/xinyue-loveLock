package com.feng.framework.ycnweapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName User
 * @Author 小风谷
 * @Date 2021/3/6 21:24
 * @Version 1.0
 * @Description 普通用户表
 */
@Data
@ToString
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //用户性别
    @Column(name = "Sex")
    private String Sex;

    //用户头像
    @Column(name = "AvatarUrl")
    private String AvatarUrl ;

    //用户名称
    @Column(name = "UName")
    private String UName;

    //用户身份证号
    @Column(name = "IdCard")
    private String IdCard;

    //身份标识 0 普通用户 , 1 老师用户
    @Column(name = "IsLabel")
    private String IsLabel;


    //用户 微信AppId
    @Column(name = "AppId")
    private String AppId;

    //用户介绍
    @Column(name = "UserDescription")
    private String UserDescription;

    @JsonIgnore
    @Column(name = "Password")
    private String Password;




    @Column(name = "Code")
    private String Code;


    /**
     * 用户登录id
     */
    @Column(name = "Uuid")
    private String Uuid = "";

    //**********************************
    //以下为微信类传输字段

    /**
     * 微信openId
     */
    @Column( unique = true,name = "OpenId")
    private String OpenId;

    /**
     * 传入参数: 用户非敏感信息
     */
    @Column(name = "RawData")
    private String RawData;

    /**
     * 传入参数: 签名
     */
    @Column(name = "Signature")
    private String Signature;

    /**
     * 传入参数: 用户敏感信息
     */
    @Column(name = "EncryptedData")
    private String EncryptedData;

    /**
     * 传入参数: 解密算法的向量
     */
    @Column(name = "Iv")
    private String Iv;

    /**
     * 会话密钥
     */
    @JsonIgnore
    @Column(name = "SessionKey")
    private String SessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    @JsonIgnore
    @Column(name = "UnionId")
    private String UnionId;

    //以上为微信类传输字段
    //**********************************

    /**
     * 返回:服务器jwt token
     */
    @Column(name = "Token")
    private String Token;

    /**
     * 返回：userName或openId对应的用户
     */
    @Column(name = "UserInfo")
    private  String UserInfo;

}
