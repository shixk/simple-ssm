package com.imooc.vat.dao;

import com.imooc.vat.entity.MtVacation;
import com.imooc.vat.entity.MtVacationCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MtVacationMapper {
    int countByExample(MtVacationCriteria example);

    int deleteByExample(MtVacationCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(MtVacation record);

    int insertSelective(MtVacation record);

    List<MtVacation> selectByExample(MtVacationCriteria example);

    MtVacation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MtVacation record, @Param("example") MtVacationCriteria example);

    int updateByExample(@Param("record") MtVacation record, @Param("example") MtVacationCriteria example);

    int updateByPrimaryKeySelective(MtVacation record);

    int updateByPrimaryKey(MtVacation record);
}