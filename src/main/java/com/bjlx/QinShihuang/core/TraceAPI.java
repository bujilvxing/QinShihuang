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
     * 添加收藏
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param traceReq
     * @return 结果信息
     * @throws Exception 异常
     */
    public static String addTrace(Long userId, String key, TraceReq traceReq) throws Exception {
        try {
            /*
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1077);
            }
            */

            Query<Trace> query = ds.createQuery(Trace.class)
                    .field(Trace.fd_userId).equal(userId);
            UpdateOperations<Trace> ops = ds.createUpdateOperations(Trace.class)
                    .set(Trace.fd_id, new ObjectId())
                    .set(Trace.fd_userId, userId)
                    .set(Trace.fd_activity,traceReq.getActivity())
                    .set(Trace.fd_audio,traceReq.getAudio())
                    .set(Trace.fd_createTime, System.currentTimeMillis())
                    .set(Trace.fd_desc, traceReq.getDesc())
                    .set(Trace.fd_hotel, traceReq.getHotel())
                    .set(Trace.fd_images, traceReq.getImages())
                    .set(Trace.fd_lat, traceReq.getLat())
                    .set(Trace.fd_lng, traceReq.getLng())
                    .set(Trace.fd_nickName, traceReq.getNickName())
                    .set(Trace.fd_originId, traceReq.getOriginId())
                    .set(Trace.fd_originUserId, traceReq.getOriginUserId())
                    .set(Trace.fd_originNickName, traceReq.getOriginNickName())
                    .set(Trace.fd_originAvatar, traceReq.getOriginAvatar())
                    .set(Trace.fd_restaurant, traceReq.getRestaurant())
                    .set(Trace.fd_shopping, traceReq.getShopping())
                    .set(Trace.fd_status, traceReq.getStatus())
                    .set(Trace.fd_traceTime, traceReq.getTraceTime())
                    .set(Trace.fd_viewspot, traceReq.getViewspot())
                    ;
            if(traceReq.getCover() != null)
                ops.set(Trace.fd_cover, traceReq.getCover());
            Trace trace = ds.findAndModify(query, ops, false, true);

            return QinShihuangResult.ok(TraceFormatter.getMapper().valueToTree(trace));
        } catch (Exception e) {
            throw e;
        }
    }


}
