package com.bjlx.QinShihuang.core.formatter.tripplan;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 行程规划项序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class TripItemSerializer extends JsonSerializer<TripItem> {

    @Override
    public void serialize(TripItem tripItem, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(tripItem.getTripTime() != null)
                gen.writeNumberField(TripItem.fd_tripTime, tripItem.getTripTime());

            if(tripItem.getCreateTime() != null)
                gen.writeNumberField(TripItem.fd_createTime, tripItem.getCreateTime());

            if(tripItem.getDesc() != null)
                gen.writeStringField(TripItem.fd_desc, tripItem.getDesc());

            gen.writeFieldName(TripItem.fd_restaurant);
            Restaurant restaurant = tripItem.getRestaurant();
            JsonSerializer<Object> retRestaurant;
            if (restaurant != null) {
                retRestaurant = serializers.findValueSerializer(Restaurant.class, null);
                retRestaurant.serialize(restaurant, gen, serializers);
            }

            gen.writeFieldName(TripItem.fd_hotel);
            Hotel hotel = tripItem.getHotel();
            JsonSerializer<Object> retHotel;
            if (hotel != null) {
                retHotel = serializers.findValueSerializer(Hotel.class, null);
                retHotel.serialize(hotel, gen, serializers);
            }

            gen.writeFieldName(TripItem.fd_viewspot);
            Viewspot viewspot = tripItem.getViewspot();
            JsonSerializer<Object> retViewspot;
            if (viewspot != null) {
                retViewspot = serializers.findValueSerializer(Viewspot.class, null);
                retViewspot.serialize(viewspot, gen, serializers);
            }
            gen.writeFieldName(TripItem.fd_activity);
            Activity activity = tripItem.getActivity();
            JsonSerializer<Object> retActivity;
            if (activity != null) {
                retActivity = serializers.findValueSerializer(Activity.class, null);
                retActivity.serialize(activity, gen, serializers);
            }

            gen.writeFieldName(TripItem.fd_shopping);
            Shopping shopping = tripItem.getShopping();
            JsonSerializer<Object> retShopping;
            if (shopping != null) {
                retShopping = serializers.findValueSerializer(Shopping.class, null);
                retShopping.serialize(shopping, gen, serializers);
            }


            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}