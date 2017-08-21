package com.xiangxun.atms.module.gis.util;

import java.util.HashMap;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.xiangxun.atms.module.gis.domain.Geometry;
import com.xiangxun.atms.module.gis.domain.GeometryType;
import com.xiangxun.atms.module.gis.domain.Graphic;
import com.xiangxun.atms.module.gis.domain.LayerBean;

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

	public static void main(String[] args) {
		StringBuffer codes = new StringBuffer("01,02,03,");
		System.out.println(codes.substring(0, codes.length()-1));
		if(1==1)return;
		
		Pattern typeReg = Pattern.compile("[MULTILINESTRING]");
		Pattern multiReg = Pattern.compile("\\)\\s*,\\s*\\(");
		
		String geoTemp = "MULTILINESTRING ((108.90568806500005 34.25239411000007, 108.90535903800003 34.261010223000085, 108.92106214500006 34.26098014400009), (108.92106214500006 34.26098014400009, 108.94151369900004 34.260940969000046), (108.94151369900004 34.260940969000046, 108.94153456045098 34.260870439778465, 108.94156215156386 34.2608022613235, 108.94159621316338 34.260737074065005, 108.94163642529415 34.260675490335245, 108.94168241022612 34.26061808861695, 108.94173373600286 34.26056540810941, 108.94178992049902 34.26051794366352, 108.94185043594922 34.26047614113347, 108.94191471390553 34.26044039318858, 108.94198215057719 34.26041103562484, 108.9420521125022 34.2603883442106, 108.9421239424978 34.26037253209617, 108.94219696583365 34.26036374781163, 108.94227049656978 34.26036207387157, 108.94234384400005 34.26036752600004), (108.94234384400005 34.26036752600004, 108.9424078843245 34.260355723049784, 108.9424728030257 34.26035062059693, 108.94253790095496 34.26035227359286, 108.94260247703339 34.26036066423546, 108.94266583580225 34.26037570216086, 108.94272729491267 34.26039722541653, 108.94278619247443 34.26042500220556, 108.94284189418413 34.26045873338294, 108.94289380015643 34.26049805567725, 108.94294135138456 34.26054254560299, 108.94298403576069 34.260591724021275, 108.94302139359098 34.26064506130004, 108.94305302254641 34.26070198301793, 108.94307858199568 34.260761876150625, 108.94309779667363 34.26082409567281, 108.94311045964581 34.2608879715049, 108.94311643453703 34.26095281572951, 108.94311565700008 34.26101793000004), (108.94311565700008 34.26101793000004, 108.95842080400007 34.260620601000085, 108.96624059200008 34.260610522000036))";
		geoTemp = typeReg.matcher(geoTemp).replaceAll("");
		System.out.println(geoTemp);
		geoTemp = multiReg.matcher(geoTemp).replaceAll(",");
		System.out.println(geoTemp);
		Pattern pattern1 = Pattern.compile("\\s+");
		Pattern pattern2 = Pattern.compile(",");
		geoTemp = pattern2.matcher(geoTemp.trim()).replaceAll(" ");
		System.out.println(geoTemp);
		geoTemp = pattern1.matcher(geoTemp).replaceAll(",");
		System.out.println(geoTemp);
		geoTemp = geoTemp.replace("(", "").replace(")", "");
		System.out.println(geoTemp);
		
		System.out.println(Math.sqrt(9));
		
		//Geometry geo = convertWKTToGeoClass(geoTemp);
		//System.out.println(geo.getPoints());
	}
	
	private String[] getPoints(String wkt) {
		Pattern typeReg = Pattern.compile("[POINT|LINESTRING|POLYGON|MULTILINESTRING|MULTIPOINT|MULTIPOLYGON]");
		Pattern multiReg = Pattern.compile("\\)\\s*,\\s*\\(");
		wkt = typeReg.matcher(wkt).replaceAll("");
		wkt = multiReg.matcher(wkt).replaceAll(",");
		Pattern pattern1 = Pattern.compile("\\s+");
		Pattern pattern2 = Pattern.compile(",");
		wkt = pattern2.matcher(wkt.trim()).replaceAll(" ");
		wkt = pattern1.matcher(wkt).replaceAll(",");
		wkt = wkt.replace("(", "").replace(")", "");
		return wkt.split(",");
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
		attr.put("ROADID", layerbean.getRoadId());
		attr.put("ROADNAME", layerbean.getRoadName());
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