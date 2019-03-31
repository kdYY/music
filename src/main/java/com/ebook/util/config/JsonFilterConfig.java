package com.ebook.util.config;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by VectorHo on 16/6/1.
 * <p>
 * JSONView注解工具类.
 */
public class JsonFilterConfig {

    private final static ObjectMapper mapper = new ObjectMapper();


    private JsonFilterConfig() {
    }


    public static Object filteredWriter(Object payload) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        // 获取filterFields
        if (payload == null) {
            return null;
        } else {
            String[] filterFields = getFilterFields(payload);
            // 过滤@JsonPropertiesFilter中的filterFields输出
            if (filterFields == null) {
                filterFields = new String[0];
            }
            FilterProvider filters = new SimpleFilterProvider().addFilter("JsonFilter", SimpleBeanPropertyFilter.serializeAllExcept(filterFields));
            String jsonString = mapper.writer(filters).writeValueAsString(payload); // 扫描@JsonFilter过滤掉@JsonPropertiesFilter(filterFields = {"a", "b"})

            // 判断是一个对象还是一个数组
            if (payload instanceof List || payload.getClass().isArray()) {
                // 将json字符串转成map结合解析出来
                List list = mapper.readValue(jsonString, List.class);
                return list;
            } else if (payload == null || payload.getClass().isPrimitive() || isSimplteObject(payload) || isWrapClass(payload.getClass())) {
                return payload;
            } else {
                // 将json字符串转成map结合解析出来
                Map<String, Map<String, Object>> maps = mapper.readValue(jsonString, Map.class);
                return maps;
            }
        }
    }

    /**
     * 获取filterFields(支持过滤基本数据类型及其封装类，List)
     */
    public static String[] getFilterFields(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        if (obj == null) {
            return new String[0];
        } else {
            StringBuffer filterFields = new StringBuffer();
            Object temp = obj;
            if (obj instanceof List) {
                if (((List) obj).size() == 0) {
                    return new String[0];
                }
                temp = ((List) obj).get(0);
            }
            //基本数据类型直接返回
            if (temp == null || temp.getClass().isPrimitive() || isSimplteObject(temp) || isWrapClass(temp.getClass()) && !temp.getClass().isArray() || temp.getClass() == Object.class || temp instanceof Map) {
                return new String[0];
            }
            JsonFilter filterAnnotation = temp.getClass().getAnnotation(JsonFilter.class);
            if (filterAnnotation == null || !"JsonFilter".equals(filterAnnotation.value())) {
                return new String[0];
            }

            JsonPropertiesFilter clazzAnnotation = temp.getClass().getAnnotation(JsonPropertiesFilter.class);
            if (clazzAnnotation != null) {
                String[] filterFields1 = clazzAnnotation.filterFields();
                if (filterFields1 != null && filterFields1.length > 0) {
                    filterFields.append(filterFields1[0]);
                    for (int i = 1; i < filterFields1.length; i++) {
                        filterFields.append("," + filterFields1[i]);
                    }
                }
            }

            //getDeclaredFields可能存在一些没有get方法的字段
            Method[] allMethods = temp.getClass().getDeclaredMethods();
            List<Method> usefulMethods = new ArrayList<Method>();
            for (Method method : allMethods) {
                if (method.getName().matches("^is.*") || method.getName().matches("^get.*")) {
                    usefulMethods.add(method);
                }
            }
            //循环递归获取其成员变量所对应的实体类中的filterFields
            if (usefulMethods != null && usefulMethods.size() > 0) {
                for (Method method : usefulMethods) {
                    Class m = method.getReturnType();
                    if (m == void.class) continue;
                    if (method.getName().equals("getOrderFor")) continue;
                    Object value = MethodUtils.invokeMethod(temp, method.getName());
                    if (m == List.class) {
                        if (value != null && ((List) value).size() > 0) {
                            m = ((List) value).get(0).getClass();
                            value = ((List) value).get(0);
                        }

                    }
                    if (!m.isPrimitive() && m != temp.getClass() && !isSimplteObject(value) && !isWrapClass(m) && !m.isArray() && m != Object.class && m != Map.class) {//不是基本数据类型或者数组
//                    System.out.println("method="+method.getName()+",returnType="+m);
                        //field包含在filterField当中的就不去扫描了
                        String tempField;
                        if (method.getName().matches("^is.*")) {
                            tempField = method.getName().substring(2);
                        } else {
                            tempField = method.getName().substring(3);
                        }
                        tempField = tempField.substring(0, 1).toLowerCase() + tempField.substring(1);
                        if (value != null && !filterFields.toString().contains(tempField)) {
                            String[] sonFields = getFilterFields(value);
                            if (sonFields != null && sonFields.length > 0) {
                                if (filterFields.length() <= 0) {
                                    filterFields.append(sonFields[0]);
                                } else {
                                    filterFields.append("," + sonFields[0]);
                                }
                                for (int i = 1; i < sonFields.length; i++) {
                                    filterFields.append("," + sonFields[i]);
                                }
                            }
                        }
                    }
                }
            }
            if (filterFields != null && filterFields.length() > 0) {
                return filterFields.toString().split(",");
            } else {
                return new String[0];
            }
        }

    }

    /**
     * 判断一个对象是否为基本数据类型对象(value为空时无法判断)
     */
    public static boolean isSimplteObject(Object o) {
        return (o != null) && (((o instanceof String)) || ((o instanceof Long)) || ((o instanceof Integer)) || ((o instanceof Float)) || ((o instanceof Double)) || ((o instanceof Boolean)) || ((o instanceof Byte)) || ((o instanceof Character)) || ((o instanceof Short)) || (o instanceof Date));
    }

    /**
     * 判断类型是否为基本数据类型的包装类
     */
    public static boolean isWrapClass(Class clzz) {
        try {
            return (((clzz == String.class)) || ((clzz == Long.class)) || ((clzz == Integer.class)) || ((clzz == Float.class)) || ((clzz == Double.class)) || ((clzz == Boolean.class)) || ((clzz == Byte.class)) || ((clzz == Character.class)) || ((clzz == Short.class)) || (clzz == Date.class));
        } catch (Exception e) {
            return false;
        }
    }

}
