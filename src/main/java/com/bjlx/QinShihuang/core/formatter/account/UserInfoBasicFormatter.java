package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 用户基本信息
 * Created by xiaozhi on 2016/11/10.
 */
public class UserInfoBasicFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(UserInfo.class, new UserInfoBasicSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
