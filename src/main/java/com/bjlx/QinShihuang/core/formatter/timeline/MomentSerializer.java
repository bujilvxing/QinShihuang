package com.bjlx.QinShihuang.core.formatter.timeline;

import com.bjlx.QinShihuang.model.misc.Card;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class MomentSerializer extends JsonSerializer<Moment> {

    @Override
    public void serialize(Moment moment, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Moment.fd_id, moment.getId() == null ? "" : moment.getId().toString());
            gen.writeNumberField(Moment.fd_publishTime, moment.getPublishTime() == null ? 0L : moment.getPublishTime());
            gen.writeNumberField(Moment.fd_userId, moment.getUserId() == null ? 0L : moment.getUserId());
            gen.writeStringField(Moment.fd_nickName, moment.getNickName() == null ? "" : moment.getNickName());

            gen.writeFieldName(Moment.fd_avatar);
            ImageItem avatar = moment.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            if(moment.getOriginId() != null)
                gen.writeStringField(Moment.fd_originId, moment.getOriginId().toString());

            if(moment.getOriginUserId() != null)
                gen.writeNumberField(Moment.fd_originId, moment.getOriginUserId());

            if(moment.getOriginNickName() != null)
                gen.writeStringField(Moment.fd_originNickName, moment.getOriginNickName());


            ImageItem originAvatar = moment.getOriginAvatar();
            if (originAvatar != null) {
                gen.writeFieldName(Moment.fd_originAvatar);
                JsonSerializer<Object> retOriginAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retOriginAvatar.serialize(originAvatar, gen, serializers);
            }

            if(moment.getText() != null)
                gen.writeStringField(Moment.fd_text, moment.getText());

            List<ImageItem> images = moment.getImages();
            if (images != null && !images.isEmpty()) {
                gen.writeFieldName(Moment.fd_images);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
                gen.writeEndArray();
            }

            if(moment.getComment() != null)
                gen.writeStringField(Moment.fd_comment, moment.getComment());


            Card card = moment.getCard();
            if (card != null) {
                gen.writeFieldName(Moment.fd_card);
                JsonSerializer<Object> retCard = serializers.findValueSerializer(Card.class, null);
                retCard.serialize(card, gen, serializers);
            }
            gen.writeNumberField(Moment.fd_favorCnt, moment.getFavorCnt() == null ? 0 : moment.getFavorCnt());
            gen.writeNumberField(Moment.fd_voteCnt, moment.getVoteCnt() == null ? 0 : moment.getVoteCnt());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}