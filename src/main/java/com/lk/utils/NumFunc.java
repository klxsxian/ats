/*
 * @(#)NumFunc.java 1.0 2006-8-7
 * Copyright 2006 VandaGroup, Inc. All rights reserved.
 */
package com.lk.utils;

import java.math.BigDecimal;

/**
 * 数字计算工具类. <br>
 * 
 * @author eagle<br>
 * @version Version 1.00<br>
 */
public class NumFunc {
	/**
	 * 乘法,默认二位小数点
	 * @param m1 乘数1
	 * @param m2 乘数2
	 * @return m1*m2结果
	 */
	public static BigDecimal multiply(BigDecimal m1,BigDecimal m2){
		return multiply(m1,m2,2);
	}
	
	/**
	 * 乘法,指定小数点
	 * @param m1 乘数1
	 * @param m2 乘数2
	 * @param scale 小数点位数
	 * @return m1*m2结果
	 */
	public static BigDecimal multiply(BigDecimal m1,BigDecimal m2,int scale){
		return m1.multiply(m2).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 除法,默认二位小数点
	 * @param m1 除数
	 * @param m2 被除数
	 * @return m1/m2结果
	 */
	public static BigDecimal divide(BigDecimal m1,BigDecimal m2){
		return divide(m1,m2,2);
	}
	
	/**
	 * 除法,指定小数点
	 * @param m1 除数
	 * @param m2 被除数
	 * @param scale 小数点位数
	 * @return m1/m2结果
	 */
	public static BigDecimal divide(BigDecimal m1,BigDecimal m2,int scale){
		return m1.divide(m2,scale,BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal divide(BigDecimal m1,BigDecimal m2,int scale,int round){
		return m1.divide(m2,scale,round);
	}
	
	/**
	 * 减法,默认二位小数点
	 * @param m1 被减数
	 * @param m2 减数
	 * @return m1-m2结果
	 */
	public static BigDecimal subtract(BigDecimal m1,BigDecimal m2){
		return subtract(m1,m2,2);
	}
	
	/**
	 * 减法,指定小数点
	 * @param m1 被减数
	 * @param m2减数2
	 * @param scale 小数点位数
	 * @return m1-m2结果
	 */
	public static BigDecimal subtract(BigDecimal m1,BigDecimal m2,int scale){
		return m1.subtract(m2).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 加法,默认二位小数点
	 * @param m1 加数1
	 * @param m2 加数2
	 * @return m1+m2结果
	 */
	public static BigDecimal add(BigDecimal m1,BigDecimal m2){
		return add(m1,m2,2);
	}
	/**
	 * 加法,指定小数点
	 * @param m1 加数1
	 * @param m2 加数2
	 * @param scale 小数点位数
	 * @return m1+m2结果
	 */
	public static BigDecimal add(BigDecimal m1,BigDecimal m2,int scale){
		return m1.add(m2).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	public static boolean isEmptyNumber(Long obj){
		if(obj == null){
			return true;
		}
		
		if(0 == obj.intValue()){
			return true;
		}
		
		return false;
	}
}
