package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Reviewer
 * @Author 小风谷
 * @Date 2021/3/23 18:47
 * @Version 1.0
 * @Description 日记评论者表
 */
@Data
@ToString
@Entity
@Table(name = "ReviewerInfo")
public class ReviewerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private   Long id;

    @Column(name = "AllCommentId")
    private   Long AllCommentId;

    @Column(name = "UserId")
    private   Long UserId;

    @Column(name = "sex")
    private  String  Sex;

    @Column(name = "NickName")
    private  String  NickName;

    @Column(name = "NavatartUrl")
    private  String  NavatartUrl;
}
