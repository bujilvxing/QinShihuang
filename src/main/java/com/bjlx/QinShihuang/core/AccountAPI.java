package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.SmsFormatter;
import com.bjlx.QinShihuang.model.Sequence;
import com.bjlx.QinShihuang.model.Sms;
import com.bjlx.QinShihuang.utils.MailerUtil;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.SmsSendUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * 账户核心实现
 * Created by xiaozhi on 2016/10/21.
 */
public class AccountAPI {

	/**
	 * 取得数据库对象
	 */
	private static Datastore ds = MorphiaFactory.getInstance();
	
	/**
     * 取得下一个userId
     * @return 用户id
     */
    public long getNextUserId() {
        Query<Sequence> query = ds.createQuery(Sequence.class).field("column").equal(Sequence.fd_userId);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(Sequence.class).inc("count");
        Sequence ret = ds.findAndModify(query, ops, false, true);
        return ret.getCount();
    }
	
	
    /**
     * 初始化一个mapper对象
     */
    public static ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    public static boolean isValidationLimit() {
    	
    	return true;
    }
    
    /**
     * 发送验证码
     * @param tel 手机号
     * @param action 验证码的用途
     * @return 发送结果
     */
    public static JsonNode sendValidationCode(String account, int action, boolean isTel) {
        // 产生验证码
        String validationCode = String.format("%d", (int) (Math.random() * 1000000));
        // 短信内容
        String[] smsdata = new String[1];
        smsdata[0] = validationCode;

        Sms sms = new Sms(validationCode, account, action);
        
        // 存数据库
        ds.save(sms);
        if(isTel) {
	        // 发送短信
	        SmsSendUtil.sendMessageByTemplate(account, smsdata);
        } else {
	        // instance.setupMailer("smtp.163.com", "25", "pengyt19890703@163.com", "19890703pyt0422");
	     	// 腾讯邀请码：bihbohnlzbhccahh
	     	MailerUtil.sendSimpleEmail("不羁旅行验证码", String.format("您的验证码为:%s", validationCode), "service@bujilvxing.com", account, "");
        }
        ObjectMapper mapper = SmsFormatter.getMapper();
        return mapper.valueToTree(sms);
    }

    public static JsonNode sendValidationCode(String tel, int action, String validationCode) {
        ObjectNode result = mapper.createObjectNode();
        // 检查是否存在
        // 检查验证码是否正确

        // 生成token
        String token = String.format("bjlx::token::%s", UUID.randomUUID().toString().replaceAll(",",""));

        result.put("token", token);
        return result;
    }
}