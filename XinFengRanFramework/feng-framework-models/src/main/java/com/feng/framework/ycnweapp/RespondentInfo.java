package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName RespondentInfo
 * @Author 小风谷
 * @Date 2021/3/23 18:57
 * @Version 1.0
 * @Description 评论区评论者信息
 */
@Data
@ToString
@Entity
@Table(name = "RespondentInfo")
public class RespondentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private   Long id;

    @Column(name = "UserId")
    private   Long UserId;

    @Column(name = "AllCommentId")
    private   Long AllCommentId;

    @Column(name = "sex")
    private  String  Sex;

    @Column(name = "NickName")
    private  String  NickName;

    @Column(name = "NavatartUrl")
    private  String  NavatartUrl;
}
