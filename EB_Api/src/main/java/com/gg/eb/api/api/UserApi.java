package com.gg.eb.api.api;


import com.gg.eb.api.service.UserService;
import com.gg.eb.common.vo.R;
import com.gg.eb.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: NewRetail
 * @description:
 * @author: Feri
 * @create: 2019-08-13 15:08
 */
@Api(value = "",tags = "会员操作")
@RestController
public class UserApi {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "",notes = "校验手机号")
    @GetMapping("/api/user/checkphone/{phone}")
    public R sendVc(@PathVariable String phone){
        return userService.check(phone);
    }
    @ApiOperation(value = "",notes = "新增会员")
    @PostMapping("/api/user/saveuser")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

}
