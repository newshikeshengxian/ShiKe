package com.sk.user.mapper;

import com.sk.user.po.ReceiverAddr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceiveAddrMapper {
    /**
     * 根据收货ID查询收货地址
     * @param RaId
     * @return 收货地址
     */
    ReceiverAddr queryAddrById(String RaId);

    /**
     * 根据用户ID查询该用户的所有收货地址
     * @param userId
     * @return 地址集合
     */
    List<ReceiverAddr> queryAllAddr(String userId);

    /**
     * 修改收货地址
     * @param receiverAddr
     */
    void updateAddr(ReceiverAddr receiverAddr);

    /**
     * 根据地址ID删除改地址信息
     * @param raId
     */
    void deleteAddr(String raId);
}
