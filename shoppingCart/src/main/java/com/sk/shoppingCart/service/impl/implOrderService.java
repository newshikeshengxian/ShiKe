package com.sk.shoppingCart.service.impl;

import com.sk.shoppingCart.dao.OrderDAO;
import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.service.IOrder;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

@Service
public class implOrderService implements IOrder {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderDAO orderDAO;



    @Override
    public void addShopCart() throws Exception{

    }

    @Override
    public JsonResult addOrder(Indent order) throws Exception {
        String token = order.getUserId();
        Map map = new HashMap();
        map.put("token",token);
        JsonResult jsonResult = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
        if(jsonResult.getCode()==0){
            Random random = new Random();
            String usrrId = (String)jsonResult.getData();
            order.setUserId(usrrId);
            long time  = new Date().getTime();
            String orderId = time +random.nextInt(1000)+"";
            Timestamp createTime = new Timestamp(time);
            order.setIndentId(orderId);
            order.setCreateTime(createTime);
            order.setState(0);
            orderDAO.addOrder(order);
            addIndPro(order.getProducts());
            orderDAO.updateOrderState(1,orderId);
            map.put("indentId",orderId);
            map.put("prMoney",order.getPrice());
            map.put("payMode",order.getPayMent());
            jsonResult = restTemplate.postForObject("http://pay/pay",map, JsonResult.class);
            if(jsonResult.getCode()!=1){
                orderDAO.updateOrderState(2,orderId);
            }
        }
        return jsonResult;
    }

    @Override
    public void addIndPro(List<IndPro> indPros) throws Exception {

    }

    @Override
    public List<Indent> findOrder(Map map) throws Exception{
        List<Indent> order = orderDAO.findOrder(map);
        return order;
    }
}
