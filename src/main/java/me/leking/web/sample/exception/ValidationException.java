package me.leking.web.sample.exception;

/**
 * 校验错误的异常。
 *
 * 因为需要输出ErrorCode定义的错误，所以请不要在表单模型里面使用java和hibernate的validation的注解做表单校验，也不要在自定义的表单校验器
 * validator里使用Errors.reject(...)抛出校验结果。
 *
 * 建议在validator内直接抛出验证异常。
 *
 * Created by jinlei on 2017/4/19.
 */
public class ValidationException extends RestException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    
}
