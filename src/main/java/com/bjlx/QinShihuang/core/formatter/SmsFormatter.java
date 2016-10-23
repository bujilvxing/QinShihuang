package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.Sms;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 短信
 *
 * Created by pengyt on 2016/10/23.
 */
public class SmsFormatter {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Sms.class, new SmsSerializer());

        mapper.registerModule(module);
        return mapper;
    }
}