package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.Vote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 点赞序列化
 * Created by xiaozhi on 2016/11/17.
 */
public class VoteSerializer extends JsonSerializer<Vote> {

    @Override
    public void serialize(Vote vote, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Vote.fd_id, vote.getId() == null ? "" : vote.getId().toString());
            gen.writeNumberField(Vote.fd_userId, vote.getUserId() == null ? 0L : vote.getUserId());
            gen.writeNumberField(Vote.fd_voteType, vote.getVoteType() == null ? 0 : vote.getVoteType());
            gen.writeStringField(Vote.fd_itemId, vote.getItemId() == null ? "" : vote.getItemId().toString());
            gen.writeNumberField(Vote.fd_voteTime, vote.getVoteTime() == null ? 0L : vote.getVoteTime());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}