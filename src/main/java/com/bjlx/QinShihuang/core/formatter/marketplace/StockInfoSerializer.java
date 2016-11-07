package com.bjlx.QinShihuang.core.formatter.marketplace;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.marketplace.StockInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StockInfoSerializer extends JsonSerializer<StockInfo> {

    @Override
    public void serialize(StockInfo stockInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(StockInfo.fd_status, stockInfo.getStatus() == null ? "empty" : stockInfo.getStatus());
            gen.writeNumberField(StockInfo.fd_quantity, stockInfo.getQuantity() == null ? 0 : stockInfo.getQuantity());
            
            List<Long> timeRange = stockInfo.getTimeRange();
            if (timeRange != null && (!timeRange.isEmpty())) {
            	gen.writeFieldName(StockInfo.fd_timeRange);
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
