package com.home.community.dto;

import lombok.Data;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 0:07
 **/
@Data
public class GithubUser {
    private long id;
    private String name;
    //描述
    private String bio;
    private String avatarUrl;
}
