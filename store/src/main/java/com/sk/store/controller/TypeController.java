package com.sk.store.controller;

import com.sk.store.po.ProductOne;
import com.sk.store.service.IType;
import com.sk.store.vo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private IType typeService;

    @RequestMapping("/listAllType")
    @ResponseBody
    public ResultJson listAllType(){
        List<ProductOne> listAllType = typeService.listAllType();
        return ResultJson.createSuccessResult(listAllType);
    }
}
