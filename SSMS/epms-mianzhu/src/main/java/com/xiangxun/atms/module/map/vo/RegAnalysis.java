package com.xiangxun.atms.module.map.vo;

import java.util.Date;

public class RegAnalysis implements java.io.Serializable {

	private static final long serialVersionUID = 7758604320131000381L;

	/**
     * 行政区名称
     * */
    private String regId;

    /**
     * 行政区面积
     * */
    private float regArea;

    /**
     * 行政区几何信息
     * */
    private String geo;

    /**
     * 行政区中心点
     * */
    private String center;

    /**
     * 统计时间
     * */
    private Date date;

    // 行政区污染级别
    private int polluteLevel;
    // 无污染
    private float polluteNull;
    // 轻微污染
    private float polluteLittle;
    // 轻度污染
    private float polluteSlight;
    // 中度污染
    private float polluteModerate;
    // 重度污染
    private float polluteSevere;

    // 最小
    private float phMin;
    // 最大
    private float phMax;
    // 平均值
    private float phAvg;
    //standard deviation 标准差
    private float phSD;
    // 变异系数
    private float phCoefficient;
    // 铬
    private float crMin;
    private float crMax;
    private float crAvg;
    private float crSD;
    // 有效钛铬
    private float crCoefficient;
    private float crValidMin;
    private float crValidMax;
    private float crValidAvg;
    private float crValidSD;
    private float crValidCoefficient;

    // 超标率
    private float exceedRate;

    /**
     * 大气沉降采点
     * */
    private int airNum;

    /**
     * 背景土壤监测点
     * */
    private int backNum;

    /**
     * 农田土壤监测点
     * */
    private int landNum;

    /**
     * 农作物监测点
     * */
    private int farmNum;

    /**
     * 肥料采集点
     * */
    private int manureNum;

    //地下水监测点
    private int undergroundWaterNum;
    //地表灌溉水监测点
    private int surfaceWaterNum;
    //底泥监测点
    private int dirtNum;

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public float getRegArea() {
        return regArea;
    }

    public void setRegArea(float regArea) {
        this.regArea = regArea;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPolluteNull() {
        return polluteNull;
    }

    public void setPolluteNull(float polluteNull) {
        this.polluteNull = polluteNull;
    }

    public float getPolluteLittle() {
        return polluteLittle;
    }

    public void setPolluteLittle(float polluteLittle) {
        this.polluteLittle = polluteLittle;
    }

    public float getPolluteSlight() {
        return polluteSlight;
    }

    public void setPolluteSlight(float polluteSlight) {
        this.polluteSlight = polluteSlight;
    }

    public float getPolluteModerate() {
        return polluteModerate;
    }

    public void setPolluteModerate(float polluteModerate) {
        this.polluteModerate = polluteModerate;
    }

    public float getPolluteSevere() {
        return polluteSevere;
    }

    public void setPolluteSevere(float polluteSevere) {
        this.polluteSevere = polluteSevere;
    }

    public int getAirNum() {
        return airNum;
    }

    public void setAirNum(int airNum) {
        this.airNum = airNum;
    }

    public int getBackNum() {
        return backNum;
    }

    public void setBackNum(int backNum) {
        this.backNum = backNum;
    }

    public int getLandNum() {
        return landNum;
    }

    public void setLandNum(int landNum) {
        this.landNum = landNum;
    }

    public int getFarmNum() {
        return farmNum;
    }

    public void setFarmNum(int farmNum) {
        this.farmNum = farmNum;
    }

    public int getManureNum() {
        return manureNum;
    }

    public void setManureNum(int manureNum) {
        this.manureNum = manureNum;
    }

    public int getUndergroundWaterNum() {
        return undergroundWaterNum;
    }

    public void setUndergroundWaterNum(int undergroundWaterNum) {
        this.undergroundWaterNum = undergroundWaterNum;
    }

    public int getSurfaceWaterNum() {
        return surfaceWaterNum;
    }

    public void setSurfaceWaterNum(int surfaceWaterNum) {
        this.surfaceWaterNum = surfaceWaterNum;
    }

    public int getDirtNum() {
        return dirtNum;
    }

    public void setDirtNum(int dirtNum) {
        this.dirtNum = dirtNum;
    }

    public int getTotalNum() {
        return this.airNum+this.backNum+this.landNum+this.farmNum+this.manureNum+this.undergroundWaterNum+this.surfaceWaterNum+this.dirtNum;
    }

    public float getPhMin() {
        return phMin;
    }

    public void setPhMin(float phMin) {
        this.phMin = phMin;
    }

    public float getPhMax() {
        return phMax;
    }

    public void setPhMax(float phMax) {
        this.phMax = phMax;
    }

    public float getPhAvg() {
        return phAvg;
    }

    public void setPhAvg(float phAvg) {
        this.phAvg = phAvg;
    }

    public float getPhSD() {
        return phSD;
    }

    public void setPhSD(float phSD) {
        this.phSD = phSD;
    }

    public float getPhCoefficient() {
        return phCoefficient;
    }

    public void setPhCoefficient(float phCoefficient) {
        this.phCoefficient = phCoefficient;
    }

    public float getCrMin() {
        return crMin;
    }

    public void setCrMin(float crMin) {
        this.crMin = crMin;
    }

    public float getCrMax() {
        return crMax;
    }

    public void setCrMax(float crMax) {
        this.crMax = crMax;
    }

    public float getCrAvg() {
        return crAvg;
    }

    public void setCrAvg(float crAvg) {
        this.crAvg = crAvg;
    }

    public float getCrSD() {
        return crSD;
    }

    public void setCrSD(float crSD) {
        this.crSD = crSD;
    }

    public float getCrCoefficient() {
        return crCoefficient;
    }

    public void setCrCoefficient(float crCoefficient) {
        this.crCoefficient = crCoefficient;
    }

    public float getCrValidMin() {
        return crValidMin;
    }

    public void setCrValidMin(float crValidMin) {
        this.crValidMin = crValidMin;
    }

    public float getCrValidMax() {
        return crValidMax;
    }

    public void setCrValidMax(float crValidMax) {
        this.crValidMax = crValidMax;
    }

    public float getCrValidAvg() {
        return crValidAvg;
    }

    public void setCrValidAvg(float crValidAvg) {
        this.crValidAvg = crValidAvg;
    }

    public float getCrValidSD() {
        return crValidSD;
    }

    public void setCrValidSD(float crValidSD) {
        this.crValidSD = crValidSD;
    }

    public float getCrValidCoefficient() {
        return crValidCoefficient;
    }

    public void setCrValidCoefficient(float crValidCoefficient) {
        this.crValidCoefficient = crValidCoefficient;
    }

    public float getExceedRate() {
        return exceedRate;
    }

    public void setExceedRate(float exceedRate) {
        this.exceedRate = exceedRate;
    }

    public int getPolluteLevel() {
        return polluteLevel;
    }

    public void setPolluteLevel(int polluteLevel) {
        this.polluteLevel = polluteLevel;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}