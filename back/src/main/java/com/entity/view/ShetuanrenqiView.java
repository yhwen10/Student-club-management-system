package com.entity.view;

import com.entity.ShetuanrenqiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 社团人气
 * 后端返回视图实体辅助类
 */
@TableName("club_popularity")
public class ShetuanrenqiView extends ShetuanrenqiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 社团名称（关联社团信息表的字段）
     */
    private String clubName;

    /**
     * 人气分数（成员人数 + 活动参与人数 * 权重）
     */
    private Integer popularityScore;

    /**
     * 构造方法：无参构造
     */
    public ShetuanrenqiView() {
    }

    /**
     * 构造方法：通过 `ShetuanrenqiEntity` 构造视图对象
     *
     * @param shetuanrenqiEntity 原实体对象
     */
    public ShetuanrenqiView(ShetuanrenqiEntity shetuanrenqiEntity) {
        try {
            BeanUtils.copyProperties(this, shetuanrenqiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置：社团名称
     */
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    /**
     * 获取：社团名称
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * 设置：人气分数
     */
    public void setPopularityScore(Integer popularityScore) {
        this.popularityScore = popularityScore;
    }

    /**
     * 获取：人气分数
     */
    public Integer getPopularityScore() {
        return popularityScore;
    }
}

