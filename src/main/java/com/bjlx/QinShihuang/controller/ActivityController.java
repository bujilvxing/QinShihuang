package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.requestmodel.ActivityReq;
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
     * @return 活动信息
     */
    @RequestMapping(value = "/app/activities", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addActivity(@RequestBody ActivityReq activityReq) {
        try {

        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
        return null;
    }

    /**
     * 取得活动列表1031
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     */
    @RequestMapping(value = "/app/activities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getActivities(Integer offset, Integer limit) {
        try {

        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
        return null;
    }

    /**
     * 取得活动详情1032
     * @param activityId 活动id
     * @return 活动详情
     */
    @RequestMapping(value = "/app/activities/{activityId:\\[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getActivityById(@PathVariable ("activityId") String activityId) {
        try {

        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
        return null;
    }

    /**
     * 取得用户活动列表1086
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/activities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserActivities(@PathVariable ("userId") Long userId, Integer offset, Integer limit) {
        try {

        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
        return null;
    }
}
