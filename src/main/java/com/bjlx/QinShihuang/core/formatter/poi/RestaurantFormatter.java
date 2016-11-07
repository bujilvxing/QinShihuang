package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RestaurantFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Restaurant.class, new RestaurantSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Description.class, new DescriptionSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        module.addSerializer(Locality.class, new LocalityBasicSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
