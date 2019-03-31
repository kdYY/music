package com.ebook.util;


import com.ebook.util.impl.id.NumberId;
import com.ebook.util.impl.id.ObjectId;

import java.util.UUID;

/** 
 * id生成器
 */
public class IdGenerator {

	/**
	 * 获取getObjectId
	 * <pre>IdGenerator.getObjectId() = 5b46c3c4f5edc8f50207321b</pre>
	 * @return
	 */
	public static String getObjectId() {
		return ObjectId.get().toHexString();
	}
	
	/**
	 * 获取getUuId
	 * @return
	 * <pre>IdGenerator.getUuId() = 1a3ab72428a343c59bfc0197f3c90a09</pre>
	 */
	public static String getUuId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getNumberId() {
		return NumberId.getNumberId();
	}
	public static void main(String[] args) {
		System.out.println(IdGenerator.getObjectId());
		System.out.println(IdGenerator.getUuId());
	}
	
}
