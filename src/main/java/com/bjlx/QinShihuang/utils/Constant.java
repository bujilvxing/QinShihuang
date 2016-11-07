package com.bjlx.QinShihuang.utils;

/**
 * 常量列表
 * Created by xiaozhi on 2016/10/21.
 */
public class Constant {

    /**
     * 验证码的类型
     * 1表示新用户注册，2表示重置密码，3表示绑定手机号，4表示绑定邮箱
     */
    public final static int NEW_USER_SIGNUP_ACTION = 1;
    public final static int RESET_PWD_ACTION = 2;
    public final static int BIND_TEL_ACTION = 3;
    public final static int BIND_EMAIL_ACTION = 4;

    public static boolean checkValidationAction(Integer action) {
        return action >= NEW_USER_SIGNUP_ACTION && action <= BIND_EMAIL_ACTION;
    }

    /**
     * 用户状态
     */
    public final static int USER_NORMAL = 1;
    public final static int USER_UNENABLE = 2;
    
    /**
     * 默认邀请码长度
     */
    public final static int DEFAULT_PROMOTIONCODE_SIZE = 8;
}
