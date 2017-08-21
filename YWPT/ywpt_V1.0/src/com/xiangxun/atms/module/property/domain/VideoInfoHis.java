package com.xiangxun.atms.module.property.domain;

//@Document(collection="PROPERTY_VIDEO_INFO_HIS")
public class VideoInfoHis extends VideoInfo {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7342073460851947482L;
//	@Indexed
    private String afterRecordId;

    public String getAfterRecordId() {
        return afterRecordId;
    }

    public void setAfterRecordId(String afterRecordId) {
        this.afterRecordId = afterRecordId == null ? null : afterRecordId.trim();
    }
}