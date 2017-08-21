package com.xiangxun.atms.framework.compnents.xls.imports.demo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.ResourceUtils;

import com.xiangxun.atms.framework.compnents.xls.imports.XLSUtil;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.AbstractXlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsModel;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelForMartValidtor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;

/**
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public  class SampleXlsImportor extends AbstractXlsMapping{
	@SuppressWarnings("unused")
	private XlsModel xlsModel;
	
	public SampleXlsImportor(int headerRowNo){
		this.setExcelImportValidator(new ExcelImportValidator());
		this.setFormartRowNo(headerRowNo);
	} 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//设置从第几行开始读取
		SampleXlsImportor sampleXlsImportor = new SampleXlsImportor(3);
		//检验使用的模板是否正确
		sampleXlsImportor.setExcelForMartValidtor(new ExcelForMartValidtor() {
			@Override
			public boolean isValid(Row row) {
				String cel0 = XLSUtil.getCell(row.getCell(0));
				String cel1 = XLSUtil.getCell(row.getCell(1));
				String cel2 = XLSUtil.getCell(row.getCell(2));
				String cel3 = XLSUtil.getCell(row.getCell(3));
				if(!"序号".equals(cel0)){
					return false;
				}
				if(!"道路".equals(cel1)){
					return false;
				}
				if(!"方向".equals(cel2)){
					return false;
				}
				if(!"车道数".equals(cel3)){
					return false;
				}
				return true;
			}
		});
		List<String> files = new ArrayList<String>();
		String path = sampleXlsImportor.getClass().getPackage().getName().replace(".","/");
		try {
			String filePath = ResourceUtils.getFile("classpath:"+path+"/sample.xls").getAbsolutePath();
			files.add(filePath);
			//获取错误消息
			List<String> s = sampleXlsImportor.importValite(files);
			for (String string : s) {
				System.out.println(string);
			}
			//获取封装的对象
			List<Object> list = sampleXlsImportor.getObjectList();
			for (Object object : list) {
				TestModel testModel = (TestModel)object;
				System.out.println(testModel);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.common.xls.imports.AbstractXlsImportProcessor#getClsName()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public	Class getClsName() {
		return TestModel.class;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.framework.common.xls.imports.AbstractXlsImportProcessor#getTransferMap()
	 */
	@Override
	public Map<String, String> getTransferMap() {
		Map<String,String> transferMap = new HashMap<String,String>();
		transferMap.put("已导入", "1");
		transferMap.put("未导入", "0");
		return transferMap;
	}
	
	
	
}
