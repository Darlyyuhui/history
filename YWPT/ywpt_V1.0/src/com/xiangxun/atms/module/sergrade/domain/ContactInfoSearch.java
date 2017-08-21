package com.xiangxun.atms.module.sergrade.domain;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContactInfoSearch() {
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

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("USERID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("USERID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("USERID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("USERID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("USERID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("USERID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("USERID like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("USERID not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("USERID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("USERID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andFactoryidIsNull() {
            addCriterion("FACTORYID is null");
            return (Criteria) this;
        }

        public Criteria andFactoryidIsNotNull() {
            addCriterion("FACTORYID is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryidEqualTo(String value) {
            addCriterion("FACTORYID =", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidNotEqualTo(String value) {
            addCriterion("FACTORYID <>", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidGreaterThan(String value) {
            addCriterion("FACTORYID >", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidGreaterThanOrEqualTo(String value) {
            addCriterion("FACTORYID >=", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidLessThan(String value) {
            addCriterion("FACTORYID <", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidLessThanOrEqualTo(String value) {
            addCriterion("FACTORYID <=", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidLike(String value) {
            addCriterion("FACTORYID like", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidNotLike(String value) {
            addCriterion("FACTORYID not like", value, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidIn(List<String> values) {
            addCriterion("FACTORYID in", values, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidNotIn(List<String> values) {
            addCriterion("FACTORYID not in", values, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidBetween(String value1, String value2) {
            addCriterion("FACTORYID between", value1, value2, "factoryid");
            return (Criteria) this;
        }

        public Criteria andFactoryidNotBetween(String value1, String value2) {
            addCriterion("FACTORYID not between", value1, value2, "factoryid");
            return (Criteria) this;
        }

        public Criteria andHasdelIsNull() {
            addCriterion("HASDEL is null");
            return (Criteria) this;
        }

        public Criteria andHasdelIsNotNull() {
            addCriterion("HASDEL is not null");
            return (Criteria) this;
        }

        public Criteria andHasdelEqualTo(String value) {
            addCriterion("HASDEL =", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelNotEqualTo(String value) {
            addCriterion("HASDEL <>", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelGreaterThan(String value) {
            addCriterion("HASDEL >", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelGreaterThanOrEqualTo(String value) {
            addCriterion("HASDEL >=", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelLessThan(String value) {
            addCriterion("HASDEL <", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelLessThanOrEqualTo(String value) {
            addCriterion("HASDEL <=", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelLike(String value) {
            addCriterion("HASDEL like", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelNotLike(String value) {
            addCriterion("HASDEL not like", value, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelIn(List<String> values) {
            addCriterion("HASDEL in", values, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelNotIn(List<String> values) {
            addCriterion("HASDEL not in", values, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelBetween(String value1, String value2) {
            addCriterion("HASDEL between", value1, value2, "hasdel");
            return (Criteria) this;
        }

        public Criteria andHasdelNotBetween(String value1, String value2) {
            addCriterion("HASDEL not between", value1, value2, "hasdel");
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