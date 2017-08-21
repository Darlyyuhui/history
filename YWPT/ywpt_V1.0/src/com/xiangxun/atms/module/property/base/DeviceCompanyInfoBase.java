package com.xiangxun.atms.module.property.base;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="PROPERTY_DEVICECOMPANY_INFO")
public class DeviceCompanyInfoBase  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5699663023414510736L;

//	@Id
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String note;

//    @Indexed
    private String contactphone;

//    @Indexed
    private BigDecimal position;

//    @Indexed
    private String contactperson;

//    @Indexed
    private String datekey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public BigDecimal getPosition() {
        return position;
    }

    public void setPosition(BigDecimal position) {
        this.position = position;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson == null ? null : contactperson.trim();
    }

    public String getDatekey() {
        return datekey;
    }

    public void setDatekey(String datekey) {
        this.datekey = datekey == null ? null : datekey.trim();
    }
}