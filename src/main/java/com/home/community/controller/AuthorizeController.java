package com.home.community.controller;

import com.home.community.dto.AccessTokenDTO;
import com.home.community.dto.GithubUser;
import com.home.community.entity.User;
import com.home.community.mapper.UserMapper;
import com.home.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description 授权
 * @Author Lg
 * @Date 2019-11-24 13:03
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
            //写cookie,session
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //重新登录
            return "redirect:/";
        }
    }
}
