package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.CommentAPI;
import com.bjlx.QinShihuang.core.CommonAPI;
import com.bjlx.QinShihuang.requestmodel.CommentReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 * Created by xiaozhi on 2016/12/12.
 */
@Controller
public class CommentController {

    /**
     * 添加新评论1104
     * @param commentReq 评论参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 评论信息
     */
    @RequestMapping(value = "/app/comments", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addComment(@RequestBody CommentReq commentReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return CommentAPI.addComment(commentReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除评论1105
     * @param commentId 评论id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 评论结果
     */
    @RequestMapping(value = "/app/comments/{commentId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeComment(@PathVariable ("commentId") String commentId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return CommentAPI.removeComment(commentId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得评论列表106
     * @param itemId 评论对象id
     * @return 评论列表
     */
    @RequestMapping(value = "/app/comments", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getComments(String itemId, Integer offset, Integer limit) {
        if(itemId == null)
            return QinShihuangResult.getResult(ErrorCode.ITEMID_NULL_1106);
        if(!CommonAPI.isObjectId(itemId))
            return QinShihuangResult.getResult(ErrorCode.ITEMID_INVALID_1106);
        int defaultOffset = offset == null ? 0 : offset;
        int defaultLimit = limit == null ? 10 : limit;
        try {
            return CommentAPI.getComments(itemId, defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
