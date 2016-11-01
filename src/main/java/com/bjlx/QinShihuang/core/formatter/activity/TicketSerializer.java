package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.model.activity.Ticket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 门票序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class TicketSerializer extends JsonSerializer<Ticket> {

    @Override
    public void serialize(Ticket ticket, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Ticket.fd_id, ticket.getId() == null ? "" : ticket.getId().toString());
            gen.writeNumberField(Ticket.fd_price, ticket.getPrice() == null ? 0 : ticket.getPrice());

            if(ticket.getMarketPrice() != null)
                gen.writeNumberField(Ticket.fd_marketPrice, ticket.getMarketPrice());

            if(ticket.isFree() != null)
                gen.writeBooleanField(Ticket.fd_free, ticket.isFree());


            gen.writeNumberField(Ticket.fd_refundWay, ticket.getRefundWay() == null ? 2 : ticket.getRefundWay());
            gen.writeStringField(Ticket.fd_refundDesc, ticket.getRefundDesc() == null ? "" : ticket.getRefundDesc());
            gen.writeStringField(Ticket.fd_desc, ticket.getDesc() == null ? "" : ticket.getDesc());
            if(ticket.getMaxNum() != null)
                gen.writeNumberField(Ticket.fd_maxNum, ticket.getMaxNum());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}