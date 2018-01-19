package com.imooc.vat.serviceImpl;

import com.imooc.vat.dao.MtDutyMapper;
import com.imooc.vat.dao.MtOvertimeMapper;
import com.imooc.vat.entity.MtDuty;
import com.imooc.vat.entity.MtDutyCriteria;
import com.imooc.vat.entity.MtOvertime;
import com.imooc.vat.entity.MtOvertimeCriteria;
import com.imooc.vat.entity.Searcher.OverTimeSearcher;
import com.imooc.vat.entity.Vo.MtDutyVo;
import com.imooc.vat.entity.Vo.MtOverTimeVo;
import com.imooc.vat.entity.Vo.SearchResultVM;
import com.imooc.vat.service.OverTimeService;
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
 * @CreateDate 2017/12/28
 * @Description
 **/
@Service
public class OverTimeServiceImpl implements OverTimeService{

    @Autowired
    private MtOvertimeMapper mtOvertimeMapper;
    @Autowired
    private MtDutyMapper mtDutyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRecord(MtOvertime overtime) {
        Date now=new Date();
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(overtime.getMisno());
        List<MtDuty> dutyList=mtDutyMapper.selectByExample(example);
        if(dutyList.size()==0){
            MtDuty duty=new MtDuty();
            duty.setName(overtime.getName());
            duty.setMisno(overtime.getMisno());
            duty.setOverdays(0.5);
            duty.setVacationdays(0.0);
            duty.setBalancedays(duty.getOverdays()-duty.getVacationdays());
            duty.setCreatetime(now);
            duty.setUpdatetime(now);
            duty.setCreateby("");
            duty.setUpdateby("");
            mtDutyMapper.insertSelective(duty);
        }else {
            MtDuty dutyRecord=new MtDuty();
            dutyRecord.setOverdays(dutyList.get(0).getOverdays()+0.5);
            dutyRecord.setBalancedays(dutyList.get(0).getBalancedays()+0.5);
            dutyRecord.setUpdatetime(now);
            dutyRecord.setUpdateby("");
            mtDutyMapper.updateByExampleSelective(dutyRecord,example);
        }

        overtime.setCreateby("");
        overtime.setCreatetime(now);
        overtime.setUpdateby("");
        overtime.setUpdatetime(now);
        int row=mtOvertimeMapper.insertSelective(overtime);
        return row;
    }

    @Override
    public SearchResultVM<MtOverTimeVo> getOverTimeList(OverTimeSearcher searcher) {
        MtOvertimeCriteria example = new MtOvertimeCriteria();
        MtOvertimeCriteria.Criteria criteria = example.createCriteria();
        criteria.andMisnoEqualTo(searcher.getMisno());
        criteria.andIsdeleteEqualTo(0);
        int total = mtOvertimeMapper.countByExample(example);
        example.setOrderByClause("updateTime desc");
        example.setLimitStart(searcher.getPage() == 1 ? 0 : (searcher.getPage() - 1) * searcher.getRows());
        example.setLimitEnd(searcher.getRows());
        List<MtOvertime> poList = mtOvertimeMapper.selectByExample(example);
        List<MtOverTimeVo> voList = new ArrayList<>();
        poList.forEach(p -> {
            MtOverTimeVo vo = new MtOverTimeVo();
            BeanUtils.copyProperties(p, vo);
            vo.setOccurtime(DateUtil.date2string(p.getOccurtime(),"yyyy-MM-dd"));
            voList.add(vo);
        });

        SearchResultVM<MtOverTimeVo> resultVM = new SearchResultVM<>();
        resultVM.setRows(voList);
        resultVM.setTotal(total);

        return resultVM;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOverTime(Integer id) throws Exception {
        MtOvertime po=mtOvertimeMapper.selectByPrimaryKey(id);
        if(null==po){
            throw new Exception("没有查到该数据，请刷新后重试");
        }

        Date now=new Date();
        MtDutyCriteria example=new MtDutyCriteria();
        MtDutyCriteria.Criteria criteria=example.createCriteria();
        criteria.andMisnoEqualTo(po.getMisno());
        List<MtDuty> dutyList=mtDutyMapper.selectByExample(example);

        MtDuty dutyRecord=new MtDuty();
        dutyRecord.setOverdays(dutyList.get(0).getOverdays()-0.5);
        dutyRecord.setBalancedays(dutyList.get(0).getBalancedays()-0.5);
        dutyRecord.setUpdatetime(now);
        dutyRecord.setUpdateby("");
        mtDutyMapper.updateByExampleSelective(dutyRecord,example);

        return mtOvertimeMapper.deleteByPrimaryKey(id);

    }
}
