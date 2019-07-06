package com.sk.auth.service;

import com.sk.auth.po.AuthCenter;

import java.util.Map;

public interface IAuthSrevice {

    AuthCenter login(String phone, String pass )throws Exception;

    public boolean authAdmin(String key ,String name)throws Exception;

    public String auth(String key)throws Exception;

    void alterPass(Map map)throws Exception;

    AuthCenter selectAuthcenter(String phine)throws Exception;

}
