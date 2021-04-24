package com.feng.ycnweapp.utils;

/**
 * @ClassName PictureUtil
 * @Author 小风谷
 * @Date 2021/3/30 11:54
 * @Version 1.0
 * @Description
 */
public class PictureUtil {
    public static String getRandomFileName(int stringLength) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }

}
