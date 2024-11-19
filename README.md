# ⚔️如何优雅的切换数据库

### 1️⃣service层添加if判断

在定义mapper时，针对不同类型的数据库定义不同的sql语句

```java
@Mapper
public interface UserMapper {

    @Select("select id, age from TB_USER where id = #{id}")
    User getUserByIdMysql(@Param("id") Integer id);

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserByIdSqlServer(@Param("id") Integer id);
}
```

在service层根据环境变量，判断使用那个方法

```java
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
```

这种方式缺点非常明显，数据库和业务紧密的耦合在了一起，如果以后需要支持更多的数据库类型，将会使代码难以维护



### 2️⃣DatabaseIdProvider & databaseId

如果项目使用的是xml进行sql语句管理，可以使用mybatis原生提供的多数据库支持

配置DatabaseIdProvider，区分不同的数据库

```java
@Configuration
public class MyBatisConfig {
    /**
     * 配置 databaseIdProvider
     */
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        DatabaseIdProvider databaseIdProvider =  new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("SQL Server", "sqlserver");
        properties.setProperty("MySQL", "mysql");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }
}
```

```java
@Mapper
public interface UserMapper {
    User getUserById(@Param("id") Integer id);
}
```

在xml中使用databaseId

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.example.demo2.mapper.UserMapper">

    <select id="getUserById" parameterType="java.lang.Integer" resultType="org.example.demo2.entity.User"
            databaseId="mysql">
        select id, age from TB_USER where id = #{id}
    </select>

    <select id="getUserById" parameterType="java.lang.Integer" resultType="org.example.demo2.entity.User"
            databaseId="sqlserver">
        select id, name from TB_USER where id = #{id}
    </select>


</mapper>
```

这种方式非常的简洁，一个方法就可以实现不同的数据库切换

缺点是只有xml的方式才支持databaseId的配置



### 3️⃣不同的实现类

对于第一种方式，当然也有解耦的办法：使用不同的service实现类，然后根据条件加载不同的bean，从而注入不同数据库需要的bean

```java
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
```

```java 
@Mapper
public interface UserMapperSqlServer {

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
```



```java
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
```

```java
@Mapper
public interface UserMapperMysql {

    @Select("select id, age from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
```

这种方式虽然实现了解耦，但是我们会发现其实不同的实现类，业务逻辑是完全一样的，不一样的只是mapper的类型，如果在service使用不同的实现，会导致过多的重复代码，于是有了第四种方式



### 4️⃣根据条件扫描不同的包

这种方式使用的是同一个service的实现类，不同的是控制不同的mapper扫描最终注入不同的mapper实现，本质和第三种方式是一样的

根据使用的数据库不同，配置不同的包扫描

```java
@Configuration
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.mysql.cj.jdbc.Driver")
@MapperScan(basePackages = "org.example.demo4.mapper.mysql")
public class MysqlMyBatisConfig {
    // 配置 SQL Server 的 SqlSessionFactory
    @PostConstruct
    public void init() {
        System.out.println("mysql MyBatis 配置加载成功");
    }
}
```

```java
@Configuration
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.microsoft.sqlserver.jdbc.SQLServerDriver")
@MapperScan(basePackages = "org.example.demo4.mapper.sqlserver")
public class SqlServerMyBatisConfig {
    // 配置 SQL Server 的 SqlSessionFactory
    @PostConstruct
    public void init() {
        System.out.println("SqlServer MyBatis 配置加载成功");
    }
}
```

定义一个通用的mapper接口在service的实现类中注入

```java
public interface UserBaseMapper {

    User getUserById(Integer id);
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserBaseMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}

```

不同的mapper接口继承这个通用的mapper接口

```java
@Mapper
public interface UserMapper extends UserBaseMapper {

    @Select("select id, age from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
```

```java
@Mapper
public interface UserMapper extends UserBaseMapper {

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
```

这样就可以实现在打包后直接使用参数的方式在启动服务的时候动态的指定数据库类型了。



这里个人推荐第二种方式，使用一个方法和xml的方式就无需加载多余的字节码，可以让打包结果体积更小