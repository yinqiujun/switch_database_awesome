package org.example.demo4.service.impl;

import jakarta.annotation.Resource;
import org.example.demo4.entity.User;
import org.example.demo4.mapper.base.UserBaseMapper;
import org.example.demo4.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author czz
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserBaseMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
