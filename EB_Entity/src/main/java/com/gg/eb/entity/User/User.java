package com.gg.eb.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String phone;
    @JsonIgnore//转化为json的时候忽略该字段
    private String password;
    private Short flag;
}
