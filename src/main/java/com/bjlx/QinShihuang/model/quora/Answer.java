package com.bjlx.QinShihuang.model.quora;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

/**
 * 回答
 * @author xiaozhi
 *
 */
@Entity
public class Answer extends AbstractQuoraEntry {

	@Transient
	public final static String fd_question = "question";
	@Transient
	public final static String fd_voteCnt = "voteCnt";
	@Transient
	public final static String fd_accepted = "accepted";
	@Transient
	public final static String fd_id = "id";

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
	private Question question;

	/**
	 * 被赞的次数
	 */
	@Min(value = 0)
	private Integer voteCnt = 0;

	/**
	 * 该回答是否被采纳
	 */
	Boolean accepted = false;

	public Answer(Question question, Integer voteCnt, Boolean accepted) {
		super();
		this.question = question;
		this.voteCnt = voteCnt;
		this.accepted = accepted;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Integer voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Boolean getAccepted() {
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
}
