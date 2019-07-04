package com.sk.user.controller;

import com.google.gson.Gson;
import com.sk.user.po.Indent;
import com.sk.user.po.ReceiverAddr;
import com.sk.user.service.impl.ReceiverAddrServiceImpl;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/userOrder")
public class UserOrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ReceiverAddrServiceImpl addrService;

    @RequestMapping("/list")
    public JsonResult getIndent(@RequestBody Map map){
        //        Map map = new HashMap();
//        map.put("token",userId);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult result = new JsonResult();
//        if(forObject.getCode() == 0) {
        Map s = map;
        JsonResult jsonResult = restTemplate.postForObject("http://order/order/list", map,JsonResult.class);
        Indent[] orders= new Gson().fromJson((String) jsonResult.getData(), Indent[].class);
        for( Indent indent : orders){
            String raId = indent.getRaId();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(indent.getCreateTime().getTime());
            String format1 = format.format(date);
            indent.setTime(format1);
            if(indent.getState() == 0){
                indent.setStateDesc("已下单");
            }else if(indent.getState() == 1){
                indent.setStateDesc("正在支付");
            }else if(indent.getState() == 2){
                indent.setStateDesc("已支付");
            }else if(indent.getState() == 3){
                indent.setStateDesc("支付失败");
            }else if(indent.getState() == 4){
                indent.setStateDesc("已完成");
            }else if(indent.getState() == 5){
                indent.setStateDesc("已退货订单");
            }else if(indent.getState() == 6){
                indent.setStateDesc("已退款订单");
            }
            try {
                ReceiverAddr receiverAddr = addrService.queryAddrById(raId);
                indent.setRaName(receiverAddr.getRaName());
            }catch (Exception e){

            }
        }
        result.setCode(0);
        result.setData(orders);
        return result;
        //        }
//        jsonResult.setCode(1);
//        return jsonResult;


    }
}
