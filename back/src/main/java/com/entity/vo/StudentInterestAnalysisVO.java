package com.entity.vo;

import java.io.Serializable;

/**
 * 学生兴趣分析辅助 VO
 */
public class StudentInterestAnalysisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String interestTags;

    private Integer activityCount;

    private String categoryDetails;

    // Getters and Setters
    public String getInterestTags() {
        return interestTags;
    }

    public void setInterestTags(String interestTags) {
        this.interestTags = interestTags;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }
}
