package com.xiangxun.atms.module.reg.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LandRegSearch {
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
    public LandRegSearch() {
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

        public Criteria andDepthIsNull() {
            addCriterion("DEPTH is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("DEPTH is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(String value) {
            addCriterion("DEPTH =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(String value) {
            addCriterion("DEPTH <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(String value) {
            addCriterion("DEPTH >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTH >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(String value) {
            addCriterion("DEPTH <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(String value) {
            addCriterion("DEPTH <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLike(String value) {
            addCriterion("DEPTH like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotLike(String value) {
            addCriterion("DEPTH not like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<String> values) {
            addCriterion("DEPTH in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<String> values) {
            addCriterion("DEPTH not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(String value1, String value2) {
            addCriterion("DEPTH between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(String value1, String value2) {
            addCriterion("DEPTH not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andSoilTypeIsNull() {
            addCriterion("SOIL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSoilTypeIsNotNull() {
            addCriterion("SOIL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSoilTypeEqualTo(String value) {
            addCriterion("SOIL_TYPE =", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeNotEqualTo(String value) {
            addCriterion("SOIL_TYPE <>", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeGreaterThan(String value) {
            addCriterion("SOIL_TYPE >", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SOIL_TYPE >=", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeLessThan(String value) {
            addCriterion("SOIL_TYPE <", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeLessThanOrEqualTo(String value) {
            addCriterion("SOIL_TYPE <=", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeLike(String value) {
            addCriterion("SOIL_TYPE like", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeNotLike(String value) {
            addCriterion("SOIL_TYPE not like", value, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeIn(List<String> values) {
            addCriterion("SOIL_TYPE in", values, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeNotIn(List<String> values) {
            addCriterion("SOIL_TYPE not in", values, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeBetween(String value1, String value2) {
            addCriterion("SOIL_TYPE between", value1, value2, "soilType");
            return (Criteria) this;
        }

        public Criteria andSoilTypeNotBetween(String value1, String value2) {
            addCriterion("SOIL_TYPE not between", value1, value2, "soilType");
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

        public Criteria andSamplingTimeIsNull() {
            addCriterion("SAMPLING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeIsNotNull() {
            addCriterion("SAMPLING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeEqualTo(Date value) {
            addCriterion("SAMPLING_TIME =", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeNotEqualTo(Date value) {
            addCriterion("SAMPLING_TIME <>", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeGreaterThan(Date value) {
            addCriterion("SAMPLING_TIME >", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SAMPLING_TIME >=", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeLessThan(Date value) {
            addCriterion("SAMPLING_TIME <", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeLessThanOrEqualTo(Date value) {
            addCriterion("SAMPLING_TIME <=", value, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeIn(List<Date> values) {
            addCriterion("SAMPLING_TIME in", values, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeNotIn(List<Date> values) {
            addCriterion("SAMPLING_TIME not in", values, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeBetween(Date value1, Date value2) {
            addCriterion("SAMPLING_TIME between", value1, value2, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingTimeNotBetween(Date value1, Date value2) {
            addCriterion("SAMPLING_TIME not between", value1, value2, "samplingTime");
            return (Criteria) this;
        }

        public Criteria andSamplingUserIsNull() {
            addCriterion("SAMPLING_USER is null");
            return (Criteria) this;
        }

        public Criteria andSamplingUserIsNotNull() {
            addCriterion("SAMPLING_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSamplingUserEqualTo(String value) {
            addCriterion("SAMPLING_USER =", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserNotEqualTo(String value) {
            addCriterion("SAMPLING_USER <>", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserGreaterThan(String value) {
            addCriterion("SAMPLING_USER >", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserGreaterThanOrEqualTo(String value) {
            addCriterion("SAMPLING_USER >=", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserLessThan(String value) {
            addCriterion("SAMPLING_USER <", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserLessThanOrEqualTo(String value) {
            addCriterion("SAMPLING_USER <=", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserLike(String value) {
            addCriterion("SAMPLING_USER like", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserNotLike(String value) {
            addCriterion("SAMPLING_USER not like", value, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserIn(List<String> values) {
            addCriterion("SAMPLING_USER in", values, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserNotIn(List<String> values) {
            addCriterion("SAMPLING_USER not in", values, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserBetween(String value1, String value2) {
            addCriterion("SAMPLING_USER between", value1, value2, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andSamplingUserNotBetween(String value1, String value2) {
            addCriterion("SAMPLING_USER not between", value1, value2, "samplingUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserIsNull() {
            addCriterion("RECEIVE_USER is null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserIsNotNull() {
            addCriterion("RECEIVE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserEqualTo(String value) {
            addCriterion("RECEIVE_USER =", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNotEqualTo(String value) {
            addCriterion("RECEIVE_USER <>", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserGreaterThan(String value) {
            addCriterion("RECEIVE_USER >", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER >=", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserLessThan(String value) {
            addCriterion("RECEIVE_USER <", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER <=", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserLike(String value) {
            addCriterion("RECEIVE_USER like", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNotLike(String value) {
            addCriterion("RECEIVE_USER not like", value, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserIn(List<String> values) {
            addCriterion("RECEIVE_USER in", values, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNotIn(List<String> values) {
            addCriterion("RECEIVE_USER not in", values, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER between", value1, value2, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER not between", value1, value2, "receiveUser");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNull() {
            addCriterion("SEND_USER is null");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNotNull() {
            addCriterion("SEND_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserEqualTo(String value) {
            addCriterion("SEND_USER =", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotEqualTo(String value) {
            addCriterion("SEND_USER <>", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThan(String value) {
            addCriterion("SEND_USER >", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_USER >=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThan(String value) {
            addCriterion("SEND_USER <", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThanOrEqualTo(String value) {
            addCriterion("SEND_USER <=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLike(String value) {
            addCriterion("SEND_USER like", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotLike(String value) {
            addCriterion("SEND_USER not like", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserIn(List<String> values) {
            addCriterion("SEND_USER in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotIn(List<String> values) {
            addCriterion("SEND_USER not in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserBetween(String value1, String value2) {
            addCriterion("SEND_USER between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotBetween(String value1, String value2) {
            addCriterion("SEND_USER not between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceIsNull() {
            addCriterion("SAMPLING_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceIsNotNull() {
            addCriterion("SAMPLING_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceEqualTo(String value) {
            addCriterion("SAMPLING_SOURCE =", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceNotEqualTo(String value) {
            addCriterion("SAMPLING_SOURCE <>", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceGreaterThan(String value) {
            addCriterion("SAMPLING_SOURCE >", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SAMPLING_SOURCE >=", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceLessThan(String value) {
            addCriterion("SAMPLING_SOURCE <", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceLessThanOrEqualTo(String value) {
            addCriterion("SAMPLING_SOURCE <=", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceLike(String value) {
            addCriterion("SAMPLING_SOURCE like", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceNotLike(String value) {
            addCriterion("SAMPLING_SOURCE not like", value, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceIn(List<String> values) {
            addCriterion("SAMPLING_SOURCE in", values, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceNotIn(List<String> values) {
            addCriterion("SAMPLING_SOURCE not in", values, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceBetween(String value1, String value2) {
            addCriterion("SAMPLING_SOURCE between", value1, value2, "samplingSource");
            return (Criteria) this;
        }

        public Criteria andSamplingSourceNotBetween(String value1, String value2) {
            addCriterion("SAMPLING_SOURCE not between", value1, value2, "samplingSource");
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

        public Criteria andCheckUserIsNull() {
            addCriterion("CHECK_USER is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNotNull() {
            addCriterion("CHECK_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserEqualTo(String value) {
            addCriterion("CHECK_USER =", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotEqualTo(String value) {
            addCriterion("CHECK_USER <>", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThan(String value) {
            addCriterion("CHECK_USER >", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_USER >=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThan(String value) {
            addCriterion("CHECK_USER <", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThanOrEqualTo(String value) {
            addCriterion("CHECK_USER <=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLike(String value) {
            addCriterion("CHECK_USER like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotLike(String value) {
            addCriterion("CHECK_USER not like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserIn(List<String> values) {
            addCriterion("CHECK_USER in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotIn(List<String> values) {
            addCriterion("CHECK_USER not in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserBetween(String value1, String value2) {
            addCriterion("CHECK_USER between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotBetween(String value1, String value2) {
            addCriterion("CHECK_USER not between", value1, value2, "checkUser");
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

        public Criteria andMissionIdIsNull() {
            addCriterion("MISSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andMissionIdIsNotNull() {
            addCriterion("MISSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMissionIdEqualTo(String value) {
            addCriterion("MISSION_ID =", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotEqualTo(String value) {
            addCriterion("MISSION_ID <>", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdGreaterThan(String value) {
            addCriterion("MISSION_ID >", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdGreaterThanOrEqualTo(String value) {
            addCriterion("MISSION_ID >=", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLessThan(String value) {
            addCriterion("MISSION_ID <", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLessThanOrEqualTo(String value) {
            addCriterion("MISSION_ID <=", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLike(String value) {
            addCriterion("MISSION_ID like", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotLike(String value) {
            addCriterion("MISSION_ID not like", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdIn(List<String> values) {
            addCriterion("MISSION_ID in", values, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotIn(List<String> values) {
            addCriterion("MISSION_ID not in", values, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdBetween(String value1, String value2) {
            addCriterion("MISSION_ID between", value1, value2, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotBetween(String value1, String value2) {
            addCriterion("MISSION_ID not between", value1, value2, "missionId");
            return (Criteria) this;
        }
    }

    /**
    T_SAMPLING_LAND_REG
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