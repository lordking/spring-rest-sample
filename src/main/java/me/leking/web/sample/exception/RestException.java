package me.leking.web.sample.exception;

import org.springframework.http.HttpStatus;


/**
 * 返回rest格式的异常定义。

 * Created by jinlei on 2017/4/18.
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;
    private final int reasonCode;

    public RestException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());

        this.httpStatus = errorCode.getHttpStatus();
        this.reasonCode = errorCode.getReasonCode();
    }

    public RestException(ErrorCode errorCode, String message) {
        super(message);

        this.httpStatus = errorCode.getHttpStatus();
        this.reasonCode = errorCode.getReasonCode();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    @Override
    public String toString() {
        return "RestException{" +
            "httpStatus=" + httpStatus +
            ", reasonCode=" + reasonCode +
            '}';
    }
}
