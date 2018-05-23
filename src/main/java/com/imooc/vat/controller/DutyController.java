package com.imooc.vat.controller;

import com.imooc.vat.entity.MtDuty;
import com.imooc.vat.entity.MtOvertime;
import com.imooc.vat.entity.MtVacation;
import com.imooc.vat.entity.Searcher.DutySearcher;
import com.imooc.vat.entity.Searcher.OverTimeSearcher;
import com.imooc.vat.entity.Searcher.VacationSearcher;
import com.imooc.vat.entity.Vo.MtDutyVo;
import com.imooc.vat.entity.Vo.MtOverTimeVo;
import com.imooc.vat.entity.Vo.MtVacationVo;
import com.imooc.vat.entity.Vo.SearchResultVM;
import com.imooc.vat.service.DutyService;
import com.imooc.vat.service.OverTimeService;
import com.imooc.vat.service.VacationService;
import com.imooc.vat.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
@Controller
@RequestMapping(value = "/duty")
public class DutyController {

    private static final Logger LOG= LoggerFactory.getLogger(DutyController.class);
    @Autowired
    private OverTimeService overTimeService;
    @Autowired
    private DutyService dutyService;
    @Autowired
    private VacationService vacationService;

    @RequestMapping(value="/index")
    public ModelAndView toIndex(HttpServletRequest request){
        return new ModelAndView("/duty/index");
    }

    @RequestMapping(value="/addOverTime")
    public ModelAndView toAddOverTime(HttpServletRequest request){
        return new ModelAndView("/duty/addOverTime");
    }

    @RequestMapping(value="/addVacation")
    public ModelAndView toAddVacation(HttpServletRequest request){
        return new ModelAndView("/duty/addVacation");
    }

    @RequestMapping(value="/OverTimeDetail")
    public ModelAndView toOverTimeDetail(HttpServletRequest request,String misno){
        Map<String, String> model=new HashMap<>();
        model.put("misno",misno);
        return new ModelAndView("/duty/OverTimeDetail",model);
    }


    @RequestMapping(value="/VacationDetail")
    public ModelAndView toVacationDetail(HttpServletRequest request,String misno){
        Map<String, String> model=new HashMap<>();
        model.put("misno",misno);
        return new ModelAndView("/duty/VacationDetail",model);
    }


    @RequestMapping(value="/getDutyList")
    @ResponseBody
    public SearchResultVM<MtDutyVo> getDutyList(DutySearcher searcher){
        SearchResultVM<MtDutyVo> result=new SearchResultVM<>();
        result=dutyService.getDutyList(searcher);
        return result;
    }

    @RequestMapping(value="/getOverTimeList")
    @ResponseBody
    public SearchResultVM<MtOverTimeVo> getOverTimeList(OverTimeSearcher searcher){
        SearchResultVM<MtOverTimeVo> result=new SearchResultVM<>();
        result=overTimeService.getOverTimeList(searcher);
        return result;
    }

    @RequestMapping(value="/getVacationList")
    @ResponseBody
    public SearchResultVM<MtVacationVo> getVacationList(VacationSearcher searcher){
        SearchResultVM<MtVacationVo> result=new SearchResultVM<>();
        result=vacationService.getVacationList(searcher);
        return result;
    }


    @RequestMapping(value="/insertOverRecord")
    @ResponseBody
    public String insertOverRecord(MtOverTimeVo overTimeVo){
        String msg="";
        if(StringUtils.isEmpty(overTimeVo.getName())){
            msg="姓名不能为空";
        }
        if(StringUtils.isEmpty(overTimeVo.getMisno())){
            msg="mis号不能为空";
        }
        if(StringUtils.isEmpty(overTimeVo.getOccurtime())){
            msg="加班时间不能为空";
        }
        if(msg!=""){
            return msg;
        }
        MtOvertime overtime=new MtOvertime();
        BeanUtils.copyProperties(overTimeVo,overtime);
        overtime.setOccurtime(DateUtil.string2date(overTimeVo.getOccurtime(),"yyyy-MM-dd hh:mm:ss"));
        overTimeService.insertRecord(overtime);
        msg="success";
        DxNotify(overTimeVo.getMisno());
        return msg;
    }

    @RequestMapping(value="/insertVacationRecord")
    @ResponseBody
    public String insertVacationRecord(MtVacationVo mtVacationVo){
        String msg="";
        if(StringUtils.isEmpty(mtVacationVo.getName())){
            msg="姓名不能为空";
        }
        if(StringUtils.isEmpty(mtVacationVo.getMisno())){
            msg="mis号不能为空";
        }
        if(StringUtils.isEmpty(mtVacationVo.getOfftime())){
            msg="调休时间不能为空";
        }
        if(msg!=""){
            return msg;
        }
        MtVacation entity=new MtVacation();
        BeanUtils.copyProperties(mtVacationVo,entity);
        entity.setOfftime(DateUtil.string2date(mtVacationVo.getOfftime(),"yyyy-MM-dd hh:mm:ss"));
        try{
            vacationService.insertVacationRecord(entity);
            msg="success";
            DxNotify(entity.getMisno());
        }catch (Exception e){
            LOG.info("填写调休异常："+e.getMessage(),e);
            msg=e.getMessage();
        }

        return msg;
    }


    @RequestMapping(value="/deleteDuty")
    @ResponseBody
    public String deleteDuty(String misno){
        String msg="";
        dutyService.deleteDuty(misno);
        msg="success";
        return msg;
    }

    @RequestMapping(value="/deleteOverTime")
    @ResponseBody
    public String deleteOverTime(Integer id){
        String msg="";
        try {
            overTimeService.deleteOverTime(id);
            msg="success";
        } catch (Exception e) {
            LOG.info("删除加班异常："+e.getMessage());
            msg=e.getMessage();
        }
        return msg;
    }

    @RequestMapping(value="/deleteVacation")
    @ResponseBody
    public String deleteVacation(Integer id){
        String msg="";
        try {
            vacationService.deleteVacation(id);
            msg="success";
        } catch (Exception e) {
            LOG.info("删除调休异常："+e.getMessage());
            msg=e.getMessage();
        }
        return msg;
    }

    private void DxNotify(String misno){
        try{
            MtDuty duty=dutyService.getDutyByMisno(misno);
            String message="【加班统计】"+"您的调休发生变动，\r\n加班总数为："+duty.getOverdays()+"天\r\n"+"调休总数："+duty.getVacationdays()+"天\r\n剩余调休为"+duty.getBalancedays()+"天，祝您工作愉快！";
            System.out.println(message);
        }catch (Exception ex){
            LOG.error("发送大象异常：", ex);
        }
    }

}
