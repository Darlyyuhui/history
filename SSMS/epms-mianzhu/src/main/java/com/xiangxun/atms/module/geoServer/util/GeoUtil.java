package com.xiangxun.atms.module.geoServer.util;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.xiangxun.atms.module.geoServer.domain.Geometry;
import com.xiangxun.atms.module.geoServer.domain.GeometryType;
import com.xiangxun.atms.module.geoServer.domain.Graphic;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;

import net.sf.json.JSONObject;

public class GeoUtil {

	/**
	 * 构建从前端Geometry到WKT格式
	 * 
	 * @param geo
	 *            Geometry 传入几何体
	 * @return geoStr 返回WKT格式几何体
	 */
	public final static String convertGeoClassToWKT(Geometry geo) {
		String geoType = geo.getType();
		String points = geo.getPoints();
		String geoStr = "";

		// 构建多点，多线，多面
		if (geoType.toLowerCase().startsWith("multi")) {
			String[] geoArr = points.split("[|]");
			if (GeometryType.MULTIPOINT.name().toLowerCase().equals(geoType)) {
				geoStr += "MULTIPOINT(";
				for (int i = 0, len = geoArr.length; i < len; i++) {
					String[] pointArr = geoArr[i].split(",");
					if (i != 0) {
						geoStr += ",";
					}
					geoStr += pointArr[0] + " " + pointArr[1];
				}
				geoStr += ")";
				return geoStr;
			}
			String wktType = "";
			String brackets = "";
			String brackets2 = "";
			if (GeometryType.MULTIPOLYLINE.name().toLowerCase().equals(geoType)) {
				wktType = "MULTILINESTRING";
				brackets = "((";
				brackets2 = "))";
			}
			if (GeometryType.MULTIPOLYGON.name().toLowerCase().equals(geoType)) {
				wktType = "MULTIPOLYGON";
				brackets = "(((";
				brackets2 = ")))";
			}
			geoStr += wktType + brackets;
			for (int i = 0, len = geoArr.length; i < len; i++) {
				String[] pointsArr = geoArr[i].split(",");
				for (int j = 0, pointsLen = pointsArr.length; j < pointsLen; j += 2) {
					if (j != 0) {
						geoStr += ",";
					}
					geoStr += pointsArr[j] + " " + pointsArr[j + 1];
				}
				if (i < len - 1) {
					geoStr += "),(";
				}
			}
			geoStr += brackets2;
			return geoStr;
		} else { // 构建单点，单线，单面
			String[] pointsArr = points.split(",");
			if (GeometryType.POINT.name().toLowerCase().equals(geoType)) {
				geoStr += "POINT(" + pointsArr[0] + " " + pointsArr[1] + ")";
				return geoStr;
			}
			int pointsLen = pointsArr.length;
			String wktType = "";
			String brackets = "";
			String brackets2 = "";
			if (GeometryType.POLYLINE.name().toLowerCase().equals(geoType)) {
				wktType = "LINESTRING";
				brackets = "(";
				brackets2 = ")";
			}
			if (GeometryType.POLYGON.name().toLowerCase().equals(geoType)) {
				wktType = "POLYGON";
				brackets = "((";
				brackets2 = "))";
			}
			geoStr += wktType + brackets;
			for (int i = 0; i < pointsLen; i += 2) {
				if (i != 0) {
					geoStr += ",";
				}
				geoStr += pointsArr[i] + " " + pointsArr[i + 1];
			}
			geoStr += brackets2;
		}
		return geoStr;
	}

	/**
	 * 将WKT格式转换为前端Geometry格式
	 * 
	 * @param wkt
	 * @return
	 */
	public final static Geometry convertWKTToGeoClass(String wkt) {
		if ("".equals(wkt)) {
			return null;
		}
		// 几何体数组的wkt字符串
		if (wkt.startsWith("GEOMETRYCOLLECTION")) {
			return convertCollectionWKTToGeoClass(wkt);
		}
		Geometry geoClass = new Geometry();
		String geoTemp = wkt;
		Pattern typeReg = Pattern.compile("[POINT|LINESTRING|POLYGON|MULTILINESTRING|MULTIPOINT|MULTIPOLYGON]");
		Pattern multiReg = Pattern.compile("\\)\\s*,\\s*\\(");
		if (wkt.startsWith("POINT")) {
			geoClass.setType(GeometryType.POINT.name().toLowerCase());
		}
		else if (wkt.startsWith("LINESTRING")) {
			geoClass.setType(GeometryType.POLYLINE.name().toLowerCase());
		}
		else if (wkt.startsWith("POLYGON")) {
			geoClass.setType(GeometryType.POLYGON.name().toLowerCase());
		}
		else if (wkt.startsWith("MULTILINESTRING")) {
			geoClass.setType(GeometryType.MULTIPOLYLINE.name().toLowerCase());
		}
		else if (wkt.startsWith("MULTIPOINT")) {
			geoClass.setType(GeometryType.MULTIPOINT.name().toLowerCase());
		}
		else if (wkt.startsWith("MULTIPOLYGON")) {
			geoClass.setType(GeometryType.MULTIPOLYGON.name().toLowerCase());
		}
		else {
			// 传递的可能非wkt字符串
			try {
				Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(wkt), Geometry.class);
				return geo;
			} catch (RuntimeException e) {
			}
			return null;
		}
		geoTemp = typeReg.matcher(geoTemp).replaceAll("");
		geoTemp = multiReg.matcher(geoTemp).replaceAll("|");
		Pattern pattern1 = Pattern.compile("\\s+");
		Pattern pattern2 = Pattern.compile(",");
		geoTemp = pattern2.matcher(geoTemp.trim()).replaceAll(" ");
		geoTemp = pattern1.matcher(geoTemp).replaceAll(",");
		geoTemp = geoTemp.replace("(", "").replace(")", "");
		geoClass.setPoints(geoTemp);
		return geoClass;
	}
	public final static Geometry convertCollectionWKTToGeoClass(String wkt) {
		if ("".equals(wkt)) {
			return null;
		}
		String geoTemp = wkt;
		Geometry geoClass = new Geometry();
		Pattern typeReg = Pattern.compile("[GEOMETRYCOLLECTION|POINT|LINESTRING|POLYGON]");
		Pattern multiReg = Pattern.compile("\\)\\s*,\\s*\\(");
		if (wkt.indexOf("LINESTRING") != -1) {
			geoClass.setType(GeometryType.MULTIPOLYLINE.name().toLowerCase());
		}
		else if (wkt.indexOf("POINT") != -1) {
			geoClass.setType(GeometryType.MULTIPOINT.name().toLowerCase());
		}
		else if (wkt.indexOf("POLYGON") != -1) {
			geoClass.setType(GeometryType.MULTIPOLYGON.name().toLowerCase());
		}
		geoTemp = typeReg.matcher(geoTemp).replaceAll("");
		geoTemp = multiReg.matcher(geoTemp).replaceAll("|");
		Pattern pattern1 = Pattern.compile("\\s+");
		Pattern pattern2 = Pattern.compile(",");
		geoTemp = pattern2.matcher(geoTemp.trim()).replaceAll(" ");
		geoTemp = pattern1.matcher(geoTemp).replaceAll(",");
		geoTemp = geoTemp.replace("(", "").replace(")", "");
		geoClass.setPoints(geoTemp);
		return geoClass;
	}

	/**
	 * 将LayerBean转换为Graphic字符串
	 * 
	 * @param layerbean
	 * @return
	 */
	public final static Graphic convertFromLayerBeanToGraphic(LayerBean layerbean) {
		Graphic graphic = new Graphic();
		HashMap<String, String> attr = new HashMap<String, String>();
		attr.put("ID", String.valueOf(layerbean.getId()));
		attr.put("NAME", layerbean.getName());
		attr.put("CODE", layerbean.getCode());
		attr.put("code", layerbean.getCode());
		attr.put("TYPE", layerbean.getType());
		graphic.setAttributes(attr);
		graphic.setGeo(convertWKTToGeoClass(layerbean.getGeometry()));
		return graphic;
	}

	/**
	 * 新对几何对象的走向进行重定向，目前仅支持多线MULTIPOLYLINE
	 * 
	 * @param geometry
	 *            Geometry 传入几何对象
	 * @return
	 */
	public final static Geometry reDirection(Geometry geometry) {
		String points = geometry.getPoints();
		String type = geometry.getType();
		if (!GeometryType.MULTIPOLYLINE.name().toLowerCase().equals(type)) {
			return geometry;
		}
		String[] lineArr = points.split("\\|");
		StringBuffer newLinesStr = new StringBuffer("");
		String lastLinesStr = "";
		for (int i = 0, len = lineArr.length; i < len - 1; i++) {
			if (i == 0) { // 判断第一条线的方向是否正确
				String[] startLine = lineArr[0].split(",");
				String[] startNextLine = lineArr[1].split(",");
				String[] endLine = lineArr[len-1].split(",");
				String[] endPreLine = lineArr[len-2].split(",");
				if(isPointEqualsStartOrEndLine(startLine[0], startLine[1], startNextLine)) {
					// 开始起点在下一条线上，说明起始线方向反了，进行反转
					for(int j=startLine.length-1; j>0; j-=2) {
						newLinesStr.append(startLine[j-1]+",");
						newLinesStr.append(startLine[j]);
						if(j != 1)newLinesStr.append(",");
					}
				}
				else {
					newLinesStr.append(lineArr[0]);
				}
				newLinesStr.append("|");
				if(!isPointEqualsStartOrEndLine(endLine[0], endLine[1], endPreLine)) {
					// 结束起点在上一条线上，说明结束线方向反了，进行反转
					for(int j=endLine.length-1; j>0; j-=2) {
						lastLinesStr += endLine[j-1] + "," + endLine[j];
						if(j != 1)lastLinesStr += ",";
					}
				}
				else {
					lastLinesStr = lineArr[len-1];
				}
				continue;
			}
			String[] nowLinePointsArr = lineArr[i].split(",");
			String[] preLinePointsArr = lineArr[i - 1].split(",");
			if(!isPointEqualsStartOrEndLine(nowLinePointsArr[0], nowLinePointsArr[1], preLinePointsArr)) {
				// 当前起点没在上一条线上，说明当前线方向反了，进行反转
				for(int j=nowLinePointsArr.length-1; j>0; j-=2) {
					newLinesStr.append(nowLinePointsArr[j-1]+",");
					newLinesStr.append(nowLinePointsArr[j]);
					if(j != 1)newLinesStr.append(",");
				}
			}
			else {
				newLinesStr.append(lineArr[i]);
			}
			newLinesStr.append("|");
		}
		
		newLinesStr.append(lastLinesStr);
		geometry.setPoints(newLinesStr.toString());
		return geometry;
	}
	
	/**
	 * 判断点是否和起始点相等
	 * @param point
	 * @param line
	 * @return
	 */
	private static boolean isPointEqualsStartOrEndLine(String x, String y, String[] line) {
		int len = line.length;
		String startX = line[0];
		String startY = line[1];
		String endX = line[len-2];
		String endY = line[len-1];
		if((x.equals(startX) && y.equals(startY)) || (x.equals(endX) && y.equals(endY)))return true;
		return false;
	}
}