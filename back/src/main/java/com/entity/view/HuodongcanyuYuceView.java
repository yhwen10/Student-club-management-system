package com.entity.view;

import com.entity.HuodongcanyuYuceEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 活动参与预测视图实体类
 */
public class HuodongcanyuYuceView extends HuodongcanyuYuceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public HuodongcanyuYuceView() {
    }

    public HuodongcanyuYuceView(HuodongcanyuYuceEntity entity) {
        try {
            BeanUtils.copyProperties(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}



