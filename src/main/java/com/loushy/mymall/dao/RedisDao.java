package com.loushy.mymall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;


    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
    }

    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     */
    public void setKey(String key ,Long time){
        template.expire(key,time, TimeUnit.MINUTES);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

    public void deleteValue(String key){
         template.delete(key);
    }
}
