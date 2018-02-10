package me.leking.web.sample.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Created by jinlei on 2017/5/16.
 */
@Configuration
@EnableSwagger2
@Profile(value = {"dev", "staging"})
public class SwaggerConfig {

    @Bean
    public Docket getApiInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(paths())
            .build();
    }

    private Predicate<String> paths() {
        return or(
            regex("/api/blog.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("SpringMVC Example API")
            .description("SpringMVC Example API reference for developers")
            .termsOfServiceUrl("http://leking.me")
            .contact(new Contact("Leo King", "http://www.leking.me", "lordking@163.com"))
            .license("MIT")
            .licenseUrl("https://github.com/lordking/spring-rest-sample/LICENSE")
            .version("1.0")
            .build();
    }


}
