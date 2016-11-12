package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.guide.GuideFormatter;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * 攻略核心实现�������
 * Created by gaomin on 2016/11/11.
 */
public class GuideAPI {
    /**
     * 取得数据库对象���ݿ����
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得攻略列表
     * @return
     * @throws Exception
     */
    public static String getGuides() throws Exception{
        Query<Guide> query = ds.createQuery(Guide.class);
        List<Guide> result;
        try{
            result = query.asList();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        if (result==null){
            return QinShihuangResult.ok(GuideFormatter.getMapper().valueToTree(new ArrayList<Guide>()));
        }
        else{
            return QinShihuangResult.ok(GuideFormatter.getMapper().valueToTree(result));
        }
    }

    /**
     * 取得攻略详情
     * @param guideId
     * @return
     * @throws Exception
     */
    public static String getGuide(Long guideId) throws Exception{
        Query<Guide> query = ds.createQuery(Guide.class).field(Guide.fd_id).equal(guideId);
        try{
            Guide guide = query.get();
            if (guide==null){
                return QinShihuangResult.getResult(ErrorCode.GUIDE_NOT_EXIST_1019);
            }
            else{
                return QinShihuangResult.ok(GuideFormatter.getMapper().valueToTree(guide));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
