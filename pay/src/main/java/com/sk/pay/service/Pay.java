package com.sk.pay.service;

import com.github.wxpay.sdk.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sk.pay.po.PayRecord;
import com.sk.shoppingCart.vo.JsonResult;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Pay {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PayService payService;

    @Autowired
    private IPayRecordService iPayRecordService;

    @RequestMapping("/pay")
    @Transactional
    public JsonResult pay(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult() ;
        String indentId = (String)  map.get("indentId");
        String price = (String)  map.get("prMoney");
        String payMode =(String) map.get("payMode");
        double v = Double.parseDouble(price);
        if(payMode.equals("2")){
            iPayRecordService.addPay(indentId,v);
             jsonResult = payService.addOrder(indentId, price);
        }else {
            jsonResult.setData(1);
        }
        return jsonResult;
    }

    @RequestMapping("/qrcode")
    public void qrcode(String codeUrl, HttpServletResponse response){
        //-------------通过微信支付短链接生成二维码---------
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //二维码精度，M精度中等
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            //参数1:生成二维码的文本
            //参数2：生成二维码类（也可以生成条形码）
            //参数3：图片的长宽
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 200, 200, hints);
            //将生成的图片流写入返回对象的输出流中
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //-------------通过微信支付短链接生成二维码---------
    }

    @RequestMapping("/notify")
    @Transactional
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response){
        try {
            ServletInputStream inputStream = request.getInputStream();
            byte[] buffer = new byte[1024];
            int len =0;
            StringBuilder stringBuilder = new StringBuilder();
            while ((len=inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer,0,len));
            }
            //获取微信返回的订单号
            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(stringBuilder.toString());

            response.getWriter().println("<xml>\n" +
                    "\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>");
            //付款完成之后，修改订单状态为已支付
            String orderId = xmlToMap.get("out_trade_no");
            Map<String,String> map  = new HashMap<>();
            map.put("indentId",orderId);
            map.put("type","3");
            restTemplate.put("http://order/updateOrder",JsonResult.class,map);
            PayRecord payRecord = new PayRecord();
            payRecord.setIndentId(orderId);
            iPayRecordService.setPay(payRecord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-3d44e1928491488599f4a35fd862c228");
        //参数1：发送消息的频道.zhangsan 表示消息只发给张三
        goEasy.publish("member", "success");

    }



}
