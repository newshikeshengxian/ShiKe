package com.sk.user.service;

import com.sk.user.po.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICouponService {
    /**
     * 查询用户所有的优惠券
     * @param userId
     * @return 哟回圈集合
     * @throws Exception
     */
    List<Coupon> queryAllCoupons(String userId) throws  Exception;

}
