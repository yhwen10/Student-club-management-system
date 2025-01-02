package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper; // 注意导入路径
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.PinglunguanliDao;
import com.entity.PinglunguanliEntity;
import com.service.PinglunguanliService;
import com.entity.vo.PinglunguanliVO;
import com.entity.view.PinglunguanliView;

@Service("pinglunguanliService")
public class PinglunguanliServiceImpl extends ServiceImpl<PinglunguanliDao, PinglunguanliEntity> implements PinglunguanliService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PinglunguanliEntity> page = this.selectPage(
                new Query<PinglunguanliEntity>(params).getPage(),
                new com.baomidou.mybatisplus.mapper.EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<PinglunguanliEntity> wrapper) {
        Page<PinglunguanliView> page = new Query<PinglunguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<PinglunguanliVO> selectListVO(Wrapper<PinglunguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public PinglunguanliVO selectVO(Wrapper<PinglunguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<PinglunguanliView> selectListView(Wrapper<PinglunguanliEntity> wrapper) {
        return baseMapper.selectListView(null, wrapper);
    }

    @Override
    public PinglunguanliView selectView(Wrapper<PinglunguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }
}











