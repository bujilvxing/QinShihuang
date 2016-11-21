package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.guide.GuideFormatter;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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
                return QinShihuangResult.getResult(ErrorCode.GUIDE_NOT_EXIST_1019);
            } else {
                return QinShihuangResult.ok(GuideFormatter.getMapper().valueToTree(guide));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
