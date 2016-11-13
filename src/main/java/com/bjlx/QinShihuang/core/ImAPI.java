package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.im.MessageFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Content;
import com.bjlx.QinShihuang.model.im.Conversation;
import com.bjlx.QinShihuang.model.im.Message;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 即时消息核心实现
 * Created by xiaozhi on 2016/11/11.
 */
public class ImAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得下一个msgId
     * @return 消息id
     */
    public static long getNextMsgId() throws Exception {
        Query<Sequence> query = ds.createQuery(Sequence.class).field("column").equal(Sequence.fd_msgId);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(Sequence.class).inc("count");
        try {
            Sequence ret = ds.findAndModify(query, ops, false, true);
            return ret.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 生成消息的摘要文本。
     * @param senderNickName 发送者昵称
     * @param msgType 消息类型
     * @param content 消息内容
     * @return 摘要
     */
    public static String buildMessageAbbrev(String senderNickName, Integer msgType, Content content) {

        String abbrev = "";
        String nickName = senderNickName == null ? "不羁人" : senderNickName;
        switch (msgType) {
            case Constant.TEXT_MSG :
            case Constant.NOTICE_MSG :
            case Constant.TIP_MSG :
                if (content.getText().length() >= Constant.ABBREV_MAX_LEN)
                    abbrev = content.getText().substring(0, Constant.ABBREV_MAX_LEN) + "...";
                else
                    abbrev = content.getText();
                break;
            case Constant.IMAGE_MSG : abbrev = "发来了一张照片"; break;
            case Constant.AUDIO_MSG : abbrev = "发来了一段语音"; break;
            case Constant.LOCATION_MSG : abbrev = "发来了一个位置"; break;
            case Constant.GUIDE_MSG : abbrev = "发来了一篇攻略"; break;
            case Constant.TRAVEL_NOTE_MSG : abbrev = "发来了一篇游记"; break;
            case Constant.VIEWSPOT_MSG : abbrev = "发来了一段景点介绍"; break;
            case Constant.RESTAURANT_MSG : abbrev = "发来了一个餐厅"; break;
            case Constant.SHOPPING_MSG : abbrev = "发来了一个扫货好去处"; break;
            case Constant.HOTEL_MSG : abbrev = "发来了一个酒店"; break;
            case Constant.COMMODITY_MSG : abbrev = "发来了一个商品"; break;
            case Constant.ORDER_MSG : abbrev = "发来了一条订单动态"; break;
            case Constant.IDCARD_MSG : abbrev = "发来了一个位置"; break;
            case Constant.FOLLOWING_MSG : abbrev = "关注了您"; break;
        }

        return String.format("%s%s", nickName, abbrev);
    }

    /**
     * 构建消息体
     * @param senderId 发送者id
     * @param senderNickName 发送者昵称
     * @param senderAvatar 发送者头像
     * @param convId 回话id
     * @param content 消息内容
     * @param receiverId 接收者id
     * @param msgType 消息类型
     * @param chatType 聊天类型
     * @return 消息体
     * @throws Exception 异常
     */
    public static Message buildMessage(Long senderId, String senderNickName, ImageItem senderAvatar, ObjectId convId, Content content, Long receiverId, Integer msgType, Integer chatType) throws Exception {
        try {
            Long msgId = getNextMsgId();
            return new Message(convId, msgId, content, senderId, receiverId, senderNickName, senderAvatar, msgType, buildMessageAbbrev(senderNickName, msgType, content), chatType);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据convId取得回话实体
     * convId为空，则新建一个回话，返回回话实体
     * convId不为空，检查convId是否存在, 不存在则返回null，存在则返回回话实体
     * @param convId 回话id
     * @param senderId 发送者id
     * @param receiverId 接收者id
     * @param chatType 聊天类型
     * @return 回话实体
     * @throws Exception 异常
     */
    public static Conversation getConversation(String convId, Long senderId, Long receiverId, Integer chatType) throws Exception {
        if(convId == null) {
            String conversationId;
            if(chatType == Constant.SINGLE_CHAT) {
                Long min = senderId < receiverId ? senderId : receiverId;
                Long max = senderId >= receiverId ? senderId : receiverId;
                conversationId = String.format("%d.%d", min, max);
            } else {
                conversationId = String.format("%d", receiverId);
            }
            Conversation conversation = new Conversation(new ObjectId(), conversationId, chatType);
            try {
                ds.save(conversation);
                return conversation;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            ObjectId id = new ObjectId(convId);
            Query<Conversation> query = ds.createQuery(Conversation.class).field(Conversation.fd_id).equal(id);
            try {
                Conversation conversation = query.get();
                if (conversation == null)
                    return null;
                else
                    return conversation;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }


    public final static String appId = "KM4CsH2Yam6gcPzLUJaUh8";
//    public final static String appSecret = "Mk5CgJ7xC18HNlOuATO026";
    public final static String masterSecret = "Wl4SJGq5kuABxz7PGg42U3";
    public final static String appKey = "x0Mte9wA5I9X0fiIMQD1x4";

    public final static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public final static IGtPush push = new IGtPush(host, appKey, masterSecret);

    /**
     * 发送单个消息
     * @param msg 消息实体
     * @param clientId 接收消息的客户端
     * @throws Exception 异常
     */
    public static void sendSingleMsg(Message msg, String clientId) throws Exception {
        TransmissionTemplate template;
        try {
            template = getTransmissionTemplate(MessageFormatter.getMapper().writeValueAsString(msg), msg.getAbbrev());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        //target.setAlias(Alias);
        IPushResult ret;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }

    }

    /**
     * 取得推送模板
     * @param contents 消息内容
     * @param abbrev 消息摘要
     * @return 推送模板
     */
    public static TransmissionTemplate getTransmissionTemplate(String contents, String abbrev) {
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        // 消息内容
        template.setTransmissionContent(contents);

        // iOS需要设置
        APNPayload payload = new APNPayload();
        // payload.setBadge(1);  //将应用icon上显示的数字设为1
        // 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，如10，效果和setBadge一样，
        // 应用icon上显示指定数字。不能和setBadge同时使用
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("");
        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(abbrev));
        //字典模式使用下者
        //payload.setAlertMsg(getDictionaryAlertMsg());
        template.setAPNInfo(payload);
        return template;
    }

    /**
     * 发送消息至客户端列表
     * @param msg 消息实体
     * @param clientIds 客户端列表
     * @throws Exception 异常
     */
    public static void sendGroupMsg(Message msg, List<String> clientIds) throws Exception {
        // 通知透传模板
        TransmissionTemplate template;
        try {
            template = getTransmissionTemplate(MessageFormatter.getMapper().writeValueAsString(msg), msg.getAbbrev());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        message.setPushNetWorkType(0);

        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        List<Target> targets = new ArrayList<Target>();
        for(String clientId : clientIds) {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(clientId);
            targets.add(target);
        }
        IPushResult ret;
        try {
            ret = push.pushMessageToList(taskId, targets);
        } catch (RequestException e) {
            e.printStackTrace();
            throw e;
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    /**
     * 发送透传消息
     * @param msg 消息实体
     * @param clientIds 客户端列表
     * @throws Exception 异常
     */
    public static void sendTransmissionMsg(Message msg, List<String> clientIds) throws Exception {
        if (clientIds == null || clientIds.isEmpty())
            return;
        try {
            if (clientIds.size() == 1)
                sendSingleMsg(msg, clientIds.get(0));
            else
                sendGroupMsg(msg, clientIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据用户id列表取得个推客户端id列表
     * @param userIds 用户id列表
     * @return 个推客户端id列表
     * @throws Exception 异常
     */
    public static List<String> getClientIdsByUserIds(List<Long> userIds) throws Exception {
        Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).in(userIds).retrievedFields(true, UserInfo.fd_clientId);
        try {
            List<UserInfo> userInfos = query.asList();
            List<String> clientIds = new ArrayList<String>();
            if(userInfos != null) {
                for (UserInfo userInfo : userInfos) {
                    if(userInfo.getClientId() != null)
                        clientIds.add(userInfo.getClientId());
                }
            }
            return clientIds;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据消息内容, 获得摘要. 这是为Conversation的lastMsgContent准备的
     * @param msg 消息实体
     * @return 最后一条消息内容
     */
    public static String getLastMsgContent(Message msg) {
        String abbrev = "";
        switch (msg.getMsgType()) {
            case Constant.TEXT_MSG :
            case Constant.NOTICE_MSG :
            case Constant.TIP_MSG :
                if (msg.getContent().getText().length() >= Constant.ABBREV_MAX_LEN)
                    abbrev = msg.getContent().getText().substring(0, Constant.ABBREV_MAX_LEN) + "...";
                else
                    abbrev = msg.getContent().getText();
                break;
            case Constant.IMAGE_MSG : abbrev = "[照片]"; break;
            case Constant.AUDIO_MSG : abbrev = "[音频]"; break;
            case Constant.LOCATION_MSG : abbrev = "[位置]"; break;
            case Constant.GUIDE_MSG : abbrev = "[攻略]"; break;
            case Constant.TRAVEL_NOTE_MSG : abbrev = "[游记]"; break;
            case Constant.VIEWSPOT_MSG : abbrev = "[景点]"; break;
            case Constant.RESTAURANT_MSG : abbrev = "[餐厅]"; break;
            case Constant.SHOPPING_MSG : abbrev = "[购物]"; break;
            case Constant.HOTEL_MSG : abbrev = "[酒店]"; break;
            case Constant.COMMODITY_MSG : abbrev = "[商品]"; break;
            case Constant.ORDER_MSG : abbrev = "[订单动态]"; break;
            case Constant.IDCARD_MSG : abbrev = "[名片]"; break;
            case Constant.FOLLOWING_MSG : abbrev = "[关注]"; break;
        }
        return abbrev;
    }

    /**
     * 发送消息至会话
     * @param msg 消息实体
     * @return 消息实体
     */
    public static Message sendMsg2Conv(Message msg) throws Exception {
        try {
            List<Long> receiverIds;
            if(msg.getChatType() == Constant.GROUP_CHAT) {
                List<Long> participants = ChatgroupAPI.getParticipantsById(msg.getReceiverId());
                if(participants == null)
                    receiverIds = new ArrayList<Long>();
                else
                    receiverIds = participants.stream().filter(item -> !item.equals(msg.getSenderId())).collect(Collectors.toList());
                msg.setReceiverIdList(receiverIds);
            } else {
                receiverIds = new ArrayList<Long>();
                receiverIds.add(msg.getReceiverId());
            }
            // 保存消息
            ds.save(msg);
            // 发送消息
            List<String> clientIds = getClientIdsByUserIds(receiverIds);
            sendTransmissionMsg(msg, clientIds);
            // 更新conversation
            Query<Conversation> query = ds.createQuery(Conversation.class).field(Conversation.fd_id).equal(msg.getConvId());
            UpdateOperations<Conversation> ops = ds.createUpdateOperations(Conversation.class)
                    .inc(Conversation.fd_msgCounter).set(Conversation.fd_updateTime, System.currentTimeMillis())
                    .set(Conversation.fd_lastMsgContent, getLastMsgContent(msg));
            ds.updateFirst(query, ops);

            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 发送消息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param convId 会话id
     * @param content 消息内容
     * @param receiverId 接收者id
     * @param msgType 消息类型
     * @param chatType 聊天类型
     * @return 消息实体
     * @throws Exception 异常
     */
    public static String sendMsg(Long userId, String key, String id, String convId, Content content, Long receiverId, Integer msgType, Integer chatType) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1064);
            }
            // 根据userId取得用户信息
            Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            UserInfo userInfo = query.get();
            if(userInfo == null) {
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1064);
            }

            Conversation conv = getConversation(convId, userId, receiverId, chatType);
            if (conv == null) {
                return QinShihuangResult.getResult(ErrorCode.CONVID_INVALID_1064);
            }
            Message msg;
            if(id != null) {
                Query<Message> queryMsg = ds.createQuery(Message.class).field(Message.fd_id).equal(new ObjectId(id));
                msg = queryMsg.get();
                if(msg == null)
                    return QinShihuangResult.getResult(ErrorCode.ID_INVALID_1064);
            } else {
                msg = buildMessage(userInfo.getUserId(), userInfo.getNickName(), userInfo.getAvatar(), conv.getId(), content, receiverId, msgType, chatType);
            }

            Message result = sendMsg2Conv(msg);
            return QinShihuangResult.ok(MessageFormatter.getMapper().valueToTree(result));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
