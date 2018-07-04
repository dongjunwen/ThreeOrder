package com.three.order.orderrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@ComponentScan("com.three.order")
@EnableRedisHttpSession
public class OrderRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderRestApplication.class, args);
	}
}
