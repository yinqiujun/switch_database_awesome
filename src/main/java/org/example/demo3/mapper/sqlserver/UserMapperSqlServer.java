package org.example.demo3.mapper.sqlserver;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.demo3.entity.User;

/**
 * @author czz
 */
@Mapper
public interface UserMapperSqlServer {

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
