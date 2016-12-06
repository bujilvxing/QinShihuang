package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ActivityAPI;
import com.bjlx.QinShihuang.model.activity.Joiner;
import com.bjlx.QinShihuang.requestmodel.ActivityReq;
import com.bjlx.QinShihuang.requestmodel.ActivityUpdateReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.web.bind.annotation.*;

/**
 * 活动控制器
 * Created by pengyt on 2016/12/4.
 */
public class ActivityController {
    
    /**
     * 发布活动1030
     * @param activityReq 活动参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 活动信息
     */
    @RequestMapping(value = "/app/activities", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addActivity(@RequestBody ActivityReq activityReq, @RequestHeader ("userId") Long userId, @RequestHeader ("key") String key) {
        try {
            return ActivityAPI.addActivity(activityReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得活动列表1031
     * @param start 是否开始
     * @param end 是否结束
     * @param theme 主题
     * @param category 活动分类
     * @param free 是否免费
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     */
    @RequestMapping(value = "/app/activities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getActivities(Boolean start, Boolean end, String theme, String category, Boolean free, Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 0 : limit;
        try {
            return ActivityAPI.getActivities(start, end, theme, category, free, defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得活动详情1032
     * @param activityId 活动id
     * @return 活动详情
     */
    @RequestMapping(value = "/app/activities/{activityId:\\[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getActivityById(@PathVariable ("activityId") String activityId) {
        try {
            return ActivityAPI.getActivityById(activityId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户活动列表1086
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/activities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserActivities(@PathVariable ("userId") Long userId, @RequestHeader ("key") String key, Integer offset, Integer limit) {
        try {
            return ActivityAPI.getUserActivities(userId, key, offset, limit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 报名活动1087
     * @param activityId 活动id
     * @param joiner 参与人联系方式
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/activities/{activityId:\\[0-9a-f]{24}}/join", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String joinActivity(@PathVariable ("activityId") String activityId, @RequestBody Joiner joiner, @RequestHeader ("userId") Long userId, @RequestHeader ("key") String key) {
        try {
            return ActivityAPI.joinActivity(activityId, joiner, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 退出报名1088
     * @param activityId 活动id
     * @param joiner 参与人联系方式
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/activities/{activityId:\\[0-9a-f]{24}}/quit", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String quitActivity(@PathVariable ("activityId") String activityId, @RequestBody Joiner joiner, @RequestHeader ("userId") Long userId, @RequestHeader ("key") String key) {
        try {
            return ActivityAPI.quitActivity(activityId, joiner, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新活动1089
     * @param activityId 活动id
     * @param activityUpdateReq 更新活动参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/activities/{activityId:\\[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateActivity(@PathVariable ("activityId") String activityId, @RequestBody ActivityUpdateReq activityUpdateReq, @RequestHeader ("userId") Long userId, @RequestHeader ("key") String key) {
        try {
            return ActivityAPI.updateActivity(activityId, activityUpdateReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加门票，删除门票，修改门票，查询门票，门票列表
     */

}
