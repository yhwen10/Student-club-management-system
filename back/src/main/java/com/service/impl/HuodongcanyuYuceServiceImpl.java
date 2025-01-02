package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.HuodongcanyuYuceDao;
import com.entity.HuodongcanyuYuceEntity;
import com.service.HuodongcanyuYuceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("huodongcanyuYuceService")
public class HuodongcanyuYuceServiceImpl extends ServiceImpl<HuodongcanyuYuceDao, HuodongcanyuYuceEntity> implements HuodongcanyuYuceService {

    @Override
    public List<HuodongcanyuYuceEntity> getUnverifiedActivities() {
        return baseMapper.getUnverifiedActivities();
    }

    @Override
    public Integer predictParticipants(HuodongcanyuYuceEntity activity) {
        // 获取社团名称
        String clubName = activity.getShetuanmingcheng();
        List<HuodongcanyuYuceEntity> stats = baseMapper.getActivityStatsByClub(clubName);

        // 假设的统计模型，根据社团人气和历史参与人数预测
        int totalParticipation = 0;
        int activityCount = stats.size();
        for (HuodongcanyuYuceEntity stat : stats) {
            totalParticipation += stat.getHuodongrenshu();
        }

        int averageParticipation = activityCount > 0 ? totalParticipation / activityCount : 0;

        // 预测结果：结合历史数据进行简单预测
        return (int) (averageParticipation * 1.2); // 假设参与人数增长 20%
    }
}






