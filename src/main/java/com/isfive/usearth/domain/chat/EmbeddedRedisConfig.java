package com.isfive.usearth.domain.chat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.io.IOException;

@Profile("test")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.data.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    private void redisServer() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    private void stopRedis(){
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
