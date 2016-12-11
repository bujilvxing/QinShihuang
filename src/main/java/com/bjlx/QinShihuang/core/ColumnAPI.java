package com.bjlx.QinShihuang.core;

import java.util.List;

import com.bjlx.QinShihuang.core.formatter.guide.GuideBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.misc.ColumnCommodityFormatter;
import com.bjlx.QinShihuang.core.formatter.misc.ColumnFormatter;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.misc.ColumnCommodity;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.ColumnGuide;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 专栏核心实现
 * Created by gaomin on 2016/11/6.
 */
public class ColumnAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得专栏
     * @return 专栏列表
     * @throws Exception 异常
     */
    public static String getColumns() throws Exception {
        Query<Column> query = ds.createQuery(Column.class).field(Column.fd_columnType).equal(Constant.SPECIAL_COLUMN);
        List<Column> result;
        try{
            result = query.asList();
            if (result == null) {
                return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_1015);
            } else {
                return QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 首页banner
     * @return banner列表
     * @throws Exception 异常
     */
    public static String getBanners() throws Exception {
        Query<Column> query = ds.createQuery(Column.class).field(Column.fd_columnType).equal(Constant.SLIDE_COLUMN);
        List<Column> result;
        try{
            result = query.asList();
            if (result==null) {
                return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_1016);
            } else {
                return QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(result));
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 商品列表
     * @return 商品列表
     * @throws Exception 异常
     */
    public static String getColumnCommoditys() throws Exception {
        // 查询商品的id列表

        Query<ColumnCommodity> query = ds.createQuery(ColumnCommodity.class);
        List<ColumnCommodity> columnCommodities;
        try {
            columnCommodities = query.asList();
            if(columnCommodities == null) {
                return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_COMMODITY_1017);
            } else {
                // 将每个模块的商品信息查询出来
                for(ColumnCommodity columnCommodity : columnCommodities) {
                    if(columnCommodity.getCommodityIds() == null)
                        return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_COMMODITY_MODULE_1017, String.format("%s模块数据为空", columnCommodity.getCategory()));
                    else {
                        Query<Commodity> queryCommodity = ds.createQuery(Commodity.class).field(Commodity.fd_id).in(columnCommodity.getCommodityIds());
                        List<Commodity> commodities = queryCommodity.asList();
                        if(commodities == null) {
                            return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_COMMODITY_MODULE_1017, String.format("%s模块数据为空", columnCommodity.getCategory()));
                        } else {
                            columnCommodity.setCommodities(commodities);
                        }
                    }
                }
                return QinShihuangResult.ok(ColumnCommodityFormatter.getMapper().valueToTree(columnCommodities));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得首页攻略列表
     * @return 取得首页攻略列表
     * @throws Exception 异常
     */
    public static String getColumnGuides() throws Exception{
        Query<ColumnGuide> query = ds.createQuery(ColumnGuide.class);
        ColumnGuide columnGuide;
        try {
            columnGuide = query.get();
            if (columnGuide == null){
                return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_GUIDE_1019);
            } else {
                if(columnGuide.getGuideIds() == null || columnGuide.getGuideIds().isEmpty()) {
                    return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_GUIDE_1019);
                } else {
                    Query<Guide> queryGuide = ds.createQuery(Guide.class).field(Guide.fd_id).in(columnGuide.getGuideIds());
                    List<Guide> guides = queryGuide.asList();
                    if (guides == null) {
                        return QinShihuangResult.getResult(ErrorCode.EMPTY_COLUMN_GUIDE_1019);
                    } else {
                        columnGuide.setGuides(guides);
                    }
                    return QinShihuangResult.ok(GuideBasicFormatter.getMapper().valueToTree(guides));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
