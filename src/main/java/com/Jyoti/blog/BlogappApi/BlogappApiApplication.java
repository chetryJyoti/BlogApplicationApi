package com.Jyoti.blog.BlogappApi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogappApiApplication.class, args);
		System.out.println("------------------------------Server started------------------------------");
	}

	@Bean
	public ModelMapper modalMapper(){
		return new ModelMapper();
	}

}
