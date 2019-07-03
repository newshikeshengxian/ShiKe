package com.sk.user.controller;

import com.sk.user.po.Coupon;
import com.sk.user.service.impl.CouponServiceImpl;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponServiceImpl couponService;

    /**
     * 跟据用户id查询所有的优惠券
     * @param uid
     * @return 返回jsonresult
     */
    @RequestMapping("/list")
    public JsonResult getCoupons(String uid){
        //        Map map = new HashMap();
//        map.put("token",userId);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://192.168.52.218:8080/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
//        if(forObject.getCode() == 0) {
        JsonResult jsonResult = new JsonResult();
        try {
            List<Coupon> coupons = couponService.queryAllCoupons(uid);
            jsonResult.setCode(0);
            jsonResult.setData(coupons);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }
    }
    //        }
//        jsonResult.setCode(1);
//        return jsonResult;
}
