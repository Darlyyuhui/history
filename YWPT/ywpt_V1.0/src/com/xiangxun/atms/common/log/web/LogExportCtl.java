package com.xiangxun.atms.common.log.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
/***
 * 操作日志导出处理
 * @author guikaiping
 */
@Controller
@RequestMapping("system/log/export")
public class LogExportCtl extends BaseCtl{

	@Resource
	OperateLogService operateLogService;
	
	/***
	 * 导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="导出操作日志信息")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.GET)
	public void doExport(@PathVariable String menuid, @RequestParam(value="tag") String tag, ModelMap model,ServletRequest request,HttpServletResponse resp){
		//导出操作日志信息
		if("log".equals(tag)){
			logExport(menuid,model,request,resp);
		}
		//导出按操作人员统计违法确认操作日志数
		if("statis".equals(tag)){
			logStatisExport(menuid,model,request,resp);
		}
	}
	
	/***
	 * 操作日志的导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings("unchecked")
	public void logExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(2);
		// 获取数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = operateLogService.getLogsByCondition(searchParams, 0,999999, "id");
		List<OperationLog> operationLogs = page.getResult();
		// 设置内容
		List result = new ArrayList();
		
		for (int i = 0; i < operationLogs.size(); i++) {
			OperationLog operationLog = operationLogs.get(i);
			//操作类型
			String operateType = operationLog.getOperateType();
			if (operateType.equals("a")) {
				operateType = "新增";
			} else if (operateType.equals("u")) {
				operateType = "修改";
			} else if (operateType.equals("d")) {
				operateType = "删除";
			} else if (operateType.equals("o")) {
				operateType = "其他";
			}
			//是否成功
			String isSuccess = operationLog.getIsSuccess();
			if (isSuccess.equals("1")) {
				isSuccess = "成功";
			} else {
				isSuccess = "失败";
			}
			Object[] values = new Object[] { 
				operationLog.getModuleName(),
				operationLog.getDes(),
				operationLog.getIp(),
				operationLog.getOperateTime(),
				isSuccess,
				
				operateType,
				operationLog.getOperator()
			};
			result.add(values);
		}

		//导出日期
		String day = DateUtil.currentDate();
		export.setFileName("操作日志信息("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "log_exp.xls", null, result, resp);
	}
	
	/***
	 * 导出按操作人员统计违法确认操作日志数
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings("unchecked")
	public void logStatisExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		// 获取数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = operateLogService.getLogsStatis(searchParams, 0,999999);
		List<OperationLog> operationLogs = page.getResult();
		// 设置内容
		List result = new ArrayList();
		
		for (int i = 0; i < operationLogs.size(); i++) {
			OperationLog operationLog = operationLogs.get(i);

			Object[] values = new Object[] { 
				operationLog.getOperator(),
				operationLog.getCounts()
			};
			result.add(values);
		}
		
		//导出日期
		String day = DateUtil.currentDate();
		if (null != searchParams && searchParams.size() != 0) {
			//开始日期
			String startTime = searchParams.get("startTime") == null?"":searchParams.get("startTime").toString();
			//结束日期
			String endTime = searchParams.get("endtime") == null?"":searchParams.get("endtime").toString();
			//构造要修改的值集合
			List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
			//修改统计日期的值
			if (startTime.equals("") && endTime.equals("")) {
				excels.add(new ExcelEntity(1,0,"统计单位：条"));
			} else if (startTime.equals("") && !"".equals(endTime)) {
				excels.add(new ExcelEntity(1,0,"统计日期：开始 至 "+endTime+"     统计单位：条"));
			} else if (!"".equals(startTime) && endTime.equals("")) {
				excels.add(new ExcelEntity(1,0,"统计日期："+startTime+" 至 当前     统计单位：条"));
			} else if (!"".equals(startTime) && !"".equals(endTime)) {
				excels.add(new ExcelEntity(1,0,"统计日期："+startTime+" 至 "+endTime+"     统计单位：条"));
			}
			ExcelEntity[] t = new ExcelEntity[]{};
			//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
			export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),new ExcelMerge[]{}));
		}
		
		export.setFileName("按操作人员违法确认统计表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "logstatis_exp.xls", null, result, resp);
	}


}
