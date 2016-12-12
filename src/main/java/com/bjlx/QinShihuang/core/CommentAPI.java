package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.requestmodel.CommentReq;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import org.mongodb.morphia.Datastore;

/**
 * 评论核心实现
 * Created by xiaozhi on 2016/12/12.
 */
public class CommentAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 添加评论
     * @param commentReq 评论参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 评论信息
     * @throws Exception 异常
     */
    public static String addComment(CommentReq commentReq, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String removeComment(String commentId, Long userId, String key) throws Exception {
        return null;
    }

    /**
     * 评论列表
     * @param commentType 评论类型
     * @param itemId 评论对象id
     * @return 评论列表
     * @throws Exception 异常
     */
    public static String getComments(Integer commentType, String itemId) throws Exception {
        return null;
    }
}
