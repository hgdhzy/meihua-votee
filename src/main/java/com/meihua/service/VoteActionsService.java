package com.meihua.service;

import com.meihua.domain.BaseResult;

/**
 * 登陆处理接口
 * 
 * @author HanZhiyuan
 */
public interface VoteActionsService {

	/**
	 * 评测活动列表检索
	 * 
	 * @param authToken
	 *            toekn
	 * @return 评测活动列表检索结果
	 */
	BaseResult voteActionsExecute(String authToken, Integer pageNum, Integer pageSize);

}
