package com.xiangxun.atms.module.property.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AssetInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssetInfoSearch() {
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

        public Criteria andDeviceidIsNull() {
            addCriterion("DEVICEID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("DEVICEID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(String value) {
            addCriterion("DEVICEID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(String value) {
            addCriterion("DEVICEID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(String value) {
            addCriterion("DEVICEID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICEID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(String value) {
            addCriterion("DEVICEID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(String value) {
            addCriterion("DEVICEID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLike(String value) {
            addCriterion("DEVICEID like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotLike(String value) {
            addCriterion("DEVICEID not like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<String> values) {
            addCriterion("DEVICEID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<String> values) {
            addCriterion("DEVICEID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(String value1, String value2) {
            addCriterion("DEVICEID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(String value1, String value2) {
            addCriterion("DEVICEID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andAssetcodeIsNull() {
            addCriterion("ASSETCODE is null");
            return (Criteria) this;
        }

        public Criteria andAssetcodeIsNotNull() {
            addCriterion("ASSETCODE is not null");
            return (Criteria) this;
        }

        public Criteria andAssetcodeEqualTo(String value) {
            addCriterion("ASSETCODE =", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeNotEqualTo(String value) {
            addCriterion("ASSETCODE <>", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeGreaterThan(String value) {
            addCriterion("ASSETCODE >", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETCODE >=", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeLessThan(String value) {
            addCriterion("ASSETCODE <", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeLessThanOrEqualTo(String value) {
            addCriterion("ASSETCODE <=", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeLike(String value) {
            addCriterion("ASSETCODE like", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeNotLike(String value) {
            addCriterion("ASSETCODE not like", value, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeIn(List<String> values) {
            addCriterion("ASSETCODE in", values, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeNotIn(List<String> values) {
            addCriterion("ASSETCODE not in", values, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeBetween(String value1, String value2) {
            addCriterion("ASSETCODE between", value1, value2, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetcodeNotBetween(String value1, String value2) {
            addCriterion("ASSETCODE not between", value1, value2, "assetcode");
            return (Criteria) this;
        }

        public Criteria andAssetnameIsNull() {
            addCriterion("ASSETNAME is null");
            return (Criteria) this;
        }

        public Criteria andAssetnameIsNotNull() {
            addCriterion("ASSETNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssetnameEqualTo(String value) {
            addCriterion("ASSETNAME =", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameNotEqualTo(String value) {
            addCriterion("ASSETNAME <>", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameGreaterThan(String value) {
            addCriterion("ASSETNAME >", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETNAME >=", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameLessThan(String value) {
            addCriterion("ASSETNAME <", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameLessThanOrEqualTo(String value) {
            addCriterion("ASSETNAME <=", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameLike(String value) {
            addCriterion("ASSETNAME like", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameNotLike(String value) {
            addCriterion("ASSETNAME not like", value, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameIn(List<String> values) {
            addCriterion("ASSETNAME in", values, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameNotIn(List<String> values) {
            addCriterion("ASSETNAME not in", values, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameBetween(String value1, String value2) {
            addCriterion("ASSETNAME between", value1, value2, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetnameNotBetween(String value1, String value2) {
            addCriterion("ASSETNAME not between", value1, value2, "assetname");
            return (Criteria) this;
        }

        public Criteria andAssetmodelIsNull() {
            addCriterion("ASSETMODEL is null");
            return (Criteria) this;
        }

        public Criteria andAssetmodelIsNotNull() {
            addCriterion("ASSETMODEL is not null");
            return (Criteria) this;
        }

        public Criteria andAssetmodelEqualTo(String value) {
            addCriterion("ASSETMODEL =", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelNotEqualTo(String value) {
            addCriterion("ASSETMODEL <>", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelGreaterThan(String value) {
            addCriterion("ASSETMODEL >", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETMODEL >=", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelLessThan(String value) {
            addCriterion("ASSETMODEL <", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelLessThanOrEqualTo(String value) {
            addCriterion("ASSETMODEL <=", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelLike(String value) {
            addCriterion("ASSETMODEL like", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelNotLike(String value) {
            addCriterion("ASSETMODEL not like", value, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelIn(List<String> values) {
            addCriterion("ASSETMODEL in", values, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelNotIn(List<String> values) {
            addCriterion("ASSETMODEL not in", values, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelBetween(String value1, String value2) {
            addCriterion("ASSETMODEL between", value1, value2, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssetmodelNotBetween(String value1, String value2) {
            addCriterion("ASSETMODEL not between", value1, value2, "assetmodel");
            return (Criteria) this;
        }

        public Criteria andAssettypeIsNull() {
            addCriterion("ASSETTYPE is null");
            return (Criteria) this;
        }

        public Criteria andAssettypeIsNotNull() {
            addCriterion("ASSETTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAssettypeEqualTo(String value) {
            addCriterion("ASSETTYPE =", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeNotEqualTo(String value) {
            addCriterion("ASSETTYPE <>", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeGreaterThan(String value) {
            addCriterion("ASSETTYPE >", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETTYPE >=", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeLessThan(String value) {
            addCriterion("ASSETTYPE <", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeLessThanOrEqualTo(String value) {
            addCriterion("ASSETTYPE <=", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeLike(String value) {
            addCriterion("ASSETTYPE like", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeNotLike(String value) {
            addCriterion("ASSETTYPE not like", value, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeIn(List<String> values) {
            addCriterion("ASSETTYPE in", values, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeNotIn(List<String> values) {
            addCriterion("ASSETTYPE not in", values, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeBetween(String value1, String value2) {
            addCriterion("ASSETTYPE between", value1, value2, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssettypeNotBetween(String value1, String value2) {
            addCriterion("ASSETTYPE not between", value1, value2, "assettype");
            return (Criteria) this;
        }

        public Criteria andAssetstatusIsNull() {
            addCriterion("ASSETSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andAssetstatusIsNotNull() {
            addCriterion("ASSETSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAssetstatusEqualTo(String value) {
            addCriterion("ASSETSTATUS =", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusNotEqualTo(String value) {
            addCriterion("ASSETSTATUS <>", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusGreaterThan(String value) {
            addCriterion("ASSETSTATUS >", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETSTATUS >=", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusLessThan(String value) {
            addCriterion("ASSETSTATUS <", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusLessThanOrEqualTo(String value) {
            addCriterion("ASSETSTATUS <=", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusLike(String value) {
            addCriterion("ASSETSTATUS like", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusNotLike(String value) {
            addCriterion("ASSETSTATUS not like", value, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusIn(List<String> values) {
            addCriterion("ASSETSTATUS in", values, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusNotIn(List<String> values) {
            addCriterion("ASSETSTATUS not in", values, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusBetween(String value1, String value2) {
            addCriterion("ASSETSTATUS between", value1, value2, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andAssetstatusNotBetween(String value1, String value2) {
            addCriterion("ASSETSTATUS not between", value1, value2, "assetstatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeIsNull() {
            addCriterion("GUARANTEETIME is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeIsNotNull() {
            addCriterion("GUARANTEETIME is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeEqualTo(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME =", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME <>", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME >", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME >=", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeLessThan(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME <", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GUARANTEETIME <=", value, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeIn(List<Date> values) {
            addCriterionForJDBCDate("GUARANTEETIME in", values, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("GUARANTEETIME not in", values, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GUARANTEETIME between", value1, value2, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andGuaranteetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GUARANTEETIME not between", value1, value2, "guaranteetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIsNull() {
            addCriterion("PURCHASETIME is null");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIsNotNull() {
            addCriterion("PURCHASETIME is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeEqualTo(Date value) {
            addCriterionForJDBCDate("PURCHASETIME =", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PURCHASETIME <>", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PURCHASETIME >", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PURCHASETIME >=", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeLessThan(Date value) {
            addCriterionForJDBCDate("PURCHASETIME <", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PURCHASETIME <=", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIn(List<Date> values) {
            addCriterionForJDBCDate("PURCHASETIME in", values, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PURCHASETIME not in", values, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PURCHASETIME between", value1, value2, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PURCHASETIME not between", value1, value2, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("MANUFACTURER is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("MANUFACTURER is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("MANUFACTURER =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("MANUFACTURER <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("MANUFACTURER >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("MANUFACTURER <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("MANUFACTURER like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("MANUFACTURER not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("MANUFACTURER in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("MANUFACTURER not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("MANUFACTURER between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("MANUFACTURER not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andEngineeringIsNull() {
            addCriterion("ENGINEERING is null");
            return (Criteria) this;
        }

        public Criteria andEngineeringIsNotNull() {
            addCriterion("ENGINEERING is not null");
            return (Criteria) this;
        }

        public Criteria andEngineeringEqualTo(String value) {
            addCriterion("ENGINEERING =", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringNotEqualTo(String value) {
            addCriterion("ENGINEERING <>", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringGreaterThan(String value) {
            addCriterion("ENGINEERING >", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringGreaterThanOrEqualTo(String value) {
            addCriterion("ENGINEERING >=", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringLessThan(String value) {
            addCriterion("ENGINEERING <", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringLessThanOrEqualTo(String value) {
            addCriterion("ENGINEERING <=", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringLike(String value) {
            addCriterion("ENGINEERING like", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringNotLike(String value) {
            addCriterion("ENGINEERING not like", value, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringIn(List<String> values) {
            addCriterion("ENGINEERING in", values, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringNotIn(List<String> values) {
            addCriterion("ENGINEERING not in", values, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringBetween(String value1, String value2) {
            addCriterion("ENGINEERING between", value1, value2, "engineering");
            return (Criteria) this;
        }

        public Criteria andEngineeringNotBetween(String value1, String value2) {
            addCriterion("ENGINEERING not between", value1, value2, "engineering");
            return (Criteria) this;
        }

        public Criteria andInstalltimeIsNull() {
            addCriterion("INSTALLTIME is null");
            return (Criteria) this;
        }

        public Criteria andInstalltimeIsNotNull() {
            addCriterion("INSTALLTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInstalltimeEqualTo(Date value) {
            addCriterionForJDBCDate("INSTALLTIME =", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("INSTALLTIME <>", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeGreaterThan(Date value) {
            addCriterionForJDBCDate("INSTALLTIME >", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSTALLTIME >=", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeLessThan(Date value) {
            addCriterionForJDBCDate("INSTALLTIME <", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INSTALLTIME <=", value, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeIn(List<Date> values) {
            addCriterionForJDBCDate("INSTALLTIME in", values, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("INSTALLTIME not in", values, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSTALLTIME between", value1, value2, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstalltimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INSTALLTIME not between", value1, value2, "installtime");
            return (Criteria) this;
        }

        public Criteria andInstallplaceIsNull() {
            addCriterion("INSTALLPLACE is null");
            return (Criteria) this;
        }

        public Criteria andInstallplaceIsNotNull() {
            addCriterion("INSTALLPLACE is not null");
            return (Criteria) this;
        }

        public Criteria andInstallplaceEqualTo(String value) {
            addCriterion("INSTALLPLACE =", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceNotEqualTo(String value) {
            addCriterion("INSTALLPLACE <>", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceGreaterThan(String value) {
            addCriterion("INSTALLPLACE >", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceGreaterThanOrEqualTo(String value) {
            addCriterion("INSTALLPLACE >=", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceLessThan(String value) {
            addCriterion("INSTALLPLACE <", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceLessThanOrEqualTo(String value) {
            addCriterion("INSTALLPLACE <=", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceLike(String value) {
            addCriterion("INSTALLPLACE like", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceNotLike(String value) {
            addCriterion("INSTALLPLACE not like", value, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceIn(List<String> values) {
            addCriterion("INSTALLPLACE in", values, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceNotIn(List<String> values) {
            addCriterion("INSTALLPLACE not in", values, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceBetween(String value1, String value2) {
            addCriterion("INSTALLPLACE between", value1, value2, "installplace");
            return (Criteria) this;
        }

        public Criteria andInstallplaceNotBetween(String value1, String value2) {
            addCriterion("INSTALLPLACE not between", value1, value2, "installplace");
            return (Criteria) this;
        }

        public Criteria andServiceidIsNull() {
            addCriterion("SERVICEID is null");
            return (Criteria) this;
        }

        public Criteria andServiceidIsNotNull() {
            addCriterion("SERVICEID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceidEqualTo(String value) {
            addCriterion("SERVICEID =", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidNotEqualTo(String value) {
            addCriterion("SERVICEID <>", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidGreaterThan(String value) {
            addCriterion("SERVICEID >", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICEID >=", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidLessThan(String value) {
            addCriterion("SERVICEID <", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidLessThanOrEqualTo(String value) {
            addCriterion("SERVICEID <=", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidLike(String value) {
            addCriterion("SERVICEID like", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidNotLike(String value) {
            addCriterion("SERVICEID not like", value, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidIn(List<String> values) {
            addCriterion("SERVICEID in", values, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidNotIn(List<String> values) {
            addCriterion("SERVICEID not in", values, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidBetween(String value1, String value2) {
            addCriterion("SERVICEID between", value1, value2, "serviceid");
            return (Criteria) this;
        }

        public Criteria andServiceidNotBetween(String value1, String value2) {
            addCriterion("SERVICEID not between", value1, value2, "serviceid");
            return (Criteria) this;
        }

        public Criteria andMacaddressIsNull() {
            addCriterion("MACADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andMacaddressIsNotNull() {
            addCriterion("MACADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andMacaddressEqualTo(String value) {
            addCriterion("MACADDRESS =", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotEqualTo(String value) {
            addCriterion("MACADDRESS <>", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressGreaterThan(String value) {
            addCriterion("MACADDRESS >", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressGreaterThanOrEqualTo(String value) {
            addCriterion("MACADDRESS >=", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLessThan(String value) {
            addCriterion("MACADDRESS <", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLessThanOrEqualTo(String value) {
            addCriterion("MACADDRESS <=", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLike(String value) {
            addCriterion("MACADDRESS like", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotLike(String value) {
            addCriterion("MACADDRESS not like", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressIn(List<String> values) {
            addCriterion("MACADDRESS in", values, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotIn(List<String> values) {
            addCriterion("MACADDRESS not in", values, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressBetween(String value1, String value2) {
            addCriterion("MACADDRESS between", value1, value2, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotBetween(String value1, String value2) {
            addCriterion("MACADDRESS not between", value1, value2, "macaddress");
            return (Criteria) this;
        }

        public Criteria andCpuStatusIsNull() {
            addCriterion("CPU_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCpuStatusIsNotNull() {
            addCriterion("CPU_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCpuStatusEqualTo(String value) {
            addCriterion("CPU_STATUS =", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusNotEqualTo(String value) {
            addCriterion("CPU_STATUS <>", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusGreaterThan(String value) {
            addCriterion("CPU_STATUS >", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CPU_STATUS >=", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusLessThan(String value) {
            addCriterion("CPU_STATUS <", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusLessThanOrEqualTo(String value) {
            addCriterion("CPU_STATUS <=", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusLike(String value) {
            addCriterion("CPU_STATUS like", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusNotLike(String value) {
            addCriterion("CPU_STATUS not like", value, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusIn(List<String> values) {
            addCriterion("CPU_STATUS in", values, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusNotIn(List<String> values) {
            addCriterion("CPU_STATUS not in", values, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusBetween(String value1, String value2) {
            addCriterion("CPU_STATUS between", value1, value2, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andCpuStatusNotBetween(String value1, String value2) {
            addCriterion("CPU_STATUS not between", value1, value2, "cpuStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusIsNull() {
            addCriterion("MEMORY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusIsNotNull() {
            addCriterion("MEMORY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusEqualTo(String value) {
            addCriterion("MEMORY_STATUS =", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusNotEqualTo(String value) {
            addCriterion("MEMORY_STATUS <>", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusGreaterThan(String value) {
            addCriterion("MEMORY_STATUS >", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusGreaterThanOrEqualTo(String value) {
            addCriterion("MEMORY_STATUS >=", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusLessThan(String value) {
            addCriterion("MEMORY_STATUS <", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusLessThanOrEqualTo(String value) {
            addCriterion("MEMORY_STATUS <=", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusLike(String value) {
            addCriterion("MEMORY_STATUS like", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusNotLike(String value) {
            addCriterion("MEMORY_STATUS not like", value, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusIn(List<String> values) {
            addCriterion("MEMORY_STATUS in", values, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusNotIn(List<String> values) {
            addCriterion("MEMORY_STATUS not in", values, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusBetween(String value1, String value2) {
            addCriterion("MEMORY_STATUS between", value1, value2, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andMemoryStatusNotBetween(String value1, String value2) {
            addCriterion("MEMORY_STATUS not between", value1, value2, "memoryStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusIsNull() {
            addCriterion("DISK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDiskStatusIsNotNull() {
            addCriterion("DISK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDiskStatusEqualTo(String value) {
            addCriterion("DISK_STATUS =", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusNotEqualTo(String value) {
            addCriterion("DISK_STATUS <>", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusGreaterThan(String value) {
            addCriterion("DISK_STATUS >", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DISK_STATUS >=", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusLessThan(String value) {
            addCriterion("DISK_STATUS <", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusLessThanOrEqualTo(String value) {
            addCriterion("DISK_STATUS <=", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusLike(String value) {
            addCriterion("DISK_STATUS like", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusNotLike(String value) {
            addCriterion("DISK_STATUS not like", value, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusIn(List<String> values) {
            addCriterion("DISK_STATUS in", values, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusNotIn(List<String> values) {
            addCriterion("DISK_STATUS not in", values, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusBetween(String value1, String value2) {
            addCriterion("DISK_STATUS between", value1, value2, "diskStatus");
            return (Criteria) this;
        }

        public Criteria andDiskStatusNotBetween(String value1, String value2) {
            addCriterion("DISK_STATUS not between", value1, value2, "diskStatus");
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

        public Criteria andRefreshtimeIsNull() {
            addCriterion("REFRESHTIME is null");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeIsNotNull() {
            addCriterion("REFRESHTIME is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeEqualTo(Date value) {
            addCriterion("REFRESHTIME =", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeNotEqualTo(Date value) {
            addCriterion("REFRESHTIME <>", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeGreaterThan(Date value) {
            addCriterion("REFRESHTIME >", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REFRESHTIME >=", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeLessThan(Date value) {
            addCriterion("REFRESHTIME <", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeLessThanOrEqualTo(Date value) {
            addCriterion("REFRESHTIME <=", value, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeIn(List<Date> values) {
            addCriterion("REFRESHTIME in", values, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeNotIn(List<Date> values) {
            addCriterion("REFRESHTIME not in", values, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeBetween(Date value1, Date value2) {
            addCriterion("REFRESHTIME between", value1, value2, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andRefreshtimeNotBetween(Date value1, Date value2) {
            addCriterion("REFRESHTIME not between", value1, value2, "refreshtime");
            return (Criteria) this;
        }

        public Criteria andPowerStatusIsNull() {
            addCriterion("POWER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPowerStatusIsNotNull() {
            addCriterion("POWER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPowerStatusEqualTo(String value) {
            addCriterion("POWER_STATUS =", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusNotEqualTo(String value) {
            addCriterion("POWER_STATUS <>", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusGreaterThan(String value) {
            addCriterion("POWER_STATUS >", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_STATUS >=", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusLessThan(String value) {
            addCriterion("POWER_STATUS <", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusLessThanOrEqualTo(String value) {
            addCriterion("POWER_STATUS <=", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusLike(String value) {
            addCriterion("POWER_STATUS like", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusNotLike(String value) {
            addCriterion("POWER_STATUS not like", value, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusIn(List<String> values) {
            addCriterion("POWER_STATUS in", values, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusNotIn(List<String> values) {
            addCriterion("POWER_STATUS not in", values, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusBetween(String value1, String value2) {
            addCriterion("POWER_STATUS between", value1, value2, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andPowerStatusNotBetween(String value1, String value2) {
            addCriterion("POWER_STATUS not between", value1, value2, "powerStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusIsNull() {
            addCriterion("NET_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andNetStatusIsNotNull() {
            addCriterion("NET_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andNetStatusEqualTo(String value) {
            addCriterion("NET_STATUS =", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotEqualTo(String value) {
            addCriterion("NET_STATUS <>", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusGreaterThan(String value) {
            addCriterion("NET_STATUS >", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusGreaterThanOrEqualTo(String value) {
            addCriterion("NET_STATUS >=", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusLessThan(String value) {
            addCriterion("NET_STATUS <", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusLessThanOrEqualTo(String value) {
            addCriterion("NET_STATUS <=", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusLike(String value) {
            addCriterion("NET_STATUS like", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotLike(String value) {
            addCriterion("NET_STATUS not like", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusIn(List<String> values) {
            addCriterion("NET_STATUS in", values, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotIn(List<String> values) {
            addCriterion("NET_STATUS not in", values, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusBetween(String value1, String value2) {
            addCriterion("NET_STATUS between", value1, value2, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotBetween(String value1, String value2) {
            addCriterion("NET_STATUS not between", value1, value2, "netStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNull() {
            addCriterion("DATA_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNotNull() {
            addCriterion("DATA_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDataStatusEqualTo(String value) {
            addCriterion("DATA_STATUS =", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotEqualTo(String value) {
            addCriterion("DATA_STATUS <>", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThan(String value) {
            addCriterion("DATA_STATUS >", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_STATUS >=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThan(String value) {
            addCriterion("DATA_STATUS <", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThanOrEqualTo(String value) {
            addCriterion("DATA_STATUS <=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLike(String value) {
            addCriterion("DATA_STATUS like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotLike(String value) {
            addCriterion("DATA_STATUS not like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIn(List<String> values) {
            addCriterion("DATA_STATUS in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotIn(List<String> values) {
            addCriterion("DATA_STATUS not in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusBetween(String value1, String value2) {
            addCriterion("DATA_STATUS between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotBetween(String value1, String value2) {
            addCriterion("DATA_STATUS not between", value1, value2, "dataStatus");
            return (Criteria) this;
        }
        
        public Criteria andPayoutstatusIsNull() {
            addCriterion("PAYOUTSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusIsNotNull() {
            addCriterion("PAYOUTSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusEqualTo(String value) {
            addCriterion("PAYOUTSTATUS =", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusNotEqualTo(String value) {
            addCriterion("PAYOUTSTATUS <>", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusGreaterThan(String value) {
            addCriterion("PAYOUTSTATUS >", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusGreaterThanOrEqualTo(String value) {
            addCriterion("PAYOUTSTATUS >=", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusLessThan(String value) {
            addCriterion("PAYOUTSTATUS <", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusLessThanOrEqualTo(String value) {
            addCriterion("PAYOUTSTATUS <=", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusLike(String value) {
            addCriterion("PAYOUTSTATUS like", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusNotLike(String value) {
            addCriterion("PAYOUTSTATUS not like", value, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusIn(List<String> values) {
            addCriterion("PAYOUTSTATUS in", values, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusNotIn(List<String> values) {
            addCriterion("PAYOUTSTATUS not in", values, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusBetween(String value1, String value2) {
            addCriterion("PAYOUTSTATUS between", value1, value2, "payoutstatus");
            return (Criteria) this;
        }

        public Criteria andPayoutstatusNotBetween(String value1, String value2) {
            addCriterion("PAYOUTSTATUS not between", value1, value2, "payoutstatus");
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