package com.xiangxun.atms.module.property.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AlaICabLogSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlaICabLogSearch() {
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

        public Criteria andSnIsNull() {
            addCriterion("SN is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("SN is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("SN =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("SN <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("SN >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("SN >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("SN <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("SN <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("SN like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("SN not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("SN in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("SN not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("SN between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("SN not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNull() {
            addCriterion("EVENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNotNull() {
            addCriterion("EVENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEventTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EVENT_TIME =", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EVENT_TIME <>", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EVENT_TIME >", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EVENT_TIME >=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThan(Date value) {
            addCriterionForJDBCDate("EVENT_TIME <", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EVENT_TIME <=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeIn(List<Date> values) {
            addCriterionForJDBCDate("EVENT_TIME in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EVENT_TIME not in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EVENT_TIME between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EVENT_TIME not between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andSensorTypeIsNull() {
            addCriterion("SENSOR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSensorTypeIsNotNull() {
            addCriterion("SENSOR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSensorTypeEqualTo(Short value) {
            addCriterion("SENSOR_TYPE =", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeNotEqualTo(Short value) {
            addCriterion("SENSOR_TYPE <>", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeGreaterThan(Short value) {
            addCriterion("SENSOR_TYPE >", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("SENSOR_TYPE >=", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeLessThan(Short value) {
            addCriterion("SENSOR_TYPE <", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeLessThanOrEqualTo(Short value) {
            addCriterion("SENSOR_TYPE <=", value, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeIn(List<Short> values) {
            addCriterion("SENSOR_TYPE in", values, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeNotIn(List<Short> values) {
            addCriterion("SENSOR_TYPE not in", values, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeBetween(Short value1, Short value2) {
            addCriterion("SENSOR_TYPE between", value1, value2, "sensorType");
            return (Criteria) this;
        }

        public Criteria andSensorTypeNotBetween(Short value1, Short value2) {
            addCriterion("SENSOR_TYPE not between", value1, value2, "sensorType");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(Object value) {
            addCriterion("MESSAGE =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(Object value) {
            addCriterion("MESSAGE <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(Object value) {
            addCriterion("MESSAGE >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(Object value) {
            addCriterion("MESSAGE >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(Object value) {
            addCriterion("MESSAGE <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(Object value) {
            addCriterion("MESSAGE <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<Object> values) {
            addCriterion("MESSAGE in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<Object> values) {
            addCriterion("MESSAGE not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(Object value1, Object value2) {
            addCriterion("MESSAGE between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(Object value1, Object value2) {
            addCriterion("MESSAGE not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageExIsNull() {
            addCriterion("MESSAGE_EX is null");
            return (Criteria) this;
        }

        public Criteria andMessageExIsNotNull() {
            addCriterion("MESSAGE_EX is not null");
            return (Criteria) this;
        }

        public Criteria andMessageExEqualTo(Object value) {
            addCriterion("MESSAGE_EX =", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExNotEqualTo(Object value) {
            addCriterion("MESSAGE_EX <>", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExGreaterThan(Object value) {
            addCriterion("MESSAGE_EX >", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExGreaterThanOrEqualTo(Object value) {
            addCriterion("MESSAGE_EX >=", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExLessThan(Object value) {
            addCriterion("MESSAGE_EX <", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExLessThanOrEqualTo(Object value) {
            addCriterion("MESSAGE_EX <=", value, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExIn(List<Object> values) {
            addCriterion("MESSAGE_EX in", values, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExNotIn(List<Object> values) {
            addCriterion("MESSAGE_EX not in", values, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExBetween(Object value1, Object value2) {
            addCriterion("MESSAGE_EX between", value1, value2, "messageEx");
            return (Criteria) this;
        }

        public Criteria andMessageExNotBetween(Object value1, Object value2) {
            addCriterion("MESSAGE_EX not between", value1, value2, "messageEx");
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

        public Criteria andStatusEqualTo(BigDecimal value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(BigDecimal value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(BigDecimal value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(BigDecimal value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<BigDecimal> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<BigDecimal> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andActualvalueIsNull() {
            addCriterion("ACTUALVALUE is null");
            return (Criteria) this;
        }

        public Criteria andActualvalueIsNotNull() {
            addCriterion("ACTUALVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andActualvalueEqualTo(BigDecimal value) {
            addCriterion("ACTUALVALUE =", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueNotEqualTo(BigDecimal value) {
            addCriterion("ACTUALVALUE <>", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueGreaterThan(BigDecimal value) {
            addCriterion("ACTUALVALUE >", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACTUALVALUE >=", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueLessThan(BigDecimal value) {
            addCriterion("ACTUALVALUE <", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACTUALVALUE <=", value, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueIn(List<BigDecimal> values) {
            addCriterion("ACTUALVALUE in", values, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueNotIn(List<BigDecimal> values) {
            addCriterion("ACTUALVALUE not in", values, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACTUALVALUE between", value1, value2, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andActualvalueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACTUALVALUE not between", value1, value2, "actualvalue");
            return (Criteria) this;
        }

        public Criteria andPowersupplyIsNull() {
            addCriterion("POWERSUPPLY is null");
            return (Criteria) this;
        }

        public Criteria andPowersupplyIsNotNull() {
            addCriterion("POWERSUPPLY is not null");
            return (Criteria) this;
        }

        public Criteria andPowersupplyEqualTo(Short value) {
            addCriterion("POWERSUPPLY =", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyNotEqualTo(Short value) {
            addCriterion("POWERSUPPLY <>", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyGreaterThan(Short value) {
            addCriterion("POWERSUPPLY >", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyGreaterThanOrEqualTo(Short value) {
            addCriterion("POWERSUPPLY >=", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyLessThan(Short value) {
            addCriterion("POWERSUPPLY <", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyLessThanOrEqualTo(Short value) {
            addCriterion("POWERSUPPLY <=", value, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyIn(List<Short> values) {
            addCriterion("POWERSUPPLY in", values, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyNotIn(List<Short> values) {
            addCriterion("POWERSUPPLY not in", values, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyBetween(Short value1, Short value2) {
            addCriterion("POWERSUPPLY between", value1, value2, "powersupply");
            return (Criteria) this;
        }

        public Criteria andPowersupplyNotBetween(Short value1, Short value2) {
            addCriterion("POWERSUPPLY not between", value1, value2, "powersupply");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueIsNull() {
            addCriterion("VOLTAGEVALUE is null");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueIsNotNull() {
            addCriterion("VOLTAGEVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueEqualTo(BigDecimal value) {
            addCriterion("VOLTAGEVALUE =", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueNotEqualTo(BigDecimal value) {
            addCriterion("VOLTAGEVALUE <>", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueGreaterThan(BigDecimal value) {
            addCriterion("VOLTAGEVALUE >", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VOLTAGEVALUE >=", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueLessThan(BigDecimal value) {
            addCriterion("VOLTAGEVALUE <", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VOLTAGEVALUE <=", value, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueIn(List<BigDecimal> values) {
            addCriterion("VOLTAGEVALUE in", values, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueNotIn(List<BigDecimal> values) {
            addCriterion("VOLTAGEVALUE not in", values, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOLTAGEVALUE between", value1, value2, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andVoltagevalueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOLTAGEVALUE not between", value1, value2, "voltagevalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueIsNull() {
            addCriterion("CURRENTVALUE is null");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueIsNotNull() {
            addCriterion("CURRENTVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueEqualTo(BigDecimal value) {
            addCriterion("CURRENTVALUE =", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueNotEqualTo(BigDecimal value) {
            addCriterion("CURRENTVALUE <>", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueGreaterThan(BigDecimal value) {
            addCriterion("CURRENTVALUE >", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CURRENTVALUE >=", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueLessThan(BigDecimal value) {
            addCriterion("CURRENTVALUE <", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CURRENTVALUE <=", value, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueIn(List<BigDecimal> values) {
            addCriterion("CURRENTVALUE in", values, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueNotIn(List<BigDecimal> values) {
            addCriterion("CURRENTVALUE not in", values, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CURRENTVALUE between", value1, value2, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andCurrentvalueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CURRENTVALUE not between", value1, value2, "currentvalue");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("CHANNEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("CHANNEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("CHANNEL_NAME =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("CHANNEL_NAME <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("CHANNEL_NAME >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_NAME >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("CHANNEL_NAME <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_NAME <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("CHANNEL_NAME like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("CHANNEL_NAME not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("CHANNEL_NAME in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("CHANNEL_NAME not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("CHANNEL_NAME between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_NAME not between", value1, value2, "channelName");
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