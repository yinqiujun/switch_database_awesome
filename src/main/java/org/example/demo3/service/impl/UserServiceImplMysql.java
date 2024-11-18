package org.example.demo3.service.impl;

import jakarta.annotation.Resource;
import org.example.demo3.entity.User;
import org.example.demo3.mapper.mysql.UserMapperMysql;
import org.example.demo3.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author czz
 */
@Service
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.mysql.cj.jdbc.Driver")
public class UserServiceImplMysql implements UserService {

    @Resource
    private UserMapperMysql userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
