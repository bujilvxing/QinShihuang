package com.bjlx.QinShihuang.core.formatter.marketplace;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.marketplace.CommodityPlan;
import com.bjlx.QinShihuang.model.marketplace.Pricing;
import com.bjlx.QinShihuang.model.marketplace.StockInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CommodityPlanSerializer extends JsonSerializer<CommodityPlan> {

    @Override
    public void serialize(CommodityPlan commodityPlan, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(CommodityPlan.fd_planId, commodityPlan.getPlanId() == null ? "" : commodityPlan.getPlanId());
            gen.writeStringField(CommodityPlan.fd_title, commodityPlan.getTitle() == null ? "" : commodityPlan.getTitle());
            gen.writeStringField(CommodityPlan.fd_desc, commodityPlan.getDesc() == null ? "" : commodityPlan.getDesc());
            
            List<Pricing> pricings = commodityPlan.getPricings();
            if (pricings != null && !pricings.isEmpty()) {
            	gen.writeFieldName(CommodityPlan.fd_pricings);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Pricing.class, null);
                for (Pricing pricing : pricings)
                    ret.serialize(pricing, gen, serializers);
                gen.writeEndArray();
            }
            
            gen.writeNumberField(CommodityPlan.fd_marketPrice, commodityPlan.getMarketPrice() == null ? 0 : commodityPlan.getMarketPrice());
            gen.writeNumberField(CommodityPlan.fd_price, commodityPlan.getPrice() == null ? 0 : commodityPlan.getPrice());
            
            List<StockInfo> stockInfos = commodityPlan.getStockInfos();
            if (stockInfos != null && !stockInfos.isEmpty()) {
            	gen.writeFieldName(CommodityPlan.fd_stockInfos);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(StockInfo.class, null);
                for (StockInfo stockInfo : stockInfos)
                    ret.serialize(stockInfo, gen, serializers);
                gen.writeEndArray();
            }

            gen.writeBooleanField(CommodityPlan.fd_timeRequired, commodityPlan.getTimeRequired() == null ? false : commodityPlan.getTimeRequired());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}