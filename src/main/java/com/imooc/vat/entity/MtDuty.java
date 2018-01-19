package com.imooc.vat.entity;

import java.util.Date;

public class MtDuty {
    private Integer id;

    private String name;

    private String misno;

    private Double overdays;

    private Double vacationdays;

    private Double balancedays;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    private Integer isdelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMisno() {
        return misno;
    }

    public void setMisno(String misno) {
        this.misno = misno == null ? null : misno.trim();
    }

    public Double getOverdays() {
        return overdays;
    }

    public void setOverdays(Double overdays) {
        this.overdays = overdays;
    }

    public Double getVacationdays() {
        return vacationdays;
    }

    public void setVacationdays(Double vacationdays) {
        this.vacationdays = vacationdays;
    }

    public Double getBalancedays() {
        return balancedays;
    }

    public void setBalancedays(Double balancedays) {
        this.balancedays = balancedays;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}