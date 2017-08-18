package com.xiangxun.atms.module.repair.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepairProjectSearch {
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
    public RepairProjectSearch() {
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

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTechnologyIsNull() {
            addCriterion("TECHNOLOGY is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyIsNotNull() {
            addCriterion("TECHNOLOGY is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyEqualTo(String value) {
            addCriterion("TECHNOLOGY =", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotEqualTo(String value) {
            addCriterion("TECHNOLOGY <>", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyGreaterThan(String value) {
            addCriterion("TECHNOLOGY >", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY >=", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLessThan(String value) {
            addCriterion("TECHNOLOGY <", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLessThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY <=", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLike(String value) {
            addCriterion("TECHNOLOGY like", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotLike(String value) {
            addCriterion("TECHNOLOGY not like", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyIn(List<String> values) {
            addCriterion("TECHNOLOGY in", values, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotIn(List<String> values) {
            addCriterion("TECHNOLOGY not in", values, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY between", value1, value2, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY not between", value1, value2, "technology");
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

        public Criteria andAreaIsNull() {
            addCriterion("AREA is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("AREA is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("AREA =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("AREA <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("AREA >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("AREA >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("AREA <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("AREA <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("AREA like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("AREA not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("AREA in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("AREA not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("AREA between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("AREA not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andMissionTargetIsNull() {
            addCriterion("MISSION_TARGET is null");
            return (Criteria) this;
        }

        public Criteria andMissionTargetIsNotNull() {
            addCriterion("MISSION_TARGET is not null");
            return (Criteria) this;
        }

        public Criteria andMissionTargetEqualTo(String value) {
            addCriterion("MISSION_TARGET =", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetNotEqualTo(String value) {
            addCriterion("MISSION_TARGET <>", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetGreaterThan(String value) {
            addCriterion("MISSION_TARGET >", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetGreaterThanOrEqualTo(String value) {
            addCriterion("MISSION_TARGET >=", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetLessThan(String value) {
            addCriterion("MISSION_TARGET <", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetLessThanOrEqualTo(String value) {
            addCriterion("MISSION_TARGET <=", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetLike(String value) {
            addCriterion("MISSION_TARGET like", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetNotLike(String value) {
            addCriterion("MISSION_TARGET not like", value, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetIn(List<String> values) {
            addCriterion("MISSION_TARGET in", values, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetNotIn(List<String> values) {
            addCriterion("MISSION_TARGET not in", values, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetBetween(String value1, String value2) {
            addCriterion("MISSION_TARGET between", value1, value2, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andMissionTargetNotBetween(String value1, String value2) {
            addCriterion("MISSION_TARGET not between", value1, value2, "missionTarget");
            return (Criteria) this;
        }

        public Criteria andEffectIsNull() {
            addCriterion("EFFECT is null");
            return (Criteria) this;
        }

        public Criteria andEffectIsNotNull() {
            addCriterion("EFFECT is not null");
            return (Criteria) this;
        }

        public Criteria andEffectEqualTo(String value) {
            addCriterion("EFFECT =", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotEqualTo(String value) {
            addCriterion("EFFECT <>", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectGreaterThan(String value) {
            addCriterion("EFFECT >", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectGreaterThanOrEqualTo(String value) {
            addCriterion("EFFECT >=", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLessThan(String value) {
            addCriterion("EFFECT <", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLessThanOrEqualTo(String value) {
            addCriterion("EFFECT <=", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLike(String value) {
            addCriterion("EFFECT like", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotLike(String value) {
            addCriterion("EFFECT not like", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectIn(List<String> values) {
            addCriterion("EFFECT in", values, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotIn(List<String> values) {
            addCriterion("EFFECT not in", values, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectBetween(String value1, String value2) {
            addCriterion("EFFECT between", value1, value2, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotBetween(String value1, String value2) {
            addCriterion("EFFECT not between", value1, value2, "effect");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeIsNull() {
            addCriterion("POLLUTE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeIsNotNull() {
            addCriterion("POLLUTE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeEqualTo(String value) {
            addCriterion("POLLUTE_TYPE =", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeNotEqualTo(String value) {
            addCriterion("POLLUTE_TYPE <>", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeGreaterThan(String value) {
            addCriterion("POLLUTE_TYPE >", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("POLLUTE_TYPE >=", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeLessThan(String value) {
            addCriterion("POLLUTE_TYPE <", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeLessThanOrEqualTo(String value) {
            addCriterion("POLLUTE_TYPE <=", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeLike(String value) {
            addCriterion("POLLUTE_TYPE like", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeNotLike(String value) {
            addCriterion("POLLUTE_TYPE not like", value, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeIn(List<String> values) {
            addCriterion("POLLUTE_TYPE in", values, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeNotIn(List<String> values) {
            addCriterion("POLLUTE_TYPE not in", values, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeBetween(String value1, String value2) {
            addCriterion("POLLUTE_TYPE between", value1, value2, "polluteType");
            return (Criteria) this;
        }

        public Criteria andPolluteTypeNotBetween(String value1, String value2) {
            addCriterion("POLLUTE_TYPE not between", value1, value2, "polluteType");
            return (Criteria) this;
        }

        public Criteria andExplainIsNull() {
            addCriterion("EXPLAIN is null");
            return (Criteria) this;
        }

        public Criteria andExplainIsNotNull() {
            addCriterion("EXPLAIN is not null");
            return (Criteria) this;
        }

        public Criteria andExplainEqualTo(String value) {
            addCriterion("EXPLAIN =", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotEqualTo(String value) {
            addCriterion("EXPLAIN <>", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainGreaterThan(String value) {
            addCriterion("EXPLAIN >", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainGreaterThanOrEqualTo(String value) {
            addCriterion("EXPLAIN >=", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLessThan(String value) {
            addCriterion("EXPLAIN <", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLessThanOrEqualTo(String value) {
            addCriterion("EXPLAIN <=", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainLike(String value) {
            addCriterion("EXPLAIN like", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotLike(String value) {
            addCriterion("EXPLAIN not like", value, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainIn(List<String> values) {
            addCriterion("EXPLAIN in", values, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotIn(List<String> values) {
            addCriterion("EXPLAIN not in", values, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainBetween(String value1, String value2) {
            addCriterion("EXPLAIN between", value1, value2, "explain");
            return (Criteria) this;
        }

        public Criteria andExplainNotBetween(String value1, String value2) {
            addCriterion("EXPLAIN not between", value1, value2, "explain");
            return (Criteria) this;
        }

        public Criteria andScheduleIsNull() {
            addCriterion("SCHEDULE is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIsNotNull() {
            addCriterion("SCHEDULE is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleEqualTo(String value) {
            addCriterion("SCHEDULE =", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotEqualTo(String value) {
            addCriterion("SCHEDULE <>", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleGreaterThan(String value) {
            addCriterion("SCHEDULE >", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEDULE >=", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLessThan(String value) {
            addCriterion("SCHEDULE <", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLessThanOrEqualTo(String value) {
            addCriterion("SCHEDULE <=", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLike(String value) {
            addCriterion("SCHEDULE like", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotLike(String value) {
            addCriterion("SCHEDULE not like", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleIn(List<String> values) {
            addCriterion("SCHEDULE in", values, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotIn(List<String> values) {
            addCriterion("SCHEDULE not in", values, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleBetween(String value1, String value2) {
            addCriterion("SCHEDULE between", value1, value2, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotBetween(String value1, String value2) {
            addCriterion("SCHEDULE not between", value1, value2, "schedule");
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

        public Criteria andAcceptionTimeIsNull() {
            addCriterion("ACCEPTION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeIsNotNull() {
            addCriterion("ACCEPTION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeEqualTo(Date value) {
            addCriterion("ACCEPTION_TIME =", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeNotEqualTo(Date value) {
            addCriterion("ACCEPTION_TIME <>", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeGreaterThan(Date value) {
            addCriterion("ACCEPTION_TIME >", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ACCEPTION_TIME >=", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeLessThan(Date value) {
            addCriterion("ACCEPTION_TIME <", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeLessThanOrEqualTo(Date value) {
            addCriterion("ACCEPTION_TIME <=", value, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeIn(List<Date> values) {
            addCriterion("ACCEPTION_TIME in", values, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeNotIn(List<Date> values) {
            addCriterion("ACCEPTION_TIME not in", values, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeBetween(Date value1, Date value2) {
            addCriterion("ACCEPTION_TIME between", value1, value2, "acceptionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptionTimeNotBetween(Date value1, Date value2) {
            addCriterion("ACCEPTION_TIME not between", value1, value2, "acceptionTime");
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
    T_LAND_REPAIR_PROJECT
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