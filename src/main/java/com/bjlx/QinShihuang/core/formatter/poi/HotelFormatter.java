package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.specialservice.RentCarBasicSerializer;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.AvailableDay;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class HotelFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Hotel.class, new HotelSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Description.class, new DescriptionSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        module.addSerializer(Locality.class, new LocalityBasicSerializer());
        module.addSerializer(RentCar.class, new RentCarBasicSerializer());
        module.addSerializer(AvailableDay.class, new AvailableDaySerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
