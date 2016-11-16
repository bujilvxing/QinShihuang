package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.Favorite;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 收藏序列化
 */
public class FavoriteSerializer extends JsonSerializer<Favorite> {

    @Override
    public void serialize(Favorite favorite, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Favorite.fd_id, favorite.getId() == null ? "" : favorite.getId().toString());
            gen.writeNumberField(Favorite.fd_userId, favorite.getUserId() == null ? 0L : favorite.getUserId());
            gen.writeNumberField(Favorite.fd_favoriteType, favorite.getFavoriteType() == null ? 0 : favorite.getFavoriteType());
            gen.writeStringField(Favorite.fd_itemId, favorite.getItemId() == null ? "" : favorite.getItemId().toString());
            if(favorite.getAuthorId() != null) {
                gen.writeNumberField(Favorite.fd_authorId, favorite.getAuthorId());
            }
            if(favorite.getAuthorNickName() != null) {
                gen.writeStringField(Favorite.fd_authorNickName, favorite.getAuthorNickName());
            }
            ImageItem authorAvatar = favorite.getAuthorAvatar();
            if (authorAvatar != null) {
                gen.writeFieldName(Favorite.fd_authorAvatar);
                JsonSerializer<Object> retAuthorAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAuthorAvatar.serialize(authorAvatar, gen, serializers);
            }
            ImageItem cover = favorite.getCover();
            if (cover != null) {
                gen.writeFieldName(Favorite.fd_cover);
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            }
            gen.writeStringField(Favorite.fd_title, favorite.getTitle() == null ? "" : favorite.getTitle());
            gen.writeNumberField(Favorite.fd_favoriteTime, favorite.getFavoriteTime() == null ? 0L : favorite.getFavoriteTime());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}