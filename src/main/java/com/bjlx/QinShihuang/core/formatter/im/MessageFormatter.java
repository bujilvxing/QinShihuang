package com.bjlx.QinShihuang.core.formatter.im;

import com.bjlx.QinShihuang.core.formatter.misc.AudioSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.PositionSerializer;
import com.bjlx.QinShihuang.model.im.Content;
import com.bjlx.QinShihuang.model.im.Emoticon;
import com.bjlx.QinShihuang.model.im.Message;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Position;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class MessageFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Message.class, new MessageSerializer());
        module.addSerializer(Content.class, new ContentSerializer());
        module.addSerializer(Audio.class, new AudioSerializer());
        module.addSerializer(Position.class, new PositionSerializer());
        module.addSerializer(Emoticon.class, new EmoticonSerializer());
//        module.addSerializer(Video.class, new VideoSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
