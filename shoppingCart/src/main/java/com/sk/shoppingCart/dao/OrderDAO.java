package com.sk.shoppingCart.dao;

import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDAO {
    /**
     * 添加订单
     * @param orders
     */
    void addOrder(Indent orders);
    void addIndPro(List<IndPro> indPros);

    /**
     * 查询订单
     * @param map
     * @return
     */
    List<Indent> findOrder(Map map);

    /**
     * 修改订单状态
     * @param i
     */
    void updateOrderState(@Param("state") Integer state ,@Param("orderId") String orderId);
}
