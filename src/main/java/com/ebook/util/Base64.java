package com.ebook.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Base64
 * @author yunzi7758
 *
 */
public class Base64 {
	
	private static final Logger logger = LoggerFactory.getLogger(Base64.class);

	private static char[] base64_code = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '/', };

	private static byte[] index_64 = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
			5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
			-1, -1, -1 };
	
	/**
	 * String中的值加密
	 */
	public static String encodeString(String value) {
		String encode = "";
		byte bytes[];
		try {
			bytes = value.getBytes("utf-8");
			encode = encodebase64(bytes, bytes.length);
		} catch (UnsupportedEncodingException e) {
			logger.error("String中的值加密失败！",e);
		}
		return encode;
	}
	
	/**
	 * 对字符串指定编码加密
	 * @param value 值
	 * @param code 编码
	 * @return 返回加密值
	 */
	public static String encodeString(String value,String code) {
		String encode = "";
		byte bytes[];
		try {
			bytes = value.getBytes(code);
			encode = encodebase64(bytes, bytes.length);
		} catch (UnsupportedEncodingException e) {
			logger.error("对字符串指定编码加密失败！",e);
		}
		return encode;
	}

	/**
	 * String中值解密
	 */
	public static String decodeString(String value) {
		byte bytes[];
		String decode = "";
		try {
			bytes = decodebase64(value, 100);
			decode = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("String中值解密失败！",e);
		}
		return decode;
	}
	/**
	 * String中值解密
	 */
	public static String decodeString(String value,String code) {
		byte bytes[];
		String decode = "";
		try {
			bytes = decodebase64(value, 100);
			decode = new String(bytes, code);
		} catch (UnsupportedEncodingException e) {
			logger.error("String中值解密失败！",e);
		}
		return decode;
	}

	private static String encodebase64(byte d[], int len) throws IllegalArgumentException {
		int off = 0;
		StringBuffer rs = new StringBuffer();
		int c1, c2;

		if (len <= 0 || len > d.length) {
			throw new IllegalArgumentException("Invalid len");
		}

		while (off < len) {
			c1 = d[off++] & 0xff;
			rs.append(base64_code[(c1 >> 2) & 0x3f]);
			c1 = (c1 & 0x03) << 4;
			if (off >= len) {
				rs.append(base64_code[c1 & 0x3f]);
				break;
			}
			c2 = d[off++] & 0xff;
			c1 |= (c2 >> 4) & 0x0f;
			rs.append(base64_code[c1 & 0x3f]);
			c1 = (c2 & 0x0f) << 2;
			if (off >= len) {
				rs.append(base64_code[c1 & 0x3f]);
				break;
			}
			c2 = d[off++] & 0xff;
			c1 |= (c2 >> 6) & 0x03;
			rs.append(base64_code[c1 & 0x3f]);
			rs.append(base64_code[c2 & 0x3f]);
		}
		return rs.toString();
	}

	private static byte char64(char x) {
		if ((int) x < 0 || (int) x > index_64.length) {
			return -1;
		}
		return index_64[(int) x];
	}

	private static byte[] decodebase64(String s, int maxolen) throws IllegalArgumentException {
		StringBuffer rs = new StringBuffer();
		int off = 0, slen = s.length(), olen = 0;
		byte ret[];
		byte c1, c2, c3, c4, o;

		if (maxolen <= 0) {
			throw new IllegalArgumentException("Invalid maxolen");
		}
		while (off < slen - 1 && olen < maxolen) {
			c1 = char64(s.charAt(off++));
			c2 = char64(s.charAt(off++));
			if (c1 == -1 || c2 == -1) {
				break;
			}
			o = (byte) (c1 << 2);
			o |= (c2 & 0x30) >> 4;
			rs.append((char) o);
			if (++olen >= maxolen || off >= slen) {
				break;
			}
			c3 = char64(s.charAt(off++));
			if (c3 == -1) {
				break;
			}
			o = (byte) ((c2 & 0x0f) << 4);
			o |= (c3 & 0x3c) >> 2;
			rs.append((char) o);
			if (++olen >= maxolen || off >= slen) {
				break;
			}
			c4 = char64(s.charAt(off++));
			o = (byte) ((c3 & 0x03) << 6);
			o |= c4;
			rs.append((char) o);
			++olen;
		}

		ret = new byte[olen];
		for (off = 0; off < olen; off++) {
			ret[off] = (byte) rs.charAt(off);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(encodeString("123456789"));
		System.out.println(decodeString("MTIzNDU2Nzg5"));
	}

}
