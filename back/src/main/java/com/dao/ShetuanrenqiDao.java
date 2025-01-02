package com.dao;

import com.entity.ShetuanrenqiEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShetuanrenqiDao {

    List<ShetuanrenqiEntity> getAllRenqiData(); // 查询所有社团人气数据
}


