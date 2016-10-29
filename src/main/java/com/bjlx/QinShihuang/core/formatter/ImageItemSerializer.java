package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 图片序列化器
 * Created by pengyt on 2016/10/28.
 */
public class ImageItemSerializer extends JsonSerializer<ImageItem> {

        @Override
        public void serialize(ImageItem imageItem, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeStringField("id", imageItem.getId() == null ? "" : imageItem.getId().toString());
            if(imageItem.getCaption() != null)
                gen.writeStringField("caption", imageItem.getCaption());
            gen.writeStringField("key", imageItem.getKey() == null ? "" : imageItem.getKey());
            gen.writeStringField("bucket", imageItem.getBucket() == null ? "" : imageItem.getBucket());
            gen.writeStringField("url", imageItem.getUrl() == null ? "" : imageItem.getUrl());
            gen.writeNumberField("width", imageItem.getWidth() == null ? 0 : imageItem.getWidth());
            gen.writeNumberField("height", imageItem.getHeight() == null ? 0 : imageItem.getHeight());
            gen.writeStringField("fmt", imageItem.getFmt() == null ? "" : imageItem.getFmt());
            if(imageItem.getCm() != null) {
                gen.writeStringField("cm", imageItem.getCm());
            }
            if(imageItem.getHash() != null) {
                gen.writeStringField("hash", imageItem.getHash());
            }
            if(imageItem.getSize() != null) {
                gen.writeNumberField("size", imageItem.getSize());
            }
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
