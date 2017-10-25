package com.meihua.service;

import com.meihua.domain.BaseResult;

/**
 * 登陆处理接口
 * 
 * @author HanZhiyuan
 */
public interface WorkSummariesSaveService {

	/**
	 * 评测活动列表检索
	 * 
	 * @param authToken
	 *            toekn
	 * @return 评测活动列表检索结果
	 */
	BaseResult saveExecute(String authToken, String actionId, String content);

}
