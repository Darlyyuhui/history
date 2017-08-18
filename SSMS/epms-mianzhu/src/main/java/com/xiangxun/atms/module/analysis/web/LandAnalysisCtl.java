package com.xiangxun.atms.module.analysis.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import com.xiangxun.atms.module.analysis.service.LandAnalysisService;
import com.xiangxun.atms.module.analysis.service.impl.LandAnalysisXlsImportService;
import com.xiangxun.atms.module.analysis.vo.LandAnalysis;
import com.xiangxun.atms.module.analysis.vo.LandAnalysisSearch;
import com.xiangxun.atms.module.analysis.vo.xls.LandImport;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.reg.service.LandRegService;
import com.xiangxun.atms.module.reg.vo.LandReg;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping(value = "analysis/land")
public class LandAnalysisCtl extends BaseCtl<LandAnalysis, LandAnalysisSearch> {
	
    @Resource
    LandAnalysisService landAnalysisService;
    @Resource
    LandAnalysisXlsImportService landAnalysisXlsImportService;
    @Resource
    LandRegService landRegService;
    @Resource
    Cache cache;

	@Override
	protected BaseService<LandAnalysis, LandAnalysisSearch> getBaseService() {
		return landAnalysisService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "T.CODE ASC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = landAnalysisService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "analysis/land/list";
	}
	
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("regs", landRegService.getInfoByAnalysis());
		return "analysis/land/add";
	}
	
	/**
	 * 获取土壤采样登记信息Ajax
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getRegById/{id}/")
	public LandReg getRegById(@PathVariable String id) {
		return this.getRegInfo(id);
	}
	
	/**
	 * 查询土壤采样登记信息
	 * @param regId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private LandReg getRegInfo(String regId) {
		LandReg reg = landRegService.getById(regId);
		if (reg != null) {
			Table<String, String, String> table = (Table<String, String, String>)cache.get(TRegionCache.ID_FULLNAME);
			Map<String, String> map = table.column(TRegionCache.ID_FULLNAME);
			reg.setRegionId(map.get(reg.getRegionId()));
			if (reg.getSamplingTime() != null) {
				reg.setSamplingTimeStr(DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss", reg.getSamplingTime()));
			}
		}
		return reg;
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤样品分析】")
	public String doAdd(@PathVariable String menuid, LandAnalysis info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landAnalysisService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/analysis/land/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		LandAnalysis info = landAnalysisService.getById(id);
		if (info != null && StringUtils.isNotEmpty(info.getRegId())) {
			model.addAttribute("regInfo", this.getRegInfo(info.getRegId()));
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "analysis/land/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤样品分析】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")LandAnalysis info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		landAnalysisService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/analysis/land/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤样品分析】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤样品分析】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                landAnalysisService.deleteById(string);
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
		LandAnalysis info = landAnalysisService.getById(id);
		if (info != null && StringUtils.isNotEmpty(info.getRegId())) {
			model.addAttribute("regInfo", this.getRegInfo(info.getRegId()));
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "analysis/land/view";
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
    	
		Page page = landAnalysisService.getListByCondition(searchParams, 1, 65500, "T.CODE ASC");
		Table<String, String, String> regionTable = (Table<String, String, String>)cache.get(TRegionCache.ID_FULLNAME);
		Map<String, String> regionMap = regionTable.column(TRegionCache.ID_FULLNAME);
		List<LandAnalysis> list = page.getResult();
		for (LandAnalysis info : list) {
			info.setRegionId(regionMap.get(info.getRegionId()));
			if (info.getSamplingTime() != null) {
				info.setSamplingTimeStr(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", info.getSamplingTime()));
			}
		}
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("landList", list);
		
		String fileName = "土壤采样分析数据" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xls";
		
		InputStream is = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		
			is = this.getTemplateFile("xls" + File.separator + "analysis_land_export.xls");
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
    
    
    @RequestMapping(value = "doImport/{menuid}/", method = RequestMethod.POST)
	public String doImport(@PathVariable String menuid
			, @RequestParam("file") MultipartFile file
			, RedirectAttributes attrs, HttpServletRequest request, HttpServletResponse response) {
		
		File uploadFile = null;
        List<KeyValue> errorMessages = new ArrayList<KeyValue>();
        List<String> errors = ExcelImportValidator.errorMessages;
        //清除历史记录
        landAnalysisXlsImportService.getObjectList().clear();
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
                return "redirect:/analysis/land/list/"+menuid+"/";
            }
            errorMessages.clear();
            try{
                List<String> files = new ArrayList<String>();
                files.add(uploadFile.getAbsolutePath());
                //获取错误消息
                List<String> s = landAnalysisXlsImportService.importValite(files);
                
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
                    List<Object> list = landAnalysisXlsImportService.getObjectList();
                    LandImport imp = null;
                    String message = "导入成功。";
                    for (Object obj : list) {
                    	imp = (LandImport)obj;
                    	//数据有效性，编号
                    	if (StringUtils.isEmpty(imp.getRegNo())) {
                    		continue;
                    	}
                    	this.saveInfo(imp);
                    }
                    attrs.addFlashAttribute("message", message);
                }
            }catch (Exception e) {
                errorMessages.add(new KeyValue("msg","数据处理异常"));
                attrs.addFlashAttribute("message", "导入失败，数据处理异常。");
            }
        } 
        return "redirect:/analysis/land/list/"+menuid+"/";
	}
    
    /**
     * 保存信息
     * @param imp
     */
    private void saveInfo(LandImport imp) {
    	//当前时间
    	Date date = new Date();
    	
    	//构造采样登记信息
    	LandReg reg = new LandReg();
    	String regId = UuidGenerateUtil.getUUIDLong();
    	reg.setId(regId);
    	reg.setCode(imp.getRegNo());
    	try {
    		if (imp.getLongitude() != null) {
        		reg.setLongitude(new BigDecimal(imp.getLongitude()));
        	}
    	}catch(Exception e) {}
    	try {
    		if (imp.getLatitude() != null) {
        		reg.setLatitude(new BigDecimal(imp.getLatitude()));
        	}
    	}catch(Exception e) {}
    	
    	reg.setRegionId(imp.getRegionId());
    	reg.setCreateId(getCurrentUserId());
    	reg.setCreateTime(date);
    	//原始录入
    	reg.setSamplingSource("1");
    	reg.setSamplingTime(date);
    	//已审查
    	reg.setCheckStatus(1);
    	reg.setCheckTime(date);
    	//审查人为录入用户
    	reg.setCheckUser(getCurrentUserId());
    	//保存采样登记
//    	if (baseService.checkCode("T_SAMPLING_LAND_REG", "CODE", reg.getCode())) {
    		landRegService.save(reg);
//    	}
    	
    	//构造采样分析信息
    	LandAnalysis la = new LandAnalysis();
    	la.setId(UuidGenerateUtil.getUUIDLong());
    	la.setCode(imp.getRegNo()+"A");
    	la.setRegId(regId);
    	la.setDept(imp.getDept());
    	la.setPh(imp.getPh());
    	la.setCadmium(imp.getCadmium());
    	la.setAvailableCadmium(imp.getAvailableCadmium());
    	la.setCreateId(getCurrentUserId());
    	la.setCreateTime(date);
    	//保存采样分析
//    	if (baseService.checkCode("T_ANALYSIS_LAND", "CODE", la.getCode())) {
    		landAnalysisService.save(la);
//    	}
    }
    

}