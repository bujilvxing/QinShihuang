package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.marketplace.CommodityFormatter;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 商品核心实现
 * Created by pengyt on 2016/11/12.
 */
public class CommodityAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 根据商品id取得商品详情
     * @param id 商品id
     * @return 商品详情
     */
    public static String getCommodityById(String id) throws Exception {
        Query<Commodity> query = ds.createQuery(Commodity.class).field(Commodity.fd_id).equal(new ObjectId(id));
        try {
            Commodity commodity = query.get();
            if(commodity == null) {
                return QinShihuangResult.getResult(ErrorCode.COMMODITY_NOT_EXIST_1017);
            } else {
                return QinShihuangResult.ok(CommodityFormatter.getMapper().valueToTree(commodity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
