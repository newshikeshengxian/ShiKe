package com.sk.pay.service;

import com.github.wxpay.sdk.MyWXConfig;
import com.github.wxpay.sdk.WXPay;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Component
public  class PayService {

    @Autowired
    private RestTemplate restTemplate;

    JsonResult jsonResult = new JsonResult();

    public JsonResult   addOrder(String indentId,String price){
        price = "1";
        try {

            WXPay wxPay = new WXPay(new MyWXConfig());
            Map<String, String> data = new HashMap<String, String>();

            //商品的名称
            data.put("body", "周杰伦环球演唱会-武汉站");
            //订单号（自定义），不能重复！！！
            data.put("out_trade_no", indentId);
            //设备信息
            data.put("device_info", "PC");
            //币种
            data.put("fee_type", "CNY");
            //订单金额（单位是分）
            data.put("total_fee", "1");
            //设备IP
            data.put("spbill_create_ip", "123.12.12.123");

            /*------------------------------------notify_url----------------------------------------*/
            //回调接口（微信支付完之后的通知接口）(测试使用内网穿透：会定时变;需要及时修改)
            data.put("notify_url", "http://hribrn.natappfree.cc/notify");
            //支付模式native或者jsapi
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            //产品ID自定义
            data.put("product_id", "12");

            //下预付单（下给微信了） ； 返回值是一个支付短链接
            Map<String, String> resp = wxPay.unifiedOrder(data);
            //支付的短链接
            String code_url = resp.get("code_url");
            //---------微信预付单----------------------
            jsonResult.setCode(3);
            jsonResult.setMsg(code_url);
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("indentId",indentId);
            map.put("type","4");
            restTemplate.postForObject("http://order/updateOrderState",map,JsonResult.class);
            jsonResult.setCode(0);
        }
        return jsonResult;
    }



}
