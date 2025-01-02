package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 近期活动排名
 * 数据库通用操作实体类
 */
@TableName("shetuanhuodong")
public class JinqihuodongpaimingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id; // 主键

    private String biaoti; // 标题
    private String shetuanmingcheng; // 社团名称
    private String huodongtupian; // 活动图片
    private Date kaishishijian; // 开始时间
    private Date jieshushijian; // 结束时间
    private Integer huodongrenshu; // 活动人数
    private String huodongdidian; // 活动地点
    private String huodongxiangqing; // 活动详情

    // Getters and Setters
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
    public String getHuodongtupian() {
        return huodongtupian;
    }
    public void setHuodongtupian(String huodongtupian) {
        this.huodongtupian = huodongtupian;
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
    public String getHuodongxiangqing() {
        return huodongxiangqing;
    }
    public void setHuodongxiangqing(String huodongxiangqing) {
        this.huodongxiangqing = huodongxiangqing;
    }
}

