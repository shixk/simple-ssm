package com.imooc.vat.service;

import com.imooc.vat.entity.MtDuty;
import com.imooc.vat.entity.Searcher.DutySearcher;
import com.imooc.vat.entity.Vo.MtDutyVo;
import com.imooc.vat.entity.Vo.SearchResultVM;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public interface DutyService {

    public SearchResultVM<MtDutyVo> getDutyList(DutySearcher searcher);

    public int deleteDuty(String misno);

    public MtDuty getDutyByMisno(String misno);

}
