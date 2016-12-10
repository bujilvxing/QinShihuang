package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.TraceAPI;
import com.bjlx.QinShihuang.requestmodel.*;
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
     * 添加足迹1040
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

}
