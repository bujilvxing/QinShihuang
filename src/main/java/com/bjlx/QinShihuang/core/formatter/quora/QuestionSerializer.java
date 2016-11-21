package com.bjlx.QinShihuang.core.formatter.quora;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.quora.Question;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class QuestionSerializer extends JsonSerializer<Question> {

    @Override
    public void serialize(Question question, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Question.fd_id, question.getId() == null ? "" : question.getId().toString());
            gen.writeStringField(Question.fd_source, question.getSource() == null ? "" : question.getSource());

            List<String> topics = question.getTopics();
            if (topics != null && (!topics.isEmpty())) {
                gen.writeFieldName(Question.fd_topics);
                gen.writeStartArray();
                for (String topic : topics)
                    gen.writeString(topic == null ? "" : topic);
                gen.writeEndArray();
            }

            List<String> tags = question.getTags();
            if (tags != null && (!tags.isEmpty())) {
                gen.writeFieldName(Question.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            gen.writeNumberField(Question.fd_viewCnt, question.getViewCnt() == null ? 0 : question.getViewCnt());
            gen.writeNumberField(Question.fd_answerCnt, question.getAnswerCnt() == null ? 0 : question.getAnswerCnt());
            gen.writeNumberField(Question.fd_maxVoteCnt, question.getMaxVoteCnt() == null ? 0 : question.getMaxVoteCnt());
            gen.writeNumberField(Question.fd_favorCnt, question.getFavorCnt() == null ? 0 : question.getFavorCnt());
            gen.writeNumberField(Question.fd_voteCnt, question.getVoteCnt() == null ? 0 : question.getVoteCnt());
            gen.writeFieldName(Question.fd_author);
            UserInfo author = question.getAuthor();
            if (author != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(UserInfo.class, null);
                retAvatar.serialize(author, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            gen.writeNumberField(Question.fd_publishTime, question.getPublishTime() == null ? 0L : question.getPublishTime());

            gen.writeStringField(Question.fd_title, question.getTitle() == null ? "" : question.getTitle());
            gen.writeStringField(Question.fd_contents, question.getContents() == null ? "" : question.getContents());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}