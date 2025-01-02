package com.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.StudentInterestAnalysisDao;
import com.entity.StudentInterestAnalysisEntity;
import com.service.StudentInterestAnalysisService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生兴趣分析服务实现类
 */
@Service("studentInterestAnalysisService")
public class StudentInterestAnalysisServiceImpl extends ServiceImpl<StudentInterestAnalysisDao, StudentInterestAnalysisEntity> implements StudentInterestAnalysisService {

    @Override
    public void generateStudentInterest(Long studentId) {
        // 模拟逻辑：统计学生参与的社团分类和活动
        Map<String, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put("体育", 5);
        categoryCounts.put("艺术", 3);

        // 生成兴趣标签
        String interestTags = "体育,艺术";

        // 活动总参与数
        int totalActivities = 8;

        // 构造兴趣分析实体
        StudentInterestAnalysisEntity analysisEntity = new StudentInterestAnalysisEntity();
        analysisEntity.setStudentId(studentId);
        analysisEntity.setInterestTags(interestTags);
        analysisEntity.setActivityCount(totalActivities);

        // 使用Jackson将categoryCounts转换为JSON字符串
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String categoryDetailsJson = objectMapper.writeValueAsString(categoryCounts);
            analysisEntity.setCategoryDetails(categoryDetailsJson);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            throw new RuntimeException("无法将categoryCounts转换为JSON字符串", e);
        }

        // 保存或更新数据
        this.insertOrUpdate(analysisEntity);
    }
}
