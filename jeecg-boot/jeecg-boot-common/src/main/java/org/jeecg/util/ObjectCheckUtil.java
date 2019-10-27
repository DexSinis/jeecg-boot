package org.jeecg.util;



import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Date: Created in 2019/5/6 19:09
 **/
public class ObjectCheckUtil {
    public static boolean checkObjFieldIsNull(Object obj) {
        boolean flag = false;
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
//                System.out.println(f.getGenericType().toString());
                if (f.getGenericType().toString().equals(
                        "class java.lang.String")) {
                    if (StringUtils.isEmpty((String) f.get(obj))) {
                        return true;
                    }
                } else {
                    if (f.get(obj) == null) {
                        flag = true;
                        return flag;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }



    public static boolean checkNull(String... str) {
        for (String string : str) {
            if (StringUtils.isEmpty(string)) {
                return true;
            }
        }
        return false;
    }


    /**
     * bean 转map
     * @param object
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object o = field.get(object);
                map.put(field.getName(), o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }



    /**
     * map转bean
     * @param
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> bean) {
        T t = null;
        try {
            t = bean.newInstance();
            for (Field field : bean.getDeclaredFields()) {
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    Object object = map.get(field.getName());
                    if (object != null ) {
                        field.set(t, object.toString());
                    }

                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }



    /**
     * map转bean，map中的下划线对应bean中的驼峰转换
     * @param
     * @param <T>
     * @return
     */
    public static <T> T lineMapToBean(Map<String, Object> map, Class<T> bean) {
        try {
            T t = bean.newInstance();
            for (Field field : bean.getDeclaredFields()) {
                field.setAccessible(true);
                //将对象属性转换为下划线模式，匹配map中的key格式
                String lineFieldName = humpToLine(field.getName());
                String lineFieldNameSame=lineFieldName.toUpperCase();
                if (map.containsKey(lineFieldName)) {
                    Object object = map.get(lineFieldName);
                    if (object != null){
                        field.set(t, object.toString());
                    }
                } else if(map.containsKey(lineFieldNameSame)){
                    Object object = map.get(lineFieldNameSame);
                    if (object != null){
                        field.set(t, object.toString());
                    }
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
//            throw new HlcException("下划线map转换为驼峰对象失败");
            return null;
        }
    }

    /**下划线转驼峰*/
    public static String lineToHump(String str){
        str=str.toLowerCase();
        Pattern linePattern = Pattern.compile("_(\\w)");
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            str = str.toLowerCase();
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**驼峰转下划线*/
    public static String humpToLine(String str){
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }




//    public static void  main(String[] args){
//        Map<String,Object> map=new HashMap<>();
//        map.put("reportdefectid","1");
//        map.put("reportId","1");
//        map.put("componentType","1");
//        map.put("bridgeId","1");
//        map.put("projectName","1");
//        try {
//          ReportInfoVo vo1= mapToBean(map, ReportInfoVo.class);
//          System.out.println(vo1.toString());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        ReportDefectInfoVo vo=new ReportDefectInfoVo();
//        vo.setComponentType("1");
//        vo.setDefectRange("1");
//        vo.setDefectType("1");
//        vo.setMaintainSuggest("1");
//        vo.setReportId("1");
//        System.out.println(checkObjFieldIsNull(vo));
//    }

}
