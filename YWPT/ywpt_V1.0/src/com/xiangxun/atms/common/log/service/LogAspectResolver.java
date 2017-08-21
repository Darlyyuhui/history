package com.xiangxun.atms.common.log.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;

/***
 * 日志注解处理器
 * @author zhouhaij
 * @Apr 25, 2013 8:52:03 AM
 */
@Aspect
public class LogAspectResolver {
	
	private static GsonBuilder builder = new GsonBuilder();   
	
	private Logging logger = new Logging(LogAspectResolver.class);
	
	Gson gson = null;
	
	public LogAspectResolver(){
		gson = builder.setDateFormat("yyyy-mm-dd").create();   
	}
	
	@Resource
    OperateLogService operateLogService;  
    
	@Resource
	ResourceService resourceService;
	
    /***
     * 标注该方法体为后置通知，当目标方法执行成功后执行该方法体  
     * @param jp
     * @param as
     */
    @AfterReturning("within(com.xiangxun.atms..*) && @annotation(la)")  
    public void addLogSuccess(JoinPoint jp, LogAspect la){  
    	//获取目标方法体参数  
        Object[] parames = jp.getArgs();
        //获取目标类名  
        String className = jp.getTarget().getClass().getSimpleName();
        //获取目标方法签名 
        String signature = jp.getSignature().toString(); 
        //获取具体的方法名
        String methodName = signature.substring(signature.lastIndexOf(".")+1, signature.indexOf("("));  
    	
        logger.info("正在调用的类名："+className);
        logger.info("方法签名:"+methodName);
        
        User user = SpringSecurityUtils.getCurrentUser();
        String ip = SpringSecurityUtils.getCurrentUserIp();
        
        OperationLog log = new OperationLog();
        log.setId(UuidGenerateUtil.getUUIDLong());
        log.setClassName(className);
        log.setDes(la.desc());
        log.setIsSuccess("1");
        log.setMethodName(methodName);
        
        log.setOperateTime(new Date());
        log.setOperateType(parseOperateType(methodName));
        log.setOperator(user.getUsername());
        log.setIp(ip);
        
        JSONArray sb = parseParams(parames, log);
        
        log.setContent("");
        log.setArgs(sb.toString());
        operateLogService.save(log);
    }



   /***
    * 标注该方法体为异常通知，当目标方法出现异常时，执行该方法体  
    * @param jp
    * @param as
    * @param ex
    */
    @AfterThrowing(pointcut="within(com.xiangxun.atms..*) && @annotation(la)", throwing="ex")  
    public void addLog(JoinPoint jp, LogAspect la,Exception ex){ 
    	//获取目标方法体参数  
        Object[] parames = jp.getArgs();
        //获取目标类名  
        String className = jp.getTarget().getClass().getSimpleName();
        //获取目标方法签名 
        String signature = jp.getSignature().toString(); 
        //获取具体的方法名
        String methodName = signature.substring(signature.lastIndexOf(".")+1, signature.indexOf("("));  
    	
        logger.info("正在调用的类名："+className);
        logger.info("方法签名:"+methodName);
        logger.info("参数:"+parames);
        
        
        User user = SpringSecurityUtils.getCurrentUser();
        String ip = SpringSecurityUtils.getCurrentUserIp();
        
        OperationLog log = new OperationLog();
        log.setId(UuidGenerateUtil.getUUIDLong());
        log.setClassName(className);
        log.setDes(la.desc());
        log.setIsSuccess("0");
        log.setMethodName(methodName);
        
        log.setOperateTime(new Date());
        log.setOperateType(parseOperateType(methodName));
        log.setOperator(user.getUsername());
        log.setIp(ip);
        
        JSONArray sb = parseParams(parames, log);
        log.setArgs(sb.toString());
        
        Writer wr = new StringWriter(0);
        PrintWriter writer = new PrintWriter(wr,true);
        ex.printStackTrace(writer);
        log.setContent(wr.toString());
        
        operateLogService.save(log);
    }
    
    

	private JSONArray parseParams(Object[] parames, OperationLog log) {
		JSONArray sb = new JSONArray();
        for (Object object : parames) {
        	if(object == null){
        		continue;
        	}
        	
        	if (object instanceof RedirectAttributes || object instanceof ServletRequest || object instanceof ServletResponse || object instanceof Model) {
        		continue;
			}
        	
        	if(object instanceof Collection){
        		sb.add(JSONArray.fromObject(object));
        	}else{
        		//判断是不是系统生成的uuid,如果是则查找模块名称
        		if(object.toString().length()==30 || object.toString().length()==32){
        			SystemResource res = resourceService.getById(object.toString());
        			if(res!=null){
        				log.setModuleName(res.getName());
        			}else{
        				//不是menuid,说明是参数
        				JSONObject obj = new JSONObject();
        				obj.put("str", object.toString());
        				sb.add(obj);
        			}
        		}
        		else{
        			if(object instanceof String){
        				JSONObject obj = new JSONObject();
        				obj.put("str", object.toString());
        				sb.add(obj);
        			}else{
        				sb.add(JSONObject.fromObject(object));
        			}
        		}
        	}
		}
		return sb;
	}

    private String parseOperateType(String methodName){
    	if(methodName.toLowerCase().contains("add"))  return "a";
    	if(methodName.toLowerCase().contains("update"))  return "u";
    	if(methodName.toLowerCase().contains("del"))  return "d";
    	return "o";
    }
    

}
