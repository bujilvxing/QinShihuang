package com.bjlx.QinShihuang.core.formatter.marketplace;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.marketplace.CommodityPlan;
import com.bjlx.QinShihuang.model.marketplace.Pricing;
import com.bjlx.QinShihuang.model.marketplace.StockInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class CommodityFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Commodity.class, new CommoditySerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Locality.class, new LocalityBasicSerializer());
        module.addSerializer(CommodityPlan.class, new CommodityPlanSerializer());
        module.addSerializer(Pricing.class, new PricingSerializer());
        module.addSerializer(StockInfo.class, new StockInfoSerializer());

        mapper.registerModule(module);
        return mapper;
    }
}
