package com.xiangxun.atms.module.eventalarm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WorkorderInfoSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkorderInfoSearch() {
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

        public Criteria andDevicenameIsNull() {
            addCriterion("DEVICENAME is null");
            return (Criteria) this;
        }

        public Criteria andDevicenameIsNotNull() {
            addCriterion("DEVICENAME is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenameEqualTo(String value) {
            addCriterion("DEVICENAME =", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotEqualTo(String value) {
            addCriterion("DEVICENAME <>", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThan(String value) {
            addCriterion("DEVICENAME >", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICENAME >=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThan(String value) {
            addCriterion("DEVICENAME <", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThanOrEqualTo(String value) {
            addCriterion("DEVICENAME <=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLike(String value) {
            addCriterion("DEVICENAME like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotLike(String value) {
            addCriterion("DEVICENAME not like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameIn(List<String> values) {
            addCriterion("DEVICENAME in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotIn(List<String> values) {
            addCriterion("DEVICENAME not in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameBetween(String value1, String value2) {
            addCriterion("DEVICENAME between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotBetween(String value1, String value2) {
            addCriterion("DEVICENAME not between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicecodeIsNull() {
            addCriterion("DEVICECODE is null");
            return (Criteria) this;
        }

        public Criteria andDevicecodeIsNotNull() {
            addCriterion("DEVICECODE is not null");
            return (Criteria) this;
        }

        public Criteria andDevicecodeEqualTo(String value) {
            addCriterion("DEVICECODE =", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeNotEqualTo(String value) {
            addCriterion("DEVICECODE <>", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeGreaterThan(String value) {
            addCriterion("DEVICECODE >", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICECODE >=", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeLessThan(String value) {
            addCriterion("DEVICECODE <", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeLessThanOrEqualTo(String value) {
            addCriterion("DEVICECODE <=", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeLike(String value) {
            addCriterion("DEVICECODE like", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeNotLike(String value) {
            addCriterion("DEVICECODE not like", value, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeIn(List<String> values) {
            addCriterion("DEVICECODE in", values, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeNotIn(List<String> values) {
            addCriterion("DEVICECODE not in", values, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeBetween(String value1, String value2) {
            addCriterion("DEVICECODE between", value1, value2, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDevicecodeNotBetween(String value1, String value2) {
            addCriterion("DEVICECODE not between", value1, value2, "devicecode");
            return (Criteria) this;
        }

        public Criteria andDeviceipIsNull() {
            addCriterion("DEVICEIP is null");
            return (Criteria) this;
        }

        public Criteria andDeviceipIsNotNull() {
            addCriterion("DEVICEIP is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceipEqualTo(String value) {
            addCriterion("DEVICEIP =", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipNotEqualTo(String value) {
            addCriterion("DEVICEIP <>", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipGreaterThan(String value) {
            addCriterion("DEVICEIP >", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICEIP >=", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipLessThan(String value) {
            addCriterion("DEVICEIP <", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipLessThanOrEqualTo(String value) {
            addCriterion("DEVICEIP <=", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipLike(String value) {
            addCriterion("DEVICEIP like", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipNotLike(String value) {
            addCriterion("DEVICEIP not like", value, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipIn(List<String> values) {
            addCriterion("DEVICEIP in", values, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipNotIn(List<String> values) {
            addCriterion("DEVICEIP not in", values, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipBetween(String value1, String value2) {
            addCriterion("DEVICEIP between", value1, value2, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDeviceipNotBetween(String value1, String value2) {
            addCriterion("DEVICEIP not between", value1, value2, "deviceip");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNull() {
            addCriterion("DEVICETYPE is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNotNull() {
            addCriterion("DEVICETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeEqualTo(String value) {
            addCriterion("DEVICETYPE =", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotEqualTo(String value) {
            addCriterion("DEVICETYPE <>", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThan(String value) {
            addCriterion("DEVICETYPE >", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICETYPE >=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThan(String value) {
            addCriterion("DEVICETYPE <", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThanOrEqualTo(String value) {
            addCriterion("DEVICETYPE <=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLike(String value) {
            addCriterion("DEVICETYPE like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotLike(String value) {
            addCriterion("DEVICETYPE not like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIn(List<String> values) {
            addCriterion("DEVICETYPE in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotIn(List<String> values) {
            addCriterion("DEVICETYPE not in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeBetween(String value1, String value2) {
            addCriterion("DEVICETYPE between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotBetween(String value1, String value2) {
            addCriterion("DEVICETYPE not between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andIsouterIsNull() {
            addCriterion("ISOUTER is null");
            return (Criteria) this;
        }

        public Criteria andIsouterIsNotNull() {
            addCriterion("ISOUTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsouterEqualTo(String value) {
            addCriterion("ISOUTER =", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterNotEqualTo(String value) {
            addCriterion("ISOUTER <>", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterGreaterThan(String value) {
            addCriterion("ISOUTER >", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterGreaterThanOrEqualTo(String value) {
            addCriterion("ISOUTER >=", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterLessThan(String value) {
            addCriterion("ISOUTER <", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterLessThanOrEqualTo(String value) {
            addCriterion("ISOUTER <=", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterLike(String value) {
            addCriterion("ISOUTER like", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterNotLike(String value) {
            addCriterion("ISOUTER not like", value, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterIn(List<String> values) {
            addCriterion("ISOUTER in", values, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterNotIn(List<String> values) {
            addCriterion("ISOUTER not in", values, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterBetween(String value1, String value2) {
            addCriterion("ISOUTER between", value1, value2, "isouter");
            return (Criteria) this;
        }

        public Criteria andIsouterNotBetween(String value1, String value2) {
            addCriterion("ISOUTER not between", value1, value2, "isouter");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("POSITION is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("POSITION =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("POSITION <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("POSITION >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("POSITION >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("POSITION <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("POSITION <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("POSITION like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("POSITION not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("POSITION in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("POSITION not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("POSITION between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("POSITION not between", value1, value2, "position");
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

        public Criteria andContactIsNull() {
            addCriterion("CONTACT is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("CONTACT is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("CONTACT =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("CONTACT <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("CONTACT >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("CONTACT <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("CONTACT <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("CONTACT like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("CONTACT not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("CONTACT in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("CONTACT not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("CONTACT between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("CONTACT not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andMessagesIsNull() {
            addCriterion("MESSAGES is null");
            return (Criteria) this;
        }

        public Criteria andMessagesIsNotNull() {
            addCriterion("MESSAGES is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesEqualTo(String value) {
            addCriterion("MESSAGES =", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotEqualTo(String value) {
            addCriterion("MESSAGES <>", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesGreaterThan(String value) {
            addCriterion("MESSAGES >", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGES >=", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesLessThan(String value) {
            addCriterion("MESSAGES <", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesLessThanOrEqualTo(String value) {
            addCriterion("MESSAGES <=", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesLike(String value) {
            addCriterion("MESSAGES like", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotLike(String value) {
            addCriterion("MESSAGES not like", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesIn(List<String> values) {
            addCriterion("MESSAGES in", values, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotIn(List<String> values) {
            addCriterion("MESSAGES not in", values, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesBetween(String value1, String value2) {
            addCriterion("MESSAGES between", value1, value2, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotBetween(String value1, String value2) {
            addCriterion("MESSAGES not between", value1, value2, "messages");
            return (Criteria) this;
        }

        public Criteria andAssignaccountIsNull() {
            addCriterion("ASSIGNACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAssignaccountIsNotNull() {
            addCriterion("ASSIGNACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAssignaccountEqualTo(String value) {
            addCriterion("ASSIGNACCOUNT =", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountNotEqualTo(String value) {
            addCriterion("ASSIGNACCOUNT <>", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountGreaterThan(String value) {
            addCriterion("ASSIGNACCOUNT >", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGNACCOUNT >=", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountLessThan(String value) {
            addCriterion("ASSIGNACCOUNT <", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountLessThanOrEqualTo(String value) {
            addCriterion("ASSIGNACCOUNT <=", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountLike(String value) {
            addCriterion("ASSIGNACCOUNT like", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountNotLike(String value) {
            addCriterion("ASSIGNACCOUNT not like", value, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountIn(List<String> values) {
            addCriterion("ASSIGNACCOUNT in", values, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountNotIn(List<String> values) {
            addCriterion("ASSIGNACCOUNT not in", values, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountBetween(String value1, String value2) {
            addCriterion("ASSIGNACCOUNT between", value1, value2, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssignaccountNotBetween(String value1, String value2) {
            addCriterion("ASSIGNACCOUNT not between", value1, value2, "assignaccount");
            return (Criteria) this;
        }

        public Criteria andAssigntimeIsNull() {
            addCriterion("ASSIGNTIME is null");
            return (Criteria) this;
        }

        public Criteria andAssigntimeIsNotNull() {
            addCriterion("ASSIGNTIME is not null");
            return (Criteria) this;
        }

        public Criteria andAssigntimeEqualTo(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME =", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME <>", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeGreaterThan(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME >", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME >=", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeLessThan(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME <", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ASSIGNTIME <=", value, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeIn(List<Date> values) {
            addCriterionForJDBCDate("ASSIGNTIME in", values, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("ASSIGNTIME not in", values, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ASSIGNTIME between", value1, value2, "assigntime");
            return (Criteria) this;
        }

        public Criteria andAssigntimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ASSIGNTIME not between", value1, value2, "assigntime");
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

        public Criteria andIsreassignIsNull() {
            addCriterion("ISREASSIGN is null");
            return (Criteria) this;
        }

        public Criteria andIsreassignIsNotNull() {
            addCriterion("ISREASSIGN is not null");
            return (Criteria) this;
        }

        public Criteria andIsreassignEqualTo(String value) {
            addCriterion("ISREASSIGN =", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignNotEqualTo(String value) {
            addCriterion("ISREASSIGN <>", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignGreaterThan(String value) {
            addCriterion("ISREASSIGN >", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignGreaterThanOrEqualTo(String value) {
            addCriterion("ISREASSIGN >=", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignLessThan(String value) {
            addCriterion("ISREASSIGN <", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignLessThanOrEqualTo(String value) {
            addCriterion("ISREASSIGN <=", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignLike(String value) {
            addCriterion("ISREASSIGN like", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignNotLike(String value) {
            addCriterion("ISREASSIGN not like", value, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignIn(List<String> values) {
            addCriterion("ISREASSIGN in", values, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignNotIn(List<String> values) {
            addCriterion("ISREASSIGN not in", values, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignBetween(String value1, String value2) {
            addCriterion("ISREASSIGN between", value1, value2, "isreassign");
            return (Criteria) this;
        }

        public Criteria andIsreassignNotBetween(String value1, String value2) {
            addCriterion("ISREASSIGN not between", value1, value2, "isreassign");
            return (Criteria) this;
        }

        public Criteria andExceptionidIsNull() {
            addCriterion("EXCEPTIONID is null");
            return (Criteria) this;
        }

        public Criteria andExceptionidIsNotNull() {
            addCriterion("EXCEPTIONID is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionidEqualTo(String value) {
            addCriterion("EXCEPTIONID =", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidNotEqualTo(String value) {
            addCriterion("EXCEPTIONID <>", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidGreaterThan(String value) {
            addCriterion("EXCEPTIONID >", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidGreaterThanOrEqualTo(String value) {
            addCriterion("EXCEPTIONID >=", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidLessThan(String value) {
            addCriterion("EXCEPTIONID <", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidLessThanOrEqualTo(String value) {
            addCriterion("EXCEPTIONID <=", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidLike(String value) {
            addCriterion("EXCEPTIONID like", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidNotLike(String value) {
            addCriterion("EXCEPTIONID not like", value, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidIn(List<String> values) {
            addCriterion("EXCEPTIONID in", values, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidNotIn(List<String> values) {
            addCriterion("EXCEPTIONID not in", values, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidBetween(String value1, String value2) {
            addCriterion("EXCEPTIONID between", value1, value2, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andExceptionidNotBetween(String value1, String value2) {
            addCriterion("EXCEPTIONID not between", value1, value2, "exceptionid");
            return (Criteria) this;
        }

        public Criteria andOffaccountIsNull() {
            addCriterion("OFFACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOffaccountIsNotNull() {
            addCriterion("OFFACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOffaccountEqualTo(String value) {
            addCriterion("OFFACCOUNT =", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountNotEqualTo(String value) {
            addCriterion("OFFACCOUNT <>", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountGreaterThan(String value) {
            addCriterion("OFFACCOUNT >", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountGreaterThanOrEqualTo(String value) {
            addCriterion("OFFACCOUNT >=", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountLessThan(String value) {
            addCriterion("OFFACCOUNT <", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountLessThanOrEqualTo(String value) {
            addCriterion("OFFACCOUNT <=", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountLike(String value) {
            addCriterion("OFFACCOUNT like", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountNotLike(String value) {
            addCriterion("OFFACCOUNT not like", value, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountIn(List<String> values) {
            addCriterion("OFFACCOUNT in", values, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountNotIn(List<String> values) {
            addCriterion("OFFACCOUNT not in", values, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountBetween(String value1, String value2) {
            addCriterion("OFFACCOUNT between", value1, value2, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOffaccountNotBetween(String value1, String value2) {
            addCriterion("OFFACCOUNT not between", value1, value2, "offaccount");
            return (Criteria) this;
        }

        public Criteria andOfftimeIsNull() {
            addCriterion("OFFTIME is null");
            return (Criteria) this;
        }

        public Criteria andOfftimeIsNotNull() {
            addCriterion("OFFTIME is not null");
            return (Criteria) this;
        }

        public Criteria andOfftimeEqualTo(Date value) {
            addCriterionForJDBCDate("OFFTIME =", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("OFFTIME <>", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeGreaterThan(Date value) {
            addCriterionForJDBCDate("OFFTIME >", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OFFTIME >=", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeLessThan(Date value) {
            addCriterionForJDBCDate("OFFTIME <", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OFFTIME <=", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeIn(List<Date> values) {
            addCriterionForJDBCDate("OFFTIME in", values, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("OFFTIME not in", values, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OFFTIME between", value1, value2, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OFFTIME not between", value1, value2, "offtime");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNull() {
            addCriterion("ORGID is null");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNotNull() {
            addCriterion("ORGID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgidEqualTo(String value) {
            addCriterion("ORGID =", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotEqualTo(String value) {
            addCriterion("ORGID <>", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThan(String value) {
            addCriterion("ORGID >", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThanOrEqualTo(String value) {
            addCriterion("ORGID >=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThan(String value) {
            addCriterion("ORGID <", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThanOrEqualTo(String value) {
            addCriterion("ORGID <=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLike(String value) {
            addCriterion("ORGID like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotLike(String value) {
            addCriterion("ORGID not like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidIn(List<String> values) {
            addCriterion("ORGID in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotIn(List<String> values) {
            addCriterion("ORGID not in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidBetween(String value1, String value2) {
            addCriterion("ORGID between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotBetween(String value1, String value2) {
            addCriterion("ORGID not between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andIsleaveIsNull() {
            addCriterion("ISLEAVE is null");
            return (Criteria) this;
        }

        public Criteria andIsleaveIsNotNull() {
            addCriterion("ISLEAVE is not null");
            return (Criteria) this;
        }

        public Criteria andIsleaveEqualTo(String value) {
            addCriterion("ISLEAVE =", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveNotEqualTo(String value) {
            addCriterion("ISLEAVE <>", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveGreaterThan(String value) {
            addCriterion("ISLEAVE >", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveGreaterThanOrEqualTo(String value) {
            addCriterion("ISLEAVE >=", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveLessThan(String value) {
            addCriterion("ISLEAVE <", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveLessThanOrEqualTo(String value) {
            addCriterion("ISLEAVE <=", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveLike(String value) {
            addCriterion("ISLEAVE like", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveNotLike(String value) {
            addCriterion("ISLEAVE not like", value, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveIn(List<String> values) {
            addCriterion("ISLEAVE in", values, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveNotIn(List<String> values) {
            addCriterion("ISLEAVE not in", values, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveBetween(String value1, String value2) {
            addCriterion("ISLEAVE between", value1, value2, "isleave");
            return (Criteria) this;
        }

        public Criteria andIsleaveNotBetween(String value1, String value2) {
            addCriterion("ISLEAVE not between", value1, value2, "isleave");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("REASON is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("REASON is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("REASON =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("REASON <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("REASON >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REASON >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("REASON <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("REASON <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("REASON like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("REASON not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("REASON in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("REASON not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("REASON between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("REASON not between", value1, value2, "reason");
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
        
        public Criteria andAssetidIsNull() {
            addCriterion("ASSETID is null");
            return (Criteria) this;
        }

        public Criteria andAssetidIsNotNull() {
            addCriterion("ASSETID is not null");
            return (Criteria) this;
        }

        public Criteria andAssetidEqualTo(String value) {
            addCriterion("ASSETID =", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidNotEqualTo(String value) {
            addCriterion("ASSETID <>", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidGreaterThan(String value) {
            addCriterion("ASSETID >", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETID >=", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidLessThan(String value) {
            addCriterion("ASSETID <", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidLessThanOrEqualTo(String value) {
            addCriterion("ASSETID <=", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidLike(String value) {
            addCriterion("ASSETID like", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidNotLike(String value) {
            addCriterion("ASSETID not like", value, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidIn(List<String> values) {
            addCriterion("ASSETID in", values, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidNotIn(List<String> values) {
            addCriterion("ASSETID not in", values, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidBetween(String value1, String value2) {
            addCriterion("ASSETID between", value1, value2, "assetid");
            return (Criteria) this;
        }

        public Criteria andAssetidNotBetween(String value1, String value2) {
            addCriterion("ASSETID not between", value1, value2, "assetid");
            return (Criteria) this;
        }
        
        public Criteria andXungengIsNull() {
            addCriterion("XUNGENG is null");
            return (Criteria) this;
        }

        public Criteria andXungengIsNotNull() {
            addCriterion("XUNGENG is not null");
            return (Criteria) this;
        }

        public Criteria andXungengEqualTo(String value) {
            addCriterion("XUNGENG =", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengNotEqualTo(String value) {
            addCriterion("XUNGENG <>", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengGreaterThan(String value) {
            addCriterion("XUNGENG >", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengGreaterThanOrEqualTo(String value) {
            addCriterion("XUNGENG >=", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengLessThan(String value) {
            addCriterion("XUNGENG <", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengLessThanOrEqualTo(String value) {
            addCriterion("XUNGENG <=", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengLike(String value) {
            addCriterion("XUNGENG like", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengNotLike(String value) {
            addCriterion("XUNGENG not like", value, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengIn(List<String> values) {
            addCriterion("XUNGENG in", values, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengNotIn(List<String> values) {
            addCriterion("XUNGENG not in", values, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengBetween(String value1, String value2) {
            addCriterion("XUNGENG between", value1, value2, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengNotBetween(String value1, String value2) {
            addCriterion("XUNGENG not between", value1, value2, "xungeng");
            return (Criteria) this;
        }

        public Criteria andXungengtimeIsNull() {
            addCriterion("XUNGENGTIME is null");
            return (Criteria) this;
        }

        public Criteria andXungengtimeIsNotNull() {
            addCriterion("XUNGENGTIME is not null");
            return (Criteria) this;
        }

        public Criteria andXungengtimeEqualTo(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME =", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME <>", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME >", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME >=", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeLessThan(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME <", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XUNGENGTIME <=", value, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeIn(List<Date> values) {
            addCriterionForJDBCDate("XUNGENGTIME in", values, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("XUNGENGTIME not in", values, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XUNGENGTIME between", value1, value2, "xungengtime");
            return (Criteria) this;
        }

        public Criteria andXungengtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XUNGENGTIME not between", value1, value2, "xungengtime");
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