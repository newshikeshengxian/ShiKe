package com.sk.auth.service.imp;

import com.google.gson.Gson;
import com.sk.auth.mapper.AuthMapper;
import com.sk.auth.note.Authenti;
import com.sk.auth.po.AuthCenter;
import com.sk.auth.service.IAuthSrevice;
import com.sk.shoppingCart.vo.JsonResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceimp implements IAuthSrevice {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Authenti authenti;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登陆
     * @param phone 手机号
     * @param pass 密码
     * @return
     */
    @Override
    @Transactional
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
            authCenter.setAcPhone("");
            JsonResult forObject = restTemplate.getForObject("http://user/user/queryUser?uid=" + authCenter.getUserId(), JsonResult.class);
            Map map =(Map) forObject.getData();
            String userName = (String) map.get("userName");
            authCenter.setUserName(userName);
            List<String> authList = authMapper.authById(authCenter.getUserId());
            if(authList != null){
                Gson gson = new  Gson();
                String json = gson.toJson(authList);
                redisTemplate.boundValueOps(authCenter.getUserId()).set(json,30, TimeUnit.MINUTES);
            }
        }
        return authCenter;
    }

    @Override
    public boolean authAdmin(String key, String name) throws Exception {
        boolean boot = authenti.authAdmin(key, name);
        return boot;
    }

    @Override
    public String auth(String key) throws Exception {
        String id = authenti.auth(key);
        return id;
    }

    @Override
    public void alterPass(Map map) throws  Exception{
        String pass = (String) map.get("pass");
        String phone = (String) map.get("phone");
        if(phone == null || phone == ""){
            new Exception("phone is null");
        }
        if(pass== null || pass == ""){
            new Exception("pwd is null ");
        }
        AuthCenter authCenter = new AuthCenter();
        String userPass = DigestUtils.md5Hex(pass + phone);
        authCenter.setAcPwd(userPass);
        authCenter.setAcPhone(phone);
        authMapper.alterPass(authCenter);
    }

    @Override
    public AuthCenter selectAuthcenter(String phone) throws Exception {
        if(phone != null || phone != ""){
            AuthCenter authCenter = authMapper.selectAuthcenter(phone);
            return  authCenter;
        }
        return null;
    }
}

