package com.feng.framework.ycnweapp.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ResAllCommentInfo
 * @Author 小风谷
 * @Date 2021/3/23 16:05
 * @Version 1.0
 * @Description  点赞下面所有评论者
 */
@Data
public class ResAllCommentInfo {

    /** 评论记录id */
    public  Long id;

    /** 该条评论所属一级评论的id */
    public  Long pid;

    /** 日记记录id */
    public  Long  diaryId;

    /** 文本评论内容 */
    public  String  textComment;

    /** 音频评论内容文件路径 */
    public  String  soundComment;

    /** 评论发表时间 */
    public Date createTime;
    /** 评论者集合 */
    public ResReviewerInfo reviewer;

    /** 日记用户 评论者 集合 */
    public ResRespondentInfo respondent;

}
