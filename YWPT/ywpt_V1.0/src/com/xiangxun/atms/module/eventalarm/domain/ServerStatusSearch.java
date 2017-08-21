package com.xiangxun.atms.module.eventalarm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerStatusSearch {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServerStatusSearch() {
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

        public Criteria andCpuinfoIsNull() {
            addCriterion("CPUINFO is null");
            return (Criteria) this;
        }

        public Criteria andCpuinfoIsNotNull() {
            addCriterion("CPUINFO is not null");
            return (Criteria) this;
        }

        public Criteria andCpuinfoEqualTo(String value) {
            addCriterion("CPUINFO =", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoNotEqualTo(String value) {
            addCriterion("CPUINFO <>", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoGreaterThan(String value) {
            addCriterion("CPUINFO >", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoGreaterThanOrEqualTo(String value) {
            addCriterion("CPUINFO >=", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoLessThan(String value) {
            addCriterion("CPUINFO <", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoLessThanOrEqualTo(String value) {
            addCriterion("CPUINFO <=", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoLike(String value) {
            addCriterion("CPUINFO like", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoNotLike(String value) {
            addCriterion("CPUINFO not like", value, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoIn(List<String> values) {
            addCriterion("CPUINFO in", values, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoNotIn(List<String> values) {
            addCriterion("CPUINFO not in", values, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoBetween(String value1, String value2) {
            addCriterion("CPUINFO between", value1, value2, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andCpuinfoNotBetween(String value1, String value2) {
            addCriterion("CPUINFO not between", value1, value2, "cpuinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoIsNull() {
            addCriterion("MEMORYINFO is null");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoIsNotNull() {
            addCriterion("MEMORYINFO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoEqualTo(String value) {
            addCriterion("MEMORYINFO =", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoNotEqualTo(String value) {
            addCriterion("MEMORYINFO <>", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoGreaterThan(String value) {
            addCriterion("MEMORYINFO >", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMORYINFO >=", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoLessThan(String value) {
            addCriterion("MEMORYINFO <", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoLessThanOrEqualTo(String value) {
            addCriterion("MEMORYINFO <=", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoLike(String value) {
            addCriterion("MEMORYINFO like", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoNotLike(String value) {
            addCriterion("MEMORYINFO not like", value, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoIn(List<String> values) {
            addCriterion("MEMORYINFO in", values, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoNotIn(List<String> values) {
            addCriterion("MEMORYINFO not in", values, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoBetween(String value1, String value2) {
            addCriterion("MEMORYINFO between", value1, value2, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andMemoryinfoNotBetween(String value1, String value2) {
            addCriterion("MEMORYINFO not between", value1, value2, "memoryinfo");
            return (Criteria) this;
        }

        public Criteria andComputernameIsNull() {
            addCriterion("COMPUTERNAME is null");
            return (Criteria) this;
        }

        public Criteria andComputernameIsNotNull() {
            addCriterion("COMPUTERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andComputernameEqualTo(String value) {
            addCriterion("COMPUTERNAME =", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameNotEqualTo(String value) {
            addCriterion("COMPUTERNAME <>", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameGreaterThan(String value) {
            addCriterion("COMPUTERNAME >", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPUTERNAME >=", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameLessThan(String value) {
            addCriterion("COMPUTERNAME <", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameLessThanOrEqualTo(String value) {
            addCriterion("COMPUTERNAME <=", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameLike(String value) {
            addCriterion("COMPUTERNAME like", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameNotLike(String value) {
            addCriterion("COMPUTERNAME not like", value, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameIn(List<String> values) {
            addCriterion("COMPUTERNAME in", values, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameNotIn(List<String> values) {
            addCriterion("COMPUTERNAME not in", values, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameBetween(String value1, String value2) {
            addCriterion("COMPUTERNAME between", value1, value2, "computername");
            return (Criteria) this;
        }

        public Criteria andComputernameNotBetween(String value1, String value2) {
            addCriterion("COMPUTERNAME not between", value1, value2, "computername");
            return (Criteria) this;
        }

        public Criteria andOsnameIsNull() {
            addCriterion("OSNAME is null");
            return (Criteria) this;
        }

        public Criteria andOsnameIsNotNull() {
            addCriterion("OSNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOsnameEqualTo(String value) {
            addCriterion("OSNAME =", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameNotEqualTo(String value) {
            addCriterion("OSNAME <>", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameGreaterThan(String value) {
            addCriterion("OSNAME >", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameGreaterThanOrEqualTo(String value) {
            addCriterion("OSNAME >=", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameLessThan(String value) {
            addCriterion("OSNAME <", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameLessThanOrEqualTo(String value) {
            addCriterion("OSNAME <=", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameLike(String value) {
            addCriterion("OSNAME like", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameNotLike(String value) {
            addCriterion("OSNAME not like", value, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameIn(List<String> values) {
            addCriterion("OSNAME in", values, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameNotIn(List<String> values) {
            addCriterion("OSNAME not in", values, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameBetween(String value1, String value2) {
            addCriterion("OSNAME between", value1, value2, "osname");
            return (Criteria) this;
        }

        public Criteria andOsnameNotBetween(String value1, String value2) {
            addCriterion("OSNAME not between", value1, value2, "osname");
            return (Criteria) this;
        }

        public Criteria andOsversionIsNull() {
            addCriterion("OSVERSION is null");
            return (Criteria) this;
        }

        public Criteria andOsversionIsNotNull() {
            addCriterion("OSVERSION is not null");
            return (Criteria) this;
        }

        public Criteria andOsversionEqualTo(String value) {
            addCriterion("OSVERSION =", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotEqualTo(String value) {
            addCriterion("OSVERSION <>", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionGreaterThan(String value) {
            addCriterion("OSVERSION >", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionGreaterThanOrEqualTo(String value) {
            addCriterion("OSVERSION >=", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLessThan(String value) {
            addCriterion("OSVERSION <", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLessThanOrEqualTo(String value) {
            addCriterion("OSVERSION <=", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLike(String value) {
            addCriterion("OSVERSION like", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotLike(String value) {
            addCriterion("OSVERSION not like", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionIn(List<String> values) {
            addCriterion("OSVERSION in", values, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotIn(List<String> values) {
            addCriterion("OSVERSION not in", values, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionBetween(String value1, String value2) {
            addCriterion("OSVERSION between", value1, value2, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotBetween(String value1, String value2) {
            addCriterion("OSVERSION not between", value1, value2, "osversion");
            return (Criteria) this;
        }

        public Criteria andOstypeIsNull() {
            addCriterion("OSTYPE is null");
            return (Criteria) this;
        }

        public Criteria andOstypeIsNotNull() {
            addCriterion("OSTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOstypeEqualTo(String value) {
            addCriterion("OSTYPE =", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeNotEqualTo(String value) {
            addCriterion("OSTYPE <>", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeGreaterThan(String value) {
            addCriterion("OSTYPE >", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeGreaterThanOrEqualTo(String value) {
            addCriterion("OSTYPE >=", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeLessThan(String value) {
            addCriterion("OSTYPE <", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeLessThanOrEqualTo(String value) {
            addCriterion("OSTYPE <=", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeLike(String value) {
            addCriterion("OSTYPE like", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeNotLike(String value) {
            addCriterion("OSTYPE not like", value, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeIn(List<String> values) {
            addCriterion("OSTYPE in", values, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeNotIn(List<String> values) {
            addCriterion("OSTYPE not in", values, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeBetween(String value1, String value2) {
            addCriterion("OSTYPE between", value1, value2, "ostype");
            return (Criteria) this;
        }

        public Criteria andOstypeNotBetween(String value1, String value2) {
            addCriterion("OSTYPE not between", value1, value2, "ostype");
            return (Criteria) this;
        }

        public Criteria andCpunumberIsNull() {
            addCriterion("CPUNUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCpunumberIsNotNull() {
            addCriterion("CPUNUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCpunumberEqualTo(String value) {
            addCriterion("CPUNUMBER =", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberNotEqualTo(String value) {
            addCriterion("CPUNUMBER <>", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberGreaterThan(String value) {
            addCriterion("CPUNUMBER >", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberGreaterThanOrEqualTo(String value) {
            addCriterion("CPUNUMBER >=", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberLessThan(String value) {
            addCriterion("CPUNUMBER <", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberLessThanOrEqualTo(String value) {
            addCriterion("CPUNUMBER <=", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberLike(String value) {
            addCriterion("CPUNUMBER like", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberNotLike(String value) {
            addCriterion("CPUNUMBER not like", value, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberIn(List<String> values) {
            addCriterion("CPUNUMBER in", values, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberNotIn(List<String> values) {
            addCriterion("CPUNUMBER not in", values, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberBetween(String value1, String value2) {
            addCriterion("CPUNUMBER between", value1, value2, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andCpunumberNotBetween(String value1, String value2) {
            addCriterion("CPUNUMBER not between", value1, value2, "cpunumber");
            return (Criteria) this;
        }

        public Criteria andBiosversionIsNull() {
            addCriterion("BIOSVERSION is null");
            return (Criteria) this;
        }

        public Criteria andBiosversionIsNotNull() {
            addCriterion("BIOSVERSION is not null");
            return (Criteria) this;
        }

        public Criteria andBiosversionEqualTo(String value) {
            addCriterion("BIOSVERSION =", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionNotEqualTo(String value) {
            addCriterion("BIOSVERSION <>", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionGreaterThan(String value) {
            addCriterion("BIOSVERSION >", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionGreaterThanOrEqualTo(String value) {
            addCriterion("BIOSVERSION >=", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionLessThan(String value) {
            addCriterion("BIOSVERSION <", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionLessThanOrEqualTo(String value) {
            addCriterion("BIOSVERSION <=", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionLike(String value) {
            addCriterion("BIOSVERSION like", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionNotLike(String value) {
            addCriterion("BIOSVERSION not like", value, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionIn(List<String> values) {
            addCriterion("BIOSVERSION in", values, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionNotIn(List<String> values) {
            addCriterion("BIOSVERSION not in", values, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionBetween(String value1, String value2) {
            addCriterion("BIOSVERSION between", value1, value2, "biosversion");
            return (Criteria) this;
        }

        public Criteria andBiosversionNotBetween(String value1, String value2) {
            addCriterion("BIOSVERSION not between", value1, value2, "biosversion");
            return (Criteria) this;
        }

        public Criteria andSysareasetIsNull() {
            addCriterion("SYSAREASET is null");
            return (Criteria) this;
        }

        public Criteria andSysareasetIsNotNull() {
            addCriterion("SYSAREASET is not null");
            return (Criteria) this;
        }

        public Criteria andSysareasetEqualTo(String value) {
            addCriterion("SYSAREASET =", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetNotEqualTo(String value) {
            addCriterion("SYSAREASET <>", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetGreaterThan(String value) {
            addCriterion("SYSAREASET >", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetGreaterThanOrEqualTo(String value) {
            addCriterion("SYSAREASET >=", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetLessThan(String value) {
            addCriterion("SYSAREASET <", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetLessThanOrEqualTo(String value) {
            addCriterion("SYSAREASET <=", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetLike(String value) {
            addCriterion("SYSAREASET like", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetNotLike(String value) {
            addCriterion("SYSAREASET not like", value, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetIn(List<String> values) {
            addCriterion("SYSAREASET in", values, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetNotIn(List<String> values) {
            addCriterion("SYSAREASET not in", values, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetBetween(String value1, String value2) {
            addCriterion("SYSAREASET between", value1, value2, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andSysareasetNotBetween(String value1, String value2) {
            addCriterion("SYSAREASET not between", value1, value2, "sysareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetIsNull() {
            addCriterion("INPUTAREASET is null");
            return (Criteria) this;
        }

        public Criteria andInputareasetIsNotNull() {
            addCriterion("INPUTAREASET is not null");
            return (Criteria) this;
        }

        public Criteria andInputareasetEqualTo(String value) {
            addCriterion("INPUTAREASET =", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetNotEqualTo(String value) {
            addCriterion("INPUTAREASET <>", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetGreaterThan(String value) {
            addCriterion("INPUTAREASET >", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetGreaterThanOrEqualTo(String value) {
            addCriterion("INPUTAREASET >=", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetLessThan(String value) {
            addCriterion("INPUTAREASET <", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetLessThanOrEqualTo(String value) {
            addCriterion("INPUTAREASET <=", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetLike(String value) {
            addCriterion("INPUTAREASET like", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetNotLike(String value) {
            addCriterion("INPUTAREASET not like", value, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetIn(List<String> values) {
            addCriterion("INPUTAREASET in", values, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetNotIn(List<String> values) {
            addCriterion("INPUTAREASET not in", values, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetBetween(String value1, String value2) {
            addCriterion("INPUTAREASET between", value1, value2, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andInputareasetNotBetween(String value1, String value2) {
            addCriterion("INPUTAREASET not between", value1, value2, "inputareaset");
            return (Criteria) this;
        }

        public Criteria andHymemoryallIsNull() {
            addCriterion("HYMEMORYALL is null");
            return (Criteria) this;
        }

        public Criteria andHymemoryallIsNotNull() {
            addCriterion("HYMEMORYALL is not null");
            return (Criteria) this;
        }

        public Criteria andHymemoryallEqualTo(String value) {
            addCriterion("HYMEMORYALL =", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallNotEqualTo(String value) {
            addCriterion("HYMEMORYALL <>", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallGreaterThan(String value) {
            addCriterion("HYMEMORYALL >", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallGreaterThanOrEqualTo(String value) {
            addCriterion("HYMEMORYALL >=", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallLessThan(String value) {
            addCriterion("HYMEMORYALL <", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallLessThanOrEqualTo(String value) {
            addCriterion("HYMEMORYALL <=", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallLike(String value) {
            addCriterion("HYMEMORYALL like", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallNotLike(String value) {
            addCriterion("HYMEMORYALL not like", value, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallIn(List<String> values) {
            addCriterion("HYMEMORYALL in", values, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallNotIn(List<String> values) {
            addCriterion("HYMEMORYALL not in", values, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallBetween(String value1, String value2) {
            addCriterion("HYMEMORYALL between", value1, value2, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryallNotBetween(String value1, String value2) {
            addCriterion("HYMEMORYALL not between", value1, value2, "hymemoryall");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeIsNull() {
            addCriterion("HYMEMORYFREE is null");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeIsNotNull() {
            addCriterion("HYMEMORYFREE is not null");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeEqualTo(String value) {
            addCriterion("HYMEMORYFREE =", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeNotEqualTo(String value) {
            addCriterion("HYMEMORYFREE <>", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeGreaterThan(String value) {
            addCriterion("HYMEMORYFREE >", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeGreaterThanOrEqualTo(String value) {
            addCriterion("HYMEMORYFREE >=", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeLessThan(String value) {
            addCriterion("HYMEMORYFREE <", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeLessThanOrEqualTo(String value) {
            addCriterion("HYMEMORYFREE <=", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeLike(String value) {
            addCriterion("HYMEMORYFREE like", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeNotLike(String value) {
            addCriterion("HYMEMORYFREE not like", value, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeIn(List<String> values) {
            addCriterion("HYMEMORYFREE in", values, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeNotIn(List<String> values) {
            addCriterion("HYMEMORYFREE not in", values, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeBetween(String value1, String value2) {
            addCriterion("HYMEMORYFREE between", value1, value2, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andHymemoryfreeNotBetween(String value1, String value2) {
            addCriterion("HYMEMORYFREE not between", value1, value2, "hymemoryfree");
            return (Criteria) this;
        }

        public Criteria andOsidIsNull() {
            addCriterion("OSID is null");
            return (Criteria) this;
        }

        public Criteria andOsidIsNotNull() {
            addCriterion("OSID is not null");
            return (Criteria) this;
        }

        public Criteria andOsidEqualTo(String value) {
            addCriterion("OSID =", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidNotEqualTo(String value) {
            addCriterion("OSID <>", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidGreaterThan(String value) {
            addCriterion("OSID >", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidGreaterThanOrEqualTo(String value) {
            addCriterion("OSID >=", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidLessThan(String value) {
            addCriterion("OSID <", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidLessThanOrEqualTo(String value) {
            addCriterion("OSID <=", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidLike(String value) {
            addCriterion("OSID like", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidNotLike(String value) {
            addCriterion("OSID not like", value, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidIn(List<String> values) {
            addCriterion("OSID in", values, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidNotIn(List<String> values) {
            addCriterion("OSID not in", values, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidBetween(String value1, String value2) {
            addCriterion("OSID between", value1, value2, "osid");
            return (Criteria) this;
        }

        public Criteria andOsidNotBetween(String value1, String value2) {
            addCriterion("OSID not between", value1, value2, "osid");
            return (Criteria) this;
        }

        public Criteria andOsreguserIsNull() {
            addCriterion("OSREGUSER is null");
            return (Criteria) this;
        }

        public Criteria andOsreguserIsNotNull() {
            addCriterion("OSREGUSER is not null");
            return (Criteria) this;
        }

        public Criteria andOsreguserEqualTo(String value) {
            addCriterion("OSREGUSER =", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserNotEqualTo(String value) {
            addCriterion("OSREGUSER <>", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserGreaterThan(String value) {
            addCriterion("OSREGUSER >", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserGreaterThanOrEqualTo(String value) {
            addCriterion("OSREGUSER >=", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserLessThan(String value) {
            addCriterion("OSREGUSER <", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserLessThanOrEqualTo(String value) {
            addCriterion("OSREGUSER <=", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserLike(String value) {
            addCriterion("OSREGUSER like", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserNotLike(String value) {
            addCriterion("OSREGUSER not like", value, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserIn(List<String> values) {
            addCriterion("OSREGUSER in", values, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserNotIn(List<String> values) {
            addCriterion("OSREGUSER not in", values, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserBetween(String value1, String value2) {
            addCriterion("OSREGUSER between", value1, value2, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsreguserNotBetween(String value1, String value2) {
            addCriterion("OSREGUSER not between", value1, value2, "osreguser");
            return (Criteria) this;
        }

        public Criteria andOsfactoryIsNull() {
            addCriterion("OSFACTORY is null");
            return (Criteria) this;
        }

        public Criteria andOsfactoryIsNotNull() {
            addCriterion("OSFACTORY is not null");
            return (Criteria) this;
        }

        public Criteria andOsfactoryEqualTo(String value) {
            addCriterion("OSFACTORY =", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryNotEqualTo(String value) {
            addCriterion("OSFACTORY <>", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryGreaterThan(String value) {
            addCriterion("OSFACTORY >", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryGreaterThanOrEqualTo(String value) {
            addCriterion("OSFACTORY >=", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryLessThan(String value) {
            addCriterion("OSFACTORY <", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryLessThanOrEqualTo(String value) {
            addCriterion("OSFACTORY <=", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryLike(String value) {
            addCriterion("OSFACTORY like", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryNotLike(String value) {
            addCriterion("OSFACTORY not like", value, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryIn(List<String> values) {
            addCriterion("OSFACTORY in", values, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryNotIn(List<String> values) {
            addCriterion("OSFACTORY not in", values, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryBetween(String value1, String value2) {
            addCriterion("OSFACTORY between", value1, value2, "osfactory");
            return (Criteria) this;
        }

        public Criteria andOsfactoryNotBetween(String value1, String value2) {
            addCriterion("OSFACTORY not between", value1, value2, "osfactory");
            return (Criteria) this;
        }

        public Criteria andDiskinfoIsNull() {
            addCriterion("DISKINFO is null");
            return (Criteria) this;
        }

        public Criteria andDiskinfoIsNotNull() {
            addCriterion("DISKINFO is not null");
            return (Criteria) this;
        }

        public Criteria andDiskinfoEqualTo(String value) {
            addCriterion("DISKINFO =", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoNotEqualTo(String value) {
            addCriterion("DISKINFO <>", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoGreaterThan(String value) {
            addCriterion("DISKINFO >", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoGreaterThanOrEqualTo(String value) {
            addCriterion("DISKINFO >=", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoLessThan(String value) {
            addCriterion("DISKINFO <", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoLessThanOrEqualTo(String value) {
            addCriterion("DISKINFO <=", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoLike(String value) {
            addCriterion("DISKINFO like", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoNotLike(String value) {
            addCriterion("DISKINFO not like", value, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoIn(List<String> values) {
            addCriterion("DISKINFO in", values, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoNotIn(List<String> values) {
            addCriterion("DISKINFO not in", values, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoBetween(String value1, String value2) {
            addCriterion("DISKINFO between", value1, value2, "diskinfo");
            return (Criteria) this;
        }

        public Criteria andDiskinfoNotBetween(String value1, String value2) {
            addCriterion("DISKINFO not between", value1, value2, "diskinfo");
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

        public Criteria andInserttimeIsNull() {
            addCriterion("INSERTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNotNull() {
            addCriterion("INSERTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInserttimeEqualTo(Date value) {
            addCriterion("INSERTTIME =", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotEqualTo(Date value) {
            addCriterion("INSERTTIME <>", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThan(Date value) {
            addCriterion("INSERTTIME >", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME >=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThan(Date value) {
            addCriterion("INSERTTIME <", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThanOrEqualTo(Date value) {
            addCriterion("INSERTTIME <=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeIn(List<Date> values) {
            addCriterion("INSERTTIME in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotIn(List<Date> values) {
            addCriterion("INSERTTIME not in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotBetween(Date value1, Date value2) {
            addCriterion("INSERTTIME not between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInsertpcIsNull() {
            addCriterion("INSERTPC is null");
            return (Criteria) this;
        }

        public Criteria andInsertpcIsNotNull() {
            addCriterion("INSERTPC is not null");
            return (Criteria) this;
        }

        public Criteria andInsertpcEqualTo(String value) {
            addCriterion("INSERTPC =", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcNotEqualTo(String value) {
            addCriterion("INSERTPC <>", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcGreaterThan(String value) {
            addCriterion("INSERTPC >", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcGreaterThanOrEqualTo(String value) {
            addCriterion("INSERTPC >=", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcLessThan(String value) {
            addCriterion("INSERTPC <", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcLessThanOrEqualTo(String value) {
            addCriterion("INSERTPC <=", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcLike(String value) {
            addCriterion("INSERTPC like", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcNotLike(String value) {
            addCriterion("INSERTPC not like", value, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcIn(List<String> values) {
            addCriterion("INSERTPC in", values, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcNotIn(List<String> values) {
            addCriterion("INSERTPC not in", values, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcBetween(String value1, String value2) {
            addCriterion("INSERTPC between", value1, value2, "insertpc");
            return (Criteria) this;
        }

        public Criteria andInsertpcNotBetween(String value1, String value2) {
            addCriterion("INSERTPC not between", value1, value2, "insertpc");
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