package com.xiangxun.atms.module.eventalarm.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 工单进度查询
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workschedule")
public class WorkorderScheduleCtl extends BaseCtl {

	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	UserService userService;
	
	@Resource
	DepartmentService departmentService;
	
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单派发信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "ASSIGNTIME desc");
		//权限过滤（按照部门和角色）
		String userDept = getCurrentUser().getDeptId();
		Collection<GrantedAuthority> authoritys = getCurrentUser().getAuthorities();
		boolean hasAuthority = false;
		Iterator<GrantedAuthority> it = authoritys.iterator();
		while(it.hasNext()){
			GrantedAuthority authority = it.next();
			if(authority.equals("ADMINISTRATOR")){
				hasAuthority = true;
			}
		}
		if(userDept.equals("00") || hasAuthority){
		}else{
			searchParams.put("contact", getCurrentUserId());
		}
		Page page = workorderInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		publicMethodService.setStatusColor(page);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<WorkorderInfo> list = (List<WorkorderInfo>)obj;
			if(list != null && list.size() != 0){
				for (WorkorderInfo resource : list) {
					List<KnowledgeInfo> knowledgeList = knowledgeInfoService.findKnowledgeByWorkId(resource.getId());
					if(knowledgeList != null && knowledgeList.size() != 0){
						resource.setIsappraised("1");
					}
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		WorkorderInfo workorder = new WorkorderInfo();
		try {
			BeanUtils.populate(workorder, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("workorder", workorder);
		
		return "alarm/workorder/schedule/list";
	}
	
	/***
	 * 变更记录导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="变更记录导出")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.GET)
	private void export(String menuid, ModelMap model,HttpServletRequest request,HttpServletResponse response){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//权限过滤（按照部门和角色）
		String userDept = getCurrentUser().getDeptId();
		Collection<GrantedAuthority> authoritys = getCurrentUser().getAuthorities();
		boolean hasAuthority = false;
		Iterator<GrantedAuthority> it = authoritys.iterator();
		while(it.hasNext()){
			GrantedAuthority authority = it.next();
			if(authority.equals("ADMINISTRATOR")){
				hasAuthority = true;
			}
		}
		if(userDept.equals("00") || hasAuthority){
		}else{
			searchParams.put("contact", getCurrentUserId());
		}
		Page page = workorderInfoService.getListByCondition(searchParams, 0,999999, "ASSIGNTIME desc", menuid);
		String statusname[] = {"已派发","已接收","已退回","已转派","已上报","遗留中","已关闭","已评估"};
		// 设置内容
		List result = new ArrayList();
		Object obj = page.getResult();
		List<WorkorderInfo> reports = null;
		if(obj!=null && obj instanceof List){
			reports = (List<WorkorderInfo>)obj;
			if(reports != null && reports.size() != 0){
				for (int i = 0; i < reports.size(); i++) {
					Object[] values = new Object[11];
					WorkorderInfo report = reports.get(i);
					values[0] = i+1;
					values[1] = report.getDevicename();
					values[2] = report.getDevicecode() == null ? null : report.getDevicecode();
					values[3] = report.getDeviceip() == null ? null : report.getDeviceip();
					values[4] = report.getDevicetype() == null ? null : report.getDevicetype();
					values[5] = report.getPosition() == null ? null : report.getPosition();
					values[6] = report.getOrgid() == null ? null : departmentService.getById(report.getOrgid()).getName();
					values[7] = report.getIsouter().equals("0") ? "是" : "否";
					values[8] = report.getAssignaccount() == null ? null : userService.getById(report.getAssignaccount()).getName();
					values[9] = report.getAssigntime() == null ? null : DateUtil.formatTime("yyyy-MM-dd HH:mm:ss", report.getAssigntime());
					String status = "已派发";
					for(int j = 0; j < statusname.length; j++){
						if(report.getStatus().equals(String.valueOf(j))){
							status = statusname[j];
						}
					}
					values[10] = status;
					result.add(values);
				}
			}
		}
		//统计日期
		String day = DateUtil.formatTime("yyyy-MM-dd HH:mm:ss", new Date());
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+day+"　总计："+reports.size()));
		List<ExcelMerge> merges = new ArrayList<ExcelMerge>();
		ExcelEntity[] t = new ExcelEntity[]{};
		ExcelMerge[] h = new ExcelMerge[]{};
		
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),merges.toArray(h)));
	
		export.setFileName("工单进度信息统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "workorder_exp.xls", null, result, response);
	}

	
}
