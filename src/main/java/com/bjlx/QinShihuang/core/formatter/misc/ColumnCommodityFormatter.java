package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.core.formatter.marketplace.CommodityBasicSerializer;
import com.bjlx.QinShihuang.model.misc.ColumnCommodity;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/12.
 */
public class ColumnCommodityFormatter {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ColumnCommodity.class, new ColumnCommoditySerializer());
        module.addSerializer(Commodity.class, new CommodityBasicSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
