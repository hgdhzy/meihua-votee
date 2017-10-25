package com.meihua.service;

import com.meihua.domain.LoginResult;
import com.meihua.domain.ResultError;

/**
 * 登陆处理接口
 * 
 * @author HanZhiyuan
 */
public interface LoginService {

    /**
     * 登陆信息检证。
     * 
     * @param tenantId 租户id
     * @param username 登陆id
     * @param password 密码
     * @return 错误信息
     */
	ResultError checkUserInfo(String tenantId, String username, String password);
	
	/**
	 * 用户信息检索
	 * 
     * @param tenantId 租户id
	 * @param username 登陆id
	 * @return 登陆处理结果
	 */
	LoginResult getUserInfo(String tenantId, String username);

}
