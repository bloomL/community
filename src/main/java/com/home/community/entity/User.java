package com.home.community.entity;

import lombok.Data;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 22:02
 **/
@Data
public class User {
    private Integer id;
    private String name;
    //账户id
    private String accountId;
    private String token;
    private Long gmtCreat;
    private Long gmtModified;
    private String avatarUrl;
}
