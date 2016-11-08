package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

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
            Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_userId).equal(userId)
                    .field(Relationship.fd_followingId).equal(followingId);
            UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userId, userId)
                    .set(Relationship.fd_followingId, followingId).set(Relationship.fd_follow, true);
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
            Query<Relationship> query = ds.createQuery(Relationship.class).field(Relationship.fd_userId).equal(userId)
                    .field(Relationship.fd_followingId).equal(followingId);
            UpdateOperations<Relationship> ops = ds.createUpdateOperations(Relationship.class).set(Relationship.fd_userId, userId)
                    .set(Relationship.fd_followingId, followingId).set(Relationship.fd_follow, false);
            ds.updateFirst(query, ops, true);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
