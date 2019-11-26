package com.home.community.controller;

import com.home.community.entity.User;
import com.home.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-23 23:40
 **/
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request){
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
        return "/index";
    }
}
