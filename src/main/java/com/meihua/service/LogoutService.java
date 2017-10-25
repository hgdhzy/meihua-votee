package com.meihua.service;

import com.meihua.domain.BaseResult;

/**
 * 登陆处理接口
 * 
 * @author HanZhiyuan
 */
public interface LogoutService {

    /**
     * 用户注销。
     * 
     * @param authToken toekn
     * @return 注销结果
     */
	BaseResult logoutExecute(String authToken);

}
