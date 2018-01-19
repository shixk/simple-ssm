package com.imooc.vat.entity.Searcher;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shixuekai
 * @CreateDate 2017/12/28
 * @Description
 **/
public class BaseSearcherVM {
    //逗号分隔的GUID，行项唯一标识
    private String ids;
    /* MyBatis IDS */
    private List<String> batisIds;

    /***
     * Easy UI 参数，由页面前台 传递
     ***/
    //当前页
    private Integer page;
    //每页行数
    private Integer rows;

    /* MyBatis 分页参数 */
    private Integer batisOffset;
    private Integer batisRows;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<String> getBatisIds() {
        if (StringUtils.isNotBlank(ids)) {
            String[] saIds = StringUtils.split(ids, ',');
            this.batisIds = Arrays.asList(saIds);
        }
        return batisIds;
    }

    public void setBatisIds(List<String> batisIds) {
        this.batisIds = batisIds;
    }

    public Integer getPage() {
        if(page == null || page < 1){
            page = 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        if(rows==null || rows < 10){
            rows = 10;
        }
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /* MyBatis 分页参数 */
    public Integer getBatisOffset() {
        if (page != null) {
            if (page == 0) {
                this.batisOffset = 0;
            } else {
                this.batisOffset = (page - 1) * rows;
            }
        }
        return this.batisOffset;
    }

    public void setBatisOffset(Integer batisOffset) {
        this.batisOffset = batisOffset;
    }

    public Integer getBatisRows() {
        this.batisRows = rows;
        return this.batisRows;
    }

    public void setBatisRows(Integer batisRows) {
        this.batisRows = batisRows;
    }
}
