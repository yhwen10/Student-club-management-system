package com.entity.vo;

import java.io.Serializable;

/**
 * 社团人气
 * 手机端接口返回实体辅助类
 * 对应于社团人气的简化数据，仅保留接口需要的字段。
 */
public class ShetuanrenqiVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 社团ID
     */
    private Long clubId;

    /**
     * 成员人数
     */
    private Integer memberCount;

    /**
     * 活动参与人数
     */
    private Integer eventParticipation;

    /**
     * 总分（人气分数，成员人数 + 活动参与人数 * 权重）
     */
    private Integer totalScore;

    /**
     * 设置：社团ID
     */
    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    /**
     * 获取：社团ID
     */
    public Long getClubId() {
        return clubId;
    }

    /**
     * 设置：成员人数
     */
    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    /**
     * 获取：成员人数
     */
    public Integer getMemberCount() {
        return memberCount;
    }

    /**
     * 设置：活动参与人数
     */
    public void setEventParticipation(Integer eventParticipation) {
        this.eventParticipation = eventParticipation;
    }

    /**
     * 获取：活动参与人数
     */
    public Integer getEventParticipation() {
        return eventParticipation;
    }

    /**
     * 设置：总分（人气分数）
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取：总分（人气分数）
     */
    public Integer getTotalScore() {
        return totalScore;
    }
}
