package com.feng.framework.ycnweapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @ClassName GradeClbum
 * @Author 小风谷
 * @Date 2021/3/18 16:53
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
public class ResGrade {

    private  Long Id;

    private  String gradeName;

    private  String pareadId;

    private  Boolean show;

}
