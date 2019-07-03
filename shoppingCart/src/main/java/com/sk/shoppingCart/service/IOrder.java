package com.sk.shoppingCart.service;

import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.vo.JsonResult;

import java.util.List;
import java.util.Map;

public interface IOrder {
     void addShopCart() throws Exception;
     JsonResult addOrder(Indent order)throws Exception;
     void addIndPro(List<IndPro> indPros) throws Exception;
     List<Indent> findOrder(Map map)throws Exception;
}
