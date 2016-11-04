package com.bjlx.QinShihuang.core.formatter.specialservice;

import com.bjlx.QinShihuang.core.formatter.account.RealNameInfoSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.AddressSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ContactSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.specialservice.Car;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RentCarFormatter {
	public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(RentCar.class, new RentCarSerializer());
        module.addSerializer(Address.class, new AddressSerializer());
        module.addSerializer(Contact.class, new ContactSerializer());
        module.addSerializer(Car.class, new CarSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(RealNameInfo.class, new RealNameInfoSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
