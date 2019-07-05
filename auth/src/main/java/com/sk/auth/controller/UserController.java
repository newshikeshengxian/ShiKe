package com.sk.auth.controller;
import com.sk.auth.note.Note;
import com.sk.auth.po.User;
import com.sk.auth.service.IUserService;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private Note note;

    @Autowired
    private IUserService iUserService;

    /**
     * 获得手机验证码
     * @param phone
     * @return
     */
    @RequestMapping("/phone")
    @ResponseBody
    public JsonResult code( @RequestBody Map map){
        String phone =(String) map.get("phone");
        JsonResult jsonVO = new JsonResult();
        try {
            String note1 = note.note(phone);
            jsonVO.setCode(0);
            jsonVO.setMsg(note1);
        }catch (Exception e){
            e.printStackTrace();
            jsonVO.setCode(1);
        }
        return jsonVO;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonResult addUser( @RequestBody User user){
        JsonResult jsonVO = new JsonResult();
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
