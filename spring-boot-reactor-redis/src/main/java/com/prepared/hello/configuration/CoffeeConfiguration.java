package com.prepared.hello.configuration;

import com.prepared.hello.domain.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CoffeeConfiguration {

  @Bean
  public ReactiveRedisConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory("localhost", 6379);
  }

  @Bean
  ReactiveRedisOperations<String, Coffee> redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Coffee> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

}