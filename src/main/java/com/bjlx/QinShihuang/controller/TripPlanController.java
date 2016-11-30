package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.requestmodel.TripPlanReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 行程规划控制器
 * Created by xiaozhi on 2016/11/30.
 */
@Controller
public class TripPlanController {

    /**
     * 发布行程规划
     * @param tripPlanReq 行程规划参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/tripplans", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addTripPlan(@RequestBody TripPlanReq tripPlanReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        return null;
    }
}
