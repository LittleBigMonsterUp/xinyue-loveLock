package com.feng.framework.ycnweapp.response;

import com.feng.framework.ycnweapp.Admirer;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ResTenLikeInfo
 * @Author 小风谷
 * @Date 2021/3/23 16:00
 * @Version 1.0
 * @Description 点赞记录
 */
@Data
public class ResTenLikeInfo {

    /** 点赞者id*/
    public  Long id;


    /** 被赞者 */
    public ResAdmirer admirer;

}
