package com.home.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-27 23:34
 **/
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages;

    public void getPageDTO(Integer count, Integer page, Integer size) {
        //总页数
        int countPage = count%size == 0 ? count/size : count/size + 1;

    }
}
