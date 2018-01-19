package com.imooc.vat.entity.Vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public class SearchResultVM<T> {

    // MyBatis 调用存储过程，返回的总条数，目前使用 "totalRecords"
    private static final String SP_PARAM_TOTAL = "totalRecords";
    /***
     * 构造成 前台 Easy UI 接受的数据结构
     ***/
    //数据行项
    private List<T> rows;
    //总条数
    private Integer total;

    public SearchResultVM(){
    }

    /**
     * [后台使用  存储过程]
     * 构造成 前台 Easy UI 接受的数据结构
     * 通过 searcher 和 返回的 结果集
     * @param searcher
     * @param rows
     */
    public SearchResultVM(Map<String, Object> searcher, List<T> rows) {
            this.setTotal(Integer.parseInt(searcher.get(SP_PARAM_TOTAL).toString()));
            this.setRows(rows);
    }

    /**
     * [后台使用 MyBatis ORM，自己写Dynamic SQL]
     *
     * @param rows
     * @param total
     */
    public SearchResultVM(List<T> rows, Integer total) {
            this.setRows(rows);
            this.setTotal(total);
    }

    /**
     * [后台使用  存储过程]
     * 构造成 前台 Easy UI 接受的数据结构
     * 通过 searcher 和 返回的 结果集
     *
     * @param rows
     * @param searcher
     * @param rows
     * @return
     */
    public SearchResultVM<T> buildResult(Map<String, Object> searcher, List<T> rows) {
        this.setTotal(Integer.parseInt(searcher.get(SP_PARAM_TOTAL).toString()));
        this.setRows(rows);
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        if(rows == null){
            rows = new ArrayList<T>();
        }
        this.rows = rows;
    }


}
