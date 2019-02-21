package com.loushy.mymall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

    @Resource
    private StringRedisTemplate template;


    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
    }

    /**
     * redis list
     */

    public void setKey(String key , List<String> params){
        template.multi();
        ListOperations<String,String> ops = template.opsForList();
        ops.leftPushAll(key,params);
//        template.exec();  //redis 事物提交
//        template.discard(); //redis 事物回滚
    }

    /**
     * redis map
     */

    public void setKey(String key, Map<String,String> map){
        HashOperations<String,String,String> hps = template.opsForHash();
        hps.putAll(key,map);
    }

    /**
     * redis set
     */

    public void setKye(String key , Set<String> set){
        SetOperations<String,String> sos = template.opsForSet();
        sos.intersect(key,set);
    }

    /**
     * redis Zset
     */



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

    public List getListValue(String key){
        ListOperations<String,String> lop = template.opsForList();

        return lop.range(key,0,-1);
    }

    public void deleteValue(String key){
         template.delete(key);
    }
}
