package com.sk.user.service;

import com.sk.user.po.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {
    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return 此用户信息
     * @throws Exception
     */
    User queryUser(String userId) throws Exception;


    /**
     * 根据用户ID修改用户余额
     * @param userId
     * @param balance
     */
    void updateUserBalance( String userId, double balance) throws  Exception;
}
