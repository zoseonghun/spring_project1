package com.canesblack.spring_project1.service;

import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 의존성 주입
    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public String findWriter(String username) {
        return userMapper.findWriter(username);
    }


}
