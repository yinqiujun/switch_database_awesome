package org.example.demo3.service.impl;

import jakarta.annotation.Resource;
import org.example.demo3.entity.User;
import org.example.demo3.mapper.sqlserver.UserMapperSqlServer;
import org.example.demo3.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.microsoft.sqlserver.jdbc.SQLServerDriver")
public class UserServiceImplSqlserver implements UserService {

    @Resource
    private UserMapperSqlServer userMapperSqlServer;

    @Override
    public User getUserById(Integer id) {
        return userMapperSqlServer.getUserById(id);
    }
}
