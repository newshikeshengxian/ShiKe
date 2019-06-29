package com.sk.auth.note;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class Note {

    public void note(){
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIVzWPsZHhmUTm", "fcfDLta5wV9U03Y5gEObOesCYvnsEQ");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "13902478987");
        request.putQueryParameter("SignName", "shike666");
        request.putQueryParameter("TemplateCode", "SMS_169103927");
        request.putQueryParameter("TemplateParam", "{\"code\":\"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Note note = new Note();
        note.note();
    }
}
