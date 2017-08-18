package com.xiangxun.atms.module.land.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LandSamplingSchemePointSearch {
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
    public LandSamplingSchemePointSearch() {
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

        public Criteria andLongitudeIsNull() {
            addCriterion("LONGITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("LONGITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("LONGITUDE =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("LONGITUDE <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("LONGITUDE >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("LONGITUDE >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("LONGITUDE <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("LONGITUDE <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("LONGITUDE like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("LONGITUDE not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("LONGITUDE in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("LONGITUDE not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("LONGITUDE between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("LONGITUDE not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("LATITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("LATITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("LATITUDE =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("LATITUDE <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("LATITUDE >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("LATITUDE >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("LATITUDE <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("LATITUDE <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("LATITUDE like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("LATITUDE not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("LATITUDE in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("LATITUDE not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("LATITUDE between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("LATITUDE not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andRangeXIsNull() {
            addCriterion("RANGE_X is null");
            return (Criteria) this;
        }

        public Criteria andRangeXIsNotNull() {
            addCriterion("RANGE_X is not null");
            return (Criteria) this;
        }

        public Criteria andRangeXEqualTo(String value) {
            addCriterion("RANGE_X =", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXNotEqualTo(String value) {
            addCriterion("RANGE_X <>", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXGreaterThan(String value) {
            addCriterion("RANGE_X >", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXGreaterThanOrEqualTo(String value) {
            addCriterion("RANGE_X >=", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXLessThan(String value) {
            addCriterion("RANGE_X <", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXLessThanOrEqualTo(String value) {
            addCriterion("RANGE_X <=", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXLike(String value) {
            addCriterion("RANGE_X like", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXNotLike(String value) {
            addCriterion("RANGE_X not like", value, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXIn(List<String> values) {
            addCriterion("RANGE_X in", values, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXNotIn(List<String> values) {
            addCriterion("RANGE_X not in", values, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXBetween(String value1, String value2) {
            addCriterion("RANGE_X between", value1, value2, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeXNotBetween(String value1, String value2) {
            addCriterion("RANGE_X not between", value1, value2, "rangeX");
            return (Criteria) this;
        }

        public Criteria andRangeYIsNull() {
            addCriterion("RANGE_Y is null");
            return (Criteria) this;
        }

        public Criteria andRangeYIsNotNull() {
            addCriterion("RANGE_Y is not null");
            return (Criteria) this;
        }

        public Criteria andRangeYEqualTo(String value) {
            addCriterion("RANGE_Y =", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYNotEqualTo(String value) {
            addCriterion("RANGE_Y <>", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYGreaterThan(String value) {
            addCriterion("RANGE_Y >", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYGreaterThanOrEqualTo(String value) {
            addCriterion("RANGE_Y >=", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYLessThan(String value) {
            addCriterion("RANGE_Y <", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYLessThanOrEqualTo(String value) {
            addCriterion("RANGE_Y <=", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYLike(String value) {
            addCriterion("RANGE_Y like", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYNotLike(String value) {
            addCriterion("RANGE_Y not like", value, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYIn(List<String> values) {
            addCriterion("RANGE_Y in", values, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYNotIn(List<String> values) {
            addCriterion("RANGE_Y not in", values, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYBetween(String value1, String value2) {
            addCriterion("RANGE_Y between", value1, value2, "rangeY");
            return (Criteria) this;
        }

        public Criteria andRangeYNotBetween(String value1, String value2) {
            addCriterion("RANGE_Y not between", value1, value2, "rangeY");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("AREA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("AREA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("AREA_CODE =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("AREA_CODE <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("AREA_CODE >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_CODE >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("AREA_CODE <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("AREA_CODE <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("AREA_CODE like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("AREA_CODE not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("AREA_CODE in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("AREA_CODE not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("AREA_CODE between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("AREA_CODE not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointIsNull() {
            addCriterion("IS_SAMPLING_POINT is null");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointIsNotNull() {
            addCriterion("IS_SAMPLING_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointEqualTo(Integer value) {
            addCriterion("IS_SAMPLING_POINT =", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointNotEqualTo(Integer value) {
            addCriterion("IS_SAMPLING_POINT <>", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointGreaterThan(Integer value) {
            addCriterion("IS_SAMPLING_POINT >", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SAMPLING_POINT >=", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointLessThan(Integer value) {
            addCriterion("IS_SAMPLING_POINT <", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SAMPLING_POINT <=", value, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointIn(List<Integer> values) {
            addCriterion("IS_SAMPLING_POINT in", values, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointNotIn(List<Integer> values) {
            addCriterion("IS_SAMPLING_POINT not in", values, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointBetween(Integer value1, Integer value2) {
            addCriterion("IS_SAMPLING_POINT between", value1, value2, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andIsSamplingPointNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SAMPLING_POINT not between", value1, value2, "isSamplingPoint");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIsNull() {
            addCriterion("IS_RELEASE is null");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIsNotNull() {
            addCriterion("IS_RELEASE is not null");
            return (Criteria) this;
        }

        public Criteria andIsReleaseEqualTo(Integer value) {
            addCriterion("IS_RELEASE =", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotEqualTo(Integer value) {
            addCriterion("IS_RELEASE <>", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseGreaterThan(Integer value) {
            addCriterion("IS_RELEASE >", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_RELEASE >=", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseLessThan(Integer value) {
            addCriterion("IS_RELEASE <", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseLessThanOrEqualTo(Integer value) {
            addCriterion("IS_RELEASE <=", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIn(List<Integer> values) {
            addCriterion("IS_RELEASE in", values, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotIn(List<Integer> values) {
            addCriterion("IS_RELEASE not in", values, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseBetween(Integer value1, Integer value2) {
            addCriterion("IS_RELEASE between", value1, value2, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_RELEASE not between", value1, value2, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsSamplingIsNull() {
            addCriterion("IS_SAMPLING is null");
            return (Criteria) this;
        }

        public Criteria andIsSamplingIsNotNull() {
            addCriterion("IS_SAMPLING is not null");
            return (Criteria) this;
        }

        public Criteria andIsSamplingEqualTo(Integer value) {
            addCriterion("IS_SAMPLING =", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingNotEqualTo(Integer value) {
            addCriterion("IS_SAMPLING <>", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingGreaterThan(Integer value) {
            addCriterion("IS_SAMPLING >", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SAMPLING >=", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingLessThan(Integer value) {
            addCriterion("IS_SAMPLING <", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SAMPLING <=", value, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingIn(List<Integer> values) {
            addCriterion("IS_SAMPLING in", values, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingNotIn(List<Integer> values) {
            addCriterion("IS_SAMPLING not in", values, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingBetween(Integer value1, Integer value2) {
            addCriterion("IS_SAMPLING between", value1, value2, "isSampling");
            return (Criteria) this;
        }

        public Criteria andIsSamplingNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SAMPLING not between", value1, value2, "isSampling");
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
    }

    /**
    T_LAND_SAMPLING_SCHEME_POINT
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