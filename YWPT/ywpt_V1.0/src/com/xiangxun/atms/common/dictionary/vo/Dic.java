package com.xiangxun.atms.common.dictionary.vo;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import com.xiangxun.atms.common.dictionary.type.DicType;

/***
 * 数据字典
 * @author zhouhaij
 * @2013-3-22 下午02:37:05
 */
public class Dic  implements Serializable,Comparator<Dic>{
	private static final long serialVersionUID = 7447924757767536890L;
	private String id;
    /***
     * 编码
     */
    private String code;

    /***
     * 名称
     */
    private String name;

    /***
     * 字典类型
     */
    private DicType type;

    /**
     * 备注
     */
    private String remark;
    
    public Dic(){}

    /**
	 * @param code
	 * @param name
	 * @param type
	 */
	public Dic(String code, String name, DicType type) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public DicType getType() {
        return type;
    }

    public void setType(DicType type) {
        this.type = type == null ? null : type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    public int compare(Dic o1, Dic o2) { 
        return Collator.getInstance(Locale.CHINESE).compare(o1.getName(), o2.getName()); 
    } 
}