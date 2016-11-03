package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.model.activity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class TicketFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Ticket.class, new TicketSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
