package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicSerializer;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TravelNoteFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TravelNote.class, new TravelNoteSerializer());
        module.addSerializer(UserInfo.class, new UserInfoBasicSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
