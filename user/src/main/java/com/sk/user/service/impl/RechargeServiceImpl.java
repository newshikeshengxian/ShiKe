package com.sk.user.service.impl;

import com.sk.user.mapper.RechargeMapper;
import com.sk.user.po.Recharge;
import com.sk.user.po.User;
import com.sk.user.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RechargeServiceImpl implements IRechargeService {

    @Autowired
    private RechargeMapper rechargeMapper;
    @Autowired
    private UserServiceImpl userService;
    /**
     * 根据用户ID查询该用户所有的余额记录
     * @param userId
     * @return 余额记录集合
     */
    @Override
    public List<Recharge> queryAllRecharges(String userId) throws Exception{
        List<Recharge> recharges = rechargeMapper.queryAllRecharges(userId);
        for(Recharge recharge : recharges){
            if(recharge.getRechState() == 1){
                recharge.setRechType("消费");
            }else {
                recharge.setRechType("充值");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(recharge.getRechTime().getTime());
            String format1 = format.format(date);
            recharge.setTime(format1);
        }
        return recharges;
    }

    /**
     * 添加余额记录 修改用户余额
     * @param map
     * 返回该用户信息
     */
    @Transactional
    @Override
    public User addRecharge(Map map) throws Exception {
        Recharge recharge = new Recharge();
        recharge.setRechId(UUID.randomUUID().toString());
        recharge.setRechPrice(Double.parseDouble((String) map.get("rechPrice")));
        recharge.setRechState(2);
        recharge.setRechTime(new Timestamp(new Date().getTime()));
        recharge.setUserId((String) map.get("userId"));
        recharge.setRechNum((String) map.get("rechNum"));
        recharge.setRechNum((String) map.get("rechPwd"));
        rechargeMapper.addRecharge(recharge);
        userService.updateUserBalance((String) map.get("userId"), Double.parseDouble((String) map.get("rechPrice")));
        User userId = userService.queryUser((String) map.get("userId"));
        return userId;
    }
}
