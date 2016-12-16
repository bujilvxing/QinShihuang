package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by xiaozhi on 2016/11/17.
 */
public class VoteFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Vote.class, new VoteSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
