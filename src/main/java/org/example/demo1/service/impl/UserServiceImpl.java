package org.example.demo1.service.impl;

import jakarta.annotation.Resource;
import org.example.demo1.entity.User;
import org.example.demo1.mapper.UserMapper;
import org.example.demo1.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author czz
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private Environment env;

    @Override
    public User getUserById(Integer id) {
        String property = env.getProperty("spring.datasource.driver-class-name");
        if (Objects.equals(property, "com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
            return userMapper.getUserByIdSqlServer(id);
        } else if (Objects.equals(property, "com.mysql.cj.jdbc.Driver")) {
            return userMapper.getUserByIdMysql(id);
        }
        return null;
    }
}
