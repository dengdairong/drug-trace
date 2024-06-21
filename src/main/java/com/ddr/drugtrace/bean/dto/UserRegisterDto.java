package com.ddr.drugtrace.bean.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDto {
    /**
     *
     */
    @NotBlank(message = "账号不能为空")
    @Size(min = 6, max = 15, message = "账号长度在6-15之间")
    private String username;
    /**
     *
     */
    @NotBlank(message = "昵称不能为空")
    @Size(min = 3, max = 20, message = "账号长度在3-20之间")
    private String nickname;
    /**
     *
     */
    @NotBlank(message = "角色不能为空")
    private String role;
    @Size(min = 8, max = 20, message = "密码长度在8-20之间")
    private String password;
}
