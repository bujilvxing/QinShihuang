package com.bjlx.QinShihuang.model.quora;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 回答
 * @author xiaozhi
 *
 */
@Entity
public class Answer extends AbstractQuoraEntry {

	@Transient
	public final static String fd_questionId = "questionId";
	@Transient
	public final static String fd_voteCnt = "voteCnt";
	@Transient
	public final static String fd_accepted = "accepted";
	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_status = "status";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id;

	/**
	 * 对应的问题
	 */
	@NotNull
	private ObjectId questionId;

	/**
	 * 被赞的次数
	 */
	@Min(value = 0)
	private Integer voteCnt = 0;

	/**
	 * 该回答是否被采纳
	 */
	private Boolean accepted = false;

	/**
	 * 回答的状态，1表示正常，2表示被删除
	 */
	private Integer status = 1;

	public Answer(){

	}

	public Answer(ObjectId questionId, Integer voteCnt, Boolean accepted) {
		super();
		this.questionId = questionId;
		this.voteCnt = voteCnt;
		this.accepted = accepted;
	}

	public ObjectId getQuestionId() {
		return questionId;
	}

	public void setQuestionId(ObjectId questionId) {
		this.questionId = questionId;
	}

	public Integer getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Integer voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
