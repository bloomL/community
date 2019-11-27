package com.home.community.mapper;

import com.home.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void create(Question question);

    @Select("select * from question limit ${offset},${size}")
    List<Question> searchAll(Integer offset, Integer size);

    @Select("select count(*) from question")
    Integer getCount();
}
