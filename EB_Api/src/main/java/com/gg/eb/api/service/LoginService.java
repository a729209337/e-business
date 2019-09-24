package com.gg.eb.api.service;


import com.gg.eb.common.vo.R;
import com.gg.eb.dto.LoginDto;
import com.gg.eb.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("LoginProvider")
public interface LoginService {
    //校验是否被冻结
    @GetMapping("nr/login/checkfreeze/{phone}")
    R checkP(@PathVariable String phone);
    //校验是否有效
    @GetMapping("nr/login/checklogin")
    R checkToken();
    //登录
    @PostMapping("nr/login/login")
    R login(@RequestBody LoginDto loginDto);
    //密码找回
    @PostMapping("nr/login/findpass")
    R findPass(@RequestBody UserDto userDto);
    //修改密码
    @PostMapping("nr/login/resetpass/{pass}")
    R changePass(@PathVariable String pass);
    //注销
    @GetMapping("nr/login/loginexit")
    R exit();
}
