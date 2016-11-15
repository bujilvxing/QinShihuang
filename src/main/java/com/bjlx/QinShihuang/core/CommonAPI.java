package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.account.Credential;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
}
