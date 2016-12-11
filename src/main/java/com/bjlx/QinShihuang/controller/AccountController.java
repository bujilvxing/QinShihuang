package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.AccountAPI;
import com.bjlx.QinShihuang.requestmodel.*;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户账户相关
 * Created by pengyt on 2016/10/20.
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/app/test", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String test() {

        return "{\"code\":200,\"msg\":\"成功\"}";
    }

    /**
     * 发送验证码, 接口编码1001
     * @param validationCodeReq 参数
     * @return 用户接收验证码的手机号，验证码
     */
    @RequestMapping(value = "/app/validationcodes", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendValidationCode(@RequestBody ValidationCodeReq validationCodeReq) {
        // 参数校验
        // account必须存在
        if(validationCodeReq.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1001);
        }
        // action必须存在
        if(validationCodeReq.getAction() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_NULL_1001);
        }

        // 检查action的值的合法性
        if(!Constant.checkValidationAction(validationCodeReq.getAction().intValue())) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_LIMIT_1001);
        }
        
        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(validationCodeReq.getAccount())) {
        	// 校验是否过了允许下次发送发验证码的时间
        	try {
	        	if(AccountAPI.isAllowSendValidationCode(validationCodeReq.getAccount(), true)) {
	        		/**
	                 * 发送短信验证码, isTel参数取值
	                 * true表示使用的是手机号
	                 * false表示使用的是邮箱
	                 */
	        		return AccountAPI.sendValidationCode(validationCodeReq.getAccount(), validationCodeReq.getAction(), true);
	        	} else {
	        		return QinShihuangResult.getResult(ErrorCode.TIME_LIMIT_1001);
	        	}
        	} catch(Exception e1) {
    			return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
    		}
        }

        if(CommonUtil.isEmail(validationCodeReq.getAccount())) {
            // 校验是否过了允许下次发送发验证码的时间
        	try {
	            if(AccountAPI.isAllowSendValidationCode(validationCodeReq.getAccount(), false)) {
	                // 发送邮件
	                return AccountAPI.sendValidationCode(validationCodeReq.getAccount(), validationCodeReq.getAction(), false);
	            } else {
	                return QinShihuangResult.getResult(ErrorCode.TIME_LIMIT_1001);
	            }
        	} catch (Exception e1) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1001);
        }
    }

    /**
     * 检验验证码，创建一个合法的token, 接口编码1002
     * @param validationCodeReq 验证码信息
     * @return 合法的令牌
     */
    @RequestMapping(value = "/app/tokens", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String checkValidationCode(@RequestBody ValidationCodeReq validationCodeReq) {

        // 参数校验
        // account必须存在
        if(validationCodeReq.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1002);
        }
        // code必须存在
        if(validationCodeReq.getCode() == null) {
            return QinShihuangResult.getResult(ErrorCode.CODE_NULL_1002);
        }

        // 检查验证码是否为六位数字
        if(!AccountAPI.isCode(validationCodeReq.getCode())) {
            return QinShihuangResult.getResult(ErrorCode.CODE_INVALID_1002);
        }

        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(validationCodeReq.getAccount())) {
            try {
                return AccountAPI.checkValidationCode(validationCodeReq.getAccount(), validationCodeReq.getCode(), true);
            } catch(Exception e1) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        }

        if(CommonUtil.isEmail(validationCodeReq.getAccount())) {
            try {
                return AccountAPI.checkValidationCode(validationCodeReq.getAccount(), validationCodeReq.getCode(), false);
            } catch(Exception e1) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1002);
        }
    }

    /**
     * 用户注册, 接口编码1003
     * @param userInfoReq 用户注册信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String signup(@RequestBody UserInfoReq userInfoReq) {
    	// 校验参数
    	if(userInfoReq.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1003);
        }
        if(userInfoReq.getPassword() == null) {
            return QinShihuangResult.getResult(ErrorCode.PWD_NULL_1003);
        }
        if(userInfoReq.getToken() == null) {
            return QinShihuangResult.getResult(ErrorCode.TOKEN_NULL_1003);
        }
        int promotionCodeSize = userInfoReq.getPromotionCodeSize() == null ? Constant.DEFAULT_PROMOTIONCODE_SIZE : userInfoReq.getPromotionCodeSize();

        if(CommonUtil.isTelLegal(userInfoReq.getAccount())) {
            try {
                return AccountAPI.signup(userInfoReq.getAccount(), userInfoReq.getToken(), userInfoReq.getPassword(), true, promotionCodeSize);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        }

        if(CommonUtil.isEmail(userInfoReq.getAccount())) {
            try {
                return AccountAPI.signup(userInfoReq.getAccount(), userInfoReq.getToken(), userInfoReq.getPassword(), false, promotionCodeSize);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1003);
        }
    }

    /**
     * 用户登录, 接口编码1004
     * @param loginReq 用户登录信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/login", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String login(@RequestBody LoginReq loginReq) {
    	// 检验参数
    	if(loginReq.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1004);
        }
        if(loginReq.getPassword() == null) {
            return QinShihuangResult.getResult(ErrorCode.PWD_NULL_1004);
        }
        if(loginReq.getClientId() == null) {
            return QinShihuangResult.getResult(ErrorCode.CLIENTID_NULL_1004);
        }
        if(CommonUtil.isTelLegal(loginReq.getAccount())) {
            try {
                return AccountAPI.login(loginReq.getAccount(), loginReq.getPassword(), loginReq.getClientId(), true);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        }

        if(CommonUtil.isEmail(loginReq.getAccount())) {
            try {
                return AccountAPI.login(loginReq.getAccount(), loginReq.getPassword(), loginReq.getClientId(), false);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1004);
        }
    }

    /**
     * 第三方登录, 接口编码1005
     * @param oauthUserInfoReq 用户第三方信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/oauthlogin", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String oauthLogin(@RequestBody OAuthUserInfoReq oauthUserInfoReq) {

        if(oauthUserInfoReq.getProvider() == null) {
            return QinShihuangResult.getResult(ErrorCode.PROVIDER_NULL_1005);
        }
        if(oauthUserInfoReq.getOauthId() == null) {
            return QinShihuangResult.getResult(ErrorCode.OAUTHID_NULL_1005);
        }
        if(oauthUserInfoReq.getToken() == null) {
            return QinShihuangResult.getResult(ErrorCode.TOKEN_NULL_1005);
        }
        if(oauthUserInfoReq.getClientId() == null) {
            return QinShihuangResult.getResult(ErrorCode.CLIENTID_NULL_1005);
        }

        try {
            return AccountAPI.oauthlogin(oauthUserInfoReq.getProvider(), oauthUserInfoReq.getOauthId(), oauthUserInfoReq.getNickName(), oauthUserInfoReq.getAvatar(), oauthUserInfoReq.getToken(), oauthUserInfoReq.getClientId());
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 退出登录, 接口编码1006
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果信息
     */
    @RequestMapping(value = "/app/logout", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String logout(@RequestHeader("userId") Long userId, @RequestHeader("key") String key) {

        try {
            return AccountAPI.logout(userId, key);
        } catch (Exception e) {
            e.printStackTrace();
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 重置密码, 接口编码1007
     * @param resetPwdReq 参数信息
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/password", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String resetPwd(@RequestBody ResetPwdReq resetPwdReq) {
    	// 检验参数
    	if(resetPwdReq.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1007);
        }
        if(resetPwdReq.getNewPassword() == null) {
            return QinShihuangResult.getResult(ErrorCode.NEW_PWD_NULL_1007);
        }
        if(resetPwdReq.getToken() == null) {
            return QinShihuangResult.getResult(ErrorCode.TOKEN_NULL_1007);
        }
        if(CommonUtil.isTelLegal(resetPwdReq.getAccount())) {
            try {
                return AccountAPI.resetPwd(resetPwdReq.getAccount(), resetPwdReq.getNewPassword(), resetPwdReq.getToken(), true);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        }

        if(CommonUtil.isEmail(resetPwdReq.getAccount())) {
            try {
                return AccountAPI.resetPwd(resetPwdReq.getAccount(), resetPwdReq.getNewPassword(), resetPwdReq.getToken(), false);
            } catch(Exception e) {
                e.printStackTrace();
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1007);
        }
    }

    /**
     * 修改密码, 接口编码1008
     * @param updatePwd 参数信息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/password", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updatePwd(@RequestBody UpdatePwdReq updatePwd, @PathVariable("userId") Long userId, @RequestHeader("key") String key) {
    	// 检验参数
    	if(updatePwd.getOldPassword() == null) {
            return QinShihuangResult.getResult(ErrorCode.OLD_PWD_NULL_1008);
        }
        if(updatePwd.getNewPassword() == null) {
            return QinShihuangResult.getResult(ErrorCode.NEW_PWD_NULL_1008);
        }
        
        try {
            return AccountAPI.updatePwd(updatePwd.getOldPassword(), updatePwd.getNewPassword(), userId, key);
        } catch(Exception e) {
            e.printStackTrace();
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 根据用户id取得用户信息, 接口编码1009
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserInfo(@PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        try {
            return AccountAPI.getUserInfoById(userId, key);
        } catch (Exception e) {
            e.printStackTrace();
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 修改用户信息, 接口编码1010
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @param updateUserInfoReq 参数信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateUserInfo(@PathVariable("userId") Long userId, @RequestHeader("key") String key, @RequestBody UpdateUserInfoReq updateUserInfoReq) {
        try {
            return AccountAPI.updateUserInfo(userId, key, updateUserInfoReq);
        } catch (Exception e) {
            e.printStackTrace();
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 绑定手机号, 接口编码1011
     * @param bindTelReq 参数信息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/tel", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String bindTel(@RequestBody BindTelReq bindTelReq, @PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        // 检验参数
        if(bindTelReq.getTel() == null) {
            return QinShihuangResult.getResult(ErrorCode.TEL_NULL_1011);
        }

        if(bindTelReq.getToken() == null) {
            return QinShihuangResult.getResult(ErrorCode.TOKEN_NULL_1011);
        }
        if(CommonUtil.isTelLegal(bindTelReq.getTel())) {
            try {
                return AccountAPI.bindTel(bindTelReq.getTel(), bindTelReq.getToken(), userId, key);
            } catch (Exception e) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.TEL_FORMAT_1011);
        }
    }

    /**
     * 绑定邮箱, 接口编码1012
     * @param bindEmailReq 参数信息
     * @param userId 用户id
     * @param key 不羁旅行令牌
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}/email", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String bindEmail(@RequestBody BindEmailReq bindEmailReq, @PathVariable("userId") Long userId, @RequestHeader("key") String key) {
        // 检验参数
        if(bindEmailReq.getEmail() == null) {
            return QinShihuangResult.getResult(ErrorCode.EMAIL_NULL_1012);
        }

        if(bindEmailReq.getToken() == null) {
            return QinShihuangResult.getResult(ErrorCode.TOKEN_NULL_1012);
        }
        if(CommonUtil.isEmail(bindEmailReq.getEmail())) {
            try {
                return AccountAPI.bindEmail(bindEmailReq.getEmail(), bindEmailReq.getToken(), userId, key);
            } catch (Exception e) {
                return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return QinShihuangResult.getResult(ErrorCode.EMAIL_FORMAT_1012);
        }
    }
}
