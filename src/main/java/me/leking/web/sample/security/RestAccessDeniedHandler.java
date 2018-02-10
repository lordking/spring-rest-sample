package me.leking.web.sample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.leking.web.sample.config.ErrorCodeConfig;
import me.leking.web.sample.exception.ErrorResponse;
import me.leking.web.sample.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jinlei on 2017/4/22.
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(AccessDeniedHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        RestException ex = new RestException(ErrorCodeConfig.FORBIDDEN,  accessDeniedException.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(ex);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(ex.getHttpStatus().value());
        response.getOutputStream().println(this.objectMapper.writeValueAsString(res));
    }
}
