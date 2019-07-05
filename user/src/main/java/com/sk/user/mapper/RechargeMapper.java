package com.sk.user.mapper;

import com.sk.user.po.Recharge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RechargeMapper {
    /**
     * 添加余额记录
     * @param recharge
     */
    void addRecharge(Recharge recharge);

    /**
     * 根据用户ID查询该用户所有的余额记录
     * @param userId
     * @return 余额记录集合
     */
    List<Recharge> queryAllRecharges(String userId);
}
