package org.example.demo3.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.demo3.entity.User;

/**
 * @author czz
 */
@Mapper
public interface UserMapperMysql {

    @Select("select id, age from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
