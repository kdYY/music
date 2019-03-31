package com.ebook.util;

import java.util.*;

/**
 * ClassName: CollectionUtil <br/>
 * Function: 有关集合处理的工具类，通过静态方法消除泛型编译警告。
 * 
 */
public class CollectionUtil {

    /**
     * 判断<code>Map</code>是否为<code>null</code>或空<code>{}</code>
     * 
     * @param map
     *            ## @see Map
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null) || (map.size() == 0);
    }

    /**
     * 判断Map是否不为<code>null</code>和空<code>{}</code>
     * 
     * @param map
     *            ## @see Map
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return (map != null) && (map.size() > 0);
    }

    /**
     * 判断<code>Collection</code>是否为<code>null</code>或空数组<code>[]</code>。
     * 
     * @param collection
     * @see Collection
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null) || (collection.size() == 0) || (collection.iterator()==null) || (collection.iterator().next()==null);
    }

    /**
     * 判断Collection是否不为<code>null</code>和空数组<code>[]</code>。
     * 
     * @param collection
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null) && (collection.size() > 0) && (collection.iterator()!=null) && (collection.iterator().next()!=null);
    }

    /**
     * 创建<code>ArrayList</code>实例
     * 
     * @param <E>
     * @return <code>ArrayList</code>实例
     */
    public static <E> ArrayList<E> createArrayList() {
        return new ArrayList<E>();
    }

    /**
     * 创建<code>ArrayList</code>实例
     * 
     * @param <E>
     * @param initialCapacity
     *            初始化容量
     * @return <code>ArrayList</code>实例
     */
    public static <E> ArrayList<E> createArrayList(int initialCapacity) {
        return new ArrayList<E>(initialCapacity);
    }

    /**
     * 创建<code>HashMap</code>实例
     * 
     * @param <K>
     * @param <V>
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap() {
        return new HashMap<K, V>();
    }

    /**
     * 创建<code>HashMap</code>实例
     * 
     * @param <K>
     * @param <V>
     * @param initialCapacity
     *            初始化容量
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap(int initialCapacity) {
        return new HashMap<K, V>(initialCapacity);
    }

    /**
     * 创建<code>HashMap</code>实例
     * 
     * @param <K>
     * @param <V>
     * @param initialCapacity
     *            初始化容量
     * @param loadFactor
     *            加载因子
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap(int initialCapacity, float loadFactor) {
        return new HashMap<K, V>(initialCapacity, loadFactor);
    }
    
    /**
     * 将List<List<V>>转为List<V>
     * @param list
     * @return
     */
    public static <V> List<V> convertList(List<List<V>> list){
    	List<V> result = new ArrayList<V>();
    	for(List<V> data:list){
    		result.addAll(data);
    	}
    	return result;
    }

}
