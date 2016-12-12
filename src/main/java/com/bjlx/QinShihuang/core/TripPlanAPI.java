package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripItem;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.requestmodel.TripItemReq;
import com.bjlx.QinShihuang.requestmodel.TripPlanReq;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * 行程规划核心实现
 * Created by pengyt on 2016/12/4.
 */
public class TripPlanAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 发布行程规划
     * @param tripPlanReq 行程规划参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划信息
     * @throws Exception 异常
     */
    public static String addTripPlan(TripPlanReq tripPlanReq, Long userId, String key) throws Exception {
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1057);

            // 取得用户信息
            UserInfo userInfo = CommonAPI.getUserBasicById(userId);
            if(userInfo == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1057);

            TripPlan tripPlan;
            if(tripPlanReq.getOriginId() == null) {
                tripPlan = new TripPlan(userId, userInfo.getNickName(), userInfo.getAvatar());
            } else {
                if(tripPlanReq.getOriginUserId() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINUSERID_NULL_1057);
                if(tripPlanReq.getOriginNickName() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINNICKNAME_NULL_1057);
                if(tripPlanReq.getOriginAvatar() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINAVATAR_NULL_1057);
                tripPlan = new TripPlan(userId, userInfo.getNickName(), userInfo.getAvatar(), new ObjectId(tripPlanReq.getOriginId()),
                        tripPlanReq.getOriginUserId(), tripPlanReq.getOriginNickName(), tripPlanReq.getOriginAvatar());
            }

            if(tripPlanReq.getTripItems() != null && !tripPlanReq.getTripItems().isEmpty()) {
                // 构建行程项
                List<TripItem> tripItems = new ArrayList<TripItem>();
                for(TripItemReq tripItemReq : tripPlanReq.getTripItems()) {
                    TripItem tripItem = new TripItem(tripItemReq.getTripTime(), tripItemReq.getDesc());

                    if(tripItemReq.getActivity() != null) {
                        Activity activity = new Activity(tripItemReq.getActivity().getId(), tripItemReq.getActivity().getTitle(),
                                tripItemReq.getActivity().getTheme(), tripItemReq.getActivity().getCategory(), tripItemReq.getActivity().getVisiable(),
                                tripItemReq.getActivity().getCover(), tripItemReq.getActivity().isFree());
                        tripItem.setActivity(activity);
                    }
                    if(tripItemReq.getHotel() != null) {
                        Hotel hotel = new Hotel(tripItemReq.getHotel().getId(), tripItemReq.getHotel().getCover(),
                                tripItemReq.getHotel().getZhName(), tripItemReq.getHotel().getEnName() == null ? "" : tripItemReq.getHotel().getEnName(),
                                tripItemReq.getHotel().getMarketPrice(), tripItemReq.getHotel().getUrl(), tripItemReq.getHotel().getPrice());
                        tripItem.setHotel(hotel);
                    }
                    if(tripItemReq.getRestaurant() != null) {
                        Restaurant restaurant = new Restaurant(tripItemReq.getRestaurant().getId(), tripItemReq.getRestaurant().getCover(),
                                tripItemReq.getRestaurant().getZhName(), tripItemReq.getRestaurant().getEnName() == null ? "" : tripItemReq.getRestaurant().getEnName(),
                                tripItemReq.getRestaurant().getUrl(), tripItemReq.getRestaurant().getPrice(), tripItemReq.getRestaurant().getMarketPrice());
                        tripItem.setRestaurant(restaurant);
                    }
                    if(tripItemReq.getShopping() != null) {
                        Shopping shopping = new Shopping(tripItemReq.getShopping().getId(), tripItemReq.getShopping().getCover(),
                                tripItemReq.getShopping().getZhName(), tripItemReq.getShopping().getEnName() == null ? "" : tripItemReq.getShopping().getEnName(),
                                tripItemReq.getShopping().getUrl(), tripItemReq.getShopping().getPrice(), tripItemReq.getShopping().getMarketPrice());
                        tripItem.setShopping(shopping);
                    }
                    if(tripItemReq.getViewspot() != null) {
                        Viewspot viewspot = new Viewspot(tripItemReq.getViewspot().getId(), tripItemReq.getViewspot().getCover(),
                                tripItemReq.getViewspot().getZhName(), tripItemReq.getViewspot().getUrl(), tripItemReq.getViewspot().getEnName() == null ? "" : tripItemReq.getViewspot().getEnName(),
                                tripItemReq.getViewspot().getPrice(), tripItemReq.getViewspot().getMarketPrice());
                        tripItem.setViewspot(viewspot);
                    }
                    tripItems.add(tripItem);
                }
                tripPlan.setTripItems(tripItems);
            }
            ds.save(tripPlan);
            return QinShihuangResult.ok(TripPlanFormatter.getMapper().valueToTree(tripPlan));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 复制行程规划
     * @param tripPlanId 行程规划id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划
     * @throws Exception 异常
     */
    public static String forkTripPlan(String tripPlanId, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1058);
            // 取得行程规划
            Query<TripPlan> query = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(tripPlanId)).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL);
            TripPlan tripPlan = query.get();
            if (tripPlan == null)
                return QinShihuangResult.getResult(ErrorCode.TRIPPLAN_NOT_EXIST_1058);

            if (!tripPlan.getUserId().equals(userId)) {
                if (tripPlan.getOriginUserId() == null) {
                    tripPlan.setOriginId(tripPlan.getId());
                    tripPlan.setOriginUserId(tripPlan.getUserId());
                    tripPlan.setOriginNickName(tripPlan.getNickName());
                    tripPlan.setOriginAvatar(tripPlan.getAvatar());
                }
                // 取得用户信息
                UserInfo userInfo = CommonAPI.getUserBasicById(userId);
                if (userInfo == null)
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1058);
                tripPlan.setUserId(userId);
                tripPlan.setNickName(userInfo.getNickName());
                tripPlan.setAvatar(userInfo.getAvatar());
            }
            tripPlan.setId(new ObjectId());
            ds.save(tripPlan);
            return QinShihuangResult.ok(TripPlanFormatter.getMapper().valueToTree(tripPlan));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户行程规划列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划列表
     * @throws Exception 异常
     */
    public static String getTripPlans(Long userId, String key) throws Exception {

        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1059);
            // 取得行程规划
            Query<TripPlan> query = ds.createQuery(TripPlan.class).field(TripPlan.fd_userId).equal(userId).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL)
                    .retrievedFields(true, TripPlan.fd_id, TripPlan.fd_userId, TripPlan.fd_nickName, TripPlan.fd_avatar, TripPlan.fd_title,
                            TripPlan.fd_cover, TripPlan.fd_originId, TripPlan.fd_originUserId, TripPlan.fd_originNickName, TripPlan.fd_originAvatar);
            List<TripPlan> tripPlans = query.asList();
            if (tripPlans == null || tripPlans.isEmpty())
                return QinShihuangResult.ok(TripPlanBasicFormatter.getMapper().valueToTree(new ArrayList<TripPlan>()));
            else
                return QinShihuangResult.ok(TripPlanBasicFormatter.getMapper().valueToTree(tripPlans));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新行程规划
     * @param tripPlanId 行程规划id
     * @param tripPlanReq 更新行程规划参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 行程规划信息
     * @throws Exception 异常
     */
    public static String updateTripPlan(String tripPlanId, TripPlanReq tripPlanReq, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1060);
            // 取得行程规划
            Query<TripPlan> query = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(tripPlanId)).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL);
            TripPlan tripPlan = query.get();
            if(tripPlan == null)
                return QinShihuangResult.getResult(ErrorCode.TRIPPLAN_NOT_EXIST_1060);
            if(!tripPlan.getUserId().equals(userId))
                return QinShihuangResult.getResult(ErrorCode.FORBIDDEN);

            UpdateOperations<TripPlan> ops = ds.createUpdateOperations(TripPlan.class);
            // 更新行程规划
            if(tripPlanReq.getTitle() != null)
                ops.set(TripPlan.fd_title, tripPlanReq.getTitle());
            if(tripPlanReq.getCover() != null)
                ops.set(TripPlan.fd_cover, tripPlanReq.getCover());
            if(tripPlanReq.getDesc() != null)
                ops.set(TripPlan.fd_desc, tripPlanReq.getDesc());

            if(tripPlanReq.getTripItems() != null && !tripPlanReq.getTripItems().isEmpty()) {
                // 构建行程项
                List<TripItem> tripItems = new ArrayList<TripItem>();
                for(TripItemReq tripItemReq : tripPlanReq.getTripItems()) {
                    TripItem tripItem = new TripItem(tripItemReq.getTripTime(), tripItemReq.getDesc());

                    if(tripItemReq.getActivity() != null) {
                        Activity activity = new Activity(tripItemReq.getActivity().getId(), tripItemReq.getActivity().getTitle(),
                                tripItemReq.getActivity().getTheme(), tripItemReq.getActivity().getCategory(), tripItemReq.getActivity().getVisiable(),
                                tripItemReq.getActivity().getCover(), tripItemReq.getActivity().isFree());
                        tripItem.setActivity(activity);
                    }
                    if(tripItemReq.getHotel() != null) {
                        Hotel hotel = new Hotel(tripItemReq.getHotel().getId(), tripItemReq.getHotel().getCover(),
                                tripItemReq.getHotel().getZhName(), tripItemReq.getHotel().getEnName() == null ? "" : tripItemReq.getHotel().getEnName(),
                                tripItemReq.getHotel().getMarketPrice(), tripItemReq.getHotel().getUrl(), tripItemReq.getHotel().getPrice());
                        tripItem.setHotel(hotel);
                    }
                    if(tripItemReq.getRestaurant() != null) {
                        Restaurant restaurant = new Restaurant(tripItemReq.getRestaurant().getId(), tripItemReq.getRestaurant().getCover(),
                                tripItemReq.getRestaurant().getZhName(), tripItemReq.getRestaurant().getEnName() == null ? "" : tripItemReq.getRestaurant().getEnName(),
                                tripItemReq.getRestaurant().getUrl(), tripItemReq.getRestaurant().getPrice(), tripItemReq.getRestaurant().getMarketPrice());
                        tripItem.setRestaurant(restaurant);
                    }
                    if(tripItemReq.getShopping() != null) {
                        Shopping shopping = new Shopping(tripItemReq.getShopping().getId(), tripItemReq.getShopping().getCover(),
                                tripItemReq.getShopping().getZhName(), tripItemReq.getShopping().getEnName() == null ? "" : tripItemReq.getShopping().getEnName(),
                                tripItemReq.getShopping().getUrl(), tripItemReq.getShopping().getPrice(), tripItemReq.getShopping().getMarketPrice());
                        tripItem.setShopping(shopping);
                    }
                    if(tripItemReq.getViewspot() != null) {
                        Viewspot viewspot = new Viewspot(tripItemReq.getViewspot().getId(), tripItemReq.getViewspot().getCover(),
                                tripItemReq.getViewspot().getZhName(), tripItemReq.getViewspot().getUrl(), tripItemReq.getViewspot().getEnName() == null ? "" : tripItemReq.getViewspot().getEnName(),
                                tripItemReq.getViewspot().getPrice(), tripItemReq.getViewspot().getMarketPrice());
                        tripItem.setViewspot(viewspot);
                    }
                    tripItems.add(tripItem);
                }
                ops.set(TripPlan.fd_tripItems, tripItems);
            }
            TripPlan result = ds.findAndModify(query, ops);
            return QinShihuangResult.ok(TripPlanFormatter.getMapper().valueToTree(result));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得行程规划详情
     * @param tripPlanId 行程规划id
     * @return 行程规划详情
     * @throws Exception 异常
     */
    public static String getTripPlan(String tripPlanId) throws Exception {
        try {
            // 取得行程规划
            Query<TripPlan> query = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(tripPlanId)).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL);
            TripPlan tripPlan = query.get();
            if(tripPlan == null)
                return QinShihuangResult.getResult(ErrorCode.TRIPPLAN_NOT_EXIST_1061);
            else
                return QinShihuangResult.ok(TripPlanFormatter.getMapper().valueToTree(tripPlan));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除行程规划
     * @param tripPlanId 行程规划id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String removeTripPlan(String tripPlanId, Long userId, String key) throws Exception {

        try {
            if (!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1062);
            Query<TripPlan> query = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(tripPlanId))
                    .field(TripPlan.fd_userId).equal(userId).field(TripPlan.fd_status).equal(Constant.TRIPPLAN_NORMAL);
            UpdateOperations<TripPlan> ops = ds.createUpdateOperations(TripPlan.class).set(TripPlan.fd_status, Constant.TRIPPLAN_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
