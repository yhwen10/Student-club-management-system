package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 社团人气
 * 数据库通用操作实体类
 * 对应数据库表：club_popularity
 */
@TableName("club_popularity")
public class ShetuanrenqiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShetuanrenqiEntity() {
    }

    public ShetuanrenqiEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主键id（社团ID，关联社团信息表）
     */
    @TableId
    private Long clubId;

    /**
     * 成员人数
     */
    @NotNull
    private Integer memberCount;

    /**
     * 活动参与人数
     */
    @NotNull
    private Integer eventParticipation;

    /**
     * 设置：主键id（社团ID）
     */
    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    /**
     * 获取：主键id（社团ID）
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
}

