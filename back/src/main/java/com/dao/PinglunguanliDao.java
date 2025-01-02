package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.mapper.Wrapper; // 注意这个导入路径
import com.entity.PinglunguanliEntity;
import com.entity.vo.PinglunguanliVO;
import com.entity.view.PinglunguanliView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PinglunguanliDao extends BaseMapper<PinglunguanliEntity> {

    List<PinglunguanliVO> selectListVO(@Param("ew") Wrapper<PinglunguanliEntity> wrapper);

    PinglunguanliVO selectVO(@Param("ew") Wrapper<PinglunguanliEntity> wrapper);

    List<PinglunguanliView> selectListView(Pagination page, @Param("ew") Wrapper<PinglunguanliEntity> wrapper);

    PinglunguanliView selectView(@Param("ew") Wrapper<PinglunguanliEntity> wrapper);
}




