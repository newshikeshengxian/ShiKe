package com.sk.auth.controller;

import com.sk.auth.service.IAuthSrevice;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private IAuthSrevice iAuthSrevice;

    /**
     * 判断是否登陆
     * @param key
     * @return
     */
    @RequestMapping("/ifLogin")
    @ResponseBody
    public JsonResult ifLogin(  @RequestBody Map map ){
        JsonResult jsonVO = new JsonResult();
        try {
            String id = iAuthSrevice.auth((String) map.get("token"));
            if(id == null || id == ""){
                jsonVO.setCode(1);
            }else {
                jsonVO.setCode(0);
                jsonVO.setData(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonVO;
    }


    /**
     * 查看用户的权限
     * @param key  Cookie 里的 Key = uid
     * @param name 权限的名称
     * @return
     */
    @RequestMapping("/Authkey")
    @ResponseBody
    public JsonResult auth(@RequestBody Map map){
        String key = (String) map.get("token");
        String name = (String) map.get("authName");
        JsonResult jsonVO = new JsonResult();
        try {
            boolean boo = iAuthSrevice.authAdmin(key,name);
            if(boo){
                jsonVO.setCode(0);
            }else {
                jsonVO.setCode(1);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonVO.setCode(1);
        }
        return jsonVO;
    }


    @RequestMapping("/testLogin")
    @ResponseBody
    public JsonResult testLogin( @RequestBody Map map ){
        JsonResult jsonVO = new JsonResult();
        String token = (String) map.get("token");
        try {
//            String id = iAuthSrevice.auth((String) map.get("token"));
            if(token.equals("111")){
                jsonVO.setCode(0);
                Map<String,String> map1 = new HashMap();
                map1.put("userId",token);
                map1.put("userName","韩驯");
                jsonVO.setData(map1);
            }else {
                jsonVO.setCode(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonVO;
    }

}
