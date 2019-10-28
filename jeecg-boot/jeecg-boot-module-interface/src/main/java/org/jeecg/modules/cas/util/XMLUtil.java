package org.jeecg.modules.cas.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Slf4j
public class XMLUtil {

    /**
     * String 转 org.dom4j.Document
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document strToDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }




    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject xmlToJSONObject(HttpServletRequest request) throws IOException {
        try {

            InputStream is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");                          }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Document document = strToDocument(sb.toString());
            return elementToJSONObject(document.getRootElement());
        } catch (DocumentException e) {
            log.error(e.getMessage());
            return null;
        }

    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject xmlToJSONObject(String xml) {
        try {
            Document document = strToDocument(xml);
            return elementToJSONObject(document.getRootElement());
        } catch (DocumentException e) {
            log.error(e.getMessage());
            return null;
        }

    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     * @param node
     * @return
     */
    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
        for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {// 遍历所有一级子节点
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }

    /**
     * 将Map转换为XML,Map可以多层转
     *
     * @param map        需要转换的map。
     * @param parentName 就是map的根key,如果map没有根key,就输入转换后的xml根节点。
     * @return String-->XML
     */
    @SuppressWarnings("unchecked")
    public static String mapToXml(Map<String, Object> map,
                                  String parentName) {
        //获取map的key对应的value
        Map<String, Object> rootMap = (Map<String, Object>) map.get(parentName);
        if (rootMap == null) {
            rootMap = map;
        }

        Document doc = DocumentHelper.createDocument();
        //设置根节点
        doc.setXMLEncoding("GB2312");
        doc.addElement(parentName);
        String xml = iteratorXml(doc.getRootElement(), parentName, rootMap);
        return (xml);
    }

    /**
     * 循环遍历params创建xml节点
     *
     * @param element    根节点
     * @param parentName 子节点名字
     * @param params     map数据
     * @return String-->Xml
     */
    @SuppressWarnings("unchecked")
    public static String iteratorXml(Element element, String parentName,
                                     Map<String, Object> params) {
        Element e = element.addElement(parentName);
        Set<String> set = params.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            if (params.get(key) instanceof Map) {
                iteratorXml(e, key, (Map<String, Object>) params.get(key));
            } else if (params.get(key) instanceof List) {
                List<Object> list = (ArrayList<Object>) params.get(key);
                for (int i = 0; i < list.size(); i++) {
                    iteratorXml(e, key, (Map<String, Object>) list.get(i));
                }
            } else {
                String value = params.get(key) == null ? "" : params.get(key)
                        .toString();
                e.addElement(key).addText(value);
                // e.addElement(key).addCDATA(value);
            }
        }
        return e.asXML();
    }
//
//    /**
//     * 格式化xml
//     *
//     * @param xml
//     * @return
//     */
//    public static String formatXML(String xml) {
//        String requestXML = null;
//        XMLWriter writer = null;
//        Document document = null;
//        try {
//            SAXReader reader = new SAXReader();
//            document = reader.read(new StringReader(xml));
//            if (document != null) {
//                StringWriter stringWriter = new StringWriter();
//                OutputFormat format = new OutputFormat(" ", true);// 格式化，每一级前的空格
//                format.setNewLineAfterDeclaration(false); // xml声明与内容是否添加空行
//                format.setSuppressDeclaration(false); // 是否设置xml声明头部 false：添加
//                format.setEncoding("GB2312");
//                format.setNewlines(true); // 设置分行
//                writer = new StandaloneWriter(stringWriter, format);
//                writer.write(document);
//                writer.flush();
//                requestXML = stringWriter.getBuffer().toString();
//            }
//            return requestXML;
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            return null;
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//
//                }
//            }
//        }
//    }

}