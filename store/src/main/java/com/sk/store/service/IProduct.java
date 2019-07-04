package com.sk.store.service;

import com.sk.store.po.Product;
import com.sk.store.po.ProductOne;
import com.sk.store.po.Promotion;

import java.util.List;

public interface IProduct {
    List<Product> newProduct();
    List<ProductOne> allProductByPthId();
    List<Promotion> allPromotion();
    List<Product> findProductByPtId(String ptId);
    Product product(String id);
}
