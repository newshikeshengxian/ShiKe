package com.sk.user.mapper;

import com.sk.user.po.ReceiverAddr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReceiveAddrMapper {
    /**
     * 根据收货ID查询收货地址
     * @param RaId
     * @return 收货地址
     */
    ReceiverAddr queryAddrById(String RaId);
}
