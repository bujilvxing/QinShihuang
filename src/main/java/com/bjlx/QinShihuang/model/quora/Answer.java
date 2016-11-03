package com.bjlx.QinShihuang.model.quora;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 回答
 * @author xiaozhi
 *
 */
public class Answer extends AbstractQuoraEntry {

	public final static String fd_question = "question";
	public final static String fd_voteCnt = "voteCnt";
	public final static String fd_accepted = "accepted";

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
}
