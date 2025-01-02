package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ConfigEntity;
import com.service.CommonService;
import com.service.ConfigService;
import com.utils.FileUtil;
import com.utils.R;

/**
 * 通用接口
 * 这个类主要提供了一系列通用的数据操作相关接口，通过调用对应的服务层方法来实现具体业务逻辑
 */
@RestController
public class CommonController {

	// 自动注入CommonService，用于调用通用业务逻辑相关的方法
	@Autowired
	private CommonService commonService;

	// 自动注入ConfigService，虽然在当前展示的代码中未明确看到使用，可能在更完整的逻辑中有其作用
	@Autowired
	private ConfigService configService;

	/**
	 * 获取table表中的column列表(联动接口)
	 * 接收表名和列名等参数，组装查询参数后调用服务层方法获取对应列的数据列表
	 *
	 * @param tableName  表名，通过路径变量传入
	 * @param columnName 列名，通过路径变量传入
	 * @param level      可选的层级参数，用于更细致的筛选（如果不为空）
	 * @param parent     可选的父级相关参数，用于更细致的筛选（如果不为空）
	 * @return 返回包含对应列数据列表的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/option/{tableName}/{columnName}")
	public R getOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
					   String level, String parent) {
		Map<String, Object> params = new HashMap<>();
		params.put("table", tableName);
		params.put("column", columnName);
		if (StringUtils.isNotBlank(level)) {
			params.put("level", level);
		}
		if (StringUtils.isNotBlank(parent)) {
			params.put("parent", parent);
		}
		List<String> data = commonService.getOption(params);
		return R.ok().put("data", data);
	}

	/**
	 * 根据table中的column获取单条记录
	 * 接收表名、列名以及具体的列值参数，组装查询参数后调用服务层方法获取符合条件的单条记录
	 *
	 * @param tableName    表名，通过路径变量传入
	 * @param columnName   列名，通过路径变量传入
	 * @param columnValue  具体的列值，通过请求参数传入，用于确定具体要获取的记录
	 * @return 返回包含查询到的单条记录（包装在Map中）的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/follow/{tableName}/{columnName}")
	public R getFollowByOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
							   @RequestParam String columnValue) {
		Map<String, Object> params = new HashMap<>();
		params.put("table", tableName);
		params.put("column", columnName);
		params.put("columnValue", columnValue);
		Map<String, Object> result = commonService.getFollowByOption(params);
		return R.ok().put("data", result);
	}

	/**
	 * 修改table表的sfsh状态
	 * 接收表名和包含修改数据的Map，将表名添加到Map中后调用服务层方法来执行状态修改操作
	 *
	 * @param tableName 表名，通过路径变量传入
	 * @param map       包含修改数据的Map，通过请求体传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/sh/{tableName}")
	public R sh(@PathVariable("tableName") String tableName, @RequestBody Map<String, Object> map) {
		map.put("table", tableName);
		commonService.sh(map);
		return R.ok();
	}

	/**
	 * 获取需要提醒的记录数
	 * 根据传入的表名、列名、类型以及其他筛选条件（如果是日期类型则处理日期范围），调用服务层方法获取符合提醒条件的记录数量
	 *
	 * @param tableName 表名，通过路径变量传入
	 * @param columnName 列名，通过路径变量传入
	 * @param type      类型，1表示数字类型筛选，2表示日期类型筛选，通过路径变量传入
	 * @param map       包含其他筛选条件的Map，通过请求参数传入，如日期范围等（针对日期类型）
	 * @return 返回包含符合提醒条件记录数量的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/remind/{tableName}/{columnName}/{type}")
	public R remindCount(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
						 @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
		map.put("table", tableName);
		map.put("column", columnName);
		map.put("type", type);

		if ("2".equals(type)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if (map.get("remindstart")!= null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if (map.get("remindend")!= null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}

		int count = commonService.remindCount(map);
		return R.ok().put("count", count);
	}

	/**
	 * 提取公共的日期格式化处理逻辑到一个私有方法中，避免重复代码
	 *
	 * @param resultList 需要处理日期格式化的结果列表，其中可能包含日期类型的数据需要格式化
	 */
	private void formatDateInResult(List<Map<String, Object>> resultList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> m : resultList) {
			for (String k : m.keySet()) {
				if (m.get(k) instanceof Date) {
					m.put(k, sdf.format((Date) m.get(k)));
				}
			}
		}
	}

	/**
	 * 单列求和
	 * 接收表名和列名参数，组装查询参数后调用服务层方法获取对应列的求和结果
	 *
	 * @param tableName  表名，通过路径变量传入
	 * @param columnName 列名，通过路径变量传入
	 * @return 返回包含求和结果（包装在Map中）的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/cal/{tableName}/{columnName}")
	public R cal(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
		Map<String, Object> params = new HashMap<>();
		params.put("table", tableName);
		params.put("column", columnName);
		Map<String, Object> result = commonService.selectCal(params);
		return R.ok().put("data", result);
	}

	/**
	 * 分组统计
	 * 接收表名和列名参数，调用服务层方法获取分组统计结果，并对结果中的日期类型数据进行格式化处理后返回
	 *
	 * @param tableName  表名，通过路径变量传入
	 * @param columnName 列名，通过路径变量传入
	 * @return 返回包含格式化后分组统计结果的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/group/{tableName}/{columnName}")
	public R group(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
		Map<String, Object> params = new HashMap<>();
		params.put("table", tableName);
		params.put("column", columnName);
		List<Map<String, Object>> result = commonService.selectGroup(params);
		formatDateInResult(result);
		return R.ok().put("data", result);
	}

	/**
	 * （按值统计）
	 * 接收表名、x列名和y列名参数，调用服务层方法获取按值统计的结果，并对结果中的日期类型数据进行格式化处理后返回
	 *
	 * @param tableName   表名，通过路径变量传入
	 * @param yColumnName y列名，通过路径变量传入
	 * @param xColumnName x列名，通过路径变量传入
	 * @return 返回包含格式化后按值统计结果的响应结果，包装在R对象中
	 */
	@IgnoreAuth
	@RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}")
	public R value(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName,
				   @PathVariable("xColumnName") String xColumnName) {
		Map<String, Object> params = new HashMap<>();
		params.put("table", tableName);
		params.put("xColumn", xColumnName);
		params.put("yColumn", yColumnName);
		List<Map<String, Object>> result = commonService.selectValue(params);
		formatDateInResult(result);
		return R.ok().put("data", result);
	}
}