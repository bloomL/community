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
        PageDTO pageDTO = new PageDTO();
        //问题总数
        Integer count = questionMapper.getCount();
        pageDTO.setPageDTO(count,page,size);
        if (page < 1){
            page = 1;
        }

        if (page > pageDTO.getTotalPage()){
            page = pageDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.searchAll(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>(questionList.size());

        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;
    }

    public PageDTO searchByUserId(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        //问题总数
        Integer count = questionMapper.getCountById(userId);
        pageDTO.setPageDTO(count,page,size);
        if (page < 1){
            page = 1;
        }

        if (page > pageDTO.getTotalPage()){
            page = pageDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.searchByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>(questionList.size());

        for (Question question:questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findById(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrModify(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    public void incrementView(Integer id) {
        Question question = questionMapper.getById(id);
        question.setViewCount(question.getViewCount() + 1);
        questionMapper.updateViewCount(question);
    }
}
