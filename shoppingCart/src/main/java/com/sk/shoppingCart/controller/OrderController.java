package com.sk.shoppingCart.controller;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sk.shoppingCart.po.Indent;
import com.sk.shoppingCart.po.ShopCart;
import com.sk.shoppingCart.service.IOrder;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrder orderService;

    /**
     * 添加购物车接口
     * 入参 一个产品
     * @return  状态 code(0 成功 1 失败)
     *          msg 信息
     */
    @RequestMapping("/addShopCart")
    public JsonResult addShopCart(@RequestBody ShopCart shopCart){
            JsonResult jsonResult = new JsonResult();
        try {
            List<ShopCart> shopCartList = orderService.addShopCart(shopCart);
            jsonResult.setCode(0);
            jsonResult.setData(shopCartList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

    /**
     * 查询购物车
     * @param
     * @return
     */
    @RequestMapping("/shopCartList")
    public JsonResult shopCartList(@RequestBody Map<String,String> map){
        JsonResult jsonResult = new JsonResult();
        try {
            List<ShopCart> shopCartList = orderService.findShopCartList(map);
            jsonResult.setCode(0);
            jsonResult.setData(shopCartList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

    /**
     * 删除购物车
     * @param map
     * @return
     */
    @RequestMapping("/deleteShopCart")
    public JsonResult deleteShopCart(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        try {
            List<ShopCart> shopCartList = orderService.deleteShopCart(map);
            jsonResult.setCode(0);
            jsonResult.setData(shopCartList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }

        return  jsonResult;
    }

    /**
     * 普通订单添加
     * @param order
     * @return
     */
    @RequestMapping("/addOrder")
    public JsonResult addOrder(@RequestBody Indent order){
        JsonResult jsonResult = new JsonResult();
        try {
            jsonResult = orderService.addOrder(order);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/updateOrderState")
    public JsonResult updateOrderState(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        try {
            orderService.updateOrderState(map);
            jsonResult.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

    /**
     * 订单查询接口
     * @param map userId 用户ID
     *            state 订单状态
     *            indentType 订单类型（0 普通 1 促销 2 抢购）
     *            deliverTime 发货时间
     *            receiveTime 签收时间
     *            leaveWord 留言
     *            months 月份
     * @return 包含订单集合的JSON串
     */
    @RequestMapping("/list")
    public JsonResult OrderList(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        try {
            String order = orderService.findOrder(map);
            jsonResult.setCode(0);
            jsonResult.setData(order);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
            jsonResult.setMsg("出错了");
        }

        return jsonResult;
    }
    @RequestMapping("/selectSales")
    public JsonResult selectSales(){
        JsonResult jsonResult = new JsonResult();

        List<Map> maps = null;
        try {
            maps = orderService.selectSales();
            String json = new Gson().toJson(maps);
            jsonResult.setCode(0);
            jsonResult.setData(json);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode(1);
        }
        return jsonResult;
    }


    /**
     * 二维码生成
     */
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



}
