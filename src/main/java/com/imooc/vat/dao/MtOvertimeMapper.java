package com.imooc.vat.dao;

import com.imooc.vat.entity.MtOvertime;
import com.imooc.vat.entity.MtOvertimeCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MtOvertimeMapper {
    int countByExample(MtOvertimeCriteria example);

    int deleteByExample(MtOvertimeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(MtOvertime record);

    int insertSelective(MtOvertime record);

    List<MtOvertime> selectByExample(MtOvertimeCriteria example);

    MtOvertime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MtOvertime record, @Param("example") MtOvertimeCriteria example);

    int updateByExample(@Param("record") MtOvertime record, @Param("example") MtOvertimeCriteria example);

    int updateByPrimaryKeySelective(MtOvertime record);

    int updateByPrimaryKey(MtOvertime record);
}