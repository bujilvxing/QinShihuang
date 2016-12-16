package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.account.Credential;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * 公共方法
 * Created by xiaozhi on 2016/11/8.
 */
public class CommonAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 默认用户头像
     */
    private static ImageItem defaultUserAvatar = new ImageItem("default_user_avatar.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_user_avatar.jpg", 100, 100, "jpg");

    /**
     * 系统用户
     */
    public final static UserInfo systemUser = new UserInfo(0L, "system-bjlx", defaultUserAvatar);

    /**
     * 不羁旅行令牌是否合法
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 是否合法
     * @throws Exception 异常
     */
    public static boolean checkKeyValid(Long userId, String key) throws Exception {
        Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId).field(Credential.fd_key).equal(key);
        try {
            return queryCredential.get() != null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 检查单个用户是否存在
     * @param userId 用户id
     * @return 是否存在
     * @throws Exception 异常
     */
    public static boolean checkUserExistById(Long userId) throws Exception {
        Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL).retrievedFields(true, UserInfo.fd_id);
        try {
            return query.get() != null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 检查单个群组是否存在
     * @param chatgroupId 群组id
     * @return 是否存在
     * @throws Exception 异常
     */
    public static boolean checkChatgroupExistById(Long chatgroupId) throws Exception {
        Query<Chatgroup> query = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId)
                .field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).retrievedFields(true, Chatgroup.fd_chatGroupId);
        try {
            return query.get() != null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取得用户基本信息
     * @param userId 用户id
     * @return 用户基本信息
     * @throws Exception 异常
     */
    public static UserInfo getUserBasicById(Long userId) throws Exception {
        Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL)
                .retrievedFields(true, UserInfo.fd_id, UserInfo.fd_userId, UserInfo.fd_nickName, UserInfo.fd_avatar);
        try {
            return query.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public static UserInfo getUserBasicWithMomentById(Long userId) throws Exception {
        Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL)
                .retrievedFields(true, UserInfo.fd_id, UserInfo.fd_userId, UserInfo.fd_nickName, UserInfo.fd_avatar, UserInfo.fd_moment);
        try {
            return query.get();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 是否ObjectId
     * @param id ObjectId字符串
     * @return 是否ObjectId
     * @throws PatternSyntaxException 模式匹配异常
     */
    public static boolean isObjectId(String id) throws PatternSyntaxException {
        String regExp = "^\\[0-9a-f]{24}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(id);
        return m.matches();
    }

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 根据用户id列表取得用户信息列表
     * @param userIds 用户id列表
     * @param retrievedFieldsSet 返回字段列表
     * @return 用户id与用户信息映射
     * @throws Exception 异常
     */
    public static Map<Long, UserInfo> getUsersMapByIdList(Set<Long> userIds, Set<String> retrievedFieldsSet) throws Exception {
        if (userIds == null || userIds.isEmpty()) {
            return new HashMap<Long, UserInfo>();
        } else {
            Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            int size = userIds.size();
            switch (size) {
                case 1 : query.field(UserInfo.fd_userId).equal(userIds.iterator().next()); break;
                default: query.field(UserInfo.fd_userId).in(userIds);
            }

            /**
             * 返回字段必须含有id和userId
             */
            retrievedFieldsSet.add(UserInfo.fd_id);
            retrievedFieldsSet.add(UserInfo.fd_userId);
            String[] retrievedFields = retrievedFieldsSet.toArray(new String[retrievedFieldsSet.size()]);
            query.retrievedFields(true, retrievedFields);
            try {
                List<UserInfo> userInfos = query.asList();
                if (userInfos == null)
                    return new HashMap<Long, UserInfo>();
                else
                    return userInfos.stream().collect(Collectors.toMap(UserInfo::getUserId, Function.identity()));
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * 根据用户id列表取得用户信息列表
     * @param userIds 用户id列表
     * @param retrievedFieldsSet 返回字段列表
     * @return 用户信息列表
     * @throws Exception 异常
     */
    public static List<UserInfo> getUsersByIdList(Set<Long> userIds, Set<String> retrievedFieldsSet) throws Exception {
        if (userIds == null || userIds.isEmpty()) {
            return new ArrayList<UserInfo>();
        } else {
            Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            int size = userIds.size();
            switch (size) {
                case 1 : query.field(UserInfo.fd_userId).equal(userIds.iterator().next()); break;
                default: query.field(UserInfo.fd_userId).in(userIds);
            }

            /**
             * 返回字段必须含有id和userId
             */
            retrievedFieldsSet.add(UserInfo.fd_id);
            retrievedFieldsSet.add(UserInfo.fd_userId);
            String[] retrievedFields = retrievedFieldsSet.toArray(new String[retrievedFieldsSet.size()]);
            query.retrievedFields(true, retrievedFields);
            try {
                List<UserInfo> userInfos = query.asList();
                if (userInfos == null)
                    return new ArrayList<UserInfo>();
                else
                    return userInfos;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
