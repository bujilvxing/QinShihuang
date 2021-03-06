package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.QuoraAPI;
import com.bjlx.QinShihuang.requestmodel.AnswerReq;
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
     * 添加问题1063
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param questionReq 问题参数
     * @return 结果
     */
    @RequestMapping(value = "/app/questions", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addQuestion(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody QuestionReq questionReq) {
        if(questionReq.getTitle() == null)
            return QinShihuangResult.getResult(ErrorCode.TITLE_NULL_1063);
        if(questionReq.getContent() == null)
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1063);
        try {
            return QuoraAPI.addQuestion(userId, key, questionReq.getTitle(), questionReq.getContent(), questionReq.getTags(), questionReq.getTopics(), questionReq.getSource());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得问答信息1064
     * @param questionId 问题id
     * @param offset 从第几条回答开始取
     * @param limit 取多少条回答
     * @return 问答详情信息
     */
    @RequestMapping(value = "/app/quoras/{questionId:[0-9a-f]{24}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getQuora(@PathVariable("questionId") String questionId, Integer offset, Integer limit) {
        int defaultOffset = offset == null ? 0 : offset;
        int defaultLimit = limit == null ? 10 : limit;
        try {
            return QuoraAPI.getQuora(questionId, defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得用户的问题列表1065
     * @param targetId 待查看的用户id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个问题开始取
     * @param limit 取多少个问题
     * @return 问题列表
     */
    @RequestMapping(value = "/app/users/{targetId:\\d+}/questions", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getQuestionsByUserId(@PathVariable("targetId") Long targetId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key, Integer offset, Integer limit) {
        int defaultOffset = offset == null ? 0 : offset;
        int defaultLimit = limit == null ? 10 : limit;
        try {
            return QuoraAPI.getQuestionsByUserId(targetId, userId, key, defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得问题列表1066
     * @param offset 从第几个问题开始取
     * @param limit 取多少个问题
     * @return 问题列表
     */
    @RequestMapping(value = "/app/questions", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getQuestions(Integer offset, Integer limit) {
        int defaultOffset = offset == null ? 0 : offset;
        int defaultLimit = limit == null ? 10 : limit;
        try {
            return QuoraAPI.getQuestions(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 添加回答1067
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param answerReq 回答参数
     * @return 结果
     */
    @RequestMapping(value = "/app/questions/{questionId:[0-9a-f]{24}}/answers", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String addAnswer(@PathVariable("questionId") String questionId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody AnswerReq answerReq) {
        if(answerReq.getTitle() == null)
            return QinShihuangResult.getResult(ErrorCode.TITLE_NULL_1067);
        if(answerReq.getContent() == null)
            return QinShihuangResult.getResult(ErrorCode.CONTENT_NULL_1067);

        try {
            return QuoraAPI.addAnswer(questionId, userId, key, answerReq.getTitle(), answerReq.getContent());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除问题1068
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/questions/{questionId:[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String delQuestion(@PathVariable("questionId") String questionId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return QuoraAPI.delQuestion(questionId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 删除问题1069
     * @param questionId 问题id
     * @param answerId 回答id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     */
    @RequestMapping(value = "/app/questions/{questionId}/answers/{answerId:[0-9a-f]{24}}", method= RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public @ResponseBody String delAnswer(@PathVariable("questionId") String questionId, @PathVariable("answerId") String answerId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return QuoraAPI.delAnswer(questionId, answerId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 编辑问题1070
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param questionReq 问题参数
     * @return 结果
     */
    @RequestMapping(value = "/app/questions/{questionId:[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String editQuestion(@PathVariable("questionId") String questionId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody QuestionReq questionReq) {
        try {
            return QuoraAPI.editQuestion(questionId, userId, key, questionReq.getTitle(), questionReq.getContent(), questionReq.getTags(), questionReq.getTopics(), questionReq.getSource());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 编辑回答1071
     * @param questionId 问题id
     * @param answerId 回答id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param answerReq 回答参数
     * @return 结果
     */
    @RequestMapping(value = "/app/questions/{questionId}/answers/{answerId:[0-9a-f]{24}}", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String editAnswer(@PathVariable("questionId") String questionId, @PathVariable("answerId") String answerId, @RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody AnswerReq answerReq) {
        try {
            return QuoraAPI.editAnswer(questionId, answerId, userId, key, answerReq.getTitle(), answerReq.getContent());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
