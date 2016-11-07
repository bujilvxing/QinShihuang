package com.bjlx.QinShihuang.core.formatter.tripplan;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.HotelBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.RestaurantBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ShoppingBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ViewspotBasicSerializer;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripItem;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TripPlanFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TripPlan.class, new TripPlanSerializer());
        module.addSerializer(TripItem.class, new TripItemSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Restaurant.class, new RestaurantBasicSerializer());
        module.addSerializer(Hotel.class, new HotelBasicSerializer());
        module.addSerializer(Viewspot.class, new ViewspotBasicSerializer());
        module.addSerializer(Shopping.class, new ShoppingBasicSerializer());
        module.addSerializer(Activity.class, new ActivityBasicSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
