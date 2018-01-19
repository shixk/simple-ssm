package com.imooc.vat.service;

import com.imooc.vat.entity.MtVacation;
import com.imooc.vat.entity.Searcher.VacationSearcher;
import com.imooc.vat.entity.Vo.MtVacationVo;
import com.imooc.vat.entity.Vo.SearchResultVM;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/29
 * @Description
 **/
public interface VacationService {
    public int insertVacationRecord(MtVacation mtVacation) throws Exception;

    public SearchResultVM<MtVacationVo> getVacationList(VacationSearcher searcher);

    public int deleteVacation(Integer id) throws Exception;

}
