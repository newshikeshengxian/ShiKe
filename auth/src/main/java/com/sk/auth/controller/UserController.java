package com.sk.auth.controller;

import com.sk.auth.note.Note;
import com.sk.auth.po.User;
import com.sk.auth.service.IUserService;
import com.sk.auth.vo.JsonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private Note note;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/phone")
    @ResponseBody
    public JsonVO code(String phone){
        JsonVO jsonVO = new JsonVO();
        try {
            String note1 = note.note(phone);
            jsonVO.setMsg(note1);
        }catch (Exception e){
            e.printStackTrace();
            jsonVO.setCode(1);
        }
        return jsonVO;
    }


    @RequestMapping("/addUser")
    @ResponseBody
    public JsonVO addUser(User user){
        JsonVO jsonVO = new JsonVO();
        try {
            iUserService.addUser(user);
            jsonVO.setCode(0);
        }catch ( Exception e){
            jsonVO.setCode(1);
            e.printStackTrace();
        }
        return jsonVO;
    }



}
