package com.ebook.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 字符串工具
 * @author yunzi7758
 *
 */
public class StringUtil {

	/**
	 * 将类似id1,id2,id3字符串改成'id1','id2','id3'字符串 即sql内的in子句内容
	 * 
	 * @param str
	 * @return
	 */
	public static String addSQuote(String str) {
		String result = "'";
		if (str.indexOf(",") > 0) {
			String tmp = str.replace(",", "','");
			result = result + tmp + "'";
		} else {
			result = result + str + "'";
		}
		return result;
	}

	public static String setToString(Set<?> set, String split) {
		if (set != null && !set.isEmpty()) {
			return listToString(Collections.list(Collections.enumeration(set)),
					split);
		} else {
			return "";
		}
	}

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	public static String listToString(List<?> list, String split) {
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				// 如果值是list类型则调用自己
				if (list.get(i) instanceof List) {
					sb.append(listToString((List<?>) list.get(i), split));
					sb.append(split);
				} else {
					sb.append(list.get(i));
					sb.append(split);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 过滤查询条件值，避免sql注入
	 * 
	 * @param str
	 * @return
	 */
	public static String filterQueryValue(String str) {
		return str.replaceAll(".*([';]+|(--)+).*", " ");
	}
	
	public static String parseFromException(Exception e){
		String result = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		e.printStackTrace(ps);
		result = new String(out.toByteArray());
		return result;
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     

	/**
	   * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
	   *
	   * <pre>
	   * StringUtils.isBlank(null)      = true
	   * StringUtils.isBlank("")        = true
	   * StringUtils.isBlank(" ")       = true
	   * StringUtils.isBlank("bob")     = false
	   * StringUtils.isBlank("  bob  ") = false
	   * </pre>
	   *
	   * @param cs  the CharSequence to check, may be null
	   * @return {@code true} if the CharSequence is null, empty or whitespace
	   * @since 2.0
	   * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	   */
	  public static boolean isBlank(CharSequence cs) {
	    int strLen;
	    if (cs == null || (strLen = cs.length()) == 0) {
	      return true;
	    }
	    for (int i = 0; i < strLen; i++) {
	      if (Character.isWhitespace(cs.charAt(i)) == false) {
	        return false;
	      }
	    }
	    return true;
	  }

	  /**
	   * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
	   *
	   * <pre>
	   * StringUtils.isNotBlank(null)      = false
	   * StringUtils.isNotBlank("")        = false
	   * StringUtils.isNotBlank(" ")       = false
	   * StringUtils.isNotBlank("bob")     = true
	   * StringUtils.isNotBlank("  bob  ") = true
	   * </pre>
	   *
	   * @param cs  the CharSequence to check, may be null
	   * @return {@code true} if the CharSequence is
	   *  not empty and not null and not whitespace
	   * @since 2.0
	   * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
	   */
	  public static boolean isNotBlank(CharSequence cs) {
	    return !isBlank(cs);
	  }

	  /**
	   * <p>Checks if a CharSequence is empty ("") or null.</p>
	   *
	   * <pre>
	   * StringUtils.isEmpty(null)      = true
	   * StringUtils.isEmpty("")        = true
	   * StringUtils.isEmpty(" ")       = false
	   * StringUtils.isEmpty("bob")     = false
	   * StringUtils.isEmpty("  bob  ") = false
	   * </pre>
	   *
	   * <p>NOTE: This method changed in Lang version 2.0.
	   * It no longer trims the CharSequence.
	   * That functionality is available in isBlank().</p>
	   *
	   * @param cs  the CharSequence to check, may be null
	   * @return {@code true} if the CharSequence is empty or null
	   * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
	   */
	  public static boolean isEmpty(CharSequence cs) {
	    return cs == null || cs.length() == 0;
	  }

	  /**
	   * <p>Checks if a CharSequence is not empty ("") and not null.</p>
	   *
	   * <pre>
	   * StringUtils.isNotEmpty(null)      = false
	   * StringUtils.isNotEmpty("")        = false
	   * StringUtils.isNotEmpty(" ")       = true
	   * StringUtils.isNotEmpty("bob")     = true
	   * StringUtils.isNotEmpty("  bob  ") = true
	   * </pre>
	   *
	   * @param cs  the CharSequence to check, may be null
	   * @return {@code true} if the CharSequence is not empty and not null
	   * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
	   */
	  public static boolean isNotEmpty(CharSequence cs) {
	    return !isEmpty(cs);
	  }
	  
	  
	  /**
	   * 首字母转小写
	   * @param s
	   * @return
	   */
	  public static String toLowerCaseFirstOne(String s){
	    if(Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	  }


	  /**
	   * 首字母转大写
	   * @param s
	   * @return
	   */
	  public static String toUpperCaseFirstOne(String s){
	    if(Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	  }
	  
	public static void main(String[] args) {
		try{
			System.out.println(toLowerCaseFirstOne("ADFSD"));
			System.out.println(toUpperCaseFirstOne("dadfad"));
			System.out.println(getRandomString(12));
		}catch(Exception e){
			System.out.println(parseFromException(e));
		}
	}
}
