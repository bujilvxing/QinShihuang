package com.bjlx.QinShihuang.core.formatter.trace;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AudioSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.HotelBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.RestaurantBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ShoppingBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ViewspotBasicSerializer;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TraceFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Trace.class, new TraceSerializer());
        module.addSerializer(Audio.class, new AudioSerializer());
        module.addSerializer(Activity.class, new ActivityBasicSerializer());
        module.addSerializer(Viewspot.class, new ViewspotBasicSerializer());
        module.addSerializer(Restaurant.class, new RestaurantBasicSerializer());
        module.addSerializer(Shopping.class, new ShoppingBasicSerializer());
        module.addSerializer(Hotel.class, new HotelBasicSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
