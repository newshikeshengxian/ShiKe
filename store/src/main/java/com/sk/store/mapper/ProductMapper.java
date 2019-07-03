package com.sk.store.mapper;


import com.sk.store.po.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> newProduct();
}
