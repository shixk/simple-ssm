package com.imooc.vat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MtDutyCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public MtDutyCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMisnoIsNull() {
            addCriterion("misno is null");
            return (Criteria) this;
        }

        public Criteria andMisnoIsNotNull() {
            addCriterion("misno is not null");
            return (Criteria) this;
        }

        public Criteria andMisnoEqualTo(String value) {
            addCriterion("misno =", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoNotEqualTo(String value) {
            addCriterion("misno <>", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoGreaterThan(String value) {
            addCriterion("misno >", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoGreaterThanOrEqualTo(String value) {
            addCriterion("misno >=", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoLessThan(String value) {
            addCriterion("misno <", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoLessThanOrEqualTo(String value) {
            addCriterion("misno <=", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoLike(String value) {
            addCriterion("misno like", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoNotLike(String value) {
            addCriterion("misno not like", value, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoIn(List<String> values) {
            addCriterion("misno in", values, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoNotIn(List<String> values) {
            addCriterion("misno not in", values, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoBetween(String value1, String value2) {
            addCriterion("misno between", value1, value2, "misno");
            return (Criteria) this;
        }

        public Criteria andMisnoNotBetween(String value1, String value2) {
            addCriterion("misno not between", value1, value2, "misno");
            return (Criteria) this;
        }

        public Criteria andOverdaysIsNull() {
            addCriterion("overdays is null");
            return (Criteria) this;
        }

        public Criteria andOverdaysIsNotNull() {
            addCriterion("overdays is not null");
            return (Criteria) this;
        }

        public Criteria andOverdaysEqualTo(Double value) {
            addCriterion("overdays =", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysNotEqualTo(Double value) {
            addCriterion("overdays <>", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysGreaterThan(Double value) {
            addCriterion("overdays >", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysGreaterThanOrEqualTo(Double value) {
            addCriterion("overdays >=", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysLessThan(Double value) {
            addCriterion("overdays <", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysLessThanOrEqualTo(Double value) {
            addCriterion("overdays <=", value, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysIn(List<Double> values) {
            addCriterion("overdays in", values, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysNotIn(List<Double> values) {
            addCriterion("overdays not in", values, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysBetween(Double value1, Double value2) {
            addCriterion("overdays between", value1, value2, "overdays");
            return (Criteria) this;
        }

        public Criteria andOverdaysNotBetween(Double value1, Double value2) {
            addCriterion("overdays not between", value1, value2, "overdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysIsNull() {
            addCriterion("vacationdays is null");
            return (Criteria) this;
        }

        public Criteria andVacationdaysIsNotNull() {
            addCriterion("vacationdays is not null");
            return (Criteria) this;
        }

        public Criteria andVacationdaysEqualTo(Double value) {
            addCriterion("vacationdays =", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysNotEqualTo(Double value) {
            addCriterion("vacationdays <>", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysGreaterThan(Double value) {
            addCriterion("vacationdays >", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysGreaterThanOrEqualTo(Double value) {
            addCriterion("vacationdays >=", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysLessThan(Double value) {
            addCriterion("vacationdays <", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysLessThanOrEqualTo(Double value) {
            addCriterion("vacationdays <=", value, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysIn(List<Double> values) {
            addCriterion("vacationdays in", values, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysNotIn(List<Double> values) {
            addCriterion("vacationdays not in", values, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysBetween(Double value1, Double value2) {
            addCriterion("vacationdays between", value1, value2, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andVacationdaysNotBetween(Double value1, Double value2) {
            addCriterion("vacationdays not between", value1, value2, "vacationdays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysIsNull() {
            addCriterion("balancedays is null");
            return (Criteria) this;
        }

        public Criteria andBalancedaysIsNotNull() {
            addCriterion("balancedays is not null");
            return (Criteria) this;
        }

        public Criteria andBalancedaysEqualTo(Double value) {
            addCriterion("balancedays =", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysNotEqualTo(Double value) {
            addCriterion("balancedays <>", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysGreaterThan(Double value) {
            addCriterion("balancedays >", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysGreaterThanOrEqualTo(Double value) {
            addCriterion("balancedays >=", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysLessThan(Double value) {
            addCriterion("balancedays <", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysLessThanOrEqualTo(Double value) {
            addCriterion("balancedays <=", value, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysIn(List<Double> values) {
            addCriterion("balancedays in", values, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysNotIn(List<Double> values) {
            addCriterion("balancedays not in", values, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysBetween(Double value1, Double value2) {
            addCriterion("balancedays between", value1, value2, "balancedays");
            return (Criteria) this;
        }

        public Criteria andBalancedaysNotBetween(Double value1, Double value2) {
            addCriterion("balancedays not between", value1, value2, "balancedays");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNull() {
            addCriterion("createBy is null");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNotNull() {
            addCriterion("createBy is not null");
            return (Criteria) this;
        }

        public Criteria andCreatebyEqualTo(String value) {
            addCriterion("createBy =", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotEqualTo(String value) {
            addCriterion("createBy <>", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThan(String value) {
            addCriterion("createBy >", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThanOrEqualTo(String value) {
            addCriterion("createBy >=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThan(String value) {
            addCriterion("createBy <", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThanOrEqualTo(String value) {
            addCriterion("createBy <=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLike(String value) {
            addCriterion("createBy like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotLike(String value) {
            addCriterion("createBy not like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyIn(List<String> values) {
            addCriterion("createBy in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotIn(List<String> values) {
            addCriterion("createBy not in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyBetween(String value1, String value2) {
            addCriterion("createBy between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotBetween(String value1, String value2) {
            addCriterion("createBy not between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNull() {
            addCriterion("updateBy is null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNotNull() {
            addCriterion("updateBy is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyEqualTo(String value) {
            addCriterion("updateBy =", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotEqualTo(String value) {
            addCriterion("updateBy <>", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThan(String value) {
            addCriterion("updateBy >", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThanOrEqualTo(String value) {
            addCriterion("updateBy >=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThan(String value) {
            addCriterion("updateBy <", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThanOrEqualTo(String value) {
            addCriterion("updateBy <=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLike(String value) {
            addCriterion("updateBy like", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotLike(String value) {
            addCriterion("updateBy not like", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIn(List<String> values) {
            addCriterion("updateBy in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotIn(List<String> values) {
            addCriterion("updateBy not in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyBetween(String value1, String value2) {
            addCriterion("updateBy between", value1, value2, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotBetween(String value1, String value2) {
            addCriterion("updateBy not between", value1, value2, "updateby");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNull() {
            addCriterion("isdelete is null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNotNull() {
            addCriterion("isdelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteEqualTo(Integer value) {
            addCriterion("isdelete =", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotEqualTo(Integer value) {
            addCriterion("isdelete <>", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThan(Integer value) {
            addCriterion("isdelete >", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdelete >=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThan(Integer value) {
            addCriterion("isdelete <", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThanOrEqualTo(Integer value) {
            addCriterion("isdelete <=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIn(List<Integer> values) {
            addCriterion("isdelete in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotIn(List<Integer> values) {
            addCriterion("isdelete not in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteBetween(Integer value1, Integer value2) {
            addCriterion("isdelete between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("isdelete not between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andCustomCriteria(String value) {
            addCriterion( "("+ value +")" );
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}