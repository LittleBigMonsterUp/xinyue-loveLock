package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName PublisherInfo
 * @Author 小风谷
 * @Date 2021/3/23 19:00
 * @Version 1.0
 * @Description 发表日记的人的用户详情
 */
@Data
@ToString
@Entity
@Table(name = "PublisherInfo")
public class PublisherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private   Long id;

    @Column(name = "UseId")
    private   Long UseId;

    @Column(name = "sex")
    private  String  Sex;

    @Column(name = "NickName")
    private  String  NickName;

    @Column(name = "avatartUrl")
    private  String  avatartUrl;

    /**日记id*/
    @Column(name = "diaryId")
    public  Long  diaryId;

}
