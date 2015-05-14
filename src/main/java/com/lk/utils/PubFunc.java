package com.lk.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PubFunc {
	
	private static Random random = new Random(System.currentTimeMillis());
	
	public static long getRandomNumber(){	
		return random.nextLong();
	}
	
  	public static int compareTime(Timestamp ts,String time){
//		int flag = 0;
  		Calendar cl = Calendar.getInstance();
  		cl.setTime(ts);
		String tsTime = fillString(cl.get(Calendar.HOUR_OF_DAY)+"",2) + fillString(cl.get(Calendar.MINUTE)+"",2) + fillString(cl.get(Calendar.SECOND)+"",2);
		
		return tsTime.compareTo(time);
	}
  	
	/**
	 * 设置字符串前导字符
	 * @param code java.lang.String　待处理字符串
	 * @param addChar java.lang.String　前导字符串
	 * @param len int　长度
	 */
	public static String getAddCode(String source, String addStr, int len) {
		if (source == null) {
			return "";
		}
		StringBuffer _rtn = new StringBuffer(100);
		if (source.length() < len) {
			for (int i = 0; i < len - source.length(); i++) {
				_rtn.append(addStr);
			}
			return _rtn + source;
		} else {
			return source;
		}
	}
	
  	public static String concatObjs(Object[] strs) {
  		
		String s = "";
		
		for (int i = 0; i < strs.length; i++) {
			s += strs[i];
			if (i != strs.length - 1) {
				s += ",";
			}
		}
		return s;
	}
  	
  	public static String concatObjs(List<Object> list) {
  		
		String s = "";
		
		for (int i = 0; i < list.size(); i++) {
			s += list.get(i);
			if (i != list.size() - 1) {
				s += ",";
			}
		}
		return s;
	}
  	
	/**
	 * 说明：
	 * 格式化数字
	 * @param dValue
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(double dValue, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		//		df.setMaximumFractionDigits(precision);
		return df.format(dValue);
	}
	/**
	 * 说明：
	 * 格式化数字
	 * @param dValue
	 * @return
	 */
	public static String formatNumber(double dValue) {
		String pattern = ",##0.00";
		return formatNumber(dValue, pattern);
	}
	/**
	 * 说明：
	 * 格式化字符串
	 * @param dValue
	 * @return
	 */
	public static double formatString(String sValue) {
		if (sValue == null) {
			return Double.NEGATIVE_INFINITY;
		}
		double d = 0d;
		int index = sValue.indexOf(",");
		String str = "";
		while (index >= 0) {
			str += sValue.substring(0, index);
			sValue = sValue.substring(index + 1, sValue.length());
			index = sValue.indexOf(",");
		}
		str += sValue;
		try {
			d = Double.parseDouble(str);
		} catch (Exception e) {
			d = Double.NEGATIVE_INFINITY;
		}
		return d;
	}
	private static int ONEDATE = 24 * 60 * 60 * 1000;
	/**
	 * 计算两个日期的差值 (前提：d2>d1，否则得到负数)
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getBalance(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long time1 = d1.getTime();
		long time2 = d2.getTime();
		return (int) ((time2 - time1) / ONEDATE);
	}


	public static boolean isEmpty(String number) {
		if(number == null || "".equals(number)){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Long number) {
		if(number == null || number.longValue() <= 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(BigDecimal number) {
		if(number == null || number.compareTo(new BigDecimal(0)) <= 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Integer number) {
		if(number == null || number.intValue() <= 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Collection<?> ct) {
		if(ct == null || ct.size() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Map<?,?> map){
		if(map == null || map.size()==0){
			return true;
		}
		return false;
	}
	
	public static List<String[]> toArray(String str,String sep,String subSep){
		String[] strs = toArray(str,sep);
		if(strs == null){
			return null;
		}
		
		List<String[]> strList = new ArrayList<String[]>(strs.length);
		for(String subStr:strs){
			strList.add(toArray(subStr,subSep));
		}
		return strList;
	}
	
	public static String[] toArray(String str,String sep){
		if(str == null){
			return null;
		}
		String[] strs = str.split(sep);
		for(int i=0;i<strs.length;i++){
			strs[i] = strs[i].trim();
		}
		return strs;
	}
	
	public static String[] toArray(String str){
		return toArray(str,",");
	}
	
	/**
     * 将字符串补0
     * @param str 字符串
     * @param length 长度，如果字符串长度小于length,其它的位置补0
     * @return 补0后的字符串
     */
    public static String fillString(String str, int length) {
        return fillString(str, length, '0');
    }
    
    /**
     * 将字符串补指定字符
     * @param str 字符串
     * @param length 长度，如果字符串长度小于length,其它的位置补ch
     * @param ch 补充字符
     * @return 补0后的字符串
     */
    public static String fillRigthString(String str, int length, char ch) {
        if (str == null || str.length() == 0 || str.length() >= length) {
            return str;
        }
        int size = length - str.length();
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        
        for (int i = 0; i < size; i++) {
            buffer.append(ch);
        }
        return buffer.toString();
    }
    
    /**
     * modify by GaryChen 20120423 修改隐藏代替符"-"个数与被隐藏编码个数一致。
     * 
     * 对身份ID进行隐码处理，用于页面
     * 
     * @param distID  身份ID
     * @return
     */
    public static String formatDistID(String distID) {
        if (PubFunc.isEmpty(distID)) {
            return "" ;    
        }
        distID = distID.trim();
        
        int length = distID.length();
        
        if(length>=10){
        	StringBuffer sb = new StringBuffer();
        	distID = sb.append("-").append(distID.substring(1,2)).append(
        			PubFunc.fillString(distID.substring(length-3,length) , length-2, '-')).toString();
    	}else if(length>3){
    		distID = PubFunc.fillString(distID.substring(length-3,length) , length, '-');
    	}
        
        return distID ;
    }
    
    /**
     * 对account进行隐码处理，用于页面
     * 
     * @param account 		帐号
     * @param startCh      起始隐码位置
     * @param formatLen    需加密的位数
     * @param ch           用于加密的符号
     * 
     * @return
     */
    public static String formatAccount(String account,int startCh, int formatLen, char ch) {
        if (PubFunc.isEmpty(account)) {
            return "" ;    
        }
        account = account.trim();
        if (startCh<0) {
            startCh = 0;
        }
        if((startCh+formatLen)<=account.length()){
        	String endStr = PubFunc.fillString(account.substring(startCh+formatLen,account.length()),account.length()-startCh,ch);
        	return account.substring(0,startCh) + endStr;
        }else if(startCh<=account.length()){
        	return PubFunc.fillRigthString(account.substring(0,startCh),account.length(),ch);
        }else{
        	return PubFunc.fillString(String.valueOf(ch),account.length(),ch);
        }
    }
    
    
    /**
     *	对公司統編 进行隐码处理
     * @param corporationId
     * @return
     */
    public static String formatCorporationId(String corporationId){
        if (PubFunc.isEmpty(corporationId)) {
            return "" ;    
        }
        corporationId = corporationId.trim();
        if(corporationId.length()<=3){
        	return corporationId;
        }
        //要被隐码处理的个数
        int size = corporationId.length()-3;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++){
        	sb.append("-");
        }
        sb.append(corporationId.substring(corporationId.length()-3));
        return sb.toString();
    }
    
    
    /**
     * 将字符串补指定字符
     * @param str 字符串
     * @param length 长度，如果字符串长度小于length,其它的位置补ch
     * @param ch 补充字符
     * @return 补0后的字符串
     */
    public static String fillString(String str, int length, char ch) {
        if (str == null || str.length() == 0 || str.length() >= length) {
            return str;
        }

        int size = length - str.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            buffer.append(ch);
        }
        buffer.append(str);
        return buffer.toString();
    }
    /**
     * 以金额为参数，该方法将阿拉伯数字转换成中文大写
     * @param invalue
     * @return
     */
    public static String changeToBig(double invalue) {
        double value = invalue;
        if (value < 0) {
            value = value * (-1);
        }
        if (value == 0) {
            return "零整";
        }
        char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
        char[] vunit = { '万', '亿' }; // 段名表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
        long midVal = NumFunc.multiply(new BigDecimal(value), new BigDecimal(100)).longValue(); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串
        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) {
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } 
                if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += String.valueOf(vunit[vidx - 1]);
                    //zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += String.valueOf(zero);
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0)
                prefix += String.valueOf(hunit[idx - 1]);
            if (idx == 0 && vidx > 0) {
                prefix += String.valueOf(vunit[vidx - 1]); // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0)
            prefix += "元"; // 如果整数部分存在,则有元的字样

        //如果是负数,前面补负
        if (invalue < 0) {
            prefix = "负" + prefix;
        }
        return prefix + suffix; // 返回正确表示
    }

    /**
     * If inputed parameter is null then return the Long Zero, otherwise return its own value.
     * @param val
     * @return
     */
    public static Long getLongValue(Long val) {
    	if(null == val) {
    		return Long.valueOf(0);
    	}
    	return val;
    }
    /**
     * If inputed parameter is null then return the BigDecimal Zero, otherwise return its own value.
     * @param val
     * @return
     */
    public static BigDecimal getBigDecimalValue(BigDecimal val) {
    	return (null == val?BigDecimal.ZERO:val);
    }
    
	/**
	 * 取得dir+name的文件流
	 * @param name 文件名
	 * @param dir 文件目录
	 * @return
	 * @throws MappingException
	 */
	public static InputStream getStream(String file)
		throws Exception {
		InputStream rsrc = null;

		try {
			rsrc = PubFunc.class.getResourceAsStream(file);
		} catch (Exception e) {
			throw new Exception(
				"Resource: " + file + " not found",
				e);
		}
		
		if (rsrc == null)
			throw new Exception("Resource: " + file + " not found");
		return rsrc;
	}
	
	/**
	 * 判断字符串是否double类型
	 * @param temp
	 * @return
	 */
	public static boolean isDouble(String temp) {
		String pattern = "^((-)?([1-9]\\d*)|0)(\\.\\d{2})?$";
		return Pattern.matches(pattern, temp);
	}
	
	/**
	 * 中文转码
	 * @param s
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return java.net.URLEncoder.encode((new sun.misc.BASE64Encoder()).encode(s.getBytes()));
	}
	
	public static String formatDate2String(String strDate){
		String s=null;
		if(!isEmpty(strDate)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date date=DateFunc.str2Date(strDate);
			 s= formatter.format(date);
		}else{
			s=strDate;
		}		
		return s;
	}
	
	public static String formatDateTime2String(String strDateTime){
		String s=null;
		if(!isEmpty(strDateTime)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date date=DateFunc.str2Date(strDateTime);
			 s= formatter.format(date);
		}else{
			s=strDateTime;
		}		
		return s;
	}
	
	public static Long obj2Long(Object o,Long defaultValue){
//		Long ret=Long.valueOf(0);
		if(defaultValue==null){
			defaultValue=Long.valueOf(0);
		}
		if(o==null){
			return defaultValue;
		}
		
		try{
			BigDecimal bg = new BigDecimal(o.toString());
			return bg.longValue();
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	public static boolean isEquals(List<?> list1, List<?> list2){
		if (list1==null || list2==null || list1.size()!=list2.size()){
			return false;
		}
		for (int i=0;i<list1.size();i++){
			if ( ! list1.get(i).equals(list2.get(i))){
				return false;
			}
		}
		return true;
	}
	
  	
  	//去掉最后的0
  	public static String removeEndZero(String str){
  		for(int i=str.length()-1;i>=0;i--){
  			if(str.charAt(i) == '0'){
  				str = str.substring(0,str.length()-1);
  			}else{
  				break;
  			}
  		}
  		if(str.length() == 0){
  			return "0";
  		}
  		return str;
  	}
  	
  	/**
	 * 过滤字符串中所有全角和半角空格
	 *  @param strInput    需要过滤空格的字符串
	 * @return String
	 * @throws Exception
	 */
	public static String trimBlank(String strInput){

		//过滤半角空格
		while (strInput.indexOf(" ") != -1) {
			strInput = strInput.replaceAll(" ", "");
		}
		//过滤全角空格
		while (strInput.indexOf("　") != -1) {
			strInput = strInput.replaceAll("　", "");
		}
		return strInput;
	}
	
	/**
	 * 验证身份证号是否有效。 步骤：判断参数idNum是否为18位；
	 * 如果是，则返回true；
	 * 如果不是，则返回false。
	 * @param idNum
	 * @return
	 */
	public static boolean isValidateIdNum(String idNum) {
		if (PubFunc.isEmpty(idNum)) {
			return false;
		}
		if (idNum.length() == 18) {
			return true;
		} else {
			return false;
		}
	}
	
	/**返回指定位数的随机数字字符串
	 * @param length
	 * @return
	 */
	public static String getRandomStrByLength(int length){
		
		StringBuilder sbd = new StringBuilder();
		Random ran = new Random();
		for(int i=0;i<length;i++){
			sbd.append(ran.nextInt(9));
		}
		
		return sbd.toString();
	}
	/**
	 * 比较2个日期是否过期
	 * @param saleDate
	 * @param date
	 * @return
	 */
	public static boolean checkExpireDate(java.util.Date saleDate, java.util.Date date) {
		if (saleDate == null || date == null ) {
			return false;
		}
		if (DateFunc.date2Int(date).intValue() < DateFunc.date2Int(saleDate).intValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获得身份证中的生日日期
	 * @param idNum
	 * @return
	 */
	public static Integer getBirthDay(String idNum) {
		if (!isValidateIdNum(idNum)) {
			return null;
		}
		if (idNum.length() == 18) {
			return Integer.valueOf(idNum.substring(6, 14));
		} else {
			return Integer.valueOf("19" + idNum.substring(6, 12));
		}
	}
	
	/**
	 * 取得随机数
	 * @param random           随机数对象
	 * @param range            随机数范围
	 * @return                 随机整数
	 */
	public static int getRandomInt(Random random, int count) {
		int returnValue = (int) (random.nextDouble() * count);
		return returnValue;
	}
	

	/**
	 * 取得随机数(不返回0)
	 * @param range           随机数范围
	 * @return 				     随机整数
	 */
	public static int getRandomInt(int range) {
		int random = (int) (Math.random() * range);
		return random + 1;
	}

    /**
     * 转换ADA为14位。
     * @param ada
     * @return
     */
    public static Long AdaTo14b(Long ada) {
        if (ada != null && ada.longValue() < 36000000000000L) {
            return new Long(ada.longValue() + 36000000000000L);
        } else {
            return ada;
        }
    }
    
    /*********************************** 身份证验证开始 ****************************************/       
    /**   
     * 身份证号码验证    
     * 1、号码的结构   
     * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，   
     * 八位数字出生日期码，三位数字顺序码和一位数字校验码。   
     * 2、地址码(前六位数）    
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。    
     * 3、出生日期码（第七位至十四位）   
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。    
     * 4、顺序码（第十五位至十七位）   
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，   
     * 顺序码的奇数分配给男性，偶数分配给女性。    
     * 5、校验码（第十八位数）   
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0,  , 16 ，先对前17位数字的权求和   
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4   
     * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0   
     * X 9 8 7 6 5 4 3 2   
     */    
    
    /**   
     * 功能：身份证的有效验证   
     * @param IDStr 身份证号   
     * @return 有效：返回"" 无效：返回String信息   
     * @throws ParseException   
     * @throws java.text.ParseException 
     * @throws NumberFormatException 
     */    
    public static String IDCardValidate(String IDStr) throws Exception, NumberFormatException, java.text.ParseException {     
        String errorInfo = "";// 记录错误信息     
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",     
                "3", "2" };     
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",     
                "9", "10", "5", "8", "4", "2" };     
        String Ai = "";     
        // ================ 号码的长度 15位或18位 ================     
        if (IDStr.length() != 15 && IDStr.length() != 18) {     
            errorInfo = "身份证号码长度应该为15位或18位。";     
            return errorInfo;     
        }     
        // =======================(end)========================     
    
        // ================ 数字 除最后以为都为数字 ================     
        if (IDStr.length() == 18) {     
            Ai = IDStr.substring(0, 17);     
        } else if (IDStr.length() == 15) {     
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);     
        }     
        if (isNumeric(Ai) == false) {     
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";     
            return errorInfo;     
        }     
        // =======================(end)========================     
    
        // ================ 出生年月是否有效 ================     
        String strYear = Ai.substring(6, 10);// 年份     
        String strMonth = Ai.substring(10, 12);// 月份     
        String strDay = Ai.substring(12, 14);// 月份     
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {     
            errorInfo = "身份证生日无效。";     
            return errorInfo;     
        }     
        GregorianCalendar gc = new GregorianCalendar();     
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");     
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150    
                || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {     
            errorInfo = "身份证生日不在有效范围。";     
            return errorInfo;     
        }     
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {     
            errorInfo = "身份证月份无效";     
            return errorInfo;     
        }     
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {     
            errorInfo = "身份证日期无效";     
            return errorInfo;     
        }     
        // =====================(end)=====================     
    
        // ================ 地区码时候有效 ================     
        Hashtable<String,String> h = GetAreaCode();     
        if (h.get(Ai.substring(0, 2)) == null) {     
            errorInfo = "身份证地区编码错误。";     
            return errorInfo;     
        }     
        // ==============================================     
    
        // ================ 判断最后一位的值 ================     
        int TotalmulAiWi = 0;     
        for (int i = 0; i < 17; i++) {     
            TotalmulAiWi = TotalmulAiWi     
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))     
                    * Integer.parseInt(Wi[i]);     
        }     
        int modValue = TotalmulAiWi % 11;     
        String strVerifyCode = ValCodeArr[modValue];     
        Ai = Ai + strVerifyCode;     
    
        if (IDStr.length() == 18) {     
            if (Ai.equals(IDStr) == false) {     
                errorInfo = "身份证无效，不是合法的身份证号码";     
                return errorInfo;     
            }     
        } else {     
            return "";     
        }     
        // =====================(end)=====================     
        return "";     
    }     
    
    /**   
     * 功能：设置地区编码   
     * @return Hashtable 对象   
     */    
    private static Hashtable<String,String> GetAreaCode() {     
        Hashtable<String,String> hashtable = new Hashtable<String,String>();     
        hashtable.put("11", "北京");     
        hashtable.put("12", "天津");     
        hashtable.put("13", "河北");     
        hashtable.put("14", "山西");     
        hashtable.put("15", "内蒙古");     
        hashtable.put("21", "辽宁");     
        hashtable.put("22", "吉林");     
        hashtable.put("23", "黑龙江");     
        hashtable.put("31", "上海");     
        hashtable.put("32", "江苏");     
        hashtable.put("33", "浙江");     
        hashtable.put("34", "安徽");     
        hashtable.put("35", "福建");     
        hashtable.put("36", "江西");     
        hashtable.put("37", "山东");     
        hashtable.put("41", "河南");     
        hashtable.put("42", "湖北");     
        hashtable.put("43", "湖南");     
        hashtable.put("44", "广东");     
        hashtable.put("45", "广西");     
        hashtable.put("46", "海南");     
        hashtable.put("50", "重庆");     
        hashtable.put("51", "四川");     
        hashtable.put("52", "贵州");     
        hashtable.put("53", "云南");     
        hashtable.put("54", "西藏");     
        hashtable.put("61", "陕西");     
        hashtable.put("62", "甘肃");     
        hashtable.put("63", "青海");     
        hashtable.put("64", "宁夏");     
        hashtable.put("65", "新疆");     
        hashtable.put("71", "台湾");     
        hashtable.put("81", "香港");     
        hashtable.put("82", "澳门");     
        hashtable.put("91", "国外");     
        return hashtable;     
    }     
    
    /**   
     * 功能：判断字符串是否为数字   
     * @param str   
     * @return   
     */    
    public static boolean isNumeric(String str) {     
        Pattern pattern = Pattern.compile("[0-9]*");     
        Matcher isNum = pattern.matcher(str);     
        if (isNum.matches()) {     
            return true;     
        } else {     
            return false;     
        }     
    }     
    
    /**   
     * 功能：判断字符串是否为日期格式   
     * @param str   
     * @return   
     */    
    public static boolean isDate(String strDate) {     
        Pattern pattern = Pattern     
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");     
        Matcher m = pattern.matcher(strDate);     
        if (m.matches()) {     
            return true;     
        } else {     
            return false;     
        }     
    }     
    
    
    
    
    /**
     * 4GPOS日期格式转换成民国日期格式
     * @param posDate 4GPOS日期
     * @return TW日期
     * @author virgil
     */
    public static Integer pos2MgDate(Integer posDate){
    	   Integer twDate = null;        
    	   
    	   if (posDate == null) {
    	       return null ;    
    	   }
    	   
        if(posDate != null && posDate.intValue() >= 10000000 && posDate.intValue() <= 99999999){ // 8 bit       		
        		twDate = new Integer(posDate.intValue() - 19110000);
        }else{
        		//LogFunc.info(PubFunc.class + " pos2MgDate() : parameter (" + posDate + ") error, pls check !");
        }

        return twDate;
    }
    
    /**
     * 民国日期格式转换成4GPOS日期格式
     * @param twDate TW日期
     * @return 4GPOS日期
     * @author Virgil
     */
    public static Integer mg2posDate(Integer twDate){
    	   Integer posDate = null;          
    	   
    	   if (twDate == null) {
    	       return null ;    
    	   }
    	   
    	   int len = twDate.toString().length() ;
    	   
        if(twDate != null && (len >= 3 && len <= 7)){   
        		posDate = new Integer(twDate.intValue() + 19110000);   
        }else{
        		//LogFunc.info(PubFunc.class + " mg2posDate() : parameter (" + twDate + ") error, pls check !");
        }
        
        return posDate;
    }
    
    /**
     * TW日期格式转换成4GPOS日期格式
     * @param twDate TW日期
     * @return 4GPOS日期
     * @author 鲍利兵
     */
    public static Integer tw2posDate(Integer twDate){
    	   Integer posDate = null;        
    	   
    	   int len = twDate.toString().length() ;
    	       
        if(twDate != null && (len >= 3 && len <= 7)){   
        		posDate = new Integer(twDate.intValue() + 19000000);   
        }
        
        return posDate;
    }
    
    /**
     * TW日期格式转换成4GPOS日期格式。<br>
     * 這裡是簡化函數，不作實際的日期格式效驗。
     * @param twDate TW日期
     * @return 4GPOS日期
     */
    public static int tw2posDate(int twDate){
    	   int posDate = twDate; 
    	      
        if(twDate > 1000000 && twDate<9999999){   
        		posDate = twDate + 19000000;   
        }
        
        return posDate;
    }

	/**
	 * 將查詢到的字符串根據分隔符保存為List 集合
	 * @param staffStr
	 */
    public static List<String> batchStr2List(String str) {
		List<String> str2List = null;
		if (!PubFunc.isEmpty(str)) {
			String[] strArr = str.split(",");
			str2List = Arrays.asList(strArr);
		} 
		return str2List;
	}

    /**
     * 4GPOS日期格式转换成TW日期格式
     * @param posDate 4GPOS日期
     * @return TW日期
     */
    public static Integer pos2twDate(Integer posDate){
    	Integer twDate = null;        
        if(posDate != null && posDate.toString().length() == 8){       		
        	twDate = new Integer(posDate.intValue() - 19000000);
        }
        return twDate;
    }
    
    /**
     * 取兩個單元素集合的交集。<br>
     * 支持List/Set。<p>
     * @param <T>
     * @param c1
     * @param c2
     * @return
     */
    @SuppressWarnings("unchecked")
    
/*    *//**   
     * @param args   
     * @throws ParseException   
     * @throws java.text.ParseException 
     * @throws NumberFormatException 
     *//*    
  public static void main(String[] args) throws ParseException, NumberFormatException, java.text.ParseException {     
     // String IDCardNum="210102820826411";     
      // String IDCardNum="210102198208264114";     
      String IDCardNum = "431202198509080810";     
     System.out.println(IDCardValidate(IDCardNum));     
      // System.out.println(cc.isDate("1996-02-29"));     
  }    */ 
/*********************************** 身份证验证结束 ****************************************/   

    /**
     * 检查是否是数字
     * @param str
     * @return
     */
    public static boolean isNum(String str){
        boolean isNum = false;
        if(!isEmpty(str) && str.length()>0){
            isNum = true;
            for(int i=0;i<str.length();i++){
                if("1234567890".indexOf(str.charAt(i)) < 0){
                    isNum = false;
                    break;
                }
            }
        }
        return isNum;
    }
    

    /**
     * @param str
     * @return
     * 如：
     * 		String source = "DpBvRate:0.97, bpvRatio:15.5, bpvRound:0, convertionRate:1.032,promotionItem:P128-P228-P141-P241-P143-P243-P163-P263-P1901-P1902-P1903-P2903-P301012-P21306171-P21307011,NewDSTEnd:20131231, soaProviderEnable:true, soaProviderUrl:t3://10.140.32.55:18011, soaProviderUser:weblogic, soaProviderPwd:weblogic.55";
     * 获取convertionRate:1.032 中 对应1.032的值
     * getRuleValueString(source,"convertionRate"); reutrn 1.032
     * 
     */
    public static String getRuleValutringByKey(String source,String target){
    	if(PubFunc.isEmpty(source)||PubFunc.isEmpty(target)){
    		return null;
    	}
    	
		if(!source.contains(target)){
			return null;
		}
		//由于String source="minbv:50,bv:100"这种有相同的字符串'bv',因此用indexof(",",fromIndex)查找的时候，后面一个bv查找的位置会被前一个替换
		int startIndex=source.indexOf(target);
		if(startIndex>0){//不是字符串的开头
			String frontStr=String.valueOf(source.charAt(startIndex-1));
			//判断查找到当前字符串的前一个字符不为空或者为","号，则表示非全字符匹配
			//比如String source="minbv:50,bv:100"这种有相同的字符串'bv',因此用indexof(",",fromIndex)查找的时候，查找bv的位置总是前面minbv中bv的位置
			if(!PubFunc.isEmpty(frontStr)&&!frontStr.equals(",")){
				source=source.substring(startIndex+target.length()+1);
				return getRuleValutringByKey(source,target);
			}
		}
		
		int start=startIndex+target.length()+1;
		int end = source.indexOf(",",startIndex);
		if(end==-1){
			return source.substring(start);
		}else{
			return source.substring(start, end);
		}
			
	}
    
	/**
	 * 数字增加千位符
	 * @param orgNum
	 * @return
	 * @author baolibing
	 */
	public static String formatNumGroup(BigDecimal orgNum){
		String returnNum = "";
		
		DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(); 
		df.setGroupingSize(3);
		returnNum = df.format(orgNum);	
		
		return returnNum;
	}
    
	/**
	 * 去除左右两边的空格(包括中文和英文空格).
	 * <p>在查询送货资料完毕时去除两边空格操作<br>
	 * 先把中文空格替换成英文空格,再去掉左右空格
	 *  toby
	 * @param str 传入的str  
	 * @return 去除空格后的str
	 */
   public static String trim(String str) { 
   	
   	  while (str.indexOf("　") != -1) {
   	  		str = str.replaceAll("　", " ");
      } 	  
   	  return str.trim();
   }
   
   /**
    * 取當前機器ip列表
    * @return
    */
   public static List<String> getIpAddress() {
       List<String> ipList = new ArrayList<String>();
       Enumeration<NetworkInterface> netInterfaces = null;
       try {
//           StringBuffer ipSb = new StringBuffer("+++++++++++++++++++++++++++");
           netInterfaces = NetworkInterface.getNetworkInterfaces();
           String ip = null;
           while (netInterfaces.hasMoreElements()) {
               
               NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
               
               Enumeration<InetAddress> ips = ni.getInetAddresses();
               while (ips.hasMoreElements()) {
                   ip = ips.nextElement().toString();
                   if (ip!=null) {
                       ip = ip.substring(1);
                   }
                   ipList.add(ip);
//                   ipSb.append(ip).append(" || ");
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return ipList;
   }
   
   /**     
    * 半角转全角     
    * @param input String.     
    * @return 全角字符串.     
    */     
   public static String ToSBC(String input) {
   	if (input==null) return null;
   	char c[] = input.toCharArray();      
   	for (int i = 0; i < c.length; i++) {      
   		if (c[i] == ' ') {      
   			c[i] = '\u3000';            //采用十六进制,相当于十进制的12288
   			
   		} else if (c[i] < '\177') {    //采用八进制,相当于十进制的127      
   			c[i] = (char) (c[i] + 65248);      
   			
   		}      
   	}      
   	return new String(c);      
   }      
   
   /**    
    * 全角转半角    
    * @param input String.    
    * @return 半角字符串    
    */     
   public static String ToDBC(String input) {
   	if (input==null) return null;
   	
   	char c[] = input.toCharArray();      
   	for (int i = 0; i < c.length; i++) {      
   		if (c[i] == '\u3000') {      
   			c[i] = ' ';      
   		} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {      
   			c[i] = (char) (c[i] - 65248);      
   			
   		}      
   	}      
   	String returnString = new String(c);      
   	
   	return returnString;      
   }
   
   public static void main(String[] args) {
/*	   String str="start:0,end:12,disStart:0,disEnd:12,isFullRedeem:true,mindp:0,minbv:0,dp:-1,bv:-1,maxCouponDP:500,couponIndex:16,type:1";
	   System.out.println(getRuleValutringByKey(str, "start"));
	   System.out.println(getRuleValutringByKey(str,"end"));
	   System.out.println(getRuleValutringByKey(str,"disStart"));
	   System.out.println(getRuleValutringByKey(str,"disEnd"));
	   System.out.println(getRuleValutringByKey(str,"isFullRedeem"));
	   System.out.println(getRuleValutringByKey(str,"mindp"));
	   System.out.println(getRuleValutringByKey(str,"minbv"));
	   System.out.println(getRuleValutringByKey(str,"dp"));
	   System.out.println(getRuleValutringByKey(str,"bv"));
	   System.out.println(getRuleValutringByKey(str,"maxCouponDP"));
	   System.out.println(getRuleValutringByKey(str,"couponIndex"));
	   System.out.println(getRuleValutringByKey(str,"type"));*/
	   String str = "99188592";
	   str = formatCorporationId(str);
	   System.out.println(str);
	   
   }
}
