package com.sk.shoppingCart.dao;

import com.sk.shoppingCart.po.IndPro;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.po.ShopCart;
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
    void addIndPro(@Param("indPros") List<IndPro> indPros);

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
    void updateOrderState(Map map);

    void addShopCart(ShopCart shopCart);
    List<ShopCart> ShopCartList(@Param("userId") String userId);
    void deleteShopCart(@Param("id") String id);
    void deleteShopCarts(@Param("shopCarts") List<IndPro> shopCarts);
}
