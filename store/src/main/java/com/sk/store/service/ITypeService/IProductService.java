package com.sk.store.service.ITypeService;

import com.netflix.discovery.converters.Auto;
import com.sk.store.mapper.ProductMapper;
import com.sk.store.po.Product;
import com.sk.store.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProductService implements IProduct {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> newProduct() {
        return productMapper.newProduct();
    }
}
