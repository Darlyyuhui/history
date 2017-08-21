package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SendTaskSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SendTaskSearch() {
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

        public Criteria andTaskidIsNull() {
            addCriterion("TASKID is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("TASKID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(BigDecimal value) {
            addCriterion("TASKID =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(BigDecimal value) {
            addCriterion("TASKID <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(BigDecimal value) {
            addCriterion("TASKID >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TASKID >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(BigDecimal value) {
            addCriterion("TASKID <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TASKID <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<BigDecimal> values) {
            addCriterion("TASKID in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<BigDecimal> values) {
            addCriterion("TASKID not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TASKID between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TASKID not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andDestnumberIsNull() {
            addCriterion("DESTNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andDestnumberIsNotNull() {
            addCriterion("DESTNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andDestnumberEqualTo(String value) {
            addCriterion("DESTNUMBER =", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberNotEqualTo(String value) {
            addCriterion("DESTNUMBER <>", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberGreaterThan(String value) {
            addCriterion("DESTNUMBER >", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberGreaterThanOrEqualTo(String value) {
            addCriterion("DESTNUMBER >=", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberLessThan(String value) {
            addCriterion("DESTNUMBER <", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberLessThanOrEqualTo(String value) {
            addCriterion("DESTNUMBER <=", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberLike(String value) {
            addCriterion("DESTNUMBER like", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberNotLike(String value) {
            addCriterion("DESTNUMBER not like", value, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberIn(List<String> values) {
            addCriterion("DESTNUMBER in", values, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberNotIn(List<String> values) {
            addCriterion("DESTNUMBER not in", values, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberBetween(String value1, String value2) {
            addCriterion("DESTNUMBER between", value1, value2, "destnumber");
            return (Criteria) this;
        }

        public Criteria andDestnumberNotBetween(String value1, String value2) {
            addCriterion("DESTNUMBER not between", value1, value2, "destnumber");
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

        public Criteria andSignnameIsNull() {
            addCriterion("SIGNNAME is null");
            return (Criteria) this;
        }

        public Criteria andSignnameIsNotNull() {
            addCriterion("SIGNNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignnameEqualTo(String value) {
            addCriterion("SIGNNAME =", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameNotEqualTo(String value) {
            addCriterion("SIGNNAME <>", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameGreaterThan(String value) {
            addCriterion("SIGNNAME >", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNNAME >=", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameLessThan(String value) {
            addCriterion("SIGNNAME <", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameLessThanOrEqualTo(String value) {
            addCriterion("SIGNNAME <=", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameLike(String value) {
            addCriterion("SIGNNAME like", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameNotLike(String value) {
            addCriterion("SIGNNAME not like", value, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameIn(List<String> values) {
            addCriterion("SIGNNAME in", values, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameNotIn(List<String> values) {
            addCriterion("SIGNNAME not in", values, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameBetween(String value1, String value2) {
            addCriterion("SIGNNAME between", value1, value2, "signname");
            return (Criteria) this;
        }

        public Criteria andSignnameNotBetween(String value1, String value2) {
            addCriterion("SIGNNAME not between", value1, value2, "signname");
            return (Criteria) this;
        }

        public Criteria andSendpriorityIsNull() {
            addCriterion("SENDPRIORITY is null");
            return (Criteria) this;
        }

        public Criteria andSendpriorityIsNotNull() {
            addCriterion("SENDPRIORITY is not null");
            return (Criteria) this;
        }

        public Criteria andSendpriorityEqualTo(Short value) {
            addCriterion("SENDPRIORITY =", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityNotEqualTo(Short value) {
            addCriterion("SENDPRIORITY <>", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityGreaterThan(Short value) {
            addCriterion("SENDPRIORITY >", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityGreaterThanOrEqualTo(Short value) {
            addCriterion("SENDPRIORITY >=", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityLessThan(Short value) {
            addCriterion("SENDPRIORITY <", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityLessThanOrEqualTo(Short value) {
            addCriterion("SENDPRIORITY <=", value, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityIn(List<Short> values) {
            addCriterion("SENDPRIORITY in", values, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityNotIn(List<Short> values) {
            addCriterion("SENDPRIORITY not in", values, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityBetween(Short value1, Short value2) {
            addCriterion("SENDPRIORITY between", value1, value2, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendpriorityNotBetween(Short value1, Short value2) {
            addCriterion("SENDPRIORITY not between", value1, value2, "sendpriority");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("SENDTIME is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("SENDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterionForJDBCDate("SENDTIME =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("SENDTIME <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("SENDTIME >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SENDTIME >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterionForJDBCDate("SENDTIME <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SENDTIME <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterionForJDBCDate("SENDTIME in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("SENDTIME not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SENDTIME between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SENDTIME not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andStatusreportIsNull() {
            addCriterion("STATUSREPORT is null");
            return (Criteria) this;
        }

        public Criteria andStatusreportIsNotNull() {
            addCriterion("STATUSREPORT is not null");
            return (Criteria) this;
        }

        public Criteria andStatusreportEqualTo(Short value) {
            addCriterion("STATUSREPORT =", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportNotEqualTo(Short value) {
            addCriterion("STATUSREPORT <>", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportGreaterThan(Short value) {
            addCriterion("STATUSREPORT >", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportGreaterThanOrEqualTo(Short value) {
            addCriterion("STATUSREPORT >=", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportLessThan(Short value) {
            addCriterion("STATUSREPORT <", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportLessThanOrEqualTo(Short value) {
            addCriterion("STATUSREPORT <=", value, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportIn(List<Short> values) {
            addCriterion("STATUSREPORT in", values, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportNotIn(List<Short> values) {
            addCriterion("STATUSREPORT not in", values, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportBetween(Short value1, Short value2) {
            addCriterion("STATUSREPORT between", value1, value2, "statusreport");
            return (Criteria) this;
        }

        public Criteria andStatusreportNotBetween(Short value1, Short value2) {
            addCriterion("STATUSREPORT not between", value1, value2, "statusreport");
            return (Criteria) this;
        }

        public Criteria andEnglishflagIsNull() {
            addCriterion("ENGLISHFLAG is null");
            return (Criteria) this;
        }

        public Criteria andEnglishflagIsNotNull() {
            addCriterion("ENGLISHFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishflagEqualTo(Short value) {
            addCriterion("ENGLISHFLAG =", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagNotEqualTo(Short value) {
            addCriterion("ENGLISHFLAG <>", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagGreaterThan(Short value) {
            addCriterion("ENGLISHFLAG >", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagGreaterThanOrEqualTo(Short value) {
            addCriterion("ENGLISHFLAG >=", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagLessThan(Short value) {
            addCriterion("ENGLISHFLAG <", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagLessThanOrEqualTo(Short value) {
            addCriterion("ENGLISHFLAG <=", value, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagIn(List<Short> values) {
            addCriterion("ENGLISHFLAG in", values, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagNotIn(List<Short> values) {
            addCriterion("ENGLISHFLAG not in", values, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagBetween(Short value1, Short value2) {
            addCriterion("ENGLISHFLAG between", value1, value2, "englishflag");
            return (Criteria) this;
        }

        public Criteria andEnglishflagNotBetween(Short value1, Short value2) {
            addCriterion("ENGLISHFLAG not between", value1, value2, "englishflag");
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

        public Criteria andPushurlIsNull() {
            addCriterion("PUSHURL is null");
            return (Criteria) this;
        }

        public Criteria andPushurlIsNotNull() {
            addCriterion("PUSHURL is not null");
            return (Criteria) this;
        }

        public Criteria andPushurlEqualTo(String value) {
            addCriterion("PUSHURL =", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlNotEqualTo(String value) {
            addCriterion("PUSHURL <>", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlGreaterThan(String value) {
            addCriterion("PUSHURL >", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlGreaterThanOrEqualTo(String value) {
            addCriterion("PUSHURL >=", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlLessThan(String value) {
            addCriterion("PUSHURL <", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlLessThanOrEqualTo(String value) {
            addCriterion("PUSHURL <=", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlLike(String value) {
            addCriterion("PUSHURL like", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlNotLike(String value) {
            addCriterion("PUSHURL not like", value, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlIn(List<String> values) {
            addCriterion("PUSHURL in", values, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlNotIn(List<String> values) {
            addCriterion("PUSHURL not in", values, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlBetween(String value1, String value2) {
            addCriterion("PUSHURL between", value1, value2, "pushurl");
            return (Criteria) this;
        }

        public Criteria andPushurlNotBetween(String value1, String value2) {
            addCriterion("PUSHURL not between", value1, value2, "pushurl");
            return (Criteria) this;
        }

        public Criteria andRecactionIsNull() {
            addCriterion("RECACTION is null");
            return (Criteria) this;
        }

        public Criteria andRecactionIsNotNull() {
            addCriterion("RECACTION is not null");
            return (Criteria) this;
        }

        public Criteria andRecactionEqualTo(Short value) {
            addCriterion("RECACTION =", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionNotEqualTo(Short value) {
            addCriterion("RECACTION <>", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionGreaterThan(Short value) {
            addCriterion("RECACTION >", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionGreaterThanOrEqualTo(Short value) {
            addCriterion("RECACTION >=", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionLessThan(Short value) {
            addCriterion("RECACTION <", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionLessThanOrEqualTo(Short value) {
            addCriterion("RECACTION <=", value, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionIn(List<Short> values) {
            addCriterion("RECACTION in", values, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionNotIn(List<Short> values) {
            addCriterion("RECACTION not in", values, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionBetween(Short value1, Short value2) {
            addCriterion("RECACTION between", value1, value2, "recaction");
            return (Criteria) this;
        }

        public Criteria andRecactionNotBetween(Short value1, Short value2) {
            addCriterion("RECACTION not between", value1, value2, "recaction");
            return (Criteria) this;
        }

        public Criteria andValidminuteIsNull() {
            addCriterion("VALIDMINUTE is null");
            return (Criteria) this;
        }

        public Criteria andValidminuteIsNotNull() {
            addCriterion("VALIDMINUTE is not null");
            return (Criteria) this;
        }

        public Criteria andValidminuteEqualTo(Short value) {
            addCriterion("VALIDMINUTE =", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteNotEqualTo(Short value) {
            addCriterion("VALIDMINUTE <>", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteGreaterThan(Short value) {
            addCriterion("VALIDMINUTE >", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteGreaterThanOrEqualTo(Short value) {
            addCriterion("VALIDMINUTE >=", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteLessThan(Short value) {
            addCriterion("VALIDMINUTE <", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteLessThanOrEqualTo(Short value) {
            addCriterion("VALIDMINUTE <=", value, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteIn(List<Short> values) {
            addCriterion("VALIDMINUTE in", values, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteNotIn(List<Short> values) {
            addCriterion("VALIDMINUTE not in", values, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteBetween(Short value1, Short value2) {
            addCriterion("VALIDMINUTE between", value1, value2, "validminute");
            return (Criteria) this;
        }

        public Criteria andValidminuteNotBetween(Short value1, Short value2) {
            addCriterion("VALIDMINUTE not between", value1, value2, "validminute");
            return (Criteria) this;
        }

        public Criteria andSendflagIsNull() {
            addCriterion("SENDFLAG is null");
            return (Criteria) this;
        }

        public Criteria andSendflagIsNotNull() {
            addCriterion("SENDFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSendflagEqualTo(Short value) {
            addCriterion("SENDFLAG =", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagNotEqualTo(Short value) {
            addCriterion("SENDFLAG <>", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagGreaterThan(Short value) {
            addCriterion("SENDFLAG >", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagGreaterThanOrEqualTo(Short value) {
            addCriterion("SENDFLAG >=", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagLessThan(Short value) {
            addCriterion("SENDFLAG <", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagLessThanOrEqualTo(Short value) {
            addCriterion("SENDFLAG <=", value, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagIn(List<Short> values) {
            addCriterion("SENDFLAG in", values, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagNotIn(List<Short> values) {
            addCriterion("SENDFLAG not in", values, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagBetween(Short value1, Short value2) {
            addCriterion("SENDFLAG between", value1, value2, "sendflag");
            return (Criteria) this;
        }

        public Criteria andSendflagNotBetween(Short value1, Short value2) {
            addCriterion("SENDFLAG not between", value1, value2, "sendflag");
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

        public Criteria andSplitcountIsNull() {
            addCriterion("SPLITCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSplitcountIsNotNull() {
            addCriterion("SPLITCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSplitcountEqualTo(Short value) {
            addCriterion("SPLITCOUNT =", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountNotEqualTo(Short value) {
            addCriterion("SPLITCOUNT <>", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountGreaterThan(Short value) {
            addCriterion("SPLITCOUNT >", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountGreaterThanOrEqualTo(Short value) {
            addCriterion("SPLITCOUNT >=", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountLessThan(Short value) {
            addCriterion("SPLITCOUNT <", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountLessThanOrEqualTo(Short value) {
            addCriterion("SPLITCOUNT <=", value, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountIn(List<Short> values) {
            addCriterion("SPLITCOUNT in", values, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountNotIn(List<Short> values) {
            addCriterion("SPLITCOUNT not in", values, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountBetween(Short value1, Short value2) {
            addCriterion("SPLITCOUNT between", value1, value2, "splitcount");
            return (Criteria) this;
        }

        public Criteria andSplitcountNotBetween(Short value1, Short value2) {
            addCriterion("SPLITCOUNT not between", value1, value2, "splitcount");
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

        public Criteria andRingflagIsNull() {
            addCriterion("RINGFLAG is null");
            return (Criteria) this;
        }

        public Criteria andRingflagIsNotNull() {
            addCriterion("RINGFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRingflagEqualTo(Short value) {
            addCriterion("RINGFLAG =", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagNotEqualTo(Short value) {
            addCriterion("RINGFLAG <>", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagGreaterThan(Short value) {
            addCriterion("RINGFLAG >", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagGreaterThanOrEqualTo(Short value) {
            addCriterion("RINGFLAG >=", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagLessThan(Short value) {
            addCriterion("RINGFLAG <", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagLessThanOrEqualTo(Short value) {
            addCriterion("RINGFLAG <=", value, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagIn(List<Short> values) {
            addCriterion("RINGFLAG in", values, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagNotIn(List<Short> values) {
            addCriterion("RINGFLAG not in", values, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagBetween(Short value1, Short value2) {
            addCriterion("RINGFLAG between", value1, value2, "ringflag");
            return (Criteria) this;
        }

        public Criteria andRingflagNotBetween(Short value1, Short value2) {
            addCriterion("RINGFLAG not between", value1, value2, "ringflag");
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