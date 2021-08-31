package com.prepared.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.prepared.mapper")
public class MybatisPlusConfig {

}