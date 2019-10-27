//package org.jeecg.util;
//
//
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
///**
// * Jackson工具类
// *
// * @author suddev
// * @create 2017-11-25 2:18 PM
// **/
//public class JsonUtil {
//
//    private static ObjectMapper objectMapper = new ObjectMapper();
//
//    static {
//        // 对象字段全部列入
//        objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
//
//        // 取消默认转换timestamps形式
//        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//
//        // 忽略空bean转json的错误
//        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
//
//        // 统一日期格式yyyy-MM-dd HH:mm:ss
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//
//        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
//        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }
//
//    //json字符串转map,list,bean
//    public static <T> String objToJsonStrng(T obj) throws IOException {
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    //map,list,bean转json字符串
//    public static <T> T jsonStrngToObj(String jsonString,Class<T> clazz) throws IOException {
//        return objectMapper.readValue(jsonString, clazz );
//    }
//
//
//}