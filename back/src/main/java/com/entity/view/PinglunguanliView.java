package com.entity.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论管理
 * 后端返回视图实体辅助类
 */
public class PinglunguanliView implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; // 主键
    private Long refid; // 社团ID
    private String nickname; // 用户名
    private String content; // 评论内容
    private String reply; // 回复内容
    private String emotion; // 情感分析结果
    private Date addtime; // 创建时间
    private String shetuanmingcheng; // 社团名称（扩展字段）

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRefid(Long refid) {
        this.refid = refid;
    }

    public Long getRefid() {
        return refid;
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

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setShetuanmingcheng(String shetuanmingcheng) {
        this.shetuanmingcheng = shetuanmingcheng;
    }

    public String getShetuanmingcheng() {
        return shetuanmingcheng;
    }
}





