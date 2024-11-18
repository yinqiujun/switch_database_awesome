package org.example.demo2.service.impl;

import jakarta.annotation.Resource;
import org.example.demo2.entity.User;
import org.example.demo2.mapper.UserMapper;
import org.example.demo2.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author czz
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
