package com.xiangxun.atms.module.geoServer.vo;

import java.util.ArrayList;
import java.util.List;

public class GeoserverSearch {
		
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GeoserverSearch() {
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
  
    // GeneratedCriteria类定义开始------------------
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

        public void addCriterion(String condition) {
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


        public Criteria andIdEqualTo(long value) {
            addCriterion("GID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(long value) {
            addCriterion("GID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(long value) {
            addCriterion("GID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(long value) {
            addCriterion("GID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(long value) {
            addCriterion("GID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(long value) {
            addCriterion("GID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(long value1, long value2) {
            addCriterion("GID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(long value1, long value2) {
            addCriterion("GID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
        	value = "%" + value + "%"; 
            addCriterion("TYPE like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
        	value = "%" + value + "%";  
            addCriterion("TYPE not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "parentid");
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

        public Criteria andNameLike(String value) {
        	value = "%" + value + "%";
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
        	value = "%" + value + "%";
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

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }
        
        public Criteria andRoadIdIsNull() {
            addCriterion("ROAD_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoadIdIsNotNull() {
            addCriterion("ROAD_ID is not null");
            return (Criteria) this;
        }
        
        public Criteria andRoadIdEqualTo(String value) {
            addCriterion("ROAD_ID =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotEqualTo(String value) {
            addCriterion("ROAD_ID <>", value, "memo");
            return (Criteria) this;
        }


        public Criteria andRoadIdLike(String value) {
        	value = "%" + value + "%"; 
            addCriterion("ROAD_ID like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotLike(String value) {
        	value = "%" + value + "%"; 
            addCriterion("ROAD_ID not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andRoadIdIn(List<String> values) {
            addCriterion("ROAD_ID in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotIn(List<String> values) {
            addCriterion("ROAD_ID not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andRoadNameIsNull() {
            addCriterion("ROAD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoadNameIsNotNull() {
            addCriterion("ROAD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoadNameEqualTo(String value) {
            addCriterion("ROAD_NAME =", value, "status");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotEqualTo(String value) {
            addCriterion("ROAD_NAME <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andRoadNameLike(String value) {
        	value = "%" + value + "%"; 
            addCriterion("ROAD_NAME like", value, "status");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotLike(String value) {
        	value = "%" + value + "%"; 
            addCriterion("ROAD_NAME not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andRoadNameIn(List<String> values) {
            addCriterion("ROAD_NAME in", values, "status");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotIn(List<String> values) {
            addCriterion("ROAD_NAME not in", values, "status");
            return (Criteria) this;
        }
        

        public Criteria andGeometryIsNull() {
            addCriterion("GEOM is null");
            return (Criteria) this;
        }

        public Criteria andGeometryIsNotNull() {
            addCriterion("GEOM is not null");
            return (Criteria) this;
        }

        public Criteria andGeometryEqualTo(String value) {
            addCriterion("GEOM =", value, "status");
            return (Criteria) this;
        }

        public Criteria andGeometryNotEqualTo(String value) {
            addCriterion("GEOM <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andGeometryLike(String value) {
            addCriterion("GEOM like", value, "status");
            return (Criteria) this;
        }

        public Criteria andGeometryNotLike(String value) {
            addCriterion("GEOM not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andGeometryIn(List<String> values) {
            addCriterion("GEOM in", values, "status");
            return (Criteria) this;
        }

        public Criteria andGeometryNotIn(List<String> values) {
            addCriterion("GEOM not in", values, "status");
            return (Criteria) this;
        }
        
    }
	// GeneratedCriteria类定义结束------------------
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    
    //设置查询条件的类----------
    public static class Criterion {
    	//设置查询条件--------
        private String condition;
         //查询条件的值设置-----
        private Object value;
         //查询第二条件值设置-----
        private Object secondValue;
        //查询是否设置---
        private boolean noValue;
        //查询条件的值是否为单值-------
        private boolean singleValue;
        //查询条件的值是否为between值-------
        private boolean betweenValue;
        //查询条件的值是否集合值-------
        private boolean listValue;
        //设置类型处理器---------------
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
            
            if (value instanceof String) {
            	this.value = "'" + value + "'";
            } else {
            	this.value = value;
            }
            
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
