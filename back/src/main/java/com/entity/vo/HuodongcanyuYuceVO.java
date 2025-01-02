package com.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动参与预测 VO 类
 */
public class HuodongcanyuYuceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String biaoti; // 活动标题
    private String shetuanmingcheng; // 社团名称
    private Integer huodongrenshu; // 活动人数
    private String huodongdidian; // 活动地点

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

    public String getHuodongdidian() {
        return huodongdidian;
    }

    public void setHuodongdidian(String huodongdidian) {
        this.huodongdidian = huodongdidian;
    }
}



