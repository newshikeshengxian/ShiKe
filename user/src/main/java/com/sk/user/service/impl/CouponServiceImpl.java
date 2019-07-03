package com.sk.user.service.impl;

import com.sk.user.mapper.CouponMapper;
import com.sk.user.po.Coupon;
import com.sk.user.service.ICouponService;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements ICouponService {
    @Autowired
    private CouponMapper couponMapper;


    /**
     * 根据用户iD查询其所有的优惠券
     * @param userId
     * @return 优惠券的集合
     * @throws Exception
     */
    @Override
    @Transactional
    public List<Coupon> queryAllCoupons(String userId) throws Exception {
        List<Coupon> coupons = couponMapper.queryAllCoupons(userId);
        for(Coupon coupon : coupons){
            if(coupon.getEndTime().getTime() < new Date().getTime()) {
                    coupon.setUseState("已过期");
                    couponMapper.updateState(coupon.getCouponId(),2);
            }else {
                if (coupon.getState() == 0) {
                    coupon.setUseState("未使用");
                } else {
                    coupon.setUseState("已使用");
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp beginTime = coupon.getBeginTime();
            Timestamp endtime = coupon.getEndTime();

            Date date = new Date(beginTime.getTime());
            Date date1 = new Date(endtime.getTime());

            String time1 = format.format(date);
            String time2 = format.format(date1);
            coupon.setTime1(time1);
            coupon.setTime2(time2);
        }
        return coupons;
    }
}
