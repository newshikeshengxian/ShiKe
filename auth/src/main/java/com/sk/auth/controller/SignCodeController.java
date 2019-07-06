package com.sk.auth.controller;

import com.sk.auth.note.AuthCode;
import com.sk.shoppingCart.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
public class SignCodeController  {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 获取图片验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */ @RequestMapping("/getCode")
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthCode authCode = new AuthCode();
        //获取验 证码图片
        BufferedImage image = authCode.getImage();
        //获取验证码内容
        String text = authCode.getText();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer(); randomCode.append(text);
        // 将验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("code", randomCode.toString());
        // 禁止图像缓存。 response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }
    /**
     * 验证图片验证码
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public JsonResult check(HttpServletRequest request,@RequestBody Map map)  {
        String code = (String) map.get("code");
        JsonResult jsonResult = new JsonResult();
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("code");
        jsonResult.setCode(0);
        if (code == null || code == "") {
            jsonResult.setCode(1);
            new Exception("code is null");
        }
        if (signcodeSession == null || signcodeSession == ""){
            jsonResult.setCode(1);
            new Exception("signcodeSession is null");
        }
        //验证的时候不区分大小写
        if (code.equalsIgnoreCase(signcodeSession)) {
            jsonResult.setCode(0);
            return jsonResult;
        }else {
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

    /**
     * 判断手机验证码
     * @param map
     * @return
     */
    @RequestMapping("/verifyCode")
    @ResponseBody
    public JsonResult code(@RequestBody Map map){
        JsonResult jsonResult = new JsonResult();
        String phone = (String) map.get("phone");
        String code =  (String) map.get("code");
        String Code = redisTemplate.boundValueOps(phone).get();
        if(code.equals(Code)){
            jsonResult.setCode(0);
        }else {
            jsonResult.setCode(1);
        }
        return jsonResult;
    }

}
