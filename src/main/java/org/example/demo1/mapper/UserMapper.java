package org.example.demo1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.demo1.entity.User;

/**
 * @author czz
 */
@Mapper
public interface UserMapper {

    @Select("select id, age from TB_USER where id = #{id}")
    User getUserByIdMysql(@Param("id") Integer id);

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserByIdSqlServer(@Param("id") Integer id);
}
