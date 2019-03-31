package com.ebook.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5工具类
 */
public class MD5Util {
	
	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	private static final String KEY_MD5 = "MD5";

	private static final String[] STR_DIGITS = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 获取该字符串的MD5值
	 * 
	 * <pre>
	 * 用例:
	 * MD5Util.getMd5ByString("123") = 202cb962ac59075b964b07152d234b70
	 * 
	 * </pre>
	 * @param sourceString
	 * @return 
	 */
	public static String getMd5ByString(String sourceString){
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(KEY_MD5);
			digest.update(sourceString.getBytes());
			byte[] b = digest.digest();
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
            	int i = b[offset];
                if (i < 0) {
					i += 256;
				}
                if (i < 16) {
					buf.append("0");
				}
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
		} catch (Exception e) {
			logger.error("获取该字符串的MD5值失败！",e);
		}
		return result;
	}

	/**
     * 获取该输入流的MD5值
     * @param is
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5ByInputStream(InputStream is) {
        StringBuffer md5 = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			byte[] dataBytes = new byte[1024];

	        int nread = 0;
	        while ((nread = is.read(dataBytes)) != -1) {
	            md.update(dataBytes, 0, nread);
	        }
	        byte[] mdbytes = md.digest();

	        // convert the byte to hex format
	        for (int i = 0; i < mdbytes.length; i++) {
	            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (Exception e) {
			logger.error("获取该输入流的MD5值失败！",e);
		}
        return md5.toString();
    }

    /**
     * 获取该文件的MD5值
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5ByFile(File file) {
    	String md5 = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			md5 = getMD5ByInputStream(fis);
		} catch (FileNotFoundException e) {
			logger.error("获取该文件的MD5值失败！",e);
		}
        return md5;
    }

	/**
	 * 前端jquery.md5.js加密 对应 后端MD5加密
	 * @param strObj
	 * @return
	 */
	public static String getMD5Code(String strObj) {
		try {
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			return byteToString(md.digest(strObj.getBytes()));
		} catch (Exception e) {
			logger.error("前端jquery.md5.js加密 对应 后端MD5加密失败！",e);
		}
		return null;
	}

	/**
	 * 返回形式为数字跟字符串
	 * @param bByte
	 * @return
	 */
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return STR_DIGITS[iD1] + STR_DIGITS[iD2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * @param bByte
	 * @return
	 */
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	

    /**
     * 普通MD5
     *
     * @param input
     * @return
     * @author daniel
     * @time 2016-6-11 下午8:00:28
     * <pre>MD5Util.MD5("123") = 202cb962ac59075b964b07152d234b70</pre>
     */
    /*public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
				hexValue.append("0");
			}
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }*/


    /**
     * 加盐MD5
     *
     * @param password
     * @return	用例
     * @author daniel
     * @time 2016-6-11 下午8:45:04
     * <pre>MD5Util.generate("123") = 977b62f6a24ad40d50c9561505613305e45e44d11020180f</pre>
     */
    public static String generate(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }


    /**
     * 校验加盐后是否和原文一致
     *
     *<pre>MD5Util.verify("123", "023681031e16a77873c39e00911a1fb9268f26938125306f")  = true</pre>
     * @param password
     * @param md5
     * @return
     * @author daniel
     * @time 2016-6-11 下午8:45:39
     */
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }


    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     *  test
     */
    public static void main(String[] args) {
        String plaintext = "123"; // 72204153686e229036d08f4207c05f507a49b4244e133a38
        // root 295850b9fa4034b94061e38da8d760a1b743120268f1c14b

        System.out.println("原始：" + plaintext);
        System.out.println(MD5Util.getMd5ByString("123"));
//        System.out.println("普通MD5后：" + MD5Util.MD5(plaintext));

        String ciphertext = MD5Util.generate(plaintext);
        System.out.println("加盐后MD5：" + ciphertext);
        System.out.println("是否是同一字符串:" + MD5Util.verify(plaintext, ciphertext));

        System.out.println("MD5Util.verify(\""+plaintext+ "\", \""+ ciphertext+"\")  = "+MD5Util.verify(plaintext, ciphertext));
    }

	
}
