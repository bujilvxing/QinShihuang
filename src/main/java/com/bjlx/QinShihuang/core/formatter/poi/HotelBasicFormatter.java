package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/15.
 */
public class HotelBasicFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Hotel.class, new HotelBasicSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
