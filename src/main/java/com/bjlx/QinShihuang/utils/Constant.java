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

    /**
     * 消息类型
     */
    public final static int TEXT_MSG = 1;
    public final static int IMAGE_MSG = 2;
    public final static int AUDIO_MSG = 3;
    public final static int LOCATION_MSG = 4;
    public final static int GUIDE_MSG = 5;
    public final static int TRAVEL_NOTE_MSG = 6;
    public final static int VIEWSPOT_MSG = 7;
    public final static int RESTAURANT_MSG = 8;
    public final static int SHOPPING_MSG = 9;
    public final static int HOTEL_MSG = 10;
    public final static int COMMODITY_MSG = 11;
    public final static int ORDER_MSG = 12;
    public final static int IDCARD_MSG = 13;
    public final static int FOLLOWING_MSG = 14;
    public final static int TIP_MSG = 15;
    public final static int MOMENT_MSG = 16;
    public final static int NOTICE_MSG = 17;
    public static boolean checkMsgType(Integer msgType) {
        return msgType >= TEXT_MSG && msgType <= NOTICE_MSG;
    }

    /**
     * 聊天类型
     */
    public final static int SINGLE_CHAT = 1;
    public final static int GROUP_CHAT = 2;
    public static boolean checkChatType(Integer chatType) {
        return chatType >= SINGLE_CHAT && chatType <= GROUP_CHAT;
    }

    /**
     * 商品状态
     * 1、审核中(待审核), 2、审核不通过  3、审核通过  4、下架 5、上架
     */
    public final static int PENDING_COMMODITY = 1;
    public final static int UPPASS_COMMODITY = 2;
    public final static int PASSED_COMMODITY = 3;
    public final static int ON_SHELVES_COMMODITY = 4;
    public final static int OFF_SHELVES_COMMODITY = 5;
    public static boolean checkCommodityStatus(Integer status) {
        return status >= PENDING_COMMODITY && status <= OFF_SHELVES_COMMODITY;
    }

    public final static int ABBREV_MAX_LEN = 16;

    /**
     * 专栏状态
     */

    //幻灯片类型
    public final static String SLIDE_COLUMN = "slide";
    public final static String SPECIAL_COLUMN = "special";

    /**
     * 群组状态
     */
    public final static int CHATGROUP_NORMAL = 1;
    public final static int CHATGROUP_UNENABLE = 2;
    public final static int CHATGROUP_PENDING = 3;

    /**
     * 全站搜索
     */
    public final static int SEARCH_ALL_OFFSET = 0;
    public final static int SEARCH_ALL_LIMIT = 3;
    public final static int SEARCH_CONDITION_OFFSET = 0;
    public final static int SEARCH_CONDITION_LIMIT = 10;

    /**
     * 收藏类型
     */
    public final static int FAVORITE_POST = 1;
    public final static int FAVORITE_TRACE = 2;
    public final static int FAVORITE_TRIPPLAN = 3;
    public final static int FAVORITE_ACTIVITY = 4;
    public final static int FAVORITE_QUORA = 5;
    public final static int FAVORITE_RESTAURANT = 6;
    public final static int FAVORITE_HOTEL = 7;
    public final static int FAVORITE_TRAVELNOTE = 8;
    public final static int FAVORITE_VIEWSPOT = 9;
    public final static int FAVORITE_SHOPPING = 10;
    public final static int FAVORITE_MOMENT = 11;
    public final static int FAVORITE_COMMODITY = 12;
    public static boolean checkFavoriteType(Integer favoriteType) {
        return favoriteType >= FAVORITE_POST && favoriteType <= FAVORITE_COMMODITY;
    }

    /**
     * 点赞类型
     */
    public final static int VOTE_POST = 1;
    public final static int VOTE_TRACE = 2;
    public final static int VOTE_TRIPPLAN = 3;
    public final static int VOTE_ACTIVITY = 4;
    public final static int VOTE_QUESTION = 5;
    public final static int VOTE_ANSWER = 6;
    public final static int VOTE_MOMENT = 7;
    public final static int VOTE_TRAVELNOTE = 8;
    public static boolean checkVoteType(Integer voteType) {
        return voteType >= VOTE_POST && voteType <= VOTE_TRAVELNOTE;
    }

    /**
     * 问答
     */
    public final static int ANSWER_NORMAL = 1;
    public final static int ANSWER_UNENABLE = 2;
    public final static int QUESTION_NORMAL = 1;
    public final static int QUESTION_UNENABLE = 2;

    /**
     * 行程规划状态
     */
    public final static int TRIPPLAN_NORMAL = 1;
    public final static int TRIPPLAN_UNENABLE = 2;

    /**
     * 门票退款方式，1表示退款到平台公共账号，2表示原路返回，3表示不接受退款
     */
    public final static int REFUND_BJLX = 1;
    public final static int REFUND_ORIGIN = 2;
    public final static int REFUND_NOWAY = 3;

    /**
     * 活动状态
     */
    public final static int ACTIVITY_NORMAL = 1;
    public final static int ACTIVITY_UNENABLE = 2;

    /**
     * 活动是否可见
     */
    public final static int ACTIVITY_UNVISIABLE = 1;
    public final static int ACTIVITY_VISIABLE = 2;

    /**
     * 门票状态
     */
    public final static int TICKET_NORMAL = 1;
    public final static int TICKET_UNENABLE = 2;

    /**
     * 游记状态
     */
    public final static int TRAVELNOTE_NORMAL = 1;
    public final static int TRAVELNOTE_UNENABLE = 2;

    /**
     * 帖子状态
     */
    public final static int POST_NORMAL = 1;
    public final static int POST_UNENABLE = 2;

    /**
     * 足迹状态
     */
    public final static int TRACE_NORMAL = 1;
    public final static int TRACE_UNENABLE = 2;
}
