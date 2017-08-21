package com.xiangxun.atms.module.property.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ServerInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServerInfoSearch() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andServeripIsNull() {
            addCriterion("SERVERIP is null");
            return (Criteria) this;
        }

        public Criteria andServeripIsNotNull() {
            addCriterion("SERVERIP is not null");
            return (Criteria) this;
        }

        public Criteria andServeripEqualTo(String value) {
            addCriterion("SERVERIP =", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotEqualTo(String value) {
            addCriterion("SERVERIP <>", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripGreaterThan(String value) {
            addCriterion("SERVERIP >", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripGreaterThanOrEqualTo(String value) {
            addCriterion("SERVERIP >=", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLessThan(String value) {
            addCriterion("SERVERIP <", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLessThanOrEqualTo(String value) {
            addCriterion("SERVERIP <=", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLike(String value) {
            addCriterion("SERVERIP like", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotLike(String value) {
            addCriterion("SERVERIP not like", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripIn(List<String> values) {
            addCriterion("SERVERIP in", values, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotIn(List<String> values) {
            addCriterion("SERVERIP not in", values, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripBetween(String value1, String value2) {
            addCriterion("SERVERIP between", value1, value2, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotBetween(String value1, String value2) {
            addCriterion("SERVERIP not between", value1, value2, "serverip");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("MODEL is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("MODEL =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("MODEL <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("MODEL >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("MODEL >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("MODEL <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("MODEL <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("MODEL like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("MODEL not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("MODEL in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("MODEL not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("MODEL between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("MODEL not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCpuModelIsNull() {
            addCriterion("CPU_MODEL is null");
            return (Criteria) this;
        }

        public Criteria andCpuModelIsNotNull() {
            addCriterion("CPU_MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andCpuModelEqualTo(String value) {
            addCriterion("CPU_MODEL =", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotEqualTo(String value) {
            addCriterion("CPU_MODEL <>", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelGreaterThan(String value) {
            addCriterion("CPU_MODEL >", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelGreaterThanOrEqualTo(String value) {
            addCriterion("CPU_MODEL >=", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLessThan(String value) {
            addCriterion("CPU_MODEL <", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLessThanOrEqualTo(String value) {
            addCriterion("CPU_MODEL <=", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLike(String value) {
            addCriterion("CPU_MODEL like", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotLike(String value) {
            addCriterion("CPU_MODEL not like", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelIn(List<String> values) {
            addCriterion("CPU_MODEL in", values, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotIn(List<String> values) {
            addCriterion("CPU_MODEL not in", values, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelBetween(String value1, String value2) {
            addCriterion("CPU_MODEL between", value1, value2, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotBetween(String value1, String value2) {
            addCriterion("CPU_MODEL not between", value1, value2, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuTypeIsNull() {
            addCriterion("CPU_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCpuTypeIsNotNull() {
            addCriterion("CPU_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCpuTypeEqualTo(String value) {
            addCriterion("CPU_TYPE =", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeNotEqualTo(String value) {
            addCriterion("CPU_TYPE <>", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeGreaterThan(String value) {
            addCriterion("CPU_TYPE >", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CPU_TYPE >=", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeLessThan(String value) {
            addCriterion("CPU_TYPE <", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeLessThanOrEqualTo(String value) {
            addCriterion("CPU_TYPE <=", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeLike(String value) {
            addCriterion("CPU_TYPE like", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeNotLike(String value) {
            addCriterion("CPU_TYPE not like", value, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeIn(List<String> values) {
            addCriterion("CPU_TYPE in", values, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeNotIn(List<String> values) {
            addCriterion("CPU_TYPE not in", values, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeBetween(String value1, String value2) {
            addCriterion("CPU_TYPE between", value1, value2, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuTypeNotBetween(String value1, String value2) {
            addCriterion("CPU_TYPE not between", value1, value2, "cpuType");
            return (Criteria) this;
        }

        public Criteria andCpuNumIsNull() {
            addCriterion("CPU_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCpuNumIsNotNull() {
            addCriterion("CPU_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCpuNumEqualTo(BigDecimal value) {
            addCriterion("CPU_NUM =", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumNotEqualTo(BigDecimal value) {
            addCriterion("CPU_NUM <>", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumGreaterThan(BigDecimal value) {
            addCriterion("CPU_NUM >", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_NUM >=", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumLessThan(BigDecimal value) {
            addCriterion("CPU_NUM <", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_NUM <=", value, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumIn(List<BigDecimal> values) {
            addCriterion("CPU_NUM in", values, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumNotIn(List<BigDecimal> values) {
            addCriterion("CPU_NUM not in", values, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_NUM between", value1, value2, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_NUM not between", value1, value2, "cpuNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumIsNull() {
            addCriterion("CPU_CORE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumIsNotNull() {
            addCriterion("CPU_CORE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumEqualTo(BigDecimal value) {
            addCriterion("CPU_CORE_NUM =", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumNotEqualTo(BigDecimal value) {
            addCriterion("CPU_CORE_NUM <>", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumGreaterThan(BigDecimal value) {
            addCriterion("CPU_CORE_NUM >", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_CORE_NUM >=", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumLessThan(BigDecimal value) {
            addCriterion("CPU_CORE_NUM <", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_CORE_NUM <=", value, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumIn(List<BigDecimal> values) {
            addCriterion("CPU_CORE_NUM in", values, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumNotIn(List<BigDecimal> values) {
            addCriterion("CPU_CORE_NUM not in", values, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_CORE_NUM between", value1, value2, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_CORE_NUM not between", value1, value2, "cpuCoreNum");
            return (Criteria) this;
        }

        public Criteria andThreeCacheIsNull() {
            addCriterion("THREE_CACHE is null");
            return (Criteria) this;
        }

        public Criteria andThreeCacheIsNotNull() {
            addCriterion("THREE_CACHE is not null");
            return (Criteria) this;
        }

        public Criteria andThreeCacheEqualTo(BigDecimal value) {
            addCriterion("THREE_CACHE =", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheNotEqualTo(BigDecimal value) {
            addCriterion("THREE_CACHE <>", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheGreaterThan(BigDecimal value) {
            addCriterion("THREE_CACHE >", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("THREE_CACHE >=", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheLessThan(BigDecimal value) {
            addCriterion("THREE_CACHE <", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheLessThanOrEqualTo(BigDecimal value) {
            addCriterion("THREE_CACHE <=", value, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheIn(List<BigDecimal> values) {
            addCriterion("THREE_CACHE in", values, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheNotIn(List<BigDecimal> values) {
            addCriterion("THREE_CACHE not in", values, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THREE_CACHE between", value1, value2, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreeCacheNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THREE_CACHE not between", value1, value2, "threeCache");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNull() {
            addCriterion("THREAD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNotNull() {
            addCriterion("THREAD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andThreadNumEqualTo(BigDecimal value) {
            addCriterion("THREAD_NUM =", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotEqualTo(BigDecimal value) {
            addCriterion("THREAD_NUM <>", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThan(BigDecimal value) {
            addCriterion("THREAD_NUM >", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("THREAD_NUM >=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThan(BigDecimal value) {
            addCriterion("THREAD_NUM <", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("THREAD_NUM <=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumIn(List<BigDecimal> values) {
            addCriterion("THREAD_NUM in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotIn(List<BigDecimal> values) {
            addCriterion("THREAD_NUM not in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THREAD_NUM between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THREAD_NUM not between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andRamTypeIsNull() {
            addCriterion("RAM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRamTypeIsNotNull() {
            addCriterion("RAM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRamTypeEqualTo(String value) {
            addCriterion("RAM_TYPE =", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeNotEqualTo(String value) {
            addCriterion("RAM_TYPE <>", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeGreaterThan(String value) {
            addCriterion("RAM_TYPE >", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RAM_TYPE >=", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeLessThan(String value) {
            addCriterion("RAM_TYPE <", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeLessThanOrEqualTo(String value) {
            addCriterion("RAM_TYPE <=", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeLike(String value) {
            addCriterion("RAM_TYPE like", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeNotLike(String value) {
            addCriterion("RAM_TYPE not like", value, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeIn(List<String> values) {
            addCriterion("RAM_TYPE in", values, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeNotIn(List<String> values) {
            addCriterion("RAM_TYPE not in", values, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeBetween(String value1, String value2) {
            addCriterion("RAM_TYPE between", value1, value2, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamTypeNotBetween(String value1, String value2) {
            addCriterion("RAM_TYPE not between", value1, value2, "ramType");
            return (Criteria) this;
        }

        public Criteria andRamVolumeIsNull() {
            addCriterion("RAM_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andRamVolumeIsNotNull() {
            addCriterion("RAM_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andRamVolumeEqualTo(String value) {
            addCriterion("RAM_VOLUME =", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeNotEqualTo(String value) {
            addCriterion("RAM_VOLUME <>", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeGreaterThan(String value) {
            addCriterion("RAM_VOLUME >", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("RAM_VOLUME >=", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeLessThan(String value) {
            addCriterion("RAM_VOLUME <", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeLessThanOrEqualTo(String value) {
            addCriterion("RAM_VOLUME <=", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeLike(String value) {
            addCriterion("RAM_VOLUME like", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeNotLike(String value) {
            addCriterion("RAM_VOLUME not like", value, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeIn(List<String> values) {
            addCriterion("RAM_VOLUME in", values, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeNotIn(List<String> values) {
            addCriterion("RAM_VOLUME not in", values, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeBetween(String value1, String value2) {
            addCriterion("RAM_VOLUME between", value1, value2, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andRamVolumeNotBetween(String value1, String value2) {
            addCriterion("RAM_VOLUME not between", value1, value2, "ramVolume");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeIsNull() {
            addCriterion("DISK_PORT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeIsNotNull() {
            addCriterion("DISK_PORT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeEqualTo(String value) {
            addCriterion("DISK_PORT_TYPE =", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeNotEqualTo(String value) {
            addCriterion("DISK_PORT_TYPE <>", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeGreaterThan(String value) {
            addCriterion("DISK_PORT_TYPE >", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DISK_PORT_TYPE >=", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeLessThan(String value) {
            addCriterion("DISK_PORT_TYPE <", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeLessThanOrEqualTo(String value) {
            addCriterion("DISK_PORT_TYPE <=", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeLike(String value) {
            addCriterion("DISK_PORT_TYPE like", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeNotLike(String value) {
            addCriterion("DISK_PORT_TYPE not like", value, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeIn(List<String> values) {
            addCriterion("DISK_PORT_TYPE in", values, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeNotIn(List<String> values) {
            addCriterion("DISK_PORT_TYPE not in", values, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeBetween(String value1, String value2) {
            addCriterion("DISK_PORT_TYPE between", value1, value2, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskPortTypeNotBetween(String value1, String value2) {
            addCriterion("DISK_PORT_TYPE not between", value1, value2, "diskPortType");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeIsNull() {
            addCriterion("DISK_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeIsNotNull() {
            addCriterion("DISK_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeEqualTo(String value) {
            addCriterion("DISK_VOLUME =", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeNotEqualTo(String value) {
            addCriterion("DISK_VOLUME <>", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeGreaterThan(String value) {
            addCriterion("DISK_VOLUME >", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("DISK_VOLUME >=", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeLessThan(String value) {
            addCriterion("DISK_VOLUME <", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeLessThanOrEqualTo(String value) {
            addCriterion("DISK_VOLUME <=", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeLike(String value) {
            addCriterion("DISK_VOLUME like", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeNotLike(String value) {
            addCriterion("DISK_VOLUME not like", value, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeIn(List<String> values) {
            addCriterion("DISK_VOLUME in", values, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeNotIn(List<String> values) {
            addCriterion("DISK_VOLUME not in", values, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeBetween(String value1, String value2) {
            addCriterion("DISK_VOLUME between", value1, value2, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andDiskVolumeNotBetween(String value1, String value2) {
            addCriterion("DISK_VOLUME not between", value1, value2, "diskVolume");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("ADD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("ADD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_TIME =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_TIME <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("ADD_TIME >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_TIME >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterionForJDBCDate("ADD_TIME <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ADD_TIME <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterionForJDBCDate("ADD_TIME in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("ADD_TIME not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADD_TIME between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ADD_TIME not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("OPERATOR =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("OPERATOR <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("OPERATOR >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATOR >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("OPERATOR <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("OPERATOR <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("OPERATOR like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("OPERATOR not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("OPERATOR in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("OPERATOR not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("OPERATOR between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("OPERATOR not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNull() {
            addCriterion("FACTORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNotNull() {
            addCriterion("FACTORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdEqualTo(String value) {
            addCriterion("FACTORY_ID =", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotEqualTo(String value) {
            addCriterion("FACTORY_ID <>", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThan(String value) {
            addCriterion("FACTORY_ID >", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("FACTORY_ID >=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThan(String value) {
            addCriterion("FACTORY_ID <", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThanOrEqualTo(String value) {
            addCriterion("FACTORY_ID <=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLike(String value) {
            addCriterion("FACTORY_ID like", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotLike(String value) {
            addCriterion("FACTORY_ID not like", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIn(List<String> values) {
            addCriterion("FACTORY_ID in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotIn(List<String> values) {
            addCriterion("FACTORY_ID not in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdBetween(String value1, String value2) {
            addCriterion("FACTORY_ID between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotBetween(String value1, String value2) {
            addCriterion("FACTORY_ID not between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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