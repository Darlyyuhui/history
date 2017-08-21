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
	
	/////////////////////////////////////////根据地图切片位置xyz加载对应的切片(如果没有则下载并保存)////////////////////////////////////////////////////////
	String outputdir = "d:/tiandituTitle/"+z+"/"+y;
	String vecFilePath = "d:/tiandituTitle/vec.png";
	String cvaFilePath = "d:/tiandituTitle/cva.png";
	
	int ms = (int)(Math.random()*10);//随机数
	String imgUrlVec = "http://t"+ms%8+".tianditu.cn/DataServer?T=vec_w&X="+x+"&Y="+y+"&L="+z;
	String imgUrlCva = "http://t"+ms%8+".tianditu.cn/DataServer?T=cva_w&X="+x+"&Y="+y+"&L="+z;
	String redirectUrl = "http://localhost:8080/tiandituTitle/"+z+"/"+y+"/"+x+".png";
	
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
		
		URL url = new URL(imgUrlVec);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		InputStream inStream = conn.getInputStream();
		OutputStream streamOut = new FileOutputStream(vecFilePath);
		BufferedImage image = ImageIO.read(inStream);
		ImageIO.write(image, "png", streamOut);
		
		URL url2 = new URL(imgUrlCva);
		HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
		InputStream inStream2 = conn2.getInputStream();
		OutputStream streamOut2 = new FileOutputStream(cvaFilePath);
		BufferedImage image2 = ImageIO.read(inStream2);
		ImageIO.write(image2, "png", streamOut2);
		
		// 合并
		BufferedImage[] buffImages = new BufferedImage[2];
		buffImages[0] = ImageIO.read(new File(vecFilePath));
		buffImages[1] = ImageIO.read(new File(cvaFilePath));
		int type = buffImages[0].getType();
		
		BufferedImage finalImg = new BufferedImage(tileSize, tileSize, type);
		finalImg.createGraphics().drawImage(buffImages[0], 0, 0, null);
		finalImg.createGraphics().drawImage(buffImages[1], 0, 0, null);
		
		// 输出合成后的图像
		ImageIO.write(finalImg, "png", file);
	}
	
	response.sendRedirect(redirectUrl);
%>
