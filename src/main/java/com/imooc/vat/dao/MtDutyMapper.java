package com.imooc.vat.dao;

import com.imooc.vat.entity.MtDuty;
import com.imooc.vat.entity.MtDutyCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MtDutyMapper {
    int countByExample(MtDutyCriteria example);

    int deleteByExample(MtDutyCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(MtDuty record);

    int insertSelective(MtDuty record);

    List<MtDuty> selectByExample(MtDutyCriteria example);

    MtDuty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MtDuty record, @Param("example") MtDutyCriteria example);

    int updateByExample(@Param("record") MtDuty record, @Param("example") MtDutyCriteria example);

    int updateByPrimaryKeySelective(MtDuty record);

    int updateByPrimaryKey(MtDuty record);
}