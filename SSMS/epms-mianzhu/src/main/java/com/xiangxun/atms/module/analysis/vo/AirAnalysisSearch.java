package com.xiangxun.atms.module.analysis.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AirAnalysisSearch {
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
    public AirAnalysisSearch() {
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

        public Criteria andCadmiumIsNull() {
            addCriterion("CADMIUM is null");
            return (Criteria) this;
        }

        public Criteria andCadmiumIsNotNull() {
            addCriterion("CADMIUM is not null");
            return (Criteria) this;
        }

        public Criteria andCadmiumEqualTo(BigDecimal value) {
            addCriterion("CADMIUM =", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumNotEqualTo(BigDecimal value) {
            addCriterion("CADMIUM <>", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumGreaterThan(BigDecimal value) {
            addCriterion("CADMIUM >", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CADMIUM >=", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumLessThan(BigDecimal value) {
            addCriterion("CADMIUM <", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CADMIUM <=", value, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumIn(List<BigDecimal> values) {
            addCriterion("CADMIUM in", values, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumNotIn(List<BigDecimal> values) {
            addCriterion("CADMIUM not in", values, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CADMIUM between", value1, value2, "cadmium");
            return (Criteria) this;
        }

        public Criteria andCadmiumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CADMIUM not between", value1, value2, "cadmium");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNull() {
            addCriterion("IS_OVER is null");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNotNull() {
            addCriterion("IS_OVER is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverEqualTo(Integer value) {
            addCriterion("IS_OVER =", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotEqualTo(Integer value) {
            addCriterion("IS_OVER <>", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThan(Integer value) {
            addCriterion("IS_OVER >", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_OVER >=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThan(Integer value) {
            addCriterion("IS_OVER <", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThanOrEqualTo(Integer value) {
            addCriterion("IS_OVER <=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverIn(List<Integer> values) {
            addCriterion("IS_OVER in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotIn(List<Integer> values) {
            addCriterion("IS_OVER not in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverBetween(Integer value1, Integer value2) {
            addCriterion("IS_OVER between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_OVER not between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxIsNull() {
            addCriterion("POLLUTE_FLUX is null");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxIsNotNull() {
            addCriterion("POLLUTE_FLUX is not null");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxEqualTo(String value) {
            addCriterion("POLLUTE_FLUX =", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxNotEqualTo(String value) {
            addCriterion("POLLUTE_FLUX <>", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxGreaterThan(String value) {
            addCriterion("POLLUTE_FLUX >", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxGreaterThanOrEqualTo(String value) {
            addCriterion("POLLUTE_FLUX >=", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxLessThan(String value) {
            addCriterion("POLLUTE_FLUX <", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxLessThanOrEqualTo(String value) {
            addCriterion("POLLUTE_FLUX <=", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxLike(String value) {
            addCriterion("POLLUTE_FLUX like", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxNotLike(String value) {
            addCriterion("POLLUTE_FLUX not like", value, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxIn(List<String> values) {
            addCriterion("POLLUTE_FLUX in", values, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxNotIn(List<String> values) {
            addCriterion("POLLUTE_FLUX not in", values, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxBetween(String value1, String value2) {
            addCriterion("POLLUTE_FLUX between", value1, value2, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andPolluteFluxNotBetween(String value1, String value2) {
            addCriterion("POLLUTE_FLUX not between", value1, value2, "polluteFlux");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("CREATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("CREATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(String value) {
            addCriterion("CREATE_ID =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(String value) {
            addCriterion("CREATE_ID <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(String value) {
            addCriterion("CREATE_ID >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_ID >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(String value) {
            addCriterion("CREATE_ID <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_ID <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLike(String value) {
            addCriterion("CREATE_ID like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotLike(String value) {
            addCriterion("CREATE_ID not like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<String> values) {
            addCriterion("CREATE_ID in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<String> values) {
            addCriterion("CREATE_ID not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(String value1, String value2) {
            addCriterion("CREATE_ID between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_ID not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNull() {
            addCriterion("UPDATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNotNull() {
            addCriterion("UPDATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdEqualTo(String value) {
            addCriterion("UPDATE_ID =", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotEqualTo(String value) {
            addCriterion("UPDATE_ID <>", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThan(String value) {
            addCriterion("UPDATE_ID >", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_ID >=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThan(String value) {
            addCriterion("UPDATE_ID <", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_ID <=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLike(String value) {
            addCriterion("UPDATE_ID like", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotLike(String value) {
            addCriterion("UPDATE_ID not like", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIn(List<String> values) {
            addCriterion("UPDATE_ID in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotIn(List<String> values) {
            addCriterion("UPDATE_ID not in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdBetween(String value1, String value2) {
            addCriterion("UPDATE_ID between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotBetween(String value1, String value2) {
            addCriterion("UPDATE_ID not between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDeptIsNull() {
            addCriterion("DEPT is null");
            return (Criteria) this;
        }

        public Criteria andDeptIsNotNull() {
            addCriterion("DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andDeptEqualTo(String value) {
            addCriterion("DEPT =", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotEqualTo(String value) {
            addCriterion("DEPT <>", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThan(String value) {
            addCriterion("DEPT >", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT >=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThan(String value) {
            addCriterion("DEPT <", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThanOrEqualTo(String value) {
            addCriterion("DEPT <=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLike(String value) {
            addCriterion("DEPT like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotLike(String value) {
            addCriterion("DEPT not like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptIn(List<String> values) {
            addCriterion("DEPT in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotIn(List<String> values) {
            addCriterion("DEPT not in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptBetween(String value1, String value2) {
            addCriterion("DEPT between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotBetween(String value1, String value2) {
            addCriterion("DEPT not between", value1, value2, "dept");
            return (Criteria) this;
        }
    }

    /**
    T_ANALYSIS_AIR
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