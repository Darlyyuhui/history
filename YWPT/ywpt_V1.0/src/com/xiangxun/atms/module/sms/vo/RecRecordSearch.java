package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RecRecordSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecRecordSearch() {
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

        public Criteria andSmsindexIsNull() {
            addCriterion("SMSINDEX is null");
            return (Criteria) this;
        }

        public Criteria andSmsindexIsNotNull() {
            addCriterion("SMSINDEX is not null");
            return (Criteria) this;
        }

        public Criteria andSmsindexEqualTo(BigDecimal value) {
            addCriterion("SMSINDEX =", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexNotEqualTo(BigDecimal value) {
            addCriterion("SMSINDEX <>", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexGreaterThan(BigDecimal value) {
            addCriterion("SMSINDEX >", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SMSINDEX >=", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexLessThan(BigDecimal value) {
            addCriterion("SMSINDEX <", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SMSINDEX <=", value, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexIn(List<BigDecimal> values) {
            addCriterion("SMSINDEX in", values, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexNotIn(List<BigDecimal> values) {
            addCriterion("SMSINDEX not in", values, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SMSINDEX between", value1, value2, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSmsindexNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SMSINDEX not between", value1, value2, "smsindex");
            return (Criteria) this;
        }

        public Criteria andSourcenumberIsNull() {
            addCriterion("SOURCENUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSourcenumberIsNotNull() {
            addCriterion("SOURCENUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSourcenumberEqualTo(String value) {
            addCriterion("SOURCENUMBER =", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberNotEqualTo(String value) {
            addCriterion("SOURCENUMBER <>", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberGreaterThan(String value) {
            addCriterion("SOURCENUMBER >", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCENUMBER >=", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberLessThan(String value) {
            addCriterion("SOURCENUMBER <", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberLessThanOrEqualTo(String value) {
            addCriterion("SOURCENUMBER <=", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberLike(String value) {
            addCriterion("SOURCENUMBER like", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberNotLike(String value) {
            addCriterion("SOURCENUMBER not like", value, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberIn(List<String> values) {
            addCriterion("SOURCENUMBER in", values, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberNotIn(List<String> values) {
            addCriterion("SOURCENUMBER not in", values, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberBetween(String value1, String value2) {
            addCriterion("SOURCENUMBER between", value1, value2, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andSourcenumberNotBetween(String value1, String value2) {
            addCriterion("SOURCENUMBER not between", value1, value2, "sourcenumber");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andSenttimeIsNull() {
            addCriterion("SENTTIME is null");
            return (Criteria) this;
        }

        public Criteria andSenttimeIsNotNull() {
            addCriterion("SENTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSenttimeEqualTo(Date value) {
            addCriterionForJDBCDate("SENTTIME =", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("SENTTIME <>", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("SENTTIME >", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SENTTIME >=", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeLessThan(Date value) {
            addCriterionForJDBCDate("SENTTIME <", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SENTTIME <=", value, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeIn(List<Date> values) {
            addCriterionForJDBCDate("SENTTIME in", values, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("SENTTIME not in", values, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SENTTIME between", value1, value2, "senttime");
            return (Criteria) this;
        }

        public Criteria andSenttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SENTTIME not between", value1, value2, "senttime");
            return (Criteria) this;
        }

        public Criteria andCommportIsNull() {
            addCriterion("COMMPORT is null");
            return (Criteria) this;
        }

        public Criteria andCommportIsNotNull() {
            addCriterion("COMMPORT is not null");
            return (Criteria) this;
        }

        public Criteria andCommportEqualTo(Short value) {
            addCriterion("COMMPORT =", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportNotEqualTo(Short value) {
            addCriterion("COMMPORT <>", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportGreaterThan(Short value) {
            addCriterion("COMMPORT >", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportGreaterThanOrEqualTo(Short value) {
            addCriterion("COMMPORT >=", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportLessThan(Short value) {
            addCriterion("COMMPORT <", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportLessThanOrEqualTo(Short value) {
            addCriterion("COMMPORT <=", value, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportIn(List<Short> values) {
            addCriterion("COMMPORT in", values, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportNotIn(List<Short> values) {
            addCriterion("COMMPORT not in", values, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportBetween(Short value1, Short value2) {
            addCriterion("COMMPORT between", value1, value2, "commport");
            return (Criteria) this;
        }

        public Criteria andCommportNotBetween(Short value1, Short value2) {
            addCriterion("COMMPORT not between", value1, value2, "commport");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIsNull() {
            addCriterion("MSGTYPE is null");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIsNotNull() {
            addCriterion("MSGTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgtypeEqualTo(Short value) {
            addCriterion("MSGTYPE =", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotEqualTo(Short value) {
            addCriterion("MSGTYPE <>", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeGreaterThan(Short value) {
            addCriterion("MSGTYPE >", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("MSGTYPE >=", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeLessThan(Short value) {
            addCriterion("MSGTYPE <", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeLessThanOrEqualTo(Short value) {
            addCriterion("MSGTYPE <=", value, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeIn(List<Short> values) {
            addCriterion("MSGTYPE in", values, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotIn(List<Short> values) {
            addCriterion("MSGTYPE not in", values, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeBetween(Short value1, Short value2) {
            addCriterion("MSGTYPE between", value1, value2, "msgtype");
            return (Criteria) this;
        }

        public Criteria andMsgtypeNotBetween(Short value1, Short value2) {
            addCriterion("MSGTYPE not between", value1, value2, "msgtype");
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