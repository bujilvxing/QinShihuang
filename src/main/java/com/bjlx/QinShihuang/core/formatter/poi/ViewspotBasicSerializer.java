package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ViewspotBasicSerializer extends JsonSerializer<Viewspot> {

    @Override
    public void serialize(Viewspot viewspot, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Viewspot.fd_id, viewspot.getId() == null ? "" : viewspot.getId().toString());
	        if(viewspot.getLat() != null)
	        	gen.writeNumberField(Viewspot.fd_lat, viewspot.getLat());
	        if(viewspot.getLng() != null)
	        	gen.writeNumberField(Viewspot.fd_lng, viewspot.getLng());

	        gen.writeFieldName(Viewspot.fd_cover);
            ImageItem cover = viewspot.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            Contact contact = viewspot.getContact();
            if (contact != null) {
            	gen.writeFieldName(Viewspot.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            
            gen.writeStringField(Viewspot.fd_zhName, viewspot.getZhName() == null ? "" : viewspot.getZhName());
            gen.writeStringField(Viewspot.fd_enName, viewspot.getEnName() == null ? "" : viewspot.getEnName());
            gen.writeStringField(Viewspot.fd_url, viewspot.getUrl() == null ? "" : viewspot.getUrl());
            gen.writeNumberField(Viewspot.fd_marketPrice, viewspot.getMarketPrice() == null ? 0.0 : viewspot.getMarketPrice());
            gen.writeNumberField(Viewspot.fd_price, viewspot.getPrice() == null ? 0.0 : viewspot.getPrice());
            
            gen.writeNumberField(Viewspot.fd_saleVolume, viewspot.getSaleVolume() == null ? 0 : viewspot.getSaleVolume());
            if(viewspot.getDiscount() != null)
            	gen.writeNumberField(Viewspot.fd_discount, viewspot.getDiscount());
            
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}