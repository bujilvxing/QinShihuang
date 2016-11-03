package com.bjlx.QinShihuang.model.quora;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * 问题
 * @author xiaozhi
 *
 */
@Entity
public class Question extends AbstractQuoraEntry {

	public final static String fd_id = "id";
	public final static String fd_source = "source";
	public final static String fd_topics = "topics";
	public final static String fd_tags = "tags";
	public final static String fd_viewCnt = "viewCnt";
	public final static String fd_answerCnt = "answerCnt";
	public final static String fd_maxVoteCnt = "maxVoteCnt";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id;

	/**
	 * 问题的来源
	 * Qyer, Ctrip, Baidu, Mafengwo, Qiongyou等等
	 */
	private String source;
	
	/**
	 * 问题的主题
	 */
	private List<String> topics;
	
	/**
	 * 问题的标签
	 */
	private List<String> tags;
	
	/**
	 * 问题被浏览的次数
	 */
	@Min(value = 0)
	private Integer viewCnt;
	
	/**
	 * 问题被回答的次数
	 */
	@Min(value = 0)
	private Integer answerCnt;
	
	/**
	 * 该问题的所有回答中，被赞的次数最高的数值
	 */
	@Min(value = 0)
	private Integer maxVoteCnt;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Integer getAnswerCnt() {
		return answerCnt;
	}

	public void setAnswerCnt(Integer answerCnt) {
		this.answerCnt = answerCnt;
	}

	public Integer getMaxVoteCnt() {
		return maxVoteCnt;
	}

	public void setMaxVoteCnt(Integer maxVoteCnt) {
		this.maxVoteCnt = maxVoteCnt;
	}

	public Question(String source, List<String> topics, List<String> tags, Integer viewCnt, Integer answerCnt, Integer maxVoteCnt) {
		super();
		this.source = source;
		this.topics = topics;
		this.tags = tags;
		this.viewCnt = viewCnt;
		this.answerCnt = answerCnt;
		this.maxVoteCnt = maxVoteCnt;
	}
}
