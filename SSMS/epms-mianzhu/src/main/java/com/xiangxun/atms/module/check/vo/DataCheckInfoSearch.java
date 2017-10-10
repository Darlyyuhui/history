package com.xiangxun.atms.module.check.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataCheckInfoSearch {
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
    public DataCheckInfoSearch() {
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

        public Criteria andSchemeIdIsNull() {
            addCriterion("SCHEME_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchemeIdIsNotNull() {
            addCriterion("SCHEME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeIdEqualTo(String value) {
            addCriterion("SCHEME_ID =", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdNotEqualTo(String value) {
            addCriterion("SCHEME_ID <>", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdGreaterThan(String value) {
            addCriterion("SCHEME_ID >", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_ID >=", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdLessThan(String value) {
            addCriterion("SCHEME_ID <", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_ID <=", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdLike(String value) {
            addCriterion("SCHEME_ID like", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdNotLike(String value) {
            addCriterion("SCHEME_ID not like", value, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdIn(List<String> values) {
            addCriterion("SCHEME_ID in", values, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdNotIn(List<String> values) {
            addCriterion("SCHEME_ID not in", values, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdBetween(String value1, String value2) {
            addCriterion("SCHEME_ID between", value1, value2, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeIdNotBetween(String value1, String value2) {
            addCriterion("SCHEME_ID not between", value1, value2, "schemeId");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNull() {
            addCriterion("SCHEME_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNotNull() {
            addCriterion("SCHEME_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameEqualTo(String value) {
            addCriterion("SCHEME_NAME =", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotEqualTo(String value) {
            addCriterion("SCHEME_NAME <>", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThan(String value) {
            addCriterion("SCHEME_NAME >", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME >=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThan(String value) {
            addCriterion("SCHEME_NAME <", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME <=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLike(String value) {
            addCriterion("SCHEME_NAME like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotLike(String value) {
            addCriterion("SCHEME_NAME not like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIn(List<String> values) {
            addCriterion("SCHEME_NAME in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotIn(List<String> values) {
            addCriterion("SCHEME_NAME not in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME not between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("REGION_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("REGION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(String value) {
            addCriterion("REGION_ID =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(String value) {
            addCriterion("REGION_ID <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(String value) {
            addCriterion("REGION_ID >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGION_ID >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(String value) {
            addCriterion("REGION_ID <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(String value) {
            addCriterion("REGION_ID <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLike(String value) {
            addCriterion("REGION_ID like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotLike(String value) {
            addCriterion("REGION_ID not like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<String> values) {
            addCriterion("REGION_ID in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<String> values) {
            addCriterion("REGION_ID not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(String value1, String value2) {
            addCriterion("REGION_ID between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(String value1, String value2) {
            addCriterion("REGION_ID not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNull() {
            addCriterion("SAMPLE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNotNull() {
            addCriterion("SAMPLE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeEqualTo(String value) {
            addCriterion("SAMPLE_TYPE =", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotEqualTo(String value) {
            addCriterion("SAMPLE_TYPE <>", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThan(String value) {
            addCriterion("SAMPLE_TYPE >", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SAMPLE_TYPE >=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThan(String value) {
            addCriterion("SAMPLE_TYPE <", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThanOrEqualTo(String value) {
            addCriterion("SAMPLE_TYPE <=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLike(String value) {
            addCriterion("SAMPLE_TYPE like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotLike(String value) {
            addCriterion("SAMPLE_TYPE not like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIn(List<String> values) {
            addCriterion("SAMPLE_TYPE in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotIn(List<String> values) {
            addCriterion("SAMPLE_TYPE not in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeBetween(String value1, String value2) {
            addCriterion("SAMPLE_TYPE between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotBetween(String value1, String value2) {
            addCriterion("SAMPLE_TYPE not between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptIsNull() {
            addCriterion("SAMPLING_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptIsNotNull() {
            addCriterion("SAMPLING_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptEqualTo(String value) {
            addCriterion("SAMPLING_DEPT =", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptNotEqualTo(String value) {
            addCriterion("SAMPLING_DEPT <>", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptGreaterThan(String value) {
            addCriterion("SAMPLING_DEPT >", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptGreaterThanOrEqualTo(String value) {
            addCriterion("SAMPLING_DEPT >=", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptLessThan(String value) {
            addCriterion("SAMPLING_DEPT <", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptLessThanOrEqualTo(String value) {
            addCriterion("SAMPLING_DEPT <=", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptLike(String value) {
            addCriterion("SAMPLING_DEPT like", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptNotLike(String value) {
            addCriterion("SAMPLING_DEPT not like", value, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptIn(List<String> values) {
            addCriterion("SAMPLING_DEPT in", values, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptNotIn(List<String> values) {
            addCriterion("SAMPLING_DEPT not in", values, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptBetween(String value1, String value2) {
            addCriterion("SAMPLING_DEPT between", value1, value2, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andSamplingDeptNotBetween(String value1, String value2) {
            addCriterion("SAMPLING_DEPT not between", value1, value2, "samplingDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptIsNull() {
            addCriterion("ANALYSIS_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptIsNotNull() {
            addCriterion("ANALYSIS_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptEqualTo(String value) {
            addCriterion("ANALYSIS_DEPT =", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptNotEqualTo(String value) {
            addCriterion("ANALYSIS_DEPT <>", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptGreaterThan(String value) {
            addCriterion("ANALYSIS_DEPT >", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptGreaterThanOrEqualTo(String value) {
            addCriterion("ANALYSIS_DEPT >=", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptLessThan(String value) {
            addCriterion("ANALYSIS_DEPT <", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptLessThanOrEqualTo(String value) {
            addCriterion("ANALYSIS_DEPT <=", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptLike(String value) {
            addCriterion("ANALYSIS_DEPT like", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptNotLike(String value) {
            addCriterion("ANALYSIS_DEPT not like", value, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptIn(List<String> values) {
            addCriterion("ANALYSIS_DEPT in", values, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptNotIn(List<String> values) {
            addCriterion("ANALYSIS_DEPT not in", values, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptBetween(String value1, String value2) {
            addCriterion("ANALYSIS_DEPT between", value1, value2, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisDeptNotBetween(String value1, String value2) {
            addCriterion("ANALYSIS_DEPT not between", value1, value2, "analysisDept");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountIsNull() {
            addCriterion("ANALYSIS_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountIsNotNull() {
            addCriterion("ANALYSIS_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountEqualTo(String value) {
            addCriterion("ANALYSIS_COUNT =", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountNotEqualTo(String value) {
            addCriterion("ANALYSIS_COUNT <>", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountGreaterThan(String value) {
            addCriterion("ANALYSIS_COUNT >", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountGreaterThanOrEqualTo(String value) {
            addCriterion("ANALYSIS_COUNT >=", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountLessThan(String value) {
            addCriterion("ANALYSIS_COUNT <", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountLessThanOrEqualTo(String value) {
            addCriterion("ANALYSIS_COUNT <=", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountLike(String value) {
            addCriterion("ANALYSIS_COUNT like", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountNotLike(String value) {
            addCriterion("ANALYSIS_COUNT not like", value, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountIn(List<String> values) {
            addCriterion("ANALYSIS_COUNT in", values, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountNotIn(List<String> values) {
            addCriterion("ANALYSIS_COUNT not in", values, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountBetween(String value1, String value2) {
            addCriterion("ANALYSIS_COUNT between", value1, value2, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andAnalysisCountNotBetween(String value1, String value2) {
            addCriterion("ANALYSIS_COUNT not between", value1, value2, "analysisCount");
            return (Criteria) this;
        }

        public Criteria andTestItemsIsNull() {
            addCriterion("TEST_ITEMS is null");
            return (Criteria) this;
        }

        public Criteria andTestItemsIsNotNull() {
            addCriterion("TEST_ITEMS is not null");
            return (Criteria) this;
        }

        public Criteria andTestItemsEqualTo(String value) {
            addCriterion("TEST_ITEMS =", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsNotEqualTo(String value) {
            addCriterion("TEST_ITEMS <>", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsGreaterThan(String value) {
            addCriterion("TEST_ITEMS >", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_ITEMS >=", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsLessThan(String value) {
            addCriterion("TEST_ITEMS <", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsLessThanOrEqualTo(String value) {
            addCriterion("TEST_ITEMS <=", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsLike(String value) {
            addCriterion("TEST_ITEMS like", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsNotLike(String value) {
            addCriterion("TEST_ITEMS not like", value, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsIn(List<String> values) {
            addCriterion("TEST_ITEMS in", values, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsNotIn(List<String> values) {
            addCriterion("TEST_ITEMS not in", values, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsBetween(String value1, String value2) {
            addCriterion("TEST_ITEMS between", value1, value2, "testItems");
            return (Criteria) this;
        }

        public Criteria andTestItemsNotBetween(String value1, String value2) {
            addCriterion("TEST_ITEMS not between", value1, value2, "testItems");
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }
    }

    /**
    T_DATACHECK_INFO
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