package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.MiscAPI;
import com.bjlx.QinShihuang.requestmodel.ApplySellerReq;
import com.bjlx.QinShihuang.requestmodel.FavoriteReq;
import com.bjlx.QinShihuang.requestmodel.FeedbackReq;
import com.bjlx.QinShihuang.requestmodel.VoteReq;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 其他模块
 * Created by xiaozhi on 2016/11/8.
 */
@Controller
public class TraceController {

    /**
     * 申请商家1012
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param applySellerReq 申请参数
     * @return 结果
     */
    @RequestMapping(value = "/app/misc/sellers", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String applySeller(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody ApplySellerReq applySellerReq) {
        if(applySellerReq.getTel() == null) {
            return QinShihuangResult.getResult(ErrorCode.TEL_NULL_1012);
        }
        if(CommonUtil.isTelLegal(applySellerReq.getTel())) {
            try {
                return MiscAPI.applySeller(userId, key, applySellerReq.getTel());
            } catch (Exception e) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.TEL_FORMAT_1012);
        }
    }

    /**
     * 用户反馈1013
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param feedbackReq 反馈参数信息
     * @return 结果
     */
    @RequestMapping(value = "/app/misc/feedback", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String feedback(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody FeedbackReq feedbackReq) {
        if(feedbackReq.getContent() == null) {
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1013);
        }

        try {
            return MiscAPI.feedback(userId, key, feedbackReq.getContent(), feedbackReq.getOrigin());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 搜索用户1100
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 搜索关键字
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String searchUser(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, String query) {
        if(query == null)
            return QinShihuangResult.getResult(ErrorCode.QUERY_NULL_1100);
        try {
            return MiscAPI.searchUser(userId, key, query);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 搜索群组1101
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param query 搜索关键字
     * @return 群组信息
     */
    @RequestMapping(value = "/app/chatgroups", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String searchChatgroup(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, String query) {
        if(query == null)
            return QinShihuangResult.getResult(ErrorCode.QUERY_NULL_1101);
        try {
            return MiscAPI.searchChatgroup(userId, key, query);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 全站搜索102
     * @param query 搜索关键词
     * @param all 是否搜索所有
     * @param momemt 是否搜索时间线
     * @param commodity 是否搜索商品
     * @param guide 是否搜索攻略
     * @param viewspot 是否搜索景点
     * @param trace 是否搜索足迹
     * @param tripPlan 是否搜索行程规划
     * @param quora 是否搜索问答
     * @param activity 是否搜索活动
     * @param travelNote 是否搜索游记
     * @param restaurant 是否搜索美食
     * @param hotel 是否搜索宾馆
     * @param shopping 是否搜索购物
     * @return 结果
     */
    @RequestMapping(value = "/app/search", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String search(String query, Boolean all, Boolean momemt, Boolean commodity, Boolean guide, Boolean viewspot,
                                          Boolean trace, Boolean tripPlan, Boolean quora, Boolean activity, Boolean travelNote,
                                          Boolean restaurant, Boolean hotel, Boolean shopping) {
        if(query == null)
            return QinShihuangResult.getResult(ErrorCode.QUERY_NULL_1102);
        try {
            if(all == null) {
                return MiscAPI.searchCondition(query, momemt == null ? false : momemt,
                        commodity == null ? false : commodity, guide == null ? false : guide,
                        viewspot == null ? false : viewspot, trace == null ? false : trace,
                        tripPlan == null ? false : tripPlan, quora == null ? false : quora,
                        activity == null ? false : activity, travelNote == null ? false : travelNote,
                        restaurant == null ? false : restaurant, hotel == null ? false : hotel,
                        shopping == null ? false : shopping);
            }
            if(all)
                return MiscAPI.searchAll(query);
            else
                return MiscAPI.searchCondition(query, momemt == null ? false : momemt,
                        commodity == null ? false : commodity, guide == null ? false : guide,
                        viewspot == null ? false : viewspot, trace == null ? false : trace,
                        tripPlan == null ? false : tripPlan, quora == null ? false : quora,
                        activity == null ? false : activity, travelNote == null ? false : travelNote,
                        restaurant == null ? false : restaurant, hotel == null ? false : hotel,
                        shopping == null ? false : shopping);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加收藏1077
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param favoriteReq 收藏参数
     * @return 结果
     */
    @RequestMapping(value = "/app/favorites", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addFavorite(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody FavoriteReq favoriteReq) {
        if(favoriteReq.getFavoriteType() == null)
            return QinShihuangResult.getResult(ErrorCode.FAVORITETYPE_NULL_1077);
        if(favoriteReq.getItemId() == null)
            return QinShihuangResult.getResult(ErrorCode.ITEMID_NULL_1077);
        if(favoriteReq.getTitle() == null)
            return QinShihuangResult.getResult(ErrorCode.TITLE_NULL_1077);
        try {
            return MiscAPI.addFavorite(userId, key, favoriteReq.getFavoriteType(), favoriteReq.getItemId(), favoriteReq.getAuthorId(),
                    favoriteReq.getAuthorNickName(), favoriteReq.getAuthorAvatar(), favoriteReq.getCover(), favoriteReq.getTitle());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取消收藏1078
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param itemId 取消收藏的对象id
     * @return 结果
     */
    @RequestMapping(value = "/app/favorites/{itemId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String cancelFavorite(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @PathVariable (value = "itemId") String itemId, Integer favoriteType) {
        if(favoriteType == null)
            return QinShihuangResult.getResult(ErrorCode.FAVORITETYPE_NULL_1078);
        if(!Constant.checkFavoriteType(favoriteType))
            return QinShihuangResult.getResult(ErrorCode.FAVORITETYPE_INVALID_1078);
        try {
            return MiscAPI.cancelFavorite(userId, key, itemId, favoriteType);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得收藏列表1079
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 收藏列表
     */
    @RequestMapping(value = "/app/favorites", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getFavorites(@RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return MiscAPI.getFavorites(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 点赞1094
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param voteReq 点赞参数
     * @return 结果
     */
    @RequestMapping(value = "/app/votes", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addVote(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody VoteReq voteReq) {
        if(voteReq.getVoteType() == null)
            return QinShihuangResult.getResult(ErrorCode.VOTETYPE_NULL_1094);
        if(voteReq.getItemId() == null)
            return QinShihuangResult.getResult(ErrorCode.ITEMID_NULL_1094);
        try {
            return MiscAPI.addVote(userId, key, voteReq.getVoteType(), voteReq.getItemId());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取消点赞1095
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param itemId 取消点赞的对象id
     * @param voteType 点赞类型
     * @return 结果
     */
    @RequestMapping(value = "/app/votes/{itemId:\\[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String cancelVote(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @PathVariable("itemId") String itemId, Integer voteType) {
        if(voteType == null)
            return QinShihuangResult.getResult(ErrorCode.VOTETYPE_NULL_1095);
        if(!Constant.checkVoteType(voteType))
            return QinShihuangResult.getResult(ErrorCode.VOTETYPE_INVALID_1095);
        try {
            return MiscAPI.cancelVote(userId, key, itemId, voteType);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得点赞列表1096
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 点赞列表
     */
    @RequestMapping(value = "/app/votes", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getVotes(@RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return MiscAPI.getVotes(userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
