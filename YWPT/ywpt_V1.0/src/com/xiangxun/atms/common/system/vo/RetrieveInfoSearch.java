package com.xiangxun.atms.common.system.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RetrieveInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RetrieveInfoSearch() {
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

        public Criteria andDeltimeIsNull() {
            addCriterion("DELTIME is null");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNotNull() {
            addCriterion("DELTIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeltimeEqualTo(Date value) {
            addCriterionForJDBCDate("DELTIME =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("DELTIME <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Date value) {
            addCriterionForJDBCDate("DELTIME >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DELTIME >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Date value) {
            addCriterionForJDBCDate("DELTIME <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DELTIME <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Date> values) {
            addCriterionForJDBCDate("DELTIME in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("DELTIME not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DELTIME between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DELTIME not between", value1, value2, "deltime");
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

        public Criteria andClearflagIsNull() {
            addCriterion("CLEARFLAG is null");
            return (Criteria) this;
        }

        public Criteria andClearflagIsNotNull() {
            addCriterion("CLEARFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andClearflagEqualTo(String value) {
            addCriterion("CLEARFLAG =", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagNotEqualTo(String value) {
            addCriterion("CLEARFLAG <>", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagGreaterThan(String value) {
            addCriterion("CLEARFLAG >", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagGreaterThanOrEqualTo(String value) {
            addCriterion("CLEARFLAG >=", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagLessThan(String value) {
            addCriterion("CLEARFLAG <", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagLessThanOrEqualTo(String value) {
            addCriterion("CLEARFLAG <=", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagLike(String value) {
            addCriterion("CLEARFLAG like", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagNotLike(String value) {
            addCriterion("CLEARFLAG not like", value, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagIn(List<String> values) {
            addCriterion("CLEARFLAG in", values, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagNotIn(List<String> values) {
            addCriterion("CLEARFLAG not in", values, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagBetween(String value1, String value2) {
            addCriterion("CLEARFLAG between", value1, value2, "clearflag");
            return (Criteria) this;
        }

        public Criteria andClearflagNotBetween(String value1, String value2) {
            addCriterion("CLEARFLAG not between", value1, value2, "clearflag");
            return (Criteria) this;
        }

        public Criteria andPojoclassIsNull() {
            addCriterion("POJOCLASS is null");
            return (Criteria) this;
        }

        public Criteria andPojoclassIsNotNull() {
            addCriterion("POJOCLASS is not null");
            return (Criteria) this;
        }

        public Criteria andPojoclassEqualTo(String value) {
            addCriterion("POJOCLASS =", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassNotEqualTo(String value) {
            addCriterion("POJOCLASS <>", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassGreaterThan(String value) {
            addCriterion("POJOCLASS >", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassGreaterThanOrEqualTo(String value) {
            addCriterion("POJOCLASS >=", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassLessThan(String value) {
            addCriterion("POJOCLASS <", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassLessThanOrEqualTo(String value) {
            addCriterion("POJOCLASS <=", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassLike(String value) {
            addCriterion("POJOCLASS like", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassNotLike(String value) {
            addCriterion("POJOCLASS not like", value, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassIn(List<String> values) {
            addCriterion("POJOCLASS in", values, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassNotIn(List<String> values) {
            addCriterion("POJOCLASS not in", values, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassBetween(String value1, String value2) {
            addCriterion("POJOCLASS between", value1, value2, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andPojoclassNotBetween(String value1, String value2) {
            addCriterion("POJOCLASS not between", value1, value2, "pojoclass");
            return (Criteria) this;
        }

        public Criteria andOperatoripIsNull() {
            addCriterion("OPERATORIP is null");
            return (Criteria) this;
        }

        public Criteria andOperatoripIsNotNull() {
            addCriterion("OPERATORIP is not null");
            return (Criteria) this;
        }

        public Criteria andOperatoripEqualTo(String value) {
            addCriterion("OPERATORIP =", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripNotEqualTo(String value) {
            addCriterion("OPERATORIP <>", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripGreaterThan(String value) {
            addCriterion("OPERATORIP >", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATORIP >=", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripLessThan(String value) {
            addCriterion("OPERATORIP <", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripLessThanOrEqualTo(String value) {
            addCriterion("OPERATORIP <=", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripLike(String value) {
            addCriterion("OPERATORIP like", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripNotLike(String value) {
            addCriterion("OPERATORIP not like", value, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripIn(List<String> values) {
            addCriterion("OPERATORIP in", values, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripNotIn(List<String> values) {
            addCriterion("OPERATORIP not in", values, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripBetween(String value1, String value2) {
            addCriterion("OPERATORIP between", value1, value2, "operatorip");
            return (Criteria) this;
        }

        public Criteria andOperatoripNotBetween(String value1, String value2) {
            addCriterion("OPERATORIP not between", value1, value2, "operatorip");
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