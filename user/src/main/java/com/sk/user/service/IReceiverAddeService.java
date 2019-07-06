package com.sk.user.service;

import com.sk.user.po.ReceiverAddr;

import java.util.List;

public interface IReceiverAddeService {
    /**
     * 根据收货ID查询收货地址
     * @param RaId
     * @return 收货地址
     */
    ReceiverAddr queryAddrById(String RaId)throws Exception;

    /**
     * 根据用户ID查询该用户所有的收货地址
     * @param userId
     * @return 收货地址集合
     * @throws Exception
     */
    List<ReceiverAddr> queryAllAddr(String userId)throws Exception;

    /**
     * 根据地址ID修改地址信息
     * @param receiverAddr
     * @throws Exception
     */
    void updateAllAddr(ReceiverAddr receiverAddr)throws Exception;

    /**
     * 根据地址ID修改地址信息 并查询所有的收货地
     * @param raId
     * @param userId
     * @return 收货地址集合
     * @throws Exception
     */
    List<ReceiverAddr>  deleteAddr(String raId,String userId) throws Exception;
}
