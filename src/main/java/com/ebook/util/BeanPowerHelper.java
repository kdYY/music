package com.ebook.util;/**
 * Created by chenshaowen on 2018/11/24.
 */

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

import java.lang.ref.WeakReference;

import static org.dozer.loader.api.TypeMappingOptions.mapEmptyString;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;


/**
 * program: optimus-common
 * <p>
 * description: Bean dozer工具类：深度克隆
 * <p>
 * 相对于BeanHelper 功能更强大，支持枚举或其他类型字段的克隆
 *
 * @author: shaowin
 * <p>
 * created on : 2018-11-24 12:37
 **/
public class BeanPowerHelper {

    private static DozerBeanMapper completeMapper = new DozerBeanMapper();

    /**
     * 部分克隆：值为null、空字符串不拷贝
     */
    public static <T> T mapPartOverrider(final Object sources, Class<T> destinationClass) {
        if (sources == null) return null;
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(sources.getClass(), destinationClass, mapNull(false), mapEmptyString(false));
            }
        });
        T dest = mapper.map(sources, destinationClass);
        mapper.destroy();
        weakReference.clear();
        return dest;
    }

    /**
     * 部分克隆：值为null、空字符串不拷贝
     */
    public static void mapPartOverrider(final Object sources, final Object destinations) {
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(sources.getClass(), destinations.getClass(), mapNull(false), mapEmptyString(false));
            }
        });
        mapper.map(sources, destinations);
        mapper.destroy();
        weakReference.clear();
    }

    /**
     * 全部克隆：值为null、空字符串也拷贝
     */
    public synchronized static <T> T mapCompleteOverrider(final Object sources, Class<T> destinationClass) {
        if (sources == null) return null;
        return completeMapper.map(sources, destinationClass);
    }

    /**
     * 全部克隆：值为null、空字符串也拷贝
     */
    public synchronized static void mapCompleteOverrider(final Object sources, final Object destinations) {
        completeMapper.map(sources, destinations);
    }

}


