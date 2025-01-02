package com.entity.view;

import com.entity.JinqihuodongpaimingEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 近期活动排名
 * 后端返回视图实体辅助类
 */
public class JinqihuodongpaimingView extends JinqihuodongpaimingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public JinqihuodongpaimingView() {}

    public JinqihuodongpaimingView(JinqihuodongpaimingEntity entity) {
        try {
            BeanUtils.copyProperties(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
