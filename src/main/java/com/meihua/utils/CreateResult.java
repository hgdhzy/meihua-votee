package com.meihua.utils;

import com.meihua.domain.ErrorInfo;
import com.meihua.domain.ResultError;

public class CreateResult {

	/**
	 * @param error
	 *            the error to set
	 */
	public static ResultError getErrorResult(String err_cd, String err_msg1, String err_msg2) {
		// 返回记过内error字段对应值作成
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage(err_msg2);
		// 返回结果作成
		ResultError resultError = new ResultError();
		resultError.setMessage(err_msg1);
		resultError.setResult_code(err_cd);
		resultError.setError(errorInfo);
		return resultError;
	}
}
