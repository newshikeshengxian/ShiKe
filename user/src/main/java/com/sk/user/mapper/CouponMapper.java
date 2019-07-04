package com.sk.user.mapper;

import com.sk.user.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {
    /**
     * 查询用户所有的优惠券
     * @param userId
     * @return 优惠券集合
     */
    List<Coupon> queryAllCoupons(String userId);

    /**
     * 根据优惠券修改优惠券状态
     * @param couponId
     * @param state
     */
    void updateState(@Param("couponId") String couponId ,@Param("state") int state);
}
