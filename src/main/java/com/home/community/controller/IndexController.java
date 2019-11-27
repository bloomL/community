package com.home.community.controller;

import com.home.community.dto.PageDTO;
import com.home.community.dto.QuestionDTO;
import com.home.community.entity.Question;
import com.home.community.entity.User;
import com.home.community.mapper.QuestionMapper;
import com.home.community.mapper.UserMapper;
import com.home.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-23 23:40
 **/
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request ,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        //解决服务重启用户登录失效
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }
        PageDTO pageDTO = questionService.searchAll(page,size);
        model.addAttribute("pageDTO",pageDTO);
        return "/index";
    }
}
