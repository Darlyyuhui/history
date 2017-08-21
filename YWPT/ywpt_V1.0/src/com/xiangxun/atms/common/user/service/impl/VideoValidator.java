package com.xiangxun.atms.common.user.service.impl;



/**
 * <p>视频监控模块验证器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class VideoValidator extends AbstractModuleValidator {

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractModuleValidator#getType()
	 */
	@Override
	public String getType() {
		return "video";
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.ModuleValidator#isEnable()
	 */
	@Override
	public boolean enable() {
		return videoEnable();
	}

	
}
