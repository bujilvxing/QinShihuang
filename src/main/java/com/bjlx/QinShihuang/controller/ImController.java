package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.CommonAPI;
import com.bjlx.QinShihuang.core.ImAPI;
import com.bjlx.QinShihuang.requestmodel.ConversationReq;
import com.bjlx.QinShihuang.requestmodel.FetchMsgReq;
import com.bjlx.QinShihuang.requestmodel.MsgReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 即时消息控制器
 * Created by pengyt on 2016/11/8.
 */
@Controller
public class ImController {

    /**
     * 发送消息1081
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param msgReq 消息实体
     * @return 消息实体
     */
    @RequestMapping(value = "/app/messages", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendMsg(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody MsgReq msgReq) {
        // 检查参数
        if(msgReq.getReceiverId() == null) {
            return QinShihuangResult.getResult(ErrorCode.RECEIVERID_NULL_1081);
        }

        if(msgReq.getContent() == null) {
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1081);
        }

        if(msgReq.getMsgType() == null) {
            return QinShihuangResult.getResult(ErrorCode.MSGTYPE_NULL_1081);
        }

        if(msgReq.getChatType() == null) {
            return QinShihuangResult.getResult(ErrorCode.CHATTYPE_NULL_1081);
        }

        try {
            return ImAPI.sendMsg(userId, key, msgReq.getConvId(), msgReq.getContent(), msgReq.getReceiverId(), msgReq.getMsgType(), msgReq.getChatType());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 拉取消息1082。对于没有送达的消息会重新发送
     * 有两个阶段需要拉取消息
     * 1、用户登录时
     * 2、过一段时间，客户端自动发一个请求，拉取消息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param fetchMsgReq 拉取消息参数
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/messages", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String fetchMsg(@PathVariable("userId") Long userId, @RequestHeader("key") String key, @RequestBody FetchMsgReq fetchMsgReq) {
        Long purgeBefore = fetchMsgReq.getPurgeBefore() == null ? 0L : fetchMsgReq.getPurgeBefore();
        try {
            return ImAPI.fetchMsg(userId, key, purgeBefore);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新回话1083
     * @param userId 用户id
     * @param id 回话id
     * @param key 不羁旅行令牌
     * @param conversationReq 消息免打扰
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/conversations/{id:[0-9a-f]{24}}", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateConversation(@PathVariable("userId") Long userId, @PathVariable("id") String id, @RequestHeader("key") String key, @RequestBody ConversationReq conversationReq) {
        if(conversationReq.isMute() == null)
            return QinShihuangResult.getResult(ErrorCode.MUTE_NULL_1083);

        try {
            return ImAPI.updateConversation(userId, id, key, conversationReq.isMute());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得会话列表1084
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param conversationReq 会话id列表
     * @return 会话列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/conversations", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String getConversationsByIds(@PathVariable("userId") Long userId, @RequestHeader("key") String key, @RequestBody ConversationReq conversationReq) {
        if(conversationReq.getIds() == null)
            return QinShihuangResult.getResult(ErrorCode.IDLIST_NULL_1084);

        try {
            List<ObjectId> ids = new ArrayList<ObjectId>();
            for(String id : conversationReq.getIds()) {
                if(CommonAPI.isObjectId(id))
                    ids.add(new ObjectId(id));
                else
                    return QinShihuangResult.getResult(ErrorCode.ID_INVALID_1084);
            }
            return ImAPI.getConversationsByIds(userId, key, ids);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
