package com.imooc.vat.serviceImpl;

import com.imooc.vat.dao.MtDutyMapper;
import com.imooc.vat.dao.MtVacationMapper;
import com.imooc.vat.entity.*;
import com.imooc.vat.entity.Searcher.VacationSearcher;
import com.imooc.vat.entity.Vo.MtOverTimeVo;
import com.imooc.vat.entity.Vo.MtVacationVo;
import com.imooc.vat.entity.Vo.SearchResultVM;
import com.imooc.vat.service.VacationService;
import com.imooc.vat.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/29
 * @Description
 **/
@Service
public class VacationServiceImpl implements VacationService{

    @Autowired
    private MtDutyMapper mtDutyMapper;
    @Autowired
    private MtVacationMapper mtVacationMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertVacationRecord(MtVacation mtVacation) throws Exception {
        Date now=new Date();
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(mtVacation.getMisno());
        List<MtDuty> dutyList=mtDutyMapper.selectByExample(example);
        if(dutyList.size()==0){
            throw new Exception("未找到对应加班信息，请先填加班！");
        }
        if((dutyList.get(0).getBalancedays()-1.0)<0){
            throw new Exception("您的剩余调休条数不足，请先填加班！");
        }

        MtDuty dutyRecord=new MtDuty();
        dutyRecord.setVacationdays(dutyList.get(0).getVacationdays()+1.0);
        dutyRecord.setBalancedays(dutyList.get(0).getBalancedays()-1.0);
        dutyRecord.setUpdatetime(now);
        dutyRecord.setUpdateby("");
        mtDutyMapper.updateByExampleSelective(dutyRecord,example);
        mtVacation.setCreatetime(now);
        mtVacation.setUpdatetime(now);
        int row=mtVacationMapper.insertSelective(mtVacation);
        return row;
    }

    @Override
    public SearchResultVM<MtVacationVo> getVacationList(VacationSearcher searcher) {
        MtVacationCriteria example=new MtVacationCriteria();
        MtVacationCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(searcher.getMisno());
        criteria.andIsdeleteEqualTo(0);
        int total=mtVacationMapper.countByExample(example);
        example.setOrderByClause("updateTime desc");
        example.setLimitStart(searcher.getPage() == 1 ? 0 : (searcher.getPage() - 1) * searcher.getRows());
        example.setLimitEnd(searcher.getRows());
        List<MtVacation> poList = mtVacationMapper.selectByExample(example);
        List<MtVacationVo> voList = new ArrayList<>();
        poList.forEach(p -> {
            MtVacationVo vo = new MtVacationVo();
            BeanUtils.copyProperties(p, vo);
            vo.setOfftime(DateUtil.date2string(p.getOfftime(),"yyyy-MM-dd"));
            voList.add(vo);
        });

        SearchResultVM<MtVacationVo> resultVM = new SearchResultVM<>();
        resultVM.setRows(voList);
        resultVM.setTotal(total);

        return resultVM;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteVacation(Integer id) throws Exception {
        MtVacation po=mtVacationMapper.selectByPrimaryKey(id);
        if(null==po){
            throw new Exception("没有查到该数据，请刷新后重试");
        }
        Date now=new Date();
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(po.getMisno());
        List<MtDuty> dutyList=mtDutyMapper.selectByExample(example);

        MtDuty dutyRecord=new MtDuty();
        dutyRecord.setVacationdays(dutyList.get(0).getVacationdays()-1.0);
        dutyRecord.setBalancedays(dutyList.get(0).getBalancedays()+1.0);
        dutyRecord.setUpdatetime(now);
        dutyRecord.setUpdateby("");
        mtDutyMapper.updateByExampleSelective(dutyRecord,example);

        return mtVacationMapper.deleteByPrimaryKey(id);
    }
}
