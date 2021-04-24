package com.feng.framework.ycnweapp.response;

import lombok.Data;

import java.security.PublicKey;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ResClockDiary
 * @Author 小风谷
 * @Date 2021/3/22 10:23
 * @Version 1.0
 * @Description  打卡日记详情
 */
@Data
public class ResClockDiary {

    /** 用户日记id*/
    public Long id;

    /** 打开日记内容 */
    public String textcontent;

    /** 打卡时间 */
    public String punchCardTime;

    /**打卡地理位置信息*/
    public String punchCardAddress;

    /**经度*/
    public String addressLongitude;
    /**纬度*/
    public String addressLatitude;

    /**日记可见类型', // 0-公开 圈子成员可见 1--仅圈主可见*/
    public Long visibleType;
    /**当前日记已坚持天数*/
    public Long currDiaryPunchCardDayNum;

    /**是否置顶', // 0--不置顶 1--置顶*/
    public Long haveSticky;

    /**是否为补打卡日记', // 0--不是 1--是*/
    public Long isRepairDiary;

    /**补打卡时间*/
    public String repairPunchCardTime;

    /**日记发表者信息*/
    public ResPublisherInfo publisher;


    /**日记资源*/
    public List<ResDiaryResource> diaryResource;

    /**点赞人数*/
    public Long likeUserNum;

    /**评论总数*/
    public Long commentNum;

    /**当前小程序使用者对本条日记的点赞情况 true已点赞 false未点赞*/
    public String haveLike;

    /**每条日记只显示前十条点赞记录*/
    public List<ResTenLikeInfo> tenLikeInfo;
    /**该日记的所有评论*/
    public List<ResAllCommentInfo> allCommentInfo;








    // 属性值说明
    // {
    //     id: '打卡日记记录id',
    //     text_content: '打卡日记文本内容',
    //     punch_card_time: '打卡时间',
    //     punch_card_address: '打卡地理位置信息',
    //     address_longitude: '经度',
    //     address_latitude: '纬度',
    //     visible_type: '日记可见类型', // 0-公开 圈子成员可见 1--仅圈主可见
    //     curr_diary_punch_card_day_num: '当前日记已坚持天数',
    //     have_sticky: '是否置顶', // 0--不置顶 1--置顶
    //     is_repair_diary: '是否为补打卡日记', // 0--不是 1--是
    //     repair_punch_card_time: '补打卡时间',
    //     publisher: {
    //         id: 0,// 日记发表者userId
    //         sex:'0--未知 1--男性 2--女性',
    //         nick_name:'',
    //         avatar_url: ''
    //     },
    //     diaryResource:{
    //         id: '打卡日记相关的资源文件记录id',
    //         resource_url: '资源文件路径信息',
    //         type: '1-图片 2-音频 3-视频'
    //     },
    //     like_user_num: 点赞总人数
    //     comment_num: 评论总数
    //     haveLike: 当前小程序使用者对本条日记的点赞情况 true已点赞 false未点赞
    //     // 每条日记只显示前十条点赞记录
    //     tenLikeInfo:[
    //         {
    //              id: 点赞记录id,
    //              admirer:{
    //                          id: 点赞者id,  nick_name: 点赞者昵称
    //                      }
    //         }
    //     ],
    //     该日记的所有评论
    //     allCommentInfo:[{
    //              id: 评论记录id
    //              pid: 该条评论所属一级评论的id
    //              diary_id: 日记记录id
    //              text_comment: 文本评论内容
    //              sound_comment: 音频评论内容文件路径
    //              create_time: 评论发表时间
    //              reviewer: {
    //                            id: 评论者用户id
    //                            nick_name: 评论者昵称
    //                            sex: 评论者性别
    //                            avatar_url: 评论者头像
    //                        }
    //              一级评论则不显示评论所回复的用户的信息 因为这是针对日记发表者进行评论的
    //              respondent: {
    //                            id: 评论所回复的用户id
    //                            nick_name: 评论所回复的用户昵称
    //                            sex: 评论所回复的用户性别
    //                            avatar_url: 评论所回复的用户头像
    //                        }
    //        }]
    // }
}
