package com.sk.auth.controller;

import com.sk.auth.note.Authentication;
import com.sk.auth.vo.JsonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private Authentication authentication;

    @RequestMapping("/ifLogin")
    @ResponseBody
    public JsonVO ifLogin(String key){
        JsonVO jsonVO = new JsonVO();
        String auth = authentication.auth(key);
        if(auth != null || auth != ""){
            jsonVO.setCode(0);
        }else {
            jsonVO.setCode(1);
        }
        return jsonVO;
    }

}
