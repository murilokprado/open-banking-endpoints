package io.openbanking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("io.openbanking")
@EnableFeignClients("io.openbanking")
public class OpenbankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenbankingApplication.class, args);
	}

}
