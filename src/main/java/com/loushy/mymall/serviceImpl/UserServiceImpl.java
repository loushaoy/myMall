package com.loushy.mymall.serviceImpl;

import com.loushy.mymall.dao.UserMapper;
import com.loushy.mymall.entity.User;
import com.loushy.mymall.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public int insertUser(User user){
        int index =0;
        if (user!=null){
            return userMapper.insert(user);
        }
        return index;

    }
}
