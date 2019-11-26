package com.home.community.controller;

import com.home.community.entity.Question;
import com.home.community.entity.User;
import com.home.community.mapper.QuestionMapper;
import com.home.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 23:26
 **/
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String postPublish(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("tag") String tag,
                              HttpServletRequest request,
                              Model model){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if (user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "publish";
    }
}
