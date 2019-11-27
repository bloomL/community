package com.home.community.mapper;

import com.home.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 22:04
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_creat,gmt_modified,avatar_rl) values(#{name},#{accountId,#{token},#{gmtCreat},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
