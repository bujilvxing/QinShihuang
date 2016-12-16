package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.model.misc.ColumnCommodity;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 首页商品序列化
 * Created by pengyt on 2016/11/12.
 */
public class ColumnCommoditySerializer extends JsonSerializer<ColumnCommodity> {

    @Override
    public void serialize(ColumnCommodity columnCommodity, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(ColumnCommodity.fd_id, columnCommodity.getId() == null ? "" : columnCommodity.getId().toString());
            gen.writeStringField(ColumnCommodity.fd_category, columnCommodity.getCategory() == null ? "" : columnCommodity.getCategory());
            List<Commodity> commodities = columnCommodity.getCommodities();
            gen.writeFieldName(ColumnCommodity.fd_commodities);
            if (commodities != null && !commodities.isEmpty()) {
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Commodity.class, null);
                for (Commodity commodity : commodities)
                    ret.serialize(commodity, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
