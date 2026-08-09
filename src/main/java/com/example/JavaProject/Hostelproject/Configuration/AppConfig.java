package com.example.JavaProject.Hostelproject.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper modelmapper){

        RedisTemplate<String,String> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        template.setStringSerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template ;
    }
}
