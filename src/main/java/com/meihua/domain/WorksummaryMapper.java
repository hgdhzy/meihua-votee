package com.meihua.domain;

import com.meihua.domain.Worksummary;
import com.meihua.domain.WorksummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorksummaryMapper {
    long countByExample(WorksummaryExample example);

    int deleteByExample(WorksummaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Worksummary record);

    int insertSelective(Worksummary record);

    List<Worksummary> selectByExampleWithBLOBs(WorksummaryExample example);

    List<Worksummary> selectByExample(WorksummaryExample example);

    Worksummary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Worksummary record, @Param("example") WorksummaryExample example);

    int updateByExampleWithBLOBs(@Param("record") Worksummary record, @Param("example") WorksummaryExample example);

    int updateByExample(@Param("record") Worksummary record, @Param("example") WorksummaryExample example);

    int updateByPrimaryKeySelective(Worksummary record);

    int updateByPrimaryKeyWithBLOBs(Worksummary record);

    int updateByPrimaryKey(Worksummary record);
}