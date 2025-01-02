package com.entity.view;

import com.entity.JiarushetuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 加入社团
 * 后端返回视图实体辅助类
 */
@TableName("jiarushetuan")
public class JiarushetuanView  extends JiarushetuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiarushetuanView(){
	}
 
 	public JiarushetuanView(JiarushetuanEntity jiarushetuanEntity){
 	try {
			BeanUtils.copyProperties(this, jiarushetuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
