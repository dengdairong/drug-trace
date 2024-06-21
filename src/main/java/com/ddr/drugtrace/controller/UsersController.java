package com.ddr.drugtrace.controller;

import com.ddr.drugtrace.bean.dto.UserRegisterDto;
import com.ddr.drugtrace.enums.UserRole;
import com.ddr.drugtrace.enums.UserStatus;
import com.ddr.drugtrace.model.Users;
import com.ddr.drugtrace.service.UsersService;
import com.ddr.drugtrace.util.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseDto<Integer> register(@Validated @RequestBody UserRegisterDto user) {
        Users users = toModel(user);
        Users userDb = usersService.getByUserName(user.getUsername());
        Assert.isNull(userDb, "账号已存在");
        userDb = usersService.getByNickname(user.getNickname());
        Assert.isNull(userDb, "昵称已存在");
        return ResponseDto.success(usersService.save(users) ? 1 : 0);
    }

    private Users toModel(UserRegisterDto user) {
        Users users = new Users();
        users.setNickname(user.getNickname());
        users.setUsername(user.getUsername());
        users.setPasswordDigest(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        UserRole role = UserRole.getByName(user.getRole());
        Assert.notNull(role, "用户角色不存在");
        users.setRole(role.getStatus());
        users.setStatus(UserStatus.ENABLE.getStatus());
        Date curDate = new Date();
        users.setCreatedAt(curDate);
        return users;

    }
}
