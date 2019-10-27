package org.jeecg.util;//package org.jeecg.util;
//
//import javax.servlet.http.HttpSession;
//
///**
// * session工具类
// */
//public class SessionUtil {
//
//    private static int DAY = 86400;
//
//    // 设置登录凭证
//    public static void setLoginUid(HttpSession session, Integer uid, Integer time) {
//        session.setAttribute("uid", uid);
//        if (time == null) {
//            session.setMaxInactiveInterval(DAY * 7);
//        } else {
//            session.setMaxInactiveInterval(time);
//        }
//    }
//
//    // 获取登录凭证
//    public static Integer getLoginUid(HttpSession session) {
//        return (Integer) session.getAttribute("uid");
//    }
//
//    // 登出去掉登录凭证
//    public static void logout(HttpSession session) {
//        session.removeAttribute("uid");
//    }
//
//}
