package com.loushy.mymall.controller;

import com.loushy.mymall.action.Md5;
import com.loushy.mymall.dao.RedisDao;
import com.loushy.mymall.entity.User;
import com.loushy.mymall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userServiceImpl;

    @Resource
    public RedisDao redisDao;

    @RequestMapping("/do")
    public void listUser(){
        User user =  new User();
        user.setUsername("loushaoYong");
        user.setPassword(Md5.setMd5("123456"));
        user.setAge(22);
        System.out.println(userServiceImpl.insertUser(user));
    }
}
