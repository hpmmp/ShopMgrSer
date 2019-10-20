package com.shop.utils;
 

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 

public class StringUtil {
	
	private static final DecimalFormat  formatter1 =new DecimalFormat("000");
	
	public static String formatDoubleStr(Double num  ,int score){
		BigDecimal   b=new   BigDecimal(num);      
		return String.valueOf( b.setScale(score,BigDecimal.ROUND_HALF_UP).doubleValue());      
	  
	}
	
	public static String datetimetostr(Date d, String sFormat){
	    if(d == null){
	      return "";

	    }else{
	       SimpleDateFormat dateformatter = new SimpleDateFormat(sFormat);
	       String s = dateformatter.format(d);
	       dateformatter = null;
	       return s;
	    }
	}
	
	public static String[] split(String s, String splitter) {
		if (s == null || splitter == null || s.length() == 0
				|| splitter.length() == 0)
			return null;
		char[] src = s.toCharArray();
		char[] split = splitter.toCharArray();
		List<String> list = new ArrayList<String>();
		int i, j, splitLen = split.length, begin = 0;
		for (i = 0; i < src.length;) {
			for (j = 0; j < splitLen; j++)
				if ((i + j) >= src.length || src[i + j] != split[j])
					break;
			if (j == splitLen) {
				list.add((begin == i) ? null
						: new String(src, begin, i - begin));
				i += splitLen;
				begin = i;
			} else
				i++;
		}
		list.add((begin == src.length) ? null : new String(src, begin,
				src.length - begin));
		String[] ret = new String[list.size()];
		return list.toArray(ret);
	}

	public static final String nullToEmptyOfObject(Object o){
		if(o != null)
	      return o.toString();
	    else
	      return "";
	}
	public static final String nullToEmpOfObject(Object o){
		if(o != null&&!o.toString().equalsIgnoreCase("null"))
	      return o.toString();
	    else
	      return "";
	}
	public static final String nullToZero(Object o){
		if(o != null && !o.toString().toLowerCase().equals("null")&& !o.toString().equals(""))
	      return o.toString();
	    else
	      return "0";
	}
	public static String dtToStr(Date d, String sFormat){
	    if(d == null){
	      return "";
	    }else{
	       SimpleDateFormat df = new SimpleDateFormat(sFormat);
	       String s = df.format(d);
	       return s;
	    }
	}
	public static String tmToStr(Timestamp d, String sFormat){
	    if(d == null){
	      return "";
	    }else{
	       SimpleDateFormat df = new SimpleDateFormat(sFormat);
	       String s = df.format(d);
	       return s;
	    }
	}
	public static final Timestamp strToTm(String val){
		 Timestamp ts = new Timestamp(System.currentTimeMillis());   
		 ts = Timestamp.valueOf(val);   
         return ts;
	}
	public static String dateToLongStr(Date d, String sFormat){
	    if(d == null){
	      return "0";
	    }else{ 
	       return String.valueOf( d.getTime());
	    }
	}
	
	public static Date strToDate(String val,String dtformat){		
	    try{
	    	if(dtformat!=null && dtformat.length()>0){
	    		SimpleDateFormat df = new SimpleDateFormat(dtformat);
	    		return df.parse(val);
	    	}else{
	    		return null;
	    	}
	    }catch(ParseException e){
	      return null;
	    }
	}
	public static String getDateFormat(String val){
		String dtformat="";
		if(val!=null && val.length()>0){
			switch(val.length()){				
				case 10:
					dtformat="yyyy-MM-dd";
					break;				
				case 19:
					dtformat="yyyy-MM-dd HH:mm:ss";
					break;					
				case 4:
					dtformat="yyyy";
					break;
				case 7:
					dtformat="yyyy-MM";
					break;
				case 21:
					dtformat="yyyy-MM-dd HH:mm:ss.s";
					break;
					
			}
    	}
    	return dtformat;
	}
	public static String getSqlDateFormat(String val){
		String dtformat="";
		if(val!=null && val.length()>0){
			switch(val.length()){
				case 10:
					dtformat="yyyy-MM-dd";
					break;
				case 19:
					dtformat="yyyy-MM-dd HH24:mi:ss";
					break;
				case 4:
					dtformat="yyyy";
					break;
				case 7:
					dtformat="yyyy-MM";
					break;
					
			}
    	}
    	return dtformat;
	}
	public static final int strToInt(String val,int defval){
		try{
			return Integer.parseInt(val);
		}catch(Exception ex){
			return defval;
		}
	}
	public static final float strToFloat(String val,float defval){
		try{
			return Float.parseFloat(val);
		}catch(Exception ex){
			return defval;
		}
	}
	public static final long strToLong(String val,long defval){
		try{
			return Long.parseLong(val);
		}catch(Exception ex){
			return defval;
		}
	}
	public static final double strToDouble(String val,double defval){
		try{
			return Double.parseDouble(val);
		}catch(Exception ex){
			return defval;
		}
	}
	public static final BigDecimal strToBigDecimal(String val,float defval){
		try{
			return new BigDecimal(val);
		}catch(Exception ex){
			return new BigDecimal(defval);
		}
	}
	public static final String getProcedure(String pName,int len){
	  String procedure = "";
	  for(int i=0;i<len ;i++)
		  procedure += ",?";
	  if(procedure.startsWith(",")) procedure = procedure.substring(1);	  
	  procedure = "{call "+pName+"(" + procedure + ")}";	  
	  return procedure;
	}
	
	public static final boolean isEmptyOrNull(String str){
		  return str==null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("") || str.trim().length()==0;	  
	}
	
	public static final String  getDirURL(String Str){
	     Str="/"+Str;
	     int i =  Str.lastIndexOf("/");
	     if (i<0) return "";
	     String  Str1 = Str.substring(0,i );

	     int j = Str1.lastIndexOf("/");
	     if (j<0) return "";
	     String Str2 = Str.substring(j + 1,i);

	     return Str2;

	}
	public static final String getTreeInfo(String str){
		String info = "";
		String[] arr = str.split(",");
		String separator=",";
		for(int i=0;i<arr.length;i++){
			if(arr[i].length()>2){
				do{					
					arr[i] = arr[i].substring(0,arr[i].length()-2);
					if(info.indexOf(separator+arr[i]+separator)==-1)
						info +=  separator+arr[i];
				}while(arr[i].length()>2);
			}
		}
		return info.startsWith(separator)?info.substring(1):info;
	}
	
	public static final String arrayToStr(Object[] objArray,String... separator) {
		StringBuffer sb = new StringBuffer("");
		String sep = ",";
		if(separator!=null && separator.length>0)
			sep = separator[0];

		if (objArray != null) {
			for (int i = 0; i < objArray.length; i++) {
				sb.append(objArray[i].toString());
				if ((i + 1) != objArray.length)
					sb.append(sep);
			}
		}
		return sb.toString();
	}

	 
	
	/**
	 * 产生指定长度的随机数字和字母组合字符串
	 * @param length：长度
	 * @return
	 * @author zhangchuyan
	 */
	public static String GenerateRandomString(int length){
		
		String Vchar = "0,1,2,3,4,5,6,7,8,9,"
		 		+ "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";   
		
	    String[] VcArray = Vchar.split(",");
	    String Vstr = "";   

	    for(int i=0;i<length;i++){ 
	    	int score = (int)(Math.random()*35);
	    	Vstr += VcArray[score];   
	    }   
	    return  Vstr;   
    }
	/**
	 * 产生指定长度的随机数字
	 * @param length：长度
	 * @return
	 * @author zhangchuyan
	 */
	public static String GenerateRandom(int length){
		
		String Vchar = "0,1,2,3,4,5,6,7,8,9";   
		
	    String[] VcArray = Vchar.split(",");
	    String Vstr = "";   

	    for(int i=0;i<length;i++){ 
	    	int score = (int)(Math.random()*9);
	    	Vstr += VcArray[score];   
	    }   
	    return  Vstr;   
    }
	public static String boldStr(String str){
		if(isEmptyOrNull(str))
			return"";
		else
			return "【"+str+"】";
	}
	public static String strToInSql(String str){
		return "('"+str.replace(";", "','")+"')";
	}
	public static final String  convertReceiver(String str){
		  int pos=str.indexOf(";");
		  if (pos<str.length()-1 && pos>0){
			  if (str.endsWith(";")){
				  return str;
			  }else return str+";";
		  }else{
			  if (str.endsWith(";")){
				  return str.substring(0,str.length()-1);
			  }else  return str;
		  }
		  
	 }
	 public static String convertPercentage(long num1,long num2, String pattern){
		 
		 DecimalFormat df = new DecimalFormat(pattern);
		 if(num2!=0){
			 return df.format(((double)num1/(double)num2)*100)+"%";
		 }else{
			 return df.format(0)+"%";
		 }
	 }
	 public static String convertPercentage(float num1,float num2, String pattern){
		 
		 DecimalFormat df = new DecimalFormat(pattern);
		 if(num2!=0){
			 return df.format(((double)num1/(double)num2)*100)+"%";
		 }else{
			 return df.format(0)+"%";
		 }
	 }
	 public static String getNowTimeForString(){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 String year = String.valueOf(cal.get(Calendar.YEAR));
		 String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		 if(month.length()==1)month="0"+month;
		 String date = String.valueOf(cal.get(Calendar.DATE));
		 if(date.length()==1)date="0"+date;
		 String hour = String.valueOf(cal.get(Calendar.HOUR)+12);
		 String minute = String.valueOf(cal.get(Calendar.MINUTE));
		 if(minute.length()==1)minute="0"+minute;
		 String second = String.valueOf(cal.get(Calendar.SECOND));
		 if(second.length()==1)second="0"+second;
		 return year+month+date+hour+minute+second;
	 }
	 public static Date addMonth(Date dt,int amount){
		 Calendar cal = Calendar.getInstance();  
		 cal.add(Calendar.MONTH, amount);
		 return  cal.getTime();    		  
	 }
	 public static Date addDay(Date dt,int amount){
		 Calendar cal = Calendar.getInstance();  
		 cal.add(Calendar.DAY_OF_MONTH, amount);
		 return  cal.getTime();    		  
	 }
	 public static final double getRoundW(double dout,int n){
	     String zeroStr="";
	     for (int i=0;i<n;i++) zeroStr=zeroStr+"0";
		  DecimalFormat   fnum   =   new   DecimalFormat("##0."+zeroStr);  
		 return Double.valueOf(fnum.format(dout));  
    } 
	 public static String indexDateToDisplay(String time){
		 if (time==null||time.equals("")) return "";
		 return StringUtil.dtToStr( StringUtil.strToDate(time, "yyyyMMddHHmmss"),"yyyy-MM-dd HH:mm:ss");
		 
	 }
	 public static String     getSortFloorNo(String floorNo){
			int floor=0;
			if (floorNo==null||floorNo.length()>4||floorNo.equals("null")||floorNo.equals("")) return floorNo;
		 
		 
			try{
				floor=Integer.parseInt(floorNo) ;
				if (floor>=0){
					//补900
					return  "9"+formatter1.format(floor);
				}else{
					//1000- 后，补0
					return  "0"+formatter1.format(1000+floor);
				}
			}catch (Exception ex){
				return floorNo;
			}
			 
		}
	 public static final  String getMonth(int i){
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMM" ); 
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, i);
		 
			return formatter.format( c.getTime() ) ;
		  
		}
	 public static final  String getNearMonthDay(int i){
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" ); 
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, i);
		 
			return formatter.format( c.getTime() ) ;
		  
		}
	 public static final  String getDate(){
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" ); 
			Calendar c = Calendar.getInstance();
			 
		 
			return formatter.format( c.getTime() ) ;
		  
		}
	 /**
	  * b转换成M
	  * @param str1
	  * @return
	  */
	 public static String parseLanSpeed(String str1) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  
		  return  String.valueOf(getRoundW(Double.valueOf(str1)/Double.valueOf("1048576"),2) )   ;
	 }
	 /**
	  * 分转换成元
	  * @param str1
	  * @return
	  */
	 public static String parseMoney(String str1) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  
		  return parseFormatMoney(String.valueOf(getRoundW(Double.valueOf(str1)/Double.valueOf("100"),2) )  );
	 }
	 /**
	  * 元转换成元
	  * @param str1
	  * @return
	  */
	 public static String parseYMoney(String str1) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  
		  return parseFormatMoney(String.valueOf(getRoundW(Double.valueOf(str1) ,2) )  );
	 }
	 
	 /**
	  * 三位一间隔，小数点后面保留
	  * @param str1
	  * @return
	  */
	 public static String parseFormatMoney(String str1) {   
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		    DecimalFormat a = new DecimalFormat(".##");
			String pattern="###,###.##";
		    a.applyPattern(pattern);
		  return a.format(Double.valueOf(str1)) ;
	 
	} 
 
     public static String parseDuration(String  str1) {  
    	  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
    	  long time=Long.valueOf(str1);
    	 String timeStr = null;  
    	 long hour = 0;  
    	 long minute = 0;  
    	/* long second = 0;  
         if (time <= 0)  
             return "00:00";  
         else {  
             minute = time / 60;  
             if (minute<=0){
            	 timeStr = time+"秒";  
             }else  if (minute < 60) {  
                 second = time % 60;  
                 timeStr = unitFormat(minute) + "分" + unitFormat(second)+"秒";  
             } else {  
                 hour = minute / 60;  
               
                 minute = minute % 60;  
                 second = time - hour * 3600 - minute * 60;  
                 timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second)+"秒";  
             }  
         }  
         */
    	 minute = time / 60;
         return   String.valueOf(minute);  
     }  
   
     public static String unitFormat(long i) {  
         String retStr = null;  
         if (i >= 0 && i < 10)  
             retStr = "" + Long.toString(i);  
         else  
             retStr = "" + i;  
         return retStr;  
     }  

     public static String parseMultMoney(String str1,String splitChar) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  String[]   moneyArry=split(str1,splitChar);
		  String  returnstr="";
		  for (int i = 0; i < moneyArry.length; i++) {
			  returnstr=returnstr+   parseMoney(moneyArry[i]) +  splitChar;
		  }
			if (returnstr.length()>0)  returnstr=returnstr.substring(0,returnstr.length()-1);
		  return returnstr;
	 }
     public static String parseMultYMoney(String str1,String splitChar) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  String[]   moneyArry=split(str1,splitChar);
		  String  returnstr="";
		  for (int i = 0; i < moneyArry.length; i++) {
			  returnstr=returnstr+   parseYMoney(moneyArry[i]) +  splitChar;
		  }
			if (returnstr.length()>0)  returnstr=returnstr.substring(0,returnstr.length()-1);
		  return returnstr;
	 }
     public static String parseMultInt(String str1,String splitChar) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  String[]   moneyArry=split(str1,splitChar);
		  String  returnstr="";
		  for (int i = 0; i < moneyArry.length; i++) {
			  returnstr=returnstr+   parseFormatMoney(moneyArry[i]) +  splitChar;
		  }
			if (returnstr.length()>0)  returnstr=returnstr.substring(0,returnstr.length()-1);
		  return returnstr;
	 }
     public static String parseRate(String fieldValue ) {
		  if  (fieldValue==null||fieldValue.toLowerCase().equals("null")||fieldValue.equals("")) return "";
		   return String.valueOf( StringUtil.getRoundW( Double.valueOf(String.valueOf(fieldValue))*Double.valueOf(100),2))+"%" ;

	 }
     public static final  String getsStartMonth(String yMonth,int i){
    
		String datestr=yMonth.substring(0,4)+"-"+yMonth.substring(4,6)	+"-01";
		Date d1=strToDate(datestr,"yyyy-MM-dd");
    	 SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMM" ); 
			Calendar c = Calendar.getInstance();
			c.setTime(d1);
			c.add(Calendar.MONTH, i);
		 
			return formatter.format( c.getTime() ) ;
		  
		}
     /**
	  * 分转换成元,直接除，入库 
	  * @param str1
	  * @return
	  */
	 public static String parseMoneyToC(String str1) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  
		  return String.valueOf(getRoundW(Double.valueOf(str1)/Double.valueOf("100"),2) )  ;
	 }
	 /**
	  *百分比 入库 
	  * @param str1
	  * @return
	  */
	 public static String parseRateToC(String str1) {
		  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
		  
		  return String.valueOf(getRoundW(Double.valueOf(str1),2) )  ;
	 }
	 /**
	  *时长 入库 
	  * @param str1
	  * @return
	  */ 
	 public static String parseDurToC(String  str1) {  
    	  if  (str1==null||str1.toLowerCase().equals("null")||str1.equals("")) return "";
    	  long time=Long.valueOf(str1);
    	   
    	 long minute = 0;  
   
    	 minute = time / 60;
         return   String.valueOf(minute);  
     }  
	 public static String parseHBRate(Object num,Object prenum){
	 
		 if (num==null ||prenum==null||prenum.toString().equals("0")||num.toString().equals("0")||prenum.toString().equals("")||num.toString().equals("") ) return "0";
		 
		 return String.valueOf(getRoundW( (Double.valueOf(num.toString())-Double.valueOf(prenum.toString()))/Double.valueOf(prenum.toString())  ,2) )  ;
		 
		 
	 }
	  public static String convertUtoChina(String utfString){  
		  if (utfString==null||utfString.length()==0) return "";
		   StringBuilder sb = new StringBuilder();  
		   int i = -1;  
		   int pos = 0;  
		     
		   while((i=utfString.indexOf("\\u", pos)) != -1){  
		       sb.append(utfString.substring(pos, i));  
		       if(i+5 < utfString.length()){  
		           pos = i+6;  
		           sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
		       }  
		   }  
		     
		   return sb.toString();  
		  }  

	 
	public static String encodeURLParam(String str){
		try {
			str = URLEncoder.encode(str,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static Integer strToInteger(String str){
		
		Integer object = null;
		if(null != str
				&& 0 < str.length()){
			try{
				object = Integer.parseInt(str);
			}catch(NumberFormatException e){
			}
		}
		return object;
	}
	
	public static int strToInt(String str){
		
		int number = 0;
		if(null != str
				&& 0 < str.length()){
			try{
				number = Integer.parseInt(str);
			}catch(NumberFormatException e){
			}
		}
		return number;
	}

	public static Double strToDouble(String str, double... defVal){
		
		Double object = null;
		if(null != str
				&& 0 < str.length()){
			try{
				object = Double.parseDouble(str);
			}catch(NumberFormatException e){
			}
		}
		if(null == object
				&& null != defVal
				&& 0 < defVal.length){	
			object = defVal[0];
		}
		return object;
	}
	
	
	public static Double formatDouble(Double numeric ,int score){
		
		if(null != numeric){
			BigDecimal bigDecimal = new BigDecimal(numeric);      
			return bigDecimal.setScale(score,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		}else{
			return null;
		}
	}
	
	public static Double formatDouble(String str ,int score){
		
		Double numeric = strToDouble(str);
		if(null != numeric){
			BigDecimal bigDecimal = new BigDecimal(numeric);      
			return bigDecimal.setScale(score,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		}else{
			return null;
		}
	}
} 