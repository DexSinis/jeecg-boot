package org.jeecg.util.jwt;

/*
jwt token 解析结果
 */
public class JWTResult {

    private JWTResult() {
    }

    public static JWTResult buildSuccess(Integer consulterId, String bmark, String flag, Integer appid, Long startTime, Long endTime, String token) {
        return new JWTResult(true, null,token,consulterId, bmark,flag,appid,startTime, endTime);
    }

    public static JWTResult buildError(String errorMsg, String errorToken) {
        return new JWTResult(false,errorMsg,errorToken,null,null,null,null,null,null);
    }

    public JWTResult(boolean success, String errorMsg, String token, Integer consulterId, String bmark, String flag, Integer appid, Long startTime, Long endTime) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.token = token;
        this.consulterId = consulterId;
        this.bmark = bmark;
        this.flag = flag;
        this.appid = appid;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean success;//是否解析成功
    private String errorMsg;//错误信息
    private String token;//token
    private Integer consulterId;//用户id
    private String bmark;//用户id
    private String flag;//用户id
    private Integer appid;//用户id
    private Long startTime;//生效时间
    private Long endTime;//失效时间

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public String getBmark() {
        return bmark;
    }

    public void setBmark(String bmark) {
        this.bmark = bmark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
