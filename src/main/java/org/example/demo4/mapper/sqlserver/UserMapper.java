package org.example.demo4.mapper.sqlserver;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.demo4.entity.User;
import org.example.demo4.mapper.base.UserBaseMapper;

/**
 * @author czz
 */
@Mapper
public interface UserMapper extends UserBaseMapper {

    @Select("select id, name from TB_USER where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
