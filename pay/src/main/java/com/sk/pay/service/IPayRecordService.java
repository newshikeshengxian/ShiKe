package com.sk.pay.service;

import com.sk.pay.po.PayRecord;

public interface IPayRecordService {

    void addPay(String id, double pay);

    void setPay(PayRecord pay);

}
