package com.sk.user.service.impl;

import com.sk.user.mapper.ReceiveAddrMapper;
import com.sk.user.po.ReceiverAddr;
import com.sk.user.service.IReceiverAddeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceiverAddrServiceImpl implements IReceiverAddeService {
    @Autowired
    private ReceiveAddrMapper receiveAddrMapper;



    /**
     * 根据地址ID修改地址信息 并查询所有的收货地址
     * @param receiverAddr
     * @return 收货地址集合
     * @throws Exception
     */
    @Override
    public void updateAllAddr(ReceiverAddr receiverAddr) throws Exception {
        receiveAddrMapper.updateAddr(receiverAddr);
    }

    /**
     * 根据收货地址ID删除该收货地址 并根据用户ID查询该用户所有的收货地址
     * @param raId
     * @param userId
     * @return 收货地址集合
     * @throws Exception
     */
    @Override
    public List<ReceiverAddr> deleteAddr(String raId, String userId) throws Exception {
        receiveAddrMapper.deleteAddr(raId);
        List<ReceiverAddr> receiverAddrs = receiveAddrMapper.queryAllAddr(userId);
        return receiverAddrs;
    }

    /**
     * 根据用户ID查询该用户所有的收货地址
     * @param userId
     * @return 收货地址集合
     * @throws Exception
     */
    @Override
    public List<ReceiverAddr> queryAllAddr(String userId) throws Exception {
        List<ReceiverAddr> receiverAddrs = receiveAddrMapper.queryAllAddr(userId);
        for(ReceiverAddr receiverAddr : receiverAddrs){
            if(receiverAddr.getRaState() == 0){
                receiverAddr.setDefaultAddr("默认地址");
            }
        }
        return receiverAddrs;
    }
    /**
     * 根据收货ID查询收货地址
     * @param RaId
     * @return 收货地址
     */
    @Override
    public ReceiverAddr queryAddrById(String RaId) throws Exception{
        ReceiverAddr receiverAddr = receiveAddrMapper.queryAddrById(RaId);
        return receiverAddr;
    }
}
