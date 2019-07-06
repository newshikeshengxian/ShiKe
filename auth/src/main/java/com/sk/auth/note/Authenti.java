package com.sk.auth.note;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sk.auth.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Authenti {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AuthMapper authMapper;

    public String auth(String key){
        if(!redisTemplate.hasKey(key)){
            return null;
        }else {
            String id  = redisTemplate.boundValueOps(key).get();
            redisTemplate.boundValueOps(key).set(id,30, TimeUnit.MINUTES);
            return id;
        }
    }

    public boolean authAdmin(String key ,String name){
        Gson gson = new Gson();
        String id  = redisTemplate.boundValueOps(key).get();
        redisTemplate.boundValueOps(key).set(id,30, TimeUnit.MINUTES);
        if(id != null){
            String auth = redisTemplate.boundValueOps(id).get();
            redisTemplate.boundValueOps(id).set(auth,30, TimeUnit.MINUTES);
            if(auth == null){
                List<String> authList = authMapper.authById(id);
                    if(authList != null){
                        String s = gson.toJson(authList);
                        redisTemplate.boundValueOps(id).set(s);
                        }
            }else{
                List<String> authList = gson.fromJson(auth, new TypeToken<List<String>>() {}.getType());
                for (String str : authList){
                    if(str.equals(name)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
