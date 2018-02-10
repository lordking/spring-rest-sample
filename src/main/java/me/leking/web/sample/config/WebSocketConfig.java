package me.leking.web.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


/**
 * Created by jinlei on 2017/6/1.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //允许跨域访问websocket
        registry.addEndpoint("/socket-js-port").setAllowedOrigins("*").withSockJS();//提供给js客户端连接
        registry.addEndpoint("/socket-port").setAllowedOrigins("*");//提供给原生客户端连接
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
}
