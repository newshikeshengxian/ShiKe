package com.sk.auth.service;

import com.sk.auth.po.User;

public interface IUserService {

    void addUser(User user)throws Exception;

    User selectUser(String userId)throws Exception;

}
