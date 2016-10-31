package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 行程规划序列化器
 * Created by xiaozhi on 2016/10/31.
 */
public class TripPlanSerializer extends JsonSerializer<TripPlan> {

    @Override
    public void serialize(TripPlan tripPlan, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}