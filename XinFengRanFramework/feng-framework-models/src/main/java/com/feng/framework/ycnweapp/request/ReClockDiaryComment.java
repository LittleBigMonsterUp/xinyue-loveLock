package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReClockDiaryComment
 * @Author 小风谷
 * @Date 2021/4/4 14:03
 * @Version 1.0
 * @Description 接收评论内容
 */
@Data
public class ReClockDiaryComment {
    private  Long diary_id;

    private  Long pid;
    /** 评论者id*/
    private  Long reviewer_id;

    private  String text_comment;
    /** 被评论者id*/
    private  Long respondent_id;

    /** 日记id*/
    private  Long diaryId;

    /** 评论内容id*/
    private  Long commentId;



}
