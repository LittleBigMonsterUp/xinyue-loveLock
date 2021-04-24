package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReTenLikeInfo
 * @Author 小风谷
 * @Date 2021/3/27 21:53
 * @Version 1.0
 * @Description
 */
@Data
public class ReTenLikeInfo {

    private  Long diary_id;
    private  Long liked_user_id;
    private  Long admirer_id;
    private  Long likeRecordId;

}
