package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.SmsFormatter;
import com.bjlx.QinShihuang.model.Sms;
import com.bjlx.QinShihuang.utils.SmsSendUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

/**
 * 账户核心实现
 * Created by xiaozhi on 2016/10/21.
 */
public class AccountAPI {

    /**
     * 初始化一个mapper对象
     */
    public static ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    /**
     * 发送验证码
     * @param tel 手机号
     * @param action 验证码的用途
     * @return 发送结果
     */
    public static JsonNode sendValidationCode(String tel, int action) {
        // 存数据库


        // 产生验证码
        String validationCode = String.format("%d", (int) Math.random() * 1000000);
        // 短信内容
        String[] smsdata = new String[1];
        smsdata[0] = String.format("您的验证码是：%d", validationCode);

        Sms sms = new Sms(validationCode, tel);

        // 发送短信
        SmsSendUtil.sendMessageByTemplate(tel, smsdata);

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