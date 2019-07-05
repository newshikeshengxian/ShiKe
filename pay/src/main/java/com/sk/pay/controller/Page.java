package com.sk.pay.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class Page {

    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }

}
