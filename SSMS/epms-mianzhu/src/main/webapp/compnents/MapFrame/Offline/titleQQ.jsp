<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@page import="java.net.*,java.io.*"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%
	double[] resolutions = {156543.03390625, 78271.516953125, 39135.7584765625, 19567.87923828125, 9783.939619140625, 4891.9698095703125, 2445.9849047851562, 1222.9924523925781, 611.4962261962891, 305.74811309814453, 152.87405654907226, 76.43702827453613, 38.218514137268066, 19.109257068634033, 9.554628534317017, 4.777314267158508, 2.388657133579254, 1.194328566789627, 0.5971642833948135, 0.29858214169740677};
	double[] maxExtent = {-20037508.34, -20037508.34, 20037508.34, 20037508.34};
	int tileSize = 256;
	
	String extent=request.getParameter("BBOX");
	String zoom=request.getParameter("ZOOM");
	int z = Integer.parseInt(zoom);
	String[] extentStr=extent.split(",");
	double boundsLeft=Double.parseDouble(extentStr[0]);
	double boundsTop=Double.parseDouble(extentStr[3]);
	
	double res = resolutions[z];
	long x = Math.round((boundsLeft - maxExtent[1]) / (res * tileSize));
	long y = Math.round((maxExtent[3] - boundsTop) / (res * tileSize));
	
	// 以下为qq地图需要的部分
	long[] scopes = {0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 7, 0, 7, 0, 15, 0, 15, 0, 31, 0, 31, 0, 63, 4, 59, 0, 127, 12, 115, 0, 225, 28, 227, 356, 455, 150, 259, 720, 899, 320, 469, 1440, 1799, 650, 929, 2880, 3589, 1200, 2069, 5760, 7179, 2550, 3709, 11520, 14349, 5100, 7999, 23060, 28689, 10710, 15429, 46120, 57369, 20290, 29849, 89990, 124729, 41430, 60689, 184228, 229827, 84169, 128886};
	int f = z*4;
	long i = scopes[f++];
	long j = scopes[f++];
	long l = scopes[f++];
	
	long scope = scopes[f];
	
	if(x >= i && x <= j && y >= l && y <= scope) {
		y = (int)Math.pow(2, z) - 1 - y;
	}
	
	/////////////////////////////////////////根据地图切片位置xyz加载对应的切片(如果没有则下载并保存)////////////////////////////////////////////////////////
	String outputdir = "d:/qqTitle/"+z+"/"+y;
	int ms = (int)(Math.random()*10);//随机数
	String imgUrl = "http://rt"+ms%4+".map.gtimg.com/realtimerender?z="+z+"&x="+x+"&y="+y+"&type=vector&style=0&v=1.1.1";
	String redirectUrl = "http://localhost:8080/qqTitle/"+z+"/"+y+"/"+x+".png";
	
	// 查看本地是否有切片，如果没有则下载并保存
	String filePath = outputdir+"/"+x+".png";
	File file = new File(filePath);
	// 判断是否存在，如果不存在则下载并保存
	if(!file.exists()) {
		// 查看图片目录是否存在
		File checkFile = new File(outputdir);
		if(!checkFile.exists()) {
			checkFile.mkdirs();
		}
		URL url = new URL(imgUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		InputStream inStream = conn.getInputStream();
		OutputStream streamOut = new FileOutputStream(filePath);
		BufferedImage image = ImageIO.read(inStream);
		ImageIO.write(image, "png", streamOut);
	}
	
	response.sendRedirect(redirectUrl);
%>
