package com.sk.shoppingCart.controller;

import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.service.IOrder;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrder orderService;

    /**
     * 添加购物车接口
     * 入参 一个产品
     * @return  状态 code(0 成功 1 失败)
     *          msg 信息
     */
    @RequestMapping("/addShopCart")
    public JsonResult addShopCart(){
        JsonResult jsonResult = new JsonResult();

        return jsonResult;
    }

    /**
     * 查询购物车
     * @param userId 用户id
     * @return
     */
    @RequestMapping("/shopCartList")
    public JsonResult shopCartList(String userId){
        JsonResult jsonResult = new JsonResult();
        return jsonResult;

    }

    @RequestMapping("/addOrder")
    public JsonResult addOrder(@RequestBody Indent order){
        JsonResult jsonResult = new JsonResult();
        try {
            orderService.addOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 订单查询接口
     * @param map userId 用户ID
     *            state 订单状态
     *            indentType 订单类型（0 普通 1 促销 2 抢购）
     *            deliverTime 发货时间
     *            receiveTime 签收时间
     *            leaveWord 留言
     *            months 月份
     * @return 包含订单集合的JSON串
     */
    @RequestMapping("/list")
    public JsonResult OrderList(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Indent> order = orderService.findOrder(map);
            jsonResult.setCode(0);
            jsonResult.setData(order);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
            jsonResult.setMsg("出错了");
        }

        return jsonResult;
    }



}
