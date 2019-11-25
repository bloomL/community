package com.home.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 23:26
 **/
@Controller
public class PublishController {

    @GetMapping("publish")
    public String publish(){
        return "publish";
    }
}
