package me.leking.web.sample.config;


import me.leking.web.sample.exception.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * 客户端请求返回的错误码定义。此处系统事先定义的统一错误。
 * reasonCode三位数字是1000-9999之间，本处事先定义的错误在100-499之间。在validator自定义的错误，在500-999之间。
 */
public enum ErrorCodeConfig implements ErrorCode {

    /**============ 4xx ============**/

    /**
     * 某参数值校验不正确。一般的错误是，输入的某个具体参数值因为条件约束校验不通过。
     * 如果想更详细地描述校验不正确的原因，可在1000-1999之间定义数字。
     */
    PARAM_INVAILD(HttpStatus.BAD_REQUEST, 1000, "This param is error."),


    /**
     * 长度不正确
     */
    PARAM_INVAILD_LENGTH(HttpStatus.BAD_REQUEST, 1001, "This param is error."),

    /**
     * 该参数的值当校验唯一性时，已经存在。
     */
    PARAM_INVAILD_UNIQUE(HttpStatus.BAD_REQUEST, 1002, "This param's value exists while checking unique."),


    /**
     * 缺少某个参数。一般的错误是，缺少某个必填的参数。
     * 如果想更详细地描述不正确的原因，可在2000-2999之间定义数字。
     */
    PARAM_NOTFOUND(HttpStatus.BAD_REQUEST, 2000, "This param is error."),


    /**
     * 用户登录验证失败。一般是基于BaseAuth，或者获取访问令牌的验证失败。
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1000, "This client is unauthorized."),

    /**
     * 客户端请求的资源禁止被访问。
     */
    FORBIDDEN(HttpStatus.FORBIDDEN, 1000, "This client's request is forbidden."),


    /**
     * 客户端请求的资源不存在。
     */
    NOTFOUND(HttpStatus.NOT_FOUND, 3000, "This resource is not found."),

    /**
     * 客户端提交参数格式不正确。一般的错误是，json格式错误
     */
    PARAMS_RESOLUTION_ERROR(HttpStatus.PRECONDITION_FAILED, 1000, "Resolving is error."),


    /**============ 5xx ============**/

    /**
     * 服务器未能识别的错误，有可能是服务器内部错误，有可能是不能识别的客户端错误。
     * 系统尽量避免此类错误，无论是未识别的客户端错误或服务器错误，都作为服务器内部错误。
     */
    SYSTEM_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "It's a unidentified error or a internal server fails. Please notice the system manager.");


    private final HttpStatus httpStatus;
    private final int reasonCode;
    private final String reasonPhrase;

    /**
     * 错误码定义
     *
     * @param status       http状态码
     * @param reasonCode
     * @param reasonPhrase
     */
    ErrorCodeConfig(HttpStatus httpStatus, int reasonCode, String reasonPhrase) {
        this.httpStatus = httpStatus;
        this.reasonCode = reasonCode;
        this.reasonPhrase = reasonPhrase;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return "ErrorCodeConfig{" +
            "httpStatus=" + httpStatus +
            ", reasonCode=" + reasonCode +
            ", reasonPhrase='" + reasonPhrase + '\'' +
            '}';
    }
}
