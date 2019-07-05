package com.sk.user.mapper;

import com.sk.user.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据用户ID修改用户余额
     * @param userId
     * @param balance
     */
    void updateUserBalance(@Param("userId") String userId,@Param("balance") double balance);

    /**
     * 根据用户ID修改用户信息
     * @param user
     */
    void updateUser(User user);
}
