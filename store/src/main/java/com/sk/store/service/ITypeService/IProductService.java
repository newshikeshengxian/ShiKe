package com.sk.store.service.ITypeService;

import com.sk.store.mapper.ProductMapper;
import com.sk.store.mapper.TypeMapper;
import com.sk.store.po.*;
import com.sk.store.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class IProductService implements IProduct {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Product> newProduct() {
        return productMapper.newProduct();
    }

    @Override
    public List<ProductOne> allProductByPthId() {
        List<ProductOne> productOne = typeMapper.listAllTypeOne();
        for(ProductOne product : productOne){
            List<ProducrTwo> producrTwos = typeMapper.listAllTypeTwo(product.getPoId());
            product.setProducrTwo(producrTwos);
            for (ProducrTwo producrTwo : producrTwos){
                List<ProductThree> productThrees = typeMapper.listAllTypeThree(producrTwo.getPtId());
                producrTwo.setProductThree(productThrees);
                for (ProductThree productThree : productThrees){
                    List<Product> productList = productMapper.allProductByPthId(productThree.getPthId());
                    productThree.setProducts(productList);
                }

            }
        }
        return productOne;
    }

    @Override
    public List<Promotion> allPromotion() {
        Date time=new Date(new java.util.Date().getTime());
        List<Promotion> promotions = productMapper.allPromotion(time);
        for (Promotion promotion : promotions){
            Product promotionProduct = productMapper.findPromotionProduct(promotion.getProduct_Id());
            promotion.setProduct(promotionProduct);
        }
        return promotions;
    }

    @Override
    public List<Product> findProductByPtId(String ptId) {
       List<Product> productByPtId = productMapper.findProductByPtId(ptId);
        return productByPtId;
    }

    @Override
    public Product product(String id) {
        Product product = productMapper.product(id);
        return product;
    }
}
