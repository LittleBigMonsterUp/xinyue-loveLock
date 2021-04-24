package com.feng.framework.ycnweapp.response;

import lombok.Data;

/**
 * @ClassName ResAttendUserInfo
 * @Author 小风谷
 * @Date 2021/4/9 17:47
 * @Version 1.0
 * @Description
 */
@Data
public class ResAttendUserInfo {
    private  Long id;
    private  String  nick_name;

    private  String   avatar_url;

    private  Long  sex;
    private  Long count;  //打卡次数
}
