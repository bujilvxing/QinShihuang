package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.mongodb.morphia.Datastore;

import java.util.List;

/**
 * 问题核心实现
 * Created by xiaozhi on 2016/11/22.
 */
public class QuoraAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 发布问题
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param title 标题
     * @param content 问题内容
     * @param tags 问题标签
     * @param topics 问题话题
     * @param source 问题来源
     * @return 结果
     * @throws Exception 异常
     */
    public static String addQuestion(Long userId, String key, String title, String content, List<String> tags, List<String> topics, String source) throws Exception {

        return null;
    }
}
