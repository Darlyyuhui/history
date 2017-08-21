package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="QUESTION_SCHEME")
public class QuestionScheme implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String infoId;

//    @Indexed
    private String content;

//    @Indexed
    private String filePath;

//    @Indexed
    private Date insertTime;

//    @Indexed
    private String operator;
    
//    @Indexed
    private String schemeNo;

    private static final long serialVersionUID = 1L;
    
    private String insertTimeStr;
    private String title;
    private String queContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
    
    public String getSchemeNo() {
        return schemeNo;
    }

    public void setSchemeNo(String schemeNo) {
        this.schemeNo = schemeNo == null ? null : schemeNo.trim();
    }

	public String getInsertTimeStr() {
		return insertTimeStr;
	}

	public void setInsertTimeStr(String insertTimeStr) {
		this.insertTimeStr = insertTimeStr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQueContent() {
		return queContent;
	}

	public void setQueContent(String queContent) {
		this.queContent = queContent;
	}
    
}