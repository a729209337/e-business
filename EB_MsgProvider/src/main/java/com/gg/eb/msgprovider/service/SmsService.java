package com.gg.eb.msgprovider.service;

import com.gg.eb.common.vo.R;

public interface SmsService {
    R sendValidateCode (String phone);

    R checkCode(String phone, String code);
}
