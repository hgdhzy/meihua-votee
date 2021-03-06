package com.meihua.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meihua.domain.BaseResult;
import com.meihua.service.LogoutService;

/**
 *
 * 
 * @author HanZhiyuan
 */
@RestController
public class LogoutController {

	@Autowired
	LogoutService logoutService;

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名（即登录编号）
	 * @param password
	 *            密码
	 * @return 注销结果
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public BaseResult login(@RequestParam Map<String, String> requestMap) {
		String authToken = requestMap.get("auth_token");

		// 用户注销
		return logoutService.logoutExecute(authToken);
	}
}
