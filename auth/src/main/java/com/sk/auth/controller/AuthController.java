package com.sk.auth.controller;
import com.sk.auth.po.AuthCenter;
import com.sk.auth.service.IAuthSrevice;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private IAuthSrevice iAuthSrevice;


    /**
     * 登陆
     * @param map
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult selectAuth(@RequestBody Map map){
        JsonResult jsonVO = new JsonResult();
        String phone =(String) map.get("phone");
        String pass = (String) map.get("pass");
        AuthCenter authCenter = null;
        try {
            authCenter = iAuthSrevice.login(phone, pass);
            jsonVO.setCode(0);
            jsonVO.setData(authCenter);
        } catch (Exception e) {
            jsonVO.setCode(1);
            e.printStackTrace();
        }
        if(authCenter == null){
            jsonVO.setCode(1);
            return jsonVO;
        }
        return jsonVO;
    }


    /**
     * 验证手机号是否存在
     * @param map
     * @return
     */
    @RequestMapping("/verifyPhone")
    @ResponseBody
    public JsonResult verifyPhone(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        String phone = (String) map.get("phone");
        AuthCenter authCenter = null;
        try {
            authCenter = iAuthSrevice.selectAuthcenter(phone);
        } catch (Exception e) {
            jsonResult.setCode(1);
            e.printStackTrace();
        }
        if(authCenter == null){
            jsonResult.setCode(1);
        }else {
            jsonResult.setCode(0);
        }
        return jsonResult;
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping("/verifyPass")
    @ResponseBody
    public JsonResult verifyPass(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        try {
            iAuthSrevice.alterPass(map);
            jsonResult.setCode(0);
        } catch (Exception e) {
            jsonResult.setCode(1);
            e.printStackTrace();
        }
        return jsonResult;
    }
}
