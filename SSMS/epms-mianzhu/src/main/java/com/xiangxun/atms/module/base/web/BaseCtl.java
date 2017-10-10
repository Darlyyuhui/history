package com.xiangxun.atms.module.base.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.validator.ResponseEntity;

public abstract class BaseCtl<T, S> extends ExportBaseCtl {

	protected abstract BaseService<T, S> getBaseService();
	
	protected Logging logger = new Logging(this.getClass());
	
	@Resource
	protected com.xiangxun.atms.module.base.service.BaseService baseService;

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
	
	@ModelAttribute("info")
	public T getInfo(@RequestParam(value = "id", required = false) String id) {
		if (StringUtils.isNotEmpty(id)) {
			return getBaseService().getById(id);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkCode/{code}/", method = RequestMethod.POST)
	public ResponseEntity checkCode(@PathVariable String code, String tName, String cName) {
		ResponseEntity re = new ResponseEntity();
		if (StringUtils.isEmpty(tName) || StringUtils.isEmpty(cName) || StringUtils.isEmpty(code)) {
			re.setResult("exception");
			re.setMessage("缺失查询参数");
		} else {
			try {
				if (baseService.checkCode(tName, cName, code)) {
					re.setResult("ok");
				} else {
					re.setResult("error");
					re.setMessage("编号重复");
				}
			}catch(Exception e) {
				logger.error("校验编号重复出错，" +e.getMessage());
				re.setResult("exception");
				re.setMessage("请检查查询参数");
			}
		}
		return re;
	}
	
}
