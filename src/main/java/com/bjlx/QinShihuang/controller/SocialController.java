package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.SocialAPI;
import com.bjlx.QinShihuang.requestmodel.FollowingReq;
import com.bjlx.QinShihuang.requestmodel.MemoReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 社交控制器
 * Created by pengyt on 2016/11/8.
 */
@Controller
public class SocialController {

    /**
     * 关注用户1055
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param followingReq 关注用户参数信息
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/followings", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String following(@PathVariable Long userId, @RequestHeader("key") String key, @RequestBody FollowingReq followingReq) {
        // 参数校验
        if(followingReq.getFollowingId() == null) {
            return QinShihuangResult.getResult(ErrorCode.FOLLOWINGID_NULL_1055);
        }

        try {
            return SocialAPI.following(userId, key, followingReq.getFollowingId());
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取消关注用户1056
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param followingReq 取消关注用户参数信息
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/followings", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String cancelFollowing(@PathVariable Long userId, @RequestHeader("key") String key, @RequestBody FollowingReq followingReq) {
        // 参数校验
        if(followingReq.getFollowingId() == null) {
            return QinShihuangResult.getResult(ErrorCode.FOLLOWINGID_NULL_1056);
        }

        try {
            return SocialAPI.cancelFollowing(userId, key, followingReq.getFollowingId());
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得好友列表1057
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第offset个文档开始取
     * @param limit 取limit个文档
     * @return 好友信息列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/contacts", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getContacts(@PathVariable Long userId, @RequestHeader("key") String key, Integer offset, Integer limit) {
        Integer defaultOffset = 0;
        Integer defaultLimit = 200;
        try {
            return SocialAPI.getContacts(userId, key, offset == null ? defaultOffset : offset, limit == null ? defaultLimit : limit);
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得好友(关注人)信息1058
     * @param userId 用户id
     * @param contactId 好友id或者关注人id
     * @param key 不羁旅行令牌
     * @return 好友(关注人)信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/contacts/{contactId:\\d+}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getContactInfo(@PathVariable Long userId, @PathVariable Long contactId, @RequestHeader("key") String key) {
        try {
            return SocialAPI.getContactInfo(userId, contactId, key);
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 修改备注1059
     * @param userId 用户id
     * @param contactId 好友(关注人)id
     * @param key 不羁旅行令牌
     * @param memoReq 备注参数
     * @return 好友(关注人)信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/contacts/{contactId:\\d+}/memos", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateMemo(@PathVariable Long userId, @PathVariable Long contactId, @RequestHeader("key") String key, @RequestBody MemoReq memoReq) {
        if(memoReq.getMemo() == null || "".equals(memoReq.getMemo())) {
            return QinShihuangResult.getResult(ErrorCode.MEMO_NULL_1059);
        }
        try {
            return SocialAPI.updateMemo(userId, contactId, key, memoReq.getMemo());
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}
