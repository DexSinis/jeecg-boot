package org.jeecg.aop;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.util.jwt.SimsJWTResult;
import org.jeecg.common.util.jwt.SimsJWTUtil;
import org.jeecg.modules.sims.entity.SimsTokenVersion;
import org.jeecg.modules.sims.service.ISimsTeacherService;
import org.jeecg.modules.sims.service.ISimsTokenVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private ISimsTokenVersionService simsTokenVersionService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("jwt token验证中...");
        log.info("请求URL : " + httpServletRequest.getRequestURL().toString());

        String token = httpServletRequest.getHeader("X-Access-Token");
        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getHeader("token");
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
        Result result = new Result();
        SimsJWTResult jwtResultVersion =  SimsJWTUtil.notRefreshToken(token);
        if(!jwtResultVersion.isSuccess()){
            PrintWriter out = httpServletResponse.getWriter();
            out.append(JSONObject.toJSONString(result.errorInterface("token已经失效，请重新登录","", PetrusConstant.CODE_TYPE.OVER)));
            return false;
        }
        SimsTokenVersion simsTokenVersionQuery = new SimsTokenVersion();
        simsTokenVersionQuery.setId(jwtResultVersion.getId());
        SimsTokenVersion simsTokenVersionRecord = simsTokenVersionService.getOne(new QueryWrapper<SimsTokenVersion>(simsTokenVersionQuery));
        //判断版本号是否和数据库一致，不一致直接返回token失效

        int versionDb = simsTokenVersionRecord.getVersion();
        int versionPre = Integer.valueOf(jwtResultVersion.getVersion());
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("versionDb-------"+versionDb);
        log.error("versionPre-------"+versionDb);
        log.error("versionPre-------versionDb------"+(versionDb!=versionPre));
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("------------------------------------");
        log.error("------------------------------------");

        SimsJWTResult jwtResult = SimsJWTUtil.refreshToken(token, SimsJWTUtil.day / 2);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");


        if(simsTokenVersionRecord!=null&&versionDb!=versionPre){
            PrintWriter out = httpServletResponse.getWriter();
            out.append(JSONObject.toJSONString(result.errorInterface("token已经失效，请重新登录","", PetrusConstant.CODE_TYPE.OVER)));
            return false;
        }

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
            httpServletRequest.setAttribute("token", jwtResult.getToken());
            httpServletRequest.setAttribute("id", jwtResult.getId());
            httpServletRequest.setAttribute("mobilePhone", jwtResult.getMobilePhone());
            httpServletRequest.setAttribute("version", jwtResult.getVersion());
            httpServletRequest.setAttribute("simsPassword", jwtResult.getSimsPassword());
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