package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.StudentInterestEntity;

/**
 * 学生兴趣分析服务接口
 */
public interface StudentInterestService extends IService<StudentInterestEntity> {

    void generateInterestTags(Long studentId);
}



