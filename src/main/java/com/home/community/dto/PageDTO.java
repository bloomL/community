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
    private Integer totalPage;
    public void setPageDTO(Integer count, Integer page, Integer size) {
        this.currentPage = page;
        //总页数
        int countPage = count%size == 0 ? count/size : count/size + 1;

        if (page < 1){
            page = 1;
        }
        if (page >countPage){
            page = countPage;
        }
        this.totalPage = countPage;
        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if (page - i > 0){
                pages.add(0,page - i);
            }
            if (page + i <= countPage){
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == countPage){
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(countPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
