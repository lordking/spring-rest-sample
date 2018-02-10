package me.leking.web.sample.exception;

import me.leking.web.sample.config.ErrorCodeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局捕获rest controller抛出的异常。
 * Created by jinlei on 2017/4/19.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 抓住所有的未知异常，并转出json格式。未知异常都认为是服务器内部错误。
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("exception class ==> " + ex.getClass().getName());
        log.error("handling exception ==> " + request.getDescription(true));
        log.error("exception message ==> " + ex.getLocalizedMessage());
        ex.printStackTrace();

        RestException rex = new RestException(ErrorCodeConfig.SYSTEM_FAIL);
        ErrorResponse res = new ErrorResponse(rex);

        return new ResponseEntity<>(res, rex.getHttpStatus());
    }

    /**
     * 处理HTTP提交格式不正确，不满足form定义的异常或不合格的ContentType的解析格式
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleRequestArgumentException(Exception ex, WebRequest request) {

        if (log.isDebugEnabled()) {
            log.debug("handling exception ==> " + request.getDescription(true));
        }

        RestException rex = new RestException(ErrorCodeConfig.PARAMS_RESOLUTION_ERROR, ex.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(rex);

        return new ResponseEntity<>(res, rex.getHttpStatus());
    }

    /**
     * 处理JPA的异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {JpaSystemException.class})
    public ResponseEntity<ErrorResponse> handleJpaException(Exception ex, WebRequest request) {

        if (log.isDebugEnabled()) {
            log.debug("handling exception ==> " + request.getDescription(true));
        }

        RestException rex = new RestException(ErrorCodeConfig.PARAMS_RESOLUTION_ERROR, ex.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(rex);

        return new ResponseEntity<>(res, rex.getHttpStatus());
    }

    /**
     * 处理已知的错误。
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException ex) {

        ErrorResponse res = new ErrorResponse(ex);
        return new ResponseEntity<>(res, ex.getHttpStatus());
    }

}
