package com.bjlx.QinShihuang.core.formatter.geo;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.geo.DetailsEntry;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalitySerializer extends JsonSerializer<Locality> {

    @Override
    public void serialize(Locality locality, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            
            gen.writeStringField(Locality.fd_id, locality.getId() == null ? "" : locality.getId().toString());
            
            if(locality.getZhName() != null)
            	gen.writeStringField(Locality.fd_zhName, locality.getZhName());
        	
            if(locality.getEnName() != null)
            	gen.writeStringField(Locality.fd_enName, locality.getEnName());

            gen.writeFieldName(Locality.fd_cover);
            ImageItem cover = locality.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            List<ImageItem> images = locality.getImages();
            gen.writeFieldName(Locality.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
        	
        	if(locality.getLat() != null)
        		gen.writeNumberField(Locality.fd_lat, locality.getLat());
        	if(locality.getLng() != null)
        		gen.writeNumberField(Locality.fd_lng, locality.getLng());
        	if(locality.getRank() != null)
        		gen.writeNumberField(Locality.fd_rank, locality.getRank());

        	List<DetailsEntry> remoteTraffic = locality.getRemoteTraffic();
            if (remoteTraffic != null && !remoteTraffic.isEmpty()) {
            	gen.writeFieldName(Locality.fd_remoteTraffic);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry rt : remoteTraffic)
                    ret.serialize(rt, gen, serializers);
                gen.writeEndArray();
            }
            
            List<DetailsEntry> localTraffic = locality.getLocalTraffic();
            if (localTraffic != null && !localTraffic.isEmpty()) {
            	gen.writeFieldName(Locality.fd_localTraffic);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry lt : localTraffic)
                    ret.serialize(lt, gen, serializers);
                gen.writeEndArray();
            }
            
            if(locality.getShoppingIntro() != null)
        		gen.writeStringField(Locality.fd_shoppingIntro, locality.getShoppingIntro());

            if(locality.getDiningIntro() != null)
        		gen.writeStringField(Locality.fd_diningIntro, locality.getDiningIntro());

            List<DetailsEntry> cuisines = locality.getCuisines();
            if (cuisines != null && !cuisines.isEmpty()) {
            	gen.writeFieldName(Locality.fd_cuisines);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry cuisine : cuisines)
                    ret.serialize(cuisine, gen, serializers);
                gen.writeEndArray();
            }
        	
            List<Activity> activities = locality.getActivities();
            if (activities != null && !activities.isEmpty()) {
            	gen.writeFieldName(Locality.fd_activities);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Activity.class, null);
                for (Activity activity : activities)
                    ret.serialize(activity, gen, serializers);
                gen.writeEndArray();
            }

            List<DetailsEntry> tips = locality.getTips();
            if (tips != null && !tips.isEmpty()) {
            	gen.writeFieldName(Locality.fd_tips);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry tip : tips)
                    ret.serialize(tip, gen, serializers);
                gen.writeEndArray();
            }

            List<DetailsEntry> geoHistory = locality.getGeoHistory();
            if (geoHistory != null && !geoHistory.isEmpty()) {
            	gen.writeFieldName(Locality.fd_geoHistory);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry gh : geoHistory)
                    ret.serialize(gh, gen, serializers);
                gen.writeEndArray();
            }
            
            List<DetailsEntry> specials = locality.getSpecials();
            if (specials != null && !specials.isEmpty()) {
            	gen.writeFieldName(Locality.fd_specials);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(DetailsEntry.class, null);
                for (DetailsEntry special : specials)
                    ret.serialize(special, gen, serializers);
                gen.writeEndArray();
            }

            Set<String> alias = locality.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Locality.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
        	
            gen.writeNumberField(Locality.fd_visitCnt, locality.getVisitCnt() == null ? 0 : locality.getVisitCnt());
            gen.writeNumberField(Locality.fd_commentCnt, locality.getCommentCnt() == null ? 0 : locality.getCommentCnt());
            gen.writeNumberField(Locality.fd_favorCnt, locality.getFavorCnt() == null ? 0 : locality.getFavorCnt());
            gen.writeNumberField(Locality.fd_hotness, locality.getHotness() == null ? 0.0 : locality.getHotness());
            gen.writeNumberField(Locality.fd_rating, locality.getRating() == null ? 0.0 : locality.getRating());
            
            Locality superAdm = locality.getSuperAdm();
            if (superAdm != null) {
            	gen.writeFieldName(Locality.fd_superAdm);
                JsonSerializer<Object> retSuperAdm = serializers.findValueSerializer(Locality.class, null);
                retSuperAdm.serialize(superAdm, gen, serializers);
            }

            Set<String> tags = locality.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Locality.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            if(locality.getDesc() != null)
        		gen.writeStringField(Locality.fd_desc, locality.getDesc());
            
            if(locality.getTravelMonth() != null)
        		gen.writeStringField(Locality.fd_travelMonth, locality.getTravelMonth());
        	
            if(locality.getTimeCostDesc() != null)
        		gen.writeStringField(Locality.fd_timeCostDesc, locality.getTimeCostDesc());

            if(locality.getTimeCost() != null)
        		gen.writeNumberField(Locality.fd_timeCost, locality.getTimeCost());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}