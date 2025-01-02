package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.StudentInterestDao;
import com.entity.StudentInterestEntity;
import com.service.StudentInterestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生兴趣分析服务实现类
 */
@Service("studentInterestService")
public class StudentInterestServiceImpl extends ServiceImpl<StudentInterestDao, StudentInterestEntity> implements StudentInterestService {

    @Override
    public void generateInterestTags(Long studentId) {
        // 模拟逻辑：从学生参与的社团和活动数据中生成标签和计数
        Map<String, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put("体育", 5);
        categoryCounts.put("艺术", 3);

        // 生成标签
        String tags = "体育, 艺术";

        // 总活动数
        int totalActivities = 8;

        // 构造实体
        StudentInterestEntity interestEntity = new StudentInterestEntity();
        interestEntity.setStudentId(studentId);
        interestEntity.setTags(tags);
        interestEntity.setActivityCounts(totalActivities);
        interestEntity.setCategoryCounts(categoryCounts.toString());

        // 插入或更新
        this.insertOrUpdate(interestEntity);
    }
}


