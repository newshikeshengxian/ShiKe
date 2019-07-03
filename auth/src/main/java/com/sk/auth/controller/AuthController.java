package com.sk.auth.controller;

import com.sk.auth.note.Authentication;
import com.sk.auth.po.AuthCenter;
import com.sk.auth.service.IAuthSrevice;
import com.sk.auth.vo.JsonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @Autowired
    private IAuthSrevice iAuthSrevice;

    @Autowired
    private Authentication authentication;

    @RequestMapping("/login")
    @ResponseBody
    public JsonVO selectAuth(String phone, String pass){
        JsonVO jsonVO = new JsonVO();
        AuthCenter authCenter = null;
        try {
            authCenter = iAuthSrevice.login(phone, pass);
            jsonVO.setCode(0);
            jsonVO.setObj(authCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(authCenter == null){
            jsonVO.setCode(1);
            return jsonVO;
        }
        return jsonVO;
    }


    @RequestMapping("/key")
    @ResponseBody
    public JsonVO auth(String key,String name){
        JsonVO jsonVO = new JsonVO();
        boolean boo = authentication.authAdmin(key,name);
        if(boo){
            jsonVO.setCode(0);
        }else {
            jsonVO.setCode(1);
        }
        return jsonVO;
    }

}
