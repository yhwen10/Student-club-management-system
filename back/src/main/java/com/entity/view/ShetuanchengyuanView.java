package com.entity.view;

import com.entity.ShetuanchengyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 社团成员
 * 后端返回视图实体辅助类
 */
@TableName("shetuanchengyuan")
public class ShetuanchengyuanView  extends ShetuanchengyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShetuanchengyuanView(){
	}
 
 	public ShetuanchengyuanView(ShetuanchengyuanEntity shetuanchengyuanEntity){
 	try {
			BeanUtils.copyProperties(this, shetuanchengyuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
