package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动参与预测实体类
 */
@TableName("shetuanhuodong")
public class HuodongcanyuYuceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id; // 主键ID

    private String biaoti; // 活动标题
    private String shetuanmingcheng; // 社团名称
    private Date kaishishijian; // 开始时间
    private Date jieshushijian; // 结束时间
    private Integer huodongrenshu; // 活动人数
    private String huodongdidian; // 活动地点
    private String sfsh; // 是否审核（默认：否）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getKaishishijian() {
        return kaishishijian;
    }

    public void setKaishishijian(Date kaishishijian) {
        this.kaishishijian = kaishishijian;
    }

    public Date getJieshushijian() {
        return jieshushijian;
    }

    public void setJieshushijian(Date jieshushijian) {
        this.jieshushijian = jieshushijian;
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

    public String getSfsh() {
        return sfsh;
    }

    public void setSfsh(String sfsh) {
        this.sfsh = sfsh;
    }
}



