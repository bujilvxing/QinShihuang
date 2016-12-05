package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.requestmodel.ActivityReq;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.mongodb.morphia.Datastore;

/**
 * 活动核心实现
 * Created by pengyt on 2016/12/4.
 */
public class ActivityAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 发布新活动
     * @param activityReq 活动参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 活动信息
     * @throws Exception 异常
     */
    public static String addActivity(ActivityReq activityReq, Long userId, String key) throws Exception {
        return null;
    }
}
