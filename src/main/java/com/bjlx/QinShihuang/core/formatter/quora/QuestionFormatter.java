package com.bjlx.QinShihuang.core.formatter.quora;

import com.bjlx.QinShihuang.core.formatter.account.UserInfoBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.quora.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class QuestionFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Question.class, new QuestionSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(UserInfo.class, new UserInfoBasicSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
