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
	TOKEN_INVALID_1007(100706, "参数令牌不合法"),
	
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
	EMAIL_NULL_1012(101201, "参数email为空"),
	TOKEN_NULL_1012(101202, "参数token为空"),
	EMAIL_FORMAT_1012(101203, "邮箱号格式不正确"),
	UNLOGIN_1012(101204, "用户未登录"),
	EMAIL_EXIST_1012(101205, "邮箱号已存在"),
	TOKEN_INVALID_1012(101206, "令牌不合法"),

	/**
	 * 其他模块
	 */
	// 申请商家
	TEL_NULL_1013(101301, "参数tel为空"),
	TEL_FORMAT_1013(101302, "手机号格式不正确"),
	UNLOGIN_1013(101303, "用户未登录"),

	// 用户反馈
	CONTENT_NULL_1014(101401, "参数content为空"),
	UNLOGIN_1014(101402, "用户未登录"),

	/**
	 * 首页模块
	 */
	// 取得专栏列表
	EMPTY_COLUMN_1015(101501, "运营专栏数据为空"),

	// 取得专栏列表
	EMPTY_COLUMN_1016(101601, "banner数据为空"),

	// 取得首页商品列表(特产等)
	EMPTY_COLUMN_COMMODITY_1017(101701, "首页运营商品数据为空"),
	EMPTY_COLUMN_COMMODITY_MODULE_1017(101702, "首页商品数据为空"),

	// 根据id取得商品详情
	COMMODITY_NOT_EXIST_1018(101801, "商品不存在"),

	// 取得首页攻略列表
	EMPTY_COLUMN_GUIDE_1019(101901, "攻略数据为空"),

	/**
	 * 攻略
	 */
	GUIDE_NOT_EXIST_1020(102001, "攻略不存在"),

	/**
	 * poi
	 */
	// 取得宾馆详情
	HOTEL_NOT_EXIST_1022(102201, "宾馆不存在"),

	// 取得目的地详情
	LOCALITY_NOT_EXIST_1024(102401, "目的地不存在"),

	// 取得景点详情
	VIEWSPOT_NOT_EXIST_1026(102601, "景点不存在"),

	// 取得美食详情
	RESTAURANT_NOT_EXIST_1028(102801, "美食不存在"),

	// 取得购物详情
	SHOPPING_NOT_EXIST_1030(103001, "购物不存在"),

	/**
	 * 活动
	 */
	// 发布活动
	UNLOGIN_1031(103101, "用户未登录"),
	USER_NOT_EXIST_1031(103102, "用户不存在"),

	// 取得活动详情
	ACTIVITY_NOT_EXIST_1033(103301, "活动不存在"),

	// 取得用户活动列表
	UNLOGIN_1034(103401, "用户未登录"),

	// 报名活动
	UNLOGIN_1035(103501, "用户未登录"),

	// 取消报名活动
	UNLOGIN_1036(103601, "用户未登录"),

	// 更新活动
	UNLOGIN_1037(103701, "用户未登录"),
	PRICE_NULL_1037(103702, "收费门票价格不可为空"),

	// 添加门票
	UNLOGIN_1038(103801, "用户未登录"),
	PRICE_NULL_1038(103802, "收费门票价格不可为空"),

	// 删除门票
	UNLOGIN_1039(103901, "用户未登录"),
	TICKET_USED_1039(103902, "门票被使用，删除失败"),

	// 修改门票
	UNLOGIN_1040(104001, "用户未登录"),
	TICKET_NOT_EXIST_1040(104002, "门票不存在"),

	// 取得门票详情
	TICKET_NOT_EXIST_1041(104101, "门票不存在"),

	// 取得门票列表
	UNLOGIN_1042(104201, "用户未登录"),

	/**
	 * 游记
	 */
	// 发布游记
	UNLOGIN_1044(104401, "用户未登录"),
	USER_NOT_EXIST_1044(104402, "用户不存在"),

	// 更新游记
	UNLOGIN_1045(104501, "用户未登录"),
	TRAVELNOTE_NOT_EXIST_1045(104502, "游记不存在"),

	// 取得游记详情
	TRAVELNOTE_NOT_EXIST_1046(104601, "游记不存在"),

	// 删除游记
	UNLOGIN_1047(104701, "用户未登录"),

	// 取得用户游记列表
	UNLOGIN_1048(104801, "用户未登录"),

	/**
	 * 时间线
	 */
	// 查看朋友圈
	TIME_NULL_1049(104901, "latestTime和earliestTime不可同时为空"),
	UNLOGIN_1049(104901, "用户未登录"),

	// 查看某个人的朋友圈
	TIME_NULL_1050(105001, "latestTime和earliestTime不可同时为空"),
	UNLOGIN_1050(105002, "用户未登录"),

	// 添加时间线
	ORIGINUSERID_NULL_1051(105101, "originUserId不可为空"),
	ORIGINNICKNAME_NULL_1051(105102, "originNickName不可为空"),
	ORIGINAVATAR_NULL_1051(105103, "OriginAvatar不可为空"),
	UNLOGIN_1051(105104, "用户未登录"),
	USER_NOT_EXIST_1051(105105, "用户不存在"),


	/**
	 * 足迹
	 */
	// 发布足迹
	UNLOGIN_1052(105201, "用户未登录"),
	USER_NOT_EXIST_1052(105202, "用户不存在"),
	ORIGINUSERID_NULL_1052(105203, "源用户id不可为空"),
	ORIGINNICKNAME_NULL_1052(105204, "源用户昵称不可为空"),
	ORIGINAVATAR_NULL_1052(105205, "源用户头像不可为空"),

	// 更新足迹
	UNLOGIN_1053(105301, "用户未登录"),
	TRACE_NOT_EXIST_1053(105302, "足迹不存在"),

	// 删除足迹
	UNLOGIN_1054(105401, "用户未登录"),

	// 取得足迹列表
	UNLOGIN_1055(105501, "用户未登录"),

	// 足迹详情
	TRACE_NOT_EXIST_1056(105601, "足迹不存在"),


	/**
	 * 行程规划
	 */
	// 发布行程规划
	UNLOGIN_1057(105701, "用户未登录"),
	USER_NOT_EXIST_1057(105702, "用户不存在"),
	ORIGINUSERID_NULL_1057(105703, "源用户id不可为空"),
	ORIGINNICKNAME_NULL_1057(105704, "源用户昵称不可为空"),
	ORIGINAVATAR_NULL_1057(105705, "源用户头像不可为空"),

	// 复制行程规划
	UNLOGIN_1058(105801, "用户未登录"),
	TRIPPLAN_NOT_EXIST_1058(105802, "行程规划不存在"),
	USER_NOT_EXIST_1058(105803, "用户不存在"),

	// 取得用户行程规划列表
	UNLOGIN_1059(105901, "用户未登录"),

	// 更新行程规划
	UNLOGIN_1060(106001, "用户未登录"),
	TRIPPLAN_NOT_EXIST_1060(106002, "行程规划不存在"),

	// 取得行程规划详情
	TRIPPLAN_NOT_EXIST_1061(106101, "行程规划不存在"),

	// 删除行程规划
	UNLOGIN_1062(106201, "用户未登录"),

	/**
	 * 问答
	 */
	// 添加问题
	TITLE_NULL_1063(106301, "标题不可为空"),
	CONTENT_NULL_1063(106302, "内容不可为空"),
	UNLOGIN_1063(106303, "用户未登录"),
	USER_NOT_EXIST_1063(106304, "用户不存在"),

	// 取得问题详情
	QUESTION_NOT_EXIST_1064(106401, "问题不存在"),

	// 取得用户问题列表
	UNLOGIN_1065(106501, "用户未登录"),

	// 添加回答
	TITLE_NULL_1067(106701, "标题不可为空"),
	CONTENT_NULL_1067(106702, "内容不可为空"),
	UNLOGIN_1067(106703, "用户未登录"),
	USER_NOT_EXIST_1067(106704, "用户不存在"),

	// 删除问题
	UNLOGIN_1068(106801, "用户未登录"),

	// 删除回答
	UNLOGIN_1069(106901, "用户未登录"),

	// 编辑问题
	UNLOGIN_1070(107001, "用户未登录"),

	// 编辑回答
	UNLOGIN_1071(107101, "用户未登录"),

	/**
	 * 社交
	 */
	// 关注用户
	FOLLOWINGID_NULL_1072(107201, "参数followingId不可为空"),
	UNLOGIN_1072(107202, "用户未登录"),
	USER_NOT_EXIST_1072(107203, "用户不存在"),

	// 取消关注用户
	FOLLOWINGID_NULL_1073(107301, "参数followingId不可为空"),
	UNLOGIN_1073(107302, "用户未登录"),

	// 取得好友列表
	UNLOGIN_1074(107401, "用户未登录"),

	// 取得好友(关注人)信息
	UNLOGIN_1075(107501, "用户未登录"),
	USER_NOT_EXIST_1075(107502, "用户不存在"),

	// 修改备注
	MEMO_NULL_1076(107601, "备注不可为空或者或字符串"),
	UNLOGIN_1076(107602, "用户未登录"),
	USER_NOT_EXIST_1076(107603, "用户不存在"),

	// 添加黑名单
	BLOCKID_NULL_1077(107701, "屏蔽用户id不可为空"),
	UNLOGIN_1077(107702, "用户未登录"),
	USER_NOT_EXIST_1077(107703, "用户不存在"),

	// 移除黑名单
	UNLOGIN_1078(107801, "用户未登录"),
	USER_NOT_EXIST_1078(107802, "用户不存在"),

	// 取得用户关注列表
	UNLOGIN_1079(107901, "用户未登录"),

	// 取得用户粉丝列表
	UNLOGIN_1080(108001, "用户未登录"),

	// 发送消息
	RECEIVERID_NULL_1081(108101, "接收者id不可为空"),
	CONTENT_NULL_1081(108102, "消息内容不可为空"),
	MSGTYPE_NULL_1081(108103, "消息类型不可为空"),
	CHATTYPE_NULL_1081(108104, "聊天类型不可为空"),
	THUMB_NULL_1081(108105, "缩略图不可为空"),
	FULL_NULL_1081(108106, "完整图不可为空"),
	ORIGIN_NULL_1081(108107, "原图不可为空"),
	URL_NULL_1081(108108, "链接不可为空"),
	AUDIO_NULL_1081(108109, "音频不可为空"),
	LENGTH_NULL_1081(108110, "长度不可为空"),
	POSITION_NULL_1081(108111, "位置不可为空"),
	LAT_NULL_1081(108112, "经度不可为空"),
	LNG_NULL_1081(108113, "纬度不可为空"),
	MSGTYPE_INVALID_1081(108114, "消息类型不合法"),
	CHATTYPE_INVALID_1081(108115, "聊天类型不合法"),
	USER_NOT_EXIST_1081(108116, "用户不存在"),
	CHATGROUP_NOT_EXIST_1081(108117, "群组不存在"),
	CONVID_INVALID_1081(108118, "回话id不合法"),
	UNLOGIN_1081(108119, "用户未登录"),
	ID_INVALID_1081(108120, "消息id不合法"),

	// 拉取消息
	UNLOGIN_1082(108201, "用户未登录"),

	// 修改会话属性
	MUTE_NULL_1083(108301, "mute不可为空"),
	UNLOGIN_1083(108302, "用户未登录"),
	CONV_NOT_EXIST_1083(108303, "会话不存在"),

	// 取得会话列表
	IDLIST_NULL_1084(108401, "会话id列表参数不可为空"),
	ID_INVALID_1084(108402, "会话id不合法"),
	UNLOGIN_1084(108403, "用户未登录"),

	/**
	 * 聊天组
	 */
	// 创建聊天组
	UNLOGIN_1085(108501, "用户未登录"),

	// 更新聊天组信息
	UNLOGIN_1086(108601, "用户未登录"),
	CHATGROUP_NOT_EXIST_1086(108602, "群组不存在"),

	// 取得聊天组信息
	UNLOGIN_1087(108701, "用户未登录"),
	CHATGROUP_NOT_EXIST_1087(108702, "群组不存在"),

	// 取得聊天组成员列表
	UNLOGIN_1088(108801, "用户未登录"),
	CHATGROUP_NOT_EXIST_1088(108802, "群组不存在"),
	CREATOR_NOT_EXIST_1088(108803, "创建者不存在"),

	// 添加聊天组成员
	UNLOGIN_1089(108901, "用户未登录"),
	CHATGROUP_NOT_EXIST_1089(108902, "群组不存在"),
	USER_NOT_EXIST_1089(108903, "用户不存在"),

	// 删除聊天组成员
	UNLOGIN_1090(109001, "用户未登录"),

	// 取得用户聊天组列表
	UNLOGIN_1091(109101, "用户未登录"),

	// 发布聊天组帖子
	UNLOGIN_1092(109201, "用户未登录"),
	USER_NOT_EXIST_1092(109202, "用户不存在"),
	CHATGROUP_NOT_EXIST_1092(109203, "群组不存在"),

	// 发布聊天组帖子
	UNLOGIN_1093(109301, "用户未登录"),

	// 更新聊天组帖子
	UNLOGIN_1094(109401, "用户未登录"),
	USER_NOT_EXIST_1094(109402, "用户不存在"),
	CHATGROUP_NOT_EXIST_1094(109403, "群组不存在"),
	POST_NOT_EXIST_1094(109404, "帖子不存在"),

	// 删除聊天组帖子
	UNLOGIN_1095(109501, "用户未登录"),

	// 取得聊天组帖子详情
	UNLOGIN_1096(109601, "用户未登录"),
	POST_NOT_EXIST_1096(109602, "帖子不存在"),

	// 取得用户聊天组帖子列表
	UNLOGIN_1097(109701, "用户未登录"),

	/**
	 * 收藏
	 */
	// 添加收藏
	FAVORITETYPE_NULL_1098(109801, "收藏类型不可为空"),
	ITEMID_NULL_1098(109802, "收藏对象id不可为空"),
	TITLE_NULL_1098(109803, "收藏标题不可为空"),
	UNLOGIN_1098(109804, "用户未登录"),
	FAVORITETYPE_INVALID_1098(109805, "favoriteType不合法"),
	ITEMID_INVALID_1098(109806, "itemId不合法"),

	// 取消收藏
	FAVORITETYPE_NULL_1099(109901, "收藏类型不可为空"),
	FAVORITETYPE_INVALID_1099(109902, "favoriteType不合法"),
	UNLOGIN_1099(109903, "用户未登录"),

	// 取得收藏列表
	UNLOGIN_1100(110001, "用户未登录"),

	/**
	 * 点赞
	 */
	// 点赞
	VOTETYPE_NULL_1101(110101, "点赞类型不可为空"),
	ITEMID_NULL_1101(110102, "点赞对象id不可为空"),
	UNLOGIN_1101(110103, "用户未登录"),
	VOTETYPE_INVALID_1101(110104, "voteType不合法"),
	ITEMID_INVALID_1101(110105, "itemId不合法"),

	// 取消点赞
	VOTETYPE_NULL_1102(110201, "点赞类型不可为空"),
	VOTETYPE_INVALID_1102(110202, "voteType不合法"),
	UNLOGIN_1102(110203, "用户未登录"),

	// 取得点赞列表
	UNLOGIN_1103(110301, "用户未登录"),

	/**
	 * 评论
	 */
	// 添加新评论
	UNLOGIN_1104(110401, "用户未登录"),
	USER_NOT_EXIST_1104(110402, "用户不存在"),
	COMMENTTYPE_INVALID_1104(110403, "评论类型不合法"),

	// 删除评论
	UNLOGIN_1105(110501, "用户未登录"),
	USER_NOT_EXIST_1105(110502, "用户不存在"),

	// 取得评论列表
	ITEMID_NULL_1106(110601, "对象id不可为空"),
	ITEMID_INVALID_1106(110602, "对象id不合法"),

	/**
	 * 搜索
	 */
	// 用户搜索
	QUERY_NULL_1107(110701, "query不可为空"),
	UNLOGIN_1107(110702, "用户未登录"),
	QUERY_INVALID_1107(110703, "query不合法"),
	USER_NOT_EXIST_1107(110704, "用户不存在"),

	// 群组搜索
	QUERY_NULL_1108(110801, "query不可为空"),
	UNLOGIN_1108(110802, "用户未登录"),

	// 全站搜索
	QUERY_NULL_1109(110901, "query不可为空"),

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
