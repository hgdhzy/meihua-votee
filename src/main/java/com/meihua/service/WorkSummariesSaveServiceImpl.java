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
import com.meihua.domain.WorkSummariesSaveResult;
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
@Transactional
public class WorkSummariesSaveServiceImpl implements WorkSummariesSaveService {

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
	public BaseResult saveExecute(String authToken, String actionId, String content) {
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

		// 活动ID检证
		Integer actionIdInt = null;
		try {
			actionIdInt = Integer.valueOf(actionId);
		} catch (Exception e) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_ACTION_ID_ERR,
					MessageConstant.ERROR_0004, MessageConstant.ERROR_0008);
			return resultError;
		}

		if (content == null || content.isEmpty()) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_CONTENT_CHK_ERR,
					MessageConstant.ERROR_0004, MessageConstant.ERROR_0005);
			return resultError;
		}

		// 活动存在性检证
		VoteActionsDetailsResult voteActionsDetailsResult = voteActionsDetailsMapper.selectByPrimaryKey(actionIdInt);
		if (voteActionsDetailsResult == null) {
			// 返回结果作成
			ResultError resultError = CreateResult.getErrorResult(Constant.RESULT_CODE_RES_NOT_EXIST,
					MessageConstant.ERROR_0008, MessageConstant.ERROR_0009);
			return resultError;
		}
		// 工作总结上传
		Worksummary worksummary = new Worksummary();
		worksummary.setActionId(actionIdInt);
		worksummary.setTenantId(tokenUserInfo.getTenantId());
		worksummary.setObjectId(tokenUserInfo.getId());
		worksummary.setType((byte) (int) tokenUserInfo.getTypeId());
		worksummary.setSummary(content);
		// 插入或更新判断
		WorksummaryExample worksummaryExample = new WorksummaryExample();
		worksummaryExample.createCriteria().andTenantIdEqualTo(tokenUserInfo.getTenantId())
				.andActionIdEqualTo(actionIdInt).andObjectIdEqualTo(tokenUserInfo.getId())
				.andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
		List<Worksummary> WorksummaryList = worksummaryMapper.selectByExampleWithBLOBs(worksummaryExample);
		if (WorksummaryList.isEmpty()) {
			worksummaryMapper.insert(worksummary);
		} else {
			worksummary.setId(WorksummaryList.get(0).getId());
			worksummaryMapper.updateByPrimaryKeyWithBLOBs(worksummary);
		}
		// 返回结果作成
		ResultSuccess<WorkSummariesSaveResult> resultSuccess = new ResultSuccess<WorkSummariesSaveResult>();
		resultSuccess.setResult_code(Constant.RESULT_CODE_SUCCESS);
		resultSuccess.setMessage(MessageConstant.INFO_0003);
		WorkSummariesSaveResult workSummariesSaveResult = new WorkSummariesSaveResult();
		workSummariesSaveResult.setMessage(MessageConstant.INFO_0002);
		resultSuccess.setResult(workSummariesSaveResult);
		return resultSuccess;
	}
}
