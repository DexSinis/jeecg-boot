//package org.jeecg.util.huanxin;
//
//import org.jeecg.util.JsonUtil;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class HuanxinUtil {
//
//    //文档地址: http://docs-im.easemob.com/im/server/ready/user#用户体系集成
//
//
////    private String baseUrl = "https://a1.easemob.com";
////    private String orgName = "szzsit";
////    private String appName = "yltest";
////    private String CLIENT_ID = "YXA69MfIcKhNEeWk92XRmo5mpw";
////    private String CLIENT_SECRET = "YXA6YRq2EWDr1E5TMpzgKSlb93blawc";
//
//    private static String baseUrl = "http://a1.easemob.com";
//    private static String orgName = "szzsit";
//    private static String appName = "yltest";
//    private static String CLIENT_ID = "YXA69MfIcKhNEeWk92XRmo5mpw";
//    private static String CLIENT_SECRET = "YXA6YRq2EWDr1E5TMpzgKSlb93blawc";
//
//
//
//    private static String split = "/";
//    private static String bearer = "Bearer ";
//    private static String authorization = "Authorization";
//
//    private static String userUrl = baseUrl + String.format("/%s/%s/users", orgName, appName);
//    private static String tokenUrl = baseUrl + String.format("/%s/%s/token", orgName, appName);
//    private static String messagesUrl = baseUrl + String.format("/%s/%s/messages", orgName, appName);
//
//
//    //获取管理员权限
//    public static Map<String,Object> getToken() {
//
//
//        Map<String, Object> reqData = new HashMap<>();
//        reqData.put("grant_type", "client_credentials");
//        reqData.put("client_id", CLIENT_ID);
//        reqData.put("client_secret", CLIENT_SECRET);
//
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl,HttpMethod.POST, httpEntity, Map.class);
//
//
////        System.out.println("result====================" + response.getBody());
//
//
//        /**
//         * access_token	有效的token字符串
//         expires_in	token 有效时间，以秒为单位，在有效期内不需要重复获取
//         application	当前 App 的 UUID 值
//         */
//
//        return response.getBody();
//    }
//    //注册单个用户(开放 )
//    public static Map<String,Object> register(String username, String password, String nickname) {
//
//        Map<String, Object> reqData = new HashMap<>();
//        reqData.put("username", username);
//        reqData.put("password", password);
//        reqData.put("nickname", nickname);
//
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(userUrl, HttpMethod.POST,httpEntity, Map.class);
//
//        return response.getBody();
//    }
//
//
//    //注册单个用户(授权)
//    public static Map<String,Object> register(String username, String password, String nickname,String token) {
//
//        Map<String, Object> reqData = new HashMap<>();
//        reqData.put("username", username);
//        reqData.put("password", password);
//        reqData.put("nickname", nickname);
//
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(authorization,bearer+token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(userUrl,HttpMethod.POST, httpEntity, Map.class);
//
//
//        return response.getBody();
//    }
//
//    //获取单个用户
//    public static Map<String,Object> getUser(String username, String token) {
//
//        Map<String, Object> reqData = new HashMap<>();
//
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(authorization,token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(userUrl+split+username,HttpMethod.POST, httpEntity, Map.class);
//
//
//        return response.getBody();
//    }
//
//    //删除单个用户
//    public static Map<String,Object> removeUser(String username, String token) {
//
//        Map<String, Object> reqData = new HashMap<>();
//
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(authorization,token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(userUrl+split+username, HttpMethod.DELETE, httpEntity, Map.class);
//
//
//        return response.getBody();
//    }
//    //修改用户密码
//    public static Map<String,Object> updateUserPassword(String username, String password,String token) {
//
//        Map<String, Object> reqData = new HashMap<>();
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(authorization,token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(userUrl+split+username+split+password, HttpMethod.PUT, httpEntity, Map.class);
//
//
//        return response.getBody();
//    }
//    //发送文本消息
//    public static Map<String,Object> sendTextMessage(String targetUsername,String fromUsername, String msg,String token) {
//
//        Map<String, Object> reqData = new HashMap<>();
//        reqData.put("target_type","users");
//        reqData.put("target",new String[]{targetUsername});
//        reqData.put("msg",msg);
//        reqData.put("type","txt");
//        reqData.put("from",fromUsername);
//        String json = null;
//        try {
//            json = JsonUtil.objToJsonStrng(reqData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(authorization,bearer + token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity(json, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(messagesUrl, HttpMethod.POST, httpEntity, Map.class);
//        return response.getBody();
//    }
//
////    public static void main(String[] args) {
////        Map<String,Object> tokenMap = getToken();
////        String str = "%shello%s";
////        str = String.format(str, "aaa", "bbb");
////        System.out.println("str: " + str);
////
////        sendTextMessage("UWpos13428281935", "3", "测试通知", tokenMap.get("access_token").toString());
////
////    }
//
//}
