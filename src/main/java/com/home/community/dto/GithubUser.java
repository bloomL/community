package com.home.community.dto;

/**
 * @Description TODO
 * @Author Lg
 * @Date 2019-11-25 0:07
 **/
public class GithubUser {
    private long id;
    private String name;
    //描述
    private String bio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
