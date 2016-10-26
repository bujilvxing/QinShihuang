package com.bjlx.QinShihuang.exception;

import com.bjlx.QinShihuang.utils.ErrorCode;

/**
 * 不羁旅行基础异常
 * @author xiaozhi
 *
 */
public class BjlxException extends Exception {

	/**
	 * 错误码
	 */
	private ErrorCode errorCode;
	
	/**
	 * 构造函数
	 * @param errorCode 错误码
	 */
	public BjlxException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
