package com.ebook.util;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;
import java.util.Map;

/**
 * java字符串表达式
 * @author yunzi7758
 *
 */
public class StringExpressionUtil {

	
	/**
	 * 执行java字符串表达式
	 * 
	 * @param jexlExp java字符串表达式
	 * @param map 参数
	 * @return
	 */
	public static Object exec(String jexlExp,Map<String,Object> map){  
        JexlEngine jexl=new JexlEngine();  
        Expression e = jexl.createExpression(jexlExp);  
        JexlContext jc = new MapContext();  
        for(String key:map.keySet()){  
            jc.set(key, map.get(key));  
        }  
        if(null==e.evaluate(jc)){  
            return "";  
        }  
        return e.evaluate(jc);  
	}  
	
	
	/**
	 * 根据class执行：clz.methodName(params)
	 * @param clz
	 * @param methodName
	 * @param params
	 * @return
	 */
	public static <T> Object invoke(Class<T> clz,String methodName,Object... params){
		T obj = ReflectionUtil.newInstance(clz);
		return invoke(obj, methodName, params);
	}
	
	/**
	 * 根据对象执行：obj.methodName(params)
	 * @param obj
	 * @param methodName
	 * @param params
	 * @return
	 */
	public static  Object invoke(Object obj,String methodName,Object... params){
		Map<String,Object> map = new HashMap<>();
		String jexlExp = "";
		
		
		map.put("obj", obj);
		jexlExp += "obj."+methodName;
		
		if (params == null || params.length == 0) {
			jexlExp += "()";
		} else {

			jexlExp += "(";
			for (int i = 0; i < params.length; i++) {
				map.put("p"+i, params[i]);
				if (i<params.length-1) {
					jexlExp += "p"+i+",";
				}else {
					jexlExp += "p"+i+")";
				}
			}
		}
		
		
		return exec(jexlExp, map);
	}
}
