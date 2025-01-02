package com.entity.view;

import com.entity.ShezhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 社长
 * 后端返回视图实体辅助类
 */
@TableName("shezhang")
public class ShezhangView  extends ShezhangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShezhangView(){
	}
 
 	public ShezhangView(ShezhangEntity shezhangEntity){
 	try {
			BeanUtils.copyProperties(this, shezhangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
