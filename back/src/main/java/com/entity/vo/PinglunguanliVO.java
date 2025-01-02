package com.entity.vo;

import java.io.Serializable;

/**
 * 评论管理
 * 手机端接口返回实体辅助类
 */
public class PinglunguanliVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userid; // 用户ID
    private String nickname; // 用户名
    private String content; // 评论内容
    private String reply; // 回复内容
    private String emotion; // 情感分析结果

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getEmotion() {
        return emotion;
    }
}






