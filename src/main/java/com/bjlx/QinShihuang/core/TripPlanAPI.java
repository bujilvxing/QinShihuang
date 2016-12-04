package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripItem;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.requestmodel.TripItemReq;
import com.bjlx.QinShihuang.requestmodel.TripPlanReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

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
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1045);

            // 取得用户信息
            UserInfo userInfo = CommonAPI.getUserBasicById(userId);
            if(userInfo == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1045);

            TripPlan tripPlan = null;
            if(tripPlanReq.getOriginId() == null) {
                tripPlan = new TripPlan(userId, userInfo.getNickName(), userInfo.getAvatar());
            } else {
                if(tripPlanReq.getOriginUserId() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINUSERID_NULL_1045);
                if(tripPlanReq.getOriginNickName() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINNICKNAME_NULL_1045);
                if(tripPlanReq.getOriginAvatar() == null)
                    return QinShihuangResult.getResult(ErrorCode.ORIGINAVATAR_NULL_1045);
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
}
