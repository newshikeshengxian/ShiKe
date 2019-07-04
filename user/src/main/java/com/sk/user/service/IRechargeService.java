package com.sk.user.service;

import com.sk.user.po.Recharge;
import com.sk.user.po.User;

import java.util.List;
import java.util.Map;

public interface IRechargeService {
    /**
     * 添加余额记录 修改用户余额
     * @param map
     * 返回该用户信息
     */
    User addRecharge(Map map)throws Exception;

    /**
     * 根据用户ID查询该用户所有的余额记录
     * @param userId
     * @return 余额记录集合
     */
    List<Recharge> queryAllRecharges(String userId)throws  Exception;
}
