package com.sk.user.service;

import com.sk.user.po.ReceiverAddr;

public interface IReceiverAddeService {
    /**
     * 根据收货ID查询收货地址
     * @param RaId
     * @return 收货地址
     */
    ReceiverAddr queryAddrById(String RaId)throws Exception;
}
