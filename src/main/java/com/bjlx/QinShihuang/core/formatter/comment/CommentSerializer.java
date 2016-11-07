package com.bjlx.QinShihuang.core.formatter.comment;

import java.io.IOException;
import java.util.List;
import com.bjlx.QinShihuang.model.comment.Comment;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CommentSerializer extends JsonSerializer<Comment> {

    @Override
    public void serialize(Comment comment, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Comment.fd_id, comment.getId() == null ? "" : comment.getId().toString());
            if(comment.getRating() != null) 
            	gen.writeNumberField(Comment.fd_rating, comment.getRating());
            
            gen.writeNumberField(Comment.fd_userId, comment.getUserId() == null ? 0L : comment.getUserId());
        	
            gen.writeFieldName(Comment.fd_avatar);
            ImageItem avatar = comment.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeStringField(Comment.fd_nickName, comment.getNickName() == null ? "" : comment.getNickName());
            gen.writeStringField(Comment.fd_contents, comment.getContents() == null ? "" : comment.getContents());

            gen.writeNumberField(Comment.fd_publishTime, comment.getPublishTime() == null ? 0L : comment.getPublishTime());
            gen.writeNumberField(Comment.fd_updateTime, comment.getUpdateTime() == null ? 0L : comment.getUpdateTime());
            gen.writeNumberField(Comment.fd_commentType, comment.getCommentType() == null ? 0 : comment.getCommentType());
        	
            gen.writeStringField(Comment.fd_itemId, comment.getItemId() == null ? "" : comment.getItemId().toString());
            
            List<ImageItem> images = comment.getImages();
            gen.writeFieldName(Comment.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
        	
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}