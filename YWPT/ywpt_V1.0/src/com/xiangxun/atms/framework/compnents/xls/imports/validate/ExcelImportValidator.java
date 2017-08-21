package com.xiangxun.atms.framework.compnents.xls.imports.validate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.springmodules.validation.bean.conf.ValidationConfigurationException;

import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean.RowValidateType;

/**
 * xls导入数据校验器
 * @author zhouhaijian
 */
public class ExcelImportValidator extends GenericValidator{
	
	private static final long serialVersionUID = -347254878807981443L;
	
	public static List<String> errorMessages = new ArrayList<String>();
	
	private UniqueValidator  uniqueValidator;

	/***
	 * 验证数据
	 * 
	 * @param value
	 *            数据值
	 * @param type
	 *            验证类型
	 * @param row
	 *            行数
	 * @param col
	 *            列数
	 * @param length
	 *            验证长度
	 */
	public void validate(File file,List<ValidateBean> validateBeans) {
		for (ValidateBean validateBean : validateBeans) {
			String value = validateBean.getValue();
			Integer row = validateBean.getRow();
			String col = validateBean.getColName();
			RowValidateType type = validateBean.getType();
			int maxlength = validateBean.getMaxLength();
			int minlength = validateBean.getMinLength();
			Boolean requeired = validateBean.getRequired();
			
			if (isBlankOrNull(value)) {
				if(requeired){
					errorMessages.add(file.getName()+":第" + row + "行"  + col + "数据为空。");
				}
			}
			
			//当为必选或者不为空时才进行其他类型的数据校验
			if(requeired || !isBlankOrNull(value)){
				
				if (RowValidateType.PHONE.equals(type)) {
					String reg="^[1][\\d]{10}";   
					if (!startCheck(reg,value)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col	+ "数据格式不正确。");
					}
				}
				
				if(RowValidateType.TELEPHONE.equals(type)){
					String reg = "[\\d]{0,15}";
					if(!startCheck(reg,value)){
						errorMessages.add(file.getName()+":第" + row + "行" + col	+ "数据格式不正确。");
					}
				}
				
				if (RowValidateType.MAXLENGTH.equals(type)) {
					if (!maxLength(value, maxlength)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据过长。");
					}
				}
				
				if (RowValidateType.MINLENGTH.equals(type)) {
					if (!minLength(value, minlength)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不能少于"+minlength+"个字符。");
					}
				}
				
				
				if (RowValidateType.GENDER.equals(type)) {
					if ((!"男".equals(value)) || (!"女".equals(value))) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据应该为男或者女。");
					}
				}
				
				if (RowValidateType.SCORE.equals(type)) {
					if (!isDouble(value)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不是有效金额。");
					}
				}
				if (RowValidateType.INTEGER.equals(type)) {
					if (!isInt(value)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不是整数。");
					}
				}
				
				if (RowValidateType.DATE.equals(type)) {
					if (!isDate(value, validateBean.getDatePattern(),true)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不是有效日期格式。");
					}
				}
				
				if (RowValidateType.EMAIL.equals(type)) {
					if (!isEmail(value)) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不是有效EMAIL格式。");
					}
				}
				
				if (RowValidateType.BOOLEAN.equals(type)) {
					try{
						Boolean.parseBoolean(value);
					}catch (Exception e) {
						errorMessages.add(file.getName()+":第" + row + "行" + col + "数据不正确。");
					}
				}
				
				//唯一性校验
				if(RowValidateType.UNIQUE.equals(type)){
					if(!isBlankOrNull(value)){
						if(uniqueValidator==null){
							throw new ValidationConfigurationException("UniqueValidator must not be null.");
						}else{
							if(uniqueValidator.validate(validateBean.getFieldName(),value)){
								errorMessages.add(file.getName()+":第" + row + "行" + col + "["+value+"] 已存在。");
							}
						}
					}
				}
				
				//add by kouyunhao 2014-04-03 匹配校验。
				if(RowValidateType.MATCH.equals(type)){
					if(!isBlankOrNull(value)){
						if(uniqueValidator==null){
							throw new ValidationConfigurationException("UniqueValidator must not be null.");
						}else{
							if(uniqueValidator.validate(validateBean.getFieldName(),value)){
								errorMessages.add(file.getName()+":第" + row + "行" + col + "["+value+"] 没有相匹配的编码。");
							}
						}
					}
				}
			}
		}
	}

	private boolean startCheck(String reg, String string) {
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		return tem;
	}

	/**
	 * @param uniqueValidator the uniqueValidator to set
	 */
	public void setUniqueValidator(UniqueValidator uniqueValidator) {
		this.uniqueValidator = uniqueValidator;
	}
	
	
}
