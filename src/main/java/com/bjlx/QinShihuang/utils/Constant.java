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

    /**
     * 性别
     */
    public final static int UNKNOW_GENDER = 1;
    public final static int MALE = 2;
    public final static int FEMALE = 3;
    public static boolean checkGender(Integer gender) {
        return gender >= UNKNOW_GENDER && gender <= FEMALE;
    }

    /**
     * 图片正确
     */
    public final static String IMAGE_NORMAL = "ok";

    /**
     * 星座
     */
    public final static int AQUARIUS = 1;
    public final static int PISCES = 2;
    public final static int ARIES = 3;
    public final static int TAURUS = 4;
    public final static int GEMINI = 5;
    public final static int CANCER = 6;
    public final static int LEO = 7;
    public final static int VIRGO = 8;
    public final static int LIBRA = 9;
    public final static int SCORPIO = 10;
    public final static int SAGITTARIUS = 11;
    public final static int CAPRICORN = 12;
    public static boolean checkZodiac(Integer zodiac) {
        return zodiac >= AQUARIUS && zodiac <= CAPRICORN;
    }

}
