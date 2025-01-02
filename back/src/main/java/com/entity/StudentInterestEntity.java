package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生兴趣分析实体类
 */
@TableName("student_interest")
public class StudentInterestEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long studentId; // 学生ID

    private String tags; // 兴趣标签

    private Integer activityCounts; // 活动总参与数

    private String categoryCounts; // 各分类活动参与数（JSON格式）

    private Date lastUpdated; // 最后更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getActivityCounts() {
        return activityCounts;
    }

    public void setActivityCounts(Integer activityCounts) {
        this.activityCounts = activityCounts;
    }

    public String getCategoryCounts() {
        return categoryCounts;
    }

    public void setCategoryCounts(String categoryCounts) {
        this.categoryCounts = categoryCounts;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

