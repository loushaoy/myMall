package com.loushy.mymall;

import com.loushy.mymall.action.Md5;
import com.loushy.mymall.entity.User;
import com.loushy.mymall.service.UserService;
import com.loushy.mymall.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MymallApplicationTests {

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

}

