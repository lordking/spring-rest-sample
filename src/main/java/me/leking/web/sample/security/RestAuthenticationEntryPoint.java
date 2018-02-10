package me.leking.web.sample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.leking.web.sample.exception.ErrorResponse;
import me.leking.web.sample.exception.RestException;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import me.leking.web.sample.config.ErrorCodeConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败返回json
 * Created by jinlei on 2017/4/22.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        RestException ex = new RestException(ErrorCodeConfig.UNAUTHORIZED);
        ErrorResponse res = new ErrorResponse(ex);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(ex.getHttpStatus().value());
        response.getOutputStream().println(this.objectMapper.writeValueAsString(res));

    }
}
