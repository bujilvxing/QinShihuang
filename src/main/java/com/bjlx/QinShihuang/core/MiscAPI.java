package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.im.ChatgroupBasicFormatter;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.Application;
import com.bjlx.QinShihuang.model.misc.Feedback;
import com.bjlx.QinShihuang.utils.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他控制器
 * Created by xiaozhi on 2016/11/8.
 */
public class MiscAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 申请商家
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param tel 手机号
     * @return 结果信息
     * @throws Exception 异常
     */
    public static String applySeller(Long userId, String key, String tel) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1012);
            }

            Query<Application> query = ds.createQuery(Application.class).field(Application.fd_number).equal(tel);
            UpdateOperations<Application> ops = ds.createUpdateOperations(Application.class).set(Application.fd_id, new ObjectId())
                    .set(Application.fd_tel, new PhoneNumber(86, tel)).set(Application.fd_userId, userId).set(Application.fd_createTime, System.currentTimeMillis());
            ds.updateFirst(query, ops, true);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 用户反馈
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param content 反馈内容
     * @param origin 从哪个App反馈过来的, 例如：不羁旅行
     * @return 结果
     * @throws Exception 异常
     */
    public static String feedback(Long userId, String key, String content, String origin) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1013);
            }
            Feedback feedback = new Feedback(userId, content, System.currentTimeMillis(), origin);
            ds.save(feedback);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索用户
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 查询关键字
     * @return 用户信息
     */
    public static String searchUser(Long userId, String key, String query) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1100);
            }
            Query<UserInfo> queryUser = ds.createQuery(UserInfo.class).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);

            if(CommonUtil.isEmail(query)) {
                UserInfo userInfo = queryUser.field(UserInfo.fd_email).equal(query).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
                else
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1100);
            }

            if(CommonUtil.isTelLegal(query)) {
                UserInfo userInfo = queryUser.field(UserInfo.fd_number).equal(query).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
            }

            if(CommonUtil.isLongDigit(query)) {
                Long uid = Long.getLong(query);
                UserInfo userInfo = queryUser.field(UserInfo.fd_userId).equal(uid).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
                else
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1100);
            } else {
                return QinShihuangResult.getResult(ErrorCode.QUERY_INVALID_1100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索聊天组
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 查询关键字
     * @return 聊天组信息
     */
    public static String searchChatgroup(Long userId, String key, String query) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1101);
            }


            List<Chatgroup> chatgroups = new ArrayList<Chatgroup>();

            if(CommonUtil.isLongDigit(query)) {
                Long chatgroupId = Long.getLong(query);
                Chatgroup chatgroup = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).get();
                if(chatgroup != null)
                    chatgroups.add(chatgroup);
            }
            List<Chatgroup> chatgroupList = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_name).startsWithIgnoreCase(query).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).asList();
            if(chatgroupList != null) {
                for(Chatgroup chatgroup : chatgroupList)
                    chatgroups.add(chatgroup);
            }
            return QinShihuangResult.ok(ChatgroupBasicFormatter.getMapper().valueToTree(chatgroups));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
