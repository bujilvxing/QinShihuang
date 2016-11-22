package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.quora.AnswerFormatter;
import com.bjlx.QinShihuang.core.formatter.quora.QuestionFormatter;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.quora.Answer;
import com.bjlx.QinShihuang.model.quora.Question;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * 问题核心实现
 * Created by xiaozhi on 2016/11/22.
 */
public class QuoraAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 发布问题
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param title 标题
     * @param content 问题内容
     * @param tags 问题标签
     * @param topics 问题话题
     * @param source 问题来源
     * @return 结果
     * @throws Exception 异常
     */
    public static String addQuestion(Long userId, String key, String title, String content, List<String> tags, List<String> topics, String source) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1051);
            }
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1051);
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setPublishTime(System.currentTimeMillis());
            question.setAuthor(author);
            if(tags != null)
                question.setTags(tags);
            if(topics != null)
                question.setTopics(topics);
            if(source != null)
                question.setSource(source);
            ds.save(question);
            return QinShihuangResult.ok(QuestionFormatter.getMapper().valueToTree(question));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得问答信息
     * @param questionId 问题id
     * @param offset 从第几条回答开始取
     * @param limit 取多少条回答
     * @return 问答信息
     * @throws Exception 异常
     */
    public static String getQuora(String questionId, Integer offset, Integer limit) throws Exception {
        Query<Question> queryQuestion = ds.createQuery(Question.class).field(Question.fd_id).equal(new ObjectId(questionId)).field(Question.fd_status).equal(Constant.QUESTION_NORMAL);
        try {
            Question question = queryQuestion.get();
            if (question == null)
                return QinShihuangResult.getResult(ErrorCode.QUESTION_NOT_EXIST_1052);
            ObjectNode quora = CommonAPI.mapper.createObjectNode();
            quora.set("question", QuestionFormatter.getMapper().valueToTree(question));
            Query<Answer> queryAnswer = ds.createQuery(Answer.class).field(Answer.fd_questionId).equal(new ObjectId(questionId)).field(Answer.fd_status).equal(Constant.ANSWER_NORMAL).offset(offset).limit(limit);
            List<Answer> answers = queryAnswer.asList();
            if(answers != null)
                quora.set("answers", AnswerFormatter.getMapper().valueToTree(answers));
            return QinShihuangResult.ok(quora);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
