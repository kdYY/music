package com.ebook.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by VectorHo on 16/2/23.
 * <p>
 * Bean工具类: 深度克隆 etc.
 *
 * 不支持枚举类型字段克隆
 */
public class BeanHelper {

    private static final String DATE_DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String[] DATE_PARSE_PATTERNS = new String[]{"yyyy-MM-dd", DATE_DEFAULT_PATTERN, "yyyy-MM-dd HH:mm"};
    protected static Logger logger = LoggerFactory.getLogger(BeanHelper.class);


    private BeanHelper() {
    }

    // 对象克隆, 非覆盖dest成员变量 -> PUT
    @Deprecated
    public static <T> T mapPartOverrider(Object dest, Object orig) {
        return BeanHelper.copyProperties(dest, orig, true);
    }

    public static <T> T mapPartOverriderV2(Object orig, Object dest) {
        return BeanHelper.copyProperties(dest, orig, true);
    }

    // 对象克隆, 覆盖dest成员变量 -> POST GET
    @Deprecated
    public static <T> T mapCompleteOverrider(Object dest, Object orig) {
        return BeanHelper.copyProperties(dest, orig, false);
    }

    public static <T> T mapCompleteOverriderV2(Object orig, Object dest) {
        return BeanHelper.copyProperties(dest, orig, false);
    }

    public static <T> T copyProperties(Object dest, Object orig) {
        return BeanHelper.copyProperties(dest, orig, false);
    }

    // 对象深度克隆
    private static <T> T copyProperties(Object dest, Object orig, boolean no_overrider) {
        Map<String, BeanField> origFieldMap = new HashMap<String, BeanField>();
        Map<String, BeanField> destFieldMap = new HashMap<String, BeanField>();
        if (orig == null || dest == null) {
            return (T) dest;
        }
        origFieldMap.clear();
        destFieldMap.clear();

        if (orig instanceof Map) {// Is Map and Not cache
            Map origMap = (Map) orig;
            for (Entry entry : (Set<Entry>) origMap.entrySet()) {
                String name = entry.getKey().toString();
                BeanField beanField = BeanField.getBeanField(name, origMap);
                if (beanField != null) {
                    origFieldMap.put(name, beanField);
                }
            }
        } else {// Is Object
            Class<?> clazz = orig.getClass();
            while (clazz != null && clazz != Object.class) {
                Field[] origFields = clazz.getDeclaredFields();
                for (Field f : origFields) {
                    BeanField beanField = BeanField.getBeanField(f, orig, true);
                    if (beanField != null && !origFieldMap.containsKey(f.getName())) {
                        origFieldMap.put(f.getName(), beanField);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }

        if (dest instanceof Map) {// Is Map and Not cache
            for (Entry<String, BeanField> entry : origFieldMap.entrySet()) {
                BeanField beanField = BeanField.copyBeanField(entry.getValue(), (Map) dest);
                if (beanField != null) {
                    destFieldMap.put(entry.getKey(), beanField);
                }
            }
        } else {// Is Object
            Class<?> clazz = dest.getClass();
            while (clazz != null && clazz != Object.class) {
                Field[] destFields = clazz.getDeclaredFields();
                for (Field f : destFields) {
                    BeanField beanField = BeanField.getBeanField(f, dest, no_overrider); // orig -> dest 成员变量非覆盖式
                    if (beanField != null && !destFieldMap.containsKey(f.getName())) {
                        destFieldMap.put(f.getName(), beanField);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }

        // 克隆赋值
        for (Entry<String, BeanField> entry : origFieldMap.entrySet()) {
            BeanField origField = entry.getValue();
            BeanField destField = destFieldMap.get(entry.getKey());
            // 检查类型
            if (destField == null || origField == null) {
                continue;
            }
            if (!(origField.getClazz() instanceof Class && destField.getClazz() instanceof Class)) {
                continue;
            }
            if (!no_overrider && origField.getClazz() == destField.getClazz()) {
                destField.setValue(origField.getValue());
            } else if (origField.getClazz() == destField.getClazz()) {
                Class<?> destClazz = (Class<?>) destField.getClazz();
                Class<?> origClazz = (Class<?>) origField.getClazz();

                if ((destClazz.isPrimitive() || Number.class.isAssignableFrom(destClazz)) && (origClazz.isPrimitive() || Number.class.isAssignableFrom(origClazz) || CharSequence.class.isAssignableFrom(origClazz))) { // primitive type

                    boolean isString = CharSequence.class.isAssignableFrom(origClazz);
                    Object value = origField.getValue();

                    if (destField.getValue() != null && no_overrider && value == null) {
                        logger.debug("不覆盖此属性. {}", destField.getName());
                    } else {
                        if (value == null) { /* if orig value is null */
                            if (destClazz.isPrimitive()) {
                                destField.setValue((byte) 0);
                            } else {
                                destField.setValue(null);
                            }
                        } else { /* if orig value is not null */
                            if (destClazz == Double.class || destClazz == double.class) {
                                if (isString) {
                                    destField.setValue(new Double(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).doubleValue());
                                }
                            } else if (destClazz == Float.class || destClazz == float.class) {
                                if (isString) {
                                    destField.setValue(new Float(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).floatValue());
                                }
                            } else if (destClazz == Long.class || destClazz == long.class) {
                                if (isString) {
                                    destField.setValue(new Long(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).longValue());
                                }
                            } else if (destClazz == Integer.class || destClazz == int.class) {
                                if (isString) {
                                    destField.setValue(new Integer(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).intValue());
                                }
                            } else if (destClazz == Short.class || destClazz == short.class) {
                                if (isString) {
                                    destField.setValue(new Short(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).shortValue());
                                }
                            } else if (destClazz == Byte.class || destClazz == byte.class) {
                                if (isString) {
                                    destField.setValue(new Byte(String.valueOf(value)));
                                } else {
                                    destField.setValue(((Number) value).byteValue());
                                }
                            } else if (destClazz == BigDecimal.class && (origClazz == Double.class || origClazz == double.class || origClazz == Float.class || origClazz == float.class || isString)) {
                                if (isString) {
                                    destField.setValue(BigDecimal.valueOf(new Double(String.valueOf(value))));
                                } else {
                                    destField.setValue(BigDecimal.valueOf(((Number) value).doubleValue()));
                                }

                            } else if (destClazz == BigDecimal.class) {
                                if (isString) {
                                    destField.setValue(BigDecimal.valueOf(new Double(String.valueOf(value))));
                                } else {
                                    destField.setValue(BigDecimal.valueOf(((Number) value).longValue()));
                                }
                            } else {
                                logger.debug(destField + " Can't set Unknowed Number value!");
                            }
                        } /* END if value is not null */
                    }
                } else if (destClazz == boolean.class || destClazz == Boolean.class) { // Object to Boolean
                    Object value = origField.getValue();
                    if (destField.getValue() != null && no_overrider && value == null) {
                        logger.debug("不覆盖此属性. {}", destField.getName());
                    } else {
                        if (origClazz == boolean.class || origClazz == Boolean.class) {
                            destField.setValue(value);
                        } else {
                            destField.setValue(Boolean.valueOf(String.valueOf(value)));
                        }
                    }
                } else if (Date.class.isAssignableFrom(destClazz) && Date.class.isAssignableFrom(origClazz)) { // Date to Date
                    Date value = origField.getValue();
                    if (destField.getValue() != null && no_overrider && value == null) {
                        logger.debug("不覆盖此属性. {}", destField.getName());
                    } else {
                        if (java.sql.Date.class == destClazz) {
                            destField.setValue(value == null ? null : new java.sql.Date(value.getTime()));
                        } else {
                            destField.setValue(value == null ? null : value);
                        }
                    }
                } else if (Date.class.isAssignableFrom(destClazz) && CharSequence.class.isAssignableFrom(origClazz)) { // String to Date
                    CharSequence value = origField.getValue();
                    if (destField.getValue() != null && no_overrider && value == null) {
                        logger.debug("不覆盖此属性. {}", destField.getName());
                    } else {
                        Date parseDate = null;
                        try {
                            parseDate = org.apache.commons.lang3.time.DateUtils.parseDate(value.toString(), DATE_PARSE_PATTERNS);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                        if (value == null) {
                            destField.setValue(null);
                        } else if (java.sql.Date.class == destClazz) {
                            destField.setValue(new java.sql.Date(parseDate.getTime()));
                        } else {
                            destField.setValue(parseDate);
                        }
                    }
                } else if (String.class.isAssignableFrom(destClazz) && Date.class.isAssignableFrom(origClazz)) { // Date to String
                    Date value = origField.getValue();
                    // 非覆盖模式: 目标有值且非覆盖式true的情况
                    destField.setValue(destField.getValue() != null && no_overrider && value == null ? String.valueOf(destField.getValue()) : DateFormatUtils.format(value, DATE_DEFAULT_PATTERN));
                } else if (String.class.isAssignableFrom(destClazz)) { // Object to String
                    Object value = (Object) origField.getValue();
                    // 非覆盖模式: 目标有值且非覆盖式true的情况
//                    destField.setValue(destField.getValue() != null && no_overrider && value == null ? String.valueOf(destField.getValue()) : String.valueOf(value));
                    if (destField.getValue() != null) {//destValue!=null
                        destField.setValue(no_overrider && value == null ? String.valueOf(destField.getValue()+"") : (value == null ? null : String.valueOf(value)));
                    } else {
                        destField.setValue(no_overrider && value == null ? null : (value == null ? null : String.valueOf(value)));
                    }
                    // Custom complex type
                } else if ((destClazz.getName().indexOf("java.") > -1 || destClazz.getName().indexOf("javax.") > -1) && (origClazz.getName().indexOf("java.") > -1 || origClazz.getName().indexOf("javax.") > -1)) {
                    logger.debug("Not support the complex type:" + destField + ", " + origField);
                } else {
                    logger.debug("Type is not match: " + destField + ", " + origField);
                }
            } /* END origField.getClazz() != destField.getClazz() */
        }
        return (T) dest;
    }

    private static class BeanField {
        private String name;
        private Object value;
        private Class<?> clazz;

        private Field field = null;
        private Object instance = null;

        private BeanField() {
        }

        static BeanField getBeanField(Field field, Object instance, boolean withValue) {
            if ((field.getModifiers() & Modifier.STATIC) != 0) {
                return null;
            }
            BeanField bf = new BeanField();
            {
                bf.name = field.getName();
                bf.clazz = field.getType();
                if (withValue) { // 带上值
                    try {
                        if (field.isAccessible()) {
                            bf.value = field.get(instance);
                        } else {
                            field.setAccessible(true);
                            bf.value = field.get(instance);
                            field.setAccessible(false);
                        }
                    } catch (Exception e) {
                        logger.error("BeanHelper.getBeanField exception: ", e);
                    }
                }
                bf.field = field;
                bf.instance = instance;
            }
            return bf;
        }

        static BeanField getBeanField(String name, Map instance) {
            BeanField bf = new BeanField();
            {
                bf.name = name;
                try {
                    bf.value = instance.get(name);
                    if (bf.value == null) {
                        bf.clazz = Object.class;
                    } else {
                        bf.clazz = bf.value.getClass();
                    }
                } catch (Exception e) {
                    logger.error("BeanHelper.getBeanField exception: ", e);
                }
                bf.field = null;
                bf.instance = instance;
            }
            return bf;
        }

        static BeanField copyBeanField(BeanField field, Map instance) {
            BeanField bf = new BeanField();
            {
                bf.name = field.name;
                bf.clazz = field.getClazz();
                bf.field = null;
                bf.instance = instance;
            }
            return bf;
        }

        public <T> T getValue() {
            return (T) value;
        }

        public void setValue(Object value) {
            if (field != null && instance != null) {
                try {
                    if (field.isAccessible()) {
                        field.set(instance, value);
                    } else {
                        field.setAccessible(true);
                        field.set(instance, value);
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    logger.error("Can't setting the value: " + field + ", value=" + value);
                }
            } else {
                ((Map) instance).put(name, value);
            }
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public String toString() {
            return "BeanField [clazz=" + clazz + ", field=" + field + ", instance=" + instance + ", name=" + name + ", value=" + value + "]";
        }
    }

    private static class A {
        private int a = 1; // 继承的成员变量

        void print() {
            System.out.println(getClass());
        }
    }


    private static class B extends A {
        private String b;
        private String c = null;
        private Long d = 10l;
        private int e = 0;
        private Date f = new Date();
        private Double g = 111.2D;
        private String h = "2015-01-02";
        private Date i = new Date();
        private Long j = 112L;
        // String to Number
        private String k = "kkkk";
        // Other
        private Long l = 112L;
        private java.sql.Date m = new java.sql.Date(new Date().getTime());

    }

    private static class C {
        private int a = 10;

        private String b = "bb";
        private String c = "cc";

        private Long d;
        private int e = 50;
        private Date f = new Date();
        private Double g = 111.1D;
        private String h = "2015-01-01";
        private Date i = new Date();
        private Long j = 111L;
        // String to Number
        private String k;
        // Other
        private Long l = 111L;
        private java.sql.Date m = new java.sql.Date(new Date().getTime());

        @Override
        public String toString() {
            return "C.class [a= " + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + ", i=" + i + ", j=" + j + ", k=" + k + ", l=" + l + ", m=" + m + "]";
        }
    }
}
