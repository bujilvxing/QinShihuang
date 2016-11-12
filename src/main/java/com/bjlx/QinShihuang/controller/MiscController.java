package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.MiscAPI;
import com.bjlx.QinShihuang.requestmodel.ApplySellerReq;
import com.bjlx.QinShihuang.requestmodel.FeedbackReq;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 其他模块
 * Created by xiaozhi on 2016/11/8.
 */
@Controller
public class MiscController {

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
                return QinShihuangResult.getResult(ErrorCode.ServerException);
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
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}
