package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.im.ChatgroupBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.im.ChatgroupFormatter;
import com.bjlx.QinShihuang.core.formatter.im.PostFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.*;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.requestmodel.ChatgroupReq;
import com.bjlx.QinShihuang.requestmodel.PostReq;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.*;
import java.util.stream.Collectors;

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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1070);
            }
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            Chatgroup chatgroup = query.get();
            if(chatgroup == null) {
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1070);
            } else {
                return QinShihuangResult.ok(ChatgroupFormatter.getMapper().valueToTree(chatgroup));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
        try {
            Chatgroup chatgroup = query.get();
            if (chatgroup == null) {
                return null;
            } else {
                return chatgroup.getParticipants();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取得下一个userId
     * @return 用户id
     */
    public static long getNextChatgroupId() throws Exception {
        Query<Sequence> query = ds.createQuery(Sequence.class).field("column").equal(Sequence.fd_chatGroupId);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(Sequence.class).inc("count");
        try {
            Sequence ret = ds.findAndModify(query, ops, false, true);
            return ret.getCount();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 默认用户头像
     */
    public static ImageItem defaultGroupAvatar = new ImageItem("default_group_avatar.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_user_avatar.jpg", 100, 100, "jpg");

    /**
     * 创建聊天组
     * @param chatgroupReq 聊天组参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     * @throws Exception 异常
     */
    public static String createChatgroup(ChatgroupReq chatgroupReq, Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1068);
            }
            Long chatGroupId = getNextChatgroupId();
            String name = chatgroupReq.getName() == null ? String.format("不羁旅行%d", chatGroupId) : chatgroupReq.getName();

            Chatgroup chatgroup = new Chatgroup(chatGroupId, name, defaultGroupAvatar, userId, chatgroupReq.getMaxUsers(), chatgroupReq.isVisible());
            if(chatgroupReq.getGroupDesc() != null)
                chatgroup.setGroupDesc(chatgroupReq.getGroupDesc());
            if(chatgroupReq.getParticipants() != null && !chatgroupReq.getParticipants().isEmpty())
                chatgroup.setParticipants(chatgroupReq.getParticipants());
            if(chatgroupReq.getTags() != null && !chatgroupReq.getTags().isEmpty())
                chatgroup.setTags(chatgroupReq.getTags());
            ds.save(chatgroup);
            return QinShihuangResult.ok(ChatgroupFormatter.getMapper().valueToTree(chatgroup));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1069);
            }
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_creatorId).equal(userId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            UpdateOperations<Chatgroup> ops = ds.createUpdateOperations(Chatgroup.class);
            if(chatgroupReq.getAvatar() != null)
                ops.set(Chatgroup.fd_avatar, chatgroupReq.getAvatar());
            if(chatgroupReq.getGroupDesc() != null)
                ops.set(Chatgroup.fd_groupDesc, chatgroupReq.getGroupDesc());
            if(chatgroupReq.getMaxUsers() != null)
                ops.set(Chatgroup.fd_maxUsers, chatgroupReq.getMaxUsers());
            if(chatgroupReq.getName() != null)
                ops.set(Chatgroup.fd_name, chatgroupReq.getName());
            if(chatgroupReq.getTags() != null)
                ops.set(Chatgroup.fd_tags, chatgroupReq.getTags());
            if(chatgroupReq.isVisible() != null)
                ops.set(Chatgroup.fd_visible, chatgroupReq.isVisible());
            Chatgroup chatgroup = ds.findAndModify(query, ops, false);
            if(chatgroup == null)
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1069);
            else
                return QinShihuangResult.ok(ChatgroupFormatter.getMapper().valueToTree(chatgroup));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1071);
            }
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL)
                    .retrievedFields(true, Chatgroup.fd_creatorId, Chatgroup.fd_participants);
            Chatgroup chatgroup = query.get();
            if(chatgroup == null)
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1071);
            else {
                ObjectNode result = CommonAPI.mapper.createObjectNode();
                UserInfo creator = CommonAPI.getUserBasicById(chatgroup.getCreatorId());
                if(creator == null)
                    return QinShihuangResult.getResult(ErrorCode.CREATOR_NOT_EXIST_1071);
                else
                    result.set("creator", UserInfoBasicFormatter.getMapper().valueToTree(creator));
                if(chatgroup.getParticipants() != null && !chatgroup.getParticipants().isEmpty()) {
                    Set<String> retrievedFields = new HashSet<String>(Arrays.asList(UserInfo.fd_id, UserInfo.fd_userId, UserInfo.fd_nickName, UserInfo.fd_avatar));
                    Set<Long> participantIds = chatgroup.getParticipants().stream().collect(Collectors.toSet());
                    List<UserInfo> participants = CommonAPI.getUsersByIdList(participantIds, retrievedFields);
                    result.set("participants", UserInfoBasicFormatter.getMapper().valueToTree(participants));
                }
                return QinShihuangResult.ok(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1072);
            }
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            UpdateOperations<Chatgroup> ops = ds.createUpdateOperations(Chatgroup.class).addToSet(Chatgroup.fd_participants, memberId);
            Chatgroup chatgroup = ds.findAndModify(query, ops, false);
            Set<Long> memberIds = new HashSet<Long>();
            if(chatgroup == null)
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1072);
            else {
                memberIds.add(chatgroup.getCreatorId());
                if(chatgroup.getParticipants() != null && !chatgroup.getParticipants().isEmpty()) {
                    for (Long participant : chatgroup.getParticipants())
                        memberIds.add(participant);
                }
            }
            Map<Long, UserInfo> userInfoMap = CommonAPI.getUsersMapByIdList(new HashSet<Long>(Arrays.asList(userId, memberId)),
                    new HashSet<String>(Arrays.asList(UserInfo.fd_id, UserInfo.fd_userId, UserInfo.fd_nickName, UserInfo.fd_avatar)));
            UserInfo userInfo;
            UserInfo member;
            if(userInfoMap == null || userInfoMap.isEmpty())
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1072);
            else {
                userInfo = userInfoMap.get(userId);
                member = userInfoMap.get(memberId);
            }
            if(userInfo == null || member == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1072);

            // 发送一条tips消息
            List<String> clientIds = ImAPI.getClientIdsByUserIds(memberIds.stream().collect(Collectors.toList()));
            Content content = new Content();
            String tips = String.format("%s将%s添加到聊天组", userInfo.getNickName(), member.getNickName());
            content.setText(tips);
            Conversation conv = ImAPI.getConversation(null, userId, chatgroupId, Constant.GROUP_CHAT);
            Message msg = ImAPI.buildMessage(userId, userInfo.getNickName(), userInfo.getAvatar(), conv.getId(), content, chatgroupId, Constant.MOMENT_MSG, Constant.GROUP_CHAT);
            msg.setAbbrev(tips);
            ImAPI.sendGroupMsg(msg, clientIds);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1073);
            }
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            UpdateOperations<Chatgroup> ops = ds.createUpdateOperations(Chatgroup.class).removeAll(Chatgroup.fd_participants, memberId);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户聊天组列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组列表
     * @throws Exception 异常
     */
    public static String getUserChatgroups(Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1074);
            }

            List<CriteriaContainerImpl> criterias = new ArrayList<>();
            criterias.add(ds.createQuery(Chatgroup.class).criteria(Chatgroup.fd_creatorId).equal(userId));
            criterias.add(ds.createQuery(Chatgroup.class).criteria(Chatgroup.fd_participants).hasThisOne(userId));
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);

            query.or(criterias.toArray(new CriteriaContainerImpl[criterias.size()]));

            List<Chatgroup> chatgroups = query.asList();
            if(chatgroups == null || chatgroups.isEmpty())
                return QinShihuangResult.ok(ChatgroupBasicFormatter.getMapper().valueToTree(new ArrayList<Chatgroup>()));
            else
                return QinShihuangResult.ok(ChatgroupBasicFormatter.getMapper().valueToTree(chatgroups));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1075);
            }
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1075);
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            Chatgroup chatgroup = query.get();
            if(chatgroup == null)
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1075);
            Post post = new Post(postReq.getTitle(), postReq.getCover(), postReq.getSummary(), postReq.getContent(), author, chatgroupId);
            if(postReq.getImages() != null && !postReq.getImages().isEmpty())
                post.setImages(postReq.getImages());
            ds.save(post);
            // 发一条群消息，提示发布帖子

            Set<Long> memberIds = new HashSet<Long>();

            memberIds.add(chatgroup.getCreatorId());
            if(chatgroup.getParticipants() != null && !chatgroup.getParticipants().isEmpty()) {
                for (Long participant : chatgroup.getParticipants())
                    memberIds.add(participant);
            }

            // 发送一条tips消息
            List<String> clientIds = ImAPI.getClientIdsByUserIds(memberIds.stream().collect(Collectors.toList()));
            Content content = new Content();
            String tips = String.format("%s发布了帖子%s", author.getNickName(), post.getTitle());
            content.setText(tips);
            Conversation conv = ImAPI.getConversation(null, CommonAPI.systemUser.getUserId(), chatgroupId, Constant.GROUP_CHAT);
            Message msg = ImAPI.buildMessage(CommonAPI.systemUser.getUserId(), CommonAPI.systemUser.getNickName(), CommonAPI.systemUser.getAvatar(), conv.getId(), content, chatgroupId, Constant.MOMENT_MSG, Constant.GROUP_CHAT);
            msg.setAbbrev(tips);
            ImAPI.sendGroupMsg(msg, clientIds);

            return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(post));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1076);
            }
            Query<Post> query = ds.createQuery(Post.class).field(Post.fd_chatgroupId).equal(chatgroupId).field(Post.fd_status).equal(Constant.POST_NORMAL);
            List<Post> posts = query.asList();
            if(posts == null || posts.isEmpty())
                return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(new ArrayList<Post>()));
            else
                return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(posts));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新聊天组帖子
     * @param chatgroupId 聊天组id
     * @param postId 帖子id
     * @param postReq 帖子参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子信息
     * @throws Exception 异常
     */
    public static String updateChatgroupPost(Long chatgroupId, String postId, PostReq postReq, Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1106);
            }
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1106);
            Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
            Chatgroup chatgroup = query.get();
            if(chatgroup == null)
                return QinShihuangResult.getResult(ErrorCode.CHATGROUP_NOT_EXIST_1106);
            Query<Post> queryPost = ds.createQuery(Post.class).field(Post.fd_id).equal(new ObjectId(postId)).field(Post.fd_authorId).equal(userId).field(Post.fd_status).equal(Constant.POST_NORMAL);
            UpdateOperations<Post> ops = ds.createUpdateOperations(Post.class).set(Post.fd_updateTime, System.currentTimeMillis());
            if(postReq.getContent() != null)
                ops.set(Post.fd_content, postReq.getContent());
            if(postReq.getCover() != null)
                ops.set(Post.fd_cover, postReq.getCover());
            if(postReq.getImages() != null && !postReq.getImages().isEmpty())
                ops.set(Post.fd_images, postReq.getImages());
            if(postReq.getSummary() != null)
                ops.set(Post.fd_summary, postReq.getSummary());
            if(postReq.getTitle() != null)
                ops.set(Post.fd_title, postReq.getTitle());
            Post post = ds.findAndModify(queryPost, ops, false);
            if(post == null)
                return QinShihuangResult.getResult(ErrorCode.POST_NOT_EXIST_1106);

            // 发一条群消息，提示发布帖子
            Set<Long> memberIds = new HashSet<Long>();

            memberIds.add(chatgroup.getCreatorId());
            if(chatgroup.getParticipants() != null && !chatgroup.getParticipants().isEmpty()) {
                for (Long participant : chatgroup.getParticipants())
                    memberIds.add(participant);
            }

            // 发送一条tips消息
            List<String> clientIds = ImAPI.getClientIdsByUserIds(memberIds.stream().collect(Collectors.toList()));
            Content content = new Content();
            String tips = String.format("%s更新了帖子%s", author.getNickName(), post.getTitle());
            content.setText(tips);
            Conversation conv = ImAPI.getConversation(null, CommonAPI.systemUser.getUserId(), chatgroupId, Constant.GROUP_CHAT);
            Message msg = ImAPI.buildMessage(CommonAPI.systemUser.getUserId(), CommonAPI.systemUser.getNickName(), CommonAPI.systemUser.getAvatar(), conv.getId(), content, chatgroupId, Constant.MOMENT_MSG, Constant.GROUP_CHAT);
            msg.setAbbrev(tips);
            ImAPI.sendGroupMsg(msg, clientIds);

            return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(post));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1107);
            }

            Query<Post> query = ds.createQuery(Post.class).field(Post.fd_id).equal(new ObjectId(postId)).field(Post.fd_status).equal(Constant.POST_NORMAL);
            UpdateOperations<Post> ops = ds.createUpdateOperations(Post.class).set(Post.fd_status, Constant.POST_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得聊天组帖子详情
     * @param postId 帖子id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子详情
     * @throws Exception 异常
     */
    public static String getChatgroupPost(String postId, Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1108);
            }

            Query<Post> query = ds.createQuery(Post.class).field(Post.fd_id).equal(new ObjectId(postId)).field(Post.fd_status).equal(Constant.POST_NORMAL);
            Post post = query.get();
            if(post == null)
                return QinShihuangResult.getResult(ErrorCode.POST_NOT_EXIST_1108);
            else
                return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(post));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户帖子列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子列表
     * @throws Exception 异常
     */
    public static String getUserPosts(Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1109);
            }

            Query<Post> query = ds.createQuery(Post.class).field(Post.fd_authorId).equal(userId).field(Post.fd_status).equal(Constant.POST_NORMAL);
            List<Post> posts = query.asList();
            if(posts == null || !posts.isEmpty())
                return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(new ArrayList<Post>()));
            else
                return QinShihuangResult.ok(PostFormatter.getMapper().valueToTree(posts));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
