package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.guide.GuideBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.im.ChatgroupBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.marketplace.CommodityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.ViewspotBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.timeline.MomentBasicFormatter;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.Application;
import com.bjlx.QinShihuang.model.misc.Feedback;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.quora.Question;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     * @param query 关键字
     * @return 时间线列表
     */
    public static List<Moment> queryMoments(String query) throws Exception {
        try {
            return ds.createQuery(Moment.class).field(Moment.fd_comment).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Moment.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索商品
     * @param query 关键字
     * @return 商品列表
     */
    public static List<Commodity> queryCommodities(String query) throws Exception {
        try {
            return ds.createQuery(Commodity.class).field(Commodity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Commodity.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索攻略
     * @param query 关键字
     * @return 攻略列表
     */
    public static List<Guide> queryGuides(String query) throws Exception {
        try {
            return ds.createQuery(Guide.class).field(Guide.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Guide.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索景点
     * @param query 关键字
     * @return 景点列表
     */
    public static List<Viewspot> queryViewspots(String query) throws Exception {
        try {
            return ds.createQuery(Viewspot.class).field(Viewspot.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Viewspot.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索足迹
     * @param query 关键字
     * @return 足迹列表
     */
    public static List<Trace> queryTraces(String query) throws Exception {
        try {
            return ds.createQuery(Trace.class).field(Trace.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Trace.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索行程规划
     * @param query 关键字
     * @return 行程规划列表
     */
    public static List<TripPlan> queryTripPlans(String query) throws Exception {
        try {
            return ds.createQuery(TripPlan.class).field(TripPlan.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", TripPlan.fd_updateTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索问题
     * @param query 关键字
     * @return 问题列表
     */
    public static List<Question> queryQuestions(String query) throws Exception {
        try {
            return ds.createQuery(Question.class).field(Question.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Question.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索活动
     * @param query 关键
     * @return 活动列表
     */
    public static List<Activity> queryActivities(String query) throws Exception {
        try {
            return ds.createQuery(Activity.class).field(Activity.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Activity.fd_endTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索游记
     * @param query 关键
     * @return 游记列表
     */
    public static List<TravelNote> queryTravelNotes(String query) throws Exception {
        try {
            return ds.createQuery(TravelNote.class).field(TravelNote.fd_title).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", TravelNote.fd_publishTime)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索美食
     * @param query 关键
     * @return 美食列表
     */
    public static List<Restaurant> queryRestaurants(String query) throws Exception {
        try {
            return ds.createQuery(Restaurant.class).field(Restaurant.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Restaurant.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索宾馆
     * @param query 关键
     * @return 宾馆列表
     */
    public static List<Hotel> queryHotels(String query) throws Exception {
        try {
            return ds.createQuery(Hotel.class).field(Hotel.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Hotel.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 搜索购物
     * @param query 关键
     * @return 购物列表
     */
    public static List<Shopping> queryShoppings(String query) throws Exception {
        try {
            return ds.createQuery(Shopping.class).field(Shopping.fd_zhName).containsIgnoreCase(query).offset(Constant.SEARCH_ALL_OFFSET)
                    .limit(Constant.SEARCH_ALL_LIMIT).order(String.format("-%s", Shopping.fd_hotness)).asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 全站搜索
     * @param query 搜索关键词
     * @return 结果
     * @throws Exception 异常
     */
    public static String searchAll(String query) throws Exception {
        ObjectNode result = mapper.createObjectNode();
        try {
            List<Moment> moments = queryMoments(query);
            if(moments != null)
                result.set("moments", MomentBasicFormatter.getMapper().valueToTree(moments));
            List<Commodity> commodities = queryCommodities(query);
            if(commodities != null)
                result.set("commodities", CommodityBasicFormatter.getMapper().valueToTree(commodities));
            List<Guide> guides = queryGuides(query);
            if(guides != null)
                result.set("guides", GuideBasicFormatter.getMapper().valueToTree(guides));
            List<Viewspot> viewspots = queryViewspots(query);
            if(viewspots != null)
                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));

//            List<Trace> traces = queryTraces(query);
//            if(traces != null)
//                result.set("traces", TraceBasicFormatter.getMapper().valueToTree(traces));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            List<Viewspot> viewspots = queryViewspots(query);
//            if(viewspots != null)
//                result.set("viewspots", ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
//            queryTraces  queryTripPlans queryQuestions queryActivities queryTravelNotes queryRestaurants queryHotels queryShoppings
//            * @param query 搜索关键词
//            * @param momemt 是否搜索时间线
//            * @param commodity 是否搜索商品
//            * @param guide 是否搜索攻略
//            * @param viewspot 是否搜索景点
//            * @param trace 是否搜索足迹
//            * @param tripplan 是否搜索行程规划
//            * @param quora 是否搜索问答
//            * @param activity 是否搜索活动
//            * @param travelnote 是否搜索游记
//            * @param restaurant 是否搜索美食
//            * @param hotel 是否搜索宾馆
//            * @param shopping 是否搜索购物
            return QinShihuangResult.ok();
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
     * @param tripplan 是否搜索行程规划
     * @param quora 是否搜索问答
     * @param activity 是否搜索活动
     * @param post 是否搜索帖子
     * @param travelnote 是否搜索游记
     * @param restaurant 是否搜索美食
     * @param hotel 是否搜索宾馆
     * @param shopping 是否搜索购物
     * @return 结果
     * @throws Exception 异常
     */
    public static String searchCondition(String query , Boolean momemt, Boolean commodity, Boolean guide, Boolean viewspot,
                                         Boolean trace, Boolean tripplan, Boolean quora, Boolean activity, Boolean post, Boolean travelnote,
                                         Boolean restaurant, Boolean hotel, Boolean shopping) throws Exception {
        return null;

    }
}
