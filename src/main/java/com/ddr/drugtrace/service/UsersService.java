package com.ddr.drugtrace.service;

import com.ddr.drugtrace.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 邓代容
* @description 针对表【users】的数据库操作Service
* @createDate 2024-04-25 21:44:25
*/
public interface UsersService extends IService<Users> {

    Users getByUserName(String username);

    Users getByNickname(String nickname);
}
