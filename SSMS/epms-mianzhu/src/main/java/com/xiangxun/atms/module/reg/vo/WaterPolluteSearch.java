package com.xiangxun.atms.module.reg.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterPolluteSearch {
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
    public WaterPolluteSearch() {
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

        public Criteria andWaterIdIsNull() {
            addCriterion("WATER_ID is null");
            return (Criteria) this;
        }

        public Criteria andWaterIdIsNotNull() {
            addCriterion("WATER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWaterIdEqualTo(String value) {
            addCriterion("WATER_ID =", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotEqualTo(String value) {
            addCriterion("WATER_ID <>", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdGreaterThan(String value) {
            addCriterion("WATER_ID >", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdGreaterThanOrEqualTo(String value) {
            addCriterion("WATER_ID >=", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLessThan(String value) {
            addCriterion("WATER_ID <", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLessThanOrEqualTo(String value) {
            addCriterion("WATER_ID <=", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLike(String value) {
            addCriterion("WATER_ID like", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotLike(String value) {
            addCriterion("WATER_ID not like", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdIn(List<String> values) {
            addCriterion("WATER_ID in", values, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotIn(List<String> values) {
            addCriterion("WATER_ID not in", values, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdBetween(String value1, String value2) {
            addCriterion("WATER_ID between", value1, value2, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotBetween(String value1, String value2) {
            addCriterion("WATER_ID not between", value1, value2, "waterId");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNull() {
            addCriterion("TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNotNull() {
            addCriterion("TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeEqualTo(String value) {
            addCriterion("TYPE_CODE =", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotEqualTo(String value) {
            addCriterion("TYPE_CODE <>", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThan(String value) {
            addCriterion("TYPE_CODE >", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE >=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThan(String value) {
            addCriterion("TYPE_CODE <", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE <=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLike(String value) {
            addCriterion("TYPE_CODE like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotLike(String value) {
            addCriterion("TYPE_CODE not like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIn(List<String> values) {
            addCriterion("TYPE_CODE in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotIn(List<String> values) {
            addCriterion("TYPE_CODE not in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeBetween(String value1, String value2) {
            addCriterion("TYPE_CODE between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotBetween(String value1, String value2) {
            addCriterion("TYPE_CODE not between", value1, value2, "typeCode");
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

        public Criteria andFeaturesIsNull() {
            addCriterion("FEATURES is null");
            return (Criteria) this;
        }

        public Criteria andFeaturesIsNotNull() {
            addCriterion("FEATURES is not null");
            return (Criteria) this;
        }

        public Criteria andFeaturesEqualTo(String value) {
            addCriterion("FEATURES =", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotEqualTo(String value) {
            addCriterion("FEATURES <>", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThan(String value) {
            addCriterion("FEATURES >", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThanOrEqualTo(String value) {
            addCriterion("FEATURES >=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThan(String value) {
            addCriterion("FEATURES <", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThanOrEqualTo(String value) {
            addCriterion("FEATURES <=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLike(String value) {
            addCriterion("FEATURES like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotLike(String value) {
            addCriterion("FEATURES not like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesIn(List<String> values) {
            addCriterion("FEATURES in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotIn(List<String> values) {
            addCriterion("FEATURES not in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesBetween(String value1, String value2) {
            addCriterion("FEATURES between", value1, value2, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotBetween(String value1, String value2) {
            addCriterion("FEATURES not between", value1, value2, "features");
            return (Criteria) this;
        }

        public Criteria andScaleIsNull() {
            addCriterion("SCALE is null");
            return (Criteria) this;
        }

        public Criteria andScaleIsNotNull() {
            addCriterion("SCALE is not null");
            return (Criteria) this;
        }

        public Criteria andScaleEqualTo(String value) {
            addCriterion("SCALE =", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotEqualTo(String value) {
            addCriterion("SCALE <>", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleGreaterThan(String value) {
            addCriterion("SCALE >", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleGreaterThanOrEqualTo(String value) {
            addCriterion("SCALE >=", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLessThan(String value) {
            addCriterion("SCALE <", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLessThanOrEqualTo(String value) {
            addCriterion("SCALE <=", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLike(String value) {
            addCriterion("SCALE like", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotLike(String value) {
            addCriterion("SCALE not like", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleIn(List<String> values) {
            addCriterion("SCALE in", values, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotIn(List<String> values) {
            addCriterion("SCALE not in", values, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleBetween(String value1, String value2) {
            addCriterion("SCALE between", value1, value2, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotBetween(String value1, String value2) {
            addCriterion("SCALE not between", value1, value2, "scale");
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

        public Criteria andRiverDistanceIsNull() {
            addCriterion("RIVER_DISTANCE is null");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceIsNotNull() {
            addCriterion("RIVER_DISTANCE is not null");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceEqualTo(String value) {
            addCriterion("RIVER_DISTANCE =", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceNotEqualTo(String value) {
            addCriterion("RIVER_DISTANCE <>", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceGreaterThan(String value) {
            addCriterion("RIVER_DISTANCE >", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceGreaterThanOrEqualTo(String value) {
            addCriterion("RIVER_DISTANCE >=", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceLessThan(String value) {
            addCriterion("RIVER_DISTANCE <", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceLessThanOrEqualTo(String value) {
            addCriterion("RIVER_DISTANCE <=", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceLike(String value) {
            addCriterion("RIVER_DISTANCE like", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceNotLike(String value) {
            addCriterion("RIVER_DISTANCE not like", value, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceIn(List<String> values) {
            addCriterion("RIVER_DISTANCE in", values, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceNotIn(List<String> values) {
            addCriterion("RIVER_DISTANCE not in", values, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceBetween(String value1, String value2) {
            addCriterion("RIVER_DISTANCE between", value1, value2, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andRiverDistanceNotBetween(String value1, String value2) {
            addCriterion("RIVER_DISTANCE not between", value1, value2, "riverDistance");
            return (Criteria) this;
        }

        public Criteria andIsOutfallIsNull() {
            addCriterion("IS_OUTFALL is null");
            return (Criteria) this;
        }

        public Criteria andIsOutfallIsNotNull() {
            addCriterion("IS_OUTFALL is not null");
            return (Criteria) this;
        }

        public Criteria andIsOutfallEqualTo(Integer value) {
            addCriterion("IS_OUTFALL =", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallNotEqualTo(Integer value) {
            addCriterion("IS_OUTFALL <>", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallGreaterThan(Integer value) {
            addCriterion("IS_OUTFALL >", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_OUTFALL >=", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallLessThan(Integer value) {
            addCriterion("IS_OUTFALL <", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallLessThanOrEqualTo(Integer value) {
            addCriterion("IS_OUTFALL <=", value, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallIn(List<Integer> values) {
            addCriterion("IS_OUTFALL in", values, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallNotIn(List<Integer> values) {
            addCriterion("IS_OUTFALL not in", values, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallBetween(Integer value1, Integer value2) {
            addCriterion("IS_OUTFALL between", value1, value2, "isOutfall");
            return (Criteria) this;
        }

        public Criteria andIsOutfallNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_OUTFALL not between", value1, value2, "isOutfall");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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
    T_SAMPLING_WATER_POLLUTE
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