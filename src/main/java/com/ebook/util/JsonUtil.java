package com.ebook.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.TreeMap;

/**
 * JsonUtil
 *
 * @author yunzi7758
 */
public class JsonUtil {

    /**
     * map转换成对象
     *
     * @param mapData
     * @param t
     * @return
     */
    public static <T> T map2Bean(Map<String, ?> mapData, T t) {
        return (T) JSON.parseObject(JSON.toJSONString(mapData), t.getClass());
    }

    /**
     * java对象转map
     *
     * @param t
     * @return
     */
    public static <T> Map<String, String> bean2Map(T t) {
        return JSON.parseObject(JSON.toJSONString(t), TreeMap.class);
    }

    /**
     * java对象转jsonString
     *
     * @param t
     * @return
     */
    public static <T> String bean2JsonString(T t) {
        return JSON.toJSONString(t);
    }

    /**
     * json 转java对象
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static <T> T json2Bean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }


    /**
     * json字符串转map
     *
     * @param jsonData
     * @return
     */
    public static Map<String, String> json2Map(String jsonData) {
        return JSON.parseObject(jsonData, TreeMap.class);
    }

    /**
     * map转换成对象
     *
     * @param mapData
     * @param clazz
     * @return
     */
    public static <T> T map2Bean(Map<String, ?> mapData, Class<T> clazz) {
        return (T) JSON.parseObject(JSON.toJSONString(mapData), clazz);
    }
}
