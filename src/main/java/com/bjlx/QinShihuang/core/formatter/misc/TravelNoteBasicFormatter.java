package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/15.
 */
public class TravelNoteBasicFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TravelNote.class, new TravelNoteBasicSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
