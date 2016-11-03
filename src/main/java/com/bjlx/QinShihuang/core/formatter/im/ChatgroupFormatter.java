package com.bjlx.QinShihuang.core.formatter.im;

import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class ChatgroupFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Chatgroup.class, new ChatgroupSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
