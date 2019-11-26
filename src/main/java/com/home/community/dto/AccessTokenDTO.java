package com.home.community.dto;

import lombok.Data;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-24 22:59
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
