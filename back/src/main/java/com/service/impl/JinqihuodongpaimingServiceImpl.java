package com.service.impl;

import com.dao.JinqihuodongpaimingDao;
import com.entity.JinqihuodongpaimingEntity;
import com.service.JinqihuodongpaimingService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 近期活动排名
 * 服务层实现类
 */
@Service("jinqihuodongpaimingService")
public class JinqihuodongpaimingServiceImpl extends ServiceImpl<JinqihuodongpaimingDao, JinqihuodongpaimingEntity>
        implements JinqihuodongpaimingService {}
