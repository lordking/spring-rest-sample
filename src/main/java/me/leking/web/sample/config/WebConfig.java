package me.leking.web.sample.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.leking.web.sample.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Web配置，替代web.xml
 * Created by jinlei on 2017/4/18.
 */

@Configuration
@EnableWebMvc
@ComponentScan(
    basePackageClasses = {Constants.class},
    useDefaultFilters = false,
    includeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = {
                Controller.class,
                RestController.class,
                ControllerAdvice.class
            }
        )
    }
)

public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    //注入将要被json解析的对象
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 加入静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //加入resources/public目录为静态资源目录
        registry.addResourceHandler("/public/**")
            .addResourceLocations("/WEB-INF/resources/public");
    }

    /**
     * 配置异常解析器为json
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(exceptionHandlerExceptionResolver());
    }

    /**
     * 使用缺省配置
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 确定所请求的媒体类型的请求
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    /**
     * 加入JSON解析器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> messageConverters = messageConverters();
        converters.addAll(messageConverters);
    }

    /**
     * 异常处理也加入json解析器
     * @return
     */
    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerExceptionResolver.setMessageConverters(messageConverters());

        return exceptionHandlerExceptionResolver;
    }

    /**
     * JSON序列化配置
     * @return
     */
    private List<HttpMessageConverter<?>>messageConverters(){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        jackson2Converter.setObjectMapper(objectMapper);

        messageConverters.add(jackson2Converter);
        return messageConverters;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {

        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(-1);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");

        return commonsMultipartResolver;
    }

//    /**
//     * 设置common-fileupload。奇怪的是，此处设置没有起到效果。
//     * @return
//     */
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver multipartResolver() throws IOException {
//
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(5242880);//5MB
//
//        return resolver;
//    }
}
