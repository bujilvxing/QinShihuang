package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.timeline.MomentBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.timeline.MomentFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 时间线核心实现
 * Created by xiaozhi on 2016/11/18.
 */
public class MomentAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    public static String getMoments(Long userId, String key, Integer offset, Integer limit, Long latestTime, Long earliestTime) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1038);
            }
            // 根据用户id查找关注人的id列表
            List<CriteriaContainerImpl> criterias = new ArrayList<>();
            criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userA).equal(userId).criteria(Relationship.fd_followingB).equal(true));
            criterias.add(ds.createQuery(Relationship.class).field(Relationship.fd_userB).equal(userId).criteria(Relationship.fd_followingA).equal(true));
            Query<Relationship> query = ds.createQuery(Relationship.class);

            query.or(criterias.toArray(new CriteriaContainerImpl[criterias.size()]));
            query.offset(offset).limit(limit);
            List<Relationship> relationships = null;
            try {
                relationships = query.asList();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            // 关注的人的id集合
            Set<Long> followingIds = new HashSet<Long>();
            if(relationships != null) {
                for (Relationship relationship : relationships) {
                    followingIds.add(relationship.getUserA());
                    followingIds.add(relationship.getUserB());
                }
            }
            followingIds.add(userId);

            // 发表人为自己或者关注的人
            Query<Moment> queryMoment = ds.createQuery(Moment.class).field(Moment.fd_userId).in(followingIds);
            if(latestTime == null) {
                query.field(Moment.fd_publishTime).lessThan(earliestTime).offset(offset).limit(limit)
                        .order(String.format("-%s", Moment.fd_publishTime));
            } else {
                query.field(Moment.fd_publishTime).greaterThan(latestTime).offset(offset).limit(limit)
                        .order(String.format("-%s", Moment.fd_publishTime));
            }
            List<Moment> moments = queryMoment.asList();
            if(moments == null)
                return QinShihuangResult.ok(MomentFormatter.getMapper().valueToTree(new ArrayList<Moment>()));
            else
                return QinShihuangResult.ok(MomentFormatter.getMapper().valueToTree(moments));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 查看某个人的朋友圈
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param targetId 查看对象的用户id
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @param latestTime 最晚时间
     * @param earliestTime 最早时间
     * @return 信息列表
     * @throws Exception 异常
     */
    public static String getMomentsById(Long userId, String key, Long targetId, Integer offset, Integer limit, Long latestTime, Long earliestTime) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1080);
            }
            // 发表人为自己或者关注的人
            Query<Moment> query = ds.createQuery(Moment.class).field(Moment.fd_userId).equal(targetId);
            if(latestTime == null) {
                query.field(Moment.fd_publishTime).lessThan(earliestTime).offset(offset).limit(limit)
                        .order(String.format("-%s", Moment.fd_publishTime));
            } else {
                query.field(Moment.fd_publishTime).greaterThan(latestTime).offset(offset).limit(limit)
                        .order(String.format("-%s", Moment.fd_publishTime));
            }
            List<Moment> moments = query.asList();
            if(moments == null)
                return QinShihuangResult.ok(MomentFormatter.getMapper().valueToTree(new ArrayList<Moment>()));
            else
                return QinShihuangResult.ok(MomentFormatter.getMapper().valueToTree(moments));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
