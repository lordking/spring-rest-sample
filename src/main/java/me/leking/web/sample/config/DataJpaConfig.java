package me.leking.web.sample.config;

import me.leking.web.sample.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jinlei on 2017/4/20.
 */
@Configuration
@EnableJpaRepositories(basePackages = {Constants.BASE_PACKAGE})
public class DataJpaConfig {


}
