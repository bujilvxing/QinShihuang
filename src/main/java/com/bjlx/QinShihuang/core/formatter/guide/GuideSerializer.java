package com.bjlx.QinShihuang.core.formatter.guide;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GuideSerializer extends JsonSerializer<Guide> {

    @Override
    public void serialize(Guide guide, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Guide.fd_id, guide.getId() == null ? "" : guide.getId().toString());

            gen.writeFieldName(Guide.fd_cover);
            ImageItem cover = guide.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            List<ImageItem> images = guide.getImages();
            gen.writeFieldName(Guide.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
        	
            gen.writeNumberField(Guide.fd_updateTime, guide.getUpdateTime() == null ? 0 : guide.getUpdateTime());
            gen.writeStringField(Guide.fd_title, guide.getTitle() == null ? "" : guide.getTitle());
        	
        	if(guide.getDesc() != null)
        		gen.writeStringField(Guide.fd_desc, guide.getDesc());

        	if(guide.getBestTripTime() != null)
        		gen.writeStringField(Guide.fd_bestTripTime, guide.getBestTripTime());
        	
        	if(guide.getTips() != null)
        		gen.writeStringField(Guide.fd_tips, guide.getTips());
        	
        	List<Hotel> hotels = guide.getHotels();
            if (hotels != null && !hotels.isEmpty()) {
            	gen.writeFieldName(Guide.fd_hotels);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Hotel.class, null);
                for (Hotel hotel : hotels)
                    ret.serialize(hotel, gen, serializers);
                gen.writeEndArray();
            }
            
            List<Shopping> shoppings = guide.getShoppings();
            if (shoppings != null && !shoppings.isEmpty()) {
            	gen.writeFieldName(Guide.fd_shoppings);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Shopping.class, null);
                for (Shopping shopping : shoppings)
                    ret.serialize(shopping, gen, serializers);
                gen.writeEndArray();
            }
            
            List<Restaurant> restaurants = guide.getRestaurants();
            if (restaurants != null && !restaurants.isEmpty()) {
            	gen.writeFieldName(Guide.fd_restaurants);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Restaurant.class, null);
                for (Restaurant restaurant : restaurants)
                    ret.serialize(restaurant, gen, serializers);
                gen.writeEndArray();
            }
            
            List<Viewspot> viewspots = guide.getViewspots();
            if (viewspots != null && !viewspots.isEmpty()) {
            	gen.writeFieldName(Guide.fd_viewspots);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Viewspot.class, null);
                for (Viewspot viewspot : viewspots)
                    ret.serialize(viewspot, gen, serializers);
                gen.writeEndArray();
            }

            List<TripPlan> tripPlans = guide.getTripPlans();
            if (tripPlans != null && !tripPlans.isEmpty()) {
            	gen.writeFieldName(Guide.fd_tripPlans);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(TripPlan.class, null);
                for (TripPlan tripPlan : tripPlans)
                    ret.serialize(tripPlan, gen, serializers);
                gen.writeEndArray();
            }
        
            List<Activity> activities = guide.getActivities();
            if (activities != null && !activities.isEmpty()) {
            	gen.writeFieldName(Guide.fd_activities);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Activity.class, null);
                for (Activity activity : activities)
                    ret.serialize(activity, gen, serializers);
                gen.writeEndArray();
            }

        	if(guide.getSummary() != null)
        		gen.writeStringField(Guide.fd_summary, guide.getSummary());

        	if(guide.getDetailUrl() != null)
        		gen.writeStringField(Guide.fd_detailUrl, guide.getDetailUrl());

        	gen.writeNumberField(Guide.fd_viewCnt, guide.getViewCnt() == null ? 0 : guide.getViewCnt());
        	gen.writeNumberField(Guide.fd_shareCnt, guide.getShareCnt() == null ? 0 : guide.getShareCnt());
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}