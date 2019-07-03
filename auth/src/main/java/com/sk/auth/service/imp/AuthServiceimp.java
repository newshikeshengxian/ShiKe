package com.sk.auth.service.imp;

import com.google.gson.Gson;
import com.sk.auth.mapper.AuthMapper;
import com.sk.auth.po.AuthCenter;
import com.sk.auth.service.IAuthSrevice;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceimp implements IAuthSrevice {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登陆
     * @param phone 手机号
     * @param pass 密码
     * @return
     */
    @Override
    public AuthCenter login(String phone, String pass)throws Exception {

        if(phone != null || phone != ""){
            new Exception("phone is null");
        }
        if(pass != null || pass != ""){
            new Exception("pass is null");
        }
        String password = DigestUtils.md5Hex(pass + phone);
        AuthCenter authCenter = authMapper.selectAuth(phone, password);
        if(authCenter != null){
            String s = String.valueOf((int) (Math.random() * 9 + 1) * 1000);
            String key = DigestUtils.md5Hex(password + s);
            redisTemplate.boundValueOps(key).set(String.valueOf(authCenter.getUserId()),30, TimeUnit.MINUTES);
            authCenter.setAcPwd(key);
            List<String> authList = authMapper.authById(authCenter.getUserId());
            Gson gson = new  Gson();
            String json = gson.toJson(authList);
            redisTemplate.boundValueOps(authCenter.getUserId()).set(json,30, TimeUnit.MINUTES);
        }
        return authCenter;
    }
}

