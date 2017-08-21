package com.xiangxun.atms.module.perambulate.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.io.Files;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.XlsExportException;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.web.StatusMonitorCtl;
import com.xiangxun.atms.module.perambulate.service.PerambulateInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.ShowIcabinetStatus;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 巡检报告定時导出
 * 
 * @author yangzhenyu
 *
 */

public class PerambulateExport {

	protected Logging logger = new Logging(getClass());
	@Resource
	AssetInfoService assetInfoService;
	@Resource
	DicService dicService;
	@Resource
	PerambulateInfoService perambulateInfoService;
	@Resource
	DepartmentService departmentService;
	@Resource
	FactoryInfoService factoryInfoService;
	@Value("${cabinet.pattern}")
    int pattern; 
    
	public void export() {

		try {
			logger.info("巡检报告生成中。。。。。。。。" + pattern);
			// 3 表示从第几行开始写入
			SimpleXlsExportor export = new SimpleXlsExportor(3);
	
			List result = new ArrayList();
			
			List<AssetInfo> assetinfolist = assetInfoService.getAll();
			
			for (int i = 0; i < assetinfolist.size(); i++) {
				AssetInfo assetInfo2 = assetinfolist.get(i); 
				FactoryInfo factoryInfo=factoryInfoService.getById(assetInfo2.getFactoryId());
				//卡口
				if (assetInfo2.getAssettype().equals("device")) {
					if(pattern==1){
						Object[] values = new Object[] { 
								i + 1,
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"卡口",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getPowerStatus() != null && assetInfo2.getPowerStatus().equals("1") ? "正常": "异常",
								"", 
								"", 
								"",
								"",
								"",
								"",
								"",
								"",
								"",
							    factoryInfo.getName(),

						};
						result.add(values);
					}else if(pattern==2){
						Object[] values = new Object[] { 
								i + 1,
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"卡口",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getPowerStatus() != null && assetInfo2.getPowerStatus().equals("1") ? "正常"
										: "异常",
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"", 
								"", 
								"",
								"",
								"",
							    factoryInfo.getName(),

						};
						result.add(values);
					}else{
						Object[] values = new Object[] { 
								i + 1,
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"卡口",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getPowerStatus() != null && assetInfo2.getPowerStatus().equals("1") ? "正常"
										: "异常",
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"", 
								"", 
								"",
							    factoryInfo.getName(),

						};
						result.add(values);
					}
					//数据库
				}else if (assetInfo2.getAssettype().equals("database")) {
					if(pattern==1){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"数据库",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"",
								"", 
								"", 
								"",
								"",
								"",
								"",
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else if(pattern==2){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"数据库",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"",
								"", 
								"", 
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else{
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"数据库",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(),
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"",
								"", 
								"", 
								factoryInfo.getName(),
								};
						result.add(values);
					}
					//服务器
				} else if (assetInfo2.getAssettype().equals("server")) {
					if(pattern==1){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"服务器",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								"",
								"", 
								"",
								assetInfo2.getCpuStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getMemoryStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDiskStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常": "异常", 
								"",
								"",
								"",
								"",
								"",
								"",
								factoryInfo.getName(),		
						};
						result.add(values);
					}else if(pattern==2){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"服务器",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								"",
								"", 
								"",
								assetInfo2.getCpuStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getMemoryStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDiskStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常": "异常", 
								"",
								"",
								factoryInfo.getName(),		
						};
						result.add(values);
					}else{
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"服务器",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								"",
								"", 
								"",
								assetInfo2.getCpuStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getMemoryStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDiskStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常": "异常", 
								factoryInfo.getName(),		
						};
						result.add(values);
					}
					//ftp
				} else if (assetInfo2.getAssettype().equals("ftp")) {
					if(pattern==1){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"ftp",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"",
								"", 
								"",
								"",
								"",
								"",
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else if(pattern==2){
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"ftp",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"",
								"", 
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else{
						Object[] values = new Object[] { 
								i + 1, 
								assetInfo2.getAssetcode(),
								assetInfo2.getAssetname(),
								"ftp",
								assetInfo2.getIp(), 
//								assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"",
								"", 
								factoryInfo.getName(),
								};
						result.add(values);
					}
					//平台
				} else if (assetInfo2.getAssettype().equals("project") ){
					if(pattern==1){
						Object[] values = new Object[] {
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"平台",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"", 
								"", 
								"",
								"",
								"",
								"",
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else if(pattern==2){
						Object[] values = new Object[] {
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"平台",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"", 
								"", 
								"",
								"",
								factoryInfo.getName(),
								};
						result.add(values);
					}else{
						Object[] values = new Object[] {
								i + 1, 
								assetInfo2.getAssetcode(), 
								assetInfo2.getAssetname(),
								"平台",
								assetInfo2.getIp(), 
		//						assetInfo2.getOrgName(), 
								assetInfo2.getNetStatus() != null && assetInfo2.getNetStatus().equals("1") ? "正常" : "异常",
								assetInfo2.getDataStatus() != null && assetInfo2.getDataStatus().equals("1") ? "正常" : "异常",
								"",
								"", 
								"", 
								"", 
								factoryInfo.getName(),
								};
						result.add(values);
					}
					}
		
			}
			//机柜
			List<ShowIcabinetStatus> status = new ArrayList<>();
			String assetName = "", assetstatus = "";
			String orgId="";
			int totalCount =new StatusMonitorCtl().getCabinetStatus(assetName, assetstatus, orgId, 0, 5000,status);
			Page page = Page.getPage(totalCount, status, 0, 5000);
			List<ShowIcabinetStatus> listCabinet = page.getResult();
			for (int i = 0; i < listCabinet.size(); i++) {
				ShowIcabinetStatus showIcabinetStatus = listCabinet.get(i);
				FactoryInfo factoryInfo = factoryInfoService.getById(showIcabinetStatus.getServiceid());
				// 封装数组
				if(pattern==1){
					Object[] values = new Object[] {
							i + 1,
							showIcabinetStatus.getAssetcode(),
							showIcabinetStatus.getAssetname(), 
							"智能机柜",
							showIcabinetStatus.getIp(), 
			//				showIcabinetStatus.getOrgName(),
							showIcabinetStatus.getNetStatus() != null && showIcabinetStatus.getNetStatus().equals("1") ? "正常" : "异常",
							"",
							showIcabinetStatus.getPowerStatus() != null && showIcabinetStatus.getPowerStatus().equals("1") ? "正常" : "异常", 
							"",
							"",
							"",
						    showIcabinetStatus.getTemperature() != null && showIcabinetStatus.getTemperature().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getHumidity() != null && showIcabinetStatus.getHumidity().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getSmoke()!= null && showIcabinetStatus.getSmoke().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getShock()!= null && showIcabinetStatus.getShock().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getACterminal()!= null && showIcabinetStatus.getACterminal().equals("1") ? "正常" : "异常",
							(showIcabinetStatus.getACcurrent()!= null && showIcabinetStatus.getACcurrent().equals("1"))&&(showIcabinetStatus.getACvoltagevalue()!= null && showIcabinetStatus.getACvoltagevalue().equals("1")) ? "正常" : "异常",
							factoryInfo.getName() 
					};
					result.add(values);
				}else if(pattern==2){
					Object[] values = new Object[] {
							i + 1,
							showIcabinetStatus.getAssetcode(),
							showIcabinetStatus.getAssetname(), 
							"智能机柜",
							showIcabinetStatus.getIp(), 
			//				showIcabinetStatus.getOrgName(),
							showIcabinetStatus.getNetStatus() != null && showIcabinetStatus.getNetStatus().equals("1") ? "正常" : "异常",
							"",
							showIcabinetStatus.getPowerStatus() != null && showIcabinetStatus.getPowerStatus().equals("1") ? "正常" : "异常", 
							"",
							"",
							"",
						    showIcabinetStatus.getTemperature() != null && showIcabinetStatus.getTemperature().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getHumidity() != null && showIcabinetStatus.getHumidity().equals("1") ? "正常" : "异常",
							factoryInfo.getName() 
					};
					result.add(values);
				}else{
					break;
				}
				
			}
				String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
	
				URI uri = PerambulateExport.class.getProtectionDomain().getCodeSource().getLocation().toURI();
				
				
				String cabinetexcel="";
				//根据不同机柜的不同类型，写入对应excel文件，1：海康机柜  2：翔迅传感器
				if(pattern==1){
					cabinetexcel="haikang_routing_inspection.xls";
				}else if(pattern==2){
					cabinetexcel="routing_inspection.xls";
				}else{
					cabinetexcel="routing_inspection_nocabinet.xls";
				}
				try (InputStream ins = export.getInputStream("xls" + File.separator + cabinetexcel, null,
						result)) {
					if (ins == null) {
						throw new XlsExportException("XLS-0003");
					}
					File classPath = new File(uri);
					classPath = classPath.getParentFile().getParentFile();
					Path p = Paths.get(classPath.getPath(), "download", "xls");
					if (!p.toFile().exists())
						java.nio.file.Files.createDirectory(p);
					// 获得一个工作薄对象
					HSSFWorkbook wbook = export.getWorkbook();
					if (wbook == null)
						wbook = new HSSFWorkbook(ins);
					
					//获取当前时间为excel命名
					Date date=new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			        String dateNowStr = sdf.format(date);  
					p = Paths.get(p.toString(), dateNowStr+"巡检报告"+".xls");
					//如果文件存在则删除。
					if (p.toFile().exists())
						p.toFile().delete();
					try (FileOutputStream out = new FileOutputStream(p.toFile())) {
						wbook.write(out);
					}
				
				logger.info("巡检报告已生成。。。。。。。。。");
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}
}
