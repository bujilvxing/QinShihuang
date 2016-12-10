package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.trace.TraceBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.trace.TraceFormatter;
import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.requestmodel.TraceReq;
import com.bjlx.QinShihuang.utils.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

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
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1040);

            Trace trace = new Trace(userId, author.getNickName(), author.getAvatar(), traceReq.getTraceTime(), traceReq.getTitle(),
                    traceReq.getLat() == null ? 0.0 : traceReq.getLat(), traceReq.getLng() == null ? 0.0 : traceReq.getLng());
            if(traceReq.getDesc() != null)
                trace.setDesc(traceReq.getDesc());
            if(traceReq.getCover() != null)
                trace.setCover(traceReq.getCover());
            if(traceReq.getImages() != null && !traceReq.getImages().isEmpty())
                trace.setImages(traceReq.getImages());
            if(traceReq.getAudio() != null)
                trace.setAudio(traceReq.getAudio());

            if(traceReq.getOriginId() != null) {
                if(traceReq.getOriginUserId() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINUSERID_NULL_1040);
                if(traceReq.getOriginNickName() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINNICKNAME_NULL_1040);
                if(traceReq.getOriginAvatar() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINAVATAR_NULL_1040);
                trace.setOriginId(new ObjectId(traceReq.getOriginId()));
                trace.setOriginUserId(traceReq.getOriginUserId());
                trace.setOriginNickName(traceReq.getOriginNickName());
                trace.setOriginAvatar(traceReq.getOriginAvatar());
            }

            if(traceReq.getActivity() != null) {
                Activity activity = new Activity(traceReq.getActivity().getId(), traceReq.getActivity().getTitle(),
                        traceReq.getActivity().getTheme(), traceReq.getActivity().getCategory(), traceReq.getActivity().getVisiable(),
                        traceReq.getActivity().getCover(), traceReq.getActivity().isFree());
                trace.setActivity(activity);
            }
            if(traceReq.getHotel() != null) {
                Hotel hotel = new Hotel(traceReq.getHotel().getId(), traceReq.getHotel().getCover(),
                        traceReq.getHotel().getZhName(), traceReq.getHotel().getEnName() == null ? "" : traceReq.getHotel().getEnName(),
                        traceReq.getHotel().getMarketPrice(), traceReq.getHotel().getUrl(), traceReq.getHotel().getPrice());
                trace.setHotel(hotel);
            }
            if(traceReq.getRestaurant() != null) {
                Restaurant restaurant = new Restaurant(traceReq.getRestaurant().getId(), traceReq.getRestaurant().getCover(),
                        traceReq.getRestaurant().getZhName(), traceReq.getRestaurant().getEnName() == null ? "" : traceReq.getRestaurant().getEnName(),
                        traceReq.getRestaurant().getUrl(), traceReq.getRestaurant().getPrice(), traceReq.getRestaurant().getMarketPrice());
                trace.setRestaurant(restaurant);
            }
            if(traceReq.getShopping() != null) {
                Shopping shopping = new Shopping(traceReq.getShopping().getId(), traceReq.getShopping().getCover(),
                        traceReq.getShopping().getZhName(), traceReq.getShopping().getEnName() == null ? "" : traceReq.getShopping().getEnName(),
                        traceReq.getShopping().getUrl(), traceReq.getShopping().getPrice(), traceReq.getShopping().getMarketPrice());
                trace.setShopping(shopping);
            }
            if(traceReq.getViewspot() != null) {
                Viewspot viewspot = new Viewspot(traceReq.getViewspot().getId(), traceReq.getViewspot().getCover(),
                        traceReq.getViewspot().getZhName(), traceReq.getViewspot().getUrl(), traceReq.getViewspot().getEnName() == null ? "" : traceReq.getViewspot().getEnName(),
                        traceReq.getViewspot().getPrice(), traceReq.getViewspot().getMarketPrice());
                trace.setViewspot(viewspot);
            }

            ds.save(trace);
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
        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1041);
            // 取得足迹
            Query<Trace> query = ds.createQuery(Trace.class).field(Trace.fd_id).equal(new ObjectId(traceId)).field(Trace.fd_status).equal(Constant.TRACE_NORMAL);
            Trace trace = query.get();
            if(trace == null)
                return QinShihuangResult.getResult(ErrorCode.TRACE_NOT_EXIST_1041);

            if(!trace.getUserId().equals(userId))
                return QinShihuangResult.getResult(ErrorCode.FORBIDDEN);

            UpdateOperations<Trace> ops = ds.createUpdateOperations(Trace.class);
            // 更新足迹
            if(traceReq.getTitle() != null)
                ops.set(Trace.fd_title, traceReq.getTitle());
            if(traceReq.getCover() != null)
                ops.set(Trace.fd_cover, traceReq.getCover());
            if(traceReq.getDesc() != null)
                ops.set(Trace.fd_desc, traceReq.getDesc());
            if(traceReq.getTraceTime() != null)
                ops.set(Trace.fd_traceTime, traceReq.getTraceTime());
            if(traceReq.getImages() != null || !traceReq.getImages().isEmpty())
                ops.set(Trace.fd_images, traceReq.getImages());
            if(traceReq.getAudio() != null)
                ops.set(Trace.fd_audio, traceReq.getAudio());
            if(traceReq.getLat() != null)
                ops.set(Trace.fd_lat, traceReq.getLat());
            if(traceReq.getLng() != null)
                ops.set(Trace.fd_lng, traceReq.getLng());

            if(traceReq.getActivity() != null) {
                Activity activity = new Activity(traceReq.getActivity().getId(), traceReq.getActivity().getTitle(),
                        traceReq.getActivity().getTheme(), traceReq.getActivity().getCategory(), traceReq.getActivity().getVisiable(),
                        traceReq.getActivity().getCover(), traceReq.getActivity().isFree());
                trace.setActivity(activity);
            }
            if(traceReq.getHotel() != null) {
                Hotel hotel = new Hotel(traceReq.getHotel().getId(), traceReq.getHotel().getCover(),
                        traceReq.getHotel().getZhName(), traceReq.getHotel().getEnName() == null ? "" : traceReq.getHotel().getEnName(),
                        traceReq.getHotel().getMarketPrice(), traceReq.getHotel().getUrl(), traceReq.getHotel().getPrice());
                trace.setHotel(hotel);
            }
            if(traceReq.getRestaurant() != null) {
                Restaurant restaurant = new Restaurant(traceReq.getRestaurant().getId(), traceReq.getRestaurant().getCover(),
                        traceReq.getRestaurant().getZhName(), traceReq.getRestaurant().getEnName() == null ? "" : traceReq.getRestaurant().getEnName(),
                        traceReq.getRestaurant().getUrl(), traceReq.getRestaurant().getPrice(), traceReq.getRestaurant().getMarketPrice());
                trace.setRestaurant(restaurant);
            }
            if(traceReq.getShopping() != null) {
                Shopping shopping = new Shopping(traceReq.getShopping().getId(), traceReq.getShopping().getCover(),
                        traceReq.getShopping().getZhName(), traceReq.getShopping().getEnName() == null ? "" : traceReq.getShopping().getEnName(),
                        traceReq.getShopping().getUrl(), traceReq.getShopping().getPrice(), traceReq.getShopping().getMarketPrice());
                trace.setShopping(shopping);
            }
            if(traceReq.getViewspot() != null) {
                Viewspot viewspot = new Viewspot(traceReq.getViewspot().getId(), traceReq.getViewspot().getCover(),
                        traceReq.getViewspot().getZhName(), traceReq.getViewspot().getUrl(), traceReq.getViewspot().getEnName() == null ? "" : traceReq.getViewspot().getEnName(),
                        traceReq.getViewspot().getPrice(), traceReq.getViewspot().getMarketPrice());
                trace.setViewspot(viewspot);
            }

            Trace result = ds.findAndModify(query, ops, false);

            return QinShihuangResult.ok(TripPlanFormatter.getMapper().valueToTree(result));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1042);
            Query<Trace> query = ds.createQuery(Trace.class).field(Trace.fd_id).equal(new ObjectId(traceId))
                    .field(Trace.fd_userId).equal(userId).field(Trace.fd_status).equal(Constant.TRACE_NORMAL);
            UpdateOperations<Trace> ops = ds.createUpdateOperations(Trace.class).set(Trace.fd_status, Constant.TRACE_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户足迹列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 足迹列表
     * @throws Exception 异常
     */
    public static String getTraces(Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1043);
            // 取得足迹
            Query<Trace> query = ds.createQuery(Trace.class).field(Trace.fd_userId).equal(userId).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL)
                    .retrievedFields(true, Trace.fd_id, Trace.fd_userId, Trace.fd_nickName, Trace.fd_avatar, Trace.fd_title,
                            Trace.fd_cover, Trace.fd_audio, Trace.fd_originId, Trace.fd_originUserId, Trace.fd_originNickName, Trace.fd_originAvatar);
            List<Trace> traces = query.asList();
            if (traces == null || traces.isEmpty())
                return QinShihuangResult.ok(TraceBasicFormatter.getMapper().valueToTree(new ArrayList<Trace>()));
            else
                return QinShihuangResult.ok(TraceBasicFormatter.getMapper().valueToTree(traces));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得足迹详情
     * @param traceId 足迹id
     * @return 足迹详情
     * @throws Exception 异常
     */
    public static String getTrace(String traceId) throws Exception {
        try {
            Query<Trace> query = ds.createQuery(Trace.class).field(Trace.fd_id).equal(new ObjectId(traceId)).field(Trace.fd_status).equal(Constant.TRACE_NORMAL);
            Trace trace = query.get();
            if(trace == null)
                return QinShihuangResult.getResult(ErrorCode.TRACE_NOT_EXIST_1044);
            else
                return QinShihuangResult.ok(TraceFormatter.getMapper().valueToTree(trace));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
