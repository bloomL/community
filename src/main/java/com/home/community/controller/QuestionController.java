package com.home.community.controller;

import com.home.community.dto.QuestionDTO;
import com.home.community.mapper.QuestionMapper;
import com.home.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-12-02 21:56
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        //增加浏览量  考虑多线程的问题
        questionService.incrementView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
