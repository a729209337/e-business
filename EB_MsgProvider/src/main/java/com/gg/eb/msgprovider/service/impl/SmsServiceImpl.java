package com.gg.eb.msgprovider.service.impl;

import com.alibaba.fastjson.JSON;
import com.gg.eb.common.config.RedisKeyConfig;
import com.gg.eb.common.config.SmsConfig;
import com.gg.eb.common.util.JedisUtil;
import com.gg.eb.common.util.Random_Util;
import com.gg.eb.common.util.StrUtil;
import com.gg.eb.common.util.TimeUtil;
import com.gg.eb.common.vo.R;
import com.gg.eb.msgprovider.config.RabbitMQConfig;
import com.gg.eb.msgprovider.model.VCode;
import com.gg.eb.msgprovider.service.SmsService;
import com.sun.glass.ui.View;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmsServiceImpl implements SmsService {
    private JedisUtil jedisUtil = JedisUtil.getInstance();
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Override
    public R sendValidateCode(String phone) {
        if (jedisUtil.exists(RedisKeyConfig.SMSKEYD1 + SmsConfig.APITEMID + ":" + phone)) {
            int dc = Integer.parseInt(jedisUtil.get(RedisKeyConfig.SMSKEYD1 + SmsConfig.APITEMID + ":" + phone));
            if (dc >= 20) {
                return R.setERROR("今日已达上限");
            }
        }
        if (jedisUtil.exists(RedisKeyConfig.SMSKEYH1+SmsConfig.APITEMID+":"+phone)){

            int hc = Integer.parseInt(jedisUtil.get(RedisKeyConfig.SMSKEYH1 + SmsConfig.APITEMID + ":" + phone));
            if (hc>=4){
                return R.setERROR("请稍后再来");
            }
        }
        if (jedisUtil.exists(RedisKeyConfig.SMSKEYM10+SmsConfig.APITEMID+":"+phone)){
            int mc = Integer.parseInt(jedisUtil.get(RedisKeyConfig.SMSKEYM10 + SmsConfig.APITEMID + ":" + phone));
            if (mc>=3){
                return R.setERROR("请稍后");
            }
        }
        if (jedisUtil.exists(RedisKeyConfig.SMSKEYM1+SmsConfig.APITEMID+":"+phone)){
            int mc1 = Integer.parseInt(jedisUtil.get(RedisKeyConfig.SMSKEYM1 + SmsConfig.APITEMID + ":" + phone));
            if (mc1>=1){
                return R.setERROR("a请稍后再来");
            }
        }
        int code;
        if (jedisUtil.exists(RedisKeyConfig.SMSCODE+phone)){
           code = Integer.parseInt(jedisUtil.get(RedisKeyConfig.SMSCODE+phone));
        }else {
            code = Random_Util.createNum(6);
            jedisUtil.setExpire(RedisKeyConfig.SMSCODE+phone,code+"",600);
        }
        amqpTemplate.convertAndSend(RabbitMQConfig.ename,"", JSON.toJSONString(new VCode(code,phone)));
        setKey(RedisKeyConfig.SMSKEYD1+SmsConfig.APITEMID+":"+phone, TimeUtil.getLastSeconds());
        setKey(RedisKeyConfig.SMSKEYH1+SmsConfig.APITEMID+":"+phone,3600);
        setKey(RedisKeyConfig.SMSKEYM10+SmsConfig.APITEMID+":"+phone,600);
        setKey(RedisKeyConfig.SMSKEYM1+SmsConfig.APITEMID+":"+phone,60);
        return R.setResult(true,"OK");
    }


    @Override
    public R checkCode(String phone, String code) {
        String cd = jedisUtil.get(RedisKeyConfig.SMSCODE + phone);
        if (StrUtil.checkNotEmpty(cd)) {
            if (Objects.equals(code, cd)) {
                jedisUtil.del(RedisKeyConfig.SMSCODE + phone);
                return R.setOK("成功");
            } else {
                return R.setERROR("失败");
            }
        }else {
            return R.setERROR("验证码无效");
        }

    }
    private void setKey(String key,int seconds){
        if (jedisUtil.exists(key)){
            jedisUtil.setExpire(key,(Integer.parseInt(jedisUtil.get(key))+1)+"",(int)jedisUtil.ttl(key));
        }else {
            jedisUtil.setExpire(key,1+"",seconds);
        }
    }
}
