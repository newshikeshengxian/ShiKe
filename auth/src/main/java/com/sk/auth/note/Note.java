package com.sk.auth.note;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sk.auth.mapper.UserMapper;
import com.sk.auth.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 发送手机验证码
 */
@Component
public class Note {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    public String note(String phone){
        User user = userMapper.selectUser(phone);
//        if(user == null){
            DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIVzWPsZHhmUTm", "fcfDLta5wV9U03Y5gEObOesCYvnsEQ");
            IAcsClient client = new DefaultAcsClient(profile);
            int i =  (int) (Math.random() * 9 + 1) * 1000;
            String code = String.valueOf(i);
            redisTemplate.boundValueOps(phone).set(code,120, TimeUnit.SECONDS);
            String str = "{\"code\":\""+code+"\"}";
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", "shike666");
            request.putQueryParameter("TemplateCode", "SMS_169103927");
            request.putQueryParameter("TemplateParam", str);
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
//            }
        }
        return code;
    }



}
