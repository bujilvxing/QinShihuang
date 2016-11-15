package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.SocialAPI;
import com.bjlx.QinShihuang.requestmodel.BlockReq;
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
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
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
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
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
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
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
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
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
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加黑名单1060
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param blockReq 屏蔽信息
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/blacklist", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addBlackList(@PathVariable Long userId, @RequestHeader("key") String key, @RequestBody BlockReq blockReq) {
        if(blockReq.getBlockId() == null) {
            return QinShihuangResult.getResult(ErrorCode.BLOCKID_NULL_1060);
        }
        try {
            return SocialAPI.updateBlackList(userId, key, blockReq.getBlockId(), true);
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 移除黑名单1061
     * @param userId 用户id
     * @param blockId 待移除黑名单用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/followings/{blockId:\\d+}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeBlackList(@PathVariable Long userId, @PathVariable Long blockId, @RequestHeader("key") String key) {
        try {
            return SocialAPI.updateBlackList(userId, key, blockId, false);
        } catch(Exception e1) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     *
     * @param userId
     * @param key
     * @return
     */
    /**
     * 取得用户关注列表1062
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个开始返回
     * @param limit 最多返回多少个
     * @return 用户关注列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/followings", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getFollowings(@PathVariable Long userId, @RequestHeader("key") String key, Integer offset, Integer limit) {
        Integer defaultOffset = 0;
        Integer defaultLimit = 200;

        try {
            return SocialAPI.getFollowings(userId, key, offset == null ? defaultOffset : offset, limit == null ? defaultLimit : limit);
        } catch(Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户粉丝列表1063
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个开始返回
     * @param limit 最多返回多少个
     * @return 用户粉丝列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/follows", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getFollows(@PathVariable Long userId, @RequestHeader("key") String key, Integer offset, Integer limit) {
        Integer defaultOffset = 0;
        Integer defaultLimit = 200;

        try {
            return SocialAPI.getFollows(userId, key, offset == null ? defaultOffset : offset, limit == null ? defaultLimit : limit);
        } catch(Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
