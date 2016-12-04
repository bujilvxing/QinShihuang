package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.TripPlanAPI;
import com.bjlx.QinShihuang.requestmodel.TripPlanReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 行程规划控制器
 * Created by xiaozhi on 2016/11/30.
 */
@Controller
public class TripPlanController {

    /**
     * 发布行程规划1045
     * @param tripPlanReq 行程规划参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/tripplans", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addTripPlan(@RequestBody TripPlanReq tripPlanReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TripPlanAPI.addTripPlan(tripPlanReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 复制行程规划1046
     * @param tripPlanId 行程规划id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划
     */
    @RequestMapping(value = "/app/tripplans/{tripPlanId:\\[0-9a-f]{24}}/fork", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String forkTripPlan(@PathVariable("tripPlanId") String tripPlanId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TripPlanAPI.forkTripPlan(tripPlanId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户行程规划列表1047
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/tripplans", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String getTripPlans(@PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return TripPlanAPI.getTripPlans(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新行程规划1048
     * @param tripPlanId 行程规划id
     * @param tripPlanReq 行程规划参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划信息
     */
    @RequestMapping(value = "/app/tripplans/{tripPlanId:\\[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateTripPlan(@PathVariable("tripPlanId") String tripPlanId, @RequestBody TripPlanReq tripPlanReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TripPlanAPI.getTripPlan(tripPlanId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得行程规划详情1049
     * @param tripPlanId 行程规划id
     * @return 行程规划信息
     */
    @RequestMapping(value = "/app/tripplans/{tripPlanId:\\[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getTripPlan(@PathVariable("tripPlanId") String tripPlanId) {

        try {
            return TripPlanAPI.getTripPlan(tripPlanId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除行程规划1050
     * @param tripPlanId 行程规划id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/tripplans/{tripPlanId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeTripPlan(@PathVariable("tripPlanId") String tripPlanId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TripPlanAPI.removeTripPlan(tripPlanId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
