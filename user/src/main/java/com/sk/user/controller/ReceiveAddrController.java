package com.sk.user.controller;

import com.sk.user.po.ReceiverAddr;
import com.sk.user.service.impl.ReceiverAddrServiceImpl;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("/userAddr")
@RestController
public class ReceiveAddrController {

    @Autowired
    private ReceiverAddrServiceImpl receiverAddrService;

    @RequestMapping("/list")
    public JsonResult listAllAddr(String token){
        //        Map map = new HashMap();
//        map.put("token",token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            List<ReceiverAddr> receiverAddrs = receiverAddrService.queryAllAddr("0006");
            jsonResult.setCode(0);
            jsonResult.setData(receiverAddrs);
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

    @RequestMapping("/update")
    public JsonResult updateAddr(String token,ReceiverAddr receiverAddr){
        //        Map map = new HashMap();
//        map.put("token",token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            receiverAddrService.updateAllAddr(receiverAddr);
            jsonResult.setCode(0);
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
    @RequestMapping("/delete")
    public JsonResult deleteaddr(String token,String raId){
        //        Map map = new HashMap();
//        map.put("token",token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            List<ReceiverAddr> receiverAddrs = receiverAddrService.deleteAddr(raId, "0006");
            jsonResult.setCode(0);
            jsonResult.setData(receiverAddrs);
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
