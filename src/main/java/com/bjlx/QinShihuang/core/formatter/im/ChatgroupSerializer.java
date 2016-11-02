package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;

import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 群组序列化
 * @author xiaozhi
 *
 */
public class ChatgroupSerializer extends JsonSerializer<Chatgroup> {

    @Override
    public void serialize(Chatgroup chatgroup, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Chatgroup.fd_id, chatgroup.getId() == null ? "" : chatgroup.getId().toString());
            gen.writeNumberField(Chatgroup.fd_chatGroupId, chatgroup.getChatGroupId() == null ? 0L : chatgroup.getChatGroupId());
            gen.writeStringField(Chatgroup.fd_name, chatgroup.getName() == null ? "不羁旅行群组" : chatgroup.getName());
            if(chatgroup.getGroupDesc() != null)
            	gen.writeStringField(Chatgroup.fd_groupDesc, chatgroup.getGroupDesc());
            

            

//            /**
//             * 头像
//             */
//            private ImageItem avatar;
//
//            /**
//             * 讨论组标签
//             */
//            List<String> tags;
//
//            /**
//             * 创建者用户id
//             */
//            @NotNull
//            private Long creator;
//            
//            /**
//             * 管理员用户id
//             */
//            private List<Long> admins;
//
//            /**
//             * 讨论组成员
//             */
//            private List<Long> participants;
//
//            /**
//             * 讨论组最大允许人数
//             */
//            @NotNull
//            @Min(value = 1)
//            @Max(value = 2000)
//            private Integer maxUsers = 250;
//            
//            /**
//             * 创建时间
//             */
//            @NotNull
//            private Long createTime = 0L;
//
//            /**
//             * 更新时间
//             */
//            @NotNull
//            private Long updateTime = 0L;
//            
//            /**
//             * 是否公开
//             */
//            @NotNull
//            private Boolean visible = true;
//
//            /**
//             * 群等级
//             */
//            private Integer level = 1;
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
