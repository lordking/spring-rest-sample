package me.leking.web.sample.exception;

import org.springframework.http.HttpStatus;

/**
 * 错误码定义接口。用于全局配置返回的错误码。考虑前后端分离的需求，后端返回的任何错误不做任何说明文字，而是用错误码来表示错误信息。
 * 错误端返回的错误说明只是为了调用API时开发理解使用，而非用于用户显示。
 * Created by jinlei on 2017/4/26.
 */
public interface ErrorCode {

    /**
     * HTTP状态码
     * @return
     */
    public HttpStatus getHttpStatus();


    /**
     * 具体错误原因码
     * @return
     */
    public int getReasonCode();


    /**
     * 错误简要说明
     * @return
     */
    public String getReasonPhrase();
}
