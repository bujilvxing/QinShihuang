package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.im.ChatgroupFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.model.misc.Token;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;


import java.util.ArrayList;
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
     * 默认群组头像
     */
    public static ImageItem defaultGroupAvatar = new ImageItem("default_group_avatar.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_group_avatar.jpg", 100, 100, "jpg");

    /**
     * 默认群组成员，应默认为创建人ID
     */
    public static List<Long> defaultparticipants = new ArrayList<Long>();

    /**
     * 默认群组大小，是否需要get得到
     */
    public static Integer maxusers = 250;

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
     * 取得下一个chatGroupId
     * @return 群组id
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
     * 创建群组
     * @param name 群组名称
     * @param token 令牌
     * @param creator 创建用户ID
     * @return 群组信息
     * @throws Exception 异常
     */
    public static String creatChatGroup(String name, String token, Long creator) throws Exception {
        try {
            // 检验群组名称是否已存在
            if(checkChatgroupExist(name)){
                return QinShihuangResult.getResult(ErrorCode.GROUP_EXIST_106904);
            }
            // 检验token的合法性
            if(!checkTokenValid(token)) {
                return QinShihuangResult.getResult(ErrorCode.TOKEN_INVALID_106905);
            }
            // 查询创建用户是否存在
            Query<UserInfo> query = ds.createQuery(UserInfo.class);
            query.field(UserInfo.fd_userId).equal(creator).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            UserInfo userInfo = null;
            try {
                userInfo = query.get();
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
            if(userInfo == null)
                return QinShihuangResult.getResult(ErrorCode.CREATOR_NOT_EXIST_106906);

            //检查用户是否登录
            if(!CommonAPI.checkKeyValid(creator, token))
                return QinShihuangResult.getResult(ErrorCode.CREATOR_NOT_LOGIN_106907);



        } catch(Exception e) {
            throw e;
        }


        // 获取群组id
        long chatgroupId = getNextChatgroupId();
        Chatgroup chatgroup = null;
        String nickName = String.format("不羁群%d", chatgroupId);

        chatgroup = new Chatgroup(chatgroupId, name, defaultGroupAvatar, creator, defaultparticipants, maxusers);
//  chatgroup.setParticipants(creator);


        try {
            // 返回群组信息
            ds.save(chatgroup);
            return QinShihuangResult.ok(ChatgroupFormatter.getMapper().valueToTree(chatgroup));
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 检验群组是否存在
     * @param name 群组名称
     * @return true表示存在，false表示不存在
     */
    public static boolean checkChatgroupExist(String name) throws Exception {
        Query<Chatgroup> queryChatgroup = ds.createQuery(Chatgroup.class);
        queryChatgroup.field(Chatgroup.fd_name).equal(name).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL);
        try {
            return queryChatgroup.get() != null;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 检验令牌的合法性
     * 6分钟有效期
     * 1、令牌未过期
     * 2、令牌正确
     * @param token 令牌
     * @return 是否合法
     * @throws Exception 数据库异常
     */
    private static boolean checkTokenValid(String token) throws Exception {
        long currentTime = System.currentTimeMillis();
        Query<Token> query = ds.createQuery(Token.class).field(Token.fd_token).equal(token).field(Token.fd_expireTime).greaterThanOrEq(currentTime);

        try {
            return query.get() != null;
        } catch (Exception e) {
            throw e;
        }
    }









}