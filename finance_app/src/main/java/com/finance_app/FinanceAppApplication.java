package com.finance_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
	}

}
