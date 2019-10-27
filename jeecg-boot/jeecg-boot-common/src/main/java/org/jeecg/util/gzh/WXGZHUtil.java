//package org.jeecg.util.gzh;
//
//
//import org.jeecg.util.LocalCache;
//import org.jeecg.util.http.HttpClientUtils;
//import org.jeecg.util.http.HttpResponse;
////import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.security.MessageDigest;
//import java.util.*;
//
////@Slf4j
//public class WXGZHUtil {
//
//
//    public static String gzhApiDomain = "https://api.weixin.qq.com";
//
//    //从缓存获取基本access_token,没有缓存则http获取并缓存起来
//    public static String httpAndCacheGetToken(String appid, String secret) throws IOException {
//        String key = "wx_token";
//        String value = LocalCache.getCache(key);
//        if (value != null) {
//            return value;
//        } else {
//            value = httpGetToken(appid, secret);
//            LocalCache.addCache(key, value, 7000L);
//            return value;
//        }
//    }
//
//    //获取基本access_token
//    public static String httpGetToken(String appid, String secret) throws IOException {
//        String result = null;
//        String url = String.format("%s/cgi-bin/token?appid=%s&secret=%s&grant_type=%s",
//                gzhApiDomain, appid, secret, "client_credential");
////                log.info("url: "+url);
//        HttpResponse httpResponse = HttpClientUtils.doGet(url);
//        return httpResponse.getStringResult();
//    }
//
//    //获取网页授权access_token
//    public static String httpGetWebToken(String code, String appid, String secret) throws IOException {
//        String result = null;
//        String url = String.format("%s/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=%s",
//                gzhApiDomain, appid, secret, code, "authorization_code");
////                log.info("url: "+url);
//        HttpResponse httpResponse = HttpClientUtils.doGet(url);
//        return httpResponse.getStringResult();
//    }
//
//    //获取微信用户信息
//    public static String httpGetWxUserinfo(String accessToken, String openid) {
//        String result = null;
//        String url = String.format("%s/sns/userinfo?access_token=%s&openid=%s&lang=%s",
//                gzhApiDomain, accessToken, openid, "zh_CN");
////                log.info("url: "+url);
//        try {
//            HttpResponse httpResponse = HttpClientUtils.doGet(url);
//            result = httpResponse.getStringResult();
//            return result;
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//            log.error("getAccessToken请求失败");
//            return "";
//        }
//    }
//
//    //从缓存获取基本jssdk Ticket,没有缓存则http获取并缓存起来
//    public static String httpAndCacheGetTicket(String accessToken) throws IOException {
//        String key = "jssdk_ticket";
//        String value = LocalCache.getCache(key);
//        if (value != null) {
//            return value;
//        } else {
//            value = httpGetTicket(accessToken);
//            LocalCache.addCache(key, value, 7000L);
//            return value;
//        }
//    }
//
//    //获取jssdk Ticket
//    public static String httpGetTicket(String accessToken) throws IOException {
//        String result = null;
//        String url = String.format("%s/cgi-bin/ticket/getticket?access_token=%s&type=%s",
//                gzhApiDomain, accessToken, "jsapi");
//        HttpResponse httpResponse = HttpClientUtils.doGet(url);
//        result = httpResponse.getStringResult();
//        return result;
//    }
//
//
//    public static boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
//        String token = "123456";
//        String[] arr = new String[]{token, timestamp, nonce};
////排序
//        Arrays.sort(arr);
////生成字符串
//        StringBuffer content = new StringBuffer();
//        for (int i = 0; i < arr.length; i++) {
//            content.append(arr[i]);
//        }
//
////sha1加密
//        String temp = buildSha1(content.toString());
//        log.info("wx temp:" + temp);
//        log.info("wx signature:" + signature);
////比较
//        return temp.equals(signature);
//    }
//
//    //加密算法
//    public static String buildSha1(String str) {
//        if (str == null || str.length() == 0) {
//            return null;
//        }
//
//        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'a', 'b', 'c', 'd', 'e', 'f'};
//        try {
//            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
//            mdTemp.update(str.getBytes("UTF-8"));
//            byte[] md = mdTemp.digest();
//            int j = md.length;
//            char buf[] = new char[j * 2];
//            int k = 0;
//
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                buf[k++] = hexDigits[byte0 & 0xf];
//            }
//
//            return new String(buf);
//        } catch (Exception e) {
//            return null;
//        }
//
//    }
//
//    public static String buildNonceStr() {
//        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
//    }
//
//    public static String buildTimestamp() {
//        return String.valueOf(System.currentTimeMillis() / 1000);//时间戳
//    }
//
//    public static Map<String, Object> getTicketSignature(String url, String jsapi_ticket) {
//        //3、时间戳和随机字符串
//        String noncestr = buildNonceStr();
//        String timestamp = buildTimestamp();
//
//        TreeMap<String, String> paramMap = new TreeMap<>();
//        paramMap.put("jsapi_ticket", jsapi_ticket);
//        paramMap.put("noncestr", noncestr);
//        paramMap.put("timestamp", timestamp);
//        paramMap.put("url", url);
//
//        //5、将参数排序并拼接字符串
//        String str = buildParamStr(paramMap);
//
//        //6、将字符串进行sha1加密
//        String signature = buildSha1(str);
//        log.info("参数：" + str + "\n签名：" + signature);
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("jsapi_ticket", jsapi_ticket);
//        rs.put("timestamp", timestamp);
//        rs.put("noncestr", noncestr);
//        rs.put("signature", signature);
//        return rs;
//    }
//
//
//    public static String buildParamStr(Map<String, String> treeMap) {
//
//        StringBuffer sb = new StringBuffer();
//        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
//            if (entry.getValue() == null) {
//                continue;
//            }
//            if (sb.length() == 0) {
//                sb.append(entry.getKey() + "=" + entry.getValue());
//            } else {
//                sb.append("&" + entry.getKey() + "=" + entry.getValue());
//            }
//        }
//        return sb.toString();
//    }
//
//
//}
