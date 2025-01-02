package com.entity.view;

import com.entity.ShetuanxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 社团信息
 * 后端返回视图实体辅助类
 */
@TableName("shetuanxinxi")
public class ShetuanxinxiView  extends ShetuanxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShetuanxinxiView(){
	}
 
 	public ShetuanxinxiView(ShetuanxinxiEntity shetuanxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, shetuanxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
