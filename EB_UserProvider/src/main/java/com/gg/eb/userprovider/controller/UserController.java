package com.gg.eb.userprovider.controller;

import com.gg.eb.common.vo.R;
import com.gg.eb.dto.UserDto;
import com.gg.eb.userprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("eb/provider/user/save.do")
    public R save(@RequestBody UserDto userDto){
       return userService.save(userDto);
    }
    @GetMapping("eb/provider/user/detail.do")
    public R detail(String phone){
        return userService.findPhone(phone);
    }
}
