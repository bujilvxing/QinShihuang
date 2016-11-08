package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.account.Credential;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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
}
