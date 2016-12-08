package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.im.ChatgroupFormatter;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.requestmodel.ChatgroupReq;
import com.bjlx.QinShihuang.requestmodel.PostReq;
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

    /**
     * 创建聊天组
     * @param chatgroupReq 聊天组参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     * @throws Exception 异常
     */
    public static String createChatgroup(ChatgroupReq chatgroupReq, Long userId, String key) throws Exception {

        return null;
    }

    /**
     * 更新聊天组
     * @param chatgroupId 聊天组id
     * @param chatgroupReq 聊天组参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     * @throws Exception 异常
     */
    public static String updateChatgroup(Long chatgroupId, ChatgroupReq chatgroupReq, Long userId, String key) throws Exception {

        return null;
    }

    /**
     * 取得聊天组成员列表
     * @param chatgroupId 聊天组id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组成员列表
     * @throws Exception 异常
     */
    public static String getChatgroupMembers(Long chatgroupId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 添加聊天组成员
     * @param chatgroupId 聊天组id
     * @param memberId 成员id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     * @throws Exception 异常
     */
    public static String addChatgroupMember(Long chatgroupId, Long memberId, Long userId, String key) throws Exception {

        // 发送一条tips消息

        return null;
    }

    /**
     * 移除聊天组成员
     * @param chatgroupId 聊天组id
     * @param memberId 成员id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     * @throws Exception 异常
     */
    public static String removeChatgroupMember(Long chatgroupId, Long memberId, Long userId, String key) throws Exception {


        return null;
    }

    /**
     * 取得用户聊天组列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组列表
     * @throws Exception 异常
     */
    public static String getUserChatgroups(Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 添加聊天组帖子
     * @param chatgroupId 聊天组id
     * @param postReq 帖子参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子信息
     * @throws Exception 异常
     */
    public static String addChatgroupPost(Long chatgroupId, PostReq postReq, Long userId, String key) throws Exception {
        // 发一条群消息，提示发布帖子
        return null;
    }

    /**
     * 取得聊天组帖子列表
     * @param chatgroupId 聊天组id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子列表
     * @throws Exception 异常
     */
    public static String getChatgroupPosts(Long chatgroupId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 更新聊天组帖子
     * @param postId 帖子id
     * @param postReq 帖子参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子信息
     * @throws Exception 异常
     */
    public static String updateChatgroupPost(String postId, PostReq postReq, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 删除聊天组帖子
     * @param postId 帖子id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String removeChatgroupPost(String postId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 取得聊天组帖子详情
     * @param chatgroupId 聊天组id
     * @param postId 帖子id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子详情
     * @throws Exception 异常
     */
    public static String getChatgroupPost(String postId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 取得用户帖子列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子列表
     * @throws Exception 异常
     */
    public static String getUserPosts(Long userId, String key) throws Exception {
        return null;
    }
}
