package com.bjlx.QinShihuang.core.formatter.guide;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.HotelBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.RestaurantBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ShoppingBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.poi.ViewspotBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanBasicSerializer;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class GuideFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Guide.class, new GuideSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(Hotel.class, new HotelBasicSerializer());
        module.addSerializer(Shopping.class, new ShoppingBasicSerializer());
        module.addSerializer(Restaurant.class, new RestaurantBasicSerializer());
        module.addSerializer(Viewspot.class, new ViewspotBasicSerializer());
        module.addSerializer(TripPlan.class, new TripPlanBasicSerializer());
        module.addSerializer(Activity.class, new ActivityBasicSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
