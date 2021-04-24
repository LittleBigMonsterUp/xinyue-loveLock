package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReClockProject
 * @Author 小风谷
 * @Date 2021/3/27 15:06
 * @Version 1.0
 * @Description 接收打卡项的数据
 */
@Data
public class ReClockProject {
    private  Long userId;
    private  Long projectId;
}
