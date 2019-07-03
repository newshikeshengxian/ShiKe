package com.sk.store.controller;

import com.sk.store.po.Product;
import com.sk.store.service.IProduct;
import com.sk.store.vo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProduct product;

    /** 查询新品
     * 商品新品查询
     * @return
     */
    @RequestMapping("/newProduct")
    @ResponseBody
    public ResultJson newProduct(){
        List<Product> products = product.newProduct();
        return ResultJson.createSuccessResult(products);
    }
}
