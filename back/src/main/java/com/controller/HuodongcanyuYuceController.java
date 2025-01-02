package com.controller;

import com.entity.HuodongcanyuYuceEntity;
import com.service.HuodongcanyuYuceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动参与预测控制器
 * 该类主要负责处理与活动参与预测相关的请求，通过调用HuodongcanyuYuceService服务层的方法，
 * 实现获取未审核活动以及预测活动参与人数的功能，并对可能出现的异常情况进行了相应的处理，返回合适的响应结果给客户端。
 */
@RestController
@RequestMapping("/huodongcanyuyuce")
public class HuodongcanyuYuceController {

    // 自动注入活动参与预测服务层接口，用于调用与活动参与预测相关的业务逻辑方法
    @Autowired
    private HuodongcanyuYuceService huodongcanyuYuceService;

    // 创建日志记录器，方便记录在处理请求过程中出现的各种情况，便于排查问题和监控系统运行状态
    private static final Logger logger = LoggerFactory.getLogger(HuodongcanyuYuceController.class);

    /**
     * 获取未审核活动
     * 调用服务层的getUnverifiedActivities方法获取未审核活动列表，若成功则将相关信息封装到响应Map中返回，
     * 若出现异常则记录异常信息，并在响应Map中设置相应的错误提示信息后返回。
     *
     * @return 包含获取未审核活动结果信息的Map，其中包含"success"表示是否成功以及"data"存放获取到的活动列表数据（若成功）
     */
    @GetMapping("/unverified")
    public ResponseEntity<Map<String, Object>> getUnverifiedActivities() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<HuodongcanyuYuceEntity> activities = huodongcanyuYuceService.getUnverifiedActivities();
            response.put("success", true);
            response.put("data", activities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("获取未审核活动时出现异常", e);
            response.put("success", false);
            response.put("message", "获取未审核活动失败：" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 预测活动参与人数
     * 接收一个HuodongcanyuYuceEntity对象作为要预测参与人数的活动相关信息，调用服务层的predictParticipants方法进行预测，
     * 若成功则将预测结果等信息封装到响应Map中返回，若出现异常则记录异常信息，并在响应Map中设置相应的错误提示信息后返回。
     *
     * @param activity 包含活动相关信息的实体对象，用于传递给服务层方法进行参与人数预测，通过请求体传入
     * @return 包含预测活动参与人数结果信息的Map，其中包含"success"表示是否成功以及"predictedParticipants"存放预测得到的参与人数（若成功）
     */
    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> predictParticipants(@RequestBody HuodongcanyuYuceEntity activity) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer predictedParticipants = huodongcanyuYuceService.predictParticipants(activity);
            response.put("success", true);
            response.put("predictedParticipants", predictedParticipants);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("预测活动参与人数时出现异常", e);
            response.put("success", false);
            response.put("message", "预测失败：" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}