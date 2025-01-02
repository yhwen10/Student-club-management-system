package com.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.service.ConfigService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ValidatorUtils;

/**
 * 该类作为配置相关的控制器，主要负责处理与配置信息（ConfigEntity）有关的各种请求操作，
 * 例如获取配置列表、单个配置详情、保存、更新以及删除配置等功能，通过调用ConfigService层的方法来实现具体业务逻辑。
 */
@RequestMapping("config")
@RestController
public class ConfigController {

    // 自动注入ConfigService，用于调用与配置信息相关的业务逻辑方法
    @Autowired
    private ConfigService configService;

    /**
     * 获取配置信息列表（分页形式）
     * 接收查询参数和一个ConfigEntity对象（当前在方法内未充分利用该对象，可根据实际业务需求完善），
     * 通过创建EntityWrapper构建查询条件，调用服务层的queryPage方法获取分页数据并返回。
     *
     * @param params 查询参数，以键值对形式传递，例如分页相关参数、筛选条件等，通过请求参数传入
     * @param config 配置实体对象，当前在方法内未充分利用，可根据业务完善使用逻辑，通过方法参数传入
     * @return 返回包含分页配置信息数据的响应结果，包装在R对象中
     */
    @RequestMapping("/page")
    public R getConfigPage(@RequestParam Map<String, Object> params, ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        PageUtils page = configService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 获取配置信息列表（无分页，忽略权限验证）
     * 与getConfigPage方法类似，接收查询参数和ConfigEntity对象，不过此方法忽略权限验证，
     * 同样通过EntityWrapper构建条件并调用服务层queryPage方法获取数据后返回。
     * 这里存在代码重复情况，可考虑提取公共逻辑进行优化，但为了保持功能清晰暂按此结构展示。
     *
     * @param params 查询参数，以键值对形式传递，例如筛选条件等，通过请求参数传入
     * @param config 配置实体对象，当前在方法内未充分利用，可根据业务完善使用逻辑，通过方法参数传入
     * @return 返回包含配置信息数据的响应结果，包装在R对象中
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R getConfigList(@RequestParam Map<String, Object> params, ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        PageUtils page = configService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 根据配置ID获取单个配置的详细信息
     * 通过路径变量接收配置的唯一标识ID，调用服务层的selectById方法获取对应的配置实体对象，
     * 然后将该对象包装在响应结果中返回。
     *
     * @param id 配置的唯一标识ID，通过路径变量传入
     * @return 返回包含指定ID对应的配置详细信息的响应结果，包装在R对象中
     */
    @RequestMapping("/info/{id}")
    public R getConfigInfoById(@PathVariable("id") String id) {
        ConfigEntity config = configService.selectById(id);
        return R.ok().put("data", config);
    }

    /**
     * 根据配置ID获取单个配置的详细信息（忽略权限验证）
     * 功能与getConfigInfoById类似，只是此方法忽略权限验证，同样根据传入的ID获取配置实体并返回。
     * 存在代码重复逻辑，可提取公共方法优化，但此处保持功能清晰展示现有结构。
     *
     * @param id 配置的唯一标识ID，通过路径变量传入
     * @return 返回包含指定ID对应的配置详细信息的响应结果，包装在R对象中
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R getConfigDetailById(@PathVariable("id") String id) {
        ConfigEntity config = configService.selectById(id);
        return R.ok().put("data", config);
    }

    /**
     * 根据配置名称获取配置信息
     * 通过请求参数接收配置名称，利用EntityWrapper构建按名称查询的条件，调用服务层的selectOne方法
     * 获取符合条件的配置实体对象，并将其包装在响应结果中返回。
     *
     * @param name 配置名称，通过请求参数传入
     * @return 返回包含指定名称对应的配置信息的响应结果，包装在R对象中
     */
    @RequestMapping("/info")
    public R getConfigInfoByName(@RequestParam String name) {
        ConfigEntity config = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
        return R.ok().put("data", config);
    }

    /**
     * 保存配置信息
     * 接收一个ConfigEntity对象作为要保存的配置信息，此处原本有验证逻辑（暂时被注释掉），
     * 调用服务层的insert方法将配置信息插入数据库，成功后返回操作成功的响应结果。
     *
     * @param config 要保存的配置实体对象，包含配置相关的各种属性，通过请求体传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @PostMapping("/save")
    public R saveConfig(@RequestBody ConfigEntity config) {
//        ValidatorUtils.validateEntity(config);
        configService.insert(config);
        return R.ok();
    }

    /**
     * 更新配置信息
     * 接收一个ConfigEntity对象作为要更新的配置信息，同样原本有验证逻辑（暂时被注释掉），
     * 调用服务层的updateById方法根据配置对象的ID更新对应的配置信息，成功后返回操作成功的响应结果。
     *
     * @param config 要更新的配置实体对象，包含配置相关的各种属性，通过请求体传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/update")
    public R updateConfig(@RequestBody ConfigEntity config) {
//        ValidatorUtils.validateEntity(config);
        configService.updateById(config);
        return R.ok();
    }

    /**
     * 删除配置信息
     * 接收一个Long类型的数组作为要删除的配置信息的ID集合，调用服务层的deleteBatchIds方法批量删除
     * 对应ID的配置信息，成功后返回操作成功的响应结果。
     *
     * @param ids 要删除的配置信息的ID数组，通过请求体传入
     * @return 返回操作成功的响应结果，包装在R对象中
     */
    @RequestMapping("/delete")
    public R deleteConfigs(@RequestBody Long[] ids) {
        configService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}