package com.home.community.controller;

import com.home.community.dto.PageDTO;
import com.home.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-23 23:40
 **/
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        PageDTO pageDTO = questionService.searchAll(page,size);
        model.addAttribute("pageDTO",pageDTO);
        return "/index";
    }
}
