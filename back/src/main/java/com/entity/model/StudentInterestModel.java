package com.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生兴趣分析 Model 类
 */
public class StudentInterestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long studentId;

    private String tags;

    private Integer activityCounts;

    private String categoryCounts;

    private Date lastUpdated;

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


