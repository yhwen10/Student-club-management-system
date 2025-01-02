package com.entity.view;

import com.entity.ShetuanhuodongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 社团活动
 * 后端返回视图实体辅助类
 */
@TableName("shetuanhuodong")
public class ShetuanhuodongView  extends ShetuanhuodongEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShetuanhuodongView(){
	}
 
 	public ShetuanhuodongView(ShetuanhuodongEntity shetuanhuodongEntity){
 	try {
			BeanUtils.copyProperties(this, shetuanhuodongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
