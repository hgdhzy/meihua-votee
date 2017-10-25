package com.meihua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meihua.constants.Constant;
import com.meihua.constants.MessageConstant;
import com.meihua.domain.BaseResult;
import com.meihua.domain.ResultError;
import com.meihua.domain.ResultSuccess;
import com.meihua.domain.TokenUserInfo;
import com.meihua.domain.VoteActionsDetailsMapper;
import com.meihua.domain.VoteActionsDetailsResult;
import com.meihua.domain.Worksummary;
import com.meihua.domain.WorksummaryExample;
import com.meihua.domain.WorksummaryMapper;
import com.meihua.utils.CreateResult;
import com.meihua.utils.TokenService;

/**
 * 登陆服务
 * 
 * @author HanZhiyuan
 *
 */
@Service
@Transactional(readOnly = true)
public class VoteActionsDetailsServiceImpl implements VoteActionsDetailsService {

	/**
	 * 评测活动列表检索
	 */
	@Autowired
	VoteActionsDetailsMapper voteActionsDetailsMapper;

	/**
	 * 工作总结检索
	 */
	@Autowired
	WorksummaryMapper worksummaryMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseResult voteActionsDetailsExecute(String authToken, String actionId) {
		// 用户信息
		TokenUserInfo tokenUserInfo = null;
		try {
			// 验证Token
			tokenUserInfo = TokenService.validateToken(authToken);
		} catch (Exception e) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_TOKEN_ERR, MessageConstant.ERROR_0004,
					MessageConstant.ERROR_0007);
			return resultError;
		}

		// 用户ID检证
		Integer actionIdInt = null;
		try {
			actionIdInt = Integer.valueOf(actionId);
		} catch (Exception e) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_ACTION_ID_ERR,
					MessageConstant.ERROR_0004, MessageConstant.ERROR_0008);
			return resultError;
		}
		// 活动详情检索
		VoteActionsDetailsResult voteActionsDetailsResult = voteActionsDetailsMapper.selectByPrimaryKey(actionIdInt);
		if (voteActionsDetailsResult == null) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_RES_NOT_EXIST,
					MessageConstant.ERROR_0008, MessageConstant.ERROR_0009);
			return resultError;
		}
		// 工作总结上传状态检索
		WorksummaryExample worksummaryExample = new WorksummaryExample();
		worksummaryExample.createCriteria().andTenantIdEqualTo(tokenUserInfo.getTenantId())
				.andActionIdEqualTo(actionIdInt).andObjectIdEqualTo(tokenUserInfo.getId())
				.andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
		List<Worksummary> WorksummaryList = worksummaryMapper.selectByExampleWithBLOBs(worksummaryExample);
		if (WorksummaryList.isEmpty() || WorksummaryList.get(0).getSummary().isEmpty()) {
			voteActionsDetailsResult.setWork_summary_uploaded(Constant.WORK_SUMMARY_NOT_UPLOADED);
			voteActionsDetailsResult.setWork_summary_content("");
		} else {
			voteActionsDetailsResult.setWork_summary_uploaded(Constant.WORK_SUMMARY_UPLOADED);
			voteActionsDetailsResult.setWork_summary_content(WorksummaryList.get(0).getSummary());
		}
		// 返回结果内result字段对应值作成
		// 返回结果作成
		ResultSuccess<VoteActionsDetailsResult> resultSuccess = new ResultSuccess<VoteActionsDetailsResult>();
		resultSuccess.setResult_code(Constant.RESULT_CODE_SUCCESS);
		resultSuccess.setMessage(MessageConstant.INFO_0003);
		resultSuccess.setResult(voteActionsDetailsResult);
		return resultSuccess;
	}
}
