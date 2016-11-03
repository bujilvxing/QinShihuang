package com.bjlx.QinShihuang.core.formatter.marketplace;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.im.PostSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.marketplace.CommodityPlan;
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
        module.addSerializer(Locality.class, new LocalityBasicSerializer());
        module.addSerializer(CommodityPlan.class, new CommodityPlanSerializer());
        module.addSerializer(Locality.class, new LocalityBasicSerializer());
        module.addSerializer(Locality.class, new LocalityBasicSerializer());

        mapper.registerModule(module);
        return mapper;
    }
}
