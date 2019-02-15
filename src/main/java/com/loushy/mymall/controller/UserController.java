package com.loushy.mymall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loushy.mymall.action.Md5;
import com.loushy.mymall.dao.RedisDao;
import com.loushy.mymall.entity.User;
import com.loushy.mymall.service.UserService;
import com.loushy.mymall.serviceImpl.HttpClient;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userServiceImpl;

    @Resource
    public RedisDao redisDao;

    @Resource
    public HttpClient httpClient;

    @RequestMapping("/do")
    public void listUser(){
        User user =  new User();
        user.setUsername("loushaoYong");
        user.setPassword(Md5.setMd5("123456"));
        user.setAge(22);
        System.out.println(userServiceImpl.insertUser(user));
    }

    @RequestMapping("/getJson")
    @ResponseBody
    public String userJson(){
        User user =  new User();
        user.setUsername("loushaoYong");
        user.setPassword(Md5.setMd5("123456"));
        user.setAge(22);
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        JSON json = new JSONObject(map);
        System.out.println("接口被调用了");
        return json.toJSONString();
    }

    @RequestMapping("/httpClient")
    public void httpClient(){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        String json = httpClient.clientGetJson("http://localhost:8080/user/getJson",HttpMethod.POST,params);//HttpMethod.POST设置请求方式
        System.out.println(json);
    }



}
