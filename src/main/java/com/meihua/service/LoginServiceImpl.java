package com.meihua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.meihua.constants.Constant;
import com.meihua.constants.MessageConstant;
import com.meihua.domain.Dept;
import com.meihua.domain.DeptExample;
import com.meihua.domain.DeptMapper;
import com.meihua.domain.LoginResult;
import com.meihua.domain.ResultError;
import com.meihua.domain.TokenUserInfo;
import com.meihua.domain.User;
import com.meihua.domain.UserExample;
import com.meihua.domain.UserMapper;
import com.meihua.domain.VoteObject;
import com.meihua.domain.VoteObjectExample;
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
public class LoginServiceImpl implements LoginService {

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
	@Transactional(readOnly = true)
	public ResultError checkUserInfo(String tenantId, String code, String password) {

		ResultError resultError = null;
		// 登陆ID，密码检证。
		if (!StringUtils.isEmpty(tenantId) && !StringUtils.isEmpty(code) && !StringUtils.isEmpty(password)) {

			UserExample userExample = new UserExample();
			userExample.or().andCodeEqualTo(code).andTenantIdEqualTo(Integer.valueOf(tenantId));
			List<User> users = userMapper.selectByExample(userExample);

			Integer voteObjectType = null;
			Integer userId = null;
			if (users.size() != 1) {
				DeptExample deptExample = new DeptExample();
				deptExample.or().andCodeEqualTo(code).andTenantIdEqualTo(Integer.valueOf(tenantId));
				List<Dept> depts = deptMapper.selectByExample(deptExample);
				if (depts.size() != 1) {
					// 用户ID不存在
					resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_USER_NOT_EXIST,
							MessageConstant.ERROR_0001, MessageConstant.ERROR_0002);
					return resultError;
				} else {
					voteObjectType = Constant.TYPE_DEPT;
					userId = depts.get(0).getId();
				}

			} else {
				voteObjectType = Constant.TYPE_USER;
				userId = users.get(0).getId();
			}

			// 密码取得及判断
			VoteObjectExample voteObjectExample = new VoteObjectExample();
			voteObjectExample.or().andTenantIdEqualTo(Integer.valueOf(tenantId)).andObjectIdEqualTo(userId)
					.andTypeEqualTo((byte) (int) voteObjectType);
			List<VoteObject> voteObject = voteObjectMapper.selectByExample(voteObjectExample);
			if (voteObject.size() != 1 || !password.equals(voteObject.get(0).getPassword())) {
				// 密码不正确
				resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_PWD_ERR, MessageConstant.ERROR_0001,
						MessageConstant.ERROR_0003);
				return resultError;
			}
		} else if (StringUtils.isEmpty(code)) {
			// 用户ID不存在
			resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_USER_NOT_EXIST, MessageConstant.ERROR_0001,
					MessageConstant.ERROR_0002);
			return resultError;
		} else if (StringUtils.isEmpty(password)) {
			// 密码不正确
			resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_PWD_ERR, MessageConstant.ERROR_0001,
					MessageConstant.ERROR_0003);
			return resultError;
		}
		return resultError;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoginResult getUserInfo(String tenantId, String code) {

		Integer voteObjectType = null;
		Integer userId = null;
		String userName = null;

		UserExample userExample = new UserExample();
		userExample.or().andCodeEqualTo(code).andTenantIdEqualTo(Integer.valueOf(tenantId));
		List<User> users = userMapper.selectByExample(userExample);
		if (users.size() != 1) {
			DeptExample deptExample = new DeptExample();
			deptExample.or().andCodeEqualTo(code).andTenantIdEqualTo(Integer.valueOf(tenantId));
			List<Dept> depts = deptMapper.selectByExample(deptExample);
			voteObjectType = Constant.TYPE_DEPT;
			userId = depts.get(0).getId();
			userName = depts.get(0).getName();
		} else {
			voteObjectType = Constant.TYPE_USER;
			userId = users.get(0).getId();
			userName = users.get(0).getName();
		}

		// Token做成
		TokenUserInfo tokenUserInfo = new TokenUserInfo();
		tokenUserInfo.setId(userId);
		tokenUserInfo.setCode(code);
		tokenUserInfo.setName(userName);
		tokenUserInfo.setTenantId(Integer.valueOf(tenantId));
		tokenUserInfo.setTypeId(voteObjectType);
		Long expired = System.currentTimeMillis() + Constant.TOKEN_EXP_TIME;
		String token = TokenService.createToken(tokenUserInfo, expired);

		LoginResult loginResult = new LoginResult();
		loginResult.setAuth_token(token);
		loginResult.setUser_id(userId.toString());
		loginResult.setUsername(userName);
		return loginResult;
	}
}
