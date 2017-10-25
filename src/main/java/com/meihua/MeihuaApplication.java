package com.meihua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@MapperScan("com.meihua.domain")
public class MeihuaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeihuaApplication.class, args);
	}
}
