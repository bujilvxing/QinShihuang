package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.activity.ActivityFormatter;
import com.bjlx.QinShihuang.core.formatter.activity.TicketFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.activity.Joiner;
import com.bjlx.QinShihuang.model.activity.Ticket;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.requestmodel.ActivityReq;
import com.bjlx.QinShihuang.requestmodel.ActivityUpdateReq;
import com.bjlx.QinShihuang.requestmodel.TicketReq;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1031);
            }
            UserInfo creator = CommonAPI.getUserBasicById(userId);
            if(creator == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1031);
            Activity activity = new Activity(activityReq.getTitle(), activityReq.getMaxNum(), activityReq.getStartTime(), activityReq.getEndTime(), activityReq.getAddress(),
                    activityReq.getCover(), activityReq.getTheme(), activityReq.getCategory(), activityReq.getVisiable(), activityReq.getDesc(), activityReq.isFree());
            activity.setCreator(creator);
            if (activityReq.getPosters() != null && !activityReq.getPosters().isEmpty())
                activity.setPosters(activityReq.getPosters());
            if(activityReq.getTags() != null && !activityReq.getTags().isEmpty())
                activity.setTags(activityReq.getTags());
            if(activityReq.isCellphoneList() != null)
                activity.setIsCellphoneList(activityReq.isCellphoneList());
            if(activityReq.isEmail() != null)
                activity.setIsEmail(activityReq.isEmail());
            if(activityReq.isFax() != null)
                activity.setIsFax(activityReq.isFax());
            if(activityReq.isPhoneList() != null)
                activity.setIsPhoneList(activityReq.isPhoneList());
            if(activityReq.isQq() != null)
                activity.setIsQq(activityReq.isQq());
            if(activityReq.isSina() != null)
                activity.setIsSina(activityReq.isSina());
            if(activityReq.isWebsite() != null)
                activity.setIsWebsite(activityReq.isWebsite());
            if(activityReq.isWeixin() != null)
                activity.setIsWeixin(activityReq.isWeixin());
            if(activityReq.getTicketIds() != null && !activityReq.getTicketIds().isEmpty()) {
                List<ObjectId> ticketIds = new ArrayList<ObjectId>();
                for(String ticketId : activityReq.getTicketIds()) {
                    ticketIds.add(new ObjectId(ticketId));
                }
                activity.setTicketIds(ticketIds);
            }
            ds.save(activity);
            return QinShihuangResult.ok(ActivityFormatter.getMapper().valueToTree(activity));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得活动列表
     * @param start 是否开始
     * @param end 是否结束
     * @param theme 活动主题
     * @param category 活动分类
     * @param free 是否免费
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     * @throws Exception 异常
     */
    public static String getActivities(Boolean start, Boolean end, String theme, String category, Boolean free, Integer offset, Integer limit) throws Exception {
        try {
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL).field(Activity.fd_visiable).equal(Constant.ACTIVITY_VISIABLE);
            Long currentTime = System.currentTimeMillis();
            // 尚未开始
            if(start != null && start.equals(Boolean.FALSE)) {
                query.field(Activity.fd_startTime).greaterThanOrEq(currentTime);
            }
            // 尚未结束
            if(end != null && end.equals(Boolean.FALSE))
                query.field(Activity.fd_endTime).greaterThanOrEq(currentTime);

            if(theme != null)
                query.field(Activity.fd_theme).equal(theme);
            if(category != null)
                query.field(Activity.fd_category).equal(category);
            if(free != null)
                query.field(Activity.fd_isFree).equal(free);

            query.order(String.format("-%s", Activity.fd_publishTime)).offset(offset).limit(limit);
            List<Activity> activities = query.asList();
            if(activities == null || activities.isEmpty())
                return QinShihuangResult.ok(ActivityBasicFormatter.getMapper().valueToTree(new ArrayList<Activity>()));
            else
                return QinShihuangResult.ok(ActivityBasicFormatter.getMapper().valueToTree(activities));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得活动详情
     * @param activityId 活动id
     * @return 活动详情
     * @throws Exception 异常
     */
    public static String getActivityById(String activityId) throws Exception {
        try {
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL);
            Activity activity = query.get();
            if(activity == null)
                return QinShihuangResult.getResult(ErrorCode.ACTIVITY_NOT_EXIST_1033);
            else {
                ObjectNode result = CommonAPI.mapper.createObjectNode();
                result.set("activity", ActivityFormatter.getMapper().valueToTree(activity));
                List<ObjectId> ticketIds = activity.getTicketIds();
                if(ticketIds != null && !ticketIds.isEmpty()) {
                    Query<Ticket> ticketQuery = ds.createQuery(Ticket.class).field(Ticket.fd_id).in(ticketIds).field(Ticket.fd_status).equal(Constant.TICKET_NORMAL);
                    List<Ticket> tickets = ticketQuery.asList();
                    if(tickets != null && !tickets.isEmpty()) {
                        result.set("tickets", TicketFormatter.getMapper().valueToTree(tickets));
                    }
                }
                return QinShihuangResult.ok(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户活动列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 活动列表
     * @throws Exception 异常
     */
    public static String getUserActivities(Long userId, String key, Integer offset, Integer limit) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1034);
            }

            Query<Activity> queryOwner = ds.createQuery(Activity.class).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL).field(Activity.fd_creatorId).equal(userId)
                    .order(String.format("-%s", Activity.fd_publishTime));
            if(offset != null)
                if(offset >= 0)
                    queryOwner.offset(offset);
            if(limit != null)
                if(limit >= 0)
                    queryOwner.limit(limit);
            // 用户发布的活动
            List<Activity> publishActivities = queryOwner.asList();

            Query<Activity> queryJoin = ds.createQuery(Activity.class).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL).field(Activity.fd_applicantInfos_userId).hasThisOne(userId)
                    .order(String.format("-%s", Activity.fd_publishTime));
            if(offset != null)
                if(offset >= 0)
                    queryJoin.offset(offset);
            if(limit != null)
                if(limit >= 0)
                    queryJoin.limit(limit);

            // 用户参与的活动
            List<Activity> joinActivities = queryJoin.asList();
            ObjectNode result = CommonAPI.mapper.createObjectNode();

            if(publishActivities == null || publishActivities.isEmpty())
                result.set("publishActivities", ActivityBasicFormatter.getMapper().valueToTree(new ArrayList<Activity>()));
            else
                result.set("publishActivities", ActivityBasicFormatter.getMapper().valueToTree(publishActivities));

            if(joinActivities == null || joinActivities.isEmpty())
                result.set("joinActivities", ActivityBasicFormatter.getMapper().valueToTree(new ArrayList<Activity>()));
            else
                result.set("joinActivities", ActivityBasicFormatter.getMapper().valueToTree(joinActivities));
            return QinShihuangResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 报名活动
     * @param activityId 活动id
     * @param contact 参与人联系方式
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String joinActivity(String activityId, Contact contact, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1035);
            }
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_id).equal(new ObjectId(activityId)).field(Activity.fd_creatorId).notEqual(userId).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL);
            Joiner joiner = new Joiner(userId, contact.getPhoneList(), contact.getCellphoneList(), contact.getQq(), contact.getWeixin(), contact.getSina(), contact.getFax(), contact.getEmail(), contact.getWebsite());
            UpdateOperations<Activity> ops = ds.createUpdateOperations(Activity.class).addToSet(Activity.fd_applicantInfos, joiner);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取消报名活动
     * @param activityId 活动id
     * @param contact 参与人联系方式
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String quitActivity(String activityId, Contact contact, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1036);
            }
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_id).equal(new ObjectId(activityId)).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL);
            Joiner joiner = new Joiner(userId, contact.getPhoneList(), contact.getCellphoneList(), contact.getQq(), contact.getWeixin(), contact.getSina(), contact.getFax(), contact.getEmail(), contact.getWebsite());
            UpdateOperations<Activity> ops = ds.createUpdateOperations(Activity.class).removeAll(Activity.fd_applicantInfos, joiner);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新活动
     * @param activityId 活动id
     * @param activityUpdateReq 更新活动参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 更新后的活动详情
     * @throws Exception 异常
     */
    public static String updateActivity(String activityId, ActivityUpdateReq activityUpdateReq, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1037);
            }
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_id).equal(new ObjectId(activityId)).field(Activity.fd_status).equal(Constant.ACTIVITY_NORMAL);
            UpdateOperations<Activity> ops = ds.createUpdateOperations(Activity.class);

            if(activityUpdateReq.getTitle() != null)
                ops.set(Activity.fd_title, activityUpdateReq.getTitle());

            if(activityUpdateReq.getMaxNum() != null)
                ops.set(Activity.fd_maxNum, activityUpdateReq.getMaxNum());

            if(activityUpdateReq.getStartTime() != null)
                ops.set(Activity.fd_startTime, activityUpdateReq.getStartTime());

            if(activityUpdateReq.getEndTime() != null)
                ops.set(Activity.fd_endTime, activityUpdateReq.getEndTime());

            if(activityUpdateReq.getAddress() != null)
                ops.set(Activity.fd_address, activityUpdateReq.getAddress());

            if(activityUpdateReq.getCover() != null)
                ops.set(Activity.fd_cover, activityUpdateReq.getCover());

            if(activityUpdateReq.getMaxNum() != null)
                ops.set(Activity.fd_maxNum, activityUpdateReq.getMaxNum());

            if(activityUpdateReq.getPosters() != null && !activityUpdateReq.getPosters().isEmpty())
                ops.set(Activity.fd_posters, activityUpdateReq.getPosters());

            if(activityUpdateReq.getTheme() != null)
                ops.set(Activity.fd_theme, activityUpdateReq.getTheme());

            if(activityUpdateReq.getCategory() != null)
                ops.set(Activity.fd_category, activityUpdateReq.getCategory());

            if(activityUpdateReq.getTags() != null && !activityUpdateReq.getTags().isEmpty())
                ops.set(Activity.fd_tags, activityUpdateReq.getTags());

            if(activityUpdateReq.getVisiable() != null)
                ops.set(Activity.fd_visiable, activityUpdateReq.getVisiable());

            if(activityUpdateReq.getDesc() != null)
                ops.set(Activity.fd_desc, activityUpdateReq.getDesc());

            if(activityUpdateReq.isFree() != null)
                ops.set(Activity.fd_isFree, activityUpdateReq.isFree());

            if(activityUpdateReq.getTicketIds() != null && !activityUpdateReq.getTicketIds().isEmpty()) {
                List<ObjectId> ticketIds = new ArrayList<ObjectId>();
                for(String ticketId : activityUpdateReq.getTicketIds()) {
                    ticketIds.add(new ObjectId(ticketId));
                }
                ops.set(Activity.fd_ticketIds, ticketIds);
            }

            if(activityUpdateReq.isCellphoneList() != null)
                ops.set(Activity.fd_isCellphoneList, activityUpdateReq.isCellphoneList());
            if(activityUpdateReq.isEmail() != null)
                ops.set(Activity.fd_isEmail, activityUpdateReq.isEmail());
            if(activityUpdateReq.isFax() != null)
                ops.set(Activity.fd_isFax, activityUpdateReq.isFax());
            if(activityUpdateReq.isPhoneList() != null)
                ops.set(Activity.fd_isPhoneList, activityUpdateReq.isPhoneList());
            if(activityUpdateReq.isQq() != null)
                ops.set(Activity.fd_isQq, activityUpdateReq.isQq());
            if(activityUpdateReq.isSina() != null)
                ops.set(Activity.fd_isSina, activityUpdateReq.isSina());
            if(activityUpdateReq.isWebsite() != null)
                ops.set(Activity.fd_isWebsite, activityUpdateReq.isWebsite());
            if(activityUpdateReq.isWeixin() != null)
                ops.set(Activity.fd_isWeixin, activityUpdateReq.isWeixin());

            ds.findAndModify(query, ops, false);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 添加门票
     * @param ticketReq 门票参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 门票信息
     * @throws Exception 异常
     */
    public static String addTicket(TicketReq ticketReq, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1038);
            }

            Ticket ticket = new Ticket(ticketReq.getTitle(), ticketReq.getMaxNum(), userId);
            if(ticketReq.getDesc() != null)
                ticket.setDesc(ticketReq.getDesc());
            if(!ticketReq.isFree()) {
                if(ticketReq.getPrice() == null)
                    return QinShihuangResult.getResult(ErrorCode.PRICE_NULL_1038);
                else
                    ticket.setPrice(ticketReq.getPrice());
                if(ticketReq.getMarketPrice() != null)
                    ticket.setMarketPrice(ticketReq.getMarketPrice());
                ticket.setRefundWay(ticketReq.getRefundWay() == null ? Constant.REFUND_ORIGIN : ticketReq.getRefundWay());
                if(ticketReq.getRefundDesc() != null)
                    ticket.setRefundDesc(ticketReq.getRefundDesc());
            }
            ticket.setFree(ticketReq.isFree());
            ds.save(ticket);
            return QinShihuangResult.ok(TicketFormatter.getMapper().valueToTree(ticket));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除门票
     * @param ticketId 门票id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String removeTicket(String ticketId, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1039);
            }

            // 检查门票是否被活动使用
            Query<Activity> query = ds.createQuery(Activity.class).field(Activity.fd_ticketIds).hasThisOne(new ObjectId(ticketId));
            Activity activity = query.get();
            if(activity != null)
                return QinShihuangResult.getResult(ErrorCode.TICKET_USED_1039);
            // 删除门票
            Query<Ticket> ticketQuery = ds.createQuery(Ticket.class).field(Ticket.fd_id).equal(new ObjectId(ticketId)).field(Ticket.fd_creatorId).equal(userId)
                    .field(Ticket.fd_status).equal(Constant.TICKET_NORMAL);
            UpdateOperations ops = ds.createUpdateOperations(Ticket.class).set(Ticket.fd_status, Constant.TICKET_UNENABLE);
            ds.updateFirst(ticketQuery, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 修改门票
     * @param ticketId 门票id
     * @param ticketReq 门票参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String updateTicket(String ticketId, TicketReq ticketReq, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1040);
            }
            Query<Ticket> query = ds.createQuery(Ticket.class).field(Ticket.fd_id).equal(new ObjectId(ticketId)).field(Ticket.fd_status).equal(Constant.TICKET_NORMAL).field(Ticket.fd_creatorId).equal(userId);
            UpdateOperations<Ticket> ops = ds.createUpdateOperations(Ticket.class).set(Ticket.fd_updateTime, System.currentTimeMillis());
            if(ticketReq.getTitle() != null)
                ops.set(Ticket.fd_title, ticketReq.getTitle());

            if(ticketReq.getPrice() != null)
                ops.set(Ticket.fd_price, ticketReq.getPrice());

            if(ticketReq.getMarketPrice() != null)
                ops.set(Ticket.fd_marketPrice, ticketReq.getMarketPrice());

            if(ticketReq.isFree() != null)
                ops.set(Ticket.fd_free, ticketReq.isFree());

            if(ticketReq.getRefundWay() != null)
                ops.set(Ticket.fd_refundWay, ticketReq.getRefundWay());

            if(ticketReq.getRefundDesc() != null)
                ops.set(Ticket.fd_refundDesc, ticketReq.getRefundDesc());

            if(ticketReq.getDesc() != null)
                ops.set(Ticket.fd_desc, ticketReq.getDesc());

            if(ticketReq.getTitle() != null)
                ops.set(Ticket.fd_title, ticketReq.getTitle());

            if(ticketReq.getMaxNum() != null)
                ops.set(Ticket.fd_maxNum, ticketReq.getMaxNum());

            Ticket ticket = ds.findAndModify(query, ops, false);
            if(ticket == null)
                return QinShihuangResult.getResult(ErrorCode.TICKET_NOT_EXIST_1040);
            else
                return QinShihuangResult.ok(TicketFormatter.getMapper().valueToTree(ticket));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得门票详情
     * @param ticketId 门票id
     * @return 门票信息
     * @throws Exception 异常
     */
    public static String getTicket(String ticketId) throws Exception {
        try {
            Query<Ticket> query = ds.createQuery(Ticket.class).field(Ticket.fd_id).equal(new ObjectId(ticketId)).field(Ticket.fd_status).equal(Constant.TICKET_NORMAL);
            Ticket ticket = query.get();
            if(ticket == null)
                return QinShihuangResult.getResult(ErrorCode.TICKET_NOT_EXIST_1041);
            else
                return QinShihuangResult.ok(TicketFormatter.getMapper().valueToTree(ticket));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户门票列表
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 门票列表
     * @throws Exception 异常
     */
    public static String getTickets(Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1042);
            }
            Query<Ticket> query = ds.createQuery(Ticket.class).field(Ticket.fd_creatorId).equal(userId).field(Ticket.fd_status).equal(Constant.TICKET_NORMAL);
            List<Ticket> tickets = query.asList();
            if(tickets == null || tickets.isEmpty())
                return QinShihuangResult.ok(TicketFormatter.getMapper().valueToTree(new ArrayList<Ticket>()));
            else
                return QinShihuangResult.ok(TicketFormatter.getMapper().valueToTree(tickets));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 数据
     */
    public static String addActivity() {
        Ticket ticket = new Ticket(new ObjectId(), "门票1", 160.1, 150.1, false, 1, "退款到平台公共账号", "票种说明", 100, 100001L);
        ds.save(ticket);
        Long currentTime = System.currentTimeMillis();
        Address address = new Address("江西省", "南昌市", "昌北区", "机场路16号", "333133");
        Joiner joiner = new Joiner(100001L, Arrays.asList("010-62737359"), Arrays.asList("13811111111"), "1234213123", "mofashi", "312413123", "010-62737358", "3813231231@qq.com", "www.bjlx.com");
        try {
            UserInfo creator = CommonAPI.getUserBasicById(100001L);
            Activity activity = new Activity("活动标题1", 100, 20, currentTime, currentTime + 24 * 60 * 1000L, address, AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar),
                    "音乐", "摇滚", Arrays.asList("摇滚", "崔健"), Constant.ACTIVITY_VISIABLE, "", Arrays.asList(joiner), Arrays.asList(ticket.getId()), false, creator);
            ds.save(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"msg\":\"success\"}";
    }
}
