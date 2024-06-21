package com.ddr.drugtrace.mapper;

import com.ddr.drugtrace.model.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 邓代容
* @description 针对表【users】的数据库操作Mapper
* @createDate 2024-04-25 21:44:25
* @Entity com.ddr.drugtrace.mapper.com.ddr.drugtrace.model.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    Users getByUserName(String username);

    Users getByNickname(String nickname);
}




