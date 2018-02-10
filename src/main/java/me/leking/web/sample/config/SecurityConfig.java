package me.leking.web.sample.config;

import me.leking.web.sample.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import me.leking.web.sample.security.AdminUserDetailsService;
import me.leking.web.sample.security.SecurityUtils;
import me.leking.web.sample.security.RestAuthenticationEntryPoint;


/**
 * 多个安全配置
 * Created by jinlei on 2017/4/21.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/**/*.html", //
                "/css/**", //
                "/js/**", //
                "/i18n/**",//
                "/libs/**",//
                "/img/**", //
                "/webjars/**",//
                "/ico/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //取消跨域限制
            .antMatcher(Constants.URI_API + "/**")
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic().authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(adminUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }


    @Bean
    public BasePasswordEncoder passwordEncoder() {
        return SecurityUtils.passwordEncoder();
    }

}
