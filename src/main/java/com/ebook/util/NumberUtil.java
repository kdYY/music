package com.ebook.util;

import java.math.BigDecimal;

public class NumberUtil {

	/**
	 * 数字字符转BigDecimal
	 * @param str 1->1.0
	 * @return
	 */
	public static BigDecimal str2BigDecimal(String str) {
		return BigDecimal.valueOf(Double.valueOf(str));
	}
	
	public static void main(String[] args) {
		System.out.println(str2BigDecimal("1"));
	}
}
