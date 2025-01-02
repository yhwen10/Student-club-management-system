package com.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 近期活动排名
 */
public class JinqihuodongpaimingModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String biaoti; // 标题
    private String shetuanmingcheng; // 社团名称
    private Integer huodongrenshu; // 活动人数

    // Getters and Setters
    public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }
    public String getShetuanmingcheng() {
        return shetuanmingcheng;
    }
    public void setShetuanmingcheng(String shetuanmingcheng) {
        this.shetuanmingcheng = shetuanmingcheng;
    }
    public Integer getHuodongrenshu() {
        return huodongrenshu;
    }
    public void setHuodongrenshu(Integer huodongrenshu) {
        this.huodongrenshu = huodongrenshu;
    }
}

