package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ImAPI;
import com.bjlx.QinShihuang.requestmodel.MsgReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 即时消息控制器
 * Created by pengyt on 2016/11/8.
 */
@Controller
public class ImController {

    /**
     * 发送消息1064
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param msgReq 消息实体
     * @return 消息实体
     */
    @RequestMapping(value = "/app/messages", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendMsg(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody MsgReq msgReq) {
        // 检查参数
        if(msgReq.getReceiverId() == null) {
            return QinShihuangResult.getResult(ErrorCode.RECEIVERID_NULL_1064);
        }

        if(msgReq.getContent() == null) {
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1064);
        }

        if(msgReq.getMsgType() == null) {
            return QinShihuangResult.getResult(ErrorCode.MSGTYPE_NULL_1064);
        }

        if(msgReq.getChatType() == null) {
            return QinShihuangResult.getResult(ErrorCode.CHATTYPE_NULL_1064);
        }

        try {
            return ImAPI.sendMsg(userId, key, msgReq.getId(), msgReq.getConvId(), msgReq.getContent(), msgReq.getReceiverId(), msgReq.getMsgType(), msgReq.getChatType());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}
