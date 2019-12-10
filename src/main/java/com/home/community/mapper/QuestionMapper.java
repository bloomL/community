package com.home.community.mapper;

import com.home.community.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void create(Question question);

    @Select("select * from question limit ${offset},${size}")
    List<Question> searchAll(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(*) from question")
    Integer getCount();

    @Select("select * from question where creator=#{userId}  limit ${offset},${size}")
    List<Question> searchByUserId(@Param("userId")Integer userId, @Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(*) from question where creator=#{userId} ")
    Integer getCountById(@Param("userId")Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},creator=#{creator},tag=#{tag} where id=#{id}")
    void update(Question question);

    @Update("update question set view_count=#{viewCount} where id=#{id}")
    void updateViewCount(Question question);
}
