package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.guide.GuideFormatter;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.Arrays;

/**
 * 攻略核心实现
 * Created by gaomin on 2016/11/11.
 */
public class GuideAPI {
    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得攻略详情
     * @param guideId 攻略id
     * @return 攻略详情信息
     * @throws Exception 异常
     */
    public static String getGuide(String guideId) throws Exception{
        Query<Guide> query = ds.createQuery(Guide.class).field(Guide.fd_id).equal(new ObjectId(guideId));
        try{
            Guide guide = query.get();
            if (guide==null){
                return QinShihuangResult.getResult(ErrorCode.GUIDE_NOT_EXIST_1020);
            } else {
                return QinShihuangResult.ok(GuideFormatter.getMapper().valueToTree(guide));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 数据
     */
    public static String addGuide() {

        Hotel hotel = new Hotel(new ObjectId().toString(), AccountAPI.defaultUserAvatar, "不羁客栈", "BuJiLvXing Hotel", 400, "http://xxx", 200);
        Shopping shopping = new Shopping(new ObjectId().toString(), AccountAPI.defaultUserAvatar, "优衣库", "uniqlo", "http://xxx", 200, 300);
        Restaurant restaurant = new Restaurant(new ObjectId().toString(), AccountAPI.defaultUserAvatar, "煌上煌", "Huang Shang Huang", "http://xxx", 80, 120);
        Viewspot viewspot = new Viewspot(new ObjectId().toString(), AccountAPI.defaultUserAvatar, "庐山", "http://xxx", "LuShan", 180, 200);
        TripPlan tripPlan = new TripPlan(100001L, "魔法师", AccountAPI.defaultUserAvatar, "庐山行程规划", AccountAPI.defaultUserAvatar, "描述", new ObjectId(), 100002L, "逍遥", AccountAPI.defaultUserAvatar);
        Activity activity = new Activity(new ObjectId().toString(), "活动标题1", "音乐", "摇滚", 1, AccountAPI.defaultUserAvatar, true);
        Guide guide = new Guide(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), System.currentTimeMillis(),
                "攻略1", "攻略描述1", "4~5月", "攻略提示", Arrays.asList(hotel), Arrays.asList(shopping), Arrays.asList(restaurant), Arrays.asList(viewspot), Arrays.asList(tripPlan), Arrays.asList(activity), "攻略摘要1", "http://XXX");
        ds.save(guide);
        return "{\"msg\":\"success\"}";
    }
}
