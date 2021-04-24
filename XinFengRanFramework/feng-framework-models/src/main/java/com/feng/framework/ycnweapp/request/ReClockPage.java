package com.feng.framework.ycnweapp.request;

import lombok.Data;

/**
 * @ClassName ReClockPage
 * @Author 小风谷
 * @Date 2021/3/26 16:41
 * @Version 1.0
 * @Description
 */
@Data
public class ReClockPage {


    private  Long  dataNum;
    private  Long  nextPage;
    private  Long  projectId;
    private  Long  userId;
    private  Long  pageNo;
    private  Long  pageSize;
    private  String typeName;
    private  String labelName;


}
