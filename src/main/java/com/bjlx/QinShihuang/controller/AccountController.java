package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.AccountAPI;
import com.bjlx.QinShihuang.requestmodel.*;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
     * @param validationCode 参数
     * @return 用户接收验证码的手机号，验证码
     */
    @RequestMapping(value = "/app/validationcodes", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendValidationCode(@RequestBody ValidationCode validationCode) {
        // 参数校验
        // account必须存在
        if(validationCode.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1001);
        }
        // action必须存在
        if(validationCode.getAction() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_NULL_1001);
        }

        // 检查action的值的合法性
        if(Constant.checkValidationAction(validationCode.getAction().intValue())) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_LIMIT_1001);
        }
        JsonNode data = null;
        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(validationCode.getAccount())) {
            // 发送短信验证码
            data = AccountAPI.sendValidationCode(validationCode.getAccount(), validationCode.getAction());
            return QinShihuangResult.ok(data);
        }

        if(CommonUtil.isEmail(validationCode.getAccount())) {
            // 发送邮件

            return QinShihuangResult.ok(data);
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1001);
        }
    }

    /**
     * 检验验证码，创建一个合法的token, 接口编码1002
     * @param validationCode 验证码信息
     * @return 合法的令牌
     */
    @RequestMapping(value = "/app/tokens", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String checkValidationCode(@RequestBody ValidationCode validationCode) {

        // 参数校验
        // account必须存在
        if(validationCode.getAccount() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_NULL_1002);
        }
        // action必须存在
        if(validationCode.getAction() == null) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_NULL_1002);
        }

        // 检查action的值的合法性
        if(Constant.checkValidationAction(validationCode.getAction().intValue())) {
            return QinShihuangResult.getResult(ErrorCode.ACTION_LIMIT_1002);
        }

        // 检查验证码是否为六位数字



        // 返回token
        return null;
    }

    /**
     * 用户注册, 接口编码1003
     * @param userInfo 用户注册信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String signup(@RequestBody UserInfo userInfo) {

        // 创建密码

        return null;
    }

    /**
     * 用户登录, 接口编码1004
     * @param userInfo 用户登录信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/login", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String login(@RequestBody UserInfo userInfo) {

        // 绑定个推的clientId

        return null;
    }

    /**
     * 第三方登录, 接口编码1005
     * @param oAuthUserInfo 用户第三方信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/oauthlogin", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String oauthLogin(@RequestBody OAuthUserInfo oAuthUserInfo) {

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
    public @ResponseBody String resetPwd(@RequestBody ResetPwd resetPwd) {


        return null;
    }

    /**
     * 修改密码, 接口编码1007
     * @param updatePwd 参数信息
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId +d}/password", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String updatePwd(@RequestBody UpdatePwd updatePwd, @PathVariable Long userId) {


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
        String bjlxToken = null;

        // 检验令牌

        return null;
    }

    /**
     * 修改用户信息, 接口编码1009
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "/app/users/{userId +d}", method= RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public @ResponseBody String updateUserInfo(@PathVariable Long userId) {

        // 取得用户的令牌
        String bjlxToken = null;

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
        String bjlxToken = null;

        // 检验令牌

        // 解绑定

        return null;
    }

    /**
     * 绑定手机号, 接口编码1011
     * @return 结果信息
     */
    @RequestMapping(value = "/app/users/{userId}/tel", method= RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public @ResponseBody String bindTel(@RequestBody BindTel bindTel, @PathVariable Long userId) {

        // 取得用户的令牌
        String bjlxToken = null;

        // 检验令牌

        // 解绑定

        return null;
    }


}
