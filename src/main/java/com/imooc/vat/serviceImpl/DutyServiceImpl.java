package com.imooc.vat.serviceImpl;

import com.imooc.vat.dao.MtDutyMapper;
import com.imooc.vat.dao.MtOvertimeMapper;
import com.imooc.vat.dao.MtVacationMapper;
import com.imooc.vat.entity.*;
import com.imooc.vat.entity.Searcher.DutySearcher;
import com.imooc.vat.entity.Vo.MtDutyVo;
import com.imooc.vat.entity.Vo.SearchResultVM;
import com.imooc.vat.service.DutyService;
import com.imooc.vat.serviceImpl.tool.RunnableExecutorService;
import com.imooc.vat.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private MtDutyMapper dutyMapper;
    @Autowired
    private MtOvertimeMapper mtOvertimeMapper;
    @Autowired
    private MtVacationMapper mtVacationMapper;

    @Resource
    private RunnableExecutorService runnableExecutorService;

    @Override
    public SearchResultVM<MtDutyVo> getDutyList(DutySearcher searcher) {
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andIsdeleteEqualTo(0);
        if(!StringUtils.isEmpty(searcher.getName())){
            criteria.andNameLike("%"+searcher.getName()+"%");
        }
        if(!StringUtils.isEmpty(searcher.getMisno())){
            criteria.andMisnoEqualTo(searcher.getMisno());
        }
        if(!StringUtils.isEmpty(searcher.getStartDate())){
            criteria.andUpdatetimeGreaterThan(DateUtil.string2date(searcher.getStartDate(),"yyyy-MM-dd hh:mm:ss"));
        }
        if(!StringUtils.isEmpty(searcher.getEndDate())){
            criteria.andUpdatetimeLessThan(DateUtil.string2date(searcher.getEndDate(),"yyyy-MM-dd hh:mm:ss"));
        }

        int count=dutyMapper.countByExample(example);

        example.setOrderByClause("updateTime desc");
        example.setLimitStart(searcher.getPage()==1?0:(searcher.getPage() - 1) * searcher.getRows());
        example.setLimitEnd(searcher.getRows());
        List<MtDuty> poList=dutyMapper.selectByExample(example);
        List<MtDutyVo> voList=new ArrayList<>();
        poList.forEach(p->{
            MtDutyVo vo=new MtDutyVo();
            BeanUtils.copyProperties(p,vo);
            voList.add(vo);
        });

        SearchResultVM<MtDutyVo> resultVM=new SearchResultVM<>();
        resultVM.setRows(voList);
        resultVM.setTotal(count);

        return resultVM;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDuty(String misno) {
        MtOvertimeCriteria overExample=new MtOvertimeCriteria();
        MtOvertimeCriteria.Criteria criteriaOver=overExample.createCriteria();
        criteriaOver.andMisnoEqualTo(misno);
        mtOvertimeMapper.deleteByExample(overExample);

        MtVacationCriteria vacationExample=new MtVacationCriteria();
        MtVacationCriteria.Criteria criteriaVacation=vacationExample.createCriteria();
        criteriaVacation.andMisnoEqualTo(misno);
        mtVacationMapper.deleteByExample(vacationExample);

        MtDutyCriteria dutyExample=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=dutyExample.createCriteria();
        criteria.andMisnoEqualTo(misno);
        return dutyMapper.deleteByExample(dutyExample);
    }

    @Override
    public MtDuty getDutyByMisno(String misno) {
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(misno);
        List<MtDuty> dutyList=dutyMapper.selectByExample(example);

        if(dutyList.size()==0){
            return null;
        }

        return dutyList.get(0);
    }
}
