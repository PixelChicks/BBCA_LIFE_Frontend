package com.bbcalifeFrontend.bbcalifeFrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BbcalifeFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbcalifeFrontendApplication.class, args);
	}

}
