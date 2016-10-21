package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.AccountAPI;
import com.bjlx.QinShihuang.requestmodel.ValidationCode;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody String sendValidationcode(@RequestBody ValidationCode validationCode) {
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
            data = AccountAPI.sendValidationcode(validationCode.getAccount(), validationCode.getAction());
            return QinShihuangResult.ok(data);
        }

        if(CommonUtil.isEmail(validationCode.getAccount())) {
            // 发送邮件

            return QinShihuangResult.ok(data);
        } else {
            return QinShihuangResult.getResult(ErrorCode.ACCOUNT_FORMAT_1001);
        }
    }
}
