package com.xiangxun.atms.module.check.vo;

import java.util.ArrayList;
import java.util.List;

public class DataCheckResultSearch {
    /**
    
     */
    protected String orderByClause;

    /**
    
     */
    protected boolean distinct;

    /**
    
     */
    protected List<Criteria> oredCriteria;

    /**
    
     */
    public DataCheckResultSearch() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
    
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
    
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
    
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
    
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
    
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
    
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
    
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
    
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
    
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
    
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
    
     */
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

        public Criteria andInfoIdIsNull() {
            addCriterion("INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNotNull() {
            addCriterion("INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInfoIdEqualTo(String value) {
            addCriterion("INFO_ID =", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotEqualTo(String value) {
            addCriterion("INFO_ID <>", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThan(String value) {
            addCriterion("INFO_ID >", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_ID >=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThan(String value) {
            addCriterion("INFO_ID <", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThanOrEqualTo(String value) {
            addCriterion("INFO_ID <=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLike(String value) {
            addCriterion("INFO_ID like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotLike(String value) {
            addCriterion("INFO_ID not like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIn(List<String> values) {
            addCriterion("INFO_ID in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotIn(List<String> values) {
            addCriterion("INFO_ID not in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdBetween(String value1, String value2) {
            addCriterion("INFO_ID between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotBetween(String value1, String value2) {
            addCriterion("INFO_ID not between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andRegIdIsNull() {
            addCriterion("REG_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegIdIsNotNull() {
            addCriterion("REG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegIdEqualTo(String value) {
            addCriterion("REG_ID =", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotEqualTo(String value) {
            addCriterion("REG_ID <>", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThan(String value) {
            addCriterion("REG_ID >", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThanOrEqualTo(String value) {
            addCriterion("REG_ID >=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThan(String value) {
            addCriterion("REG_ID <", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThanOrEqualTo(String value) {
            addCriterion("REG_ID <=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLike(String value) {
            addCriterion("REG_ID like", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotLike(String value) {
            addCriterion("REG_ID not like", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdIn(List<String> values) {
            addCriterion("REG_ID in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotIn(List<String> values) {
            addCriterion("REG_ID not in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdBetween(String value1, String value2) {
            addCriterion("REG_ID between", value1, value2, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotBetween(String value1, String value2) {
            addCriterion("REG_ID not between", value1, value2, "regId");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNull() {
            addCriterion("REG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNotNull() {
            addCriterion("REG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegCodeEqualTo(String value) {
            addCriterion("REG_CODE =", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotEqualTo(String value) {
            addCriterion("REG_CODE <>", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThan(String value) {
            addCriterion("REG_CODE >", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REG_CODE >=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThan(String value) {
            addCriterion("REG_CODE <", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThanOrEqualTo(String value) {
            addCriterion("REG_CODE <=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLike(String value) {
            addCriterion("REG_CODE like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotLike(String value) {
            addCriterion("REG_CODE not like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeIn(List<String> values) {
            addCriterion("REG_CODE in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotIn(List<String> values) {
            addCriterion("REG_CODE not in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeBetween(String value1, String value2) {
            addCriterion("REG_CODE between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotBetween(String value1, String value2) {
            addCriterion("REG_CODE not between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andErrItemsIsNull() {
            addCriterion("ERR_ITEMS is null");
            return (Criteria) this;
        }

        public Criteria andErrItemsIsNotNull() {
            addCriterion("ERR_ITEMS is not null");
            return (Criteria) this;
        }

        public Criteria andErrItemsEqualTo(String value) {
            addCriterion("ERR_ITEMS =", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsNotEqualTo(String value) {
            addCriterion("ERR_ITEMS <>", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsGreaterThan(String value) {
            addCriterion("ERR_ITEMS >", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsGreaterThanOrEqualTo(String value) {
            addCriterion("ERR_ITEMS >=", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsLessThan(String value) {
            addCriterion("ERR_ITEMS <", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsLessThanOrEqualTo(String value) {
            addCriterion("ERR_ITEMS <=", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsLike(String value) {
            addCriterion("ERR_ITEMS like", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsNotLike(String value) {
            addCriterion("ERR_ITEMS not like", value, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsIn(List<String> values) {
            addCriterion("ERR_ITEMS in", values, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsNotIn(List<String> values) {
            addCriterion("ERR_ITEMS not in", values, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsBetween(String value1, String value2) {
            addCriterion("ERR_ITEMS between", value1, value2, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrItemsNotBetween(String value1, String value2) {
            addCriterion("ERR_ITEMS not between", value1, value2, "errItems");
            return (Criteria) this;
        }

        public Criteria andErrTypesIsNull() {
            addCriterion("ERR_TYPES is null");
            return (Criteria) this;
        }

        public Criteria andErrTypesIsNotNull() {
            addCriterion("ERR_TYPES is not null");
            return (Criteria) this;
        }

        public Criteria andErrTypesEqualTo(String value) {
            addCriterion("ERR_TYPES =", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesNotEqualTo(String value) {
            addCriterion("ERR_TYPES <>", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesGreaterThan(String value) {
            addCriterion("ERR_TYPES >", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesGreaterThanOrEqualTo(String value) {
            addCriterion("ERR_TYPES >=", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesLessThan(String value) {
            addCriterion("ERR_TYPES <", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesLessThanOrEqualTo(String value) {
            addCriterion("ERR_TYPES <=", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesLike(String value) {
            addCriterion("ERR_TYPES like", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesNotLike(String value) {
            addCriterion("ERR_TYPES not like", value, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesIn(List<String> values) {
            addCriterion("ERR_TYPES in", values, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesNotIn(List<String> values) {
            addCriterion("ERR_TYPES not in", values, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesBetween(String value1, String value2) {
            addCriterion("ERR_TYPES between", value1, value2, "errTypes");
            return (Criteria) this;
        }

        public Criteria andErrTypesNotBetween(String value1, String value2) {
            addCriterion("ERR_TYPES not between", value1, value2, "errTypes");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIsNull() {
            addCriterion("IS_INVALID is null");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIsNotNull() {
            addCriterion("IS_INVALID is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvalidEqualTo(Integer value) {
            addCriterion("IS_INVALID =", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotEqualTo(Integer value) {
            addCriterion("IS_INVALID <>", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidGreaterThan(Integer value) {
            addCriterion("IS_INVALID >", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_INVALID >=", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidLessThan(Integer value) {
            addCriterion("IS_INVALID <", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidLessThanOrEqualTo(Integer value) {
            addCriterion("IS_INVALID <=", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIn(List<Integer> values) {
            addCriterion("IS_INVALID in", values, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotIn(List<Integer> values) {
            addCriterion("IS_INVALID not in", values, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidBetween(Integer value1, Integer value2) {
            addCriterion("IS_INVALID between", value1, value2, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_INVALID not between", value1, value2, "isInvalid");
            return (Criteria) this;
        }
    }

    /**
    T_DATACHECK_RESULT
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
    
     */
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