package me.leking.web.sample.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.BeanClassLoaderAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.security.jackson2.SecurityJackson2Modules;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 使用redis做session会话配置，不推荐
 * Created by jinlei on 2017/5/26.
 */
//@Configuration
//@EnableRedisHttpSession
//public class HttpSessionConfig implements BeanClassLoaderAware {
//
//    private ClassLoader loader;
//
//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer(objectMapper());
//    }
//
//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    /**
//     * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
//     * constructors
//     *
//     * @return the {@link ObjectMapper} to use
//     */
//    ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
//        return mapper;
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang
//     * .ClassLoader)
//     */
//    public void setBeanClassLoader(ClassLoader classLoader) {
//        this.loader = classLoader;
//    }
//}
