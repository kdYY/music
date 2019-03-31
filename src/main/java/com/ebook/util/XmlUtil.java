package com.ebook.util;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * XmlUtil
 * @author yunzi7758
 *
 */
public class XmlUtil {

	/**
     * xml 转map
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static Map<String, String> xml2Map(String xml) throws Exception {
        Document document = DocumentHelper.parseText(xml);
        //添加节点信息
        Element rootElement = document.getRootElement();
        Map<String, String> simMap = new TreeMap<>();
        Iterator it = rootElement.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            simMap.put(element.getName(), element.getText());
        }
        return simMap;
    }

    /**
     * xml 转bean
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static <T> T xml2Bean(String xml, T t) throws Exception {
        return (T) JSON.parseObject(JSON.toJSONString(xml2Map(xml)), t.getClass());
    }
}
