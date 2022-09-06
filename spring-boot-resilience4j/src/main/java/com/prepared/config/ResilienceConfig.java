package com.prepared.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.ratelimiter.configuration.RateLimiterConfigCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhongshibo
 * @Date: 2022/9/5 19:36
 */
public class ResilienceConfig {

	@Bean
	public CircuitBreakerConfigCustomizer testCustomizer() {
		return CircuitBreakerConfigCustomizer
				.of("backendA", builder -> builder.slidingWindowSize(100));
	}

	@Bean
	public RateLimiterConfigCustomizer rateLimiterConfigCustomizer() {
		return RateLimiterConfigCustomizer
				.of("backendA", builder -> builder.limitForPeriod(1));
	}
}
