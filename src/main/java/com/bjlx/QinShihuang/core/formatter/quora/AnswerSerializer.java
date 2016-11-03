package com.bjlx.QinShihuang.core.formatter.quora;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.quora.Answer;
import com.bjlx.QinShihuang.model.quora.Question;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AnswerSerializer extends JsonSerializer<Answer> {

    @Override
    public void serialize(Answer answer, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            Question question = answer.getQuestion();
            if (question != null) {
                JsonSerializer<Object> retQuestion = serializers.findValueSerializer(Question.class, null);
                retQuestion.serialize(question, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeNumberField(Answer.fd_voteCnt, answer.getVoteCnt() == null ? 0 : answer.getVoteCnt());
            gen.writeBooleanField(Answer.fd_accepted, answer.getAccepted() == null ? false : answer.getAccepted());

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
            gen.writeStringField(Answer.fd_contents, answer.getContents() == null ? "" : answer.getContents());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}