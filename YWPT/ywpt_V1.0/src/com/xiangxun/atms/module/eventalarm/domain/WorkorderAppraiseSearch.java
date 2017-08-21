package com.xiangxun.atms.module.eventalarm.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WorkorderAppraiseSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkorderAppraiseSearch() {
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

        public Criteria andWorkorderidIsNull() {
            addCriterion("WORKORDERID is null");
            return (Criteria) this;
        }

        public Criteria andWorkorderidIsNotNull() {
            addCriterion("WORKORDERID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkorderidEqualTo(String value) {
            addCriterion("WORKORDERID =", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotEqualTo(String value) {
            addCriterion("WORKORDERID <>", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidGreaterThan(String value) {
            addCriterion("WORKORDERID >", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidGreaterThanOrEqualTo(String value) {
            addCriterion("WORKORDERID >=", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLessThan(String value) {
            addCriterion("WORKORDERID <", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLessThanOrEqualTo(String value) {
            addCriterion("WORKORDERID <=", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLike(String value) {
            addCriterion("WORKORDERID like", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotLike(String value) {
            addCriterion("WORKORDERID not like", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidIn(List<String> values) {
            addCriterion("WORKORDERID in", values, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotIn(List<String> values) {
            addCriterion("WORKORDERID not in", values, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidBetween(String value1, String value2) {
            addCriterion("WORKORDERID between", value1, value2, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotBetween(String value1, String value2) {
            addCriterion("WORKORDERID not between", value1, value2, "workorderid");
            return (Criteria) this;
        }

        public Criteria andContactnameIsNull() {
            addCriterion("CONTACTNAME is null");
            return (Criteria) this;
        }

        public Criteria andContactnameIsNotNull() {
            addCriterion("CONTACTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactnameEqualTo(String value) {
            addCriterion("CONTACTNAME =", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameNotEqualTo(String value) {
            addCriterion("CONTACTNAME <>", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameGreaterThan(String value) {
            addCriterion("CONTACTNAME >", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACTNAME >=", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameLessThan(String value) {
            addCriterion("CONTACTNAME <", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameLessThanOrEqualTo(String value) {
            addCriterion("CONTACTNAME <=", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameLike(String value) {
            addCriterion("CONTACTNAME like", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameNotLike(String value) {
            addCriterion("CONTACTNAME not like", value, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameIn(List<String> values) {
            addCriterion("CONTACTNAME in", values, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameNotIn(List<String> values) {
            addCriterion("CONTACTNAME not in", values, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameBetween(String value1, String value2) {
            addCriterion("CONTACTNAME between", value1, value2, "contactname");
            return (Criteria) this;
        }

        public Criteria andContactnameNotBetween(String value1, String value2) {
            addCriterion("CONTACTNAME not between", value1, value2, "contactname");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andViolateruleIsNull() {
            addCriterion("VIOLATERULE is null");
            return (Criteria) this;
        }

        public Criteria andViolateruleIsNotNull() {
            addCriterion("VIOLATERULE is not null");
            return (Criteria) this;
        }

        public Criteria andViolateruleEqualTo(String value) {
            addCriterion("VIOLATERULE =", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleNotEqualTo(String value) {
            addCriterion("VIOLATERULE <>", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleGreaterThan(String value) {
            addCriterion("VIOLATERULE >", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleGreaterThanOrEqualTo(String value) {
            addCriterion("VIOLATERULE >=", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleLessThan(String value) {
            addCriterion("VIOLATERULE <", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleLessThanOrEqualTo(String value) {
            addCriterion("VIOLATERULE <=", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleLike(String value) {
            addCriterion("VIOLATERULE like", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleNotLike(String value) {
            addCriterion("VIOLATERULE not like", value, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleIn(List<String> values) {
            addCriterion("VIOLATERULE in", values, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleNotIn(List<String> values) {
            addCriterion("VIOLATERULE not in", values, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleBetween(String value1, String value2) {
            addCriterion("VIOLATERULE between", value1, value2, "violaterule");
            return (Criteria) this;
        }

        public Criteria andViolateruleNotBetween(String value1, String value2) {
            addCriterion("VIOLATERULE not between", value1, value2, "violaterule");
            return (Criteria) this;
        }

        public Criteria andDeductscoreIsNull() {
            addCriterion("DEDUCTSCORE is null");
            return (Criteria) this;
        }

        public Criteria andDeductscoreIsNotNull() {
            addCriterion("DEDUCTSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andDeductscoreEqualTo(BigDecimal value) {
            addCriterion("DEDUCTSCORE =", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreNotEqualTo(BigDecimal value) {
            addCriterion("DEDUCTSCORE <>", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreGreaterThan(BigDecimal value) {
            addCriterion("DEDUCTSCORE >", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DEDUCTSCORE >=", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreLessThan(BigDecimal value) {
            addCriterion("DEDUCTSCORE <", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DEDUCTSCORE <=", value, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreIn(List<BigDecimal> values) {
            addCriterion("DEDUCTSCORE in", values, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreNotIn(List<BigDecimal> values) {
            addCriterion("DEDUCTSCORE not in", values, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEDUCTSCORE between", value1, value2, "deductscore");
            return (Criteria) this;
        }

        public Criteria andDeductscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEDUCTSCORE not between", value1, value2, "deductscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreIsNull() {
            addCriterion("FINALSCORE is null");
            return (Criteria) this;
        }

        public Criteria andFinalscoreIsNotNull() {
            addCriterion("FINALSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFinalscoreEqualTo(BigDecimal value) {
            addCriterion("FINALSCORE =", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreNotEqualTo(BigDecimal value) {
            addCriterion("FINALSCORE <>", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreGreaterThan(BigDecimal value) {
            addCriterion("FINALSCORE >", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FINALSCORE >=", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreLessThan(BigDecimal value) {
            addCriterion("FINALSCORE <", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FINALSCORE <=", value, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreIn(List<BigDecimal> values) {
            addCriterion("FINALSCORE in", values, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreNotIn(List<BigDecimal> values) {
            addCriterion("FINALSCORE not in", values, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FINALSCORE between", value1, value2, "finalscore");
            return (Criteria) this;
        }

        public Criteria andFinalscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FINALSCORE not between", value1, value2, "finalscore");
            return (Criteria) this;
        }

        public Criteria andAppraiserIsNull() {
            addCriterion("APPRAISER is null");
            return (Criteria) this;
        }

        public Criteria andAppraiserIsNotNull() {
            addCriterion("APPRAISER is not null");
            return (Criteria) this;
        }

        public Criteria andAppraiserEqualTo(String value) {
            addCriterion("APPRAISER =", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserNotEqualTo(String value) {
            addCriterion("APPRAISER <>", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserGreaterThan(String value) {
            addCriterion("APPRAISER >", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISER >=", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserLessThan(String value) {
            addCriterion("APPRAISER <", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserLessThanOrEqualTo(String value) {
            addCriterion("APPRAISER <=", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserLike(String value) {
            addCriterion("APPRAISER like", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserNotLike(String value) {
            addCriterion("APPRAISER not like", value, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserIn(List<String> values) {
            addCriterion("APPRAISER in", values, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserNotIn(List<String> values) {
            addCriterion("APPRAISER not in", values, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserBetween(String value1, String value2) {
            addCriterion("APPRAISER between", value1, value2, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraiserNotBetween(String value1, String value2) {
            addCriterion("APPRAISER not between", value1, value2, "appraiser");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeIsNull() {
            addCriterion("APPRAISETIME is null");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeIsNotNull() {
            addCriterion("APPRAISETIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeEqualTo(Date value) {
            addCriterionForJDBCDate("APPRAISETIME =", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("APPRAISETIME <>", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("APPRAISETIME >", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPRAISETIME >=", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeLessThan(Date value) {
            addCriterionForJDBCDate("APPRAISETIME <", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPRAISETIME <=", value, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeIn(List<Date> values) {
            addCriterionForJDBCDate("APPRAISETIME in", values, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("APPRAISETIME not in", values, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPRAISETIME between", value1, value2, "appraisetime");
            return (Criteria) this;
        }

        public Criteria andAppraisetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPRAISETIME not between", value1, value2, "appraisetime");
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