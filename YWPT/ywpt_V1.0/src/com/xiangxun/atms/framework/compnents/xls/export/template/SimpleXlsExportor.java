package com.xiangxun.atms.framework.compnents.xls.export.template;


/***
 * <h2>xls 按模板文件导出器</h2>
 * <pre>使用方式如：
 * 		//3 表示从第几行开始写入   	
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//需要对模板中的第2行第一单元格的文字进行动态替换
		ExcelEntity dateEntity = new ExcelEntity(1,0,str);
		//对第一行第一个单元格内容进行处理
		ExcelEntity titleEntity = new ExcelEntity(0,0,str2);
		
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(new ExcelEntity[]{dateEntity,titleEntity}));
		//设置内容
		List result = new ArrayList();
		for (int i = 0; i < resultList.size(); i++) {
			RecordDayForm  record = resultList.get(i);
			Object[] values = new Object[]{
				i+1,
				record.getRoadName(),
				record.getDevName(),
				record.getDiRectionName(),
				record.getLineNum(),
				record.getMorningAvgSpeed(),
				record.getMiddayAvgSpeed(),
				record.getAfternoonAvgSpeed(),
				record.getFluxCount()					
			};
			result.add(values);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//此处的avg_speed_tempate.xls为模板文件的名称,加载时默认按照classpath路径
		//不需要header,传入null
		export.exportExcelFileStream("avg_speed_tempate.xls",null,result,response);
 * </pre>
 * @author zhouhaijian
 * @version 1.0
 * @since 1.0
 *
 */
public class SimpleXlsExportor extends AbstractXlsTemplateExportor{
	
	int formRowNo = 0;
	
	/**
	 *根据实际需求对从第几行写入数据，进行自定义
	 */ 
	public SimpleXlsExportor(int rowNo){
		this.formRowNo = rowNo;
	}
	
	/* (non-Javadoc)
	 * @see com.diablo.framework.web.export.AbstractXlsTemplateExportor#getWriterRowNo()
	 */
	@Override
	protected int getWriterRowNo() {
		return formRowNo;		
	}
}
