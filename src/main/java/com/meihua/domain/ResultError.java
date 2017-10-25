package com.meihua.domain;

import java.io.Serializable;

/**
 * 、失败返回结果
 * 
 * @author hzy
 */
public class ResultError extends BaseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ErrorInfo error;

	/**
	 * @return the error
	 */
	public ErrorInfo getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(ErrorInfo error) {
		this.error = error;
	}

}
