package com.imooc.vat.entity.Vo;

import java.util.Date;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public class MtOverTimeVo {
    private Integer id;
    private String name;

    private String misno;

    private String occurtime;

    private String remark;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

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

    public String getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(String occurtime) {
        this.occurtime = occurtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

}
