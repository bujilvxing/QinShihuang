package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.utils.Constant;
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
            // 校验用户是否存在
            if(CommonAPI.checkUserExistById(followingId)) {
                Long userA = userId < followingId ? userId : followingId;
                Long userB = userId >= followingId ? userId : followingId;
                Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                        .field(Relationship.fd_userB).equal(userB);
                UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userA, userA)
                        .set(Relationship.fd_userB, userB);
                if (userId < followingId)
                    ops.set(Relationship.fd_followingB, true);
                else
                    ops.set(Relationship.fd_followingA, true);
                ds.updateFirst(query, ops, true);
                // TODO 发送一条消息
                return QinShihuangResult.ok();
            } else {
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1055);
            }
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

    /**
     * 取得好友(关注人)信息
     * @param userId 用户id
     * @param contactId 好友(关注人)id
     * @param key 不羁旅行令牌
     * @return 好友(关注人)信息
     */
    public static String getContactInfo(Long userId, Long contactId, String key) throws Exception {
        try {
            // 校验用户登录
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1058);
            Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            UserInfo userInfo = query.get();
            if(userInfo == null) {
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1058);
            } else {
                // 备注
                Long userA = userId < contactId ? userId : contactId;
                Long userB = userId >= contactId ? userId : contactId;
                Query<Relationship> queryRel = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                        .field(Relationship.fd_userB).equal(userB);
                Relationship relationship = queryRel.get();
                if(relationship != null) {
                    String memo = userId < contactId ? relationship.getMemoB() : relationship.getMemoA();
                    if(memo != null)
                        userInfo.setMemo(memo);
                }
                return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新备注
     * @param userId 用户id
     * @param contactId 好友(关注人)id
     * @param key 不羁旅行令牌
     * @param memo 备注
     * @return 好友(关注人)信息
     * @throws Exception 异常
     */
    public static String updateMemo(Long userId, Long contactId, String key, String memo) throws Exception {
        try {
            // 校验用户登录
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1059);
            Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
            UserInfo userInfo = query.get();
            if(userInfo == null) {
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1059);
            } else {
                // 备注
                Long userA = userId < contactId ? userId : contactId;
                Long userB = userId >= contactId ? userId : contactId;
                Query<Relationship> queryRel = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                        .field(Relationship.fd_userB).equal(userB);
                String field = userId < contactId ? Relationship.fd_memoB : Relationship.fd_memoA;
                UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userA, userA).set(Relationship.fd_userB, userB).set(field, memo);
                Relationship relationship = ds.findAndModify(queryRel, ops, false, true);
                userInfo.setMemo(userId < contactId ? relationship.getMemoB() : relationship.getMemoA());
                return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新黑名单
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param blockId 待添加(移除)屏蔽用户id
     * @param isAdd true表示添加，false表示移除
     * @return 结果
     * @throws Exception 异常
     */
    public static String updateBlackList(Long userId, String key, Long blockId, Boolean isAdd) throws Exception {
        try {
            // 校验用户登录
            if(!CommonAPI.checkKeyValid(userId, key)) {
                if(isAdd)
                    return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1060);
                else
                    return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1061);
            }

            if(!CommonAPI.checkUserExistById(blockId)) {
                if(isAdd)
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1060);
                else
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1061);
            }
            // 更新黑名单
            Long userA = userId < blockId ? userId : blockId;
            Long userB = userId >= blockId ? userId : blockId;
            Query<Relationship> queryRel = ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userA)
                    .field(Relationship.fd_userB).equal(userB);
            String field = userId < blockId ? Relationship.fd_blockB : Relationship.fd_blockA;
            UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userA, userA).set(Relationship.fd_userB, userB).set(field, isAdd);
            ds.updateFirst(queryRel, ops, true);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户关注列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个开始返回
     * @param limit 最多返回多少个
     * @return 用户关注列表
     * @throws Exception 异常
     */
    public static String getFollowings(Long userId, String key, Integer offset, Integer limit) throws Exception {
        // 校验用户登录
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1062);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List<CriteriaContainerImpl> criterias = new ArrayList<>();
        criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userId).criteria(Relationship.fd_followingB).equal(true));
        criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userB).equal(userId).criteria(Relationship.fd_followingA).equal(true));
        Query<Relationship> query = ds.createQuery(Relationship.class);

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

    /**
     * 取得用户粉丝列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个开始返回
     * @param limit 最多返回多少个
     * @return 用户粉丝列表
     * @throws Exception 异常
     */
    public static String getFollows(Long userId, String key, Integer offset, Integer limit) throws Exception {
        // 校验用户登录
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1063);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List<CriteriaContainerImpl> criterias = new ArrayList<>();
        criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userId).criteria(Relationship.fd_followingA).equal(true));
        criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userB).equal(userId).criteria(Relationship.fd_followingB).equal(true));
        Query<Relationship> query = ds.createQuery(Relationship.class);

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
