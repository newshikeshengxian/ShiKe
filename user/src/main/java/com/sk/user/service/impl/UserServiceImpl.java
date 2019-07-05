package com.sk.user.service.impl;

import com.sk.user.mapper.UserMapper;
import com.sk.user.po.User;
import com.sk.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户ID修改用户信息
     * @param user
     * @return  返回修改后用户信息
     */
    @Transactional
    @Override
    public User updateUser(User user) throws Exception{
        String userId = user.getUserId();
        if(user.getUserSex() == "0"){
            user.setUserSex("男");
        }else {
            user.setUserSex("女");
        }
        userMapper.updateUser(user);
        User user1 = userMapper.queryUser(userId);
        return user1;
    }

    /**
     * 根据用户ID修改用户余额
     * @param userId
     * @param balance
     */
    @Override
    public void updateUserBalance(String userId, double balance) throws Exception {
        userMapper.updateUserBalance(userId,balance);
    }

    /**
     * 根据用户ID查询用户全部信息
     * @param userId
     * @return 此用户信息
     * @throws Exception
     */
    @Override
    public User queryUser(String userId) throws Exception {
        User user = userMapper.queryUser(userId);
        return user;
    }
}
