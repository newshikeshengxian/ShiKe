package com.sk.user.service.impl;

import com.sk.user.mapper.ReceiveAddrMapper;
import com.sk.user.po.ReceiverAddr;
import com.sk.user.service.IReceiverAddeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiverAddrServiceImpl implements IReceiverAddeService {
    @Autowired
    private ReceiveAddrMapper receiveAddrMapper;


    @Override
    public ReceiverAddr queryAddrById(String RaId) throws Exception{
        ReceiverAddr receiverAddr = receiveAddrMapper.queryAddrById(RaId);

        return receiverAddr;
    }
}
