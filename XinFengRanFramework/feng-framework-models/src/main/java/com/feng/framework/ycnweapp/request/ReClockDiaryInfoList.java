package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReClockDiaryInfoList
 * @Author 小风谷
 * @Date 2021/3/26 19:43
 * @Version 1.0
 * @Description 接收日记也请求数据
 */
@Data
public class ReClockDiaryInfoList {
    private  String isCreator;
    private  Long pageNo;
    private  Long dataNum;
    private  Long projectId;
    private  Long userId;
}
