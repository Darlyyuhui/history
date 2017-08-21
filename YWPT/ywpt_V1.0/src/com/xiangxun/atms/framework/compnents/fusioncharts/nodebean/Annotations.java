package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

public class Annotations {
	private AnnotationGroup annotationGroup;

	public AnnotationGroup getAnnotationGroup() {
		return annotationGroup;
	}

	public void setAnnotationGroup(AnnotationGroup annotationGroup) {
		this.annotationGroup = annotationGroup;
	}

	@Override
	public String toString() {
		if (annotationGroup == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<annotations>");
		sb.append(annotationGroup.toString());
		sb.append("</annotations>");
		return sb.toString();
	}

}
