package com.meihua.constants;

/**
 * 共同常量定义
 * 
 * @author HanZhiyuan
 *
 */
public class Constant {

	/**
	 * 0 成功
	 */
	public static final String RESULT_CODE_SUCCESS = "0";

	/**
	 * 10001 用户名不存在
	 */
	public static final String RESULT_CODE_USER_NOT_EXIST = "10001";

	/**
	 * 10002 密码不正确
	 */
	public static final String RESULT_CODE_PWD_ERR = "10002";

	/**
	 * 20001 非法请求
	 */
	public static final String RESULT_TOKEN_ERR = "20001";

	/**
	 * 30001 指定资源不存在
	 */
	public static final String RESULT_CODE_RES_NOT_EXIST = "30001";

	/**
	 * 30002 活动ID非法
	 */
	public static final String RESULT_CODE_ACTION_ID_ERR = "30002";

	/**
	 * 40001 校验失败
	 */
	public static final String RESULT_CONTENT_CHK_ERR = "40001";

	/**
	 * 50001 系统级别异常
	 */
	public static final String RESULT_CODE_SYS_ERR = "50001";

	/**
	 * 3600 Token有效时长(Millisecond)
	 */
	public static final Long TOKEN_EXP_TIME = (long) 3600000;

	/**
	 * 1 用户
	 */
	public static final Integer TYPE_USER = 1;

	/**
	 * 2 部门
	 */
	public static final Integer TYPE_DEPT = 2;

	/**
	 * MeihuaVoteSystem 密钥
	 */
	public static final String SECRET = "MeihuaVoteSystem";

	/**
	 * 10 每页件数
	 */
	public static final Integer PAGE_SIZE = 2;

	/**
	 * 1 工作总结未上传
	 */
	public static final Integer WORK_SUMMARY_NOT_UPLOADED = 0;

	/**
	 * 1 工作总结已上传
	 */
	public static final Integer WORK_SUMMARY_UPLOADED = 1;
}
