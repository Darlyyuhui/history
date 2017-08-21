package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/***
 * FTP维护
 * @author kouyunhao
 * 
 */
@Controller
@RequestMapping(value="property/ftpinfo")
public class FtpInfoCtl extends BaseCtl {
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	FtpInfoService ftpInfoService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行FTP信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = ftpInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<FtpInfo> list = (List<FtpInfo>)obj;
			if(list != null && list.size() != 0){
				for (FtpInfo resource : list) {
					FactoryInfo factory = factoryInfoService.getById(resource.getFactoryId());
					if(factory != null){
						resource.setFactoryName(factory.getName());
					}
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		//服务厂商
		request.getSession().setAttribute("factoryList", factoryInfoService.findAll());
		FtpInfo ftp = new FtpInfo();
		try {
			BeanUtils.populate(ftp, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("ftp", ftp);
		
		return "property/ftpinfo/list";
	}
	
	/***
	 * 删除一个FTP
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个FTP")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个FTP。。。。。。");
			String[] id = ids.split(",");
			
			for (int i = 0; i < id.length; i++) {

//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				FtpInfo ftp = ftpInfoService.getById(id[i]);
				String assetcode = "";
				//String assetname = "";

				List<AssetInfo> assetList = assetInfoService.findByDeviceId(id[i]);
				if(assetList != null && assetList.size() != 0){
					assetcode = assetList.get(0).getAssetcode();
					//assetname = assetList.get(0).getAssetname();

				}
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(id[i]);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(id[i]);
				//同步删除设备日志信息
				//assetInfoService.deleteDeviceLogByCode(ftp.getId());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(assetcode);
				if(workorderList != null && workorderList.size() != 0){
					for(WorkorderInfo workorder : workorderList){
						String exceptionid = workorder.getExceptionid();
						if(exceptionid != null){
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(assetcode);
				
				ftpInfoService.deleteById(id[i]);
				
				/*HashMap<String,FtpStatus> ftpStatusMap = new HashMap<String,FtpStatus>();

				for (FtpStatus ftpStatus : ftpStatusMap) {

					if(assetname.equals(ftpStatusMap.){
						
						//listDeviceStatus.clear();
						break;
					}
					
				}*/

				
				
			
			}
			
			/*for (String string : id) {
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				FtpInfo ftp = ftpInfoService.getById(string);
				String assetcode = "";
				//String assetname = "";

				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
				if(assetList != null && assetList.size() != 0){
					assetcode = assetList.get(0).getAssetcode();
					//assetname = assetList.get(0).getAssetname();

				}
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(string);
				//同步删除设备日志信息
				//assetInfoService.deleteDeviceLogByCode(ftp.getId());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(assetcode);
				if(workorderList != null && workorderList.size() != 0){
					for(WorkorderInfo workorder : workorderList){
						String exceptionid = workorder.getExceptionid();
						if(exceptionid != null){
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(assetcode);
				
				ftpInfoService.deleteById(string);
				
				HashMap<String,FtpStatus> ftpStatusMap = new HashMap<String,FtpStatus>();

				for (FtpStatus ftpStatus : ftpStatusMap) {

					if(assetname.equals(ftpStatusMap.){
						
						//listDeviceStatus.clear();
						break;
					}
					
				}

				
				
			}*/
			
			Dictionary.FtpStatusMap.clear();
			
			
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个FTP
	 * @param ftp
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个FTP")
	public String doAdd(FtpInfo ftp,AssetInfo asset,String menuid,RedirectAttributes redirectAttributes) throws ParseException{
		ftp.setId(UuidGenerateUtil.getUUID());
		ftpInfoService.save(ftp);
		String toasset = asset.getToasset();
		if(toasset.equals("1")){
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("ftp");
			asset.setAssetname(ftp.getName());
			asset.setServiceid(ftp.getFactoryId());
			asset.setDeviceid(ftp.getId());
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			//服务商ID
			asset.setFactoryId(ftp.getFactoryId());
			
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/property/ftpinfo/list/"+menuid+"/";
	}

	/***
	 * 获取待修改FTP信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		FtpInfo ftp = ftpInfoService.getById(id);
		//判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if(assetList != null && assetList.size() != 0){
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}
		model.addAttribute("isasset", isasset);
		
		model.addAttribute("ftp",ftp);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/ftpinfo/update";
	}

	/***
	 * 修改FTP信息
	 * @param ftp
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改FTP信息")
	public String doUpdate(@ModelAttribute("preload") FtpInfo ftp,AssetInfo asset,String page,String menuid,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
		ftpInfoService.updateByIdSelective(ftp);
		
		//资产配置修改
		String isasset = request.getParameter("isasset");
		asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
		asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
		asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
		//服务商ID
		asset.setFactoryId(ftp.getFactoryId());
		if(isasset.equals("1")){
			asset.setAssetname(request.getParameter("name"));
			asset.setId(request.getParameter("assetid"));
			assetInfoService.updateByIdSelective(asset);
		}else{
			String toasset = asset.getToasset();
			if(toasset.equals("1")){
				asset.setId(UuidGenerateUtil.getUUID());
				asset.setAssettype("ftp");
				asset.setAssetname(ftp.getName());
				asset.setServiceid(ftp.getFactoryId());
				asset.setDeviceid(ftp.getId());
				assetInfoService.save(asset);
			}
		}
		
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/ftpinfo/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 判断名称是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nmaeExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<FtpInfo> ftpList = ftpInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (ftpList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}

	/***
	 * 判断IP是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String ipExist(HttpServletRequest req, @RequestParam(value = "ftpip") String ftpip) {
		List<FtpInfo> ftpList = ftpInfoService.findByIp(ftpip);
		String returnStr = Boolean.FALSE.toString();
		if (ftpList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(ftpip.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 判断ip是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "isIpExist/{ip}/",method = RequestMethod.GET)
	public void isIpExist(@PathVariable String ip, ServletResponse response){
		List<FtpInfo> devList = ftpInfoService.findByIp(ip);
		try {
			response.getWriter().write(devList.size()==0?"no":"yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		FtpInfo ftp = ftpInfoService.getById(id);
		FactoryInfo factory = factoryInfoService.getById(ftp.getFactoryId());
		if(factory != null){
			ftp.setFactoryName(factory.getName());
		}
		model.addAttribute("ftp", ftp);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/ftpinfo/view";
	}
	
	/***
	 * ftp信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		//sortType = "id des";
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//searchParams.remove("ftptype");
		List<FtpInfo> list = ftpInfoService.selectByExampleDiy(searchParams,sortType,menuid);
		
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			FtpInfo ftpInfo = list.get(i);
			
			//封装数组
			Object[] values = new Object[] { 
			
				i+1, 				
				ftpInfo.getName(),
				ftpInfo.getFtptype(),
				ftpInfo.getIp(),
				ftpInfo.getPort(),
				ftpInfo.getUsername(),
				ftpInfo.getPassword(),
				ftpInfo.getDirname(),
				//查询运维服务商
				(factoryInfoService.getById(ftpInfo.getFactoryId())).getName(),


			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("ftp信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "ftpInfo_exp.xls", null, result, response);
	}
	

}
