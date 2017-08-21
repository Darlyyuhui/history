package com.xiangxun.atms.module.repository.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="REPOSITORY_KNOWLEDGE_INFO")
public class KnowledgeInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3195777930020222650L;

//	@Indexed
    private String id;

//    @Indexed
    private String type;

//    @Indexed
    private String name;

//    @Indexed
    private String path;

//    @Indexed
    private String pid;

//    @Indexed
    private String operator;

//    @Indexed
    private Date createtime;

//    @Indexed
    private String applyflag;

//    @Indexed
    private Date applytime;

//    @Indexed
    private String verifyflag;

//    @Indexed
    private String verifier;

//    @Indexed
    private Date verifytime;

//    @Indexed
    private String verifyresult;

//    @Indexed
    private String disabled;

//    @Indexed
    private String note;

//    @Indexed
    private String rebutreason;

//    @Indexed
    private String context;
    
    //附加属性
    private String knowledgetype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getApplyflag() {
        return applyflag;
    }

    public void setApplyflag(String applyflag) {
        this.applyflag = applyflag == null ? null : applyflag.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getVerifyflag() {
        return verifyflag;
    }

    public void setVerifyflag(String verifyflag) {
        this.verifyflag = verifyflag == null ? null : verifyflag.trim();
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier == null ? null : verifier.trim();
    }

    public Date getVerifytime() {
        return verifytime;
    }

    public void setVerifytime(Date verifytime) {
        this.verifytime = verifytime;
    }

    public String getVerifyresult() {
        return verifyresult;
    }

    public void setVerifyresult(String verifyresult) {
        this.verifyresult = verifyresult == null ? null : verifyresult.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getRebutreason() {
        return rebutreason;
    }

    public void setRebutreason(String rebutreason) {
        this.rebutreason = rebutreason == null ? null : rebutreason.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

	public String getKnowledgetype() {
		return knowledgetype;
	}

	public void setKnowledgetype(String knowledgetype) {
		this.knowledgetype = knowledgetype;
	}
}