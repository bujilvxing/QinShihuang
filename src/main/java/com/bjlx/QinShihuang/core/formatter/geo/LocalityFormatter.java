package com.bjlx.QinShihuang.core.formatter.geo;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.geo.DetailsEntry;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class LocalityFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Locality.class, new LocalitySerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(DetailsEntry.class, new DetailsEntrySerializer());
        module.addSerializer(Activity.class, new ActivityBasicSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
