package com.xiangxun.epms.mobile.business.domain;



public class Geometry {
	/**
	 * 几何类型
	 */
	private String type;

	/**
	 * 几何点字符串，用逗号分隔
	 */
	private String points;

	public Geometry(){
		
	}

	public Geometry(GeometryType type,String points){
		this.type = type.name().toLowerCase();
		this.points = points;
	}
	
	/**
	 * 通过三维double数组，创建线或者面，多线多面之间用|隔开
	 * @param type
	 * @param path
	 */
	public Geometry(GeometryType type, double[][][] path){
		this.type = type.name().toLowerCase();
		StringBuffer sb = new StringBuffer("");
		for(int i=0,len=path.length; i<len; i++) {
			if(i != 0)sb.append("|");
			double[][] points = path[i];
			for(int j=0,jl=points.length; j<jl; j++) {
				double[] point = points[j];
				if(null != point && point.length == 2) {
					if(j != 0)sb.append(",");
					sb.append(point[0]+","+point[1]);
				}
			}
		}
		this.points = sb.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String toString(){
		return "{\"type\":\""+this.type+"\",\"points\":\""+this.points+"\"}";
	}
	
}
