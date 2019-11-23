package com.home.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-23 23:40
 **/
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }
}
