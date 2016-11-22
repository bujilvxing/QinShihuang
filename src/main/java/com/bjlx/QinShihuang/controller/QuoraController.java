package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.QuoraAPI;
import com.bjlx.QinShihuang.requestmodel.QuestionReq;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 问答控制器
 * Created by xiaozhi on 2016/11/22.
 */
@Controller
public class QuoraController {

    /**
     * 添加问题
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param questionReq 问题参数
     * @return 结果
     */
    @RequestMapping(value = "/app/questions", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addQuestion(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody QuestionReq questionReq) {
        if(questionReq.getTitle() == null)
            return QinShihuangResult.getResult(ErrorCode.TITLE_NULL_1051);
        if(questionReq.getContent() == null)
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1051);
        try {
            return QuoraAPI.addQuestion(userId, key, questionReq.getTitle(), questionReq.getContent(), questionReq.getTags(), questionReq.getTopics(), questionReq.getSource());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
