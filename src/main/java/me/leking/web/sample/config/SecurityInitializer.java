package me.leking.web.sample.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 安全管理框架启动器
 * 启动顺序排在AppInitializer
 * Created by jinlei on 2017/4/21.
 */
@Order(1)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
