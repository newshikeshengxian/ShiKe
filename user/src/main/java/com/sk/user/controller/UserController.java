package com.sk.user.controller;

import com.sk.user.po.User;
import com.sk.user.service.impl.UserServiceImpl;
import com.sk.user.util.JschSftpClient;
import com.sk.user.vo.JsonResult;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/user")
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/queryUser")
    public JsonResult getUser(String uid,String token){
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

    @RequestMapping("/upload")
    public JsonResult upload(MultipartFile file){
        JsonResult jsonResult = new JsonResult();
        try {
            String path = JschSftpClient.testPsw(file);
            if(!path.equals("1")) {
                jsonResult.setCode(0);
                jsonResult.setData(path);
                return jsonResult;
            }else {
                throw new NullPointerException();
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }

    }
    @RequestMapping("/userUpdate")
    public JsonResult userUpdate(@RequestBody User user){
        //        Map map = new HashMap();
//        map.put("token",user.token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult result = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            User user1 = userService.updateUser(user);
            result.setCode(0);
            result.setData(user1);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(1);
            return  result;
        }


        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }

}
