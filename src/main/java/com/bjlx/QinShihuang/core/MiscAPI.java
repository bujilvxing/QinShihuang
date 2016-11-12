package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.misc.Application;
import com.bjlx.QinShihuang.model.misc.Feedback;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

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
}
