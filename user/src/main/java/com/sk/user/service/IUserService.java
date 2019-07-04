package com.sk.user.service;

import com.sk.user.po.User;

public interface IUserService {
    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return 此用户信息
     * @throws Exception
     */
    User queryUser(String userId) throws Exception;
}
