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

import com.entity.DiscussshetuanxinxiEntity;
import com.entity.view.DiscussshetuanxinxiView;

import com.service.DiscussshetuanxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 社团信息评论表后端接口类
 * 该类主要负责处理与社团信息评论相关的各种HTTP请求，通过调用对应的服务层（DiscussshetuanxinxiService）方法来实现具体业务逻辑，
 * 例如获取评论列表（分页、不分页等情况）、查询单个评论详情、保存、更新以及删除评论等功能，同时还包含一个提醒相关的接口。
 */
@RestController
@RequestMapping("/discussshetuanxinxi")
public class DiscussshetuanxinxiController {

    // 自动注入社团信息评论服务层接口，用于调用与社团信息评论相关的业务逻辑方法
    @Autowired
    private DiscussshetuanxinxiService discussshetuanxinxiService;

    /**
     * 提取公共的构建EntityWrapper的逻辑到一个私有方法中，根据传入的实体对象和参数构建查询条件
     *
     * @param discussshetuanxinxi 社团信息评论实体对象，用于提取属性构建查询条件
     * @param params              查询参数，包含各种筛选、排序等条件信息
     * @return 构建好的EntityWrapper对象，用于后续查询操作
     */
    private EntityWrapper<DiscussshetuanxinxiEntity> buildEntityWrapper(DiscussshetuanxinxiEntity discussshetuanxinxi, Map<String, Object> params) {
        EntityWrapper<DiscussshetuanxinxiEntity> ew = new EntityWrapper<>();
        ew = (EntityWrapper<DiscussshetuanxinxiEntity>) MPUtil.likeOrEq(ew, discussshetuanxinxi);
        ew = (EntityWrapper<DiscussshetuanxinxiEntity>) MPUtil.between(ew, params);
        ew = (EntityWrapper<DiscussshetuanxinxiEntity>) MPUtil.sort(ew, params);
        return ew;
    }

    /**
     * 后端获取社团信息评论列表（分页）
     * 接收查询参数、社团信息评论实体对象（用于构建查询条件）以及HttpServletRequest对象（当前在方法内未充分利用，可根据实际业务完善使用逻辑），
     * 通过构建EntityWrapper设置查询条件，调用服务层的queryPage方法获取分页数据并返回。
     *
     * @param params              查询参数，例如分页相关参数、筛选条件等，通过请求参数传入
     * @param discussshetuanxinxi 社团信息评论实体对象，用于构建查询条件，通过方法参数传入
     * @param request             HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
     * @return 返回包含分页社团信息评论数据的响应结果，包装在R对象中
     */
    @RequestMapping("/page")
    public R getBackendPage(@RequestParam Map<String, Object> params, DiscussshetuanxinxiEntity discussshetuanxinxi,
                            HttpServletRequest request) {
        EntityWrapper<DiscussshetuanxinxiEntity> ew = buildEntityWrapper(discussshetuanxinxi, params);
        PageUtils page = discussshetuanxinxiService.queryPage(params, ew);
        return R.ok().put("data", page);
    }

    /**
     * 前端获取社团信息评论列表（分页，忽略权限验证）
     * 功能与getBackendPage类似，不过此方法忽略权限验证，同样接收相关参数构建查询条件后获取分页数据并返回。
     *
     * @param params              查询参数，例如分页相关参数、筛选条件等，通过请求参数传入
     * @param discussshetuanxinxi 社团信息评论实体对象，用于构建查询条件，通过方法参数传入
     * @param request             HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
     * @return 返回包含分页社团信息评论数据的响应结果，包装在R对象中
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R getFrontendPage(@RequestParam Map<String, Object> params, DiscussshetuanxinxiEntity discussshetuanxinxi,
                             HttpServletRequest request) {
        EntityWrapper<DiscussshetuanxinxiEntity> ew = buildEntityWrapper(discussshetuanxinxi, params);
        PageUtils page = discussshetuanxinxiService.queryPage(params, ew);
        return R.ok().put("data", page);
    }

    /**
     * 获取社团信息评论列表（无分页相关逻辑）
     * 根据传入的社团信息评论实体对象构建查询条件（使用allEq匹配所有属性相等的条件），调用服务层的selectListView方法获取数据并返回。
     *
     * @param discussshetuanxinxi 社团信息评论实体对象，用于构建查询条件，通过方法参数传入
     * @return 返回包含社团信息评论数据的响应结果，包装在R对象中
     */
    @RequestMapping("/lists")
    public R getList(DiscussshetuanxinxiEntity discussshetuanxinxi) {
        EntityWrapper<DiscussshetuanxinxiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(discussshetuanxinxi, "discussshetuanxinxi"));
        return R.ok().put("data", discussshetuanxinxiService.selectListView(ew));
    }

    /**
     * 查询社团信息评论详情（返回视图对象）
     * 根据传入的社团信息评论实体对象构建查询条件（使用allEq匹配所有属性相等的条件），调用服务层的selectView方法获取对应的视图对象数据并返回。
     *
     * @param discussshetuanxinxi 社团信息评论实体对象，用于构建查询条件，通过方法参数传入
     * @return 返回包含查询到的社团信息评论视图对象数据的响应结果，包装在R对象中，并附带成功提示信息
     */
    @RequestMapping("/query")
    public R query(DiscussshetuanxinxiEntity discussshetuanxinxi) {
        EntityWrapper<DiscussshetuanxinxiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(discussshetuanxinxi, "discussshetuanxinxi"));
        DiscussshetuanxinxiView discussshetuanxinxiView = discussshetuanxinxiService.selectView(ew);
        return R.ok("查询社团信息评论表成功").put("data", discussshetuanxinxiView);
    }

    /**
     * 后端获取社团信息评论详情（通过ID）
     * 通过路径变量接收社团信息评论的唯一标识ID，调用服务层的selectById方法获取对应的社团信息评论实体对象，
     * 然后将该对象包装在响应结果中返回。
     *
     * @param id 社团信息评论的唯一标识ID，通过路径变量传入
     * @return 返回包含指定ID对应的社团信息评论详细信息的响应结果，包装在R对象中
     */
    @RequestMapping("/info/{id}")
    public R getBackendInfo(@PathVariable("id") Long id) {
        DiscussshetuanxinxiEntity discussshetuanxinxi = discussshetuanxinxiService.selectById(id);
        return R.ok().put("data", discussshetuanxinxi);
    }

    /**
     * 前端获取社团信息评论详情（通过ID，忽略权限验证）
     * 功能与getBackendInfo类似，只是此方法忽略权限验证，同样根据传入的ID获取社团信息评论实体并返回。
     *
     * @param id 社团信息评论的唯一标识ID，通过路径变量传入
     * @return 返回包含指定ID对应的社团信息评论详细信息的响应结果，包装在R对象中
     */
    @RequestMapping("/detail/{id}")
    public R getFrontendInfo(@PathVariable("id") Long id) {
        DiscussshetuanxinxiEntity discussshetuanxinxi = discussshetuanxinxiService.selectById(id);
        return R.ok().put("data", discussshetuanxinxi);
    }

    /**
     * 后端保存社团信息评论
     * 接收一个社团信息评论实体对象作为要保存的信息，设置其ID（通过当前时间戳和随机数生成），原本有验证逻辑（暂时被注释掉），
     * 调用服务层的insert方法将社团信息评论插入数据库，成功后返回操作成功的响应结果。
     *
     * @param discussshetuanxinxi 要保存的社团信息评论实体对象，包含评论相关的各种属性，通过请求体传入
     * @param request             HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/save")
    public R saveBackend(@RequestBody DiscussshetuanxinxiEntity discussshetuanxinxi, HttpServletRequest request) {
        discussshetuanxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
//        ValidatorUtils.validateEntity(discussshetuanxinxi);
        discussshetuanxinxiService.insert(discussshetuanxinxi);
        return R.ok();
    }

    /**
     * 前端保存社团信息评论
     * 与saveBackend方法功能类似，同样接收社团信息评论实体对象进行保存操作，设置ID并插入数据库，成功后返回响应结果，
     * 只是名称上区分了前端保存操作，方便代码逻辑区分。
     *
     * @param discussshetuanxinxi 要保存的社团信息评论实体对象，包含评论相关的各种属性，通过请求体传入
     * @param request             HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/add")
    public R saveFrontend(@RequestBody DiscussshetuanxinxiEntity discussshetuanxinxi, HttpServletRequest request) {
        discussshetuanxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
//        ValidatorUtils.validateEntity(discussshetuanxinxi);
        discussshetuanxinxiService.insert(discussshetuanxinxi);
        return R.ok();
    }

    /**
     * 更新社团信息评论
     * 接收一个社团信息评论实体对象作为要更新的信息，原本有验证逻辑（暂时被注释掉），
     * 调用服务层的updateById方法根据评论对象的ID更新对应的评论信息，成功后返回操作成功的响应结果。
     *
     * @param discussshetuanxinxi 要更新的社团信息评论实体对象，包含评论相关的各种属性，通过请求体传入
     * @param request             HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/update")
    public R updateComment(@RequestBody DiscussshetuanxinxiEntity discussshetuanxinxi, HttpServletRequest request) {
//        ValidatorUtils.validateEntity(discussshetuanxinxi);
        discussshetuanxinxiService.updateById(discussshetuanxinxi);
        return R.ok();
    }

    /**
     * 删除社团信息评论（批量）
     * 接收一个Long类型的数组作为要删除的社团信息评论的ID集合，调用服务层的deleteBatchIds方法批量删除
     * 对应ID的社团信息评论，成功后返回操作成功的响应结果。
     *
     * @param ids 要删除的社团信息评论的ID数组，通过请求体传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/delete")
    public R deleteComments(@RequestBody Long[] ids) {
        discussshetuanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 社团信息评论提醒接口
     * 根据传入的列名、类型以及其他筛选条件（如果是日期类型则处理日期范围）构建查询条件，调用服务层的selectCount方法获取符合提醒条件的记录数量并返回。
     *
     * @param columnName 列名，用于构建查询条件，通过路径变量传入
     * @param request    HttpServletRequest对象，当前未充分利用，可根据业务完善使用，通过方法参数传入
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

        Wrapper<DiscussshetuanxinxiEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart")!= null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend")!= null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = discussshetuanxinxiService.selectCount(wrapper);
        return R.ok().put("count", count);
    }
}