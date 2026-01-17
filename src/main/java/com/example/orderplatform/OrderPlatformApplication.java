package com.example.orderplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class OrderPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderPlatformApplication.class, args);
	}

}
