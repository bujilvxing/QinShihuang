package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.AccountAPI;
import com.bjlx.QinShihuang.exception.BjlxException;
import com.bjlx.QinShihuang.requestmodel.*;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.JsonNode;
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
        JsonNode data = null;
        
        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(validationCodeReq.getAccount())) {
        	// 校验是否过了允许下次发送发验证码的时间
        	if(AccountAPI.isAllowSendValidationCode(validationCodeReq.getAccount(), true)) {
        		/**
                 * 发送短信验证码, isTel参数取值
                 * true表示使用的是手机号
                 * false表示使用的是邮箱
                 */
        		try {
        			data = AccountAPI.sendValidationCode(validationCodeReq.getAccount(), validationCodeReq.getAction(), true);
        		} catch(BjlxException e) {
        			return QinShihuangResult.getResult(e.getErrorCode());
        		} catch(Exception e1) {
        			return QinShihuangResult.getResult(ErrorCode.ServerException);
        		}
                return QinShihuangResult.ok(data);
        	} else {
        		return QinShihuangResult.getResult(ErrorCode.TIME_LIMIT_1001);
        	}
        }

        if(CommonUtil.isEmail(validationCodeReq.getAccount())) {
            // 校验是否过了允许下次发送发验证码的时间
            if(AccountAPI.isAllowSendValidationCode(validationCodeReq.getAccount(), false)) {
                // 发送邮件
                try {
                    data = AccountAPI.sendValidationCode(validationCodeReq.getAccount(), validationCodeReq.getAction(), false);
                } catch (BjlxException e) {
                    return QinShihuangResult.getResult(e.getErrorCode());
                } catch (Exception e1) {
                    return QinShihuangResult.getResult(ErrorCode.ServerException);
                }
                return QinShihuangResult.ok(data);
            } else {
                return QinShihuangResult.getResult(ErrorCode.TIME_LIMIT_1001);
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

        JsonNode data = null;
        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(validationCodeReq.getAccount())) {
            try {
                data = AccountAPI.checkValidationCode(validationCodeReq.getAccount(), validationCodeReq.getCode(), true);
            } catch(BjlxException e) {
                return QinShihuangResult.getResult(e.getErrorCode());
            } catch(Exception e1) {
                return QinShihuangResult.getResult(ErrorCode.ServerException);
            }
            return QinShihuangResult.ok(data);
        }

        if(CommonUtil.isEmail(validationCodeReq.getAccount())) {
            try {
                data = AccountAPI.checkValidationCode(validationCodeReq.getAccount(), validationCodeReq.getCode(), false);
            } catch(BjlxException e) {
                return QinShihuangResult.getResult(e.getErrorCode());
            } catch(Exception e1) {
                return QinShihuangResult.getResult(ErrorCode.ServerException);
            }
            return QinShihuangResult.ok(data);
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1002);
        }
    }

    /**
     * 用户注册, 接口编码1003
     * @param userInfo 用户注册信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String signup(@RequestBody UserInfoReq userInfo) {

        // 创建密码

        return null;
    }

    /**
     * 用户登录, 接口编码1004
     * @param userInfo 用户登录信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/login", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String login(@RequestBody UserInfoReq userInfo) {

        // 绑定个推的clientId

        return null;
    }

    /**
     * 第三方登录, 接口编码1005
     * @param oAuthUserInfo 用户第三方信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/oauthlogin", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String oauthLogin(@RequestBody OAuthUserInfoReq oAuthUserInfo) {

        // 绑定个推的clientId

        // 首次登录，创建新用户

        // 非首次登录，直接登录
        return null;
    }

    /**
     * 重置密码, 接口编码1006
     * @param resetPwd 参数信息
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/password", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String resetPwd(@RequestBody ResetPwdReq resetPwd) {


        return null;
    }

    /**
     * 修改密码, 接口编码1007
     * @param updatePwd 参数信息
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId +d}/password", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updatePwd(@RequestBody UpdatePwdReq updatePwd, @PathVariable Long userId) {


        return null;
    }

    /**
     * 根据用户id取得用户信息, 接口编码1008
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/{userId +d}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getUserInfo(@PathVariable Long userId) {

        // 取得用户的令牌
//        String bjlxToken = null;

        // 检验令牌

        return null;
    }

    /**
     * 修改用户信息, 接口编码1009
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/{userId:\\d+}", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateUserInfo(@PathVariable(value="userId") Long userId) {

        // 取得用户的令牌
//        String bjlxToken = null;

        // 检验令牌

        return null;
    }

    /**
     * 退出登录, 接口编码1010
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/logout", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String logout() {

        // 取得用户的令牌
//        String bjlxToken = null;

        // 检验令牌

        // 解绑定

        return null;
    }

    /**
     * 绑定手机号, 接口编码1011
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId}/tel", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String bindTel(@RequestBody BindTelReq bindTel, @PathVariable Long userId) {

        // 取得用户的令牌
//        String bjlxToken = null;

        // 检验令牌

        // 解绑定

        return null;
    }


}
