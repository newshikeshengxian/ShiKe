package com.sk.auth.controller;

import com.google.gson.Gson;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class Skip {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/skip")
    @ResponseBody
    public JsonResult skip(){
        JsonResult forObject = restTemplate.getForObject("http://store/type/listAllTypes", JsonResult.class);
        String data = (String)forObject.getData();
        Gson gson = new Gson();
        Object[] objects =  gson.fromJson(data, Object[].class);
        List<Object> objects1 = Arrays.asList(objects);
        forObject.setData(objects1);
        return forObject;
    }

}
