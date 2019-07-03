package com.sk.user.mapper;

import com.sk.user.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return 此用户的信息
     */
    User queryUser(String userId);
}
