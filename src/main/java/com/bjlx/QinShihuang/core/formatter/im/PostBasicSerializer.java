package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;

import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PostBasicSerializer  extends JsonSerializer<Post> {

    @Override
    public void serialize(Post post, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Post.fd_id, post.getId() == null ? "" : post.getId().toString());
            gen.writeStringField(Post.fd_title, post.getTitle() == null ? "" : post.getTitle());
            
            ImageItem cover = post.getCover();
            if (cover != null) {
            	gen.writeFieldName(Post.fd_cover);
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            }
            gen.writeNumberField(Post.fd_updateTime, post.getUpdateTime() == null ? 0L : post.getUpdateTime());
            
            gen.writeStringField(Post.fd_summary, post.getSummary() == null ? "" : post.getSummary());
            gen.writeNumberField(Post.fd_chatgroupId, post.getChatgroupId() == null ? 0 : post.getChatgroupId());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
