package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.model.misc.ValidationCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 短信
 *
 * Created by pengyt on 2016/10/23.
 */
public class ValidationCodeFormatter {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(ValidationCode.class, new ValidationCodeSerializer());

        mapper.registerModule(module);
        return mapper;
    }
}