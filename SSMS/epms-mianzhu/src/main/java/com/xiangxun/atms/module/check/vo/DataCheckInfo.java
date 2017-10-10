package com.xiangxun.atms.module.check.vo;

import java.util.Date;

public class DataCheckInfo {
    private String id;

    private String schemeId;

    private String schemeName;

    private String regionId;

    private String sampleType;

    private String samplingDept;

    private String analysisDept;

    private String analysisCount;

    private String testItems;

    private Integer status;

    private Date checkTime;

    /**
     * @return the value of ID
     */
    public String getId() {
        return id;
    }

    /**
    
     *
     * @param id the value for ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of SCHEME_ID
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
    
     *
     * @param schemeId the value for SCHEME_ID
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * @return the value of SCHEME_NAME
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
    
     *
     * @param schemeName the value for SCHEME_NAME
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    /**
     * @return the value of REGION_ID
     */
    public String getRegionId() {
        return regionId;
    }

    /**
    
     *
     * @param regionId the value for REGION_ID
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * @return the value of SAMPLE_TYPE
     */
    public String getSampleType() {
        return sampleType;
    }

    /**
    
     *
     * @param sampleType the value for SAMPLE_TYPE
     */
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType == null ? null : sampleType.trim();
    }

    /**
     * @return the value of SAMPLING_DEPT
     */
    public String getSamplingDept() {
        return samplingDept;
    }

    /**
    
     *
     * @param samplingDept the value for SAMPLING_DEPT
     */
    public void setSamplingDept(String samplingDept) {
        this.samplingDept = samplingDept == null ? null : samplingDept.trim();
    }

    /**
     * @return the value of ANALYSIS_DEPT
     */
    public String getAnalysisDept() {
        return analysisDept;
    }

    /**
    
     *
     * @param analysisDept the value for ANALYSIS_DEPT
     */
    public void setAnalysisDept(String analysisDept) {
        this.analysisDept = analysisDept == null ? null : analysisDept.trim();
    }

    /**
     * @return the value of ANALYSIS_COUNT
     */
    public String getAnalysisCount() {
        return analysisCount;
    }

    /**
    
     *
     * @param analysisCount the value for ANALYSIS_COUNT
     */
    public void setAnalysisCount(String analysisCount) {
        this.analysisCount = analysisCount == null ? null : analysisCount.trim();
    }

    /**
     * @return the value of TEST_ITEMS
     */
    public String getTestItems() {
        return testItems;
    }

    /**
    
     *
     * @param testItems the value for TEST_ITEMS
     */
    public void setTestItems(String testItems) {
        this.testItems = testItems == null ? null : testItems.trim();
    }

    /**
     * @return the value of STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
    
     *
     * @param status the value for STATUS
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the value of CHECK_TIME
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
    
     *
     * @param checkTime the value for CHECK_TIME
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schemeId=").append(schemeId);
        sb.append(", schemeName=").append(schemeName);
        sb.append(", regionId=").append(regionId);
        sb.append(", sampleType=").append(sampleType);
        sb.append(", samplingDept=").append(samplingDept);
        sb.append(", analysisDept=").append(analysisDept);
        sb.append(", analysisCount=").append(analysisCount);
        sb.append(", testItems=").append(testItems);
        sb.append(", status=").append(status);
        sb.append(", checkTime=").append(checkTime);
        sb.append("]");
        return sb.toString();
    }
}