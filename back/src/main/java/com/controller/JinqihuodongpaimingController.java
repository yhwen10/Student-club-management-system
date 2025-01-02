package com.controller;

import com.entity.JinqihuodongpaimingEntity;
import com.service.JinqihuodongpaimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 近期活动排名
 * 控制器
 */
@RestController
@RequestMapping("/jinqihuodongpaiming")
public class JinqihuodongpaimingController {

    @Autowired
    private JinqihuodongpaimingService jinqihuodongpaimingService;

    /**
     * 获取近期活动排名（按活动人数排序）
     */
    @GetMapping("/list")
    public List<JinqihuodongpaimingEntity> getRankingList() {
        return jinqihuodongpaimingService
                .selectList(null)
                .stream()
                .sorted((a, b) -> b.getHuodongrenshu().compareTo(a.getHuodongrenshu())) // 按活动人数倒序
                .collect(Collectors.toList()); // 替换 toList() 为 collect(Collectors.toList())
    }
}




