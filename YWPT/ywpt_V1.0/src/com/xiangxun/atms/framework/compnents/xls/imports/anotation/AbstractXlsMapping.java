package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.xiangxun.atms.framework.compnents.xls.imports.AbstractXlsImportProcessor;
import com.xiangxun.atms.framework.compnents.xls.imports.XLSUtil;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean;

/**
 * <p>xls数据映射处理器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public abstract class AbstractXlsMapping extends AbstractXlsImportProcessor{
	
	/***
	 * 待转换的对象模型
	 */
	private XlsModel xlsModel;
	
	private XlsMappingResolver xlsMappingResolver = new XlsMappingResolver();
	
	private XlsValidateResolver xlsValidateResolver = new XlsValidateResolver();
	
	public Serializable getRowModel(Row row) {
		xlsModel = getXlsModel();
		int rowNo = row.getRowNum();
		Object object =	getModelClass();
		BeanWrapper bw = new BeanWrapperImpl(object);
		List<XlsProperty> props = xlsModel.getXlsPropertys();
		Map<String,String> transferMap =  xlsModel.getTransferMap();
		if(object!=null){
				for (XlsProperty xlsProperty : props) {
					//待转换的model的属性名称
					String propName = xlsProperty.getPropertyName();
					int columnNo = xlsProperty.getXlsCellColumn();
					String value = null;
					if(columnNo == -1){
						//如果是固定列的值
						if(xlsProperty.getFixity()){
							 value = xlsProperty.getFixValue();
							if(StringUtils.isEmpty(value)){
								value = xlsProperty.getDefaultValue();
							}
						}
						
					}else{
						String val = XLSUtil.getCell(row.getCell(columnNo));
						logger.debug(rowNo+":"+columnNo+"-->>"+val);
						xlsProperty.setXlsCellValue(val);
						//如果此值需要转换 
						if(xlsProperty.getIstransfer()){
							try{
								value = transferMap.get(val);
							}catch (NullPointerException e) {
								//获取转换的值失败，则设置为excel对应的值
								value = val;
							}
						}else{
							value = val;
						}
					}
					logger.debug("set "+object.getClass().getName()+" (propertity name:" +propName+" value is "+value+")");
					bw.setPropertyValue(propName, value);
			}
		}
		return (Serializable) object;
	}
	/**
	 * 有日期的方法
	 * @param row
	 * @param formatStr
	 * @return
	 */
	public Serializable getRowModel(Row row,String formatStr) {
		xlsModel = getXlsModel();
		int rowNo = row.getRowNum();
		Object object =	getModelClass();
		BeanWrapper bw = new BeanWrapperImpl(object);
		List<XlsProperty> props = xlsModel.getXlsPropertys();
		Map<String,String> transferMap =  xlsModel.getTransferMap();
		if(object!=null){
				for (XlsProperty xlsProperty : props) {
					//待转换的model的属性名称
					String propName = xlsProperty.getPropertyName();
					int columnNo = xlsProperty.getXlsCellColumn();
					String value = null;
					if(columnNo == -1){
						//如果是固定列的值
						if(xlsProperty.getFixity()){
							 value = xlsProperty.getFixValue();
							if(StringUtils.isEmpty(value)){
								value = xlsProperty.getDefaultValue();
							}
						}
						
					}else{
						String val = XLSUtil.getCell(row.getCell(columnNo),formatStr);
						logger.debug(rowNo+":"+columnNo+"-->>"+val);
						xlsProperty.setXlsCellValue(val);
						//如果此值需要转换 
						if(xlsProperty.getIstransfer()){
							try{
								value = transferMap.get(val);
							}catch (NullPointerException e) {
								//获取转换的值失败，则设置为excel对应的值
								value = val;
							}
						}else{
							value = val;
						}
					}
					logger.debug("set "+object.getClass().getName()+" (propertity name:" +propName+" value is "+value+")");
					bw.setPropertyValue(propName, value);
			}
		}
		return (Serializable) object;
	}


	/***
	 * 设置待转换的对象模型
	 * @return
	 */
	XlsModel getXlsModel(){
		XlsModel model = new XlsModel();
		List<XlsProperty> xlsPropertys;
		if(isAnotation()){
			xlsPropertys = xlsMappingResolver.process(getClsName());
		}else{
			xlsPropertys = getXlsPropertyList();
		}
		model.setXlsPropertys(xlsPropertys);
		model.setTransferMap(getTransferMap());
		return model;
	}
	
	/***
	 * 设置xls属性对象，预留扩展点(当isAnotation返回false时调用)
	 * @return
	 */
	protected List<XlsProperty> getXlsPropertyList(){
		return null;
	}
	

	/***
	 * 预留扩展点，行内设置单元格的验证类(当isAnotation返回false时调用)
	 * @return
	 */
	protected List<ValidateBean> getValidateBean(Row row){
		return null;
	}
	
	/****
	 * 设置待转换值对应的map数据
	 * @return
	 */
	public abstract Map<String,String> getTransferMap();

	/***
	 * 设置待转换的类对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract Class getClsName();
	
	
	/***
	 * 行内设置单元格的验证类
	 * @return
	 */
	public List<ValidateBean> getValidateBeans(Row row){
		List<ValidateBean> validateBeans = null;
		if(isAnotation()){
			validateBeans = xlsValidateResolver.process(getClsName());
			for (ValidateBean validateBean : validateBeans) {
				int cell = validateBean.getCellNo();
				String val = XLSUtil.getCell(row.getCell(cell));
				String headerName = XLSUtil.getCell(getHeaderRow().getCell(cell));
				validateBean.setRow(row.getRowNum()+1);
				validateBean.setColName("【"+headerName+"】");
				validateBean.setValue(val);
			}
		}else{
			validateBeans = getValidateBean(row);
		}
		return validateBeans;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.framework.common.xls.imports.XLSImportProcessor#isAnotation()
	 */
	@Override
	public Boolean isAnotation() {
		return true;
	}

	
	/**
	 * 通过对象名得到对象
	 * @param className
	 * @return
	 */
	private Object getModelClass() {
		Object obj = null;
		try {
			obj = getClsName().newInstance();
		} catch (InstantiationException e) {
			logger.error("实例化对象时发生错误："+e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error("实例化对象时发生错误：" + e.getMessage());
		}
		logger.debug("init Class = " + obj.getClass().getName());
		return obj;
	}
	
	
	

}
