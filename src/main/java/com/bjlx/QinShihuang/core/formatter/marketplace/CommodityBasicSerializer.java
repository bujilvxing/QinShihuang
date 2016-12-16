package com.bjlx.QinShihuang.core.formatter.marketplace;

import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 商品基本信息序列化
 * Created by pengyt on 2016/11/12.
 */
public class CommodityBasicSerializer extends JsonSerializer<Commodity> {

    @Override
    public void serialize(Commodity commodity, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Commodity.fd_id, commodity.getId() == null ? "" : commodity.getId().toString());
            gen.writeStringField(Commodity.fd_firstCategory, commodity.getFirstCategory() == null ? "" : commodity.getFirstCategory());

            if (commodity.getSecondCategory() != null)
                gen.writeStringField(Commodity.fd_secondCategory, commodity.getSecondCategory());
            if (commodity.getThirdCategory() != null)
                gen.writeStringField(Commodity.fd_thirdCategory, commodity.getThirdCategory());

            gen.writeStringField(Commodity.fd_title, commodity.getTitle() == null ? "" : commodity.getTitle());

            if (commodity.getDesc() != null)
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

            gen.writeNumberField(Commodity.fd_price, commodity.getPrice() == null ? 0 : commodity.getPrice());
            gen.writeNumberField(Commodity.fd_marketPrice, commodity.getMarketPrice() == null ? 0 : commodity.getMarketPrice());
            gen.writeNumberField(Commodity.fd_status, commodity.getStatus() == null ? 1 : commodity.getStatus());

            gen.writeNumberField(Commodity.fd_salesVolume, commodity.getSalesVolume() == null ? 0 : commodity.getSalesVolume());
            gen.writeNumberField(Commodity.fd_createTime, commodity.getCreateTime() == null ? 0L : commodity.getCreateTime());
            gen.writeNumberField(Commodity.fd_updateTime, commodity.getUpdateTime() == null ? 0L : commodity.getUpdateTime());
            gen.writeNumberField(Commodity.fd_rating, commodity.getRating() == null ? 0.0 : commodity.getRating());
            gen.writeStringField(Commodity.fd_commodityType, commodity.getCommodityType() == null ? "" : commodity.getCommodityType());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}