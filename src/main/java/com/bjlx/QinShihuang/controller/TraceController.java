package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.TraceAPI;
import com.bjlx.QinShihuang.requestmodel.TraceReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 足迹模块
 * Created by zhouyb on 2016/11/20.
 */
@Controller
public class TraceController {

    /**
     * 发布足迹1052
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param traceReq 足迹参数
     * @return 结果
     */
    @RequestMapping(value = "/app/traces", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addTrace(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody TraceReq traceReq) {

        try {
            return TraceAPI.addTrace(userId, key, traceReq);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新足迹1053
     * @param traceId 足迹id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param traceReq 足迹参数
     * @return 足迹信息
     */
    @RequestMapping(value = "/app/traces/{traceId:\\[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateTrace(@PathVariable("traceId") String traceId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody TraceReq traceReq) {

        try {
            return TraceAPI.updateTrace(traceId, userId, key, traceReq);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除足迹1054
     * @param traceId 足迹id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/traces/{traceId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeTrace(@PathVariable("traceId") String traceId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TraceAPI.removeTrace(traceId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户足迹列表1055
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 足迹列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/traces", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getTraces(@PathVariable("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return TraceAPI.getTraces(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得足迹详情1056
     * @param traceId 足迹id
     * @return 足迹详情
     */
    @RequestMapping(value = "/app/traces/{traceId:\\[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getTrace(@PathVariable("traceId") String traceId) {

        try {
            return TraceAPI.getTrace(traceId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
