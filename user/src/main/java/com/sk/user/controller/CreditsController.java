package com.sk.user.controller;

import com.sk.user.service.impl.CreditsServiceImpl;
import com.sk.user.vo.JsonResult;
import com.sk.user.vo.ListCreditsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/credits")
public class CreditsController {
    @Autowired
    private CreditsServiceImpl creditsService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 参数名 userId
    *查询所有的积分
     * 返回jsonresult
    */
    @RequestMapping("/list")
    public JsonResult getCredits(String uid){
//        Map map = new HashMap();
//        map.put("token",userId);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://192.168.52.218:8080/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
//        if(forObject.getCode() == 0) {
        JsonResult jsonResult = null;
            try {
                ListCreditsVO creditsVO = creditsService.queryAllCre(uid);
                jsonResult = new JsonResult();
                jsonResult.setCode(0);
                jsonResult.setData(creditsVO);
                return jsonResult;
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setCode(1);
                return jsonResult;
            }
//        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }
}
