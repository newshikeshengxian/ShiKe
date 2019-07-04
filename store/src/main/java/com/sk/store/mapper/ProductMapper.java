package com.sk.store.mapper;


import com.sk.store.po.Product;
import com.sk.store.po.Promotion;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> newProduct();
    List<Product> allProductByPthId(String pthId);
    List<Promotion> allPromotion(Date nowtime);
    Product findPromotionProduct(String pId);
    List<Product> findProductByPtId(String ptId);
    Product product(String id);
}
