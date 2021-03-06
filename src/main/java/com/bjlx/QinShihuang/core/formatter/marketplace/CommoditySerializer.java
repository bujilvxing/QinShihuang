package com.bjlx.QinShihuang.core.formatter.marketplace;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.marketplace.CommodityPlan;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class CommoditySerializer extends JsonSerializer<Commodity> {

    @Override
    public void serialize(Commodity commodity, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Commodity.fd_id, commodity.getId() == null ? "" : commodity.getId().toString());

            gen.writeStringField(Commodity.fd_firstCategory, commodity.getFirstCategory() == null ? "" : commodity.getFirstCategory());

            if(commodity.getSecondCategory() != null)
                gen.writeStringField(Commodity.fd_secondCategory, commodity.getSecondCategory());
            if(commodity.getThirdCategory() != null)
                gen.writeStringField(Commodity.fd_thirdCategory, commodity.getThirdCategory());

            gen.writeStringField(Commodity.fd_title, commodity.getTitle() == null ? "" : commodity.getTitle());
            
            Locality locality = commodity.getLocality();
            if (locality != null) {
            	gen.writeFieldName(Commodity.fd_locality);
                JsonSerializer<Object> retLocality = serializers.findValueSerializer(Locality.class, null);
                retLocality.serialize(locality, gen, serializers);
            }
            
            if(commodity.getDesc() != null)
            	gen.writeStringField(Commodity.fd_desc, commodity.getDesc());
            
            gen.writeFieldName(Commodity.fd_cover);
            ImageItem cover = commodity.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
            	gen.writeStartObject();
                gen.writeEndObject();
            }

            List<ImageItem> images = commodity.getImages();
            if (images != null && !images.isEmpty()) {
            	gen.writeFieldName(Commodity.fd_images);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
                gen.writeEndArray();
            }
            
            gen.writeNumberField(Commodity.fd_price, commodity.getPrice() == null ? 0 : commodity.getPrice());
            gen.writeNumberField(Commodity.fd_marketPrice, commodity.getMarketPrice() == null ? 0 : commodity.getMarketPrice());
            gen.writeNumberField(Commodity.fd_status, commodity.getStatus() == null ? 1 : commodity.getStatus());
            gen.writeNumberField(Commodity.fd_favorCnt, commodity.getFavorCnt() == null ? 0 : commodity.getFavorCnt());
            List<CommodityPlan> plans = commodity.getPlans();
            if (plans != null && !plans.isEmpty()) {
            	gen.writeFieldName(Commodity.fd_plans);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(CommodityPlan.class, null);
                for (CommodityPlan plan : plans)
                    ret.serialize(plan, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeNumberField(Commodity.fd_salesVolume, commodity.getSalesVolume() == null ? 0 : commodity.getSalesVolume());
            gen.writeNumberField(Commodity.fd_createTime, commodity.getCreateTime() == null ? 0L : commodity.getCreateTime());
            gen.writeNumberField(Commodity.fd_updateTime, commodity.getUpdateTime() == null ? 0L : commodity.getUpdateTime());
            gen.writeNumberField(Commodity.fd_rating, commodity.getRating() == null ? 0.0 : commodity.getRating());
            gen.writeNumberField(Commodity.fd_version, commodity.getVersion() == null ? 0L : commodity.getVersion());
            gen.writeStringField(Commodity.fd_commodityType, commodity.getCommodityType() == null ? "" : commodity.getCommodityType());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}