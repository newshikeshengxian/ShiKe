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

    /** 查询所有商品类型
     * 商城分类页面，所有一级、二级、三级类型
     * @return
     */
    @RequestMapping("/listAllType")
    @ResponseBody
    public ResultJson listAllType(){
        List<ProductOne> listAllType = typeService.listAllType();
        return ResultJson.createSuccessResult(listAllType);
    }
}
