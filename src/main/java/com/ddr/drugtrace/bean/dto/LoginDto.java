package com.ddr.drugtrace.bean.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String nickname;
    private String role;
    private String token;
}
