package com.xiangxun.atms.module.repository.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class KnowledgeInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KnowledgeInfoSearch() {
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

        public Criteria andPathIsNull() {
            addCriterion("PATH is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("PATH =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("PATH <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("PATH >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("PATH >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("PATH <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("PATH <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("PATH like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("PATH not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("PATH in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("PATH not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("PATH between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("PATH not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("PID is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PID is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("PID =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("PID <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("PID >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("PID >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("PID <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("PID <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("PID like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("PID not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("PID in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("PID not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("PID between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("PID not between", value1, value2, "pid");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("CREATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CREATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATETIME =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATETIME <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATETIME >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATETIME >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATETIME <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATETIME <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATETIME in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATETIME not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATETIME between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATETIME not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andApplyflagIsNull() {
            addCriterion("APPLYFLAG is null");
            return (Criteria) this;
        }

        public Criteria andApplyflagIsNotNull() {
            addCriterion("APPLYFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andApplyflagEqualTo(String value) {
            addCriterion("APPLYFLAG =", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagNotEqualTo(String value) {
            addCriterion("APPLYFLAG <>", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagGreaterThan(String value) {
            addCriterion("APPLYFLAG >", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagGreaterThanOrEqualTo(String value) {
            addCriterion("APPLYFLAG >=", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagLessThan(String value) {
            addCriterion("APPLYFLAG <", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagLessThanOrEqualTo(String value) {
            addCriterion("APPLYFLAG <=", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagLike(String value) {
            addCriterion("APPLYFLAG like", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagNotLike(String value) {
            addCriterion("APPLYFLAG not like", value, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagIn(List<String> values) {
            addCriterion("APPLYFLAG in", values, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagNotIn(List<String> values) {
            addCriterion("APPLYFLAG not in", values, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagBetween(String value1, String value2) {
            addCriterion("APPLYFLAG between", value1, value2, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplyflagNotBetween(String value1, String value2) {
            addCriterion("APPLYFLAG not between", value1, value2, "applyflag");
            return (Criteria) this;
        }

        public Criteria andApplytimeIsNull() {
            addCriterion("APPLYTIME is null");
            return (Criteria) this;
        }

        public Criteria andApplytimeIsNotNull() {
            addCriterion("APPLYTIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplytimeEqualTo(Date value) {
            addCriterionForJDBCDate("APPLYTIME =", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("APPLYTIME <>", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThan(Date value) {
            addCriterionForJDBCDate("APPLYTIME >", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLYTIME >=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThan(Date value) {
            addCriterionForJDBCDate("APPLYTIME <", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLYTIME <=", value, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeIn(List<Date> values) {
            addCriterionForJDBCDate("APPLYTIME in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("APPLYTIME not in", values, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLYTIME between", value1, value2, "applytime");
            return (Criteria) this;
        }

        public Criteria andApplytimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLYTIME not between", value1, value2, "applytime");
            return (Criteria) this;
        }

        public Criteria andVerifyflagIsNull() {
            addCriterion("VERIFYFLAG is null");
            return (Criteria) this;
        }

        public Criteria andVerifyflagIsNotNull() {
            addCriterion("VERIFYFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyflagEqualTo(String value) {
            addCriterion("VERIFYFLAG =", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagNotEqualTo(String value) {
            addCriterion("VERIFYFLAG <>", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagGreaterThan(String value) {
            addCriterion("VERIFYFLAG >", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagGreaterThanOrEqualTo(String value) {
            addCriterion("VERIFYFLAG >=", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagLessThan(String value) {
            addCriterion("VERIFYFLAG <", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagLessThanOrEqualTo(String value) {
            addCriterion("VERIFYFLAG <=", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagLike(String value) {
            addCriterion("VERIFYFLAG like", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagNotLike(String value) {
            addCriterion("VERIFYFLAG not like", value, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagIn(List<String> values) {
            addCriterion("VERIFYFLAG in", values, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagNotIn(List<String> values) {
            addCriterion("VERIFYFLAG not in", values, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagBetween(String value1, String value2) {
            addCriterion("VERIFYFLAG between", value1, value2, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifyflagNotBetween(String value1, String value2) {
            addCriterion("VERIFYFLAG not between", value1, value2, "verifyflag");
            return (Criteria) this;
        }

        public Criteria andVerifierIsNull() {
            addCriterion("VERIFIER is null");
            return (Criteria) this;
        }

        public Criteria andVerifierIsNotNull() {
            addCriterion("VERIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andVerifierEqualTo(String value) {
            addCriterion("VERIFIER =", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierNotEqualTo(String value) {
            addCriterion("VERIFIER <>", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierGreaterThan(String value) {
            addCriterion("VERIFIER >", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierGreaterThanOrEqualTo(String value) {
            addCriterion("VERIFIER >=", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierLessThan(String value) {
            addCriterion("VERIFIER <", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierLessThanOrEqualTo(String value) {
            addCriterion("VERIFIER <=", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierLike(String value) {
            addCriterion("VERIFIER like", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierNotLike(String value) {
            addCriterion("VERIFIER not like", value, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierIn(List<String> values) {
            addCriterion("VERIFIER in", values, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierNotIn(List<String> values) {
            addCriterion("VERIFIER not in", values, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierBetween(String value1, String value2) {
            addCriterion("VERIFIER between", value1, value2, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifierNotBetween(String value1, String value2) {
            addCriterion("VERIFIER not between", value1, value2, "verifier");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIsNull() {
            addCriterion("VERIFYTIME is null");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIsNotNull() {
            addCriterion("VERIFYTIME is not null");
            return (Criteria) this;
        }

        public Criteria andVerifytimeEqualTo(Date value) {
            addCriterionForJDBCDate("VERIFYTIME =", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("VERIFYTIME <>", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeGreaterThan(Date value) {
            addCriterionForJDBCDate("VERIFYTIME >", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("VERIFYTIME >=", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeLessThan(Date value) {
            addCriterionForJDBCDate("VERIFYTIME <", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("VERIFYTIME <=", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIn(List<Date> values) {
            addCriterionForJDBCDate("VERIFYTIME in", values, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("VERIFYTIME not in", values, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("VERIFYTIME between", value1, value2, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("VERIFYTIME not between", value1, value2, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifyresultIsNull() {
            addCriterion("VERIFYRESULT is null");
            return (Criteria) this;
        }

        public Criteria andVerifyresultIsNotNull() {
            addCriterion("VERIFYRESULT is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyresultEqualTo(String value) {
            addCriterion("VERIFYRESULT =", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultNotEqualTo(String value) {
            addCriterion("VERIFYRESULT <>", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultGreaterThan(String value) {
            addCriterion("VERIFYRESULT >", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultGreaterThanOrEqualTo(String value) {
            addCriterion("VERIFYRESULT >=", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultLessThan(String value) {
            addCriterion("VERIFYRESULT <", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultLessThanOrEqualTo(String value) {
            addCriterion("VERIFYRESULT <=", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultLike(String value) {
            addCriterion("VERIFYRESULT like", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultNotLike(String value) {
            addCriterion("VERIFYRESULT not like", value, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultIn(List<String> values) {
            addCriterion("VERIFYRESULT in", values, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultNotIn(List<String> values) {
            addCriterion("VERIFYRESULT not in", values, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultBetween(String value1, String value2) {
            addCriterion("VERIFYRESULT between", value1, value2, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andVerifyresultNotBetween(String value1, String value2) {
            addCriterion("VERIFYRESULT not between", value1, value2, "verifyresult");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNull() {
            addCriterion("DISABLED is null");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNotNull() {
            addCriterion("DISABLED is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledEqualTo(String value) {
            addCriterion("DISABLED =", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotEqualTo(String value) {
            addCriterion("DISABLED <>", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThan(String value) {
            addCriterion("DISABLED >", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThanOrEqualTo(String value) {
            addCriterion("DISABLED >=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThan(String value) {
            addCriterion("DISABLED <", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThanOrEqualTo(String value) {
            addCriterion("DISABLED <=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLike(String value) {
            addCriterion("DISABLED like", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotLike(String value) {
            addCriterion("DISABLED not like", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledIn(List<String> values) {
            addCriterion("DISABLED in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotIn(List<String> values) {
            addCriterion("DISABLED not in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledBetween(String value1, String value2) {
            addCriterion("DISABLED between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotBetween(String value1, String value2) {
            addCriterion("DISABLED not between", value1, value2, "disabled");
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

        public Criteria andRebutreasonIsNull() {
            addCriterion("REBUTREASON is null");
            return (Criteria) this;
        }

        public Criteria andRebutreasonIsNotNull() {
            addCriterion("REBUTREASON is not null");
            return (Criteria) this;
        }

        public Criteria andRebutreasonEqualTo(String value) {
            addCriterion("REBUTREASON =", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonNotEqualTo(String value) {
            addCriterion("REBUTREASON <>", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonGreaterThan(String value) {
            addCriterion("REBUTREASON >", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonGreaterThanOrEqualTo(String value) {
            addCriterion("REBUTREASON >=", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonLessThan(String value) {
            addCriterion("REBUTREASON <", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonLessThanOrEqualTo(String value) {
            addCriterion("REBUTREASON <=", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonLike(String value) {
            addCriterion("REBUTREASON like", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonNotLike(String value) {
            addCriterion("REBUTREASON not like", value, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonIn(List<String> values) {
            addCriterion("REBUTREASON in", values, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonNotIn(List<String> values) {
            addCriterion("REBUTREASON not in", values, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonBetween(String value1, String value2) {
            addCriterion("REBUTREASON between", value1, value2, "rebutreason");
            return (Criteria) this;
        }

        public Criteria andRebutreasonNotBetween(String value1, String value2) {
            addCriterion("REBUTREASON not between", value1, value2, "rebutreason");
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