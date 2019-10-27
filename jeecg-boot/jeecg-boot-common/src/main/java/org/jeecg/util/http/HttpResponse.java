package org.jeecg.util.http;

import lombok.Getter;
import lombok.Setter;

/**
 * http请求响应封装
 */
@Getter
@Setter
public class HttpResponse {

    /**
     * http响应状态码
     */
    private int httpStatus;

    /**
     * 请求花费的毫秒数
     */
    private int costsMilliseconds;

    /**
     * String类型的result
     */
    private String stringResult;

    public HttpResponse(int httpStatus, int costsMilliseconds, String stringResult) {
        this.httpStatus = httpStatus;
        this.costsMilliseconds = costsMilliseconds;
        this.stringResult = stringResult;
    }
}
