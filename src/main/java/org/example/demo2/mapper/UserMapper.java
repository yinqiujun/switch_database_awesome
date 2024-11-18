package org.example.demo2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.demo2.entity.User;

/**
 * @author czz
 */
@Mapper
public interface UserMapper {

    // @Select("select * from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
