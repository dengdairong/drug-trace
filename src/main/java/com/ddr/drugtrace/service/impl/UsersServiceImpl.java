package com.ddr.drugtrace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddr.drugtrace.model.Users;
import com.ddr.drugtrace.service.UsersService;
import com.ddr.drugtrace.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author 邓代容
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-04-25 21:44:25
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Override
    public Users getByUserName(String username) {
        return this.baseMapper.getByUserName(username);
    }

    @Override
    public Users getByNickname(String nickname) {
        return this.baseMapper.getByNickname(nickname);
    }
}




