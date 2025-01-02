package com.entity.view;

import com.entity.ShetuanfenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 社团分类
 * 后端返回视图实体辅助类
 */
@TableName("shetuanfenlei")
public class ShetuanfenleiView  extends ShetuanfenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShetuanfenleiView(){
	}
 
 	public ShetuanfenleiView(ShetuanfenleiEntity shetuanfenleiEntity){
 	try {
			BeanUtils.copyProperties(this, shetuanfenleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
