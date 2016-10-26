package com.bjlx.QinShihuang.model.quora;

import com.bjlx.QinShihuang.model.account.UserInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问答数据的基础类
 * @author xiaozhi
 *
 */
public abstract class AbstractQuoraEntry {

	/**
	 * 作者信息
	 */
	@NotNull
	private UserInfo author;
	
	/**
	 * 发表时间戳
	 */
	@NotNull
	private Long publishTime;
	
	/**
	 * 标题
	 */
	@NotBlank
	String title;
	
	/**
	 * 具体描述
	 */
	@NotNull
	String contents;

	public UserInfo getAuthor() {
		return author;
	}

	public void setAuthor(UserInfo author) {
		this.author = author;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
