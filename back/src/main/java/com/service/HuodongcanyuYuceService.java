package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.HuodongcanyuYuceEntity;

import java.util.List;

public interface HuodongcanyuYuceService extends IService<HuodongcanyuYuceEntity> {
    // 获取未审核活动
    List<HuodongcanyuYuceEntity> getUnverifiedActivities();

    // 根据社团名称预测活动参与人数
    Integer predictParticipants(HuodongcanyuYuceEntity activity);
}





