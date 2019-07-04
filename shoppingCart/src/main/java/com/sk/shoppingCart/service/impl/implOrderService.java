package com.sk.shoppingCart.service.impl;

import com.google.gson.Gson;
import com.sk.shoppingCart.dao.OrderDAO;
import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.po.ShopCart;
import com.sk.shoppingCart.service.IOrder;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

@Service
public class implOrderService implements IOrder {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderDAO orderDAO;


    /**
     * 添加购物车
     * @param shopCart
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<ShopCart> addShopCart(ShopCart shopCart) throws Exception{
        List<ShopCart> shopCartList = null;
        String token = shopCart.getUserId();
        JsonResult jsonResult = auth(token);
        if(jsonResult.getCode()==0){
            Map<String,String> userMap = (Map)jsonResult.getData();
            String userId = userMap.get("userId");
            shopCart.setUserId(userId);
            orderDAO.addShopCart(shopCart);
            shopCartList = shopCartList(userId);
        }else{
            throw new Exception();
        }
        return shopCartList;
    }

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    @Override
    public List<ShopCart> shopCartList(String userId) {
        List<ShopCart> shopCartList = orderDAO.ShopCartList(userId);
        return shopCartList;
    }

    /**
     * 删除购物车
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<ShopCart> deleteShopCart(Map<String,String> map)throws Exception {
        List<ShopCart> shopCartList = null;
        String token = map.get("token");
        JsonResult jsonResult = auth(token);
        if(jsonResult.getCode()==0){
            String id = map.get("id");
            Map<String,String> userMap = (Map)jsonResult.getData();
            String userId = userMap.get("userId");
            orderDAO.deleteShopCart(id);
            shopCartList = shopCartList(userId);
        }else {
            throw  new Exception();
        }
        return shopCartList;
    }

    /**
     * 添加订单
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JsonResult addOrder(Indent order) throws Exception {
        String token = order.getUserId();
        JsonResult jsonResult = auth(token);
        if(jsonResult.getCode()==0){
            Random random = new Random();
            Map<String,String> map = (Map)jsonResult.getData();
            String userId = map.get("userId");
            order.setUserId(userId);
            long time  = new Date().getTime();
            String orderId = time +random.nextInt(1000)+"";
            Timestamp createTime = new Timestamp(time);
            order.setIndentId(orderId);
            order.setCreateTime(createTime);
            order.setState(0);
            List<IndPro> pros = order.getProducts();
            for(IndPro pro :pros){
                pro.setIndentId(orderId);
            }
            orderDAO.addOrder(order);
            addIndPro(order.getProducts());
            map.put("state","1");
            map.put("userId",userId);
            orderDAO.updateOrderState(map);
            orderDAO.deleteShopCarts(pros);
            jsonResult = pay(order);
            if(jsonResult.getCode()!=1){
                return jsonResult;
            }else{
                throw new Exception();
            }
        }else{
            throw  new Exception();
        }
    }

    /**
     * 添加订单产品
     * @param indPros
     * @throws Exception
     */
    @Override
    @Transactional
    public void addIndPro(List<IndPro> indPros) throws Exception {
        orderDAO.addIndPro(indPros);
    }



    /**
     * 查询订单
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public String findOrder(Map map) throws Exception{
        List<Indent> order = orderDAO.findOrder(map);
        Gson gson = new Gson();
        String s = gson.toJson(order);
        return s;
    }

    @Override
    @Transactional
    public void updateOrderState(Map map) throws Exception {
        orderDAO.updateOrderState(map);
    }


    /**
     * 鉴权
     * @param token
     * @return
     */
    public JsonResult auth(String token){
        Map map = new HashMap();
        map.put("token",token);
        JsonResult jsonResult = restTemplate.postForObject("http://auth-server/testLogin",map,JsonResult.class);
        return jsonResult;
    }


    /**
     * 支付
     * @param order
     * @return
     */
    public JsonResult pay(Indent order){
        Map map = new HashMap();
        map.put("indentId",order.getIndentId());
        map.put("prMoney",order.getPrice());
        map.put("payMode",order.getPayMent());
        JsonResult jsonResult = restTemplate.postForObject("http://pay-server/pay",map, JsonResult.class);
        return jsonResult;
    }
}
