package com.meihua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meihua.constants.Constant;
import com.meihua.constants.MessageConstant;
import com.meihua.domain.BaseResult;
import com.meihua.domain.DeptMapper;
import com.meihua.domain.LogoutResult;
import com.meihua.domain.ResultError;
import com.meihua.domain.ResultSuccess;
import com.meihua.domain.UserMapper;
import com.meihua.domain.VoteObjectMapper;
import com.meihua.utils.CreateResult;
import com.meihua.utils.TokenService;

/**
 * 登陆服务
 * 
 * @author HanZhiyuan
 *
 */
@Service
@Transactional
public class LogoutServiceImpl implements LogoutService {

	/**
	 * 被测评对象信息检索
	 */
	@Autowired
	VoteObjectMapper voteObjectMapper;
	/**
	 * 用户信息检索
	 */
	@Autowired
	UserMapper userMapper;
	/**
	 * 部门信息检索
	 */
	@Autowired
	DeptMapper deptMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseResult logoutExecute(String authToken) {
		LogoutResult logoutResult = null;
		try {
			TokenService.revokeToken(authToken);
			ResultSuccess<LogoutResult> resultSuccess = new ResultSuccess<LogoutResult>();
			resultSuccess.setResult_code(Constant.RESULT_CODE_SUCCESS);
			resultSuccess.setMessage(MessageConstant.INFO_0003);
			logoutResult = new LogoutResult();
			logoutResult.setMessage(MessageConstant.INFO_0001);
			resultSuccess.setResult(logoutResult);
			return resultSuccess;
		} catch (Exception e) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_TOKEN_ERR, MessageConstant.ERROR_0006,
					MessageConstant.ERROR_0007);
			return resultError;
		}
	}
}
