package com.sk.store.controller;

import com.sk.store.po.Product;
import com.sk.store.po.ProductOne;
import com.sk.store.po.Promotion;
import com.sk.store.service.IProduct;
import com.sk.store.vo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /** 根据所有产品类型查看商品
     *
     * @return
     */
    @RequestMapping("/allProductById")
    @ResponseBody
    public ResultJson allProductByPthId(){
        List<ProductOne> productOnes = product.allProductByPthId();
        return ResultJson.createSuccessResult(productOnes);
    }

    /** 查看正在促销的商品
     *
     * @return
     */
    @RequestMapping("/promotion")
    @ResponseBody
    public ResultJson promotionProduct(){
        List<Promotion> promotions = product.allPromotion();
        return ResultJson.createSuccessResult(promotions);
    }

    /** 根据二级产品类型查看商品列表
     *
     * @return
     */
    @RequestMapping("/findProductByPtId")
    @ResponseBody
    public ResultJson findProductByPtId(@RequestParam(value = "ptId") String ptId){
        String id="0000";
        if(ptId.equals("1")){
            id="0001";
        }else if (ptId.equals("2")){
            id="0002";
        }
        List<Product> productByPtId = product.findProductByPtId(id);
        return ResultJson.createSuccessResult(productByPtId);
    }


    /** 查看单个商品
     *
     * @return
     */
    @RequestMapping("/products")
    @ResponseBody
    public ResultJson products(String id){
        Product products = product.product(id);
        return ResultJson.createSuccessResult(products);
    }
}
