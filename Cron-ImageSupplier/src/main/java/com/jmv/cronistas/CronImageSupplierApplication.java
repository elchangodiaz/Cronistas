package com.jmv.cronistas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CronImageSupplierApplication {
    private final static Logger logger = LoggerFactory.getLogger(CronImageSupplierApplication.class);

    
	public static void main(String[] args) {
		SpringApplication.run(CronImageSupplierApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
				//registry.addMapping("/**").allowedOrigins("https://cronistastopilejo-d617b.web.app").allowedMethods("*").allowedHeaders("*");

			}
		};
	}
	
}