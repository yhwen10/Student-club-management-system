package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.mapper.Wrapper; // 注意导入路径
import com.utils.PageUtils;
import com.entity.PinglunguanliEntity;
import com.entity.vo.PinglunguanliVO;
import com.entity.view.PinglunguanliView;

import java.util.List;
import java.util.Map;

public interface PinglunguanliService extends IService<PinglunguanliEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Wrapper<PinglunguanliEntity> wrapper);

    List<PinglunguanliVO> selectListVO(Wrapper<PinglunguanliEntity> wrapper);

    PinglunguanliVO selectVO(Wrapper<PinglunguanliEntity> wrapper);

    List<PinglunguanliView> selectListView(Wrapper<PinglunguanliEntity> wrapper);

    PinglunguanliView selectView(Wrapper<PinglunguanliEntity> wrapper);
}










