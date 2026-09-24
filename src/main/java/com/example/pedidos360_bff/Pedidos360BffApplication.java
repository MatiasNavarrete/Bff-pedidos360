package com.example.pedidos360_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Pedidos360BffApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pedidos360BffApplication.class, args);
	}

}
