package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.im.ChatgroupFormatter;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * 群组核心实现
 * Created by pengyt on 2016/11/13.
 */
public class ChatgroupAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得群组信息
     * @param chatgroupId 群组id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 群组信息
     * @throws Exception 异常
     */
    public static String getChatgroup(Long chatgroupId, Long userId, String key) throws Exception {
        if(CommonAPI.checkKeyValid(userId, key)) {
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            Chatgroup chatgroup = query.get();
            if(chatgroup == null) {
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1070);
            } else {
                return QinShihuangResult.ok(ChatgroupFormatter.getMapper().valueToTree(chatgroup));
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1070);
        }
    }

    /**
     * 取得群成员id列表
     * @param chatgroupId 群组id
     * @return 群成员id列表
     * @throws Exception 异常
     */
    public static List<Long> getParticipantsById(Long chatgroupId) throws Exception {
        Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId)
                .field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).retrievedFields(true, Chatgroup.fd_participants);
        Chatgroup chatgroup = query.get();
        if(chatgroup == null) {
            return null;
        } else {
            return chatgroup.getParticipants();
        }
    }
}
