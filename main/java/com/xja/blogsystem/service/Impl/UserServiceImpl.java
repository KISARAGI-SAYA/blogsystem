package com.xja.blogsystem.service.impl;

import com.xja.blogsystem.mapper.UserMapper;
import com.xja.blogsystem.pojo.User;
import com.xja.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByAccountAndPassword(String account, String password) {
        return userMapper.queryUserByAccountAndPassword(account, password);
    }

    @Override
    public User queryUserByAccount(String account) {
        return userMapper.queryUserByAccount(account);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }
}