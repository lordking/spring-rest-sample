package me.leking.web.sample.security;

import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

/**
 * 加密处理
 * Created by jinlei on 2017/5/8.
 */
public class SecurityUtils {

    private SecurityUtils() {
        throw new InstantiationError( "Must not instantiate this class" );
    }

    public static BasePasswordEncoder passwordEncoder() {
       return new PlaintextPasswordEncoder();
    }
}
