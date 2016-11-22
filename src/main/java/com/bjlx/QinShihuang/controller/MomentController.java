package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.MomentAPI;
import com.bjlx.QinShihuang.requestmodel.MomentReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 时间线控制器
 * Created by xiaozhi on 2016/11/18.
 */
@Controller
public class MomentController {

    /**
     * 查看自己的关注圈1038
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几条开始取
     * @param limit 取多少条
     * @param latestTime 最晚时间，用于拉取最新的朋友圈
     * @param earliestTime 最早时间，用户拉取老的朋友圈
     * @return 信息列表
     */
    @RequestMapping(value = "/app/moments", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getMoments(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, Integer offset, Integer limit, Long latestTime, Long earliestTime) {
        if(latestTime == null && earliestTime == null) {
            return QinShihuangResult.getResult(ErrorCode.TIME_NULL_1038);
        }
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return MomentAPI.getMoments(userId, key, defaultOffset, defaultLimit, latestTime, earliestTime);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加时间线1039
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param momentReq 时间线参数
     * @return 结果
     */
    @RequestMapping(value = "/app/moments", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addMoment(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody MomentReq momentReq) {
        if(momentReq.getOriginId() != null) {
            if(momentReq.getOriginUserId() == null)
                return QinShihuangResult.getResult(ErrorCode.ORIGINUSERID_NULL_1039);
            if(momentReq.getOriginNickName() == null)
                return QinShihuangResult.getResult(ErrorCode.ORIGINNICKNAME_NULL_1039);
            if(momentReq.getOriginAvatar() == null)
                return QinShihuangResult.getResult(ErrorCode.ORIGINAVATAR_NULL_1039);
        }
        try {
            return MomentAPI.addMoment(userId, key, momentReq.getOriginId(), momentReq.getOriginUserId(), momentReq.getOriginNickName(), momentReq.getOriginAvatar(), momentReq.getText(),
                    momentReq.getComment(), momentReq.getImages(), momentReq.getCard());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 查看某个人的发表记录1080
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几条开始取
     * @param limit 取多少条
     * @param targetId 查看对象用户id
     * @param latestTime 最晚时间，用于拉取最新的朋友圈
     * @param earliestTime 最早时间，用户拉取老的朋友圈
     * @return 信息列表
     */
    @RequestMapping(value = "/app/users/{targetId:\\d+}/moments", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getMomentsById(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @PathVariable("targetId") Long targetId, Integer offset, Integer limit, Long latestTime, Long earliestTime) {
        if(latestTime == null && earliestTime == null) {
            return QinShihuangResult.getResult(ErrorCode.TIME_NULL_1080);
        }
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return MomentAPI.getMomentsById(userId, key, targetId, defaultOffset, defaultLimit, latestTime, earliestTime);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
