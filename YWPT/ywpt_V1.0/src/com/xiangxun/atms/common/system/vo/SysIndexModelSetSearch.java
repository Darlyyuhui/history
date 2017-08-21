package com.xiangxun.atms.common.system.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysIndexModelSetSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysIndexModelSetSearch() {
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

        public Criteria andLeftIsNull() {
            addCriterion("LEFT is null");
            return (Criteria) this;
        }

        public Criteria andLeftIsNotNull() {
            addCriterion("LEFT is not null");
            return (Criteria) this;
        }

        public Criteria andLeftEqualTo(String value) {
            addCriterion("LEFT =", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftNotEqualTo(String value) {
            addCriterion("LEFT <>", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftGreaterThan(String value) {
            addCriterion("LEFT >", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftGreaterThanOrEqualTo(String value) {
            addCriterion("LEFT >=", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftLessThan(String value) {
            addCriterion("LEFT <", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftLessThanOrEqualTo(String value) {
            addCriterion("LEFT <=", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftLike(String value) {
            addCriterion("LEFT like", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftNotLike(String value) {
            addCriterion("LEFT not like", value, "left");
            return (Criteria) this;
        }

        public Criteria andLeftIn(List<String> values) {
            addCriterion("LEFT in", values, "left");
            return (Criteria) this;
        }

        public Criteria andLeftNotIn(List<String> values) {
            addCriterion("LEFT not in", values, "left");
            return (Criteria) this;
        }

        public Criteria andLeftBetween(String value1, String value2) {
            addCriterion("LEFT between", value1, value2, "left");
            return (Criteria) this;
        }

        public Criteria andLeftNotBetween(String value1, String value2) {
            addCriterion("LEFT not between", value1, value2, "left");
            return (Criteria) this;
        }

        public Criteria andCenterIsNull() {
            addCriterion("CENTER is null");
            return (Criteria) this;
        }

        public Criteria andCenterIsNotNull() {
            addCriterion("CENTER is not null");
            return (Criteria) this;
        }

        public Criteria andCenterEqualTo(String value) {
            addCriterion("CENTER =", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterNotEqualTo(String value) {
            addCriterion("CENTER <>", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterGreaterThan(String value) {
            addCriterion("CENTER >", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterGreaterThanOrEqualTo(String value) {
            addCriterion("CENTER >=", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterLessThan(String value) {
            addCriterion("CENTER <", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterLessThanOrEqualTo(String value) {
            addCriterion("CENTER <=", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterLike(String value) {
            addCriterion("CENTER like", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterNotLike(String value) {
            addCriterion("CENTER not like", value, "center");
            return (Criteria) this;
        }

        public Criteria andCenterIn(List<String> values) {
            addCriterion("CENTER in", values, "center");
            return (Criteria) this;
        }

        public Criteria andCenterNotIn(List<String> values) {
            addCriterion("CENTER not in", values, "center");
            return (Criteria) this;
        }

        public Criteria andCenterBetween(String value1, String value2) {
            addCriterion("CENTER between", value1, value2, "center");
            return (Criteria) this;
        }

        public Criteria andCenterNotBetween(String value1, String value2) {
            addCriterion("CENTER not between", value1, value2, "center");
            return (Criteria) this;
        }

        public Criteria andRightIsNull() {
            addCriterion("RIGHT is null");
            return (Criteria) this;
        }

        public Criteria andRightIsNotNull() {
            addCriterion("RIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andRightEqualTo(String value) {
            addCriterion("RIGHT =", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotEqualTo(String value) {
            addCriterion("RIGHT <>", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightGreaterThan(String value) {
            addCriterion("RIGHT >", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightGreaterThanOrEqualTo(String value) {
            addCriterion("RIGHT >=", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightLessThan(String value) {
            addCriterion("RIGHT <", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightLessThanOrEqualTo(String value) {
            addCriterion("RIGHT <=", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightLike(String value) {
            addCriterion("RIGHT like", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotLike(String value) {
            addCriterion("RIGHT not like", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightIn(List<String> values) {
            addCriterion("RIGHT in", values, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotIn(List<String> values) {
            addCriterion("RIGHT not in", values, "right");
            return (Criteria) this;
        }

        public Criteria andRightBetween(String value1, String value2) {
            addCriterion("RIGHT between", value1, value2, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotBetween(String value1, String value2) {
            addCriterion("RIGHT not between", value1, value2, "right");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNull() {
            addCriterion("INSERTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNotNull() {
            addCriterion("INSERTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInserttimeEqualTo(Date value) {
            addCriterion("INSERTTIME =", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotEqualTo(Date value) {
            addCriterion("INSERTTIME <>", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThan(Date value) {
            addCriterion("INSERTTIME >", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME >=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThan(Date value) {
            addCriterion("INSERTTIME <", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME <=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeIn(List<Date> values) {
            addCriterion("INSERTTIME in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotIn(List<Date> values) {
            addCriterion("INSERTTIME not in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME not between", value1, value2, "inserttime");
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