package com.bjlx.QinShihuang.model.account;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户数据
 * @author xiaozhi
 *
 */
@Entity
public class UserInfo {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_number = "tel.number";
	@Transient
	public final static String fd_tel = "tel";
	@Transient
	public final static String fd_email = "email";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_nickName = "nickName";
	@Transient
	public final static String fd_avatar = "avatar";
	@Transient
	public final static String fd_gender = "gender";
	@Transient
	public final static String fd_signature = "signature";
	@Transient
	public final static String fd_travellers = "travellers";
	@Transient
	public final static String fd_promotionCode = "promotionCode";
	@Transient
	public final static String fd_backGround = "backGround";
	@Transient
	public final static String fd_updateTime = "updateTime";
	@Transient
	public final static String fd_createTime = "createTime";
	@Transient
	public final static String fd_soundNotify = "soundNotify";
	@Transient
	public final static String fd_vibrateNotify = "vibrateNotify";
	@Transient
	public final static String fd_zodiac = "zodiac";
	@Transient
	public final static String fd_loginStatus = "loginStatus";
	@Transient
	public final static String fd_loginTime = "loginTime";
	@Transient
	public final static String fd_logoutTime = "logoutTime";
	@Transient
	public final static String fd_loginSource = "loginSource";
	@Transient
	public final static String fd_version = "version";
	@Transient
	public final static String fd_roles = "roles";
	@Transient
	public final static String fd_memo = "memo";
	@Transient
	public final static String fd_residence = "residence";
	@Transient
	public final static String fd_birthday = "birthday";
	@Transient
	public final static String fd_weixin = "weixin";
	@Transient
	public final static String fd_weixin_provider = "weixin.provider";
	@Transient
	public final static String fd_weixin_oauthId = "weixin.oauthId";
	@Transient
	public final static String fd_sina = "sina";
	@Transient
	public final static String fd_sina_provider = "sina.provider";
	@Transient
	public final static String fd_sina_oauthId = "sina.oauthId";
	@Transient
	public final static String fd_qq = "qq";
	@Transient
	public final static String fd_qq_provider = "qq.provider";
	@Transient
	public final static String fd_qq_oauthId = "qq.oauthId";
	@Transient
	public final static String fd_level = "level";
	@Transient
	public final static String fd_travelNotes = "travelNotes";
	@Transient
	public final static String fd_traces = "traces";
	@Transient
	public final static String fd_tripPlans = "tripPlans";
	@Transient
	public final static String fd_activities = "activities";
	@Transient
	public final static String fd_status = "status";
	@Transient
	public final static String fd_clientId = "clientId";
	@Transient
	public final static String fd_key = "key";

	/**
	 * 授权码
	 */
	@Transient
	private String key;

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 用户ID
	 */
	@NotNull
	@Min(value = 1)
	private Long userId;

	/**
	 * 用户的电话号码
	 */
	private PhoneNumber tel;

	/**
	 * 昵称
	 */
	@NotBlank
	@Length(min = 1, max = 64)
	private String nickName;

	/**
	 * 头像
	 */
	private ImageItem avatar;

	/**
	 * 性别
	 * 1表示未选择，2表示男，3表示女
	 */
	private Integer gender = 1;

	/**
	 * 用户签名
	 */
	@Length(max = 1024)
	private String signature;

	/**
	 * 电子邮件
	 */
	@Length(max = 50)
	@Email
	private String email;
	
	/**
	 * 旅客信息
	 */
	private List<RealNameInfo> travellers;

	/**
	 * 用户的状态
	 * 1、表示正常 2、表示已注销
	 */
	private Integer status = 1;

	/**
	 * 邀请码。每个人有全局唯一的一个邀请码，用于分享平台内容时奖励
	 */
	@NotBlank
	@Indexed(options = @IndexOptions(unique = true))
	private String promotionCode;

	/**
	 * 登录的状态，是否在线
	 */
	private Boolean loginStatus = false;

	/**
	 * 登录时间
	 */
	private Long loginTime = 0L;

	/**
	 * 登出时间
	 */
	private Long logoutTime = 0L;

	/**
	 * 登录设备来源
	 */
	private List<String> loginSource = null;

	/**
	 * 设备版本
	 */
	private Long version = 0L;

	/**
	 * 用户角色。普通用户，商家等等
	 */
	private List<Integer> roles = null;

	/**
	 * 用户备注。此字段为Transient，是不存入数据库的，但是取用户数据的时候，可以将给此字段赋值，为了返回用户信息的
	 */
	@Transient
	private String memo = null;

	/**
	 * 用户的居住地
	 */
	private String residence = null;

	/**
	 * 用户的生日
	 */
	private Long birthday  = null;

	/**
	 * 第三方账号微信的信息
	 */
	private OAuthInfo weixin = null;

	/**
	 * 第三方账号微博的信息
	 */
	private OAuthInfo sina = null;

	/**
	 * 第三方账号qq的信息
	 */
	private OAuthInfo qq = null;

	/**
	 * 用户等级
	 */
	private Integer level = 1;

	/**
	 * 用户足迹
	 */
	private List<Trace> traces;

	/**
	 * 用户发布的活动
	 */
	private List<Activity> activities;

	/**
	 * 用户发表的游记
	 */
	private List<TravelNote> travelNotes;

	/**
	 * 用户发布的行程规划
	 */
	private List<TripPlan> tripPlans;

	/**
	 * 星座。1 水瓶 2 双鱼 3 白羊 4 金牛 5 双子 6 巨蟹 7 狮子 8 处女 9 天秤 10 天蝎 11 射手 12 魔杰
	 */
	private Integer zodiac;

	/**
	 * 设置声音提醒
	 */
	private Boolean soundNotify = true;

	/**
	 * 设置振动提醒
	 */
	private Boolean vibrateNotify = true;

	/**
	 * 背景图片
	 */
	private ImageItem backGround;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 个推的clientId
	 */
	private String clientId;
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Trace> getTraces() {
		return traces;
	}

	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<TravelNote> getTravelNotes() {
		return travelNotes;
	}

	public void setTravelNotes(List<TravelNote> travelNotes) {
		this.travelNotes = travelNotes;
	}

	public List<TripPlan> getTripPlans() {
		return tripPlans;
	}

	public void setTripPlans(List<TripPlan> tripPlans) {
		this.tripPlans = tripPlans;
	}

	public Integer getZodiac() {
		return zodiac;
	}

	public void setZodiac(Integer zodiac) {
		this.zodiac = zodiac;
	}

	public Boolean getSoundNotify() {
		return soundNotify;
	}

	public void setSoundNotify(Boolean soundNotify) {
		this.soundNotify = soundNotify;
	}

	public Boolean getVibrateNotify() {
		return vibrateNotify;
	}

	public void setVibrateNotify(Boolean vibrateNotify) {
		this.vibrateNotify = vibrateNotify;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public PhoneNumber getTel() {
		return tel;
	}

	public void setTel(PhoneNumber tel) {
		this.tel = tel;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ImageItem getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageItem avatar) {
		this.avatar = avatar;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RealNameInfo> getTravellers() {
		return travellers;
	}

	public void setTravellers(List<RealNameInfo> travellers) {
		this.travellers = travellers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Long logoutTime) {
		this.logoutTime = logoutTime;
	}

	public List<String> getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(List<String> loginSource) {
		this.loginSource = loginSource;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public Boolean isLoginStatus() {
		return loginStatus;
	}

	public OAuthInfo getWeixin() {
		return weixin;
	}

	public void setWeixin(OAuthInfo weixin) {
		this.weixin = weixin;
	}

	public OAuthInfo getSina() {
		return sina;
	}

	public void setSina(OAuthInfo sina) {
		this.sina = sina;
	}

	public OAuthInfo getQq() {
		return qq;
	}

	public void setQq(OAuthInfo qq) {
		this.qq = qq;
	}

	public Boolean isSoundNotify() {
		return soundNotify;
	}

	public Boolean isVibrateNotify() {
		return vibrateNotify;
	}

	public ImageItem getBackGround() {
		return backGround;
	}

	public void setBackGround(ImageItem backGround) {
		this.backGround = backGround;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public UserInfo() {
		this.id = new ObjectId();
	}
	
	public UserInfo(Long userId, PhoneNumber tel, String nickName, ImageItem avatar, ImageItem backGround, String promotionCode) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.tel = tel;
		this.nickName = nickName;
		this.avatar = avatar;
		this.backGround = backGround;
		this.promotionCode = promotionCode;
		Long currentTime = System.currentTimeMillis();
		this.createTime = currentTime;
		this.updateTime = currentTime;
	}
	
	public UserInfo(Long userId, String email, String nickName, ImageItem avatar, ImageItem backGround, String promotionCode) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.nickName = nickName;
		this.avatar = avatar;
		this.backGround = backGround;
		this.email = email;
		this.promotionCode = promotionCode;
		Long currentTime = System.currentTimeMillis();
		this.createTime = currentTime;
		this.updateTime = currentTime;
	}

	public UserInfo(Long userId, String nickName, ImageItem avatar, ImageItem backGround, String promotionCode) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.nickName = nickName;
		this.avatar = avatar;
		this.backGround = backGround;
		this.promotionCode = promotionCode;
		Long currentTime = System.currentTimeMillis();
		this.createTime = currentTime;
		this.updateTime = currentTime;
	}
}
