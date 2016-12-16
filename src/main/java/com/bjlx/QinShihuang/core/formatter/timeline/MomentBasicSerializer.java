package com.bjlx.QinShihuang.core.formatter.timeline;

import com.bjlx.QinShihuang.model.misc.Card;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by xiaozhi on 2016/11/15.
 */
public class MomentBasicSerializer extends JsonSerializer<Moment> {

    @Override
    public void serialize(Moment moment, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Moment.fd_id, moment.getId() == null ? "" : moment.getId().toString());
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

            if(moment.getText() != null)
                gen.writeStringField(Moment.fd_text, moment.getText());

            if(moment.getComment() != null)
                gen.writeStringField(Moment.fd_comment, moment.getComment());

            Card card = moment.getCard();
            if (card != null) {
                gen.writeFieldName(Moment.fd_card);
                JsonSerializer<Object> retCard = serializers.findValueSerializer(Card.class, null);
                retCard.serialize(card, gen, serializers);
            }
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}