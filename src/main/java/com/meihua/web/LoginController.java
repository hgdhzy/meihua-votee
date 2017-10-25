package com.meihua.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meihua.constants.Constant;
import com.meihua.constants.MessageConstant;
import com.meihua.domain.BaseResult;
import com.meihua.domain.LoginResult;
import com.meihua.domain.ResultSuccess;
import com.meihua.service.LoginService;

/**
 *
 * 
 * @author HanZhiyuan
 */
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名（即登录编号）
	 * @param password
	 *            密码
	 * @return 登陆结果
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResult login(@RequestParam Map<String, String> requestMap) {
		String tenantId = requestMap.get("tenantid");
		String code = requestMap.get("username");

		LoginResult userInfo = loginService.getUserInfo(tenantId, code);
		ResultSuccess<LoginResult> resultSuccess = new ResultSuccess<LoginResult>();
		resultSuccess.setResult_code(Constant.RESULT_CODE_SUCCESS);
		resultSuccess.setMessage(MessageConstant.INFO_0003);
		resultSuccess.setResult(userInfo);
		return resultSuccess;
	}
}
