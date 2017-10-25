package com.meihua.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meihua.constants.Constant;
import com.meihua.domain.BaseResult;
import com.meihua.service.VoteActionsService;

/**
 *
 * 
 * @author HanZhiyuan
 */
@RestController
public class VoteActionsController {

	@Autowired
	VoteActionsService voteActionsService;

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名（即登录编号）
	 * @param password
	 *            密码
	 * @return 注销结果
	 */
	@RequestMapping(value = "/vote-actions", method = RequestMethod.POST)
	public BaseResult login(@RequestParam Map<String, String> requestMap) {
		String authToken = requestMap.get("auth_token");
		Integer page = requestMap.get("page") == null || requestMap.get("page").isEmpty() ? 1
				: Integer.valueOf(requestMap.get("page"));
		return voteActionsService.voteActionsExecute(authToken, page, Constant.PAGE_SIZE);
	}
}
