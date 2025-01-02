package com.entity.model;

import java.io.Serializable;

/**
 * 活动参与预测 Model 类
 */
public class HuodongcanyuYuceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String biaoti; // 活动标题
    private String shetuanmingcheng; // 社团名称
    private Integer huodongrenshu; // 活动人数

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


