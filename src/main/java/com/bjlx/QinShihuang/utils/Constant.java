package com.bjlx.QinShihuang.utils;

/**
 * Created by xiaozhi on 2016/10/21.
 */
public class Constant {

    /**
     * 验证码的类型
     */
    public final static Integer NEW_USER_SIGNUP_ACTION = 1;
    public final static Integer BIND_TEL_ACTION = 2;
    public final static Integer RESET_PWD_ACTION = 3;

    public static boolean checkValidationAction(Integer action) {
        return action >= NEW_USER_SIGNUP_ACTION && NEW_USER_SIGNUP_ACTION <= RESET_PWD_ACTION;
    }

    /**
     *
     */
}
