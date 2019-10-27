package org.jeecg.util.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param <T>
 * @author Administrator
 * 通用服务返回结果类
 */
//@JsonInclude(Include.NON_NULL)
@Data
@ApiModel(value="ServiceResult", description="接口返回对象")
public class ServiceResult<T> {


    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;
    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;

    @ApiModelProperty(value = "返回处理消息=message")
    private String msg;

    @ApiModelProperty(value = "返回数据对象=result")
    private T data;



    public  ServiceResult<T> errorInterface(String message,T data) {
        this.message = message;
        this.code = 1;
        this.success = true;
        this.result =  data;
        this.data = data;
        return this;

    }

    public  ServiceResult<T> successInterface(String message,T data) {
        this.message = message;
        this.code = 0;
        this.result =  data;
        this.data = data;
        this.success = true;
        return this;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServiceResult() {

    }

    public ServiceResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResult(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResult(int code, String msg, Long count, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildSuccess(T data) {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.SUCCESS, null, data);
    }
    public static <T> org.jeecg.util.response.ServiceResult<T> buildSuccess(String msg,T data) {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.SUCCESS, msg, data);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildSuccess(Integer code,String msg) {
        return new org.jeecg.util.response.ServiceResult<T>(code, msg, null);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildSuccess(T data, Long count) {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.SUCCESS, "", count, data);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildSuccess() {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.SUCCESS, null, null);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildNotLogin() {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.NOT_LOGIN, ServiceResultMsg.NOT_LOGIN_MSG, null);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildError(String msg,T data) {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.ERROR, msg, data);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildError(String msg) {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.ERROR, msg, null);
    }
    public static <T> org.jeecg.util.response.ServiceResult<T> buildError(Integer code,String msg) {
        return new org.jeecg.util.response.ServiceResult<T>(code, msg);
    }

    public static <T> org.jeecg.util.response.ServiceResult<T> buildNotPermission() {
        return new org.jeecg.util.response.ServiceResult<T>(ServiceResultCode.NOT_PERMISSION, ServiceResultMsg.NOT_PERMISSION_MSG, null);
    }

//    @JsonIgnore
    public boolean isSuccess() {
        return ServiceResultCode.SUCCESS == this.code;

    }

    public interface ServiceResultCode {
        int ERROR = 1;
        int SUCCESS = 0;
        int NOT_LOGIN = 10;
        int NOT_PERMISSION = 20;
    }

    public interface ServiceResultMsg {
        String SERVER_ERROR_MSG = "服务器异常";
        String REQUEST_PARAM_ERROR_MSG = "传参错误";
        String VERSION_ERROR_MSG = "该版本不存在或放弃维护";
        String LOGIN_ERROR_MSG = "账号或或密码错误";
        String NOT_LOGIN_MSG = "没有登录";
        String NOT_PERMISSION_MSG = "没有权限";
    }



}
