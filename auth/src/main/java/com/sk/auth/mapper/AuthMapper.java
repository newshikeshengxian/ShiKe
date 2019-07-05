package com.sk.auth.mapper;

import com.sk.auth.po.AuthCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {

    //添加鉴权
    void addAuth(AuthCenter authCenter);

    //登陆
    AuthCenter selectAuth(@Param("acPhone") String phone, @Param("acPwd") String pass );

    //查询权限
    List<String> authById(String UserId);

    //修改密码
    void alterPass(AuthCenter authCenter);

    //判断是否有手机号
    AuthCenter selectAuthcenter(String phone);

}
