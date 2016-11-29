package com.bjlx.QinShihuang.requestmodel;

/**
 * Created by Nov on 2016/11/22.
 */
public class ChatGroupReq {

    /**
     * 合法的token
     */
    private String token;

    /**
     * 群名
     */
    private String name;

    /**
     * 创建者用户Id
     */
    private Long creator;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }



}
