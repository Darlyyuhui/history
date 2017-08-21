package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="QUESTION_INFO")
public class QuestionInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String operator;

//    @Indexed
    private Date insertTime;

//    @Indexed
    private String content;

//    @Indexed
    private String title;

    private static final long serialVersionUID = 1L;
    
    private String insertTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

	public String getInsertTimeStr() {
		return insertTimeStr;
	}

	public void setInsertTimeStr(String insertTimeStr) {
		this.insertTimeStr = insertTimeStr;
	}
    
    
}