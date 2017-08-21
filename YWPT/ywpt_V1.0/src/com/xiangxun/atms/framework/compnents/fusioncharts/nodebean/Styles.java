package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

public class Styles {
	private Definition definition;
	private Application application;
	
	
	/**
	 * @return the definition
	 */
	public Definition getDefinition() {
		return definition;
	}


	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(Definition definition) {
		this.definition = definition;
	}


	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}


	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<styles>");
		if(getDefinition()!=null){
			sb.append(getDefinition().toString());
		}
		if(getApplication()!=null){
			sb.append(getApplication().toString());
		}
		sb.append("</styles>");
		return sb.toString();
	}
	

}
