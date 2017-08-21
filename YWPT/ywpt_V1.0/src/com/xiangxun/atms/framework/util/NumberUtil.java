package com.xiangxun.atms.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数学计算帮助类
 * @author yantao
 * @version 1.0
 * Oct 18, 2013
 */
public class NumberUtil 
{
	private static final int DEF_DIV_SCALE = 10;
	/**
	 * 求百分比 默认2位小数
	 * @param y
	 * @param z
	 * @return
	 */
	public static String myPercent(Long y,long z){
		   String baifenbi="";//接受百分比的值
		   double baiy=y*1.0;
		   double baiz=z*1.0;
		   double fen=baiy/baiz;
		   //NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法
		   //nf.setMinimumFractionDigits( 2 );        保留到小数点后几位
		   //baifenbi=nf.format(fen);   
		   DecimalFormat df1 = new DecimalFormat("#0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
		   baifenbi= df1.format(fen);  
		   return baifenbi;
	}
	
	//获得对应的百分比
	public static String myPercent(int y,int z){
		long yvalue = Long.parseLong(String.valueOf(y));
		long zvalue = Long.parseLong(String.valueOf(z));
		return myPercent(yvalue,zvalue);
	}
	
	/**
	 * 求除数
	 * @param y
	 * @param z
	 * @return
	 */
	public static double myDivision(Long y,long z){
		   double fen=0;
		   double baiy=y*1.0;
		   double baiz=z*1.0;
		   fen=baiy/baiz;
		   return fen;
	}
	public static double myDivision(int y,int z){
		long yvalue = Long.parseLong(String.valueOf(y));
		long zvalue = Long.parseLong(String.valueOf(z));
		return myDivision(yvalue,zvalue);
	}
	
	public static long average(Long y,long z){
		long average = 0;
		average = y/z;
		return average;
	}

	/**
	 * 求两数相除的百分号前数值（如80%-前80数字）
	 * @param y
	 * @param z
	 * @return
	 */
	public static long percentpack(Long y,long z){
		   double fen=0;
		   double baiy=y*1.0;
		   double baiz=z*1.0;
		   fen=baiy/baiz*100;
		   return Double.valueOf(fen).longValue();
		}
	
	  /**
     * 两个Double数相加
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double add(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.add(b2).doubleValue();
    }
    
    /**
     * 两个Double数相减
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double sub(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }
    
    /**
     * 两个Double数相乘
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mul(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * 两个Double数相除
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double div(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 两个Double数相除，并保留scale位小数
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1,Double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 指定Double型的精确位数
     * @param v1
     * @param scale
     * @return
     */
    public static Double toscale(Double v1,int scale)
    {
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	return b1.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    
}
