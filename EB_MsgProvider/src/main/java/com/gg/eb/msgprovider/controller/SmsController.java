package com.gg.eb.msgprovider.controller;

import com.gg.eb.common.vo.R;
import com.gg.eb.msgprovider.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @GetMapping("nr/provider/sms/sendvc.do")
    public R sendSms(@RequestParam("phone") String phone){
        return smsService.sendValidateCode(phone);
    }
    @GetMapping("nr/provider/sms/checkvc.do")
    public R checkVC(@RequestParam("phone") String phone,@RequestParam("code") String code){
        return smsService.checkCode(phone, code);
    }

}
