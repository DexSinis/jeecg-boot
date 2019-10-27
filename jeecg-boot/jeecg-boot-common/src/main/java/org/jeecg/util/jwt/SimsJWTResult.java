package org.jeecg.util.jwt;

import lombok.Data;

/*
jwt token 解析结果
 */
@Data
public class SimsJWTResult {

    private SimsJWTResult() {
    }


    public static SimsJWTResult buildSuccess(String id, String mobilePhone, String version, String simsPassword, Long startTime, Long endTime, String token) {
        return new SimsJWTResult(true, null, token, id, mobilePhone, version, simsPassword, startTime, endTime);
    }

    public static SimsJWTResult buildError(String errorMsg, String errorToken) {
        return new SimsJWTResult(false, errorMsg, errorToken, null, null, null, null, null, null);
    }


    public SimsJWTResult(boolean success, String errorMsg, String token, String id, String mobilePhone, String version, String simsPassword, Long startTime, Long endTime) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.token = token;
        this.id = id;
        this.mobilePhone = mobilePhone;
        this.version = version;
        this.simsPassword = simsPassword;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    private boolean success;//是否解析成功
    private String errorMsg;//错误信息
    private String token;//token
    private String id;//用户id
    private String mobilePhone;//用户手机号码
    private String version;// token的版本号
    private String simsPassword;//第三方平台密钥
    private Long startTime;//生效时间
    private Long endTime;//失效时间

}