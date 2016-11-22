package com.bjlx.QinShihuang.core.formatter.quora;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.quora.Answer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AnswerSerializer extends JsonSerializer<Answer> {

    @Override
    public void serialize(Answer answer, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Answer.fd_questionId, answer.getQuestionId() == null ? "" : answer.getQuestionId().toString());

            gen.writeNumberField(Answer.fd_voteCnt, answer.getVoteCnt() == null ? 0 : answer.getVoteCnt());
            gen.writeBooleanField(Answer.fd_accepted, answer.isAccepted() == null ? false : answer.isAccepted());

            gen.writeFieldName(Answer.fd_author);
            UserInfo author = answer.getAuthor();
            if (author != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(UserInfo.class, null);
                retAvatar.serialize(author, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            gen.writeNumberField(Answer.fd_publishTime, answer.getPublishTime() == null ? 0L : answer.getPublishTime());

            gen.writeStringField(Answer.fd_title, answer.getTitle() == null ? "" : answer.getTitle());
            gen.writeStringField(Answer.fd_content, answer.getContent() == null ? "" : answer.getContent());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}