package com.sk.store.mapper;


import com.sk.store.po.ProducrTwo;
import com.sk.store.po.ProductOne;
import com.sk.store.po.ProductThree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TypeMapper {

    List<ProductOne> listAllTypeOne();
    List<ProducrTwo> listAllTypeTwo(String poId);
    List<ProductThree> listAllTypeThree(String ptId);

}
