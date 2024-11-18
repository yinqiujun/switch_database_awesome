package org.example.demo4.config;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.mysql.cj.jdbc.Driver")
@MapperScan(basePackages = "org.example.demo4.mapper.mysql")
public class MysqlMyBatisConfig {
    // 配置 SQL Server 的 SqlSessionFactory
    @PostConstruct
    public void init() {
        System.out.println("SqlServer MyBatis 配置加载成功");
    }
}