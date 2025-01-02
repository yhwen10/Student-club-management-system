package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.StudentInterestAnalysisEntity;

/**
 * 学生兴趣分析服务接口
 */
public interface StudentInterestAnalysisService extends IService<StudentInterestAnalysisEntity> {

    void generateStudentInterest(Long studentId);
}

