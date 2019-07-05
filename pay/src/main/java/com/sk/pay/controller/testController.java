package com.sk.pay.controller;

import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class testController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("test")
    public String aaa(Model model){
        Map map = new HashMap();
        map.put("indentId","2165498484765487");
        map.put("prMoney","0.1");
        map.put("payMode","2");
        JsonResult jsonResult = restTemplate.postForObject("http://pay-server/pay", map, JsonResult.class);
        String msg = jsonResult.getMsg();
        model.addAttribute("code",msg);
        return "pay.html";
    }
}
