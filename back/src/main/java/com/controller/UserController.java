package com.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.TokenEntity;
import com.entity.UserEntity;
import com.service.TokenService;
import com.service.UserService;
import com.utils.CommonUtil;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ValidatorUtils;

/**
 * 用户相关操作的控制器类，主要处理登录、注册、用户信息管理（查询、保存、更新、删除等）以及一些与用户会话相关的操作。
 */
@RequestMapping("users")
@RestController
public class UserController {

	// 自动注入用户服务层接口，用于调用与用户相关的业务逻辑方法
	@Autowired
	private UserService userService;

	// 自动注入令牌服务层接口，用于生成和管理用户登录令牌相关操作
	@Autowired
	private TokenService tokenService;

	/**
	 * 提取公共的构建EntityWrapper的逻辑到一个私有方法中，根据传入的用户实体对象和查询参数构建查询条件，
	 * 方便在多个涉及查询用户信息的方法中复用该逻辑，减少重复代码。
	 *
	 * @param user   用户实体对象，用于提取属性构建查询条件
	 * @param params 查询参数，包含各种筛选、排序等条件信息
	 * @return 构建好的EntityWrapper对象，用于后续查询操作
	 */
	private EntityWrapper<UserEntity> buildEntityWrapper(UserEntity user, Map<String, Object> params) {
		EntityWrapper<UserEntity> ew = new EntityWrapper<>();
		ew = (EntityWrapper<UserEntity>) MPUtil.allLike(ew, user);
		ew = (EntityWrapper<UserEntity>) MPUtil.between(ew, params);
		ew = (EntityWrapper<UserEntity>) MPUtil.sort(ew, params);
		return ew;
	}

	/**
	 * 用户登录方法
	 * 根据传入的用户名和密码进行用户验证，如果用户不存在或者密码不匹配则返回错误信息，
	 * 验证通过则生成用户令牌并返回包含令牌信息的成功响应。
	 * 此方法忽略权限验证（通过 @IgnoreAuth 注解标识），意味着不需要先登录即可访问该接口进行登录操作。
	 *
	 * @param username 用户名，通过请求参数传入
	 * @param password 密码，通过请求参数传入
	 * @param captcha  验证码（当前代码中未对验证码进行实际验证逻辑处理，可根据需求完善），通过请求参数传入
	 * @param request  HttpServletRequest对象，用于获取请求相关信息（当前代码中未充分利用，可根据业务完善），通过方法参数传入
	 * @return 返回包含登录结果信息的响应对象R，若登录成功则包含生成的令牌信息，若失败则包含相应的错误提示信息
	 */
	@IgnoreAuth
	@PostMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
		if (user == null ||!user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(user.getId(), username, "users", user.getRole());
		return R.ok().put("token", token);
	}

	/**
	 * 用户注册方法
	 * 首先验证传入的用户实体对象是否符合要求（原本有验证逻辑被注释掉，可根据实际需求完善验证规则），
	 * 然后检查用户名是否已存在，如果已存在则返回错误信息，否则将用户信息插入数据库并返回注册成功的响应。
	 * 此方法忽略权限验证（通过 @IgnoreAuth 注解标识），意味着不需要先登录即可访问该接口进行注册操作。
	 *
	 * @param user 要注册的用户实体对象，包含用户相关的各种属性，通过请求体传入
	 * @return 返回包含注册结果信息的响应对象R，若注册成功则返回成功提示，若用户名已存在则返回相应错误提示
	 */
	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody UserEntity user) {
//        ValidatorUtils.validateEntity(user);
		if (userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername()))!= null) {
			return R.error("用户已存在");
		}
		userService.insert(user);
		return R.ok();
	}

	/**
	 * 用户退出方法
	 * 通过使当前会话失效来实现用户退出功能，然后返回退出成功的响应信息。
	 *
	 * @param request HttpServletRequest对象，用于操作当前用户会话，通过方法参数传入
	 * @return 返回包含退出成功提示信息的响应对象R
	 */
	@GetMapping(value = "logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
	 * 用户密码重置方法
	 * 根据传入的用户名查找对应的用户信息，如果用户不存在则返回错误信息，
	 * 找到用户后将其密码重置为固定值 "123456"，并更新用户信息到数据库，最后返回密码重置成功的提示信息。
	 * 此方法忽略权限验证（通过 @IgnoreAuth 注解标识），意味着不需要先登录即可访问该接口进行密码重置操作。
	 *
	 * @param username 要重置密码的用户名，通过请求参数传入
	 * @param request  HttpServletRequest对象，用于获取请求相关信息（当前代码中未充分利用，可根据业务完善），通过方法参数传入
	 * @return 返回包含密码重置结果信息的响应对象R，若重置成功则返回包含新密码的提示信息，若用户不存在则返回相应错误提示
	 */
	@IgnoreAuth
	@RequestMapping(value = "/resetPass")
	public R resetPass(String username, HttpServletRequest request) {
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
		if (user == null) {
			return R.error("账号不存在");
		}
		user.setPassword("123456");
		userService.update(user, null);
		return R.ok("密码已重置为：123456");
	}

	/**
	 * 获取用户列表（分页）方法
	 * 接收查询参数和用户实体对象（用于构建查询条件），通过构建EntityWrapper设置查询条件，
	 * 调用服务层的queryPage方法获取分页数据并返回包含分页用户数据的响应结果。
	 *
	 * @param params 查询参数，例如分页相关参数、筛选条件等，通过请求参数传入
	 * @param user   用户实体对象，用于构建查询条件，通过方法参数传入
	 * @return 返回包含分页用户数据的响应结果，包装在R对象中
	 */
	@RequestMapping("/page")
	public R getUserPage(@RequestParam Map<String, Object> params, UserEntity user) {
		EntityWrapper<UserEntity> ew = buildEntityWrapper(user, params);
		PageUtils page = userService.queryPage(params, ew);
		return R.ok().put("data", page);
	}

	/**
	 * 获取用户列表（无分页相关逻辑）方法
	 * 根据传入的用户实体对象构建查询条件（使用allEq匹配所有属性相等的条件），调用服务层的selectListView方法获取数据并返回。
	 *
	 * @param user 用户实体对象，用于构建查询条件，通过方法参数传入
	 * @return 返回包含用户数据的响应结果，包装在R对象中
	 */
	@RequestMapping("/list")
	public R getUserList(UserEntity user) {
		EntityWrapper<UserEntity> ew = new EntityWrapper<>();
		ew.allEq(MPUtil.allEQMapPre(user, "user"));
		return R.ok().put("data", userService.selectListView(ew));
	}

	/**
	 * 获取用户详细信息方法
	 * 通过路径变量接收用户的唯一标识ID，调用服务层的selectById方法获取对应的用户实体对象，
	 * 然后将该对象包装在响应结果中返回。
	 *
	 * @param id 用户的唯一标识ID，通过路径变量传入
	 * @return 返回包含指定ID对应的用户详细信息的响应结果，包装在R对象中
	 */
	@RequestMapping("/info/{id}")
	public R getUserInfo(@PathVariable("id") String id) {
		UserEntity user = userService.selectById(id);
		return R.ok().put("data", user);
	}

	/**
	 * 获取当前登录用户的会话信息方法
	 * 从当前会话中获取用户ID，再根据该ID调用服务层的selectById方法获取对应的用户实体对象，
	 * 然后将该对象包装在响应结果中返回，用于获取当前登录用户的相关信息。
	 *
	 * @param request HttpServletRequest对象，用于获取当前用户会话中的用户ID信息，通过方法参数传入
	 * @return 返回包含当前登录用户详细信息的响应结果，包装在R对象中
	 */
	@RequestMapping("/session")
	public R getCurrentUser(HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("userId");
		UserEntity user = userService.selectById(id);
		return R.ok().put("data", user);
	}

	/**
	 * 保存用户信息方法
	 * 首先验证传入的用户实体对象是否符合要求（原本有验证逻辑被注释掉，可根据实际需求完善验证规则），
	 * 然后检查用户名是否已存在，如果已存在则返回错误信息，否则将用户信息插入数据库并返回保存成功的响应。
	 *
	 * @param user 要保存的用户实体对象，包含用户相关的各种属性，通过请求体传入
	 * @return 返回包含保存结果信息的响应对象R，若保存成功则返回成功提示，若用户名已存在则返回相应错误提示
	 */
	@PostMapping("/save")
	public R saveUser(@RequestBody UserEntity user) {
//        ValidatorUtils.validateEntity(user);
		if (userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername()))!= null) {
			return R.error("用户已存在");
		}
		userService.insert(user);
		return R.ok();
	}

	/**
	 * 更新用户信息方法
	 * 首先验证传入的用户实体对象是否符合要求（原本有验证逻辑被注释掉，可根据实际需求完善验证规则），
	 * 然后检查要更新的用户名是否已被其他用户使用（除了自身外），如果已存在则返回错误信息，
	 * 否则调用服务层的updateById方法根据用户对象的ID更新对应的用户信息，并返回更新成功的响应。
	 *
	 * @param user 要更新的用户实体对象，包含用户相关的各种属性，通过请求体传入
	 * @return 返回包含更新结果信息的响应对象R，若更新成功则返回成功提示，若用户名冲突则返回相应错误提示
	 */
	@RequestMapping("/update")
	public R updateUser(@RequestBody UserEntity user) {
//        ValidatorUtils.validateEntity(user);
		UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername()));
		if (u!= null && u.getId()!= user.getId() && u.getUsername().equals(user.getUsername())) {
			return R.error("用户名已存在。");
		}
		userService.updateById(user);
		return R.ok();
	}

	/**
	 * 删除用户信息（批量）方法
	 * 接收一个Long类型的数组作为要删除的用户的ID集合，调用服务层的deleteBatchIds方法批量删除
	 * 对应ID的用户，成功后返回操作成功的响应结果。
	 *
	 * @param ids 要删除的用户的ID数组，通过请求体传入
	 * @return 返回操作成功的响应结果，包装在R对象中
	 */
	@RequestMapping("/delete")
	public R deleteUsers(@RequestBody Long[] ids) {
		userService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}
}