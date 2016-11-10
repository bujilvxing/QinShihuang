package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 社交核心实现
 * Created by pengyt on 2016/11/8.
 */
public class SocialAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 关注用户
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param followingId 待关注用户id
     * @return 结果
     * @throws Exception 异常
     */
    public static String following(Long userId, String key, Long followingId) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1055);
            Long userA = userId < followingId ? userId : followingId;
            Long userB = userId >= followingId ? userId : followingId;
            Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                    .field(Relationship.fd_userB).equal(userB);
            UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userA, userA)
                    .set(Relationship.fd_userB, userB);
            if(userId < followingId)
                ops.set(Relationship.fd_followingB, true);
            else
                ops.set(Relationship.fd_followingA, true);
            ds.updateFirst(query, ops, true);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取消关注用户
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param followingId 待取消关注用户id
     * @return 结果
     * @throws Exception 异常
     */
    public static String cancelFollowing(Long userId, String key, Long followingId) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1056);
            Long userA = userId < followingId ? userId : followingId;
            Long userB = userId >= followingId ? userId : followingId;
            Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                    .field(Relationship.fd_userB).equal(userB);
            UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userA, userA)
                    .set(Relationship.fd_userB, userB);
            if(userId < followingId)
                ops.set(Relationship.fd_followingB, false);
            else
                ops.set(Relationship.fd_followingA, false);
            ds.updateFirst(query, ops, true);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据用户id列表取得用户信息列表
     * @param userIds 用户id列表
     * @param retrievedFieldsSet 返回字段列表
     * @return 用户id与用户信息映射
     * @throws Exception 异常
     */
    public static Map<Long, UserInfo> getUsersByIdList(Set<Long> userIds, Set<String> retrievedFieldsSet) throws Exception {
        if (userIds == null || userIds.isEmpty()) {
            return new HashMap<Long, UserInfo>();
        } else {
            Query<UserInfo> query = ds.createQuery(UserInfo.class);
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
     * 取得好友列表1057
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第offset个文档开始取
     * @param limit 取limit个文档
     * @return 好友信息列表
     * @throws Exception 异常
     */
    public static String getContacts(Long userId, String key, Integer offset, Integer limit) throws Exception {
        // 校验用户登录
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1057);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List<CriteriaContainerImpl> criterias = new ArrayList<>();
        criterias.add(ds.createQuery(Relationship.class).criteria(Relationship.fd_userA).equal(userId));
        criterias.add(ds.createQuery(Relationship.class).criteria(Relationship.fd_userB).equal(userId));
        Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_followingA).equal(true).field(Relationship.fd_followingB).equal(true);
        query.or(criterias.toArray(new CriteriaContainerImpl[criterias.size()]));
        query.offset(offset).limit(limit);
        List<Relationship> relationships = null;
        try {
            relationships = query.asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        if(relationships == null) {
            return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(new ArrayList<UserInfo>()));
        }
        // 好友id集合
        Set<Long> contactIds = new HashSet<Long>();
        for(Relationship relationship : relationships) {
            contactIds.add(relationship.getUserA());
            contactIds.add(relationship.getUserB());
        }
        contactIds.remove(userId);

        Set<String> retrievedFieldsSet = new HashSet<String>();
        retrievedFieldsSet.add(UserInfo.fd_nickName);
        retrievedFieldsSet.add(UserInfo.fd_avatar);
        Map<Long, UserInfo> userInfoMap = null;
        try {
            userInfoMap = getUsersByIdList(contactIds, retrievedFieldsSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for(Relationship relationship : relationships) {
            if(relationship.getUserA() == userId) {
                if(relationship.getMemoB() != null) {
                    userInfoMap.get(relationship.getUserB()).setMemo(relationship.getMemoB());
                }
                userInfos.add(userInfoMap.get(relationship.getUserB()));
            } else {
                if(relationship.getMemoA() != null) {
                    userInfoMap.get(relationship.getUserA()).setMemo(relationship.getMemoA());
                }
                userInfos.add(userInfoMap.get(relationship.getUserA()));
            }
        }

        return QinShihuangResult.ok(UserInfoBasicFormatter.getMapper().valueToTree(userInfos));
    }
}
