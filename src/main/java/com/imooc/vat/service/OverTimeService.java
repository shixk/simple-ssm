package com.imooc.vat.service;

import com.imooc.vat.entity.MtOvertime;
import com.imooc.vat.entity.Searcher.OverTimeSearcher;
import com.imooc.vat.entity.Vo.MtOverTimeVo;
import com.imooc.vat.entity.Vo.SearchResultVM;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public interface OverTimeService {
    public int insertRecord(MtOvertime overtime);

    public SearchResultVM<MtOverTimeVo> getOverTimeList(OverTimeSearcher searcher);

    public int deleteOverTime(Integer id) throws Exception;
}
