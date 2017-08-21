package com.xiangxun.atms.framework.compnents.fusioncharts.chartfactory;
/**
 * <p>Description: 这个类是用于构建图表内嵌代码</p>
 */
public class FusionChartsCreator {
	
	
	/**
	 * 生成创建图表的JS+HTML内嵌代码
	 * @param chartSWF 图表的swf文件路径
	 * @param strURL URL形式提供数据
	 * @param strXML XML形式提供数据
	 * @param chartId 显示图表的DIV的Id，区分大小写
	  * @param chartWidth 图表的宽度
	 * @param chartHeight 图表的高度
	 * @param debugMode 是否启用调试模式
	 * @param registerWithJS 是否使用js注册图表
	 * @return
	 */
	public static String createChart(String chartSWF, String strURL,
			String strXML, String chartId, int chartWidth, int chartHeight,
			boolean debugMode, boolean registerWithJS) {
		
		StringBuffer strBuf = new StringBuffer();
		//首先我们创建一个DIV,并指定DIV的id为chartId，注意区分大小写		

		strBuf.append("\t\t<!-- START Script Block for Chart-->\n");
//		strBuf.append("\t\t<script LANGUAGE='Javascript' SRC='/fusioncharts/js/FusionCharts.js'></script>\n");
		strBuf.append("\t\t<div id='" + chartId + "Div' align='center'>\n");
		strBuf.append("\t\t\t\tChart.\n");
		/*
		 * 如果服务器中的SWF加载滞后或用户没有安装Flash Player，上述文字"Chart"将会显示，
		 * 你可以根据需要进行配置。
		 */
		strBuf.append("\t\t</div>\n");

		/*
		 * 开始使用FusionCharts js类库创建图表， 每个图表的javascrpt实例Id名称为chart_"chartId".
		 */

		strBuf.append("\t\t<script type='text/javascript'>\n");
		// 实例化图表
		Boolean registerWithJSBool = new Boolean(registerWithJS);
		Boolean debugModeBool = new Boolean(debugMode);
		int regWithJSInt = boolToNum(registerWithJSBool);
		int debugModeInt = boolToNum(debugModeBool);

		strBuf.append("\t\t\t\tvar chart_" + chartId + " = new FusionCharts('"
				+ chartSWF + "', '" + chartId + "', '" + chartWidth + "', '"
				+ chartHeight + "', '" + debugModeInt + "', '" + regWithJSInt
				+ "');\n");
		// 检测提供的数据是基于dataURL还dataXML。
		if (strXML.equals("")) {
			strBuf.append("\t\t\t\t// Set the dataURL of the chart\n");
			strBuf.append("\t\t\t\tchart_" + chartId + ".setDataURL(\""
					+ strURL + "\");\n");
		} else {
			strBuf
					.append("\t\t\t\t// Provide entire XML data using dataXML method\n");
			strBuf.append("\t\t\t\tchart_" + chartId + ".setDataXML(\""
					+ strXML + "\");\n");
		}
		strBuf.append("\t\t\t\t// Finally, render the chart.\n");
		strBuf.append("\t\t\t\tchart_" + chartId + ".render(\"" + chartId
				+ "Div\");\n");
		strBuf.append("\t\t</script>\n");
		strBuf.append("\t\t<!--END Script Block for Chart-->\n");
		return strBuf.substring(0);
	}
	
	/**
	 * 生成创建图表的Object+HTML内嵌代码
	 * @param chartSWF 图表的swf文件路径
	 * @param strURL URL形式提供数据
	 * @param strXML XML形式提供数据
	 * @param chartId 显示图表的DIV的Id，区分大小写
	 * @param chartWidth 图表的宽度
	 * @param chartHeight 图表的高度
	 * @param debugMode 是否启用调试模式
	 * @return
	 */
	public static String createChartHTML(String chartSWF, String strURL,
			String strXML, String chartId, int chartWidth, int chartHeight,
			boolean debugMode) { 
		/*
		 * 生成的FlashVars字符串基于dataURL或dataXML。
		*/
		String strFlashVars = "";
		Boolean debugModeBool = new Boolean(debugMode);

		if (strXML.equals("")) {
			// DataURL 形式
			strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
					+ chartHeight + "&debugMode=" + boolToNum(debugModeBool)
					+ "&dataURL='" + strURL + "'";
		} else {
			// DataXML 形式
			strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
					+ chartHeight + "&debugMode=" + boolToNum(debugModeBool)
					+ "&dataXML='" + strXML + "'";
		}
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("\t\t<!--START Code Block for Chart-->\n");
		strBuf
				.append("\t\t<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"
						+ chartWidth
						+ "' height='"
						+ chartHeight
						+ "' id='"
						+ chartId + "'>\n");
		strBuf
				.append("\t\t\t\t<param name='allowScriptAccess' value='always' />\n");
		strBuf.append("\t\t\t\t<param name='movie' value='" + chartSWF
				+ "'/>\n");
		strBuf.append("\t\t\t\t<param name='FlashVars' value=\"" + strFlashVars
				+ "\" />\n");
		strBuf.append("\t\t\t\t<param name='quality' value='high' />\n");
		strBuf
				.append("\t\t\t\t<embed src='"
						+ chartSWF
						+ "' FlashVars=\""
						+ strFlashVars
						+ "\" quality='high' width='"
						+ chartWidth
						+ "' height='"
						+ chartHeight
						+ "' name='"
						+ chartId
						+ "' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />\n");
		strBuf.append("\t\t</object>\n");
		strBuf.append("\t\t<!--END Code Block for Chart-->\n");
		return strBuf.substring(0);
	}
	/**
	 * Boolean类型转换为int类型，True为1，false为0
	 * @param bool
	 * @return
	 */
	public static int boolToNum(Boolean bool) {
		int num = 0;
		if (bool.booleanValue()) {
			num = 1;
		}
		return num;
	}

}
