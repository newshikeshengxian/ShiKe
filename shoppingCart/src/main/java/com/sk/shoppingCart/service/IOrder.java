package com.sk.shoppingCart.service;

import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.po.ShopCart;
import com.sk.shoppingCart.vo.JsonResult;

import java.util.List;
import java.util.Map;

public interface IOrder {
     List<ShopCart> addShopCart(ShopCart shopCart) throws Exception;
     List<ShopCart> shopCartList(String userId)throws Exception;
     List<ShopCart> deleteShopCart(Map map) throws Exception;
     JsonResult addOrder(Indent order)throws Exception;
     void addIndPro(List<IndPro> indPros) throws Exception;
     String findOrder(Map map)throws Exception;
     void updateOrderState(Map map)throws Exception;
     List<ShopCart> findShopCartList(Map map);
     List<Map> selectSales()throws Exception;

}
