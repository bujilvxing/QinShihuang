package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PostSerializer extends JsonSerializer<Post> {

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
            
            List<ImageItem> images = post.getImages();
            if (images != null && !images.isEmpty()) {
            	gen.writeFieldName(Post.fd_images);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
                gen.writeEndArray();
            }
            
            
            gen.writeNumberField(Post.fd_publishTime, post.getPublishTime() == null ? 0L : post.getPublishTime());
            gen.writeNumberField(Post.fd_updateTime, post.getUpdateTime() == null ? 0L : post.getUpdateTime());
            gen.writeNumberField(Post.fd_favorCnt, post.getFavorCnt() == null ? 0 : post.getFavorCnt());
            gen.writeNumberField(Post.fd_commentCnt, post.getCommentCnt() == null ? 0 : post.getCommentCnt());
            gen.writeNumberField(Post.fd_viewCnt, post.getViewCnt() == null ? 0 : post.getViewCnt());
            gen.writeNumberField(Post.fd_shareCnt, post.getShareCnt() == null ? 0 : post.getShareCnt());
            gen.writeNumberField(Post.fd_voteCnt, post.getVoteCnt() == null ? 0 : post.getVoteCnt());
            gen.writeStringField(Post.fd_summary, post.getSummary() == null ? "" : post.getSummary());
            gen.writeStringField(Post.fd_content, post.getContent() == null ? "" : post.getContent());
            gen.writeNumberField(Post.fd_rank, post.getRank() == null ? 0 : post.getRank());
            gen.writeNumberField(Post.fd_hotness, post.getHotness() == null ? 0.0 : post.getHotness());
            gen.writeNumberField(Post.fd_rating, post.getRating() == null ? 0.0 : post.getRating());
            
            if(post.getAuthorId() != null)
            	gen.writeNumberField(Post.fd_authorId, post.getAuthorId());
            
            if(post.getAuthorNickName() != null)
            	gen.writeStringField(Post.fd_authorNickName, post.getAuthorNickName());
            
            ImageItem authorAvatar = post.getAuthorAvatar();
            if (authorAvatar != null) {
            	gen.writeFieldName(Post.fd_authorAvatar);
                JsonSerializer<Object> retAuthorAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAuthorAvatar.serialize(authorAvatar, gen, serializers);
            }
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
