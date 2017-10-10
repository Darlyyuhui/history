package com.xiangxun.atms.module.check.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataCheckRuleSearch {
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
    public DataCheckRuleSearch() {
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

        public Criteria andCheckTypeIsNull() {
            addCriterion("CHECK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCheckTypeIsNotNull() {
            addCriterion("CHECK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTypeEqualTo(String value) {
            addCriterion("CHECK_TYPE =", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeNotEqualTo(String value) {
            addCriterion("CHECK_TYPE <>", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeGreaterThan(String value) {
            addCriterion("CHECK_TYPE >", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_TYPE >=", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeLessThan(String value) {
            addCriterion("CHECK_TYPE <", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeLessThanOrEqualTo(String value) {
            addCriterion("CHECK_TYPE <=", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeLike(String value) {
            addCriterion("CHECK_TYPE like", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeNotLike(String value) {
            addCriterion("CHECK_TYPE not like", value, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeIn(List<String> values) {
            addCriterion("CHECK_TYPE in", values, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeNotIn(List<String> values) {
            addCriterion("CHECK_TYPE not in", values, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeBetween(String value1, String value2) {
            addCriterion("CHECK_TYPE between", value1, value2, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckTypeNotBetween(String value1, String value2) {
            addCriterion("CHECK_TYPE not between", value1, value2, "checkType");
            return (Criteria) this;
        }

        public Criteria andCheckObjIsNull() {
            addCriterion("CHECK_OBJ is null");
            return (Criteria) this;
        }

        public Criteria andCheckObjIsNotNull() {
            addCriterion("CHECK_OBJ is not null");
            return (Criteria) this;
        }

        public Criteria andCheckObjEqualTo(String value) {
            addCriterion("CHECK_OBJ =", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjNotEqualTo(String value) {
            addCriterion("CHECK_OBJ <>", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjGreaterThan(String value) {
            addCriterion("CHECK_OBJ >", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_OBJ >=", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjLessThan(String value) {
            addCriterion("CHECK_OBJ <", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjLessThanOrEqualTo(String value) {
            addCriterion("CHECK_OBJ <=", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjLike(String value) {
            addCriterion("CHECK_OBJ like", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjNotLike(String value) {
            addCriterion("CHECK_OBJ not like", value, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjIn(List<String> values) {
            addCriterion("CHECK_OBJ in", values, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjNotIn(List<String> values) {
            addCriterion("CHECK_OBJ not in", values, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjBetween(String value1, String value2) {
            addCriterion("CHECK_OBJ between", value1, value2, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckObjNotBetween(String value1, String value2) {
            addCriterion("CHECK_OBJ not between", value1, value2, "checkObj");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionIsNull() {
            addCriterion("CHECK_DIMENSION is null");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionIsNotNull() {
            addCriterion("CHECK_DIMENSION is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionEqualTo(String value) {
            addCriterion("CHECK_DIMENSION =", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionNotEqualTo(String value) {
            addCriterion("CHECK_DIMENSION <>", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionGreaterThan(String value) {
            addCriterion("CHECK_DIMENSION >", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_DIMENSION >=", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionLessThan(String value) {
            addCriterion("CHECK_DIMENSION <", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionLessThanOrEqualTo(String value) {
            addCriterion("CHECK_DIMENSION <=", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionLike(String value) {
            addCriterion("CHECK_DIMENSION like", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionNotLike(String value) {
            addCriterion("CHECK_DIMENSION not like", value, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionIn(List<String> values) {
            addCriterion("CHECK_DIMENSION in", values, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionNotIn(List<String> values) {
            addCriterion("CHECK_DIMENSION not in", values, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionBetween(String value1, String value2) {
            addCriterion("CHECK_DIMENSION between", value1, value2, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andCheckDimensionNotBetween(String value1, String value2) {
            addCriterion("CHECK_DIMENSION not between", value1, value2, "checkDimension");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterIsNull() {
            addCriterion("OUTLIER_PARAMETER is null");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterIsNotNull() {
            addCriterion("OUTLIER_PARAMETER is not null");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterEqualTo(Integer value) {
            addCriterion("OUTLIER_PARAMETER =", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterNotEqualTo(Integer value) {
            addCriterion("OUTLIER_PARAMETER <>", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterGreaterThan(Integer value) {
            addCriterion("OUTLIER_PARAMETER >", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterGreaterThanOrEqualTo(Integer value) {
            addCriterion("OUTLIER_PARAMETER >=", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterLessThan(Integer value) {
            addCriterion("OUTLIER_PARAMETER <", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterLessThanOrEqualTo(Integer value) {
            addCriterion("OUTLIER_PARAMETER <=", value, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterIn(List<Integer> values) {
            addCriterion("OUTLIER_PARAMETER in", values, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterNotIn(List<Integer> values) {
            addCriterion("OUTLIER_PARAMETER not in", values, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterBetween(Integer value1, Integer value2) {
            addCriterion("OUTLIER_PARAMETER between", value1, value2, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierParameterNotBetween(Integer value1, Integer value2) {
            addCriterion("OUTLIER_PARAMETER not between", value1, value2, "outlierParameter");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkIsNull() {
            addCriterion("OUTLIER_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkIsNotNull() {
            addCriterion("OUTLIER_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkEqualTo(String value) {
            addCriterion("OUTLIER_REMARK =", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkNotEqualTo(String value) {
            addCriterion("OUTLIER_REMARK <>", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkGreaterThan(String value) {
            addCriterion("OUTLIER_REMARK >", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("OUTLIER_REMARK >=", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkLessThan(String value) {
            addCriterion("OUTLIER_REMARK <", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkLessThanOrEqualTo(String value) {
            addCriterion("OUTLIER_REMARK <=", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkLike(String value) {
            addCriterion("OUTLIER_REMARK like", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkNotLike(String value) {
            addCriterion("OUTLIER_REMARK not like", value, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkIn(List<String> values) {
            addCriterion("OUTLIER_REMARK in", values, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkNotIn(List<String> values) {
            addCriterion("OUTLIER_REMARK not in", values, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkBetween(String value1, String value2) {
            addCriterion("OUTLIER_REMARK between", value1, value2, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andOutlierRemarkNotBetween(String value1, String value2) {
            addCriterion("OUTLIER_REMARK not between", value1, value2, "outlierRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterIsNull() {
            addCriterion("INVALID_PARAMETER is null");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterIsNotNull() {
            addCriterion("INVALID_PARAMETER is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterEqualTo(Integer value) {
            addCriterion("INVALID_PARAMETER =", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterNotEqualTo(Integer value) {
            addCriterion("INVALID_PARAMETER <>", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterGreaterThan(Integer value) {
            addCriterion("INVALID_PARAMETER >", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterGreaterThanOrEqualTo(Integer value) {
            addCriterion("INVALID_PARAMETER >=", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterLessThan(Integer value) {
            addCriterion("INVALID_PARAMETER <", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterLessThanOrEqualTo(Integer value) {
            addCriterion("INVALID_PARAMETER <=", value, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterIn(List<Integer> values) {
            addCriterion("INVALID_PARAMETER in", values, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterNotIn(List<Integer> values) {
            addCriterion("INVALID_PARAMETER not in", values, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterBetween(Integer value1, Integer value2) {
            addCriterion("INVALID_PARAMETER between", value1, value2, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidParameterNotBetween(Integer value1, Integer value2) {
            addCriterion("INVALID_PARAMETER not between", value1, value2, "invalidParameter");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkIsNull() {
            addCriterion("INVALID_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkIsNotNull() {
            addCriterion("INVALID_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkEqualTo(String value) {
            addCriterion("INVALID_REMARK =", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkNotEqualTo(String value) {
            addCriterion("INVALID_REMARK <>", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkGreaterThan(String value) {
            addCriterion("INVALID_REMARK >", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("INVALID_REMARK >=", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkLessThan(String value) {
            addCriterion("INVALID_REMARK <", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkLessThanOrEqualTo(String value) {
            addCriterion("INVALID_REMARK <=", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkLike(String value) {
            addCriterion("INVALID_REMARK like", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkNotLike(String value) {
            addCriterion("INVALID_REMARK not like", value, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkIn(List<String> values) {
            addCriterion("INVALID_REMARK in", values, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkNotIn(List<String> values) {
            addCriterion("INVALID_REMARK not in", values, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkBetween(String value1, String value2) {
            addCriterion("INVALID_REMARK between", value1, value2, "invalidRemark");
            return (Criteria) this;
        }

        public Criteria andInvalidRemarkNotBetween(String value1, String value2) {
            addCriterion("INVALID_REMARK not between", value1, value2, "invalidRemark");
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
    }

    /**
    T_DATACHECK_RULE
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