package com.bjlx.QinShihuang.core;


import com.bjlx.QinShihuang.core.formatter.account.UserInfoFormatter;
import com.bjlx.QinShihuang.core.formatter.misc.ValidationCodeFormatter;
import com.bjlx.QinShihuang.model.account.Credential;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.SecretKey;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.model.misc.Token;
import com.bjlx.QinShihuang.model.misc.ValidationCode;
import com.bjlx.QinShihuang.utils.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
    public static long getNextUserId() throws Exception {
        Query<Sequence> query = ds.createQuery(Sequence.class).field("column").equal(Sequence.fd_userId);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(Sequence.class).inc("count");
        try {
	        Sequence ret = ds.findAndModify(query, ops, false, true);
	        return ret.getCount();
        } catch (Exception e) {
        	throw e;
        }
    }
	
	
    /**
     * 初始化一个mapper对象
     */
    public static ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    
    /**
     * 是否允许发送验证码
     * @param account 账号
     * @param isTel 是否手机号
     * @return 是否允许
     */
    public static boolean isAllowSendValidationCode(String account, boolean isTel)  throws Exception {
    	long currentTime = System.currentTimeMillis();
    	
    	Query<ValidationCode> query = ds.createQuery(ValidationCode.class).field("action").equal(1);
    	if(isTel) {
    		query.field(ValidationCode.fd_number).equal(account).field(ValidationCode.fd_resendTime).greaterThan(currentTime);
    	} else {
    		query.field(ValidationCode.fd_email).equal(account).field(ValidationCode.fd_resendTime).greaterThan(currentTime);
    	}
    	try {
    		return query.get() == null;
        } catch (Exception e) {
         	throw e;
        }
    }
    
    /**
     * 检验用户是否存在
     * @param account 用户账户
     * @param isTel 是否手机号
     * @return true表示存在，false表示不存在
     */
    private static boolean checkUserExist(String account, boolean isTel) throws Exception {
    	Query<UserInfo> queryUser = ds.createQuery(UserInfo.class);
    	if(isTel) {
    		queryUser.field(UserInfo.fd_number).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	} else {
    		queryUser.field(UserInfo.fd_email).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	}
    	try {
    		return queryUser.get() != null;
        } catch (Exception e) {
         	throw e;
        }
    }

	/**
	 * 发送验证码
	 * @param account 用户账户
	 * @param action 验证码的用途
	 * @param isTel 是否手机号
	 * @return 发送结果
	 * @throws Exception 运行时异常
	 */
    public static String sendValidationCode(String account, int action, boolean isTel) throws Exception {  	
    	// 根据action检查异常情况
    	try{
	    	switch(action) {
		    	case Constant.NEW_USER_SIGNUP_ACTION :
		    		if(checkUserExist(account, isTel)) {
		    			return QinShihuangResult.getResult(ErrorCode.USER_EXIST_1001);
		    		}
		    		break;
		    	case Constant.BIND_TEL_ACTION:
		    		if(checkUserExist(account, isTel)) {
		    			return QinShihuangResult.getResult(ErrorCode.TEL_EXIST_1001);
		    		}
		    		break;
		    	case Constant.RESET_PWD_ACTION:
		    		if(!checkUserExist(account, isTel)) {
		    			return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1001);
		    		}
		    		break;
		    	case Constant.BIND_EMAIL_ACTION:
		    		if(checkUserExist(account, isTel)) {
		    			return QinShihuangResult.getResult(ErrorCode.EMAIL_EXIST_1001);
		    		}
		    		break;
	    	}
    	} catch(Exception e) {
    		throw e;
    	}
        // 产生验证码
        String code = String.format("%d", (int) (100000 + Math.random() * 900000));
        // 短信内容
        String[] smsdata = new String[1];
        smsdata[0] = code;
        Long currentTime = System.currentTimeMillis();
        ValidationCode result = null;
        Query<ValidationCode> query = ds.createQuery(ValidationCode.class);
        UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class);
        if(isTel) {
			PhoneNumber phoneNumber = new PhoneNumber(86, account);
            ValidationCode validationCode = new ValidationCode(currentTime, currentTime + 6 * 60 * 1000, code, phoneNumber, action);
        	// 存数据库
            query.field(ValidationCode.fd_number).equal(account);

            ops.set(ValidationCode.fd_tel, validationCode.getTel())
        	   	.set(ValidationCode.fd_createTime, validationCode.getCreateTime())
        		.set(ValidationCode.fd_expireTime, validationCode.getExpireTime())
        		.set(ValidationCode.fd_action, validationCode.getAction())
        		.set(ValidationCode.fd_code, validationCode.getCode())
        		.set(ValidationCode.fd_lastSendTime, validationCode.getLastSendTime())
        		.set(ValidationCode.fd_used, validationCode.isUsed())
        		.set(ValidationCode.fd_resendTime, validationCode.getResendTime())
        		.set(ValidationCode.fd_failCnt, validationCode.getFailCnt());
            int smsResult = 0;
            try {
            	result = ds.findAndModify(query, ops, false, true);
            	smsResult = SmsSendUtil.sendMessageByTemplate(account, smsdata);
            } catch(Exception e) {
				e.printStackTrace();
            	throw e;
            }
	        // 发送短信
	       
	        switch(smsResult) {
				case -1: return QinShihuangResult.getResult(ErrorCode.NETWORK_ERROR);
				case 1:  break;// 成功
				case 2: return QinShihuangResult.getResult(ErrorCode.TIME_LIMIT_1001);
				case 3:
				case 4: 
				case 5:
					return QinShihuangResult.getResult(ErrorCode.REQUEST_TOO_MANY_1001); 
				default:
					return QinShihuangResult.getResult(ErrorCode.UNKNOWN);  
			}
        } else {
			// 过期时间设置为6分钟
			Long expireTime = 6 * 60 * 1000L;

        	ValidationCode validationCode = new ValidationCode(currentTime, currentTime + expireTime, code, account, action);
        	// 存数据库
        	query.field(ValidationCode.fd_email).equal(account);
        	ops.set(ValidationCode.fd_email, validationCode.getEmail())
        		.set(ValidationCode.fd_createTime, validationCode.getCreateTime())
        		.set(ValidationCode.fd_expireTime, validationCode.getExpireTime())
        		.set(ValidationCode.fd_action, validationCode.getAction())
        		.set(ValidationCode.fd_code, validationCode.getCode())
        		.set(ValidationCode.fd_lastSendTime, validationCode.getLastSendTime())
        		.set(ValidationCode.fd_used, validationCode.isUsed())
        		.set(ValidationCode.fd_resendTime, validationCode.getResendTime())
        		.set(ValidationCode.fd_failCnt, validationCode.getFailCnt());
        	try {
            	result = ds.findAndModify(query, ops, false, true);
    	     	MailerUtil.sendSimpleEmail("不羁旅行验证码", String.format("您的验证码为:%s", code), "service@bujilvxing.com", account, "");
            } catch(Exception e) {
				e.printStackTrace();
            	throw e;
            }
        }
        ObjectMapper mapper = ValidationCodeFormatter.getMapper();
        return QinShihuangResult.ok(mapper.valueToTree(result));
    }

	/**
	 * 是否验证码，验证码必须是6位的数字
	 * @param code 验证码
	 * @return 是否验证码
	 * @throws PatternSyntaxException 模式匹配异常
	 */
	public static boolean isCode(String code) throws PatternSyntaxException {
		String regExp = "^\\d{6}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(code);
		return m.matches();
	}

	/**
	 * 检验验证码的合法性，并返回合法的令牌
	 * @param account 账户
	 * @param code 验证码
	 * @param isTel 账户是否手机号
	 * @return 令牌
	 * @throws Exception 运行时异常
	 */
    public static String checkValidationCode(String account, String code, boolean isTel) throws Exception {
		Query<ValidationCode> query = ds.createQuery(ValidationCode.class);

		long currentTime = System.currentTimeMillis();
		/**
		 * 符合条件的验证码
		 * 1、没有过期
		 * 2、没有被使用过
		 * 3、验证错误次数没有超过10次
		 * 4、账户和验证码正确
		 */
		if(isTel) {
			query.field(ValidationCode.fd_number).equal(account);
		} else {
			query.field(ValidationCode.fd_email).equal(account);
		}
		query.field(ValidationCode.fd_expireTime).greaterThanOrEq(currentTime).field(ValidationCode.fd_used).equal(false)
				.field(ValidationCode.fd_code).equal(code).field(ValidationCode.fd_failCnt).lessThanOrEq(10);

		UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class);
		try {
			if (query.get() == null) {
				// 验证错误次数增加一次
				ops.inc(ValidationCode.fd_failCnt);
				ds.updateFirst(query, ops);
				return QinShihuangResult.getResult(ErrorCode.VALIDATION_FAIL_1002);
			} else {
				// 存入token并返回
				Token token = new Token();
				ds.save(token);

				// 将验证码设置为已使用
				ops.set(ValidationCode.fd_used, true);
				ds.updateFirst(query, ops);

				ObjectNode result = mapper.createObjectNode();
				// 检验完后，将使用设置为true
				result.put("token", token.getToken());
				return QinShihuangResult.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    /**
     * 取得数字或者大写字母
     * @return 数字或者大写字母的字符
     */
    private static char getChar() {
		int r =  (int)(Math.random() * 36);
		int asciiCode = 0;
		if (r < 10) 
			asciiCode = r + 48;
		else 
			asciiCode = r - 10 + 65;
		return (char)asciiCode;
	}
	
    /**
     * 取得邀请码
     * @param length 邀请码长度
     * @return 邀请码
     */
	private static String getPromotionCode(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			sb.append(getChar());
		}
		return sb.toString();
	}
    
	/**
	 * 检验令牌的合法性
	 * 6分钟有效期
	 * 1、令牌未过期
	 * 2、令牌正确
	 * @param token 令牌
	 * @return 是否合法
	 * @throws Exception 数据库异常
	 */
	private static boolean checkTokenValid(String token) throws Exception {
		long currentTime = System.currentTimeMillis();
		Query<Token> query = ds.createQuery(Token.class).field(Token.fd_token).equal(token).field(Token.fd_expireTime).greaterThanOrEq(currentTime);
    	
    	try {
    		return query.get() != null;
        } catch (Exception e) {
         	throw e;
        }
	}
	
	/**
	 * 默认用户头像
	 */
	public static ImageItem defaultUserAvatar = new ImageItem("default_user_avatar.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_user_avatar.jpg", 100, 100, "jpg");
	
	/**
	 * 默认用户背景
	 */
	public static ImageItem defaultUserBackGround = new ImageItem("default_user_background.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_user_background.jpg", 400, 400, "jpg");
	
	/**
	 * 默认群组头像
	 */
	public static ImageItem defaultGroupAvatar = new ImageItem("default_group_avatar.jpg", "qiniu-bujilvxing", "http://oe7hx2tam.bkt.clouddn.com/default_group_avatar.jpg", 100, 100, "jpg");
    
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	// 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
    
	private static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
        	sb.append(byteToArrayString(bytes[i]));
        }
        return sb.toString();
    }
	
	  
	/**
     * 用户注册
     * @param account 用户账户
     * @param token 令牌
     * @param password 密码明文
     * @param isTel 是否手机号
     * @return 用户信息
     * @throws Exception 异常
     */
    public static String signup(String account, String token, String password, boolean isTel, int promotionCodeSize) throws Exception {
    	try {
    		// 检验用户是否已存在
	    	if(checkUserExist(account, isTel)){
	    		return QinShihuangResult.getResult(ErrorCode.USER_EXIST_1003);
	    	}
	    	// 检验token的合法性
	    	if(!checkTokenValid(token)) {
	    		return QinShihuangResult.getResult(ErrorCode.TOKEN_INVALID_1003);
	    	}
    	} catch(Exception e) {
    		throw e;
    	}
    	// 生成邀请码
    	String promotionCode = getPromotionCode(promotionCodeSize);
    	
    	// 获取用户id
    	long userId = getNextUserId();
    	UserInfo userInfo = null;
    	String nickName = String.format("不羁%d", userId);
    	
    	if(isTel) {
    		PhoneNumber tel = new PhoneNumber(86, account);
    		userInfo = new UserInfo(userId, tel, nickName, defaultUserAvatar, defaultUserBackGround, promotionCode);
    	} else 
    		userInfo = new UserInfo(userId, account, nickName, defaultUserAvatar, defaultUserBackGround, promotionCode);
    	
    	// 用户凭证
    	
    	Credential credential = null;
    	// 生成密码
		try {
			// 生成64个字节的salt
			String salt = MessageDigest.getInstance("MD5").digest(String.valueOf(System.currentTimeMillis()).getBytes()).toString();
			byte[] bytes = MessageDigest.getInstance("SHA-256").digest((salt + password).getBytes());
			String passwdHash = bytesToString(bytes);
			SecretKey secretKey = new SecretKey();
			credential = new Credential(userId, salt, passwdHash, secretKey);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		try {
			ds.save(userInfo);
			ds.save(credential);
			return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
		} catch(Exception e) {
			throw e;
		}
    }

    /**
     * 用户登录
     * @param account 用户账户
     * @param password 密码
     * @param clientId 个推的clientId
     * @param isTel 是否电话号码
     * @return 用户信息
     * @throws Exception 异常
     */
    public static String login(String account, String password, String clientId, boolean isTel) throws Exception {
    	// 查询用户信息
    	Query<UserInfo> query = ds.createQuery(UserInfo.class);
    	if(isTel)
    		query.field(UserInfo.fd_number).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	else
    		query.field(UserInfo.fd_email).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	UserInfo userInfo = null;
    	try {
    		userInfo = query.get();
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	if(userInfo == null) {
    		return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1004);
    	} else {
    		long userId = userInfo.getUserId();
    		Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId);
    		
    		try {
    			Credential credential = queryCredential.get();
    			
    			byte[] bytes = MessageDigest.getInstance("SHA-256").digest((credential.getSalt() + password).getBytes());
    			String passwdHash = bytesToString(bytes);
    			// 检验用户密码
    			if(credential.getPasswdHash().equals(passwdHash)) {
    				UpdateOperations<Credential> opsCredential = ds.createUpdateOperations(Credential.class);
    				SecretKey secretKey = new SecretKey();
    				opsCredential.set(Credential.fd_secretKey, secretKey);
    				// 更新授权码
    				ds.updateFirst(queryCredential, opsCredential);
    				// 更新用户登录状态以及绑定个推的clientId
    				UpdateOperations<UserInfo> opsUser = ds.createUpdateOperations(UserInfo.class)
    						.set(UserInfo.fd_loginStatus, true).set(UserInfo.fd_loginTime, System.currentTimeMillis())
    						.set(UserInfo.fd_clientId, clientId);
    				ds.updateFirst(query, opsUser);
    				userInfo.setKey(secretKey.getKey());
    				return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
    			} else {
    				return QinShihuangResult.getResult(ErrorCode.PWD_INVALID_1004);
    			}
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			throw e;
    		}
    	}
    }

	/**
	 * 第三方登录
	 * @param provider 第三方平台名称
	 * @param oauthId 第三方平台的用户id
	 * @param nickName 第三方平台的用户昵称
	 * @param avatar 第三方平台的用户头像
	 * @param token 第三方平台的用户令牌
	 * @param clientId 个推客户端id
	 * @return 用户信息
	 */
	public static String oauthlogin(String provider, String oauthId, String nickName, String avatar, String token, String clientId) {
		// 查询用户是否存在

//		Query<>

		// 绑定个推的clientId

		// 首次登录，创建新用户

		// 非首次登录，直接登录

		return null;
	}

	/**
	 * 不羁旅行令牌是否合法
	 * @param userId 用户id
	 * @param key 不羁旅行令牌
	 * @return 是否合法
	 * @throws Exception 异常
	 */
	public static boolean delKey(Long userId, String key) throws Exception {
		Query<Credential> query = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId).field(Credential.fd_key).equal(key);
		UpdateOperations<Credential> ops = ds.createUpdateOperations(Credential.class)
				.unset(Credential.fd_secretKey);
		try {
			return ds.findAndModify(query, ops, true) != null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 登出
	 * @param key 不羁旅行令牌
	 * @return 结果
	 */
	public static String logout(Long userId, String key) throws Exception {
		try {
			if(delKey(userId, key)) {
				// 解除个推客户端的绑定
				Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId);
				UpdateOperations<UserInfo> ops = ds.createUpdateOperations(UserInfo.class)
						.set(UserInfo.fd_logoutTime, System.currentTimeMillis())
						.unset(UserInfo.fd_clientId);
				ds.updateFirst(query, ops);
				return QinShihuangResult.ok();
			} else {
				return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1006);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
    /**
     * 重置密码
     * @param account 账户
     * @param newPassword 新密码
     * @param token 令牌
     * @param isTel 是否手机号
     * @return 结果
     * @throws Exception 异常
     */
    public static String resetPwd(String account, String newPassword, String token, boolean isTel) throws Exception {
    	// 检查令牌是否合法
    	try {
	    	// 检验token的合法性
	    	if(!checkTokenValid(token)) {
	    		return QinShihuangResult.getResult(ErrorCode.TOKEN_INVALID_1007);
	    	}
    	} catch(Exception e) {
    		throw e;
    	}
    	Query<UserInfo> query = ds.createQuery(UserInfo.class);
    	if(isTel)
    		query.field(UserInfo.fd_number).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	else
    		query.field(UserInfo.fd_email).equal(account).field(UserInfo.fd_status).equal(Constant.USER_NORMAL);
    	UserInfo userInfo = null;
    	try {
    		userInfo = query.get();
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	if(userInfo == null) {
    		return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1007);
    	} else {
    		long userId = userInfo.getUserId();
    		Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId);
    		
        	// 生成密码
    		try {
    			// 生成64个字节的salt
    			String salt = MessageDigest.getInstance("MD5").digest(String.valueOf(System.currentTimeMillis()).getBytes()).toString();
    			byte[] bytes = MessageDigest.getInstance("SHA-256").digest((salt + newPassword).getBytes());
    			String passwdHash = bytesToString(bytes);
    			SecretKey secretKey = new SecretKey();
    			UpdateOperations<Credential> opsCredential = ds.createUpdateOperations(Credential.class)
    					.set(Credential.fd_salt, salt).set(Credential.fd_passwdHash, passwdHash)
    					.set(Credential.fd_secretKey, secretKey);
    			ds.updateFirst(queryCredential, opsCredential);
    			return QinShihuangResult.ok();
    		} catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			throw e;
    		}
    	}
    }

    /**
     * 修改密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param userId 用户id
     * @return 结果
     * @throws Exception 异常
     */
    public static String updatePwd(String oldPwd, String newPwd, Long userId, String key) throws Exception {
    	// 检验用户是否存在
    	Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId);
    	Credential credential = null;
    	try {
    		credential = queryCredential.get();
    		if(credential == null) {
    			return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1008);
    		} else {
    			// 用户是否登录
    			if(!credential.getSecretKey().getKey().equals(key))
    				return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1008);
    			String salt = credential.getSalt();
    			byte[] bytes = MessageDigest.getInstance("SHA-256").digest((salt + oldPwd).getBytes());
    			String oldPwdHash = bytesToString(bytes);
    			if(oldPwdHash.equals(credential.getPasswdHash())) {
    				bytes = MessageDigest.getInstance("SHA-256").digest((salt + newPwd).getBytes());
        			String newPwdHash = bytesToString(bytes);
        			UpdateOperations<Credential> opsCredential = ds.createUpdateOperations(Credential.class)
        					.set(Credential.fd_passwdHash, newPwdHash);
        			ds.updateFirst(queryCredential, opsCredential);
        			return QinShihuangResult.ok();
    			} else {
    				return QinShihuangResult.getResult(ErrorCode.OLD_PWD_INVALID_1008);
    			}
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    }

	/**
	 * 不羁旅行令牌是否合法
	 * @param userId 用户id
	 * @param key 不羁旅行令牌
	 * @return 是否合法
	 * @throws Exception 异常
	 */
	public static boolean checkKeyValid(Long userId, String key) throws Exception {
		Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_userId).equal(userId).field(Credential.fd_key).equal(key);
		try {
			return queryCredential.get() != null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据用户id取得用户信息
	 * @param userId 用户id
	 * @param key 不羁旅行令牌
	 * @return 用户信息
	 * @throws Exception 异常
	 */
	public static String getUserInfoById(Long userId, String key) throws Exception {
		try {
			if(checkKeyValid(userId, key)) {
				Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_userId).equal(userId);
				UserInfo userInfo = query.get();
				if(userInfo == null) {
					return QinShihuangResult.getResult(ErrorCode.USER_NOT_EXIST_1009);
				} else {
					return QinShihuangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
				}
			} else {
				return QinShihuangResult.getResult(ErrorCode.UNLOGIN_1009);
			}
		} catch (Exception e) {
			throw e;
		}
	}


}