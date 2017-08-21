package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SentRecordSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SentRecordSearch() {
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

        public Criteria andMsgidIsNull() {
            addCriterion("MSGID is null");
            return (Criteria) this;
        }

        public Criteria andMsgidIsNotNull() {
            addCriterion("MSGID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgidEqualTo(BigDecimal value) {
            addCriterion("MSGID =", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotEqualTo(BigDecimal value) {
            addCriterion("MSGID <>", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThan(BigDecimal value) {
            addCriterion("MSGID >", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MSGID >=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThan(BigDecimal value) {
            addCriterion("MSGID <", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MSGID <=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidIn(List<BigDecimal> values) {
            addCriterion("MSGID in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotIn(List<BigDecimal> values) {
            addCriterion("MSGID not in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MSGID between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MSGID not between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andSplitindexIsNull() {
            addCriterion("SPLITINDEX is null");
            return (Criteria) this;
        }

        public Criteria andSplitindexIsNotNull() {
            addCriterion("SPLITINDEX is not null");
            return (Criteria) this;
        }

        public Criteria andSplitindexEqualTo(Short value) {
            addCriterion("SPLITINDEX =", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexNotEqualTo(Short value) {
            addCriterion("SPLITINDEX <>", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexGreaterThan(Short value) {
            addCriterion("SPLITINDEX >", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexGreaterThanOrEqualTo(Short value) {
            addCriterion("SPLITINDEX >=", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexLessThan(Short value) {
            addCriterion("SPLITINDEX <", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexLessThanOrEqualTo(Short value) {
            addCriterion("SPLITINDEX <=", value, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexIn(List<Short> values) {
            addCriterion("SPLITINDEX in", values, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexNotIn(List<Short> values) {
            addCriterion("SPLITINDEX not in", values, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexBetween(Short value1, Short value2) {
            addCriterion("SPLITINDEX between", value1, value2, "splitindex");
            return (Criteria) this;
        }

        public Criteria andSplitindexNotBetween(Short value1, Short value2) {
            addCriterion("SPLITINDEX not between", value1, value2, "splitindex");
            return (Criteria) this;
        }

        public Criteria andDesttelIsNull() {
            addCriterion("DESTTEL is null");
            return (Criteria) this;
        }

        public Criteria andDesttelIsNotNull() {
            addCriterion("DESTTEL is not null");
            return (Criteria) this;
        }

        public Criteria andDesttelEqualTo(String value) {
            addCriterion("DESTTEL =", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelNotEqualTo(String value) {
            addCriterion("DESTTEL <>", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelGreaterThan(String value) {
            addCriterion("DESTTEL >", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelGreaterThanOrEqualTo(String value) {
            addCriterion("DESTTEL >=", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelLessThan(String value) {
            addCriterion("DESTTEL <", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelLessThanOrEqualTo(String value) {
            addCriterion("DESTTEL <=", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelLike(String value) {
            addCriterion("DESTTEL like", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelNotLike(String value) {
            addCriterion("DESTTEL not like", value, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelIn(List<String> values) {
            addCriterion("DESTTEL in", values, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelNotIn(List<String> values) {
            addCriterion("DESTTEL not in", values, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelBetween(String value1, String value2) {
            addCriterion("DESTTEL between", value1, value2, "desttel");
            return (Criteria) this;
        }

        public Criteria andDesttelNotBetween(String value1, String value2) {
            addCriterion("DESTTEL not between", value1, value2, "desttel");
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

        public Criteria andSentstatusIsNull() {
            addCriterion("SENTSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andSentstatusIsNotNull() {
            addCriterion("SENTSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSentstatusEqualTo(Short value) {
            addCriterion("SENTSTATUS =", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusNotEqualTo(Short value) {
            addCriterion("SENTSTATUS <>", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusGreaterThan(Short value) {
            addCriterion("SENTSTATUS >", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("SENTSTATUS >=", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusLessThan(Short value) {
            addCriterion("SENTSTATUS <", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusLessThanOrEqualTo(Short value) {
            addCriterion("SENTSTATUS <=", value, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusIn(List<Short> values) {
            addCriterion("SENTSTATUS in", values, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusNotIn(List<Short> values) {
            addCriterion("SENTSTATUS not in", values, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusBetween(Short value1, Short value2) {
            addCriterion("SENTSTATUS between", value1, value2, "sentstatus");
            return (Criteria) this;
        }

        public Criteria andSentstatusNotBetween(Short value1, Short value2) {
            addCriterion("SENTSTATUS not between", value1, value2, "sentstatus");
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

        public Criteria andBatchidIsNull() {
            addCriterion("BATCHID is null");
            return (Criteria) this;
        }

        public Criteria andBatchidIsNotNull() {
            addCriterion("BATCHID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchidEqualTo(String value) {
            addCriterion("BATCHID =", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotEqualTo(String value) {
            addCriterion("BATCHID <>", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThan(String value) {
            addCriterion("BATCHID >", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThanOrEqualTo(String value) {
            addCriterion("BATCHID >=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThan(String value) {
            addCriterion("BATCHID <", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThanOrEqualTo(String value) {
            addCriterion("BATCHID <=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLike(String value) {
            addCriterion("BATCHID like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotLike(String value) {
            addCriterion("BATCHID not like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidIn(List<String> values) {
            addCriterion("BATCHID in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotIn(List<String> values) {
            addCriterion("BATCHID not in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidBetween(String value1, String value2) {
            addCriterion("BATCHID between", value1, value2, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotBetween(String value1, String value2) {
            addCriterion("BATCHID not between", value1, value2, "batchid");
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