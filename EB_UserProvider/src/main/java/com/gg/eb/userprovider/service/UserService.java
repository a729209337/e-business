package com.gg.eb.userprovider.service;

import com.gg.eb.common.vo.R;
import com.gg.eb.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    R findPhone(String phone);
    R save(UserDto userDto);
}
