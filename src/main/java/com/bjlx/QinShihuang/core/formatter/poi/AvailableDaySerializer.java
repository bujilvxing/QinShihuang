package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;

import com.bjlx.QinShihuang.model.poi.AvailableDay;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class AvailableDaySerializer extends JsonSerializer<AvailableDay> {

    @Override
    public void serialize(AvailableDay availableDay, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeNumberField(AvailableDay.fd_bookTime, availableDay.getBookTime() == null ? 0L : availableDay.getBookTime());
            gen.writeBooleanField(AvailableDay.fd_available, availableDay.getAvailable() == null ? false : availableDay.getAvailable());
            gen.writeNumberField(AvailableDay.fd_price, availableDay.getPrice() == null ? 0 : availableDay.getPrice());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}