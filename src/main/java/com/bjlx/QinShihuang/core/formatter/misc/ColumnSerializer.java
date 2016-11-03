package com.bjlx.QinShihuang.core.formatter.misc;

import java.io.IOException;
import java.util.List;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ColumnSerializer extends JsonSerializer<Column> {

    @Override
    public void serialize(Column column, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Column.fd_id, column.getId() == null ? "" : column.getId().toString());
            gen.writeNumberField(Column.fd_rank, column.getRank() == null ? 10000 : column.getRank());
            gen.writeStringField(Column.fd_columnType, column.getColumnType() == null ? "special" : column.getColumnType());
            gen.writeStringField(Column.fd_title, column.getTitle() == null ? "" : column.getTitle());
            gen.writeStringField(Column.fd_link, column.getLink() == null ? "" : column.getLink());

            List<ImageItem> images = column.getImages();
            if (images != null && !images.isEmpty()) {
            	gen.writeFieldName(Column.fd_images);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeStringField(Column.fd_status, column.getStatus() == null ? "review" : column.getStatus());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

