package com.home.community.service;

import com.home.community.dto.PageDTO;
import com.home.community.dto.QuestionDTO;
import com.home.community.entity.Question;
import com.home.community.entity.User;
import com.home.community.mapper.QuestionMapper;
import com.home.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-27 21:57
 **/
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PageDTO searchAll(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.searchAll(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>(questionList.size());
        PageDTO pageDTO = new PageDTO();
        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        //问题总数
        Integer count = questionMapper.getCount();
        pageDTO.getPageDTO(count,page,size);
        return pageDTO;
    }
}
