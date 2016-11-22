package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ChatgroupAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 群组控制器
 * Created by pengyt on 2016/11/13.
 */
@Controller
public class ChatgroupController {

    /**
     * 取得群组信息1070
     * @param chatgroupId 群组id
     * @param key 不羁旅行令牌
     * @return 群组信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroup(@PathVariable("chatgroupId") Long chatgroupId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroup(chatgroupId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
