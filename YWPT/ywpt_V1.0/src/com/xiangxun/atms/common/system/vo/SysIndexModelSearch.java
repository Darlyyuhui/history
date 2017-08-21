package com.xiangxun.atms.common.system.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysIndexModelSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysIndexModelSearch() {
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

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcIsNull() {
            addCriterion("HTMLSRC is null");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcIsNotNull() {
            addCriterion("HTMLSRC is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcEqualTo(String value) {
            addCriterion("HTMLSRC =", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcNotEqualTo(String value) {
            addCriterion("HTMLSRC <>", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcGreaterThan(String value) {
            addCriterion("HTMLSRC >", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcGreaterThanOrEqualTo(String value) {
            addCriterion("HTMLSRC >=", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcLessThan(String value) {
            addCriterion("HTMLSRC <", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcLessThanOrEqualTo(String value) {
            addCriterion("HTMLSRC <=", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcLike(String value) {
            addCriterion("HTMLSRC like", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcNotLike(String value) {
            addCriterion("HTMLSRC not like", value, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcIn(List<String> values) {
            addCriterion("HTMLSRC in", values, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcNotIn(List<String> values) {
            addCriterion("HTMLSRC not in", values, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcBetween(String value1, String value2) {
            addCriterion("HTMLSRC between", value1, value2, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andHtmlsrcNotBetween(String value1, String value2) {
            addCriterion("HTMLSRC not between", value1, value2, "htmlsrc");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andRowcountIsNull() {
            addCriterion("ROWCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRowcountIsNotNull() {
            addCriterion("ROWCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRowcountEqualTo(BigDecimal value) {
            addCriterion("ROWCOUNT =", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountNotEqualTo(BigDecimal value) {
            addCriterion("ROWCOUNT <>", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountGreaterThan(BigDecimal value) {
            addCriterion("ROWCOUNT >", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ROWCOUNT >=", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountLessThan(BigDecimal value) {
            addCriterion("ROWCOUNT <", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ROWCOUNT <=", value, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountIn(List<BigDecimal> values) {
            addCriterion("ROWCOUNT in", values, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountNotIn(List<BigDecimal> values) {
            addCriterion("ROWCOUNT not in", values, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ROWCOUNT between", value1, value2, "rowcount");
            return (Criteria) this;
        }

        public Criteria andRowcountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ROWCOUNT not between", value1, value2, "rowcount");
            return (Criteria) this;
        }

        public Criteria andLayoutIsNull() {
            addCriterion("LAYOUT is null");
            return (Criteria) this;
        }

        public Criteria andLayoutIsNotNull() {
            addCriterion("LAYOUT is not null");
            return (Criteria) this;
        }

        public Criteria andLayoutEqualTo(String value) {
            addCriterion("LAYOUT =", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotEqualTo(String value) {
            addCriterion("LAYOUT <>", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutGreaterThan(String value) {
            addCriterion("LAYOUT >", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutGreaterThanOrEqualTo(String value) {
            addCriterion("LAYOUT >=", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLessThan(String value) {
            addCriterion("LAYOUT <", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLessThanOrEqualTo(String value) {
            addCriterion("LAYOUT <=", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLike(String value) {
            addCriterion("LAYOUT like", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotLike(String value) {
            addCriterion("LAYOUT not like", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutIn(List<String> values) {
            addCriterion("LAYOUT in", values, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotIn(List<String> values) {
            addCriterion("LAYOUT not in", values, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutBetween(String value1, String value2) {
            addCriterion("LAYOUT between", value1, value2, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotBetween(String value1, String value2) {
            addCriterion("LAYOUT not between", value1, value2, "layout");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("ISSHOW is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("ISSHOW is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(String value) {
            addCriterion("ISSHOW =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(String value) {
            addCriterion("ISSHOW <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(String value) {
            addCriterion("ISSHOW >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(String value) {
            addCriterion("ISSHOW >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(String value) {
            addCriterion("ISSHOW <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(String value) {
            addCriterion("ISSHOW <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLike(String value) {
            addCriterion("ISSHOW like", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotLike(String value) {
            addCriterion("ISSHOW not like", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<String> values) {
            addCriterion("ISSHOW in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<String> values) {
            addCriterion("ISSHOW not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(String value1, String value2) {
            addCriterion("ISSHOW between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(String value1, String value2) {
            addCriterion("ISSHOW not between", value1, value2, "isshow");
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