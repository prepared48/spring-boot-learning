package com.prepared.SpringBootUnitTest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.prepared.SpringBootUnitTest.mapper")
@SpringBootApplication
public class SpringBootUnitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUnitTestApplication.class, args);
	}

}
