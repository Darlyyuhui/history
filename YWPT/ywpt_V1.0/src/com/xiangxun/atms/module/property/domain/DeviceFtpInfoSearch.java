package com.xiangxun.atms.module.property.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceFtpInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceFtpInfoSearch() {
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

        public Criteria andFtpipIsNull() {
            addCriterion("FTPIP is null");
            return (Criteria) this;
        }

        public Criteria andFtpipIsNotNull() {
            addCriterion("FTPIP is not null");
            return (Criteria) this;
        }

        public Criteria andFtpipEqualTo(String value) {
            addCriterion("FTPIP =", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipNotEqualTo(String value) {
            addCriterion("FTPIP <>", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipGreaterThan(String value) {
            addCriterion("FTPIP >", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipGreaterThanOrEqualTo(String value) {
            addCriterion("FTPIP >=", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipLessThan(String value) {
            addCriterion("FTPIP <", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipLessThanOrEqualTo(String value) {
            addCriterion("FTPIP <=", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipLike(String value) {
            addCriterion("FTPIP like", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipNotLike(String value) {
            addCriterion("FTPIP not like", value, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipIn(List<String> values) {
            addCriterion("FTPIP in", values, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipNotIn(List<String> values) {
            addCriterion("FTPIP not in", values, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipBetween(String value1, String value2) {
            addCriterion("FTPIP between", value1, value2, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpipNotBetween(String value1, String value2) {
            addCriterion("FTPIP not between", value1, value2, "ftpip");
            return (Criteria) this;
        }

        public Criteria andFtpuserIsNull() {
            addCriterion("FTPUSER is null");
            return (Criteria) this;
        }

        public Criteria andFtpuserIsNotNull() {
            addCriterion("FTPUSER is not null");
            return (Criteria) this;
        }

        public Criteria andFtpuserEqualTo(String value) {
            addCriterion("FTPUSER =", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserNotEqualTo(String value) {
            addCriterion("FTPUSER <>", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserGreaterThan(String value) {
            addCriterion("FTPUSER >", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserGreaterThanOrEqualTo(String value) {
            addCriterion("FTPUSER >=", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserLessThan(String value) {
            addCriterion("FTPUSER <", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserLessThanOrEqualTo(String value) {
            addCriterion("FTPUSER <=", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserLike(String value) {
            addCriterion("FTPUSER like", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserNotLike(String value) {
            addCriterion("FTPUSER not like", value, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserIn(List<String> values) {
            addCriterion("FTPUSER in", values, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserNotIn(List<String> values) {
            addCriterion("FTPUSER not in", values, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserBetween(String value1, String value2) {
            addCriterion("FTPUSER between", value1, value2, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtpuserNotBetween(String value1, String value2) {
            addCriterion("FTPUSER not between", value1, value2, "ftpuser");
            return (Criteria) this;
        }

        public Criteria andFtppasswordIsNull() {
            addCriterion("FTPPASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andFtppasswordIsNotNull() {
            addCriterion("FTPPASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andFtppasswordEqualTo(String value) {
            addCriterion("FTPPASSWORD =", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordNotEqualTo(String value) {
            addCriterion("FTPPASSWORD <>", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordGreaterThan(String value) {
            addCriterion("FTPPASSWORD >", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordGreaterThanOrEqualTo(String value) {
            addCriterion("FTPPASSWORD >=", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordLessThan(String value) {
            addCriterion("FTPPASSWORD <", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordLessThanOrEqualTo(String value) {
            addCriterion("FTPPASSWORD <=", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordLike(String value) {
            addCriterion("FTPPASSWORD like", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordNotLike(String value) {
            addCriterion("FTPPASSWORD not like", value, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordIn(List<String> values) {
            addCriterion("FTPPASSWORD in", values, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordNotIn(List<String> values) {
            addCriterion("FTPPASSWORD not in", values, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordBetween(String value1, String value2) {
            addCriterion("FTPPASSWORD between", value1, value2, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtppasswordNotBetween(String value1, String value2) {
            addCriterion("FTPPASSWORD not between", value1, value2, "ftppassword");
            return (Criteria) this;
        }

        public Criteria andFtpportIsNull() {
            addCriterion("FTPPORT is null");
            return (Criteria) this;
        }

        public Criteria andFtpportIsNotNull() {
            addCriterion("FTPPORT is not null");
            return (Criteria) this;
        }

        public Criteria andFtpportEqualTo(String value) {
            addCriterion("FTPPORT =", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportNotEqualTo(String value) {
            addCriterion("FTPPORT <>", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportGreaterThan(String value) {
            addCriterion("FTPPORT >", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportGreaterThanOrEqualTo(String value) {
            addCriterion("FTPPORT >=", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportLessThan(String value) {
            addCriterion("FTPPORT <", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportLessThanOrEqualTo(String value) {
            addCriterion("FTPPORT <=", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportLike(String value) {
            addCriterion("FTPPORT like", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportNotLike(String value) {
            addCriterion("FTPPORT not like", value, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportIn(List<String> values) {
            addCriterion("FTPPORT in", values, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportNotIn(List<String> values) {
            addCriterion("FTPPORT not in", values, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportBetween(String value1, String value2) {
            addCriterion("FTPPORT between", value1, value2, "ftpport");
            return (Criteria) this;
        }

        public Criteria andFtpportNotBetween(String value1, String value2) {
            addCriterion("FTPPORT not between", value1, value2, "ftpport");
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

        public Criteria andHttpportIsNull() {
            addCriterion("HTTPPORT is null");
            return (Criteria) this;
        }

        public Criteria andHttpportIsNotNull() {
            addCriterion("HTTPPORT is not null");
            return (Criteria) this;
        }

        public Criteria andHttpportEqualTo(String value) {
            addCriterion("HTTPPORT =", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportNotEqualTo(String value) {
            addCriterion("HTTPPORT <>", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportGreaterThan(String value) {
            addCriterion("HTTPPORT >", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportGreaterThanOrEqualTo(String value) {
            addCriterion("HTTPPORT >=", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportLessThan(String value) {
            addCriterion("HTTPPORT <", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportLessThanOrEqualTo(String value) {
            addCriterion("HTTPPORT <=", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportLike(String value) {
            addCriterion("HTTPPORT like", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportNotLike(String value) {
            addCriterion("HTTPPORT not like", value, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportIn(List<String> values) {
            addCriterion("HTTPPORT in", values, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportNotIn(List<String> values) {
            addCriterion("HTTPPORT not in", values, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportBetween(String value1, String value2) {
            addCriterion("HTTPPORT between", value1, value2, "httpport");
            return (Criteria) this;
        }

        public Criteria andHttpportNotBetween(String value1, String value2) {
            addCriterion("HTTPPORT not between", value1, value2, "httpport");
            return (Criteria) this;
        }

        public Criteria andDirnameIsNull() {
            addCriterion("DIRNAME is null");
            return (Criteria) this;
        }

        public Criteria andDirnameIsNotNull() {
            addCriterion("DIRNAME is not null");
            return (Criteria) this;
        }

        public Criteria andDirnameEqualTo(String value) {
            addCriterion("DIRNAME =", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotEqualTo(String value) {
            addCriterion("DIRNAME <>", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameGreaterThan(String value) {
            addCriterion("DIRNAME >", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameGreaterThanOrEqualTo(String value) {
            addCriterion("DIRNAME >=", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLessThan(String value) {
            addCriterion("DIRNAME <", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLessThanOrEqualTo(String value) {
            addCriterion("DIRNAME <=", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLike(String value) {
            addCriterion("DIRNAME like", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotLike(String value) {
            addCriterion("DIRNAME not like", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameIn(List<String> values) {
            addCriterion("DIRNAME in", values, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotIn(List<String> values) {
            addCriterion("DIRNAME not in", values, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameBetween(String value1, String value2) {
            addCriterion("DIRNAME between", value1, value2, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotBetween(String value1, String value2) {
            addCriterion("DIRNAME not between", value1, value2, "dirname");
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