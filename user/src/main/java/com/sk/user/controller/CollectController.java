package com.sk.user.controller;

import com.sk.user.service.impl.CollectServiceImpl;
import com.sk.user.vo.CollectVO;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userCollect")
public class CollectController {
    @Autowired
    private CollectServiceImpl collectService;

    @RequestMapping("/list")
    public JsonResult getCollect(String userId,String token){
        //        Map map = new HashMap();
//        map.put("token",token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            List<CollectVO> list = collectService.queryAllCollect(userId);
            jsonResult.setCode(0);
            jsonResult.setData(list);
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
