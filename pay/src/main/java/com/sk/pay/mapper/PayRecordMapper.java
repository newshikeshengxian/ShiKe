package com.sk.pay.mapper;

import com.sk.pay.po.PayRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayRecordMapper {

    void addPay(PayRecord payRecord);

    void setPay(PayRecord pay);

}
