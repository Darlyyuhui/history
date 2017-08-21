package com.xiangxun.atms.framework.compnents.xls.imports.demo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsValidate;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean.RowValidateType;

/**
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class TestModel implements Serializable{
	
	private static final long serialVersionUID = 6290152604982846544L;

	@XlsValidate(type={RowValidateType.INTEGER,RowValidateType.EMAIL})
	@XlsMapping(cellNo=-1,fixity=true,value="1",fixValue="0")
	private Long id;

	@XlsMapping(cellNo=1)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private String road;

	@XlsMapping(cellNo=2)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private String direction;

	@XlsMapping(cellNo=3)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Integer lane;

	@XlsMapping(cellNo=4)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long h0;

	@XlsMapping(cellNo=5)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long h05;

	@XlsMapping(cellNo=6)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long h10;

	@XlsMapping(cellNo=7)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long h15;

	@XlsMapping(cellNo=8)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long speed;
	
	@XlsMapping(cellNo=9,istransfer=true)
	@XlsValidate(type=RowValidateType.MAXLENGTH,maxLength=20)
	private Long status;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the road
	 */
	public String getRoad() {
		return this.road;
	}

	/**
	 * @param road the road to set
	 */
	public void setRoad(String road) {
		this.road = road;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the lane
	 */
	public Integer getLane() {
		return this.lane;
	}

	/**
	 * @param lane the lane to set
	 */
	public void setLane(Integer lane) {
		this.lane = lane;
	}

	/**
	 * @return the h0
	 */
	public Long getH0() {
		return this.h0;
	}

	/**
	 * @param h0 the h0 to set
	 */
	public void setH0(Long h0) {
		this.h0 = h0;
	}

	/**
	 * @return the h05
	 */
	public Long getH05() {
		return this.h05;
	}

	/**
	 * @param h05 the h05 to set
	 */
	public void setH05(Long h05) {
		this.h05 = h05;
	}

	/**
	 * @return the h10
	 */
	public Long getH10() {
		return this.h10;
	}

	/**
	 * @param h10 the h10 to set
	 */
	public void setH10(Long h10) {
		this.h10 = h10;
	}

	/**
	 * @return the h15
	 */
	public Long getH15() {
		return this.h15;
	}

	/**
	 * @param h15 the h15 to set
	 */
	public void setH15(Long h15) {
		this.h15 = h15;
	}

	/**
	 * @return the speed
	 */
	public Long getSpeed() {
		return this.speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Long speed) {
		this.speed = speed;
	}
	
	

	 /**
	 * @return the status
	 */
	public Long getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
     * {@link object.toString()}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
