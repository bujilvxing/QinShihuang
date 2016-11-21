package com.bjlx.QinShihuang.model.activity;

import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pengyt on 2016/7/21.
 * 活动
 */
@Entity
public class Activity {

	@Transient
    public final static String fd_id = "id";
	@Transient
    public final static String fd_title = "title";
	@Transient
    public final static String fd_maxNum = "maxNum";
	@Transient
    public final static String fd_joinNum = "joinNum";
	@Transient
    public final static String fd_startTime = "startTime";
	@Transient
    public final static String fd_endTime = "endTime";
	@Transient
    public final static String fd_address = "address";
	@Transient
    public final static String fd_favorCnt = "favorCnt";
	@Transient
    public final static String fd_commentCnt = "commentCnt";
	@Transient
    public final static String fd_viewCnt = "viewCnt";
	@Transient
    public final static String fd_shareCnt = "shareCnt";
	@Transient
    public final static String fd_cover = "cover";
	@Transient
    public final static String fd_posters = "posters";
	@Transient
    public final static String fd_theme = "theme";
	@Transient
    public final static String fd_category = "category";
	@Transient
    public final static String fd_tags = "tags";
	@Transient
    public final static String fd_visiable = "visiable";
	@Transient
    public final static String fd_desc = "desc";
	@Transient
    public final static String fd_applicantInfos = "applicantInfos";
	@Transient
    public final static String fd_tickets = "tickets";
	@Transient
    public final static String fd_isFree = "isFree";
    @Transient
    public final static String fd_voteCnt = "voteCnt";
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 活动名称(标题)
     */
    @NotNull
    private String title;

    /**
     * 最大允许人数
     */
    private Integer maxNum;

    /**
     * 报名人数
     */
    private Integer joinNum;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 活动地点
     */
    private Address address;

    /**
     * 收藏次数
     */
    @Min(value = 0)
    private Integer favorCnt = 0;

    /**
     * 点赞数
     */
    @Min(value = 0)
    private Integer voteCnt = 0;

    /**
     * 评论次数
     */
    @Min(value = 0)
    private Integer commentCnt = 0;

    /**
     * 浏览次数
     */
    @Min(value = 0)
    private Integer viewCnt = 0;

    /**
     * 分享次数
     */
    @Min(value = 0)
    private Integer shareCnt = 0;

    /**
     * 封面图
     */
    private ImageItem cover;
    
    /**
     * 海报
     */
    private List<ImageItem> posters;

    /**
     * 活动主题
     */
    private String theme;

    /**
     * 活动分类
     */
    private String category;

    /**
     * 活动标签
     */
    private List<String> tags;

    /**
     * 活动是否为隐私活动，1表示不可见，2表示可见
     */
    private Integer visiable = 1;

    /**
     * 活动详情
     */
    private String desc;

    /**
     * 报名人信息
     */
    private List<Contact> applicantInfos;

    /**
     * 门票
     */
    private List<Ticket> tickets;
    
    /**
     * 是否免费
     */
    private Boolean isFree = true;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getFavorCnt() {
        return favorCnt;
    }

    public void setFavorCnt(Integer favorCnt) {
        this.favorCnt = favorCnt;
    }

    public Integer getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(Integer commentCnt) {
        this.commentCnt = commentCnt;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Integer getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(Integer shareCnt) {
        this.shareCnt = shareCnt;
    }

    public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public List<ImageItem> getPosters() {
        return posters;
    }

    public void setPosters(List<ImageItem> posters) {
        this.posters = posters;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getVisiable() {
        return visiable;
    }

    public void setVisiable(Integer visiable) {
        this.visiable = visiable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Contact> getApplicantInfos() {
        return applicantInfos;
    }

    public void setApplicantInfos(List<Contact> applicantInfos) {
        this.applicantInfos = applicantInfos;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

    public Integer getVoteCnt() {
        return voteCnt;
    }

    public void setVoteCnt(Integer voteCnt) {
        this.voteCnt = voteCnt;
    }

    public Boolean isFree() {
        return isFree;
    }

    public Activity(String title, Integer maxNum, Long startTime, Long endTime, Address address, String theme, String category) {
        this.id = new ObjectId();
        this.title = title;
        this.maxNum = maxNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.theme = theme;
        this.category = category;
    }
}
