package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.HuodongcanyuYuceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HuodongcanyuYuceDao extends BaseMapper<HuodongcanyuYuceEntity> {

    // 查询未审核的活动
    List<HuodongcanyuYuceEntity> getUnverifiedActivities();

    // 获取指定社团的成员人数和历史活动参与人数（用于预测）
    List<HuodongcanyuYuceEntity> getActivityStatsByClub(String shetuanmingcheng);
}




