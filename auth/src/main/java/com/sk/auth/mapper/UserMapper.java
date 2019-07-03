package com.sk.auth.mapper;

import com.sk.auth.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //注册
    void addUser(User user);

    //通过userId 查询用户信息
    User selectUser(String userId);

}
