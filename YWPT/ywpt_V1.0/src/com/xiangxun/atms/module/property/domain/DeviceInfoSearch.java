package com.xiangxun.atms.module.property.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeviceInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceInfoSearch() {
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("COMPANY_ID like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("COMPANY_ID not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andFtpIdIsNull() {
            addCriterion("FTP_ID is null");
            return (Criteria) this;
        }

        public Criteria andFtpIdIsNotNull() {
            addCriterion("FTP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFtpIdEqualTo(String value) {
            addCriterion("FTP_ID =", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdNotEqualTo(String value) {
            addCriterion("FTP_ID <>", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdGreaterThan(String value) {
            addCriterion("FTP_ID >", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_ID >=", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdLessThan(String value) {
            addCriterion("FTP_ID <", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdLessThanOrEqualTo(String value) {
            addCriterion("FTP_ID <=", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdLike(String value) {
            addCriterion("FTP_ID like", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdNotLike(String value) {
            addCriterion("FTP_ID not like", value, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdIn(List<String> values) {
            addCriterion("FTP_ID in", values, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdNotIn(List<String> values) {
            addCriterion("FTP_ID not in", values, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdBetween(String value1, String value2) {
            addCriterion("FTP_ID between", value1, value2, "ftpId");
            return (Criteria) this;
        }

        public Criteria andFtpIdNotBetween(String value1, String value2) {
            addCriterion("FTP_ID not between", value1, value2, "ftpId");
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
            addCriterion("ROAD_ID =", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotEqualTo(String value) {
            addCriterion("ROAD_ID <>", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdGreaterThan(String value) {
            addCriterion("ROAD_ID >", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROAD_ID >=", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdLessThan(String value) {
            addCriterion("ROAD_ID <", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdLessThanOrEqualTo(String value) {
            addCriterion("ROAD_ID <=", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdLike(String value) {
            addCriterion("ROAD_ID like", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotLike(String value) {
            addCriterion("ROAD_ID not like", value, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdIn(List<String> values) {
            addCriterion("ROAD_ID in", values, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotIn(List<String> values) {
            addCriterion("ROAD_ID not in", values, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdBetween(String value1, String value2) {
            addCriterion("ROAD_ID between", value1, value2, "roadId");
            return (Criteria) this;
        }

        public Criteria andRoadIdNotBetween(String value1, String value2) {
            addCriterion("ROAD_ID not between", value1, value2, "roadId");
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

        public Criteria andBuildtimeIsNull() {
            addCriterion("BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andBuildtimeIsNotNull() {
            addCriterion("BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andBuildtimeEqualTo(Date value) {
            addCriterionForJDBCDate("BUILDTIME =", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("BUILDTIME <>", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("BUILDTIME >", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BUILDTIME >=", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeLessThan(Date value) {
            addCriterionForJDBCDate("BUILDTIME <", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BUILDTIME <=", value, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeIn(List<Date> values) {
            addCriterionForJDBCDate("BUILDTIME in", values, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("BUILDTIME not in", values, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BUILDTIME between", value1, value2, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BUILDTIME not between", value1, value2, "buildtime");
            return (Criteria) this;
        }

        public Criteria andBuildispIdIsNull() {
            addCriterion("BUILDISP_ID is null");
            return (Criteria) this;
        }

        public Criteria andBuildispIdIsNotNull() {
            addCriterion("BUILDISP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBuildispIdEqualTo(String value) {
            addCriterion("BUILDISP_ID =", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdNotEqualTo(String value) {
            addCriterion("BUILDISP_ID <>", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdGreaterThan(String value) {
            addCriterion("BUILDISP_ID >", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDISP_ID >=", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdLessThan(String value) {
            addCriterion("BUILDISP_ID <", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdLessThanOrEqualTo(String value) {
            addCriterion("BUILDISP_ID <=", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdLike(String value) {
            addCriterion("BUILDISP_ID like", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdNotLike(String value) {
            addCriterion("BUILDISP_ID not like", value, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdIn(List<String> values) {
            addCriterion("BUILDISP_ID in", values, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdNotIn(List<String> values) {
            addCriterion("BUILDISP_ID not in", values, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdBetween(String value1, String value2) {
            addCriterion("BUILDISP_ID between", value1, value2, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildispIdNotBetween(String value1, String value2) {
            addCriterion("BUILDISP_ID not between", value1, value2, "buildispId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdIsNull() {
            addCriterion("BUILDNETWORK_ID is null");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdIsNotNull() {
            addCriterion("BUILDNETWORK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdEqualTo(String value) {
            addCriterion("BUILDNETWORK_ID =", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdNotEqualTo(String value) {
            addCriterion("BUILDNETWORK_ID <>", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdGreaterThan(String value) {
            addCriterion("BUILDNETWORK_ID >", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDNETWORK_ID >=", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdLessThan(String value) {
            addCriterion("BUILDNETWORK_ID <", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdLessThanOrEqualTo(String value) {
            addCriterion("BUILDNETWORK_ID <=", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdLike(String value) {
            addCriterion("BUILDNETWORK_ID like", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdNotLike(String value) {
            addCriterion("BUILDNETWORK_ID not like", value, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdIn(List<String> values) {
            addCriterion("BUILDNETWORK_ID in", values, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdNotIn(List<String> values) {
            addCriterion("BUILDNETWORK_ID not in", values, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdBetween(String value1, String value2) {
            addCriterion("BUILDNETWORK_ID between", value1, value2, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andBuildnetworkIdNotBetween(String value1, String value2) {
            addCriterion("BUILDNETWORK_ID not between", value1, value2, "buildnetworkId");
            return (Criteria) this;
        }

        public Criteria andPowersourceIsNull() {
            addCriterion("POWERSOURCE is null");
            return (Criteria) this;
        }

        public Criteria andPowersourceIsNotNull() {
            addCriterion("POWERSOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andPowersourceEqualTo(String value) {
            addCriterion("POWERSOURCE =", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceNotEqualTo(String value) {
            addCriterion("POWERSOURCE <>", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceGreaterThan(String value) {
            addCriterion("POWERSOURCE >", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceGreaterThanOrEqualTo(String value) {
            addCriterion("POWERSOURCE >=", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceLessThan(String value) {
            addCriterion("POWERSOURCE <", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceLessThanOrEqualTo(String value) {
            addCriterion("POWERSOURCE <=", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceLike(String value) {
            addCriterion("POWERSOURCE like", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceNotLike(String value) {
            addCriterion("POWERSOURCE not like", value, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceIn(List<String> values) {
            addCriterion("POWERSOURCE in", values, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceNotIn(List<String> values) {
            addCriterion("POWERSOURCE not in", values, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceBetween(String value1, String value2) {
            addCriterion("POWERSOURCE between", value1, value2, "powersource");
            return (Criteria) this;
        }

        public Criteria andPowersourceNotBetween(String value1, String value2) {
            addCriterion("POWERSOURCE not between", value1, value2, "powersource");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("TIMEOUT is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("TIMEOUT is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(BigDecimal value) {
            addCriterion("TIMEOUT =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(BigDecimal value) {
            addCriterion("TIMEOUT <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(BigDecimal value) {
            addCriterion("TIMEOUT >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TIMEOUT >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(BigDecimal value) {
            addCriterion("TIMEOUT <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TIMEOUT <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<BigDecimal> values) {
            addCriterion("TIMEOUT in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<BigDecimal> values) {
            addCriterion("TIMEOUT not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TIMEOUT between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TIMEOUT not between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseIsNull() {
            addCriterion("ISBLACKANALYSE is null");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseIsNotNull() {
            addCriterion("ISBLACKANALYSE is not null");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseEqualTo(String value) {
            addCriterion("ISBLACKANALYSE =", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseNotEqualTo(String value) {
            addCriterion("ISBLACKANALYSE <>", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseGreaterThan(String value) {
            addCriterion("ISBLACKANALYSE >", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseGreaterThanOrEqualTo(String value) {
            addCriterion("ISBLACKANALYSE >=", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseLessThan(String value) {
            addCriterion("ISBLACKANALYSE <", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseLessThanOrEqualTo(String value) {
            addCriterion("ISBLACKANALYSE <=", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseLike(String value) {
            addCriterion("ISBLACKANALYSE like", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseNotLike(String value) {
            addCriterion("ISBLACKANALYSE not like", value, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseIn(List<String> values) {
            addCriterion("ISBLACKANALYSE in", values, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseNotIn(List<String> values) {
            addCriterion("ISBLACKANALYSE not in", values, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseBetween(String value1, String value2) {
            addCriterion("ISBLACKANALYSE between", value1, value2, "isblackanalyse");
            return (Criteria) this;
        }

        public Criteria andIsblackanalyseNotBetween(String value1, String value2) {
            addCriterion("ISBLACKANALYSE not between", value1, value2, "isblackanalyse");
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

        public Criteria andImportcodeIsNull() {
            addCriterion("IMPORTCODE is null");
            return (Criteria) this;
        }

        public Criteria andImportcodeIsNotNull() {
            addCriterion("IMPORTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andImportcodeEqualTo(String value) {
            addCriterion("IMPORTCODE =", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeNotEqualTo(String value) {
            addCriterion("IMPORTCODE <>", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeGreaterThan(String value) {
            addCriterion("IMPORTCODE >", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeGreaterThanOrEqualTo(String value) {
            addCriterion("IMPORTCODE >=", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeLessThan(String value) {
            addCriterion("IMPORTCODE <", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeLessThanOrEqualTo(String value) {
            addCriterion("IMPORTCODE <=", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeLike(String value) {
            addCriterion("IMPORTCODE like", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeNotLike(String value) {
            addCriterion("IMPORTCODE not like", value, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeIn(List<String> values) {
            addCriterion("IMPORTCODE in", values, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeNotIn(List<String> values) {
            addCriterion("IMPORTCODE not in", values, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeBetween(String value1, String value2) {
            addCriterion("IMPORTCODE between", value1, value2, "importcode");
            return (Criteria) this;
        }

        public Criteria andImportcodeNotBetween(String value1, String value2) {
            addCriterion("IMPORTCODE not between", value1, value2, "importcode");
            return (Criteria) this;
        }

        public Criteria andLicenseIsNull() {
            addCriterion("LICENSE is null");
            return (Criteria) this;
        }

        public Criteria andLicenseIsNotNull() {
            addCriterion("LICENSE is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseEqualTo(String value) {
            addCriterion("LICENSE =", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotEqualTo(String value) {
            addCriterion("LICENSE <>", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseGreaterThan(String value) {
            addCriterion("LICENSE >", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("LICENSE >=", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLessThan(String value) {
            addCriterion("LICENSE <", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLessThanOrEqualTo(String value) {
            addCriterion("LICENSE <=", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseLike(String value) {
            addCriterion("LICENSE like", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotLike(String value) {
            addCriterion("LICENSE not like", value, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseIn(List<String> values) {
            addCriterion("LICENSE in", values, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotIn(List<String> values) {
            addCriterion("LICENSE not in", values, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseBetween(String value1, String value2) {
            addCriterion("LICENSE between", value1, value2, "license");
            return (Criteria) this;
        }

        public Criteria andLicenseNotBetween(String value1, String value2) {
            addCriterion("LICENSE not between", value1, value2, "license");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeIsNull() {
            addCriterion("LASTDATATIME is null");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeIsNotNull() {
            addCriterion("LASTDATATIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeEqualTo(Date value) {
            addCriterionForJDBCDate("LASTDATATIME =", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("LASTDATATIME <>", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeGreaterThan(Date value) {
            addCriterionForJDBCDate("LASTDATATIME >", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LASTDATATIME >=", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeLessThan(Date value) {
            addCriterionForJDBCDate("LASTDATATIME <", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LASTDATATIME <=", value, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeIn(List<Date> values) {
            addCriterionForJDBCDate("LASTDATATIME in", values, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("LASTDATATIME not in", values, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LASTDATATIME between", value1, value2, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andLastdatatimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LASTDATATIME not between", value1, value2, "lastdatatime");
            return (Criteria) this;
        }

        public Criteria andPicmixIdIsNull() {
            addCriterion("PICMIX_ID is null");
            return (Criteria) this;
        }

        public Criteria andPicmixIdIsNotNull() {
            addCriterion("PICMIX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPicmixIdEqualTo(String value) {
            addCriterion("PICMIX_ID =", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdNotEqualTo(String value) {
            addCriterion("PICMIX_ID <>", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdGreaterThan(String value) {
            addCriterion("PICMIX_ID >", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdGreaterThanOrEqualTo(String value) {
            addCriterion("PICMIX_ID >=", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdLessThan(String value) {
            addCriterion("PICMIX_ID <", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdLessThanOrEqualTo(String value) {
            addCriterion("PICMIX_ID <=", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdLike(String value) {
            addCriterion("PICMIX_ID like", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdNotLike(String value) {
            addCriterion("PICMIX_ID not like", value, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdIn(List<String> values) {
            addCriterion("PICMIX_ID in", values, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdNotIn(List<String> values) {
            addCriterion("PICMIX_ID not in", values, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdBetween(String value1, String value2) {
            addCriterion("PICMIX_ID between", value1, value2, "picmixId");
            return (Criteria) this;
        }

        public Criteria andPicmixIdNotBetween(String value1, String value2) {
            addCriterion("PICMIX_ID not between", value1, value2, "picmixId");
            return (Criteria) this;
        }

        public Criteria andToporgcodeIsNull() {
            addCriterion("TOPORGCODE is null");
            return (Criteria) this;
        }

        public Criteria andToporgcodeIsNotNull() {
            addCriterion("TOPORGCODE is not null");
            return (Criteria) this;
        }

        public Criteria andToporgcodeEqualTo(String value) {
            addCriterion("TOPORGCODE =", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeNotEqualTo(String value) {
            addCriterion("TOPORGCODE <>", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeGreaterThan(String value) {
            addCriterion("TOPORGCODE >", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TOPORGCODE >=", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeLessThan(String value) {
            addCriterion("TOPORGCODE <", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeLessThanOrEqualTo(String value) {
            addCriterion("TOPORGCODE <=", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeLike(String value) {
            addCriterion("TOPORGCODE like", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeNotLike(String value) {
            addCriterion("TOPORGCODE not like", value, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeIn(List<String> values) {
            addCriterion("TOPORGCODE in", values, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeNotIn(List<String> values) {
            addCriterion("TOPORGCODE not in", values, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeBetween(String value1, String value2) {
            addCriterion("TOPORGCODE between", value1, value2, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andToporgcodeNotBetween(String value1, String value2) {
            addCriterion("TOPORGCODE not between", value1, value2, "toporgcode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeIsNull() {
            addCriterion("DEVICETYPECODE is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeIsNotNull() {
            addCriterion("DEVICETYPECODE is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeEqualTo(String value) {
            addCriterion("DEVICETYPECODE =", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeNotEqualTo(String value) {
            addCriterion("DEVICETYPECODE <>", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeGreaterThan(String value) {
            addCriterion("DEVICETYPECODE >", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICETYPECODE >=", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeLessThan(String value) {
            addCriterion("DEVICETYPECODE <", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeLessThanOrEqualTo(String value) {
            addCriterion("DEVICETYPECODE <=", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeLike(String value) {
            addCriterion("DEVICETYPECODE like", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeNotLike(String value) {
            addCriterion("DEVICETYPECODE not like", value, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeIn(List<String> values) {
            addCriterion("DEVICETYPECODE in", values, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeNotIn(List<String> values) {
            addCriterion("DEVICETYPECODE not in", values, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeBetween(String value1, String value2) {
            addCriterion("DEVICETYPECODE between", value1, value2, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andDevicetypecodeNotBetween(String value1, String value2) {
            addCriterion("DEVICETYPECODE not between", value1, value2, "devicetypecode");
            return (Criteria) this;
        }

        public Criteria andTrademarkIsNull() {
            addCriterion("TRADEMARK is null");
            return (Criteria) this;
        }

        public Criteria andTrademarkIsNotNull() {
            addCriterion("TRADEMARK is not null");
            return (Criteria) this;
        }

        public Criteria andTrademarkEqualTo(String value) {
            addCriterion("TRADEMARK =", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotEqualTo(String value) {
            addCriterion("TRADEMARK <>", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkGreaterThan(String value) {
            addCriterion("TRADEMARK >", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkGreaterThanOrEqualTo(String value) {
            addCriterion("TRADEMARK >=", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLessThan(String value) {
            addCriterion("TRADEMARK <", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLessThanOrEqualTo(String value) {
            addCriterion("TRADEMARK <=", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLike(String value) {
            addCriterion("TRADEMARK like", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotLike(String value) {
            addCriterion("TRADEMARK not like", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkIn(List<String> values) {
            addCriterion("TRADEMARK in", values, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotIn(List<String> values) {
            addCriterion("TRADEMARK not in", values, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkBetween(String value1, String value2) {
            addCriterion("TRADEMARK between", value1, value2, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotBetween(String value1, String value2) {
            addCriterion("TRADEMARK not between", value1, value2, "trademark");
            return (Criteria) this;
        }

        public Criteria andPatternIsNull() {
            addCriterion("PATTERN is null");
            return (Criteria) this;
        }

        public Criteria andPatternIsNotNull() {
            addCriterion("PATTERN is not null");
            return (Criteria) this;
        }

        public Criteria andPatternEqualTo(String value) {
            addCriterion("PATTERN =", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotEqualTo(String value) {
            addCriterion("PATTERN <>", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThan(String value) {
            addCriterion("PATTERN >", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThanOrEqualTo(String value) {
            addCriterion("PATTERN >=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThan(String value) {
            addCriterion("PATTERN <", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThanOrEqualTo(String value) {
            addCriterion("PATTERN <=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLike(String value) {
            addCriterion("PATTERN like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotLike(String value) {
            addCriterion("PATTERN not like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternIn(List<String> values) {
            addCriterion("PATTERN in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotIn(List<String> values) {
            addCriterion("PATTERN not in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternBetween(String value1, String value2) {
            addCriterion("PATTERN between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotBetween(String value1, String value2) {
            addCriterion("PATTERN not between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeIsNull() {
            addCriterion("EFFICACIOUS_STIME is null");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeIsNotNull() {
            addCriterion("EFFICACIOUS_STIME is not null");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME =", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME <>", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME >", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME >=", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeLessThan(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME <", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME <=", value, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeIn(List<Date> values) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME in", values, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME not in", values, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME between", value1, value2, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousStimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFICACIOUS_STIME not between", value1, value2, "efficaciousStime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeIsNull() {
            addCriterion("EFFICACIOUS_ETIME is null");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeIsNotNull() {
            addCriterion("EFFICACIOUS_ETIME is not null");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME =", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME <>", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME >", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME >=", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeLessThan(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME <", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME <=", value, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeIn(List<Date> values) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME in", values, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME not in", values, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME between", value1, value2, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andEfficaciousEtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFICACIOUS_ETIME not between", value1, value2, "efficaciousEtime");
            return (Criteria) this;
        }

        public Criteria andInspectorgIsNull() {
            addCriterion("INSPECTORG is null");
            return (Criteria) this;
        }

        public Criteria andInspectorgIsNotNull() {
            addCriterion("INSPECTORG is not null");
            return (Criteria) this;
        }

        public Criteria andInspectorgEqualTo(String value) {
            addCriterion("INSPECTORG =", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgNotEqualTo(String value) {
            addCriterion("INSPECTORG <>", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgGreaterThan(String value) {
            addCriterion("INSPECTORG >", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgGreaterThanOrEqualTo(String value) {
            addCriterion("INSPECTORG >=", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgLessThan(String value) {
            addCriterion("INSPECTORG <", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgLessThanOrEqualTo(String value) {
            addCriterion("INSPECTORG <=", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgLike(String value) {
            addCriterion("INSPECTORG like", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgNotLike(String value) {
            addCriterion("INSPECTORG not like", value, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgIn(List<String> values) {
            addCriterion("INSPECTORG in", values, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgNotIn(List<String> values) {
            addCriterion("INSPECTORG not in", values, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgBetween(String value1, String value2) {
            addCriterion("INSPECTORG between", value1, value2, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspectorgNotBetween(String value1, String value2) {
            addCriterion("INSPECTORG not between", value1, value2, "inspectorg");
            return (Criteria) this;
        }

        public Criteria andInspecttimeIsNull() {
            addCriterion("INSPECTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInspecttimeIsNotNull() {
            addCriterion("INSPECTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInspecttimeEqualTo(Date value) {
            addCriterionForJDBCDate("INSPECTTIME =", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("INSPECTTIME <>", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("INSPECTTIME >", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSPECTTIME >=", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeLessThan(Date value) {
            addCriterionForJDBCDate("INSPECTTIME <", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSPECTTIME <=", value, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeIn(List<Date> values) {
            addCriterionForJDBCDate("INSPECTTIME in", values, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("INSPECTTIME not in", values, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSPECTTIME between", value1, value2, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspecttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSPECTTIME not between", value1, value2, "inspecttime");
            return (Criteria) this;
        }

        public Criteria andInspectnumberIsNull() {
            addCriterion("INSPECTNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andInspectnumberIsNotNull() {
            addCriterion("INSPECTNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andInspectnumberEqualTo(String value) {
            addCriterion("INSPECTNUMBER =", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberNotEqualTo(String value) {
            addCriterion("INSPECTNUMBER <>", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberGreaterThan(String value) {
            addCriterion("INSPECTNUMBER >", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberGreaterThanOrEqualTo(String value) {
            addCriterion("INSPECTNUMBER >=", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberLessThan(String value) {
            addCriterion("INSPECTNUMBER <", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberLessThanOrEqualTo(String value) {
            addCriterion("INSPECTNUMBER <=", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberLike(String value) {
            addCriterion("INSPECTNUMBER like", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberNotLike(String value) {
            addCriterion("INSPECTNUMBER not like", value, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberIn(List<String> values) {
            addCriterion("INSPECTNUMBER in", values, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberNotIn(List<String> values) {
            addCriterion("INSPECTNUMBER not in", values, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberBetween(String value1, String value2) {
            addCriterion("INSPECTNUMBER between", value1, value2, "inspectnumber");
            return (Criteria) this;
        }

        public Criteria andInspectnumberNotBetween(String value1, String value2) {
            addCriterion("INSPECTNUMBER not between", value1, value2, "inspectnumber");
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

        public Criteria andVideoCodeIsNull() {
            addCriterion("VIDEO_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVideoCodeIsNotNull() {
            addCriterion("VIDEO_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVideoCodeEqualTo(String value) {
            addCriterion("VIDEO_CODE =", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeNotEqualTo(String value) {
            addCriterion("VIDEO_CODE <>", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeGreaterThan(String value) {
            addCriterion("VIDEO_CODE >", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VIDEO_CODE >=", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeLessThan(String value) {
            addCriterion("VIDEO_CODE <", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeLessThanOrEqualTo(String value) {
            addCriterion("VIDEO_CODE <=", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeLike(String value) {
            addCriterion("VIDEO_CODE like", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeNotLike(String value) {
            addCriterion("VIDEO_CODE not like", value, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeIn(List<String> values) {
            addCriterion("VIDEO_CODE in", values, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeNotIn(List<String> values) {
            addCriterion("VIDEO_CODE not in", values, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeBetween(String value1, String value2) {
            addCriterion("VIDEO_CODE between", value1, value2, "videoCode");
            return (Criteria) this;
        }

        public Criteria andVideoCodeNotBetween(String value1, String value2) {
            addCriterion("VIDEO_CODE not between", value1, value2, "videoCode");
            return (Criteria) this;
        }

        public Criteria andIssendIsNull() {
            addCriterion("ISSEND is null");
            return (Criteria) this;
        }

        public Criteria andIssendIsNotNull() {
            addCriterion("ISSEND is not null");
            return (Criteria) this;
        }

        public Criteria andIssendEqualTo(String value) {
            addCriterion("ISSEND =", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotEqualTo(String value) {
            addCriterion("ISSEND <>", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendGreaterThan(String value) {
            addCriterion("ISSEND >", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendGreaterThanOrEqualTo(String value) {
            addCriterion("ISSEND >=", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendLessThan(String value) {
            addCriterion("ISSEND <", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendLessThanOrEqualTo(String value) {
            addCriterion("ISSEND <=", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendLike(String value) {
            addCriterion("ISSEND like", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotLike(String value) {
            addCriterion("ISSEND not like", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendIn(List<String> values) {
            addCriterion("ISSEND in", values, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotIn(List<String> values) {
            addCriterion("ISSEND not in", values, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendBetween(String value1, String value2) {
            addCriterion("ISSEND between", value1, value2, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotBetween(String value1, String value2) {
            addCriterion("ISSEND not between", value1, value2, "issend");
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