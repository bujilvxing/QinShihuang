package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.marketplace.CommodityFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ʒ����ʵ��
 * Created by gaomin on 2016/11/11.
 */
public class CommoditieAPI {

    /**
     * 取得数据库对象����ݿ����
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 商品列表
     * @param category 商品分类
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public static String getCommoditysByPage(String category,Integer offset,Integer limit) throws Exception{
        Query<Commodity> query = ds.createQuery(Commodity.class)
                                    .field(Commodity.fd_category).equal(category)
                                    .field(Commodity.fd_status).equal(Constant.LOWER_SHELVES_COMMODITY_);
        query.offset(offset).limit(limit);
        List<Commodity> result;
        try {
            result = query.asList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        if(result == null) {
            return QinShihuangResult.ok(CommodityFormatter.getMapper().valueToTree(new ArrayList<Commodity>()));
        }
        else{
            return QinShihuangResult.ok(CommodityFormatter.getMapper().valueToTree(result));
        }
    }

    /**
     * 商品详情
     * @param commodityId   商品id
     * @return
     */
    public  static String getCommodity(Long commodityId) throws Exception{
        try{
            Query<Commodity> query = ds.createQuery(Commodity.class)
                                        .field(Commodity.fd_id).equal(commodityId)
                                        .field(Commodity.fd_status).equal(Constant.LOWER_SHELVES_COMMODITY_);
            Commodity commodity = query.get();
            if (commodity==null){
                return QinShihuangResult.getResult(ErrorCode.COMMODITY_NOT_EXIST_1017);
            }else{
                return QinShihuangResult.ok(CommodityFormatter.getMapper().valueToTree(commodity));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
