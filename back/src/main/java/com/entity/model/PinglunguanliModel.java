package com.entity.model;

import java.io.Serializable;

/**
 * 评论管理
 */
public class PinglunguanliModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long refid; // 社团ID
    private Long userid; // 用户ID
    private String nickname; // 用户名
    private String content; // 评论内容

    public void setRefid(Long refid) {
        this.refid = refid;
    }

    public Long getRefid() {
        return refid;
    }

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
}





