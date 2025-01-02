package com.controller;

import com.entity.StudentInterestAnalysisEntity;
import com.service.StudentInterestAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学生兴趣分析控制器
 */
@RestController
@RequestMapping("/api/student-interest")
public class StudentInterestAnalysisController {

    @Autowired
    private StudentInterestAnalysisService studentInterestAnalysisService;

    /**
     * 获取学生兴趣分析数据
     */
    @GetMapping("/{studentId}")
    public StudentInterestAnalysisEntity getStudentInterest(@PathVariable Long studentId) {
        System.out.println("Request received for studentId: " + studentId);
        StudentInterestAnalysisEntity result = studentInterestAnalysisService.selectOne(
                new com.baomidou.mybatisplus.mapper.EntityWrapper<StudentInterestAnalysisEntity>()
                        .eq("student_id", studentId));
        System.out.println("Query result: " + result);
        return result;
    }


    /**
     * 生成学生兴趣分析数据
     */
    @PostMapping("/generate/{studentId}")
    public void generateStudentInterest(@PathVariable Long studentId) {
        studentInterestAnalysisService.generateStudentInterest(studentId);
    }
}

