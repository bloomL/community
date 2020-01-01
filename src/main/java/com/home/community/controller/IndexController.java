package com.home.community.controller;

import com.home.community.dto.PageDTO;
import com.home.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


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
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PageDTO pageDTO = questionService.list(search, tag, sort, page, size);
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("sort", sort);
        return "index";
    }
}
