package com.meihua.domain;

import com.meihua.domain.VoteObject;
import com.meihua.domain.VoteObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteObjectMapper {
    long countByExample(VoteObjectExample example);

    int deleteByExample(VoteObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteObject record);

    int insertSelective(VoteObject record);

    List<VoteObject> selectByExampleWithBLOBs(VoteObjectExample example);

    List<VoteObject> selectByExample(VoteObjectExample example);

    VoteObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteObject record, @Param("example") VoteObjectExample example);

    int updateByExampleWithBLOBs(@Param("record") VoteObject record, @Param("example") VoteObjectExample example);

    int updateByExample(@Param("record") VoteObject record, @Param("example") VoteObjectExample example);

    int updateByPrimaryKeySelective(VoteObject record);

    int updateByPrimaryKeyWithBLOBs(VoteObject record);

    int updateByPrimaryKey(VoteObject record);
}