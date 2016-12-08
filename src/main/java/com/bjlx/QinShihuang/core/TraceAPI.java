package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.FavoriteFormatter;
import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.account.VoteFormatter;
import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.guide.GuideBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.im.ChatgroupBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.marketplace.CommodityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.misc.TravelNoteBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.HotelBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.RestaurantBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.ShoppingBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.ViewspotBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.quora.QuestionBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.timeline.MomentBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.trace.TraceBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanBasicFormatter;
import com.bjlx.QinShihuang.model.account.Favorite;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.account.Vote;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.Application;
import com.bjlx.QinShihuang.model.misc.Feedback;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.quora.Answer;
import com.bjlx.QinShihuang.model.quora.Question;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.utils.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    /**
     * 搜索用户
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 查询关键字
     * @return 用户信息
     */
    public static String searchUser(Long userId, String key, String query) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1100);
            }
            Query<UserInfo> queryUser = ds.createQuery(UserInfo.class).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);

            if(CommonUtil.isEmail(query)) {
                UserInfo userInfo = queryUser.field(UserInfo.fd_email).equal(query).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
                else
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1100);
            }

            if(CommonUtil.isTelLegal(query)) {
                UserInfo userInfo = queryUser.field(UserInfo.fd_number).equal(query).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
            }

            if(CommonUtil.isLongDigit(query)) {
                Long uid = Long.getLong(query);
                UserInfo userInfo = queryUser.field(UserInfo.fd_userId).equal(uid).get();
                if(userInfo != null)
                    return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
                else
                    return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1100);
            } else {
                return QinShihuangResult.getResult(ErrorCode.QUERY_INVALID_1100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索聊天组
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 查询关键字
     * @return 聊天组信息
     */
    public static String searchChatgroup(Long userId, String key, String query) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1101);
            }


            List<Chatgroup> chatgroups = new ArrayList<Chatgroup>();

            if(CommonUtil.isLongDigit(query)) {
                Long chatgroupId = Long.getLong(query);
                Chatgroup chatgroup = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_chatGroupId).equal(chatgroupId).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).get();
                if(chatgroup != null)
                    chatgroups.add(chatgroup);
            }
            List<Chatgroup> chatgroupList = ds.createQuery(Chatgroup.class).field(Chatgroup.fd_name).startsWithIgnoreCase(query).field(Chatgroup.fd_status).equal(Constant.CHATGROUP_NORMAL).asList();
            if(chatgroupList != null) {
                for(Chatgroup chatgroup : chatgroupList)
                    chatgroups.add(chatgroup);
            }
            return QinShihuangResult.ok(ChatgroupBasicFormatter.getMapper().valueToTree(chatgroups));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索时间线
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 时间线列表
     * @throws Exception 异常
     */
    public static List<Moment> queryMoments(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Moment.class).field(Moment.fd_comment).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Moment.fd_publishTime)).asList();
            else
                return ds.createQuery(Moment.class).field(Moment.fd_comment).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Moment.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 搜索商品
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 商品列表
     * @throws Exception 异常
     */
    public static List<Commodity> queryCommodities(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Commodity.class).field(Commodity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Commodity.fd_updateTime)).asList();
            else
                return ds.createQuery(Commodity.class).field(Commodity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Commodity.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索攻略
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 攻略列表
     * @throws Exception 异常
     */
    public static List<Guide> queryGuides(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Guide.class).field(Guide.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Guide.fd_updateTime)).asList();
            else
                return ds.createQuery(Guide.class).field(Guide.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Guide.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索景点
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 景点列表
     * @throws Exception 异常
     */
    public static List<Viewspot> queryViewspots(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Viewspot.class).field(Viewspot.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Viewspot.fd_hotness)).asList();
            else
                return ds.createQuery(Viewspot.class).field(Viewspot.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Viewspot.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索足迹
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 足迹列表
     * @throws Exception 异常
     */
    public static List<Trace> queryTraces(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Trace.class).field(Trace.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Trace.fd_updateTime)).asList();
            else
                return ds.createQuery(Trace.class).field(Trace.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Trace.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索行程规划
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 行程规划列表
     * @throws Exception 异常
     */
    public static List<TripPlan> queryTripPlans(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(TripPlan.class).field(TripPlan.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", TripPlan.fd_updateTime)).asList();
            else
                return ds.createQuery(TripPlan.class).field(TripPlan.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", TripPlan.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索问题
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 问题列表
     * @throws Exception 异常
     */
    public static List<Question> queryQuestions(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Question.class).field(Question.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Question.fd_publishTime)).asList();
            else
                return ds.createQuery(Question.class).field(Question.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Question.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索活动
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 活动列表
     * @throws Exception 异常
     */
    public static List<Activity> queryActivities(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Activity.class).field(Activity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Activity.fd_endTime)).asList();
            else
                return ds.createQuery(Activity.class).field(Activity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Activity.fd_endTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索游记
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 游记列表
     * @throws Exception 异常
     */
    public static List<TravelNote> queryTravelNotes(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(TravelNote.class).field(TravelNote.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", TravelNote.fd_publishTime)).asList();
            else
                return ds.createQuery(TravelNote.class).field(TravelNote.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", TravelNote.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索美食
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 美食列表
     * @throws Exception 异常
     */
    public static List<Restaurant> queryRestaurants(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Restaurant.class).field(Restaurant.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Restaurant.fd_hotness)).asList();
            else
                return ds.createQuery(Restaurant.class).field(Restaurant.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Restaurant.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索宾馆
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 宾馆列表
     * @throws Exception 异常
     */
    public static List<Hotel> queryHotels(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Hotel.class).field(Hotel.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Hotel.fd_hotness)).asList();
            else
                return ds.createQuery(Hotel.class).field(Hotel.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_CONDITION_OFFSET)
                        .limit(Constant.SEARCH_CONDITION_LIMIT).order(String.format("-%s", Hotel.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索购物
     * @param isAll 是否搜索全站
     * @param query 关键字
     * @return 购物列表
     * @throws Exception 异常
     */
    public static List<Shopping> queryShoppings(Boolean isAll, String query) throws Exception {
        try {
            if(isAll)
                return ds.createQuery(Shopping.class).field(Shopping.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Shopping.fd_hotness)).asList();
            else
                return ds.createQuery(Shopping.class).field(Shopping.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                        .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Shopping.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    /**
     * 全站搜索
     * @param query 搜索关键词
     * @return 结果
     * @throws Exception 异常
     */
    public static String searchAll(String query) throws Exception {
        ObjectNode result = CommonAPI.mapper.createObjectNode();
        try {
            List<Moment> moments = queryMoments(true, query);
            if(moments != null)
                result.set("moments", MomentBasicFormatter.getMapper().valueToTree(moments));
            List<Commodity> commodities = queryCommodities(true, query);
            if(commodities != null)
                result.set("commodities", CommodityBasicFormatter.getMapper().valueToTree(commodities));
            List<Guide> guides = queryGuides(true, query);
            if(guides != null)
                result.set("guides", GuideBasicFormatter.getMapper().valueToTree(guides));
            List<Viewspot> viewspots = queryViewspots(true, query);
            if(viewspots != null)
                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
            List<Trace> traces = queryTraces(true, query);
            if(traces != null)
                result.set("traces", TraceBasicFormatter.getMapper().valueToTree(traces));
            List<TripPlan> tripPlans = queryTripPlans(true, query);
            if(tripPlans != null)
                result.set("tripPlans", TripPlanBasicFormatter.getMapper().valueToTree(tripPlans));
            List<Question> questions = queryQuestions(true, query);
            if(questions != null)
                result.set("quoras", QuestionBasicFormatter.getMapper().valueToTree(questions));
            List<Activity> activities = queryActivities(true, query);
            if(activities != null)
                result.set("activities", ActivityBasicFormatter.getMapper().valueToTree(activities));
            List<TravelNote> travelNotes = queryTravelNotes(true, query);
            if(travelNotes != null)
                result.set("travelNotes", TravelNoteBasicFormatter.getMapper().valueToTree(travelNotes));
            List<Restaurant> restaurants = queryRestaurants(true, query);
            if(restaurants != null)
                result.set("restaurants", RestaurantBasicFormatter.getMapper().valueToTree(restaurants));
            List<Hotel> hotels = queryHotels(true, query);
            if(hotels != null)
                result.set("hotels", HotelBasicFormatter.getMapper().valueToTree(hotels));
            List<Shopping> shoppings = queryShoppings(true, query);
            if(shoppings != null)
                result.set("shoppings", ShoppingBasicFormatter.getMapper().valueToTree(shoppings));
            return QinShihuangResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 条件搜索搜索
     * @param query 搜索关键词
     * @param momemt 是否搜索时间线
     * @param commodity 是否搜索商品
     * @param guide 是否搜索攻略
     * @param viewspot 是否搜索景点
     * @param trace 是否搜索足迹
     * @param tripPlan 是否搜索行程规划
     * @param quora 是否搜索问答
     * @param activity 是否搜索活动
     * @param travelNote 是否搜索游记
     * @param restaurant 是否搜索美食
     * @param hotel 是否搜索宾馆
     * @param shopping 是否搜索购物
     * @return 结果
     * @throws Exception 异常
     */
    public static String searchCondition(String query , Boolean momemt, Boolean commodity, Boolean guide, Boolean viewspot,
                                         Boolean trace, Boolean tripPlan, Boolean quora, Boolean activity, Boolean travelNote,
                                         Boolean restaurant, Boolean hotel, Boolean shopping) throws Exception {
        ObjectNode result = CommonAPI.mapper.createObjectNode();
        try {
            if(momemt) {
                List<Moment> moments = queryMoments(false, query);
                if (moments != null)
                    result.set("moments", MomentBasicFormatter.getMapper().valueToTree(moments));
            }
            if(commodity) {
                List<Commodity> commodities = queryCommodities(false, query);
                if (commodities != null)
                    result.set("commodities", CommodityBasicFormatter.getMapper().valueToTree(commodities));
            }
            if(guide) {
                List<Guide> guides = queryGuides(false, query);
                if (guides != null)
                    result.set("guides", GuideBasicFormatter.getMapper().valueToTree(guides));
            }
            if(viewspot) {
                List<Viewspot> viewspots = queryViewspots(false, query);
                if (viewspots != null)
                    result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
            }
            if(trace) {
                List<Trace> traces = queryTraces(false, query);
                if (traces != null)
                    result.set("traces", TraceBasicFormatter.getMapper().valueToTree(traces));
            }
            if(tripPlan) {
                List<TripPlan> tripPlans = queryTripPlans(false, query);
                if (tripPlans != null)
                    result.set("tripPlans", TripPlanBasicFormatter.getMapper().valueToTree(tripPlans));
            }
            if(quora) {
                List<Question> questions = queryQuestions(false, query);
                if (questions != null)
                    result.set("quoras", QuestionBasicFormatter.getMapper().valueToTree(questions));
            }
            if(activity) {
                List<Activity> activities = queryActivities(false, query);
                if (activities != null)
                    result.set("activities", ActivityBasicFormatter.getMapper().valueToTree(activities));
            }
            if(travelNote) {
                List<TravelNote> travelNotes = queryTravelNotes(false, query);
                if (travelNotes != null)
                    result.set("travelNotes", TravelNoteBasicFormatter.getMapper().valueToTree(travelNotes));
            }
            if(restaurant) {
                List<Restaurant> restaurants = queryRestaurants(false, query);
                if (restaurants != null)
                    result.set("restaurants", RestaurantBasicFormatter.getMapper().valueToTree(restaurants));
            }
            if(hotel) {
                List<Hotel> hotels = queryHotels(false, query);
                if (hotels != null)
                    result.set("hotels", HotelBasicFormatter.getMapper().valueToTree(hotels));
            }
            if(shopping) {
                List<Shopping> shoppings = queryShoppings(false, query);
                if (shoppings != null)
                    result.set("shoppings", ShoppingBasicFormatter.getMapper().valueToTree(shoppings));
            }
            return QinShihuangResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 更新收藏数
     * @param itemId 收藏对象id
     * @param favoriteType 收藏类型
     * @param isAdd 是否添加，true表示加1，false表示减1
     * @throws Exception 异常
     */
    public static void updateFavorCnt(String itemId, Integer favoriteType, Boolean isAdd) throws Exception {
        try {
            switch (favoriteType) {
                case Constant.FAVORITE_POST:
                    Query<Post> queryPost = ds.createQuery(Post.class).field(Post.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Post> opsPost = ds.createUpdateOperations(Post.class);
                    if (isAdd) opsPost.inc(Post.fd_favorCnt);
                    else opsPost.dec(Post.fd_favorCnt);
                    ds.updateFirst(queryPost, opsPost);
                    break;
                case Constant.FAVORITE_TRACE:
                    Query<Trace> queryTrace = ds.createQuery(Trace.class).field(Trace.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Trace> opsTrace = ds.createUpdateOperations(Trace.class);
                    if (isAdd) opsTrace.inc(Trace.fd_favorCnt);
                    else opsTrace.dec(Trace.fd_favorCnt);
                    ds.updateFirst(queryTrace, opsTrace);
                    break;
                case Constant.FAVORITE_TRIPPLAN:
                    Query<TripPlan> queryTripPlan = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<TripPlan> opsTripPlan = ds.createUpdateOperations(TripPlan.class);
                    if (isAdd) opsTripPlan.inc(TripPlan.fd_favorCnt);
                    else opsTripPlan.dec(TripPlan.fd_favorCnt);
                    ds.updateFirst(queryTripPlan, opsTripPlan);
                    break;
                case Constant.FAVORITE_ACTIVITY:
                    Query<Activity> queryActivity = ds.createQuery(Activity.class).field(Activity.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Activity> opsActivity = ds.createUpdateOperations(Activity.class);
                    if (isAdd) opsActivity.inc(Activity.fd_favorCnt);
                    else opsActivity.dec(Activity.fd_favorCnt);
                    ds.updateFirst(queryActivity, opsActivity);
                    break;
                case Constant.FAVORITE_QUORA:
                    Query<Question> queryQuestion = ds.createQuery(Question.class).field(Question.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Question> opsQuestion = ds.createUpdateOperations(Question.class);
                    if (isAdd) opsQuestion.inc(Question.fd_favorCnt);
                    else opsQuestion.dec(Question.fd_favorCnt);
                    ds.updateFirst(queryQuestion, opsQuestion);
                    break;
                case Constant.FAVORITE_RESTAURANT:
                    Query<Restaurant> queryRestaurant = ds.createQuery(Restaurant.class).field(Restaurant.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Restaurant> opsRestaurant = ds.createUpdateOperations(Restaurant.class);
                    if (isAdd) opsRestaurant.inc(Restaurant.fd_favorCnt);
                    else opsRestaurant.dec(Restaurant.fd_favorCnt);
                    ds.updateFirst(queryRestaurant, opsRestaurant);
                    break;
                case Constant.FAVORITE_HOTEL:
                    Query<Hotel> queryHotel = ds.createQuery(Hotel.class).field(Hotel.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Hotel> opsHotel = ds.createUpdateOperations(Hotel.class);
                    if (isAdd) opsHotel.inc(Hotel.fd_favorCnt);
                    else opsHotel.dec(Hotel.fd_favorCnt);
                    ds.updateFirst(queryHotel, opsHotel);
                    break;
                case Constant.FAVORITE_TRAVELNOTE:
                    Query<TravelNote> queryTravelNote = ds.createQuery(TravelNote.class).field(TravelNote.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<TravelNote> opsTravelNote = ds.createUpdateOperations(TravelNote.class);
                    if (isAdd) opsTravelNote.inc(TravelNote.fd_favorCnt);
                    else opsTravelNote.dec(TravelNote.fd_favorCnt);
                    ds.updateFirst(queryTravelNote, opsTravelNote);
                    break;
                case Constant.FAVORITE_VIEWSPOT:
                    Query<Viewspot> queryViewspot = ds.createQuery(Viewspot.class).field(Viewspot.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Viewspot> opsViewspot = ds.createUpdateOperations(Viewspot.class);
                    if (isAdd) opsViewspot.inc(Viewspot.fd_favorCnt);
                    else opsViewspot.dec(Viewspot.fd_favorCnt);
                    ds.updateFirst(queryViewspot, opsViewspot);
                    break;
                case Constant.FAVORITE_SHOPPING:
                    Query<Shopping> queryShopping = ds.createQuery(Shopping.class).field(Shopping.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Shopping> opsShopping = ds.createUpdateOperations(Shopping.class);
                    if (isAdd) opsShopping.inc(Shopping.fd_favorCnt);
                    else opsShopping.dec(Shopping.fd_favorCnt);
                    ds.updateFirst(queryShopping, opsShopping);
                    break;
                case Constant.FAVORITE_MOMENT:
                    Query<Moment> queryMoment = ds.createQuery(Moment.class).field(Moment.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Moment> opsMoment = ds.createUpdateOperations(Moment.class);
                    if (isAdd) opsMoment.inc(Moment.fd_favorCnt);
                    else opsMoment.dec(Moment.fd_favorCnt);
                    ds.updateFirst(queryMoment, opsMoment);
                    break;
                case Constant.FAVORITE_COMMODITY:
                    Query<Commodity> queryCommodity = ds.createQuery(Commodity.class).field(Commodity.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Commodity> opsCommodity = ds.createUpdateOperations(Commodity.class);
                    if (isAdd) opsCommodity.inc(Commodity.fd_favorCnt);
                    else opsCommodity.dec(Commodity.fd_favorCnt);
                    ds.updateFirst(queryCommodity, opsCommodity);
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 添加收藏
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param favoriteType 收藏类型
     * @param itemId 收藏对象id
     * @param authorId 作者id
     * @param authorNickName 作者昵称
     * @param authorAvatar 作者头像
     * @param cover 收藏封面图
     * @param title 收藏标题
     * @return 结果信息
     * @throws Exception 异常
     */
    public static String addFavorite(Long userId, String key, Integer favoriteType, String itemId, Long authorId, String authorNickName, ImageItem authorAvatar, ImageItem cover, String title) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1077);
            }
            if(!Constant.checkFavoriteType(favoriteType)) {
                return QinShihuangResult.getResult(ErrorCode.FAVORITETYPE_INVALID_1077);
            }
            if(!CommonAPI.isObjectId(itemId)) {
                return QinShihuangResult.getResult(ErrorCode.ITEMID_INVALID_1077);
            }
            Query<Favorite> query = ds.createQuery(Favorite.class).field(Favorite.fd_userId).equal(userId).field(Favorite.fd_itemId).equal(new ObjectId(itemId));
            UpdateOperations<Favorite> ops = ds.createUpdateOperations(Favorite.class).set(Favorite.fd_id, new ObjectId()).set(Favorite.fd_userId, userId).set(Favorite.fd_favoriteType, favoriteType)
                    .set(Favorite.fd_itemId, new ObjectId(itemId)).set(Favorite.fd_title, title).set(Favorite.fd_favoriteTime, System.currentTimeMillis());
            if(authorId != null)
                ops.set(Favorite.fd_authorId, authorId);
            if(authorNickName != null)
                ops.set(Favorite.fd_authorNickName, authorNickName);
            if(authorAvatar != null)
                ops.set(Favorite.fd_authorAvatar, authorAvatar);
            if(cover != null)
                ops.set(Favorite.fd_cover, cover);
            Favorite favorite = ds.findAndModify(query, ops, false, true);
            // 更新收藏数
            updateFavorCnt(itemId, favoriteType, true);
            return QinShihuangResult.ok(FavoriteFormatter.getMapper().valueToTree(favorite));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 更新点赞数
     * @param itemId 点赞对象id
     * @param voteType 点赞类型
     * @param isAdd 是否点赞，true表示加1，false表示减1
     * @throws Exception 异常
     */
    public static void updateVoteCnt(String itemId, Integer voteType, Boolean isAdd) throws Exception {
        try {
            switch (voteType) {
                case Constant.VOTE_POST:
                    Query<Post> queryPost = ds.createQuery(Post.class).field(Post.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Post> opsPost = ds.createUpdateOperations(Post.class);
                    if (isAdd) opsPost.inc(Post.fd_voteCnt);
                    else opsPost.dec(Post.fd_voteCnt);
                    ds.updateFirst(queryPost, opsPost);
                    break;
                case Constant.VOTE_TRACE:
                    Query<Trace> queryTrace = ds.createQuery(Trace.class).field(Trace.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Trace> opsTrace = ds.createUpdateOperations(Trace.class);
                    if (isAdd) opsTrace.inc(Trace.fd_voteCnt);
                    else opsTrace.dec(Trace.fd_voteCnt);
                    ds.updateFirst(queryTrace, opsTrace);
                    break;
                case Constant.VOTE_TRIPPLAN:
                    Query<TripPlan> queryTripPlan = ds.createQuery(TripPlan.class).field(TripPlan.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<TripPlan> opsTripPlan = ds.createUpdateOperations(TripPlan.class);
                    if (isAdd) opsTripPlan.inc(TripPlan.fd_voteCnt);
                    else opsTripPlan.dec(TripPlan.fd_voteCnt);
                    ds.updateFirst(queryTripPlan, opsTripPlan);
                    break;
                case Constant.VOTE_ACTIVITY:
                    Query<Activity> queryActivity = ds.createQuery(Activity.class).field(Activity.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Activity> opsActivity = ds.createUpdateOperations(Activity.class);
                    if (isAdd) opsActivity.inc(Activity.fd_voteCnt);
                    else opsActivity.dec(Activity.fd_voteCnt);
                    ds.updateFirst(queryActivity, opsActivity);
                    break;
                case Constant.VOTE_QUESTION:
                    Query<Question> queryQuestion = ds.createQuery(Question.class).field(Question.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Question> opsQuestion = ds.createUpdateOperations(Question.class);
                    if (isAdd) opsQuestion.inc(Question.fd_voteCnt);
                    else opsQuestion.dec(Question.fd_voteCnt);
                    ds.updateFirst(queryQuestion, opsQuestion);
                    break;
                case Constant.VOTE_ANSWER:
                    Query<Answer> queryAnswer = ds.createQuery(Answer.class).field(Answer.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Answer> opsAnswer = ds.createUpdateOperations(Answer.class);
                    if (isAdd) opsAnswer.inc(Answer.fd_voteCnt);
                    else opsAnswer.dec(Answer.fd_voteCnt);
                    ds.updateFirst(queryAnswer, opsAnswer);
                    break;
                case Constant.VOTE_MOMENT:
                    Query<Moment> queryMoment = ds.createQuery(Moment.class).field(Moment.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<Moment> opsMoment = ds.createUpdateOperations(Moment.class);
                    if (isAdd) opsMoment.inc(Moment.fd_voteCnt);
                    else opsMoment.dec(Moment.fd_voteCnt);
                    ds.updateFirst(queryMoment, opsMoment);
                    break;
                case Constant.VOTE_TRAVELNOTE:
                    Query<TravelNote> queryTravelNote = ds.createQuery(TravelNote.class).field(TravelNote.fd_id).equal(new ObjectId(itemId));
                    UpdateOperations<TravelNote> opsTravelNote = ds.createUpdateOperations(TravelNote.class);
                    if (isAdd) opsTravelNote.inc(TravelNote.fd_voteCnt);
                    else opsTravelNote.dec(TravelNote.fd_voteCnt);
                    ds.updateFirst(queryTravelNote, opsTravelNote);
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取消收藏
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param itemId 取消对象id
     * @param favoriteType 收藏类型
     * @return 结果
     * @throws Exception 异常
     */
    public static String cancelFavorite(Long userId, String key, String itemId, Integer favoriteType) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1078);
            }
            Query<Favorite> query = ds.createQuery(Favorite.class).field(Favorite.fd_userId).equal(userId).field(Favorite.fd_itemId).equal(new ObjectId(itemId));
            ds.delete(query);
            // 更新收藏数
            updateFavorCnt(itemId, favoriteType, false);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取得收藏列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 收藏列表
     * @throws Exception 异常
     */
    public static String getFavorites(Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1079);
            }
            Query<Favorite> query = ds.createQuery(Favorite.class).field(Favorite.fd_userId).equal(userId);
            List<Favorite> favorites = query.asList();
            if(favorites == null)
                return QinShihuangResult.ok(FavoriteFormatter.getMapper().valueToTree(new ArrayList<Favorite>()));
            else
                return QinShihuangResult.ok(FavoriteFormatter.getMapper().valueToTree(favorites));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 点赞
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param voteType 点赞类型
     * @param itemId 点赞对象id
     * @return 结果
     * @throws Exception 异常
     */
    public static String addVote(Long userId, String key, Integer voteType, String itemId) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1094);
            }
            if(!Constant.checkVoteType(voteType)) {
                return QinShihuangResult.getResult(ErrorCode.VOTETYPE_INVALID_1094);
            }
            if(!CommonAPI.isObjectId(itemId)) {
                return QinShihuangResult.getResult(ErrorCode.ITEMID_INVALID_1094);
            }
            Query<Vote> query = ds.createQuery(Vote.class).field(Vote.fd_userId).equal(userId).field(Vote.fd_itemId).equal(new ObjectId(itemId));
            UpdateOperations<Vote> ops = ds.createUpdateOperations(Vote.class).set(Vote.fd_id, new ObjectId()).set(Vote.fd_userId, userId).set(Vote.fd_voteType, voteType)
                    .set(Vote.fd_itemId, new ObjectId(itemId)).set(Vote.fd_voteTime, System.currentTimeMillis());
            ds.updateFirst(query, ops, true);
            updateVoteCnt(itemId, voteType, true);
            // TODO 发一条消息提醒
            return QinShihuangResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取消点赞
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param itemId 取消点赞对象id
     * @param voteType 点赞类型
     * @return 结果
     * @throws Exception 异常
     */
    public static String cancelVote(Long userId, String key, String itemId, Integer voteType) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1095);
            }
            Query<Vote> query = ds.createQuery(Vote.class).field(Vote.fd_userId).equal(userId).field(Vote.fd_itemId).equal(new ObjectId(itemId));
            ds.delete(query);
            updateVoteCnt(itemId, voteType, false);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取得点赞列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 点赞列表
     * @throws Exception 异常
     */
    public static String getVotes(Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1096);
            }
            Query<Vote> query = ds.createQuery(Vote.class).field(Vote.fd_userId).equal(userId);
            List<Vote> votes = query.asList();
            if(votes == null)
                return QinShihuangResult.ok(VoteFormatter.getMapper().valueToTree(new ArrayList<Vote>()));
            else
                return QinShihuangResult.ok(VoteFormatter.getMapper().valueToTree(votes));
        } catch (Exception e) {
            throw e;
        }
    }
}
