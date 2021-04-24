package com.feng.ycnweapp.wechat;

import com.alibaba.fastjson.JSONObject;
import com.feng.ycnweapp.wechat.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @ClassName WxMiniApiImpl
 * @Author 小风谷
 * @Date 2021/3/23 23:46
 * @Version 1.0
 * @Description 微信小程序Api接口实现
 */
@Slf4j
@Service
public class WxMiniApiImpl implements WxMiniApi {

    @Override
    public JSONObject authCode2Session(String appId, String secret, String jsCode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        log.info("api/wx-mini/getSessionKey:" + str);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return JSONObject.parseObject(str);
        }

    }
}