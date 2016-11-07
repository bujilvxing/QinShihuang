package com.bjlx.QinShihuang.core.formatter.marketplace;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.marketplace.Pricing;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PricingSerializer extends JsonSerializer<Pricing> {

    @Override
    public void serialize(Pricing pricing, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeNumberField(Pricing.fd_price, pricing.getPrice() == null ? 0 : pricing.getPrice());
            
            List<Long> timeRange = pricing.getTimeRange();
            if (timeRange != null && (!timeRange.isEmpty())) {
            	gen.writeFieldName(Pricing.fd_timeRange);
                gen.writeStartArray();
                for (Long tr : timeRange)
                    gen.writeNumber(tr == null ? 0 : tr);
                gen.writeEndArray();
            }
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}