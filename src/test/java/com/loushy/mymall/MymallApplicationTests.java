package com.loushy.mymall;

import com.loushy.mymall.action.Md5;
import com.loushy.mymall.dao.RedisDao;
import com.loushy.mymall.entity.User;
import com.loushy.mymall.service.UserService;
import com.loushy.mymall.serviceImpl.UserServiceImpl;
import com.sun.tracing.dtrace.Attributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MymallApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void insertUser(){
        UserService userService = new UserServiceImpl();
        User user =  new User();
        user.setUsername("loushaoYong");
        user.setPassword(Md5.setMd5("123456"));
        user.setAge(22);

        System.out.println(userService.insertUser(user));
    }

    @Resource
    RedisDao redisDao;
    @Test
    public void redisTest(){
        redisDao.setKey("aac","123");
        System.out.println(redisDao.getValue("aac"));
    }



}

