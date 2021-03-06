package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.quora.AnswerFormatter;
import com.bjlx.QinShihuang.core.formatter.quora.QuestionBasicFormatter;
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
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
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
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1063);
            }
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1063);
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
                return QinShihuangResult.getResult(ErrorCode.QUESTION_NOT_EXIST_1064);
            ObjectNode quora = CommonAPI.mapper.createObjectNode();
            quora.set("question", QuestionFormatter.getMapper().valueToTree(question));
            Query<Answer> queryAnswer = ds.createQuery(Answer.class).field(Answer.fd_questionId).equal(new ObjectId(questionId))
                    .field(Answer.fd_status).equal(Constant.ANSWER_NORMAL).offset(offset).limit(limit).order(String.format("-%s", Answer.fd_voteCnt));
            List<Answer> answers = queryAnswer.asList();
            if(answers != null)
                quora.set("answers", AnswerFormatter.getMapper().valueToTree(answers));
            return QinShihuangResult.ok(quora);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得用户的问题列表
     * @param targetId 待查看的用户id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param offset 从第几个问题开始取
     * @param limit 取多少个问题
     * @return 问题列表
     * @throws Exception 异常
     */
    public static String getQuestionsByUserId(Long targetId, Long userId, String key, Integer offset, Integer limit) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1065);
            }
            Query<Question> queryQuestion = ds.createQuery(Question.class).field(Question.fd_authorId).equal(targetId)
                    .field(Question.fd_status).equal(Constant.QUESTION_NORMAL).order(String.format("-%s", Question.fd_publishTime)).offset(offset).limit(limit);
            List<Question> questions = queryQuestion.asList();
            if (questions == null)
                return QinShihuangResult.ok(QuestionBasicFormatter.getMapper().valueToTree(new ArrayList<Question>()));
            else
                return QinShihuangResult.ok(QuestionBasicFormatter.getMapper().valueToTree(questions));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得问题列表
     * @param offset 从第几个问题开始取
     * @param limit 取多少个问题
     * @return 问题列表
     * @throws Exception 异常
     */
    public static String getQuestions(Integer offset, Integer limit) throws Exception {
        try {
            Query<Question> queryQuestion = ds.createQuery(Question.class).field(Question.fd_status).equal(Constant.QUESTION_NORMAL)
                    .order(String.format("-%s", Question.fd_publishTime)).offset(offset).limit(limit);
            List<Question> questions = queryQuestion.asList();
            if (questions == null)
                return QinShihuangResult.ok(QuestionBasicFormatter.getMapper().valueToTree(new ArrayList<Question>()));
            else
                return QinShihuangResult.ok(QuestionBasicFormatter.getMapper().valueToTree(questions));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 添加回答
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param title 标题
     * @param content 回答内容
     * @return 结果
     * @throws Exception 异常
     */
    public static String addAnswer(String questionId, Long userId, String key, String title, String content) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1067);
            }
            UserInfo author = CommonAPI.getUserBasicById(userId);
            if(author == null)
                return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1067);
            Answer answer = new Answer(new ObjectId(questionId));
            answer.setTitle(title);
            answer.setContent(content);
            answer.setPublishTime(System.currentTimeMillis());
            answer.setAuthor(author);
            ds.save(answer);
            return QinShihuangResult.ok(AnswerFormatter.getMapper().valueToTree(answer));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除问题
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String delQuestion(String questionId, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1068);
            }
            Query<Question> query = ds.createQuery(Question.class).field(Question.fd_id).equal(new ObjectId(questionId))
                    .field(Question.fd_authorId).equal(userId).field(Question.fd_status).equal(Constant.QUESTION_NORMAL);
            UpdateOperations<Question> ops = ds.createUpdateOperations(Question.class).set(Question.fd_status, Constant.QUESTION_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除回答
     * @param questionId 问题id
     * @param answerId 回答id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果
     * @throws Exception 异常
     */
    public static String delAnswer(String questionId, String answerId, Long userId, String key) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1069);
            }
            // TODO 问题作者和回答作者均可删除
            Query<Answer> query = ds.createQuery(Answer.class).field(Answer.fd_id).equal(new ObjectId(answerId))
                    .field(Answer.fd_questionId).equal(new ObjectId(questionId))
                    .field(Answer.fd_authorId).equal(userId).field(Answer.fd_status).equal(Constant.ANSWER_NORMAL);
            UpdateOperations<Answer> ops = ds.createUpdateOperations(Answer.class).set(Answer.fd_status, Constant.ANSWER_UNENABLE);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 编辑问题
     * @param questionId 问题id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param title 标题
     * @param content 内容
     * @param tags 标签
     * @param topics 主题
     * @param source 来源
     * @return 问题信息
     * @throws Exception 异常
     */
    public static String editQuestion(String questionId, Long userId, String key, String title, String content, List<String> tags, List<String> topics, String source) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1070);
            }
            Query<Question> query = ds.createQuery(Question.class).field(Question.fd_id).equal(new ObjectId(questionId)).field(Question.fd_authorId).equal(userId)
                    .field(Question.fd_status).equal(Constant.QUESTION_NORMAL);
            UpdateOperations<Question> ops = ds.createUpdateOperations(Question.class).set(Question.fd_publishTime, System.currentTimeMillis());
            if(title != null) ops.set(Question.fd_title, title);
            if(content != null) ops.set(Question.fd_content, content);
            if(tags != null) ops.set(Question.fd_tags, tags);
            if(topics != null) ops.set(Question.fd_topics, topics);
            if(source != null) ops.set(Question.fd_source, source);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 编辑回答
     * @param questionId 问题id
     * @param answerId 答案id
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param title 标题
     * @param content 内容
     * @return 结果
     * @throws Exception 异常
     */
    public static String editAnswer(String questionId, String answerId, Long userId, String key, String title, String content) throws Exception {
        try {
            if (!CommonAPI.checkKeyValid(userId, key)) {
                return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1071);
            }
            Query<Answer> query = ds.createQuery(Answer.class).field(Answer.fd_id).equal(new ObjectId(answerId)).field(Answer.fd_authorId).equal(userId)
                    .field(Answer.fd_questionId).equal(new ObjectId(questionId)).field(Answer.fd_status).equal(Constant.ANSWER_NORMAL);
            UpdateOperations<Answer> ops = ds.createUpdateOperations(Answer.class).set(Answer.fd_publishTime, System.currentTimeMillis());
            if(title != null) ops.set(Question.fd_title, title);
            if(content != null) ops.set(Question.fd_content, content);
            ds.updateFirst(query, ops);
            return QinShihuangResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
