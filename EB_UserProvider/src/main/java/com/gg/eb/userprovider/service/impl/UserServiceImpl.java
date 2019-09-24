package com.gg.eb.userprovider.service.impl;

import com.gg.eb.common.config.KeyConfig;
import com.gg.eb.common.util.EncryptionUtil;
import com.gg.eb.common.util.StrUtil;
import com.gg.eb.common.vo.R;
import com.gg.eb.dto.UserDto;
import com.gg.eb.entity.User.User;
import com.gg.eb.userprovider.dao.UserDao;
import com.gg.eb.userprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public R findPhone(String phone) {
        if (StrUtil.checkNotEmpty(phone)) {
            User user = userDao.selectByPhone(phone);
            if (user != null) {
                return R.setERROR("手机号重复");
            } else {
                return R.setOK("keyong");
            }
        }else {
            return R.setERROR("请输入手机号");
        }
    }

    @Override
    public R save(UserDto userDto) {
        User user = new User();
        user.setPassword(EncryptionUtil.AESEnc(KeyConfig.PASSKEY,userDto.getPass()));
        int r = userDao.save(user);
        return R.setResult(r>0,"新增用户");
    }
}
