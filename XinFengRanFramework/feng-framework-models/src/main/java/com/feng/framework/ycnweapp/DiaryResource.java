package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @ClassName DiaryResource
 * @Author 小风谷
 * @Date 2021/3/23 18:35
 * @Version 1.0
 * @Description 打卡媒体资源
 */

@Data
@ToString
@Entity
@Table(name = "DiaryResource")
public class DiaryResource {

    /**打卡日记相关的资源文件记录id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public  Long id;


    /** 打卡项id*/
    @Column(name = "projectId")
    public  Long  projectId;

    /**日记id*/
    @Column(name = "diaryId")
    public  Long  diaryId;

    /**日记id*/
    @Column(name = "userId")
    public  Long  userId;

    /** 资源文件路径信息*/
    @Column(name = "ResourceUrl")
    public  String  ResourceUrl;

    /** 1-图片 2-音频 3-视频 */
    @Column(name = "Type")
    public  Long  Type;
    /**
     * 文件实际名称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 文件名
     */

    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件主名称
     */

    @Column(name = "primary_name")
    private String primaryName;

    /**
     * 文件扩展名
     */
    @Column(name = "extension")
    private String extension;

    /**
     * 文件大小
     */
    @Column(name = "size")
    private Long size;

}
