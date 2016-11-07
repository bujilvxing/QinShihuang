package com.bjlx.QinShihuang.core.formatter.im;

import com.bjlx.QinShihuang.model.im.Conversation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class ConversationFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Conversation.class, new ConversationSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
