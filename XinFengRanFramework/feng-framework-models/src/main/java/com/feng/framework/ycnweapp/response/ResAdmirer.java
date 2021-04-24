package com.feng.framework.ycnweapp.response;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName ResAdmirer
 * @Author 小风谷
 * @Date 2021/3/23 18:43
 * @Version 1.0
 * @Description 点赞者
 */
@Data
public class ResAdmirer {
    /** 点赞者id */
    public  Long id;

    /** 点赞者名字 */
    public   String nickName;

}
