package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.activity.Joiner;
import com.bjlx.QinShihuang.model.activity.Ticket;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 *
 * Created by pengyt on 2016/11/4.
 */
public class ActivityFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Activity.class, new ActivitySerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Joiner.class, new JoinerSerializer());
        module.addSerializer(Ticket.class, new TicketSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
