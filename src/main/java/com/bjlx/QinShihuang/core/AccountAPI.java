package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.ValidationCodeFormatter;
import com.bjlx.QinShihuang.exception.BjlxException;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.model.misc.ValidationCode;
import com.bjlx.QinShihuang.utils.ErrorCode;
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
    
    public static boolean isAllowSendValidationCode(String account, boolean isTel) {
    	Long currentTime = System.currentTimeMillis();
    	
    	return false;
    }
    
    /**
     * 发送验证码
     * @param tel 手机号
     * @param action 验证码的用途
     * @return 发送结果
     * @throws BjlxException 
     */
    public static JsonNode sendValidationCode(String account, int action, boolean isTel) throws BjlxException, Exception {
        // 产生验证码
        String code = String.format("%d", (int) (Math.random() * 1000000));
        // 短信内容
        String[] smsdata = new String[1];
        smsdata[0] = code;
        Long currentTime = System.currentTimeMillis();
        ValidationCode result = null;
        
        if(isTel) {
			PhoneNumber phoneNumber = new PhoneNumber(86, account);
            ValidationCode validationCode = new ValidationCode(currentTime, currentTime + 6 * 60 * 1000, code, phoneNumber, action);
        	// 存数据库
            Query<ValidationCode> query = ds.createQuery(ValidationCode.class).field(ValidationCode.fd_number).equal(account);

            UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class)
            		.set(validationCode.fd_number, phoneNumber.getNumber())
            		.set(validationCode.fd_dialCode, phoneNumber.getDialCode())
            		.set(validationCode.fd_createTime, validationCode.getCreateTime())
            		.set(validationCode.fd_expireTime, validationCode.getExpireTime())
            		.set(validationCode.fd_action, validationCode.getAction())
            		.set(validationCode.fd_code, validationCode.getCode())
            		.set(validationCode.fd_lastSendTime, validationCode.getLastSendTime())
            		.set(validationCode.fd_used, validationCode.isUsed())
            		.set(validationCode.fd_resendTime, validationCode.getResendTime())
            		.set(validationCode.fd_failCnt, validationCode.getFailCnt());
            try {
            	result = ds.findAndModify(query, ops, false, true);
            } catch(Exception e) {
            	throw e;
            }
	        // 发送短信
	        int smsResult = SmsSendUtil.sendMessageByTemplate(account, smsdata);
	        switch(smsResult) {
				case -1: throw new BjlxException(ErrorCode.NETWORK_ERROR);
				case 1:  break;// 成功
				case 2: throw new BjlxException(ErrorCode.TIME_LIMIT_1001);
				case 3:
				case 4:
				case 5:
					throw new BjlxException(ErrorCode.REQUEST_TOO_MANY_1001); 
				default:
					throw new BjlxException(ErrorCode.UNKNOWN);  
			}
        } else {
        	ValidationCode validationCode = new ValidationCode(currentTime, currentTime + 6 * 60 * 1000, code, account, action);
        	// 存数据库
        	Query<ValidationCode> query = ds.createQuery(ValidationCode.class).field(ValidationCode.fd_email).equal(account);
        	UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class)
            		.set(validationCode.fd_email, validationCode.getEmail())
            		.set(validationCode.fd_createTime, validationCode.getCreateTime())
            		.set(validationCode.fd_expireTime, validationCode.getExpireTime())
            		.set(validationCode.fd_action, validationCode.getAction())
            		.set(validationCode.fd_code, validationCode.getCode())
            		.set(validationCode.fd_lastSendTime, validationCode.getLastSendTime())
            		.set(validationCode.fd_used, validationCode.isUsed())
            		.set(validationCode.fd_resendTime, validationCode.getResendTime())
            		.set(validationCode.fd_failCnt, validationCode.getFailCnt());
        	try {
            	result = ds.findAndModify(query, ops, false, true);
            	// instance.setupMailer("smtp.163.com", "25", "pengyt19890703@163.com", "19890703pyt0422");
    	     	// 腾讯邀请码：bihbohnlzbhccahh
    	     	MailerUtil.sendSimpleEmail("不羁旅行验证码", String.format("您的验证码为:%s", validationCode), "service@bujilvxing.com", account, "");
            } catch(Exception e) {
            	throw e;
            }
        }
        ObjectMapper mapper = ValidationCodeFormatter.getMapper();
        return mapper.valueToTree(result);
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