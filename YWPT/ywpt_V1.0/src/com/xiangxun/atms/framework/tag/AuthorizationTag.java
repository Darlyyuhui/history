package com.xiangxun.atms.framework.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xiangxun.atms.common.user.service.MenuCacheService;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;

/**
 * 用于对内部标签的鉴权，专用于对按钮的鉴权
 * 
 * @author zhouhaij
 */
public class AuthorizationTag extends BodyTagSupport {

	private Logging logger = new Logging(AuthorizationTag.class);

	private String text;

	private String menuid;

	private static final long serialVersionUID = 7683288029657419544L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		try {
			ServletContext servletContext = pageContext.getSession().getServletContext();
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			JspWriter out = bodyContent.getEnclosingWriter();
			if (!authenticate(applicationContext)) {
				out.write("");
			} else {
				out.write(bodyContent.getString());
			}
			bodyContent.clear();
			bodyContent.close();
			return EVAL_PAGE;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 验证当前用户是否有操作此按钮的权限
	 * 
	 * @param applicationContext
	 *            Spring上行文
	 * @return 有权限返回true,否则返回false
	 */
	@SuppressWarnings("unchecked")
	private boolean authenticate(ApplicationContext applicationContext) {
		// 获取登陆用户
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户未登录");
		}
		MenuCacheService menuCacheService = applicationContext.getBean(MenuCacheService.class);
		logger.debug("userid:" + userInfo.getId());
		logger.debug("menuid:" + menuid);
		Object object = menuCacheService.getMenuCache(userInfo.getId().toString(), menuid);
		if (object != null) {
			List<String> codes = (List<String>) object;
			String str = "btn_"+ ZhongWenToPinYin.getPinYin(text).trim().toUpperCase();
			logger.debug("btn text:" + str);
			if (codes.contains(str)) {
				return true;
			}
		}
		logger.debug("当前用户:{0}无此按钮{1}的权限。",new String[] { userInfo.getAccount(), text });
		return false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
