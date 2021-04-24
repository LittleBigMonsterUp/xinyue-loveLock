package com.feng.framework.ycnweapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName UTeacher
 * @Author 小风谷
 * @Date 2021/3/6 21:19
 * @Version 1.0
 * @Description 老师表
 */
@Data
@ToString
@Entity
@Table(name = "UTeacher")
public class UTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //老师性别
    @Column(name = "Sex")
    private String Sex;

    //老师名称
    @Column(name = "TeaName")
    private String TeaName;

    //老师身份
    @Column(name = "IdCard")
    private String IdCard;


    //用户头像
    @Column(name = "AvatarUrl")
    private String AvatarUrl ;


    //用户 微信AppId
    @Column(name = "AppId")
    private String AppId;


    @JsonIgnore
    @Column(name = "Password")
    private String Password;

    //老师介绍
    @Column(name = "TeaDescription")
    private String TeaDescription;

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
    @Column(name = "OpenId")
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
