package com.bjlx.QinShihuang.core.formatter.im;

import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/11/4.
 */
public class PostFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Post.class, new PostSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
