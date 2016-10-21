package com.bjlx.QinShihuang.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
     * @param account 手机号
     * @param action 验证码的用途
     * @return 发送结果
     */
    public static JsonNode sendValidationcode(String account, int action) {
        ObjectNode data = mapper.createObjectNode();

        data.put("account", "");
        data.put("validationCode", "");

        return data;
    }
}