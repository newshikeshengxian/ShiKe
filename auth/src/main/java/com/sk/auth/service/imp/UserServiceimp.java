package com.sk.auth.service.imp;

import com.sk.auth.mapper.AuthMapper;
import com.sk.auth.mapper.UserMapper;
import com.sk.auth.po.AuthCenter;
import com.sk.auth.po.User;
import com.sk.auth.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceimp implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AuthMapper authMapper;

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) throws Exception{

        if (user.getPhone() != null || user.getPhone() != "") {
            new Exception(" phone is  null");
        }
        if(user.getUserPass() != null || user.getUserPass() != ""){
            new Exception(" pass is  null");
        }
        String code = redisTemplate.boundValueOps(user.getPhone()).get();
        if (user.getCode().equals(code)) {
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            user.setUserId(date);
            userMapper.addUser(user);
            AuthCenter authCenter = new AuthCenter();
            authCenter.setAcPhone(user.getPhone());
            authCenter.setUserId(user.getUserId());
            String pass = DigestUtils.md5Hex(user.getUserPass() + user.getPhone());
            authCenter.setAcPwd(pass);
            authMapper.addAuth(authCenter);
        }

    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    @Override
    public User selectUser(String userId)throws Exception {
        User user = userMapper.selectUser(userId);
        return user;
    }


}