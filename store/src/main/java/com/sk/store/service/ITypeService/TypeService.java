package com.sk.store.service.ITypeService;


import com.sk.store.mapper.TypeMapper;
import com.sk.store.po.ProducrTwo;
import com.sk.store.po.ProductOne;
import com.sk.store.po.ProductThree;
import com.sk.store.service.IType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService implements IType{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<ProductOne> listAllType() {
        List<ProductOne> productOne = typeMapper.listAllTypeOne();
        for(ProductOne product : productOne){
            List<ProducrTwo> producrTwos = typeMapper.listAllTypeTwo(product.getPoId());
            product.setProducrTwo(producrTwos);
            for (ProducrTwo producrTwo : producrTwos){
                List<ProductThree> productThrees = typeMapper.listAllTypeThree(producrTwo.getPtId());
                producrTwo.setProductThree(productThrees);
            }
        }
        return productOne;
    }
}
