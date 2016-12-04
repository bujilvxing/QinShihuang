package com.bjlx.QinShihuang.utils;

/**
 * 错误码
 * 所有接口的错误码都在这里，除了通用错误，其他的错误码都是按照接口来定的
 * 错误码由6位数字构成，每1位的含义如下：
 * 第1位：表示产品编号
 * 第2~4位：表示接口编号
 * 第5~6位：表示指定接口的错误编号
 * 比如100101表示第一个产品的第一个接口的第一个错误编号，这样可以根据错误码，定位到是哪个接口出现了什么问题
 *
 * 错误名称定义是由错误描述加上接口编号，比如：TEL_FORMAT_1001表示手机号格式错误，是1001接口抛出来的
 */
public enum ErrorCode {

	/**
	 * 通用错误
	 */
	OK(0, "Normal"),
	INVALID_ARGUMENTS(100, "Invalid arguments"),
	FORBIDDEN(403, "Forbidden"), 
	NOT_FOUND(404, "Resource not found"),
	SERVER_EXCEPTION(500, "Server Exception"),
	ALIPAY_REFUND(901, "Ali pay refund need manual operation"),
	UNKNOWN(999, "Unknown error"),
	NETWORK_ERROR(601, "网络异常"),

	// 图片
	IMAGE_KEY_NULL(100001, "图片key为空"),
	IMAGE_BUCKET_NULL(100002, "图片bucket为空"),
	IMAGE_URL_NULL(100003, "图片url为空"),
	IMAGE_WIDTH_NULL(100004, "图片width为空"),
	IMAGE_HEIGHT_NULL(100005, "图片height为空"),
	IMAGE_FMT_NULL(100006, "图片fmt为空"),
	IMAGE_HASH_NULL(100007, "图片hash为空"),
	IMAGE_SIZE_NULL(100008, "图片size为空"),

	/**
	 * 验证码
 	 */

	// 发送验证码
	ACCOUNT_NULL_1001(100101, "参数账户为空"),
	ACCOUNT_FORMAT_1001(100102, "账户格式不正确"),
	ACTION_NULL_1001(100103, "参数action为空"),
	ACTION_LIMIT_1001(100104, "参数action的值不合法"),
	USER_EXIST_1001(100105, "用户已存在"),
	USER_NOT_EXIST_1001(100106, "账户不存在"),
	TEL_EXIST_1001(100107, "手机号已存在"),
	EMAIL_EXIST_1001(100108, "邮箱已存在"),
	TIME_LIMIT_1001(100109, "请求过于频繁，请稍后再试"),
	REQUEST_TOO_MANY_1001(100110, "请求次数过多"),

	// 检验验证码
	ACCOUNT_NULL_1002(100201, "参数账户为空"),
	ACCOUNT_FORMAT_1002(100202, "账户格式不正确"),
	CODE_NULL_1002(100203, "参数验证码为空"),
	CODE_INVALID_1002(100204, "验证码不合法"),
	VALIDATION_FAIL_1002(100205, "验证失败"),

	/**
	 * 注册登录
	 */

	// 用户注册
	ACCOUNT_NULL_1003(100301, "参数账户为空"),
	ACCOUNT_FORMAT_1003(100302, "账户格式不正确"),
	PWD_NULL_1003(100303, "参数密码为空"),
	TOKEN_NULL_1003(100304, "参数令牌为空"),
	USER_EXIST_1003(100305, "用户已存在"),
	TOKEN_INVALID_1003(100306, "令牌不合法"),
	
	// 用户登录
	ACCOUNT_NULL_1004(100401, "参数账户为空"),
	PWD_NULL_1004(100402, "参数密码为空"),
	CLIENTID_NULL_1004(100403, "参数clientId为空"),
	ACCOUNT_FORMAT_1004(100404, "账户格式不正确"),
	PWD_INVALID_1004(100405, "密码不正确"),
	USER_NOT_EXIST_1004(100406, "用户不存在"),

	// 用户第三方登录
	PROVIDER_NULL_1005(100501, "参数provider为空"),
	OAUTHID_NULL_1005(100502, "参数oauthId为空"),
	TOKEN_NULL_1005(100503, "参数token为空"),
	CLIENTID_NULL_1005(100504, "参数clientId为空"),
	PROVIDER_INVALID_1005(100505, "参数provider不合法"),

	// 登出
	UNLOGIN_1006(100601, "用户未登录"),

	/**
	 * 用户模块
	 */
	// 重置密码
	ACCOUNT_NULL_1007(100701, "参数账户为空"),
	NEW_PWD_NULL_1007(100702, "参数新密码为空"),
	TOKEN_NULL_1007(100703, "参数令牌为空"),
	ACCOUNT_FORMAT_1007(100704, "账户格式不正确"),
	USER_NOT_EXIST_1007(100705, "用户不存在"),
	TOKEN_INVALID_1007(100706, "参数令牌为空"),
	
	// 修改密码
	OLD_PWD_NULL_1008(100801, "参数旧密码为空"),
	NEW_PWD_NULL_1008(100802, "参数新密码为空"),
	UNLOGIN_1008(100803, "用户未登录"),
	OLD_PWD_INVALID_1008(100804, "旧密码不正确"),
	USER_NOT_EXIST_1008(100805, "用户不存在"),
	
	// 根据用户id取得用户信息
	UNLOGIN_1009(100901, "用户未登录"),
	USER_NOT_EXIST_1009(100902, "用户不存在"),

	// 修改用户信息
	UNLOGIN_1010(101001, "用户未登录"),
	GENDER_INVALID_1010(101002, "性别不合法"),
	ZODIAC_INVALID_1010(101003, "星座不合法"),
	USER_NOT_EXIST_1010(101004, "用户不存在"),

	// 绑定手机号
	TEL_NULL_1011(101101, "参数tel为空"),
	TOKEN_NULL_1011(101102, "参数token为空"),
	TEL_FORMAT_1011(101103, "手机号格式不正确"),
	UNLOGIN_1011(101104, "用户未登录"),
	TEL_EXIST_1011(101105, "手机号已存在"),
	TOKEN_INVALID_1011(101106, "令牌不合法"),

	// 绑定邮箱号
	EMAIL_NULL_1103(110301, "参数email为空"),
	TOKEN_NULL_1103(110302, "参数token为空"),
	EMAIL_FORMAT_1103(110303, "邮箱号格式不正确"),
	UNLOGIN_1103(110304, "用户未登录"),
	EMAIL_EXIST_1103(110305, "邮箱号已存在"),
	TOKEN_INVALID_1103(110306, "令牌不合法"),

	/**
	 * 其他模块
	 */
	// 申请商家
	TEL_NULL_1012(101201, "参数tel为空"),
	TEL_FORMAT_1012(101202, "手机号格式不正确"),
	UNLOGIN_1012(101203, "用户未登录"),

	// 用户反馈
	CONTENT_NULL_1013(101301, "参数content为空"),
	UNLOGIN_1013(101302, "用户未登录"),

	/**
	 * 首页模块
	 */
	// 取得专栏列表
	EMPTY_COLUMN_1014(101401, "运营专栏数据为空"),

	// 取得专栏列表
	EMPTY_COLUMN_1015(101501, "banner数据为空"),

	// 取得首页商品列表(特产等)
	EMPTY_COLUMN_COMMODITY_1016(101601, "首页商品数据为空"),
	EMPTY_COLUMN_COMMODITY_MODULE_1016(101602, "首页商品数据为空"),

	// 根据id取得商品详情
	COMMODITY_NOT_EXIST_1017(101701, "商品不存在"),

	// 取得首页攻略列表
	EMPTY_COLUMN_GUIDE_1018(101801, "攻略数据为空"),

	/**
	 * 攻略
	 */
	GUIDE_NOT_EXIST_1019(101901, "攻略不存在"),

	/**
	 * 时间线
	 */
	// 查看朋友圈
	TIME_NULL_1038(103801, "latestTime和earliestTime不可同时为空"),
	UNLOGIN_1038(103801, "用户未登录"),

	// 查看朋友圈
	ORIGINUSERID_NULL_1039(103901, "originUserId不可为空"),
	ORIGINNICKNAME_NULL_1039(103902, "originNickName不可为空"),
	ORIGINAVATAR_NULL_1039(103903, "OriginAvatar不可为空"),
	UNLOGIN_1039(103904, "用户未登录"),
	USER_NOT_EXIST_1039(103905, "用户不存在"),

	// 查看朋友圈
	TIME_NULL_1080(108001, "latestTime和earliestTime不可同时为空"),
	UNLOGIN_1080(108002, "用户未登录"),

	/**
	 * 行程规划
	 */
	// 发布行程规划
	UNLOGIN_1045(104501, "用户未登录"),
	USER_NOT_EXIST_1045(104502, "用户不存在"),
	ORIGINUSERID_NULL_1045(104503, "源用户id不可为空"),
	ORIGINNICKNAME_NULL_1045(104504, "源用户昵称不可为空"),
	ORIGINAVATAR_NULL_1045(104505, "源用户头像不可为空"),

	// 复制行程规划
	UNLOGIN_1046(104601, "用户未登录"),
	TRIPPLAN_NOT_EXIST_1046(104602, "行程规划不存在"),
	USER_NOT_EXIST_1046(104603, "用户不存在"),

	// 取得行程规划详情
	TRIPPLAN_NOT_EXIST_1049(104901, "行程规划不存在"),

	// 删除行程规划
	UNLOGIN_1050(105001, "用户未登录"),

	/**
	 * 问答
	 */
	// 添加问题
	TITLE_NULL_1051(105101, "标题不可为空"),
	CONTENT_NULL_1051(105102, "内容不可为空"),
	UNLOGIN_1051(105103, "用户未登录"),
	USER_NOT_EXIST_1051(105104, "用户不存在"),

	// 取得问题详情
	QUESTION_NOT_EXIST_1052(105201, "问题不存在"),

	// 取得用户问题列表
	UNLOGIN_1053(105301, "用户未登录"),

	// 添加回答
	TITLE_NULL_1081(108101, "标题不可为空"),
	CONTENT_NULL_1081(108102, "内容不可为空"),
	UNLOGIN_1081(108103, "用户未登录"),
	USER_NOT_EXIST_1081(108104, "用户不存在"),

	// 删除问题
	UNLOGIN_1082(108201, "用户未登录"),

	// 删除回答
	UNLOGIN_1083(108301, "用户未登录"),

	// 编辑问题
	UNLOGIN_1084(108401, "用户未登录"),

	// 编辑回答
	UNLOGIN_1085(108501, "用户未登录"),

	/**
	 * 社交
	 */
	// 关注用户
	FOLLOWINGID_NULL_1055(105501, "参数followingId不可为空"),
	UNLOGIN_1055(105502, "用户未登录"),
	USER_NOT_EXIST_1055(105503, "用户不存在"),

	// 取消关注用户
	FOLLOWINGID_NULL_1056(105601, "参数followingId不可为空"),
	UNLOGIN_1056(105602, "用户未登录"),

	// 取得好友列表
	UNLOGIN_1057(105701, "用户未登录"),

	// 取得好友(关注人)信息
	UNLOGIN_1058(105801, "用户未登录"),
	USER_NOT_EXIST_1058(105802, "用户不存在"),

	// 修改备注
	MEMO_NULL_1059(105901, "备注不可为空或者或字符串"),
	UNLOGIN_1059(105902, "用户未登录"),
	USER_NOT_EXIST_1059(105903, "用户不存在"),

	// 添加黑名单
	BLOCKID_NULL_1060(106001, "屏蔽用户id不可为空"),
	UNLOGIN_1060(106002, "用户未登录"),
	USER_NOT_EXIST_1060(106003, "用户不存在"),

	// 添加黑名单
	UNLOGIN_1061(106101, "用户未登录"),
	USER_NOT_EXIST_1061(106102, "用户不存在"),

	// 取得用户关注列表
	UNLOGIN_1062(106201, "用户未登录"),

	// 取得用户粉丝列表
	UNLOGIN_1063(106301, "用户未登录"),

	// 发送消息
	RECEIVERID_NULL_1064(106401, "接收者id不可为空"),
	CONTENT_NULL_1064(106402, "消息内容不可为空"),
	MSGTYPE_NULL_1064(106403, "消息类型不可为空"),
	CHATTYPE_NULL_1064(106404, "聊天类型不可为空"),
	THUMB_NULL_1064(106405, "缩略图不可为空"),
	FULL_NULL_1064(106406, "完整图不可为空"),
	ORIGIN_NULL_1064(106407, "原图不可为空"),
	URL_NULL_1064(106408, "链接不可为空"),
	AUDIO_NULL_1064(106409, "音频不可为空"),
	LENGTH_NULL_1064(106410, "长度不可为空"),
	POSITION_NULL_1064(106411, "位置不可为空"),
	LAT_NULL_1064(106412, "经度不可为空"),
	LNG_NULL_1064(106413, "纬度不可为空"),
	MSGTYPE_INVALID_1064(106414, "消息类型不合法"),
	CHATTYPE_INVALID_1064(106415, "聊天类型不合法"),
	USER_NOT_EXIST_1064(106416, "用户不存在"),
	CHATGROUP_NOT_EXIST_1064(106417, "群组不存在"),
	CONVID_INVALID_1064(106418, "回话id不合法"),
	UNLOGIN_1064(106419, "用户未登录"),
	ID_INVALID_1064(106420, "消息id不合法"),

	// 拉取消息
	UNLOGIN_1065(106501, "用户未登录"),

	// 修改会话属性
	MUTE_NULL_1066(106601, "mute不可为空"),
	UNLOGIN_1066(106602, "用户未登录"),
	CONV_NOT_EXIST_1066(106603, "会话不存在"),

	// 取得会话列表
	IDLIST_NULL_1067(106701, "会话id列表参数不可为空"),
	ID_INVALID_1067(106702, "会话id不合法"),
	UNLOGIN_1067(106703, "用户未登录"),

	/**
	 * 群组
	 */
	// 取得群组信息
	UNLOGIN_1070(107001, "用户未登录"),
	CHATGROUP_NOT_EXIST_1070(107002, "群组不存在"),

	/**
	 * 收藏
	 */
	// 添加收藏
	FAVORITETYPE_NULL_1077(107701, "收藏类型不可为空"),
	ITEMID_NULL_1077(107702, "收藏对象id不可为空"),
	TITLE_NULL_1077(107703, "收藏标题不可为空"),
	UNLOGIN_1077(107704, "用户未登录"),
	FAVORITETYPE_INVALID_1077(107705, "favoriteType不合法"),
	ITEMID_INVALID_1077(107706, "itemId不合法"),

	// 取消收藏
	FAVORITETYPE_NULL_1078(107801, "收藏类型不可为空"),
	FAVORITETYPE_INVALID_1078(107802, "favoriteType不合法"),
	UNLOGIN_1078(107803, "用户未登录"),

	// 取得收藏列表
	UNLOGIN_1079(107901, "用户未登录"),

	/**
	 * 点赞
	 */
	// 点赞
	VOTETYPE_NULL_1094(109401, "点赞类型不可为空"),
	ITEMID_NULL_1094(109402, "点赞对象id不可为空"),
	UNLOGIN_1094(109403, "用户未登录"),
	VOTETYPE_INVALID_1094(109404, "voteType不合法"),
	ITEMID_INVALID_1094(109405, "itemId不合法"),

	// 取消点赞
	VOTETYPE_NULL_1095(109501, "点赞类型不可为空"),
	VOTETYPE_INVALID_1095(109502, "voteType不合法"),
	UNLOGIN_1095(109503, "用户未登录"),

	// 取得点赞列表
	UNLOGIN_1096(109601, "用户未登录"),

	/**
	 * 搜索
	 */
	// 用户搜索
	QUERY_NULL_1100(110001, "query不可为空"),
	UNLOGIN_1100(110002, "用户未登录"),
	QUERY_INVALID_1100(110003, "query不合法"),
	USER_NOT_EXIST_1100(110004, "用户不存在"),

	// 群组搜索
	QUERY_NULL_1101(110101, "query不可为空"),
	UNLOGIN_1101(110102, "用户未登录"),

	// 全站搜索
	QUERY_NULL_1102(110201, "query不可为空"),
	;









	public String msg;

	public int code;
	
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}
