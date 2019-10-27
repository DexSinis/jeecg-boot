package org.jeecg.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimsJWTUtil {

    private static String SECRET = "asd3243232fdgfdg%$^^$#";//密钥
    public static long day = 86400000L;
    public static long hour = 3600000L;

    /**
     * 颁发token
     * @param consulterId
     * @param bmark
     * @param flag
     * @param appid
     * @param validTime
     * @return
     */
//    public static SimsJWTResult createTokenOld(Integer consulterId, String bmark, String flag, Integer appid, long validTime) {
//        long startTime = new Date().getTime();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("alg", "HS256");
//        map.put("typ", "JWT");
//
//        long endTime=startTime + validTime;
//        String token = JWT.create()
//                .withHeader(map)//header
//                .withClaim("consulterId", consulterId)
//                .withClaim("bmark", bmark)  //String
//                .withClaim("flag", flag) // int
//                .withClaim("appid", appid) // dyh,fwh
//                .withClaim("startTime", startTime)
//                .withClaim("endTime", endTime)
//                .sign(Algorithm.HMAC256(SECRET));//加密
//        return SimsJWTResult.buildSuccess(consulterId,bmark,flag,appid, startTime, endTime, token);
//    }



    /**
     * 颁发token
     * @param id
     * @param mobilePhone
     * @param version
     * @param simsPassword
     * @param validTime
     * @return
     */
    public static SimsJWTResult createToken(String id, String mobilePhone, String version, String simsPassword, long validTime) {
        long startTime = new Date().getTime();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        long endTime=startTime + validTime;
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("id", id)
                .withClaim("mobilePhone", mobilePhone)  //String
                .withClaim("version", version) // int
                .withClaim("simsPassword", simsPassword) // dyh,fwh
                .withClaim("startTime", startTime)
                .withClaim("endTime", endTime)
                .sign(Algorithm.HMAC256(SECRET));//加密
        return SimsJWTResult.buildSuccess(id,mobilePhone,version,simsPassword, startTime, endTime, token);
    }







    /**
     *验证token是否过期方法
     * @param token
     * @return
     */
    public static SimsJWTResult verifyToken(String token) {
        return verifyToken(token, 0L, 0L, false, false);
    }

    /**
     * 验证token是否过期,密码修改影响,新token颁发影响
     * @param token
     * @param updatePasswordTime 密码修改时间毫秒
     * @param lastCreateTokenTime 最后一次颁发token时间
     * @return
     */
    public static SimsJWTResult verifyToken(String token, long updatePasswordTime, long lastCreateTokenTime) {
        return verifyToken(token, updatePasswordTime, lastCreateTokenTime, true, true);
    }

//    /**
//     *
//     * @param token 需验证token
//     * @param updatePasswordTime 最近修改密码时间
//     * @param lastCreateTokenTime 最近创建token时间
//     * @param verifyPassword 是否验证修改密码导致旧token失效
//     * @param verifyOldToken 是否验证新token颁发导致旧token失效
//     * @return
//     */
//    public static SimsJWTResult verifyTokenOld(String token, long updatePasswordTime, long lastCreateTokenTime, boolean verifyPassword, boolean verifyOldToken) {
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
//                .build();
//        DecodedJWT jwt = null;
//        try {
//            jwt = verifier.verify(token);
//        } catch (Exception e) {
//            return SimsJWTResult.buildError("token无效", token);
//        }
//        Map<String, Claim> claims = jwt.getClaims();
//
//        Integer consulterId = claims.get("consulterId").asInt();
//        String bmark = claims.get("bmark").asString();
//        String flag = claims.get("flag").asString();
//        Integer appid = claims.get("appid").asInt();
//        Long startTime = claims.get("startTime").asLong();
//        Long endTime = claims.get("endTime").asLong();
//        if (consulterId == null || bmark == null || flag == null || appid == null || startTime == null || endTime == null) {
//            return SimsJWTResult.buildError("token无效", token);
//        }
//
//        if (verifyPassword && startTime < updatePasswordTime) {
//            return SimsJWTResult.buildError("token无效,密码已被修改,需重新授权", token);//处理密码被修改后,需重新登录需求
//        }
//        if (verifyOldToken && startTime < lastCreateTokenTime) {
//            return SimsJWTResult.buildError("token无效,不是最新token", token);//处理新token颁发后旧token失效需求
//        }
//        if (endTime < new Date().getTime()) {
//            return SimsJWTResult.buildError("token过期", token);
//        }
//
//        return SimsJWTResult.buildSuccess(consulterId,bmark,flag,appid, startTime, endTime, token);
//
//    }
//



    /**
     *
     * @param token 需验证token
     * @param updatePasswordTime 最近修改密码时间
     * @param lastCreateTokenTime 最近创建token时间
     * @param verifyPassword 是否验证修改密码导致旧token失效
     * @param verifyOldToken 是否验证新token颁发导致旧token失效
     * @return
     */
    public static SimsJWTResult verifyToken(String token, long updatePasswordTime, long lastCreateTokenTime, boolean verifyPassword, boolean verifyOldToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return SimsJWTResult.buildError("token无效", token);
        }
        Map<String, Claim> claims = jwt.getClaims();
        String id = claims.get("id").asString();
        String mobilePhone = claims.get("mobilePhone").asString();
        String version = claims.get("version").asString();
        String simsPassword = claims.get("simsPassword").asString();
        Long startTime = claims.get("startTime").asLong();
        Long endTime = claims.get("endTime").asLong();
        if (id == null || mobilePhone == null || version == null || simsPassword == null || startTime == null || endTime == null) {
            return SimsJWTResult.buildError("token无效", token);
        }

        if (verifyPassword && startTime < updatePasswordTime) {
            return SimsJWTResult.buildError("token无效,密码已被修改,需重新授权", token);//处理密码被修改后,需重新登录需求
        }
        if (verifyOldToken && startTime < lastCreateTokenTime) {
            return SimsJWTResult.buildError("token无效,不是最新token", token);//处理新token颁发后旧token失效需求
        }
        if (endTime < new Date().getTime()) {
            return SimsJWTResult.buildError("token过期", token);
        }

        return SimsJWTResult.buildSuccess(id,mobilePhone,version,simsPassword, startTime, endTime, token);

    }



    /**
     * token刷新方法
     * @param token
     * @param addValidTime 刷新增加有效时间(毫秒)
     * @return
     */
    public static SimsJWTResult refreshToken(String token, long addValidTime) {
        SimsJWTResult jwtResult = verifyToken(token);//挑选自己的验证方法
        if (!jwtResult.isSuccess()) {
            return jwtResult;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");



        String id = jwtResult.getId();
        String mobilePhone = jwtResult.getMobilePhone();
        String version = jwtResult.getVersion();
        String simsPassword = jwtResult.getSimsPassword();
        long endTime= jwtResult.getEndTime() + addValidTime;
        long startTime= jwtResult.getStartTime();

        String neWtoken = JWT.create()
                .withHeader(map)//header
                .withClaim("id", id)//保留原来结束时间
                .withClaim("mobilePhone", mobilePhone)//保留原来结束时间
                .withClaim("version", version)//保留原来结束时间
                .withClaim("simsPassword", simsPassword)//保留原来结束时间
                .withClaim("startTime",startTime)//保留原来创建时间
                .withClaim("endTime", endTime)//结束时间延长
                .sign(Algorithm.HMAC256(SECRET));//加密
        return SimsJWTResult.buildSuccess(id,mobilePhone,version,simsPassword, startTime, endTime, neWtoken);

    }

    /**
     * token刷新方法
     * @param token
     * @return
     */
    public static SimsJWTResult notRefreshToken(String token) {
        SimsJWTResult jwtResult = verifyToken(token);//挑选自己的验证方法
        return jwtResult;
    }


}
