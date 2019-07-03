package com.sk.auth.service;

import com.sk.auth.po.AuthCenter;

public interface IAuthSrevice {

    AuthCenter login(String phone, String pass )throws Exception;

}
