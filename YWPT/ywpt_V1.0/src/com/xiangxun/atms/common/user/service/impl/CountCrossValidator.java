package com.xiangxun.atms.common.user.service.impl;


/**
 * <p>流量模块验证器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class CountCrossValidator extends AbstractModuleValidator {

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractModuleValidator#getType()
	 */
	@Override
	public String getType() {
		return "count";
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractModuleValidator#enable()
	 */
	@Override
	protected boolean enable() {
		return countAcrossEnable();
	}
	
	
}
