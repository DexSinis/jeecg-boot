package org.jeecg.util;

public class UrlParamUtil {

    private static final String flag1 = "#";
    private static final String flag2 = "?";
    private static final String flag3 = "&";
    private static final String flag4 = "=";

    public static String getUrlParam(String url, String paramName) {
        String result = null;
        if (url == null || paramName == null) {
            throw new NullPointerException();
        }
        int index = url.indexOf(flag1);
        if (index != -1) {
            url = url.substring(0, index);
        }
        index = url.indexOf(flag2);
        if (index != -1) {
            url = url.substring(index+1);
        }

        String[] paramStr = url.split(flag3);
        for (String param : paramStr) {
            String[] keyValue = param.split(flag4);
            if (keyValue.length > 1) {
                String key = keyValue[0];
                if (key.equals(paramName)) {
                    result = keyValue[1];
                    return result;
                }
            }

        }


        return result;
    }


//    public static void main(String[] args) {
//        String url = "http://sfds.dsfsdf.sdfds?v=1&a=2&b=3&u=f4#dsfsd";
//        String value = getUrlParam(url, "u");
//        System.out.println("value: " + value);
//    }
}
