package com.xiangxun.atms.module.analysis.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.framework.vo.KeyValue;
import com.xiangxun.atms.module.analysis.service.AirAnalysisService;
import com.xiangxun.atms.module.analysis.service.impl.AirAnalysisXlsImportService;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.analysis.vo.AirAnalysisSearch;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.AirPointCache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.bs.vo.AirPoint;
import com.xiangxun.atms.module.reg.service.AirRegService;
import com.xiangxun.atms.module.reg.vo.AirReg;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping(value = "analysis/air")
@SessionAttributes(value = "airpoints")
public class AirAnalysisCtl extends BaseCtl<AirAnalysis, AirAnalysisSearch> {
    
	@Resource
    AirAnalysisService airAnalysisService;
	@Resource
	AirAnalysisXlsImportService airAnalysisXlsImportService;
	@Resource
	AirRegService airRegService;
    @Resource
    Cache cache;

	@Override
	protected BaseService<AirAnalysis, AirAnalysisSearch> getBaseService() {
		return airAnalysisService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "T.CODE ASC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = airAnalysisService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "analysis/air/list";
	}
	
	private void initModel(Model model) {
		@SuppressWarnings("unchecked")
		List<AirPoint> apList = (List<AirPoint>)cache.get(AirPointCache.ALL_ITEM);
		model.addAttribute("airpoints", apList);
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("regs", airRegService.getInfoByAnalysis());
		return "analysis/air/add";
	}
	
	/**
	 * 获取空气采样登记信息Ajax
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getRegById/{id}/")
	public AirReg getRegById(@PathVariable String id) {
		return this.getRegInfo(id);
	}
	
	/**
	 * 查询空气采样登记信息
	 * @param regId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AirReg getRegInfo(String regId) {
		AirReg reg = airRegService.getById(regId);
		Table<String, String, String> pointTable = (Table<String, String, String>)cache.get(AirPointCache.ID_NAME);
		Map<String, String> pointMap = pointTable.column(AirPointCache.ID_NAME);
		reg.setPointId(pointMap.get(reg.getPointId()));
		reg.setSamplingTimeStr(DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss", reg.getSamplingTime()));
		return reg;
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【空气样品分析】")
	public String doAdd(@PathVariable String menuid, AirAnalysis info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.ANALYSIS_AIR);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		this.getPolluteFlux(info);
		airAnalysisService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/analysis/air/list/"+menuid+"/";
	}
	
	/**
	 * 计算污染通量
	 * @param info
	 */
	private void getPolluteFlux(AirAnalysis info) {
		if (info == null || info.getCadmium() == null) {
			return;
		}
		try {
			//镉含量
			double cadmiumVal = info.getCadmium().doubleValue();
			AirReg reg = this.getRegInfo(info.getRegId());
			//收集量
			double cvVal = Double.parseDouble(reg.getCollectVolume());
			//污染通量值
			double pfVal = cadmiumVal*cvVal*10*667*2/400;
			info.setPolluteFlux(pfVal+"");
		}catch(Exception e) {
			logger.error("收集量转换失败。");
		}
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		AirAnalysis info = airAnalysisService.getById(id);
		if (info != null && StringUtils.isNotEmpty(info.getRegId())) {
			model.addAttribute("regInfo", this.getRegInfo(info.getRegId()));
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "analysis/air/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【空气样品分析】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")AirAnalysis info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		this.getPolluteFlux(info);
		airAnalysisService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/analysis/air/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【空气样品分析】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【空气样品分析】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                airAnalysisService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showView/{menuid}/{id}/")
	public String showView(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		AirAnalysis info = airAnalysisService.getById(id);
		if (info != null && StringUtils.isNotEmpty(info.getRegId())) {
			model.addAttribute("regInfo", this.getRegInfo(info.getRegId()));
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "analysis/air/view";
	}
    
    /**
	 * 导出操作
	 * @return
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "doExport/", method = RequestMethod.POST)
	public void doExport(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> searchParams = new HashMap<String, Object>();
		
		String params = request.getParameter("params");
		String[] array = params.split("&");
		String[] temp = null;
		String key = null;
		for (String str : array) {
			temp = str.split("=");
			key = temp[0].substring("search_".length(), temp[0].length());
			searchParams.put(key, temp.length==1?"":temp[1]);
		}
    	
		Page page = airAnalysisService.getListByCondition(searchParams, 1, 65500, "T.CODE ASC");
		Table<String, String, String> regionTable = (Table<String, String, String>)cache.get(TRegionCache.ID_FULLNAME);
		Map<String, String> regionMap = regionTable.column(TRegionCache.ID_FULLNAME);
		
		Table<String, String, AirPoint> pointTable = (Table<String, String, AirPoint>)cache.get(AirPointCache.ID_OBJ);
		Map<String, AirPoint> pointMap = pointTable.column(AirPointCache.ID_OBJ);
		
		List<AirAnalysis> list = page.getResult();
		AirPoint ap = null;
		for (AirAnalysis info : list) {
			ap = pointMap.get(info.getPointId());
			if (ap != null) {
				info.setPointId(ap.getName());
				info.setRegionId(regionMap.get(ap.getRegionId()));
			}
			info.setSamplingTimeStr(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", info.getSamplingTime()));
			info.setIsOverStr(info.getIsOver()==0?"否":"是");
		}
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("airList", list);
		
		String fileName = "大气采样分析数据" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xls";
		
		InputStream is = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		
			is = this.getTemplateFile("xls" + File.separator + "analysis_air_export.xls");
		    os = response.getOutputStream();
		    
		    XLSTransformer transformer = new XLSTransformer();
		    Workbook workbook = transformer.transformXLS(is, beans);
		    
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				os = null;
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				is = null;
			}
		}
	}
    
    //TODO 结构变更，需重做
    @RequestMapping(value = "doImport/{menuid}/", method = RequestMethod.POST)
	public String doImport(@PathVariable String menuid
			, @RequestParam("file") MultipartFile file
			, RedirectAttributes attrs, HttpServletRequest request, HttpServletResponse response) {
		
		File uploadFile = null;
        List<KeyValue> errorMessages = new ArrayList<KeyValue>();
        List<String> errors = ExcelImportValidator.errorMessages;
        //清除历史记录
        airAnalysisXlsImportService.getObjectList().clear();
        errors.clear();
        if (file.isEmpty()) {
        	attrs.addFlashAttribute("message", "错误：未获取到上传的数据文件。");
        } else {
            try{
                uploadFile = upload(file, request, response, errorMessages);
            } catch (IOException e) {
                logger.error(e);
                errorMessages.add(new KeyValue("success","false"));
                errorMessages.add(new KeyValue("msg", "系统异常"));
                attrs.addFlashAttribute("message", "数据文件上传异常。");
                return "redirect:/analysis/air/list/"+menuid+"/";
            }
            errorMessages.clear();
            try{
                List<String> files = new ArrayList<String>();
                files.add(uploadFile.getAbsolutePath());
                //获取错误消息
                List<String> s = airAnalysisXlsImportService.importValite(files);
                
                StringBuilder errStr = new  StringBuilder();
                for (int i = 0; i < s.size(); i++) {
                	errStr.append(s.get(i));
                }
                //如果有错误
                if(!s.isEmpty()){
                	attrs.addFlashAttribute("message", errStr.toString());
                }else{
                    //没有错误则清空错误消息
                    errorMessages.clear();
                    //获取封装的对象
                    List<Object> list = airAnalysisXlsImportService.getObjectList();
                    AirAnalysis info = null;
                    String message = "导入成功。";
                    for (Object obj : list) {
                    	info = (AirAnalysis)obj;
                    	//数据有效性，编号
                    	if (StringUtils.isEmpty(info.getCode())) {
                    		continue;
                    	}
                    	if (baseService.checkCode("T_ANALYSIS_AIR", "CODE", info.getCode())) {
                    		this.initAddInfo(info);
                        	airAnalysisService.save(info);
                    	} else {
                    		message += "（部分数据由于编号重复，不能保存至数据库）";
                    	}
                    }
                    attrs.addFlashAttribute("message", message);
                }
            }catch (Exception e) {
                errorMessages.add(new KeyValue("msg","数据处理异常"));
                attrs.addFlashAttribute("message", "导入失败，数据处理异常。");
            }
        } 
        return "redirect:/analysis/air/list/"+menuid+"/";
	}
    
    private void initAddInfo(AirAnalysis info) {
    	info.setId(UuidGenerateUtil.getUUIDLong());
    	info.setCreateId(this.getCurrentUserId());
    	info.setCreateTime(new Date());
    }

}