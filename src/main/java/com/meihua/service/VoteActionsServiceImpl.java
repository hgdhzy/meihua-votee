package com.meihua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meihua.constants.Constant;
import com.meihua.constants.MessageConstant;
import com.meihua.domain.BaseResult;
import com.meihua.domain.ResultError;
import com.meihua.domain.ResultSuccess;
import com.meihua.domain.TokenUserInfo;
import com.meihua.domain.VoteActionExample;
import com.meihua.domain.VoteActionMapper;
import com.meihua.domain.VoteActions;
import com.meihua.domain.VoteActionsResult;
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
public class VoteActionsServiceImpl implements VoteActionsService {

	/**
	 * 评测活动列表检索
	 */
	@Autowired
	VoteActionMapper voteActionMapper;

	/**
	 * 工作总结检索
	 */
	@Autowired
	WorksummaryMapper worksummaryMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseResult voteActionsExecute(String authToken, Integer pageNum, Integer pageSize) {
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
		// 查询条件作成
		VoteActionExample voteActionExample = new VoteActionExample();
		voteActionExample.or().andTenantIdEqualTo(tokenUserInfo.getTenantId());
		voteActionExample.setOrderByClause("vote_action_id");
		// 分页查询实行
		PageHelper.startPage(pageNum, pageSize);
		List<VoteActions> voteActionList = voteActionMapper.selectByExample(voteActionExample);
		// 分页查询结果对象作成
		PageInfo<VoteActions> pageInfo = new PageInfo<VoteActions>(voteActionList);
		// 总页数
		Integer pages = pageInfo.getPages();
		// 工作总结上传状态检索
		for (VoteActions voteAction : voteActionList) {
			WorksummaryExample worksummaryExample = new WorksummaryExample();
			worksummaryExample.createCriteria().andTenantIdEqualTo(tokenUserInfo.getTenantId())
					.andActionIdEqualTo(voteAction.getVote_action_id()).andObjectIdEqualTo(tokenUserInfo.getId())
					.andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
			List<Worksummary> WorksummaryList = worksummaryMapper.selectByExampleWithBLOBs(worksummaryExample);
			if (WorksummaryList.isEmpty() || WorksummaryList.get(0).getSummary().isEmpty()) {
				voteAction.setWork_summary_uploaded(Constant.WORK_SUMMARY_NOT_UPLOADED);
			} else {
				voteAction.setWork_summary_uploaded(Constant.WORK_SUMMARY_UPLOADED);
			}
		}

		// 返回结果内result字段对应值作成
		VoteActionsResult voteActionsResult = new VoteActionsResult();
		voteActionsResult.setPages(pages);
		voteActionsResult.setVote_actions(voteActionList);
		// 返回结果作成
		ResultSuccess<VoteActionsResult> resultSuccess = new ResultSuccess<VoteActionsResult>();
		resultSuccess.setResult_code(Constant.RESULT_CODE_SUCCESS);
		resultSuccess.setMessage(MessageConstant.INFO_0003);
		resultSuccess.setResult(voteActionsResult);
		return resultSuccess;
	}
}
