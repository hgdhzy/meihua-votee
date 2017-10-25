package com.meihua.domain;

import java.io.Serializable;

/**
 * 登陆结果
 * 
 * @author hzy
 */
public class LoginResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String auth_token;
	private String user_id;
	private String username;
	/**
	 * @return the auth_token
	 */
	public String getAuth_token() {
		return auth_token;
	}
	/**
	 * @param auth_token the auth_token to set
	 */
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
