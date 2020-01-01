package com.home.community.dto;

import com.home.community.entity.User;
import lombok.Data;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-26 21:32
 **/
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
