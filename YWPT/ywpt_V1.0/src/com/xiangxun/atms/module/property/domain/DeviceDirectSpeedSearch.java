package com.xiangxun.atms.module.property.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceDirectSpeedSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceDirectSpeedSearch() {
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

        public Criteria andDeviceCodeIsNull() {
            addCriterion("DEVICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNotNull() {
            addCriterion("DEVICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeEqualTo(String value) {
            addCriterion("DEVICE_CODE =", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotEqualTo(String value) {
            addCriterion("DEVICE_CODE <>", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThan(String value) {
            addCriterion("DEVICE_CODE >", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_CODE >=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThan(String value) {
            addCriterion("DEVICE_CODE <", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_CODE <=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLike(String value) {
            addCriterion("DEVICE_CODE like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotLike(String value) {
            addCriterion("DEVICE_CODE not like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIn(List<String> values) {
            addCriterion("DEVICE_CODE in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotIn(List<String> values) {
            addCriterion("DEVICE_CODE not in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeBetween(String value1, String value2) {
            addCriterion("DEVICE_CODE between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotBetween(String value1, String value2) {
            addCriterion("DEVICE_CODE not between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeIsNull() {
            addCriterion("DIRECTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeIsNotNull() {
            addCriterion("DIRECTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeEqualTo(String value) {
            addCriterion("DIRECTION_CODE =", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotEqualTo(String value) {
            addCriterion("DIRECTION_CODE <>", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeGreaterThan(String value) {
            addCriterion("DIRECTION_CODE >", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECTION_CODE >=", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLessThan(String value) {
            addCriterion("DIRECTION_CODE <", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLessThanOrEqualTo(String value) {
            addCriterion("DIRECTION_CODE <=", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLike(String value) {
            addCriterion("DIRECTION_CODE like", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotLike(String value) {
            addCriterion("DIRECTION_CODE not like", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeIn(List<String> values) {
            addCriterion("DIRECTION_CODE in", values, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotIn(List<String> values) {
            addCriterion("DIRECTION_CODE not in", values, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeBetween(String value1, String value2) {
            addCriterion("DIRECTION_CODE between", value1, value2, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotBetween(String value1, String value2) {
            addCriterion("DIRECTION_CODE not between", value1, value2, "directionCode");
            return (Criteria) this;
        }

        public Criteria andLandtotalIsNull() {
            addCriterion("LANDTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andLandtotalIsNotNull() {
            addCriterion("LANDTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andLandtotalEqualTo(String value) {
            addCriterion("LANDTOTAL =", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalNotEqualTo(String value) {
            addCriterion("LANDTOTAL <>", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalGreaterThan(String value) {
            addCriterion("LANDTOTAL >", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalGreaterThanOrEqualTo(String value) {
            addCriterion("LANDTOTAL >=", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalLessThan(String value) {
            addCriterion("LANDTOTAL <", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalLessThanOrEqualTo(String value) {
            addCriterion("LANDTOTAL <=", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalLike(String value) {
            addCriterion("LANDTOTAL like", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalNotLike(String value) {
            addCriterion("LANDTOTAL not like", value, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalIn(List<String> values) {
            addCriterion("LANDTOTAL in", values, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalNotIn(List<String> values) {
            addCriterion("LANDTOTAL not in", values, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalBetween(String value1, String value2) {
            addCriterion("LANDTOTAL between", value1, value2, "landtotal");
            return (Criteria) this;
        }

        public Criteria andLandtotalNotBetween(String value1, String value2) {
            addCriterion("LANDTOTAL not between", value1, value2, "landtotal");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedIsNull() {
            addCriterion("CARLIMITSPEED is null");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedIsNotNull() {
            addCriterion("CARLIMITSPEED is not null");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedEqualTo(String value) {
            addCriterion("CARLIMITSPEED =", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedNotEqualTo(String value) {
            addCriterion("CARLIMITSPEED <>", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedGreaterThan(String value) {
            addCriterion("CARLIMITSPEED >", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedGreaterThanOrEqualTo(String value) {
            addCriterion("CARLIMITSPEED >=", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedLessThan(String value) {
            addCriterion("CARLIMITSPEED <", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedLessThanOrEqualTo(String value) {
            addCriterion("CARLIMITSPEED <=", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedLike(String value) {
            addCriterion("CARLIMITSPEED like", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedNotLike(String value) {
            addCriterion("CARLIMITSPEED not like", value, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedIn(List<String> values) {
            addCriterion("CARLIMITSPEED in", values, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedNotIn(List<String> values) {
            addCriterion("CARLIMITSPEED not in", values, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedBetween(String value1, String value2) {
            addCriterion("CARLIMITSPEED between", value1, value2, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedNotBetween(String value1, String value2) {
            addCriterion("CARLIMITSPEED not between", value1, value2, "carlimitspeed");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchIsNull() {
            addCriterion("CARLIMITSPEEDCATCH is null");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchIsNotNull() {
            addCriterion("CARLIMITSPEEDCATCH is not null");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchEqualTo(String value) {
            addCriterion("CARLIMITSPEEDCATCH =", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchNotEqualTo(String value) {
            addCriterion("CARLIMITSPEEDCATCH <>", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchGreaterThan(String value) {
            addCriterion("CARLIMITSPEEDCATCH >", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchGreaterThanOrEqualTo(String value) {
            addCriterion("CARLIMITSPEEDCATCH >=", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchLessThan(String value) {
            addCriterion("CARLIMITSPEEDCATCH <", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchLessThanOrEqualTo(String value) {
            addCriterion("CARLIMITSPEEDCATCH <=", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchLike(String value) {
            addCriterion("CARLIMITSPEEDCATCH like", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchNotLike(String value) {
            addCriterion("CARLIMITSPEEDCATCH not like", value, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchIn(List<String> values) {
            addCriterion("CARLIMITSPEEDCATCH in", values, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchNotIn(List<String> values) {
            addCriterion("CARLIMITSPEEDCATCH not in", values, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchBetween(String value1, String value2) {
            addCriterion("CARLIMITSPEEDCATCH between", value1, value2, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andCarlimitspeedcatchNotBetween(String value1, String value2) {
            addCriterion("CARLIMITSPEEDCATCH not between", value1, value2, "carlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedIsNull() {
            addCriterion("BIGCARLIMITSPEED is null");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedIsNotNull() {
            addCriterion("BIGCARLIMITSPEED is not null");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEED =", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedNotEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEED <>", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedGreaterThan(String value) {
            addCriterion("BIGCARLIMITSPEED >", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedGreaterThanOrEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEED >=", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedLessThan(String value) {
            addCriterion("BIGCARLIMITSPEED <", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedLessThanOrEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEED <=", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedLike(String value) {
            addCriterion("BIGCARLIMITSPEED like", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedNotLike(String value) {
            addCriterion("BIGCARLIMITSPEED not like", value, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedIn(List<String> values) {
            addCriterion("BIGCARLIMITSPEED in", values, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedNotIn(List<String> values) {
            addCriterion("BIGCARLIMITSPEED not in", values, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedBetween(String value1, String value2) {
            addCriterion("BIGCARLIMITSPEED between", value1, value2, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedNotBetween(String value1, String value2) {
            addCriterion("BIGCARLIMITSPEED not between", value1, value2, "bigcarlimitspeed");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchIsNull() {
            addCriterion("BIGCARLIMITSPEEDCATCH is null");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchIsNotNull() {
            addCriterion("BIGCARLIMITSPEEDCATCH is not null");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH =", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchNotEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH <>", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchGreaterThan(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH >", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchGreaterThanOrEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH >=", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchLessThan(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH <", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchLessThanOrEqualTo(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH <=", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchLike(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH like", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchNotLike(String value) {
            addCriterion("BIGCARLIMITSPEEDCATCH not like", value, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchIn(List<String> values) {
            addCriterion("BIGCARLIMITSPEEDCATCH in", values, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchNotIn(List<String> values) {
            addCriterion("BIGCARLIMITSPEEDCATCH not in", values, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchBetween(String value1, String value2) {
            addCriterion("BIGCARLIMITSPEEDCATCH between", value1, value2, "bigcarlimitspeedcatch");
            return (Criteria) this;
        }

        public Criteria andBigcarlimitspeedcatchNotBetween(String value1, String value2) {
            addCriterion("BIGCARLIMITSPEEDCATCH not between", value1, value2, "bigcarlimitspeedcatch");
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