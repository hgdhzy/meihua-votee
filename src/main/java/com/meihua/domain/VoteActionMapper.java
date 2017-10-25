package com.meihua.domain;

import java.util.List;

public interface VoteActionMapper {
	long countByExample(VoteActionExample example);

	List<VoteActions> selectByExampleWithBLOBs(VoteActionExample example);

	List<VoteActions> selectByExample(VoteActionExample example);

	VoteActions selectByPrimaryKey(Integer id);
}