package com.xiangxun.atms.module.perambulate.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
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
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfo;
import com.xiangxun.atms.module.perambulate.service.PerambulateInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;


/**
 * 巡检模块
 * @author miaoxu
 *
 */
@Controller
@RequestMapping(value="alarm/perambulateList")
public class PerambulateListCtl extends BaseCtl {
	
	@Resource
	PerambulateInfoService perambulateInfoService;

	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	UserService userService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	CabInfoService cabInfoService;
	
	@Resource
	DatabaseInfoService databaseInfoService;
	
	@Resource
	FtpInfoService ftpInfoService;
	
	@Resource
	ProjectInfoService projectInfoService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "inserttime") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行巡检信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "inserttime desc");
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

		Page page = perambulateInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		PerambulateInfo perambulate = new PerambulateInfo();
		
		/*perambulate.setDevicecode(request.getParameter("devicecode"));
		perambulate.setDevicename(request.getParameter("devicename"));
		perambulate.setDeviceip(request.getParameter("deviceip"));*/
		
		try {
			BeanUtils.populate(perambulate, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("perambulate", perambulate);
		
		return "alarm/perambulate/details/list";
	}

	/***
	 * 根据设备名字和类型,查出设备信息
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="referAsset/{menuid}/")
	public String referAsset(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		//searchParams.put("sortType", "id");
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

		//Page page = perambulateInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
	
		Page page = assetInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
			
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		AssetInfo assetInfo = new AssetInfo();
		
		try {
			BeanUtils.populate(assetInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("assetInfo", assetInfo);
		
		return "alarm/perambulate/add/assetDetails";
	}
	
	/***
	 * 保存巡检工单的信息
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="save/{menuid}/")
	public String save(@PathVariable String menuid,ModelMap model,HttpServletRequest request){
		
		String id = request.getParameter("id");
		
		//获取32位的uuid
		String UUID = UuidGenerateUtil.getUUIDLong();
		
		String reason = request.getParameter("reason");
		PerambulateInfo perambulate = new PerambulateInfo();
		
		AssetInfo assetInfo = assetInfoService.getById(id);
		String type = assetInfo.getAssettype();
		
		if("device".equals(type)){
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo = deviceInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(deviceInfo.getIp());
		}
		
		if("cabinet".equals(type)){
			CabInfo cabInfo = new CabInfo();
			cabInfo = cabInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(cabInfo.getIp());
		}
		
		if("database".equals(type)){
			DatabaseInfo databaseInfo = new DatabaseInfo();
			databaseInfo = databaseInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(databaseInfo.getIp());
		}
		
		if("ftp".equals(type)){
			FtpInfo ftpInfo = new FtpInfo();
			ftpInfo = ftpInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(ftpInfo.getIp());
		}
		
		if("project".equals(type)){
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo = projectInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(projectInfo.getIp());
		}
		
		if("server".equals(type)){
			ServerInfo serverInfo = new ServerInfo();
			serverInfo = serverInfoService.getById(assetInfo.getDeviceid());
			perambulate.setDeviceip(serverInfo.getServerip());
		}

		
		perambulate.setDevicecode(assetInfo.getAssetcode());
		perambulate.setDeviceid(assetInfo.getDeviceid());
		perambulate.setDevicename(assetInfo.getAssetname());
		perambulate.setDevicetype(assetInfo.getAssettype());
		perambulate.setPosition(assetInfo.getInstallplace());
		//perambulate.setDeviceip(deviceInfo.getIp());

		perambulate.setId(UUID);
		perambulate.setReason(reason);
		perambulate.setUserid(getCurrentUser().getId());
		
		perambulateInfoService.save(perambulate);
		
		model.addAttribute("status", true);
		model.addAttribute("menuid", menuid);
		
		return "alarm/perambulate/add/assetDetails";
	}
	
	
	/***
	 * 巡检信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "INSERTTIME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<PerambulateInfo> list = perambulateInfoService.selectByExampleDiy(searchParams,sortType);
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			PerambulateInfo perambulateInfo = list.get(i);
			//建设时间格式转换
			/*Date buildtime =perambulateInfo.getInserttime() ;
			String buildtimeStr = DateUtil.dateFormatToString(buildtime, "yyyy-MM-dd HH:mm:SS");
			*/
			//巡检人
			User user= new User();
			if(perambulateInfo.getUserid() != null){
				user = userService.getById(perambulateInfo.getUserid());

			}
			String userName = "";
			String userMobile = "";
			if(user!=null){
				userName  = user.getName();
				userMobile = user.getMobile();
			}

			
			//所属部门信息
			/*Department department = new Department();
			if(perambulateInfo.getOrgid() != null){
				
				department = departmentService.getById(perambulateInfo.getOrgid());

			}
			String departmentName = "";
			if(department!=null){
				departmentName  = department.getName();
			}*/
			//建设厂商信息
			/*DeviceCompanyInfo deviceCompanyInfo = new DeviceCompanyInfo();
			if(perambulateInfo.getCompanyid() != null){
				deviceCompanyInfo = deviceCompanyInfoService.getById(perambulateInfo.getCompanyid());

			}
			String deviceCompanyName = "";
			if(deviceCompanyInfo!=null){
				deviceCompanyName = deviceCompanyInfo.getName();
			}*/
			String devicetype = "";
			String type = perambulateInfo.getDevicetype();
			if("device".equals(type)){
				devicetype = "卡口设备";
			}
			if("database".equals(type)){
				devicetype = "数据库";
			}
			if("project".equals(type)){
				devicetype = "平台";
			}
			if("ftp".equals(type)){
				devicetype = "FTP";
			}
			if("server".equals(type)){
				devicetype = "服务器";
			}
			if("cabinet".equals(type)){
				devicetype = "智能机柜";
			}	

			//封装数组
			Object[] values = new Object[] { 
				i+1, 
				perambulateInfo.getDevicename(),
				perambulateInfo.getDevicecode(),
				perambulateInfo.getDeviceip(),
				devicetype,
				perambulateInfo.getPosition(),
				//userName,
				//userMobile,
				//departmentName,
				//deviceCompanyName,
				//buildtimeStr,
				perambulateInfo.getReason(),
				perambulateInfo.getNote(),

			
				
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("巡检信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "perambulateInfo_exp.xls", null, result, response);
	}
}
