package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;

/**
 * 时间线核心实现
 * Created by xiaozhi on 2016/11/18.
 */
public class MomentAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 查看朋友圈
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 信息列表
     * @throws Exception 异常
     */
    public static String getMoments(Long userId, String key, Integer offset, Integer limit, Long targetId) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1038);
            }
            // 发表人为自己
            // 关注用户的id列表
            // 取关注圈的信息列表
//            Query<Moment> query = ds.createQuery(Moment.class).field(Moment.fd_userId).equal()
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
