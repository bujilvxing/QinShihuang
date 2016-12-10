package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.trace.TraceFormatter;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.requestmodel.TraceReq;
import com.bjlx.QinShihuang.utils.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;


/**
 * 其他控制器
 * Created by xiaozhi on 2016/11/8.
 */
public class TraceAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 添加足迹
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param traceReq 足迹参数
     * @return 足迹信息
     * @throws Exception 异常
     */
    public static String addTrace(Long userId, String key, TraceReq traceReq) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1040);
            }
            Trace trace = new Trace();
            return QinShihuangResult.ok(TraceFormatter.getMapper().valueToTree(trace));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 更新足迹
     * @param traceId 足迹id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param traceReq 足迹参数
     * @return 足迹信息
     * @throws Exception 异常
     */
    public static String updateTrace(String traceId, Long userId, String key, TraceReq traceReq) throws Exception {
        return null;
    }

    /**
     * 删除足迹
     * @param traceId 足迹id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String removeTrace(String traceId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 取得足迹列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 足迹列表
     * @throws Exception 异常
     */
    public static String getTraces(Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 取得足迹详情
     * @param traceId 足迹id
     * @return 足迹详情
     * @throws Exception 异常
     */
    public static String getTrace(String traceId) throws Exception {
        return null;
    }
}
