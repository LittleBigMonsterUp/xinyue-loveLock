package com.feng.framework.ycnweapp.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResRawData
 * @Author 小风谷
 * @Date 2021/3/25 16:11
 * @Version 1.0
 * @Description
 */
@Data
public class ReRawData implements Serializable {

    private  Integer id;
    private  String  nickName;
    private  String  gender;
    private  String  language;
    private  String  city;
    private  String  province;
    private  String  country;
    private  String  avatarUrl;

}
