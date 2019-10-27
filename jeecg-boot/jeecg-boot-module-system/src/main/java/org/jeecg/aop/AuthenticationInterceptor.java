package org.jeecg.aop;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.util.jwt.JWTResult;
import org.jeecg.util.jwt.JWTUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("jwt token验证中...");
        log.info("请求URL : " + httpServletRequest.getRequestURL().toString());

        String token = httpServletRequest.getHeader("X-Access-Token");
        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getHeader("webToken");
        }        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        log.info("token: " + token);
        JWTResult jwtResult = JWTUtil.refreshToken(token, JWTUtil.day / 2);


        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        Result result = new Result();

        // 执行认证
        if (token == null) {
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.append(JSONObject.toJSONString(result.errorInterface("无token，请重新登录","")));
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                httpServletResponse.sendError(500);
                return false;
            }
        }
        if (jwtResult.isSuccess()) {
            httpServletRequest.setAttribute("webToken", jwtResult.getToken());
            httpServletRequest.setAttribute("bMark", jwtResult.getBmark());
            httpServletRequest.setAttribute("appId", jwtResult.getAppid());
            httpServletRequest.setAttribute("flag", jwtResult.getFlag());
            httpServletRequest.setAttribute("consulterId", jwtResult.getConsulterId());
        } else {
            log.info("token校验失败，请核对token"+token);
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.append(JSONObject.toJSONString(result.errorInterface("token校验失败，请核对token: "+token,"")));
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                httpServletResponse.sendError(500);
                return false;
            }
        }

        log.info("jwt token--------:"+token);
        log.info("jwt token完毕...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}