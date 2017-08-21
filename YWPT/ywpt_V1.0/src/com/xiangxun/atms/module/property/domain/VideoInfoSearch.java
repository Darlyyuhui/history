package com.xiangxun.atms.module.property.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VideoInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VideoInfoSearch() {
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

        public Criteria andDirectionCodeIsNull() {
            addCriterion("DIRECTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeIsNotNull() {
            addCriterion("DIRECTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeEqualTo(String value) {
            addCriterion("DIRECTION_CODE =", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotEqualTo(String value) {
            addCriterion("DIRECTION_CODE <>", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeGreaterThan(String value) {
            addCriterion("DIRECTION_CODE >", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECTION_CODE >=", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLessThan(String value) {
            addCriterion("DIRECTION_CODE <", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLessThanOrEqualTo(String value) {
            addCriterion("DIRECTION_CODE <=", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeLike(String value) {
            addCriterion("DIRECTION_CODE like", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotLike(String value) {
            addCriterion("DIRECTION_CODE not like", value, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeIn(List<String> values) {
            addCriterion("DIRECTION_CODE in", values, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotIn(List<String> values) {
            addCriterion("DIRECTION_CODE not in", values, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeBetween(String value1, String value2) {
            addCriterion("DIRECTION_CODE between", value1, value2, "directionCode");
            return (Criteria) this;
        }

        public Criteria andDirectionCodeNotBetween(String value1, String value2) {
            addCriterion("DIRECTION_CODE not between", value1, value2, "directionCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeIsNull() {
            addCriterion("VIDEOTYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeIsNotNull() {
            addCriterion("VIDEOTYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeEqualTo(String value) {
            addCriterion("VIDEOTYPE_CODE =", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeNotEqualTo(String value) {
            addCriterion("VIDEOTYPE_CODE <>", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeGreaterThan(String value) {
            addCriterion("VIDEOTYPE_CODE >", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VIDEOTYPE_CODE >=", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeLessThan(String value) {
            addCriterion("VIDEOTYPE_CODE <", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeLessThanOrEqualTo(String value) {
            addCriterion("VIDEOTYPE_CODE <=", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeLike(String value) {
            addCriterion("VIDEOTYPE_CODE like", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeNotLike(String value) {
            addCriterion("VIDEOTYPE_CODE not like", value, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeIn(List<String> values) {
            addCriterion("VIDEOTYPE_CODE in", values, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeNotIn(List<String> values) {
            addCriterion("VIDEOTYPE_CODE not in", values, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeBetween(String value1, String value2) {
            addCriterion("VIDEOTYPE_CODE between", value1, value2, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andVideotypeCodeNotBetween(String value1, String value2) {
            addCriterion("VIDEOTYPE_CODE not between", value1, value2, "videotypeCode");
            return (Criteria) this;
        }

        public Criteria andIscloudIsNull() {
            addCriterion("ISCLOUD is null");
            return (Criteria) this;
        }

        public Criteria andIscloudIsNotNull() {
            addCriterion("ISCLOUD is not null");
            return (Criteria) this;
        }

        public Criteria andIscloudEqualTo(String value) {
            addCriterion("ISCLOUD =", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudNotEqualTo(String value) {
            addCriterion("ISCLOUD <>", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudGreaterThan(String value) {
            addCriterion("ISCLOUD >", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudGreaterThanOrEqualTo(String value) {
            addCriterion("ISCLOUD >=", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudLessThan(String value) {
            addCriterion("ISCLOUD <", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudLessThanOrEqualTo(String value) {
            addCriterion("ISCLOUD <=", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudLike(String value) {
            addCriterion("ISCLOUD like", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudNotLike(String value) {
            addCriterion("ISCLOUD not like", value, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudIn(List<String> values) {
            addCriterion("ISCLOUD in", values, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudNotIn(List<String> values) {
            addCriterion("ISCLOUD not in", values, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudBetween(String value1, String value2) {
            addCriterion("ISCLOUD between", value1, value2, "iscloud");
            return (Criteria) this;
        }

        public Criteria andIscloudNotBetween(String value1, String value2) {
            addCriterion("ISCLOUD not between", value1, value2, "iscloud");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdIsNull() {
            addCriterion("ROADINFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdIsNotNull() {
            addCriterion("ROADINFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdEqualTo(String value) {
            addCriterion("ROADINFO_ID =", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdNotEqualTo(String value) {
            addCriterion("ROADINFO_ID <>", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdGreaterThan(String value) {
            addCriterion("ROADINFO_ID >", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROADINFO_ID >=", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdLessThan(String value) {
            addCriterion("ROADINFO_ID <", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdLessThanOrEqualTo(String value) {
            addCriterion("ROADINFO_ID <=", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdLike(String value) {
            addCriterion("ROADINFO_ID like", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdNotLike(String value) {
            addCriterion("ROADINFO_ID not like", value, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdIn(List<String> values) {
            addCriterion("ROADINFO_ID in", values, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdNotIn(List<String> values) {
            addCriterion("ROADINFO_ID not in", values, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdBetween(String value1, String value2) {
            addCriterion("ROADINFO_ID between", value1, value2, "roadinfoId");
            return (Criteria) this;
        }

        public Criteria andRoadinfoIdNotBetween(String value1, String value2) {
            addCriterion("ROADINFO_ID not between", value1, value2, "roadinfoId");
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

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("PORT is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("PORT is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(String value) {
            addCriterion("PORT =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(String value) {
            addCriterion("PORT <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(String value) {
            addCriterion("PORT >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(String value) {
            addCriterion("PORT >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(String value) {
            addCriterion("PORT <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(String value) {
            addCriterion("PORT <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLike(String value) {
            addCriterion("PORT like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotLike(String value) {
            addCriterion("PORT not like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<String> values) {
            addCriterion("PORT in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<String> values) {
            addCriterion("PORT not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(String value1, String value2) {
            addCriterion("PORT between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(String value1, String value2) {
            addCriterion("PORT not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andTracodeIsNull() {
            addCriterion("TRACODE is null");
            return (Criteria) this;
        }

        public Criteria andTracodeIsNotNull() {
            addCriterion("TRACODE is not null");
            return (Criteria) this;
        }

        public Criteria andTracodeEqualTo(String value) {
            addCriterion("TRACODE =", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeNotEqualTo(String value) {
            addCriterion("TRACODE <>", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeGreaterThan(String value) {
            addCriterion("TRACODE >", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeGreaterThanOrEqualTo(String value) {
            addCriterion("TRACODE >=", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeLessThan(String value) {
            addCriterion("TRACODE <", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeLessThanOrEqualTo(String value) {
            addCriterion("TRACODE <=", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeLike(String value) {
            addCriterion("TRACODE like", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeNotLike(String value) {
            addCriterion("TRACODE not like", value, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeIn(List<String> values) {
            addCriterion("TRACODE in", values, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeNotIn(List<String> values) {
            addCriterion("TRACODE not in", values, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeBetween(String value1, String value2) {
            addCriterion("TRACODE between", value1, value2, "tracode");
            return (Criteria) this;
        }

        public Criteria andTracodeNotBetween(String value1, String value2) {
            addCriterion("TRACODE not between", value1, value2, "tracode");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PASSWORD in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PASSWORD not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andIphisIsNull() {
            addCriterion("IPHIS is null");
            return (Criteria) this;
        }

        public Criteria andIphisIsNotNull() {
            addCriterion("IPHIS is not null");
            return (Criteria) this;
        }

        public Criteria andIphisEqualTo(String value) {
            addCriterion("IPHIS =", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisNotEqualTo(String value) {
            addCriterion("IPHIS <>", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisGreaterThan(String value) {
            addCriterion("IPHIS >", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisGreaterThanOrEqualTo(String value) {
            addCriterion("IPHIS >=", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisLessThan(String value) {
            addCriterion("IPHIS <", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisLessThanOrEqualTo(String value) {
            addCriterion("IPHIS <=", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisLike(String value) {
            addCriterion("IPHIS like", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisNotLike(String value) {
            addCriterion("IPHIS not like", value, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisIn(List<String> values) {
            addCriterion("IPHIS in", values, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisNotIn(List<String> values) {
            addCriterion("IPHIS not in", values, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisBetween(String value1, String value2) {
            addCriterion("IPHIS between", value1, value2, "iphis");
            return (Criteria) this;
        }

        public Criteria andIphisNotBetween(String value1, String value2) {
            addCriterion("IPHIS not between", value1, value2, "iphis");
            return (Criteria) this;
        }

        public Criteria andPorthisIsNull() {
            addCriterion("PORTHIS is null");
            return (Criteria) this;
        }

        public Criteria andPorthisIsNotNull() {
            addCriterion("PORTHIS is not null");
            return (Criteria) this;
        }

        public Criteria andPorthisEqualTo(String value) {
            addCriterion("PORTHIS =", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisNotEqualTo(String value) {
            addCriterion("PORTHIS <>", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisGreaterThan(String value) {
            addCriterion("PORTHIS >", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisGreaterThanOrEqualTo(String value) {
            addCriterion("PORTHIS >=", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisLessThan(String value) {
            addCriterion("PORTHIS <", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisLessThanOrEqualTo(String value) {
            addCriterion("PORTHIS <=", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisLike(String value) {
            addCriterion("PORTHIS like", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisNotLike(String value) {
            addCriterion("PORTHIS not like", value, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisIn(List<String> values) {
            addCriterion("PORTHIS in", values, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisNotIn(List<String> values) {
            addCriterion("PORTHIS not in", values, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisBetween(String value1, String value2) {
            addCriterion("PORTHIS between", value1, value2, "porthis");
            return (Criteria) this;
        }

        public Criteria andPorthisNotBetween(String value1, String value2) {
            addCriterion("PORTHIS not between", value1, value2, "porthis");
            return (Criteria) this;
        }

        public Criteria andTracodehisIsNull() {
            addCriterion("TRACODEHIS is null");
            return (Criteria) this;
        }

        public Criteria andTracodehisIsNotNull() {
            addCriterion("TRACODEHIS is not null");
            return (Criteria) this;
        }

        public Criteria andTracodehisEqualTo(String value) {
            addCriterion("TRACODEHIS =", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisNotEqualTo(String value) {
            addCriterion("TRACODEHIS <>", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisGreaterThan(String value) {
            addCriterion("TRACODEHIS >", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisGreaterThanOrEqualTo(String value) {
            addCriterion("TRACODEHIS >=", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisLessThan(String value) {
            addCriterion("TRACODEHIS <", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisLessThanOrEqualTo(String value) {
            addCriterion("TRACODEHIS <=", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisLike(String value) {
            addCriterion("TRACODEHIS like", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisNotLike(String value) {
            addCriterion("TRACODEHIS not like", value, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisIn(List<String> values) {
            addCriterion("TRACODEHIS in", values, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisNotIn(List<String> values) {
            addCriterion("TRACODEHIS not in", values, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisBetween(String value1, String value2) {
            addCriterion("TRACODEHIS between", value1, value2, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andTracodehisNotBetween(String value1, String value2) {
            addCriterion("TRACODEHIS not between", value1, value2, "tracodehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisIsNull() {
            addCriterion("USERNAMEHIS is null");
            return (Criteria) this;
        }

        public Criteria andUsernamehisIsNotNull() {
            addCriterion("USERNAMEHIS is not null");
            return (Criteria) this;
        }

        public Criteria andUsernamehisEqualTo(String value) {
            addCriterion("USERNAMEHIS =", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisNotEqualTo(String value) {
            addCriterion("USERNAMEHIS <>", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisGreaterThan(String value) {
            addCriterion("USERNAMEHIS >", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAMEHIS >=", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisLessThan(String value) {
            addCriterion("USERNAMEHIS <", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisLessThanOrEqualTo(String value) {
            addCriterion("USERNAMEHIS <=", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisLike(String value) {
            addCriterion("USERNAMEHIS like", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisNotLike(String value) {
            addCriterion("USERNAMEHIS not like", value, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisIn(List<String> values) {
            addCriterion("USERNAMEHIS in", values, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisNotIn(List<String> values) {
            addCriterion("USERNAMEHIS not in", values, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisBetween(String value1, String value2) {
            addCriterion("USERNAMEHIS between", value1, value2, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andUsernamehisNotBetween(String value1, String value2) {
            addCriterion("USERNAMEHIS not between", value1, value2, "usernamehis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisIsNull() {
            addCriterion("PASSWORDHIS is null");
            return (Criteria) this;
        }

        public Criteria andPasswordhisIsNotNull() {
            addCriterion("PASSWORDHIS is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordhisEqualTo(String value) {
            addCriterion("PASSWORDHIS =", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisNotEqualTo(String value) {
            addCriterion("PASSWORDHIS <>", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisGreaterThan(String value) {
            addCriterion("PASSWORDHIS >", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORDHIS >=", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisLessThan(String value) {
            addCriterion("PASSWORDHIS <", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisLessThanOrEqualTo(String value) {
            addCriterion("PASSWORDHIS <=", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisLike(String value) {
            addCriterion("PASSWORDHIS like", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisNotLike(String value) {
            addCriterion("PASSWORDHIS not like", value, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisIn(List<String> values) {
            addCriterion("PASSWORDHIS in", values, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisNotIn(List<String> values) {
            addCriterion("PASSWORDHIS not in", values, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisBetween(String value1, String value2) {
            addCriterion("PASSWORDHIS between", value1, value2, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andPasswordhisNotBetween(String value1, String value2) {
            addCriterion("PASSWORDHIS not between", value1, value2, "passwordhis");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNull() {
            addCriterion("COMPANYID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("COMPANYID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(String value) {
            addCriterion("COMPANYID =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(String value) {
            addCriterion("COMPANYID <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(String value) {
            addCriterion("COMPANYID >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANYID >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(String value) {
            addCriterion("COMPANYID <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(String value) {
            addCriterion("COMPANYID <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLike(String value) {
            addCriterion("COMPANYID like", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotLike(String value) {
            addCriterion("COMPANYID not like", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<String> values) {
            addCriterion("COMPANYID in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<String> values) {
            addCriterion("COMPANYID not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(String value1, String value2) {
            addCriterion("COMPANYID between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(String value1, String value2) {
            addCriterion("COMPANYID not between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andPhoto1IsNull() {
            addCriterion("PHOTO1 is null");
            return (Criteria) this;
        }

        public Criteria andPhoto1IsNotNull() {
            addCriterion("PHOTO1 is not null");
            return (Criteria) this;
        }

        public Criteria andPhoto1EqualTo(String value) {
            addCriterion("PHOTO1 =", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1NotEqualTo(String value) {
            addCriterion("PHOTO1 <>", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1GreaterThan(String value) {
            addCriterion("PHOTO1 >", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1GreaterThanOrEqualTo(String value) {
            addCriterion("PHOTO1 >=", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1LessThan(String value) {
            addCriterion("PHOTO1 <", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1LessThanOrEqualTo(String value) {
            addCriterion("PHOTO1 <=", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1Like(String value) {
            addCriterion("PHOTO1 like", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1NotLike(String value) {
            addCriterion("PHOTO1 not like", value, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1In(List<String> values) {
            addCriterion("PHOTO1 in", values, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1NotIn(List<String> values) {
            addCriterion("PHOTO1 not in", values, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1Between(String value1, String value2) {
            addCriterion("PHOTO1 between", value1, value2, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto1NotBetween(String value1, String value2) {
            addCriterion("PHOTO1 not between", value1, value2, "photo1");
            return (Criteria) this;
        }

        public Criteria andPhoto2IsNull() {
            addCriterion("PHOTO2 is null");
            return (Criteria) this;
        }

        public Criteria andPhoto2IsNotNull() {
            addCriterion("PHOTO2 is not null");
            return (Criteria) this;
        }

        public Criteria andPhoto2EqualTo(String value) {
            addCriterion("PHOTO2 =", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2NotEqualTo(String value) {
            addCriterion("PHOTO2 <>", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2GreaterThan(String value) {
            addCriterion("PHOTO2 >", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2GreaterThanOrEqualTo(String value) {
            addCriterion("PHOTO2 >=", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2LessThan(String value) {
            addCriterion("PHOTO2 <", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2LessThanOrEqualTo(String value) {
            addCriterion("PHOTO2 <=", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2Like(String value) {
            addCriterion("PHOTO2 like", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2NotLike(String value) {
            addCriterion("PHOTO2 not like", value, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2In(List<String> values) {
            addCriterion("PHOTO2 in", values, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2NotIn(List<String> values) {
            addCriterion("PHOTO2 not in", values, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2Between(String value1, String value2) {
            addCriterion("PHOTO2 between", value1, value2, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto2NotBetween(String value1, String value2) {
            addCriterion("PHOTO2 not between", value1, value2, "photo2");
            return (Criteria) this;
        }

        public Criteria andPhoto3IsNull() {
            addCriterion("PHOTO3 is null");
            return (Criteria) this;
        }

        public Criteria andPhoto3IsNotNull() {
            addCriterion("PHOTO3 is not null");
            return (Criteria) this;
        }

        public Criteria andPhoto3EqualTo(String value) {
            addCriterion("PHOTO3 =", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3NotEqualTo(String value) {
            addCriterion("PHOTO3 <>", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3GreaterThan(String value) {
            addCriterion("PHOTO3 >", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3GreaterThanOrEqualTo(String value) {
            addCriterion("PHOTO3 >=", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3LessThan(String value) {
            addCriterion("PHOTO3 <", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3LessThanOrEqualTo(String value) {
            addCriterion("PHOTO3 <=", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3Like(String value) {
            addCriterion("PHOTO3 like", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3NotLike(String value) {
            addCriterion("PHOTO3 not like", value, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3In(List<String> values) {
            addCriterion("PHOTO3 in", values, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3NotIn(List<String> values) {
            addCriterion("PHOTO3 not in", values, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3Between(String value1, String value2) {
            addCriterion("PHOTO3 between", value1, value2, "photo3");
            return (Criteria) this;
        }

        public Criteria andPhoto3NotBetween(String value1, String value2) {
            addCriterion("PHOTO3 not between", value1, value2, "photo3");
            return (Criteria) this;
        }

        public Criteria andUsetemplateIsNull() {
            addCriterion("USETEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andUsetemplateIsNotNull() {
            addCriterion("USETEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andUsetemplateEqualTo(String value) {
            addCriterion("USETEMPLATE =", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateNotEqualTo(String value) {
            addCriterion("USETEMPLATE <>", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateGreaterThan(String value) {
            addCriterion("USETEMPLATE >", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateGreaterThanOrEqualTo(String value) {
            addCriterion("USETEMPLATE >=", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateLessThan(String value) {
            addCriterion("USETEMPLATE <", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateLessThanOrEqualTo(String value) {
            addCriterion("USETEMPLATE <=", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateLike(String value) {
            addCriterion("USETEMPLATE like", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateNotLike(String value) {
            addCriterion("USETEMPLATE not like", value, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateIn(List<String> values) {
            addCriterion("USETEMPLATE in", values, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateNotIn(List<String> values) {
            addCriterion("USETEMPLATE not in", values, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateBetween(String value1, String value2) {
            addCriterion("USETEMPLATE between", value1, value2, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andUsetemplateNotBetween(String value1, String value2) {
            addCriterion("USETEMPLATE not between", value1, value2, "usetemplate");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidIsNull() {
            addCriterion("REALVIDEOADDRESSID is null");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidIsNotNull() {
            addCriterion("REALVIDEOADDRESSID is not null");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidEqualTo(String value) {
            addCriterion("REALVIDEOADDRESSID =", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidNotEqualTo(String value) {
            addCriterion("REALVIDEOADDRESSID <>", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidGreaterThan(String value) {
            addCriterion("REALVIDEOADDRESSID >", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidGreaterThanOrEqualTo(String value) {
            addCriterion("REALVIDEOADDRESSID >=", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidLessThan(String value) {
            addCriterion("REALVIDEOADDRESSID <", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidLessThanOrEqualTo(String value) {
            addCriterion("REALVIDEOADDRESSID <=", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidLike(String value) {
            addCriterion("REALVIDEOADDRESSID like", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidNotLike(String value) {
            addCriterion("REALVIDEOADDRESSID not like", value, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidIn(List<String> values) {
            addCriterion("REALVIDEOADDRESSID in", values, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidNotIn(List<String> values) {
            addCriterion("REALVIDEOADDRESSID not in", values, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidBetween(String value1, String value2) {
            addCriterion("REALVIDEOADDRESSID between", value1, value2, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andRealvideoaddressidNotBetween(String value1, String value2) {
            addCriterion("REALVIDEOADDRESSID not between", value1, value2, "realvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidIsNull() {
            addCriterion("HISVIDEOADDRESSID is null");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidIsNotNull() {
            addCriterion("HISVIDEOADDRESSID is not null");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidEqualTo(String value) {
            addCriterion("HISVIDEOADDRESSID =", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidNotEqualTo(String value) {
            addCriterion("HISVIDEOADDRESSID <>", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidGreaterThan(String value) {
            addCriterion("HISVIDEOADDRESSID >", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidGreaterThanOrEqualTo(String value) {
            addCriterion("HISVIDEOADDRESSID >=", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidLessThan(String value) {
            addCriterion("HISVIDEOADDRESSID <", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidLessThanOrEqualTo(String value) {
            addCriterion("HISVIDEOADDRESSID <=", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidLike(String value) {
            addCriterion("HISVIDEOADDRESSID like", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidNotLike(String value) {
            addCriterion("HISVIDEOADDRESSID not like", value, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidIn(List<String> values) {
            addCriterion("HISVIDEOADDRESSID in", values, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidNotIn(List<String> values) {
            addCriterion("HISVIDEOADDRESSID not in", values, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidBetween(String value1, String value2) {
            addCriterion("HISVIDEOADDRESSID between", value1, value2, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andHisvideoaddressidNotBetween(String value1, String value2) {
            addCriterion("HISVIDEOADDRESSID not between", value1, value2, "hisvideoaddressid");
            return (Criteria) this;
        }

        public Criteria andMapxIsNull() {
            addCriterion("MAPX is null");
            return (Criteria) this;
        }

        public Criteria andMapxIsNotNull() {
            addCriterion("MAPX is not null");
            return (Criteria) this;
        }

        public Criteria andMapxEqualTo(String value) {
            addCriterion("MAPX =", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxNotEqualTo(String value) {
            addCriterion("MAPX <>", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxGreaterThan(String value) {
            addCriterion("MAPX >", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxGreaterThanOrEqualTo(String value) {
            addCriterion("MAPX >=", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxLessThan(String value) {
            addCriterion("MAPX <", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxLessThanOrEqualTo(String value) {
            addCriterion("MAPX <=", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxLike(String value) {
            addCriterion("MAPX like", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxNotLike(String value) {
            addCriterion("MAPX not like", value, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxIn(List<String> values) {
            addCriterion("MAPX in", values, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxNotIn(List<String> values) {
            addCriterion("MAPX not in", values, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxBetween(String value1, String value2) {
            addCriterion("MAPX between", value1, value2, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapxNotBetween(String value1, String value2) {
            addCriterion("MAPX not between", value1, value2, "mapx");
            return (Criteria) this;
        }

        public Criteria andMapyIsNull() {
            addCriterion("MAPY is null");
            return (Criteria) this;
        }

        public Criteria andMapyIsNotNull() {
            addCriterion("MAPY is not null");
            return (Criteria) this;
        }

        public Criteria andMapyEqualTo(String value) {
            addCriterion("MAPY =", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyNotEqualTo(String value) {
            addCriterion("MAPY <>", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyGreaterThan(String value) {
            addCriterion("MAPY >", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyGreaterThanOrEqualTo(String value) {
            addCriterion("MAPY >=", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyLessThan(String value) {
            addCriterion("MAPY <", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyLessThanOrEqualTo(String value) {
            addCriterion("MAPY <=", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyLike(String value) {
            addCriterion("MAPY like", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyNotLike(String value) {
            addCriterion("MAPY not like", value, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyIn(List<String> values) {
            addCriterion("MAPY in", values, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyNotIn(List<String> values) {
            addCriterion("MAPY not in", values, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyBetween(String value1, String value2) {
            addCriterion("MAPY between", value1, value2, "mapy");
            return (Criteria) this;
        }

        public Criteria andMapyNotBetween(String value1, String value2) {
            addCriterion("MAPY not between", value1, value2, "mapy");
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

        public Criteria andDeviceIpIsNull() {
            addCriterion("DEVICE_IP is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIpIsNotNull() {
            addCriterion("DEVICE_IP is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIpEqualTo(String value) {
            addCriterion("DEVICE_IP =", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpNotEqualTo(String value) {
            addCriterion("DEVICE_IP <>", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpGreaterThan(String value) {
            addCriterion("DEVICE_IP >", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_IP >=", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpLessThan(String value) {
            addCriterion("DEVICE_IP <", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_IP <=", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpLike(String value) {
            addCriterion("DEVICE_IP like", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpNotLike(String value) {
            addCriterion("DEVICE_IP not like", value, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpIn(List<String> values) {
            addCriterion("DEVICE_IP in", values, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpNotIn(List<String> values) {
            addCriterion("DEVICE_IP not in", values, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpBetween(String value1, String value2) {
            addCriterion("DEVICE_IP between", value1, value2, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIpNotBetween(String value1, String value2) {
            addCriterion("DEVICE_IP not between", value1, value2, "deviceIp");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameIsNull() {
            addCriterion("DEVICE_USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameIsNotNull() {
            addCriterion("DEVICE_USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameEqualTo(String value) {
            addCriterion("DEVICE_USERNAME =", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameNotEqualTo(String value) {
            addCriterion("DEVICE_USERNAME <>", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameGreaterThan(String value) {
            addCriterion("DEVICE_USERNAME >", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_USERNAME >=", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameLessThan(String value) {
            addCriterion("DEVICE_USERNAME <", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_USERNAME <=", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameLike(String value) {
            addCriterion("DEVICE_USERNAME like", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameNotLike(String value) {
            addCriterion("DEVICE_USERNAME not like", value, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameIn(List<String> values) {
            addCriterion("DEVICE_USERNAME in", values, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameNotIn(List<String> values) {
            addCriterion("DEVICE_USERNAME not in", values, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameBetween(String value1, String value2) {
            addCriterion("DEVICE_USERNAME between", value1, value2, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDeviceUsernameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_USERNAME not between", value1, value2, "deviceUsername");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordIsNull() {
            addCriterion("DEVICE_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordIsNotNull() {
            addCriterion("DEVICE_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordEqualTo(String value) {
            addCriterion("DEVICE_PASSWORD =", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordNotEqualTo(String value) {
            addCriterion("DEVICE_PASSWORD <>", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordGreaterThan(String value) {
            addCriterion("DEVICE_PASSWORD >", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_PASSWORD >=", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordLessThan(String value) {
            addCriterion("DEVICE_PASSWORD <", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_PASSWORD <=", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordLike(String value) {
            addCriterion("DEVICE_PASSWORD like", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordNotLike(String value) {
            addCriterion("DEVICE_PASSWORD not like", value, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordIn(List<String> values) {
            addCriterion("DEVICE_PASSWORD in", values, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordNotIn(List<String> values) {
            addCriterion("DEVICE_PASSWORD not in", values, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordBetween(String value1, String value2) {
            addCriterion("DEVICE_PASSWORD between", value1, value2, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePasswordNotBetween(String value1, String value2) {
            addCriterion("DEVICE_PASSWORD not between", value1, value2, "devicePassword");
            return (Criteria) this;
        }

        public Criteria andDevicePortIsNull() {
            addCriterion("DEVICE_PORT is null");
            return (Criteria) this;
        }

        public Criteria andDevicePortIsNotNull() {
            addCriterion("DEVICE_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andDevicePortEqualTo(String value) {
            addCriterion("DEVICE_PORT =", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortNotEqualTo(String value) {
            addCriterion("DEVICE_PORT <>", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortGreaterThan(String value) {
            addCriterion("DEVICE_PORT >", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_PORT >=", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortLessThan(String value) {
            addCriterion("DEVICE_PORT <", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_PORT <=", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortLike(String value) {
            addCriterion("DEVICE_PORT like", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortNotLike(String value) {
            addCriterion("DEVICE_PORT not like", value, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortIn(List<String> values) {
            addCriterion("DEVICE_PORT in", values, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortNotIn(List<String> values) {
            addCriterion("DEVICE_PORT not in", values, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortBetween(String value1, String value2) {
            addCriterion("DEVICE_PORT between", value1, value2, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDevicePortNotBetween(String value1, String value2) {
            addCriterion("DEVICE_PORT not between", value1, value2, "devicePort");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeIsNull() {
            addCriterion("DEVICE_SHAPE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeIsNotNull() {
            addCriterion("DEVICE_SHAPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeEqualTo(String value) {
            addCriterion("DEVICE_SHAPE =", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeNotEqualTo(String value) {
            addCriterion("DEVICE_SHAPE <>", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeGreaterThan(String value) {
            addCriterion("DEVICE_SHAPE >", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_SHAPE >=", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeLessThan(String value) {
            addCriterion("DEVICE_SHAPE <", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_SHAPE <=", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeLike(String value) {
            addCriterion("DEVICE_SHAPE like", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeNotLike(String value) {
            addCriterion("DEVICE_SHAPE not like", value, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeIn(List<String> values) {
            addCriterion("DEVICE_SHAPE in", values, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeNotIn(List<String> values) {
            addCriterion("DEVICE_SHAPE not in", values, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeBetween(String value1, String value2) {
            addCriterion("DEVICE_SHAPE between", value1, value2, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andDeviceShapeNotBetween(String value1, String value2) {
            addCriterion("DEVICE_SHAPE not between", value1, value2, "deviceShape");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNull() {
            addCriterion("INSERTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNotNull() {
            addCriterion("INSERTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInserttimeEqualTo(Date value) {
            addCriterionForJDBCDate("INSERTTIME =", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("INSERTTIME <>", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("INSERTTIME >", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSERTTIME >=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThan(Date value) {
            addCriterionForJDBCDate("INSERTTIME <", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSERTTIME <=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeIn(List<Date> values) {
            addCriterionForJDBCDate("INSERTTIME in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("INSERTTIME not in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSERTTIME between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSERTTIME not between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
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
        
        public Criteria andFactoryIdIsNull() {
            addCriterion("FACTORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNotNull() {
            addCriterion("FACTORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdEqualTo(String value) {
            addCriterion("FACTORY_ID =", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotEqualTo(String value) {
            addCriterion("FACTORY_ID <>", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThan(String value) {
            addCriterion("FACTORY_ID >", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("FACTORY_ID >=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThan(String value) {
            addCriterion("FACTORY_ID <", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThanOrEqualTo(String value) {
            addCriterion("FACTORY_ID <=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLike(String value) {
            addCriterion("FACTORY_ID like", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotLike(String value) {
            addCriterion("FACTORY_ID not like", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIn(List<String> values) {
            addCriterion("FACTORY_ID in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotIn(List<String> values) {
            addCriterion("FACTORY_ID not in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdBetween(String value1, String value2) {
            addCriterion("FACTORY_ID between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotBetween(String value1, String value2) {
            addCriterion("FACTORY_ID not between", value1, value2, "factoryId");
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