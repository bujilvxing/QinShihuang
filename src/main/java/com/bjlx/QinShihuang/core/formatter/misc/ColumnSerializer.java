package com.bjlx.QinShihuang.core.formatter.misc;

import java.io.IOException;
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
            gen.writeStringField(Column.fd_itemId, column.getId() == null ? "" : column.getId().toString());
            gen.writeNumberField(Column.fd_rank, column.getRank() == null ? 10000 : column.getRank());
            gen.writeStringField(Column.fd_itemType, column.getItemType() == null ? "" : column.getItemType());
            gen.writeStringField(Column.fd_columnType, column.getColumnType() == null ? "special" : column.getColumnType());
            gen.writeStringField(Column.fd_title, column.getTitle() == null ? "" : column.getTitle());
            gen.writeStringField(Column.fd_desc, column.getDesc() == null ? "" : column.getDesc());
            gen.writeStringField(Column.fd_link, column.getLink() == null ? "" : column.getLink());
            gen.writeStringField(Column.fd_linkType, column.getLinkType() == null ? "app" : column.getLinkType());
            ImageItem cover = column.getCover();
            if (cover != null) {
            	gen.writeFieldName(Column.fd_cover);
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                ret.serialize(cover, gen, serializers);
            }
            gen.writeStringField(Column.fd_status, column.getStatus() == null ? "review" : column.getStatus());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

