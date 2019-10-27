//package org.jeecg.util;
//
//import org.jeecg.config.security.MySecurityUser;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class SpringSecurityUtil {
//
//    public static MySecurityUser getCurrentLoginUser(){
//        MySecurityUser mySecurityUser = (MySecurityUser) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//        return mySecurityUser;
//    }
//}
