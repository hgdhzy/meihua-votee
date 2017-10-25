package com.meihua.domain;

import java.io.Serializable;

/**
 * 、成功返回结果
 * 
 * @author hzy
 */
public class ResultSuccess<T> extends BaseResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private T result;

	public ResultSuccess(T result) {
		super();
		this.result = result;
	}
	
	public ResultSuccess() {
		super();
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}
	
}
