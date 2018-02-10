package me.leking.web.sample.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import me.leking.web.sample.config.ErrorCodeConfig;

/**
 * BindingResult转换成ValidatorRestException
 * Created by jinlei on 2017/4/29.
 */
public final class ValidationExceptionUtils {

    private ValidationExceptionUtils() {
        throw new InstantiationError( "Must not instantiate this class" );
    }

    /**
     * 只将第一个错误抛出异常
     * @param errResult
     * @return
     */
    public static ValidationException build(BindingResult errResult) {

        FieldError fieldError = errResult.getFieldError();

        String code = fieldError.getCode();
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        message = "`" + field + "` " + message;

        ErrorCode errorCode = ErrorCodeConfig.PARAM_INVAILD;
        switch (code) {
            case "NotNull":
                errorCode = ErrorCodeConfig.PARAM_NOTFOUND;
                break;
            case "Length":
                errorCode = ErrorCodeConfig.PARAM_INVAILD_LENGTH;
                break;
            default:
                break;
        }

        return new ValidationException(errorCode, message);
    }
}
