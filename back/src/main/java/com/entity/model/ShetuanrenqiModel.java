package com.entity.model;

import java.io.Serializable;

/**
 * 社团人气
 */
public class ShetuanrenqiModel implements Serializable {
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
     * 权重（用于计算人气分数时的活动参与人数权重）
     */
    private Integer weight;

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
     * 设置：权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取：权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 计算总人气分数（成员人数 + 活动参与人数 * 权重）
     */
    public Integer calculatePopularityScore() {
        if (weight == null) {
            weight = 2; // 默认权重
        }
        return memberCount + eventParticipation * weight;
    }
}

