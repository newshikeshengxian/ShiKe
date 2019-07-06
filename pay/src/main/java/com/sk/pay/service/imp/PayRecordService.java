package com.sk.pay.service.imp;

import com.sk.pay.mapper.PayRecordMapper;
import com.sk.pay.po.PayRecord;
import com.sk.pay.service.IPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PayRecordService implements IPayRecordService {

    @Autowired
    private PayRecordMapper payRecordMapper;

    @Override
    public void addPay(String id, double pay) {
        PayRecord payRecord = new PayRecord();
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        payRecord.setPrId(date);
        payRecord.setIndentId(id);
        payRecord.setPrMoney(pay);
        payRecord.setPrMode("微信支付");
        payRecord.setState(1);
        payRecordMapper.addPay(payRecord);
    }

    @Override
    public void setPay(PayRecord pay){
        PayRecord payRecord = new PayRecord();
        payRecord.setState(2);
        payRecordMapper.setPay(payRecord);
    }


}
