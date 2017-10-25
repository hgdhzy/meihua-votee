package com.meihua.domain;

import java.util.List;

public interface VoteActionsDetailsMapper {
	long countByExample(VoteActionExample example);

	List<VoteActionsDetailsResult> selectByExampleWithBLOBs(VoteActionExample example);

	List<VoteActionsDetailsResult> selectByExample(VoteActionExample example);

	VoteActionsDetailsResult selectByPrimaryKey(Integer id);
}