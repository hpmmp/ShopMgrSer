package com.shop.utils;

/**
 * 
 * @author zhangchuyan
 * @date 2016-04-13
 */

public class StringHelper {

	public static boolean isBlank(String str){
	    int strLen;
	    if((null == str) || (0 == (strLen = str.length())) || "null".equals(str))
	      return true;
	    
	    for(int i = 0; i < strLen; i++){
	      if(!Character.isWhitespace(str.charAt(i))){
	        return false;
	      }
	    }
	    return true;
	}

	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	public static Float strToFloat(String str, float... defVal){
		
		Float object = null;
		if(null != str
				&& 0 < str.length()){
			try{
				object = Float.parseFloat(str);
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
}
