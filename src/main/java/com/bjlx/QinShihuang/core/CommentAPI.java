package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.comment.CommentFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.comment.Comment;
import com.bjlx.QinShihuang.requestmodel.CommentReq;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

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
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1104);
            UserInfo userInfo = CommonAPI.getUserBasicById(userId);
            if(userInfo == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1104);
            if(!Constant.checkCommentType(commentReq.getCommentType()))
                return QinShihuangResult.getResult(ErrorCode.COMMENTTYPE_INVALID_1104);
            Comment comment = new Comment(userId, userInfo.getAvatar(), userInfo.getNickName(), commentReq.getContents(), commentReq.getCommentType(), new ObjectId(commentReq.getItemId()));
            if(commentReq.getImages() != null && !commentReq.getImages().isEmpty())
                comment.setImages(commentReq.getImages());
            ds.save(comment);
            return QinShihuangResult.ok(CommentFormatter.getMapper().valueToTree(comment));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        try {
            if(!CommonAPI.checkKeyValid(userId, key))
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1105);
            Query<Comment> query = ds.createQuery(Comment.class).field(Comment.fd_id).equal(new ObjectId(commentId)).field(Comment.fd_userId).equal(userId).field(Comment.fd_status).equal(Constant.COMMENT_NORMAL);
            UpdateOperations<Comment> ops = ds.createUpdateOperations(Comment.class).set(Comment.fd_status, Constant.COMMENT_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 评论列表
     * @param itemId 评论对象id
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 评论列表
     * @throws Exception 异常
     */
    public static String getComments(String itemId, Integer offset, Integer limit) throws Exception {
        try {
            Query<Comment> query = ds.createQuery(Comment.class).field(Comment.fd_itemId).equal(new ObjectId(itemId)).field(Comment.fd_status).equal(Constant.COMMENT_NORMAL)
                    .order(String.format("-%s", Comment.fd_publishTime)).offset(offset).limit(limit);

            List<Comment> comments = query.asList();
            if(comments == null || comments.isEmpty())
                return QinShihuangResult.ok(CommentFormatter.getMapper().valueToTree(new ArrayList<Comment>()));
            else
                return QinShihuangResult.ok(CommentFormatter.getMapper().valueToTree(comments));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
