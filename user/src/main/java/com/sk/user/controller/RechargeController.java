package com.sk.user.controller;

import com.sk.user.po.Recharge;
import com.sk.user.po.User;
import com.sk.user.service.impl.ReceiverAddrServiceImpl;
import com.sk.user.service.impl.RechargeServiceImpl;
import com.sk.user.service.impl.UserServiceImpl;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/userRecharge")
public class RechargeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RechargeServiceImpl rechargeService;

    /**
     * 添加余额
     * @param map
     * @return jsonresult data 中装有用户信息
     */
    @RequestMapping("/recharge")
    public JsonResult toRecharge(@RequestBody Map map){
        //        Map map = new HashMap();
//        map.put("token","666");
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            User user = rechargeService.addRecharge(map);
            jsonResult.setCode(0);
            jsonResult.setData(user);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }
        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }
    @RequestMapping("/list")
    public JsonResult getRecharges(String userId){
        //        Map map = new HashMap();
//        map.put("token","666");
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            List<Recharge> recharges = rechargeService.queryAllRecharges(userId);
            jsonResult.setCode(0);
            jsonResult.setData(recharges);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }



        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }

}
