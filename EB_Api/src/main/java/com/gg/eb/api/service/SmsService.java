package com.gg.eb.api.service;

import com.gg.eb.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("MsgProvider")
public interface SmsService {
    @GetMapping("nr/provider/sms/sendvc.do")
    R sendSms(@RequestParam("phone") String phone);
    @GetMapping("nr/provider/sms/checkvc.do")
    R checkVC(@RequestParam("phone") String phone, @RequestParam("code")String code);
}
