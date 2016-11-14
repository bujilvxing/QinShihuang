package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ImAPI;
import com.bjlx.QinShihuang.requestmodel.ConversationReq;
import com.bjlx.QinShihuang.requestmodel.FetchMsgReq;
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

    /**
     * 拉取消息。对于没有送达的消息会重新发送
     * 有两个阶段需要拉取消息
     * 1、用户登录时
     * 2、过一段时间，客户端自动发一个请求，拉取消息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param fetchMsgReq 拉取消息参数
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/messages", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String fetchMsg(@PathVariable Long userId, @RequestHeader("key") String key, @RequestBody FetchMsgReq fetchMsgReq) {
        Long purgeBefore = fetchMsgReq.getPurgeBefore() == null ? 0L : fetchMsgReq.getPurgeBefore();
        try {
            return ImAPI.fetchMsg(userId, key, purgeBefore);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 更新回话
     * @param userId 用户id
     * @param id 回话id
     * @param key 不羁旅行令牌
     * @param conversationReq 更新回话的参数
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/conversations/{id:\\[0-9a-f]{24}}", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateConversation(@PathVariable Long userId, @PathVariable String id, @RequestHeader("key") String key, @RequestBody ConversationReq conversationReq) {
        if(conversationReq.isMute() == null)
            return QinShihuangResult.getResult(ErrorCode.MUTE_NULL_1066);

        try {
            return ImAPI.updateConversation(userId, id, key, conversationReq.isMute());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得会话列表
     * @param userId 用户id
     * @param key
     * @param conversationReq
     * @return
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/conversations", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String getConversationByIds(@PathVariable Long userId, @RequestHeader("key") String key, @RequestBody ConversationReq conversationReq) {
        if(conversationReq.getIds() == null)
            return QinShihuangResult.getResult(ErrorCode.MUTE_NULL_1066);

        try {
//            return ImAPI.updateConversation(userId, key, conversationReq.getIds());
            return null;
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}
