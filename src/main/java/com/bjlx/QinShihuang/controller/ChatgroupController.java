package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ChatgroupAPI;
import com.bjlx.QinShihuang.requestmodel.ChatgroupReq;
import com.bjlx.QinShihuang.requestmodel.MemberReq;
import com.bjlx.QinShihuang.requestmodel.PostReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 群组控制器
 * Created by pengyt on 2016/11/13.
 */
@Controller
public class ChatgroupController {


    /**
     * 创建聊天组1068
     * @param chatgroupReq 聊天组参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     */
    @RequestMapping(value = "/app/chatgroups", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String createChatgroup(@RequestBody ChatgroupReq chatgroupReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.createChatgroup(chatgroupReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新聊天组1069
     * @param chatgroupId 聊天组id
     * @param chatgroupReq 聊天组参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateChatgroup(@PathVariable("chatgroupId") Long chatgroupId, @RequestBody ChatgroupReq chatgroupReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.updateChatgroup(chatgroupId, chatgroupReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得聊天组信息1070
     * @param chatgroupId 聊天组id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroup(@PathVariable("chatgroupId") Long chatgroupId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroup(chatgroupId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得聊天组成员列表1071
     * @param chatgroupId 聊天组id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组成员列表
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}/members", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroupMembers(@PathVariable("chatgroupId") Long chatgroupId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroupMembers(chatgroupId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加聊天组成员1072
     * @param chatgroupId 聊天组id
     * @param memberReq 成员参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}/members", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addChatgroupMember(@PathVariable("chatgroupId") Long chatgroupId, @RequestBody MemberReq memberReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.addChatgroupMember(chatgroupId, memberReq.getMemberId(), userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 移除聊天组成员1073
     * @param chatgroupId 聊天组id
     * @param memberReq 成员参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}/members", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeChatgroupMember(@PathVariable("chatgroupId") Long chatgroupId, @RequestBody MemberReq memberReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.removeChatgroupMember(chatgroupId, memberReq.getMemberId(), userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户聊天组列表1074
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 聊天组列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/chatgroups", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserChatgroups(@PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getUserChatgroups(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 发布聊天组帖子1075
     * @param chatgroupId 聊天组id
     * @param postReq 帖子参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}/posts", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addChatgroupPost(@PathVariable("chatgroupId") Long chatgroupId, @RequestBody PostReq postReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.addChatgroupPost(chatgroupId, postReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得聊天组帖子列表1076
     * @param chatgroupId 聊天组id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子列表
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}/posts", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroupPosts(@PathVariable("chatgroupId") Long chatgroupId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroupPosts(chatgroupId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 更新聊天组帖子1106
     * @param postId 帖子id
     * @param postReq 帖子参数
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子信息
     */
    @RequestMapping(value = "/app/posts/{postId:\\[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateChatgroupPost(@PathVariable("postId") String postId, @RequestBody PostReq postReq, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.updateChatgroupPost(postId, postReq, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除聊天组帖子1107
     * @param postId 帖子id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/posts/{postId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String removeChatgroupPost(@PathVariable("postId") String postId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.removeChatgroupPost(postId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得聊天组帖子详情1108
     * @param postId 帖子id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子详情
     */
    @RequestMapping(value = "/app/posts/{postId:\\[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroupPost(@PathVariable("postId") String postId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroupPost(postId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户帖子列表1109
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 帖子列表
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/posts", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserPosts(@PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getUserPosts(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
