package com.feng.framework.ycnweapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ResGradeClbum
 * @Author 小风谷
 * @Date 2021/3/18 16:52
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
public class ResGradeClbum {

    //是否成功
    private Boolean success;
    //状态码
    private Integer code;

    //提示信息
    private String msg;

    List<ResClbum> ListClbum;
    List<ResGrade> LsitGrade;

}
