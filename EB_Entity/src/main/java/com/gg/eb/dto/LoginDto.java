package com.gg.eb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LoginDto {
    private int id;
    private String phone;
    @JsonIgnore
    private String pass;
    private int type;
    private String mac;
}
