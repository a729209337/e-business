package com.gg.eb.msgprovider.utli;

import com.alibaba.fastjson.JSON;
import com.gg.eb.common.config.SmsConfig;
import com.gg.eb.common.util.Http_Util;
import com.gg.eb.msgprovider.model.SmsResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SmsUtil {
    public static boolean sendMsg(String phone,int code) {
        String  url =null;
        try {
            url = "http://v.juhe.cn/sms/send?tpl_id="+ SmsConfig.APITEMID
                    +"&key="+SmsConfig.APIKEY+"&mobile="+phone+"&tpl_value="
                    + URLEncoder.encode("#code#="+code,"UTF-8");
            String json = Http_Util.getStr(url);
            SmsResult sr = JSON.parseObject(json, SmsResult.class);
            return sr.getError_code()==0;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
