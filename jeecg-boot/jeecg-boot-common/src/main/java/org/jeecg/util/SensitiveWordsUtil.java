package org.jeecg.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SensitiveWordsUtil {

    public static Map<String, Object> rootNode = new HashMap();

//    public static void main(String[] args) {
//
//        Set<String> set = new HashSet<>();
//        set.add("tion");
//        set.add("i");
//        set.add("长沙人民");
//        //build DFA
//        org.jeecg.util.SensitiveWordsUtil.buildOrRefreshDFAData(set);
//        //source
//        String text = "t action 长沙女人 长沙男人 长沙人民 i";
//        Set<String> rs = org.jeecg.util.SensitiveWordsUtil.checkAllSensitiveWords(text);
//        System.out.println("处理完毕 count: " + rs.size());
//        String t = org.jeecg.util.SensitiveWordsUtil.filterSensitiveWords(text);
//        System.out.println("处理完毕 t: " + t);
//    }

    private SensitiveWordsUtil() {

    }


    //构建DFA算法所需数据
    private static void buildOrRefreshDFAData(Set<String> set) {
        Map<String, Object> rootNode = new HashMap();
        for (String str : set) {


            char[] arr = str.toCharArray();
            int len = arr.length;
            Map<String, Object> lastNode = null;
            for (int i = 0; i < len; i++) {
                String wordEl = String.valueOf(arr[i]);
                Map<String, Object> node = new HashMap();
                if (i == 0) {
                    rootNode.put(wordEl, node);
                    lastNode = node;
                } else {
                    lastNode.put(wordEl, node);
                    lastNode = node;
                }
                //加上结束标识
                if ((i + 1) == len) {
                    lastNode.put("end", "1");
                }
            }
        }
        org.jeecg.util.SensitiveWordsUtil.rootNode = rootNode;
    }

    //检测所有敏感字并返回
    public static Set<String> checkAllSensitiveWords(String text) {
        Set<String> set = new HashSet<>();
        Map<String, Object> nextNode = rootNode;
        char[] arr = text.toCharArray();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < arr.length; i++) {
            String str = String.valueOf(arr[i]);
            Map<String, Object> map = (Map<String, Object>) nextNode.get(str);
            if (map != null) {
                sb.append(str);
                String endFlag = (String) map.get("end");
                if (endFlag != null) {
                    set.add(sb.toString());
                    nextNode = rootNode;
                    sb.setLength(0);
                } else {
                    nextNode = map;
                }
            }else {
                nextNode = rootNode;
            }
        }
        return set;
    }

    //检测是否包含敏感字有则true
    public static boolean checkIncludeSensitiveWords(String text) {
        Set<String> set = new HashSet<>();
        Map<String, Object> nextNode = rootNode;
        char[] arr = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String str = String.valueOf(arr[i]);
            Map<String, Object> map = (Map<String, Object>) nextNode.get(str);
            if (map != null) {
                sb.append(str);
                String endFlag = (String) map.get("end");
                if (endFlag != null) {
                    set.add(sb.toString());
                    nextNode = rootNode;
                    sb.setLength(0);
                    return true;
                } else {
                    nextNode = map;
                }
            } else {
                nextNode = rootNode;
            }
        }
        return false;
    }

    //过滤敏感字,返回过滤后的字符串
    public static String filterSensitiveWords(String text) {
        Map<String, Object> nextNode = rootNode;
        char[] arr = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder waitVerifySb = new StringBuilder();
        StringBuilder waitVerifyStarSb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String str = String.valueOf(arr[i]);
            Map<String, Object> map = (Map<String, Object>) nextNode.get(str);
            if (map != null) {
                waitVerifySb.append(str);
                waitVerifyStarSb.append("*");
                String endFlag = (String) map.get("end");
                if (endFlag != null) {
                    nextNode = rootNode;
                    sb.append(waitVerifyStarSb.toString());
                    waitVerifySb.setLength(0);
                    waitVerifyStarSb.setLength(0);
                } else {
                    nextNode = map;
                }
            } else {
                nextNode = rootNode;
                if(waitVerifySb.length()>0){
                    sb.append(waitVerifySb.toString());
                    waitVerifySb.setLength(0);
                    waitVerifyStarSb.setLength(0);
                }
                    sb.append(str);


            }
        }
        return sb.toString();
    }

}
