package com.sk.user.controller;

import com.sk.user.po.User;
import com.sk.user.service.impl.UserServiceImpl;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/user")
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/queryUser")
    public JsonResult getUser(String uid){
//                Map map = new HashMap();
//        map.put("token",userId);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
//        if(forObject.getCode() == 0) {
        JsonResult result = new JsonResult();
        try {
            User user = userService.queryUser(uid);
            result.setCode(0);
            result.setData(user);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(1);
            return result;
        }
        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }
}
