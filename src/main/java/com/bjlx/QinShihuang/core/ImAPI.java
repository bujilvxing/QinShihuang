package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.im.Content;
import com.bjlx.QinShihuang.model.im.Message;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

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
            throw e;
        }
    }


    /**
     * 生成消息的摘要文本。可以为None
     * @return
     */
    public static String buildMessageAbbrev(Message msg) {

        switch (msg.getMsgType()) {
            case Constant.TEXT_MSG : break;
            case Constant.IMAGE_MSG : break;
            case Constant.AUDIO_MSG : break;
            case Constant.LOCATION_MSG : break;
            case Constant.GUIDE_MSG : break;
            case Constant.TRAVEL_NOTE_MSG : break;
            case Constant.VIEWSPOT_MSG : break;
            case Constant.RESTAURANT_MSG : break;
            case Constant.SHOPPING_MSG : break;
            case Constant.HOTEL_MSG : break;
            case Constant.COMMODITY_MSG : break;
            case Constant.ORDER_MSG : break;
            case Constant.IDCARD_MSG : break;
            case Constant.FOLLOWING_MSG : break;
            case Constant.TIP_MSG : break;
            case Constant.NOTICE_MSG : break;

        }
//                msg.msgType match {
//            case item if item == TEXT.id =>
//                val maxLen = 16
//                // 截断后的消息
//                val abbrev = if (msg.contents.length >= maxLen)
//                msg.contents.take(maxLen) + "..."
//            else
//                msg.contents
//                Some(s": $abbrev")
//            case item if item == IMAGE.id =>
//                Some("发来了一张照片")
//            case item if item == AUDIO.id =>
//                Some("发来了一段语音")
//            case item if item == LOCATION.id =>
//                Some("发来了一个位置")
//            case item if item == GUIDE.id =>
//                Some("发来了一篇攻略")
//            case item if item == TRAVEL_NOTE.id =>
//                Some("发来了一篇游记")
//            case item if item == CITY_POI.id =>
//                Some("发来了一段目的地介绍")
//            case item if item == SPOT.id =>
//                Some("发来了一段景点介绍")
//            case item if item == RESTAURANT.id =>
//                Some("发来了一个餐厅")
//            case item if item == SHOPPING.id =>
//                Some("发来了一个扫货好去处")
//            case item if item == HOTEL.id =>
//                Some("发来了一个酒店")
//            case item if item == COMMODITY.id =>
//                Some("发来了一个商品")
//            case item if item == ORDER.id =>
//                Some("发来了一条订单动态")
//            case _ =>
//                None
//        }
//
//        val abbrev = tailOpt map (tail =>
//                FinagleCore.getUserById(msg.senderId) map (v => (v map (_.nickName) getOrElse "神秘人") + tail))
//
//        abbrev map (future => {
//                future map (s => {
//                val r: Option[String] = Some(s)
//        r
//        })
//        }) getOrElse Future(None)
        return null;
    }

    public static Message buildMessage(Long senderId, String senderNickName, ImageItem senderAvatar, ObjectId convId, Content content, Long receiverId, Integer msgType, Integer chatType) throws Exception {
//        try {
//            Long msgId = getNextMsgId();
//            Message msg = new Message(convId, msgId, content, senderId, receiverId, senderNickName, senderAvatar, msgType, abbrev, chatType);
//        } catch (Exception e) {
//
//        }

//        for {
//            msg <- Future {
//                msgType match {
//                    case _ => Message(msgType, contents, cid, msgId.get, receiver, sender, chatType)
//                }
//            }
//            optAbbrev <- buildMessageAbbrev(msg)
//        } yield {
//            msg.abbrev = optAbbrev.orNull
//            msg
//        }
        return null;
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
    public static String sendMsg(Long userId, String key, String convId, Content content, Long receiverId, Integer msgType, Integer chatType) throws Exception {

        String abbrev;
        return null;
    }
}
