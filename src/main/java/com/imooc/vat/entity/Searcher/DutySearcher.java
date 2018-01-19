package com.imooc.vat.entity.Searcher;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public class DutySearcher extends BaseSearcherVM{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMisno() {
        return misno;
    }

    public void setMisno(String misno) {
        this.misno = misno;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    private String name;
    private String misno;
    private String endDate;
    private String startDate;


}
