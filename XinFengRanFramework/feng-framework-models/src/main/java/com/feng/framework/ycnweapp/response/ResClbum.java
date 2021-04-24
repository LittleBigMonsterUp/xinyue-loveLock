package com.feng.framework.ycnweapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Clbum
 * @Author 小风谷
 * @Date 2021/3/18 16:59
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
public class ResClbum {

    private   Long id;

    private  String clbumName;

    private  String pareadId;


    private  Boolean choose;

}
