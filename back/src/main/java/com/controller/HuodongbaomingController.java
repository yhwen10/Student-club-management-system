package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.HuodongbaomingEntity;
import com.entity.view.HuodongbaomingView;

import com.service.HuodongbaomingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 活动报名后端接口类
 * 该类主要负责处理与活动报名相关的各种HTTP请求，通过调用对应的服务层（HuodongbaomingService）方法来实现具体业务逻辑，
 * 例如获取报名列表（分页、不分页等情况）、查询单个报名详情、保存、更新以及删除报名记录等功能，同时还包含一个提醒相关的接口，
 * 并且部分操作会根据当前登录用户的角色（从Session中获取相关信息判断）来进行额外的条件限制。
 */
@RestController
@RequestMapping("/huodongbaoming")
public class HuodongbaomingController {

	// 自动注入活动报名服务层接口，用于调用与活动报名相关的业务逻辑方法
	@Autowired
	private HuodongbaomingService huodongbaomingService;

	/**
	 * 提取公共的构建EntityWrapper的逻辑到一个私有方法中，根据传入的实体对象、参数以及HttpServletRequest对象构建查询条件
	 * （此处考虑到提醒接口中有根据用户角色添加额外条件的情况，所以将HttpServletRequest也作为参数传入）
	 *
	 * @param huodongbaoming 活动报名实体对象，用于提取属性构建查询条件
	 * @param params          查询参数，包含各种筛选、排序等条件信息
	 * @param request         HttpServletRequest对象，用于获取用户相关信息（如角色等）来添加额外查询条件
	 * @return 构建好的EntityWrapper对象，用于后续查询操作
	 */
	private EntityWrapper<HuodongbaomingEntity> buildEntityWrapper(HuodongbaomingEntity huodongbaoming, Map<String, Object> params, HttpServletRequest request) {
		EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<>();
		ew = (EntityWrapper<HuodongbaomingEntity>) MPUtil.likeOrEq(ew, huodongbaoming);
		ew = (EntityWrapper<HuodongbaomingEntity>) MPUtil.between(ew, params);
		ew = (EntityWrapper<HuodongbaomingEntity>) MPUtil.sort(ew, params);

		// 根据用户角色（从Session中获取表名判断）添加额外的查询条件
		String tableName = (String) request.getSession().getAttribute("tableName");
		if ("shezhang".equals(tableName)) {
			ew.eq("zhanghao", (String) request.getSession().getAttribute("username"));
		} else if ("xuesheng".equals(tableName)) {
			ew.eq("xuehao", (String) request.getSession().getAttribute("username"));
		}

		return ew;
	}

	/**
	 * 后端获取活动报名列表（分页）
	 * 接收查询参数、活动报名实体对象（用于构建查询条件）以及HttpServletRequest对象（用于获取用户相关信息来添加额外条件），
	 * 通过构建EntityWrapper设置查询条件，调用服务层的queryPage方法获取分页数据并返回。
	 *
	 * @param params          查询参数，例如分页相关参数、筛选条件等，通过请求参数传入
	 * @param huodongbaoming  活动报名实体对象，用于构建查询条件，通过方法参数传入
	 * @param request         HttpServletRequest对象，用于获取用户相关信息来添加额外条件，通过方法参数传入
	 * @return 返回包含分页活动报名数据的响应结果，包装在R对象中
	 */
	@RequestMapping("/page")
	public R getBackendPage(@RequestParam Map<String, Object> params, HuodongbaomingEntity huodongbaoming,
							HttpServletRequest request) {
		EntityWrapper<HuodongbaomingEntity> ew = buildEntityWrapper(huodongbaoming, params, request);
		PageUtils page = huodongbaomingService.queryPage(params, ew);
		return R.ok().put("data", page);
	}

	/**
	 * 前端获取活动报名列表（分页）
	 * 功能与getBackendPage类似，不过此方法未特别针对用户角色添加额外条件（若有需要可进一步调整），
	 * 同样接收相关参数构建查询条件后获取分页数据并返回。
	 *
	 * @param params          查询参数，例如分页相关参数、筛选条件等，通过请求参数传入
	 * @param huodongbaoming  活动报名实体对象，用于构建查询条件，通过方法参数传入
	 * @param request         HttpServletRequest对象，当前未充分利用添加额外条件相关逻辑（可根据业务完善），通过方法参数传入
	 * @return 返回包含分页活动报名数据的响应结果，包装在R对象中
	 */
	@RequestMapping("/list")
	public R getFrontendPage(@RequestParam Map<String, Object> params, HuodongbaomingEntity huodongbaoming,
							 HttpServletRequest request) {
		EntityWrapper<HuodongbaomingEntity> ew = buildEntityWrapper(huodongbaoming, params, request);
		PageUtils page = huodongbaomingService.queryPage(params, ew);
		return R.ok().put("data", page);
	}

	/**
	 * 获取活动报名列表（无分页相关逻辑）
	 * 根据传入的活动报名实体对象构建查询条件（使用allEq匹配所有属性相等的条件），调用服务层的selectListView方法获取数据并返回。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param huodongbaoming 活动报名实体对象，用于构建查询条件，通过方法参数传入
	 * @return 返回包含活动报名数据的响应结果，包装在R对象中
	 */
	@RequestMapping("/lists")
	public R getList(HuodongbaomingEntity huodongbaoming) {
		EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<>();
		ew.allEq(MPUtil.allEQMapPre(huodongbaoming, "huodongbaoming"));
		return R.ok().put("data", huodongbaomingService.selectListView(ew));
	}

	/**
	 * 查询活动报名详情（返回视图对象）
	 * 根据传入的活动报名实体对象构建查询条件（使用allEq匹配所有属性相等的条件），调用服务层的selectView方法获取对应的视图对象数据并返回。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param huodongbaoming 活动报名实体对象，用于构建查询条件，通过方法参数传入
	 * @return 返回包含查询到的活动报名视图对象数据的响应结果，包装在R对象中，并附带成功查询提示信息
	 */
	@RequestMapping("/query")
	public R query(HuodongbaomingEntity huodongbaoming) {
		EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<>();
		ew.allEq(MPUtil.allEQMapPre(huodongbaoming, "huodongbaoming"));
		HuodongbaomingView huodongbaomingView = huodongbaomingService.selectView(ew);
		return R.ok("查询活动报名成功").put("data", huodongbaomingView);
	}

	/**
	 * 后端获取活动报名详情（通过ID）
	 * 通过路径变量接收活动报名的唯一标识ID，调用服务层的selectById方法获取对应的活动报名实体对象，
	 * 然后将该对象包装在响应结果中返回。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param id 活动报名的唯一标识ID，通过路径变量传入
	 * @return 返回包含指定ID对应的活动报名详细信息的响应结果，包装在R对象中
	 */
	@RequestMapping("/info/{id}")
	public R getBackendInfo(@PathVariable("id") Long id) {
		HuodongbaomingEntity huodongbaoming = huodongbaomingService.selectById(id);
		return R.ok().put("data", huodongbaoming);
	}

	/**
	 * 前端获取活动报名详情（通过ID）
	 * 功能与getBackendInfo类似，只是此方法未特别针对用户角色添加额外条件（若有需要可进一步调整），
	 * 同样根据传入的ID获取活动报名实体并返回。
	 *
	 * @param id 活动报名的唯一标识ID，通过路径变量传入
	 * @return 返回包含指定ID对应的活动报名详细信息的响应结果，包装在R对象中
	 */
	@RequestMapping("/detail/{id}")
	public R getFrontendInfo(@PathVariable("id") Long id) {
		HuodongbaomingEntity huodongbaoming = huodongbaomingService.selectById(id);
		return R.ok().put("data", huodongbaoming);
	}

	/**
	 * 后端保存活动报名信息
	 * 接收一个活动报名实体对象作为要保存的信息，设置其ID（通过当前时间戳和随机数生成），原本有验证逻辑（暂时被注释掉），
	 * 调用服务层的insert方法将活动报名信息插入数据库，成功后返回操作成功的响应结果。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param huodongbaoming 要保存的活动报名实体对象，包含报名相关的各种属性，通过请求体传入
	 * @param request         HttpServletRequest对象，当前未充分利用（可根据业务完善使用），通过方法参数传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/save")
	public R saveBackend(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request) {
		huodongbaoming.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
//        ValidatorUtils.validateEntity(huodongbaoming);
		huodongbaomingService.insert(huodongbaoming);
		return R.ok();
	}

	/**
	 * 前端保存活动报名信息
	 * 与saveBackend方法功能类似，同样接收活动报名实体对象进行保存操作，设置ID并插入数据库，成功后返回响应结果，
	 * 只是名称上区分了前端保存操作，方便代码逻辑区分。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param huodongbaoming 要保存的活动报名实体对象，包含报名相关的各种属性，通过请求体传入
	 * @param request         HttpServletRequest对象，当前未充分利用（可根据业务完善使用），通过方法参数传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/add")
	public R saveFrontend(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request) {
		huodongbaoming.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
//        ValidatorUtils.validateEntity(huodongbaoming);
		huodongbaomingService.insert(huodongbaoming);
		return R.ok();
	}

	/**
	 * 更新活动报名信息
	 * 接收一个活动报名实体对象作为要更新的信息，原本有验证逻辑（暂时被注释掉），
	 * 调用服务层的updateById方法根据报名对象的ID更新对应的报名信息，成功后返回操作成功的响应结果。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param huodongbaoming 要更新的活动报名实体对象，包含报名相关的各种属性，通过请求体传入
	 * @param request         HttpServletRequest对象，当前未充分利用（可根据业务完善使用），通过方法参数传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/update")
	public R updateComment(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request) {
//        ValidatorUtils.validateEntity(huodongbaoming);
		huodongbaomingService.updateById(huodongbaoming);
		return R.ok();
	}

	/**
	 * 删除活动报名信息（批量）
	 * 接收一个Long类型的数组作为要删除的活动报名的ID集合，调用服务层的deleteBatchIds方法批量删除
	 * 对应ID的活动报名，成功后返回操作成功的响应结果。
	 * 这里未涉及根据用户角色添加额外条件的逻辑，若有需要可参考其他方法进行调整。
	 *
	 * @param ids 要删除的活动报名的ID数组，通过请求体传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/delete")
	public R deleteComments(@RequestBody Long[] ids) {
		huodongbaomingService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

	/**
	 * 活动报名提醒接口
	 * 根据传入的列名、类型以及其他筛选条件（如果是日期类型则处理日期范围）构建查询条件，
	 * 同时会根据当前登录用户的角色（从Session中获取相关信息判断）添加额外的查询条件，
	 * 调用服务层的selectCount方法获取符合提醒条件的记录数量并返回。
	 *
	 * @param columnName 列名，用于构建查询条件，通过路径变量传入
	 * @param request    HttpServletRequest对象，用于获取用户相关信息（如角色等）来添加额外查询条件，通过方法参数传入
	 * @param type       类型，1表示非日期类型筛选，2表示日期类型筛选，通过路径变量传入
	 * @param map        包含其他筛选条件的Map，例如日期范围等（针对日期类型），通过请求参数传入
	 * @return 返回包含符合提醒条件记录数量的响应结果，包装在R对象中
	 */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
						 @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
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

		Wrapper<HuodongbaomingEntity> wrapper = new EntityWrapper<>();
		if (map.get("remindstart")!= null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if (map.get("remindend")!= null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = (String) request.getSession().getAttribute("tableName");
		if ("shezhang".equals(tableName)) {
			wrapper.eq("zhanghao", (String) request.getSession().getAttribute("username"));
		} else if ("xuesheng".equals(tableName)) {
			wrapper.eq("xuehao", (String) request.getSession().getAttribute("username"));
		}

		int count = huodongbaomingService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
}